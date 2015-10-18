package com.example.outsider.mathapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import static android.widget.AdapterView.*;

public class geometry extends AppCompatActivity
        implements OnClickListener{
    private CanvasView canvas;

    private EditText width, height; // We're dynamically adding these, so we want to keep track of them this way, or else we won't have access to them later


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry);

        Spinner geometricSelection = (Spinner)findViewById(R.id.geometricShape);
        String [] options = { "Rectangle", "Ellipse", "Triangle"}; // This is our options
        geometricSelection.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, options)); // Set out options to the dropdown
        geometricSelection.setSelection(0); // Set the default selection to the first item in the dropdown

        Button submit = (Button)findViewById(R.id.geometricSubmit);
        submit.setOnClickListener(this); // Tell the button that this class will take care of it's click events

        canvas = (CanvasView)findViewById(R.id.geoCanvas); // We want to keep track of our CanvasView

        // Here we're dynamically adding elements for the sake of showing how it's done.
        // This actually isn't needed, I just did it to show how it would be done
        LinearLayout layout = (LinearLayout)findViewById(R.id.geoInputs);

        TextView widthLbl = new TextView(this);
        widthLbl.setText("Width: ");
        layout.addView(widthLbl);

        width = new EditText(this);
        width.setWidth(150);
        width.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL); // We want only decimal numbers as inputs (like 10.32 for example)
        layout.addView(width);

        TextView heightLbl = new TextView(this);
        heightLbl.setText(" Height: ");
        layout.addView(heightLbl);

        height = new EditText(this);
        height.setWidth(150);
        height.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        layout.addView(height);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_geometry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Spinner menu = (Spinner)findViewById(R.id.geometricShape); // Get our drop down so we can get the selected value

        Double Width = Double.parseDouble(width.getText().toString()); //get the numeric value from our input of width
        Double Height = Double.parseDouble(height.getText().toString());

        canvas.setShape(menu.getSelectedItem().toString(), Width, Height); // Tell our CanvasView that we custom created what we want it to draw
        canvas.invalidate(); // Tell the CanvasView to redraw itself
    }
}

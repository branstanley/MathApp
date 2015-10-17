package com.example.outsider.mathapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import static android.widget.AdapterView.*;

public class geometry extends AppCompatActivity
        implements OnItemSelectedListener, OnClickListener{
    CanvasView canvas;

    EditText width, height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry);

        Spinner geometricSelection = (Spinner)findViewById(R.id.geometricShape);
        String [] options = { "Rectangle", "Ellipse", "Triangle"};
        geometricSelection.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, options));
        geometricSelection.setSelection(0);
        geometricSelection.setOnItemSelectedListener(this);

        Button submit = (Button)findViewById(R.id.geometricSubmit);
        submit.setOnClickListener(this);

        canvas = (CanvasView)findViewById(R.id.geoCanvas);

        LinearLayout layout = (LinearLayout)findViewById(R.id.geoInputs);

        TextView widthLbl = new TextView(this);
        widthLbl.setText("Width: ");
        layout.addView(widthLbl);

        width = new EditText(this);
        width.setWidth(150);
        width.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        Spinner menu = (Spinner)findViewById(R.id.geometricShape);

        Double Width = Double.parseDouble(width.getText().toString());
        Double Height = Double.parseDouble(height.getText().toString());

        canvas.setShape(menu.getSelectedItem().toString(), Width, Height);
        canvas.invalidate();
    }
}

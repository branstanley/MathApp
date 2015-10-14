package com.example.outsider.mathapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class quadratic extends AppCompatActivity
    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratic);

        ((Button)findViewById(R.id.quadCalculate)).setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quadratic, menu);
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
        TextView output = (TextView)findViewById(R.id.quadOutput);
        output.setText("Calculating:\n\nSub Value:\n");

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.HALF_DOWN);

        Double A = Double.parseDouble(((EditText) findViewById(R.id.quadA)).getText().toString());
        Double B = Double.parseDouble( ((EditText) findViewById(R.id.quadB)).getText().toString() );
        Double C = Double.parseDouble( ((EditText) findViewById(R.id.quadC)).getText().toString() );
        Double X1 = 0.0, X2 = 0.0;

        // The quadratic formula is x = (-B ± √(B^2 - 4*A*C))/(2*a)
        Double subValue = Math.pow(B, 2) - 4 * A * C;
        output.setText(output.getText().toString() + df.format(B) + "^2 - 4 * " + df.format(A) + " * " + df.format(C) + " = " + df.format(subValue) + "\n" );
        if(subValue < 0){
            // x is going to be imaginary
            output.setText(output.getText().toString() + "X is imaginary"+ "\n" );

            output.setText(output.getText().toString() + "X1 = (" + "-" + df.format(B) + " + √(" + df.format(subValue) + ")) / " + " ( 2 * " + df.format(A) + " )" + "\n" );
            output.setText(output.getText().toString() + "X1 = (" + df.format(-1 * B) + " + " + df.format(Math.sqrt(Math.abs(subValue))) + "i ) / " + df.format(2 * A) + "\n" );
            output.setText(output.getText().toString() + "X2 = (" + "-" + df.format(B) + " - √(" + df.format(subValue) + ")) / " + " ( 2 * " + df.format(A) + " )"+ "\n" );
            output.setText(output.getText().toString() + "X2 = (" + df.format(-1 * B) + " - " + df.format(Math.sqrt(Math.abs(subValue))) + "i ) / " + df.format(2 * A) + "\n" );


        }
        else{
            output.setText(output.getText().toString() + "X is real"+ "\n" );
            X1 = (-1 * B + Math.sqrt(subValue)) / ( 2 * A);
            X2 = (-1 * B - Math.sqrt(subValue)) / ( 2 * A);

            output.setText(output.getText().toString() + "X1 = (" + "-" + df.format(B) + " + √(" + df.format(subValue) + ")) / " + " ( 2 * " + df.format(A) + " ) = " + df.format(X1) + "\n" );
            output.setText(output.getText().toString() + "X2 = (" + "-" + df.format(B) + " - √(" + df.format(subValue) + ")) / " + " ( 2 * " + df.format(A) + " ) = " + df.format(X2) + "\n" );
        }
    }
}

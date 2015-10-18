package com.example.outsider.mathapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class basicArithmetic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_arithmetic);

        Spinner arithmeticSelection = (Spinner)findViewById(R.id.arithSelect);
        String [] options = { "+", "-", "*", "/"}; // An array containing each of the drop down items
        arithmeticSelection.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, options)); // Add options to dropdown
        arithmeticSelection.setSelection(0); // Set the first option (+) as our default selected item

        setUpOnClickEvent();  // Call the setUpOnClickEvent method
    }

    /****************************************************************************************
     * This Method is used to set up what happens when the Calculate button is clicked
     ****************************************************************************************/
    public void setUpOnClickEvent(){
        // Find the Calculate button
        Button calculate = (Button) findViewById(R.id.arithCalculate);

        // Set up what happens when the button is clicked
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Find the first number we're using to perform arithmetic by looking for its EditText and getting the string stored
                Double value1 = Double.parseDouble( ( (EditText)findViewById(R.id.arith1) ).getText().toString() );
                // Get the second number
                Double value2 = Double.parseDouble( ( (EditText)findViewById(R.id.arith2) ).getText().toString() );

                // Find the TextView where we're going to output the answer
                TextView answer = (TextView)findViewById(R.id.arithAnswer);

                // Look at the arithmetic operation dropdown (it's a Spinner) and find out what character it is
                switch( ( (Spinner)findViewById(R.id.arithSelect) ).getSelectedItem().toString() ){
                    case "+":
                        answer.setText(String.valueOf(value1 + value2));  // Add
                        break;
                    case "-":
                        answer.setText(String.valueOf(value1 - value2)); //Subtract
                        break;
                    case "*":
                        answer.setText(String.valueOf(value1 * value2)); // Multiply
                        break;
                    case "/":
                        if(value2 == 0) //Check if the second value is zero
                            answer.setText("NaN"); // Can't divide by 0, so Not A Number
                        else
                            answer.setText(String.valueOf(value1 / value2)); // The value is not zero, so divide
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic_arithmetic, menu);
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
}

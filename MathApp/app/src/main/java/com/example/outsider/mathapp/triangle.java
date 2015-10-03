package com.example.outsider.mathapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class triangle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle);

        Button calculate = (Button) findViewById(R.id.triangleCalc);
        calculate.setOnClickListener( new listener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_triangle, menu);
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

    private class listener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            TextView output = (TextView)findViewById(R.id.triangleOutput);

            EditText A = (EditText) findViewById(R.id.triA);
            EditText B = (EditText) findViewById(R.id.triB);
            EditText H = (EditText) findViewById(R.id.triH);
            EditText a = (EditText) findViewById(R.id.tria);
            EditText b = (EditText) findViewById(R.id.trib);

            /********************************************************************************************
             * Create an array of Boolean values.  Arrays are 0 indexed, meaning the first entry is 0
             * A Boolean value has two values: true or false
             *
             * ! means not, it will give us the opposite boolean value
             * ex/ !true is false
             *
             * We're checking to see which values were entered on our GUI (Graphical User Interface)
             * ex/ A.getText().toString().isEmpty() is referenced as: checks[0]
             *
             * For this array: A = checks[0], B = checks[1], H = checks[2], a = checks[3], b = checks[4]
             ********************************************************************************************/
            Boolean [] checks = {!A.getText().toString().isEmpty(),!B.getText().toString().isEmpty(),!H.getText().toString().isEmpty(),
                    !a.getText().toString().isEmpty(),!b.getText().toString().isEmpty()};

            // Make sure we have at least one Side's value
            // && is a logical and, meaning both the left and right side values of the && must equal true for that section to be true
            // ex/ if(true && true)  will be true
            // if(true && true && false) will be false
            Boolean check_ABH = checks[0] || checks[1] || checks[2];
            if(!check_ABH){
                output.setText("Must supply one of the side values");
                return; // We want to stop executing this method, since there was an error with the input
            }

            int check_count = 0; // count the number of true Boolean values
            for(Boolean check : checks){
                if(check) // See if check is true
                    ++check_count; // if check is true, add one to our check counter
            }

            if(check_count < 2){ // Check to see if we have enough filled in values
                output.setText("Need at least two values to calculate.");
                return; // We want to stop executing this method, since there was an error with the input
            }

            /********************************************************************************************
             * Calculate missing values, This part gets Crazy if you haven't covered the math, I'm sorry.
             * If you don't know the math, you can either ignore all the math and accept it works,
             * or use this as a chance to learn a bit more about math.
             *
             * When calculates for sides are done with angles, we use SOHCAHTOA:
             *   sin = opposite / hypotenuse
             *   cos = adjacent / hypotenuse
             *   tan = opposite / adjacent
             ********************************************************************************************/
            Double sideA = 0.0, sideB = 0.0, sideH = 0.0, anglea = 0.0, angleb = 0.0;

            if(!checks[0]){
            // in this case we only want to execute this section if checks[0] is false (A has no value)
            // In other words, we need to solve for side A

                // Since we don't have side A, we *MUST* have either side B, side H, or both side B and H
                if(checks[1] && checks[2]){
                    // We have both sides B and H
                    sideB = Double.parseDouble(B.getText().toString()); // Get the value of side B
                    sideH = Double.parseDouble(H.getText().toString()); // Get the value of the hypotenuse
                    sideA = Math.sqrt( Math.pow(sideH, 2) - Math.pow(sideB, 2)); // We can use pythagorean theorem to get side A
                }
                else if(checks[1]){
                    // We have only side B
                    sideB = Double.parseDouble(B.getText().toString()); // Get the value of side B

                    if(checks[3]){
                        // we have angle a
                        anglea = Double.parseDouble(a.getText().toString()); // Get the value of angle a
                        sideA = sideB * Math.tan(Math.toRadians(anglea));  // A = B * tan(a)
                    }
                    else{
                        // We *must* have angle b if we don't have A, H, or a
                        angleb = Double.parseDouble(b.getText().toString()); // Get the value of angle b
                        sideA = sideB / Math.tan(Math.toRadians(angleb)); // A = B / tan(b)
                    }
                }
                else{ // We have only side H
                    sideH = Double.parseDouble(H.getText().toString()); // Get the value of the hypotenuse

                    if(checks[3]){
                        // We have angle a
                        anglea = Double.parseDouble(a.getText().toString()); // Get the value of angle a
                        sideA = Math.sin(Math.toRadians(anglea)) / sideH; // A = sin(a) / H
                    }
                    else{
                        // We *must* have angle b if we don't have A, B, or a
                        angleb = Double.parseDouble(b.getText().toString()); // Get the value of angle b
                        sideA = Math.cos(Math.toRadians(angleb)) / sideH; // A = cos(b) / H
                    }
                }
            }
            else{ // We have a value for side A
                sideA = Double.parseDouble(A.getText().toString()); // Get the value of side A
            }
            sideA = Math.abs(sideA); // make sure we have a positive number

            // We now have the value for side A, from now on we can assume we always have the value of side A
            if(!checks[1]){
                // Side B has no value
                if(checks[2]){
                    // We have a value for the hypotenuse, and it has already been assigned because of our calculation for A
                    sideB = Math.sqrt(Math.pow(sideH, 2) - Math.pow(sideA,2 )); // We can use pythagorean theorem to get side A
                }
                else{
                    // We need to use one of our angles to calculate for side B
                    if(checks[3]){
                        // We have angle a
                        anglea = Double.parseDouble(a.getText().toString()); // Get the value of angle a
                        sideB = sideA / Math.tan(Math.toRadians(anglea)); // B = A / tan(a)
                    }
                    else{
                        // We *must* have angle b if we don't have H, or a
                        angleb = Double.parseDouble(b.getText().toString()); // Get the value of angle b
                        sideB = sideA * Math.tan(Math.toRadians(angleb)); // B = A * tan(b)
                    }
                }
            }
            else{
                sideB = Double.parseDouble(B.getText().toString()); // Get the value of side B
            }
            sideB = Math.abs(sideB); // Make sure we have a positive number

            // We now have a value for both side A and B, and can assume as such for the rest
            if(!checks[2]){
                // We need to calculate our hypotenuse
                sideH = Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2)); // We can use pythagorean theorem to get the hypotenuse
            }
            else{
                sideH = Double.parseDouble(H.getText().toString()); // Get the value of side H
            }
            sideH = Math.abs(sideH); //Make sure we have a positive number

            // We now have a value for sides A, B and the hypotenuse
            if(!checks[3]){
                // angle a has no value
                if(checks[4]){
                    // We have angle b
                    angleb = Double.parseDouble(b.getText().toString()); // Get the value of angle b
                    anglea = 90.0 - angleb; // The angles in a triangle add up to 180, 90 is our right angle, 180 - 90 = 90, so 90 - b = a
                }
                else{
                    // We don't have angle b
                    anglea = Math.toDegrees(Math.asin(sideA / sideH)); // a = arcsin(A / H)
                }
            }
            else{
                anglea = Double.parseDouble(a.getText().toString()); // Get the value of angle a
            }
            anglea = Math.abs(anglea); //Make sure we have a positive number

            // We now have a value for sides A, B, the hypotenuse, and angle a
            if(!checks[4]){
                // We don't have angle b
                angleb = 90.0 - anglea;
            }
            else{
                angleb = Double.parseDouble(b.getText().toString()); // Get the value of angle b
            }
            angleb = Math.abs(angleb); //Make sure we have a positive number

            output.setText("A: " + sideA + "\nB: " + sideB + "\nH: " + sideH + "\na: " + anglea + "\nb: " + angleb); //  \n means newline
        }
    }
}

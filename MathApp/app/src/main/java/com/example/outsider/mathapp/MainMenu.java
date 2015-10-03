package com.example.outsider.mathapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainMenu extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button useMode = (Button) findViewById(R.id.useMode);
        useMode.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
        RadioGroup group = (RadioGroup) findViewById(R.id.modeSelection);
        RadioButton selectedButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        Intent intent;
        switch(selectedButton.getId()){
            case R.id.basicMath:
                intent = new Intent(this, basicArithmetic.class);
                startActivity(intent);
                break;
            case R.id.quadraticRadioButton:
                // Open quadratic
                intent = new Intent(this, quadratic.class);
                startActivity(intent);
                break;
            case R.id.triangleRadioButton:
                // open triangle
                intent = new Intent(this, triangle.class);
                startActivity(intent);
                break;
        }
    }
}

package com.example.androidfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonToStepsActivity(View view) {
        Intent toStepsActivity = new Intent(MainActivity.this, Steps_Activity.class);
        startActivity(toStepsActivity);
    }


    public void buttonToMacroActivity(View view) {
        Intent toMacroActivity = new Intent(MainActivity.this, Macronutrients_Activity.class);
        startActivity(toMacroActivity);
    }


    public void buttonToMicroActivity(View view) {
        Intent toMicroActivity = new Intent(MainActivity.this, Micronutrients_Activity.class);
        startActivity(toMicroActivity);
    }
}
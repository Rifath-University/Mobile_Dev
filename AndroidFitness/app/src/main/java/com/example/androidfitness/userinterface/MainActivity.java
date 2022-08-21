package com.example.androidfitness.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidfitness.R;
import com.example.androidfitness.userinterface.macronutrients.Macronutrients_Activity;
import com.example.androidfitness.userinterface.micronutrients.Micronutrients_Activity;
import com.example.androidfitness.userinterface.sleep.Sleep_Activity;
import com.example.androidfitness.userinterface.steps.Steps_Activity;

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


    public void buttonToSleepActivity(View view) {
        Intent toSleepActivity = new Intent (MainActivity.this, Sleep_Activity.class);
        startActivity(toSleepActivity);
    }

    public void buttonToLocation(View view) {
        Intent toLocationActivity = new Intent(MainActivity.this, Locations_Activity.class);
        startActivity(toLocationActivity);
    }
}
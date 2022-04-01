package com.example.androidfitness2;

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
        Intent toStepsActivity = new Intent(MainActivity.this, Activity_Steps.class);
        startActivity(toStepsActivity);
    }
}
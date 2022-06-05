package com.example.androidfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Steps_AddActivity extends AppCompatActivity {

    EditText date_input, steps_input, calories_input;
    Button steps_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_add_form);

        double stepsToCalories = 0.004;
        date_input = findViewById(R.id.date_input);
        steps_input = findViewById(R.id.steps_input);
        calories_input = findViewById(R.id.calories_input);
        steps_add_button = findViewById(R.id.save_steps_button);
        steps_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Steps_MyDatabaseHelper stepsDB = new Steps_MyDatabaseHelper(Steps_AddActivity.this);
                stepsDB.addBook(date_input.getText().toString().trim(),
                                Integer.valueOf(steps_input.getText().toString().trim()),
                                Integer.valueOf(calories_input.getText().toString().trim()));


            }
        });

    }
}
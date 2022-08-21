package com.example.androidfitness.userinterface.steps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidfitness.R;
import com.example.androidfitness.logic.steps.StepsLogic;
import com.example.androidfitness.logic.steps.StepsLogicImpl;

public class Steps_AddActivity extends AppCompatActivity {

    private EditText date_input, steps_input, calories_input;
    private Button steps_add_button;
    private StepsLogic stepsLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_add_form);
        stepsLogic = new StepsLogicImpl(this);

        date_input = findViewById(R.id.date_input);
        steps_input = findViewById(R.id.steps_input);
        calories_input = findViewById(R.id.calories_input);
        steps_add_button = findViewById(R.id.save_steps_button);
        steps_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isStepsAddedSuccess = stepsLogic.addSteps(date_input.getText().toString().trim(),
                                Integer.valueOf(steps_input.getText().toString().trim()),
                                Integer.valueOf(calories_input.getText().toString().trim()));
                if (isStepsAddedSuccess) {
                    Toast.makeText(Steps_AddActivity.this, "Steps Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Steps_AddActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
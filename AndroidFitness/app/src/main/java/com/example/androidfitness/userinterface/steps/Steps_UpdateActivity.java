package com.example.androidfitness.userinterface.steps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidfitness.R;
import com.example.androidfitness.logic.steps.StepsLogic;
import com.example.androidfitness.logic.steps.StepsLogicImpl;

public class Steps_UpdateActivity extends AppCompatActivity {

    private EditText date_input_update, steps_input_update, calories_input_update;
    private Button update_button, delete_button;
    private String id, date, daily_steps, calories;
    private StepsLogic stepsLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_update);
        stepsLogic = new StepsLogicImpl(this);

        date_input_update = findViewById(R.id.date_input_update);
        steps_input_update = findViewById(R.id.steps_input_update);
        calories_input_update = findViewById(R.id.calories_input_update);
        update_button = findViewById(R.id.update_steps_button);
        delete_button = findViewById(R.id.delete_steps_button);
        getAndSetStepIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(date);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = date_input_update.getText().toString().trim();
                daily_steps = steps_input_update.getText().toString().trim();
                calories = calories_input_update.getText().toString().trim();
                boolean isUpdateSuccess = stepsLogic.updateStepsData(id, date, daily_steps, calories);

                if (!isUpdateSuccess) {
                    Toast.makeText(Steps_UpdateActivity.this, "Failed to Update steps", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Steps_UpdateActivity.this, "Steps Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    private void getAndSetStepIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("date") &&
            getIntent().hasExtra("daily_steps") && getIntent().hasExtra("calories")) {
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            daily_steps = getIntent().getStringExtra("daily_steps");
            calories = getIntent().getStringExtra("calories");

            // Setting intent data
            date_input_update.setText(date);
            steps_input_update.setText(daily_steps);
            calories_input_update.setText(calories);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + date + " ?");
        builder.setMessage("Are you sure you want to delete this data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean isDeleteSuccess = stepsLogic.deleteRowSteps(id);
                if (!isDeleteSuccess) {
                    Toast.makeText(Steps_UpdateActivity.this, "Failed to delete steps", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Steps_UpdateActivity.this, "Steps Deleted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.create().show();
    }
}
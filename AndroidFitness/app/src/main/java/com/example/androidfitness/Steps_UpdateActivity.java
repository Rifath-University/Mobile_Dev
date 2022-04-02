package com.example.androidfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Steps_UpdateActivity extends AppCompatActivity {

    EditText date_input_update, steps_input_update, calories_input_update;
    Button update_button, delete_button;
    String id, date, daily_steps, calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_update);

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
                Steps_MyDatabaseHelper updateSteps = new Steps_MyDatabaseHelper(Steps_UpdateActivity.this);
                date = date_input_update.getText().toString().trim();
                daily_steps = steps_input_update.getText().toString().trim();
                calories = calories_input_update.getText().toString().trim();
                updateSteps.updateStepsData(id, date, daily_steps, calories);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetStepIntentData() {
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

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + date + " ?");
        builder.setMessage("Are you sure you want to delete this data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Steps_MyDatabaseHelper deleteStepsRow = new Steps_MyDatabaseHelper(Steps_UpdateActivity.this);
                deleteStepsRow.deleteRowSteps(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
package com.example.androidfitness.userinterface.sleep;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidfitness.R;
import com.example.androidfitness.datalayer.Sleep_MyDatabaseHelper;

public class Sleep_UpdateActivity extends AppCompatActivity {

    private EditText sleep_date_input_update, sleep_totalSleep_input_update, sleep_deepSleep_input_update;
    private Button sleep_update_button, sleep_delete_button;
    private String id, date, totalSleepUpdate, deepSleepUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_update);

        sleep_date_input_update = findViewById(R.id.sleep_date_input_update);
        sleep_totalSleep_input_update = findViewById(R.id.totalSleep_input_update);
        sleep_deepSleep_input_update = findViewById(R.id.deepSleep_input_update);
        sleep_update_button = findViewById(R.id.update_sleep_button);
        sleep_delete_button = findViewById(R.id.delete_sleep_button);
        getAndSetStepIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(date);
        }

        sleep_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sleep_MyDatabaseHelper updateSleep = new Sleep_MyDatabaseHelper(Sleep_UpdateActivity.this);
                date = sleep_date_input_update.getText().toString().trim();
                totalSleepUpdate = sleep_totalSleep_input_update.getText().toString().trim();
                deepSleepUpdate = sleep_deepSleep_input_update.getText().toString().trim();
                updateSleep.updateSleepData(id, date, totalSleepUpdate, deepSleepUpdate);
            }
        });

        sleep_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    public void getAndSetStepIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("date") &&
                getIntent().hasExtra("totalSleep") && getIntent().hasExtra("deepSleep")) {
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            totalSleepUpdate = getIntent().getStringExtra("totalSleep");
            deepSleepUpdate = getIntent().getStringExtra("deepSleep");

            // Setting intent data
            sleep_date_input_update.setText(date);
            sleep_totalSleep_input_update.setText(totalSleepUpdate);
            sleep_deepSleep_input_update.setText(deepSleepUpdate);

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
                Sleep_MyDatabaseHelper deleteSleepRow = new Sleep_MyDatabaseHelper(Sleep_UpdateActivity.this);
                deleteSleepRow.deleteRowSleep(id);
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
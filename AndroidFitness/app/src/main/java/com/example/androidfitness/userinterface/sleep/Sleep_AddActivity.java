package com.example.androidfitness.userinterface.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidfitness.R;
import com.example.androidfitness.datalayer.sleep.SleepDbImpl;
import com.example.androidfitness.logic.sleep.SleepLogic;
import com.example.androidfitness.logic.sleep.SleepLogicImpl;

public class Sleep_AddActivity extends AppCompatActivity {

    EditText sleep_date_input, sleep_totalSleep_input, sleep_deepSleep_input;
    Button sleep_add_button;
    private SleepLogic sleepLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_add_form);
        sleepLogic = new SleepLogicImpl(Sleep_AddActivity.this);

        sleep_date_input = findViewById(R.id.sleep_date_input);
        sleep_totalSleep_input = findViewById(R.id.sleep_totalSleep);
        sleep_deepSleep_input = findViewById(R.id.sleep_deepSleep);
        sleep_add_button = findViewById(R.id.save_sleep_button);
        sleep_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSleepAddedSuccess = sleepLogic.addSleep(
                        sleep_date_input.getText().toString().trim(),
                        Float.parseFloat(sleep_totalSleep_input.getText().toString().trim()),
                        Float.parseFloat(sleep_deepSleep_input.getText().toString().trim()));

                if (!isSleepAddedSuccess) {
                    Toast.makeText(Sleep_AddActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                } else {
                     Toast.makeText(Sleep_AddActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
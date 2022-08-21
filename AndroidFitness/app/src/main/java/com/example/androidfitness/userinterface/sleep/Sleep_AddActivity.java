package com.example.androidfitness.userinterface.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidfitness.R;
import com.example.androidfitness.datalayer.Sleep_MyDatabaseHelper;

public class Sleep_AddActivity extends AppCompatActivity {

    EditText sleep_date_input, sleep_totalSleep_input, sleep_deepSleep_input;
    Button sleep_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_add_form);

        sleep_date_input = findViewById(R.id.sleep_date_input);
        sleep_totalSleep_input = findViewById(R.id.sleep_totalSleep);
        sleep_deepSleep_input = findViewById(R.id.sleep_deepSleep);
        sleep_add_button = findViewById(R.id.save_sleep_button);
        sleep_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sleep_MyDatabaseHelper sleepDB = new Sleep_MyDatabaseHelper(Sleep_AddActivity.this);
                sleepDB.addSleep(sleep_date_input.getText().toString().trim(),
                        Integer.valueOf(sleep_totalSleep_input.getText().toString().trim()),
                        Integer.valueOf(sleep_deepSleep_input.getText().toString().trim()));


            }
        });
    }
}
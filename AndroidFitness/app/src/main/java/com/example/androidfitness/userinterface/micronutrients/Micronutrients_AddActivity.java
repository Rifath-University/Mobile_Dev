package com.example.androidfitness.userinterface.micronutrients;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidfitness.R;
import com.example.androidfitness.datalayer.Micronutrients_MyDatabaseHelper;

public class Micronutrients_AddActivity extends AppCompatActivity {

    private EditText vit_b, vit_c, vit_e, magnesium, zinc;
    private Button micro_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micronutrients_add_form);

        vit_b = findViewById(R.id.vitB_input_add);
        vit_c = findViewById(R.id.vitC_input_add);
        vit_e = findViewById(R.id.vitE_input_add);
        magnesium = findViewById(R.id.vitMag_input_add);
        zinc = findViewById(R.id.vitZinc_input_add);

        micro_add_button = findViewById(R.id.save_micro_button);
        micro_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Micronutrients_MyDatabaseHelper microDB = new Micronutrients_MyDatabaseHelper(Micronutrients_AddActivity.this);
                microDB.addMicronutrients(
                        Float.parseFloat(vit_b.getText().toString().trim()),
                        Float.parseFloat(vit_c.getText().toString().trim()),
                        Float.parseFloat(vit_e.getText().toString().trim()),
                        Float.parseFloat(magnesium.getText().toString().trim()),
                        Float.parseFloat(zinc.getText().toString().trim())
                );
            }
        });
    }
}
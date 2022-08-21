package com.example.androidfitness.userinterface.macronutrients;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidfitness.R;
import com.example.androidfitness.datalayer.Macronutrients_MyDatabaseHelper;

public class Macronutrients_AddActivity extends AppCompatActivity {

    private EditText protein_input, fat_input, carbs_input, fibre_input, salt_input;
    private Button macronutrients_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macronutrients_add_form);

        protein_input = findViewById(R.id.protein_input);
        fat_input = findViewById(R.id.fat_input);
        carbs_input = findViewById(R.id.carbs_input);
        fibre_input = findViewById(R.id.fibre_input);
        salt_input = findViewById(R.id.salt_input);
        macronutrients_add_button = findViewById(R.id.macronutrients_add_btn);

        macronutrients_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Macronutrients_MyDatabaseHelper macronutrientsDB = new Macronutrients_MyDatabaseHelper(Macronutrients_AddActivity.this);
                macronutrientsDB.addMacronutrients(
                        Integer.valueOf(protein_input.getText().toString().trim()),
                        Integer.valueOf(fat_input.getText().toString().trim()),
                        Integer.valueOf(carbs_input.getText().toString().trim()),
                        Integer.valueOf(fibre_input.getText().toString().trim()),
                        Integer.valueOf(salt_input.getText().toString().trim())
                );
            }
        });
    }
}

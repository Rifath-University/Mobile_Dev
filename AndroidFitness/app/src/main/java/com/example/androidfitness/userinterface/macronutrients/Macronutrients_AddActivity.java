package com.example.androidfitness.userinterface.macronutrients;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidfitness.R;
import com.example.androidfitness.datalayer.macronutrients.MacronutrientsDbImpl;
import com.example.androidfitness.logic.macronutrients.MacronutrientsLogic;
import com.example.androidfitness.logic.macronutrients.MacronutrientsLogicImpl;

public class Macronutrients_AddActivity extends AppCompatActivity {

    private EditText protein_input, fat_input, carbs_input, fibre_input, salt_input;
    private Button macronutrients_add_button;
    private MacronutrientsLogic macroLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macronutrients_add_form);
        macroLogic = new MacronutrientsLogicImpl(this);

        protein_input = findViewById(R.id.protein_input);
        fat_input = findViewById(R.id.fat_input);
        carbs_input = findViewById(R.id.carbs_input);
        fibre_input = findViewById(R.id.fibre_input);
        salt_input = findViewById(R.id.salt_input);
        macronutrients_add_button = findViewById(R.id.macronutrients_add_btn);

        macronutrients_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isMacronutrientsAddedSuccess = macroLogic.addMacronutrients(
                        Integer.valueOf(protein_input.getText().toString().trim()),
                        Integer.valueOf(fat_input.getText().toString().trim()),
                        Integer.valueOf(carbs_input.getText().toString().trim()),
                        Integer.valueOf(fibre_input.getText().toString().trim()),
                        Integer.valueOf(salt_input.getText().toString().trim()));

                if (!isMacronutrientsAddedSuccess) {
                    Toast.makeText(Macronutrients_AddActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Macronutrients_AddActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

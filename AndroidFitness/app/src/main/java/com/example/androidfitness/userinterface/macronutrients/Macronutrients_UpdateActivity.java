package com.example.androidfitness.userinterface.macronutrients;

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
import com.example.androidfitness.datalayer.macronutrients.MacronutrientsDbImpl;
import com.example.androidfitness.logic.macronutrients.MacronutrientsLogic;
import com.example.androidfitness.logic.macronutrients.MacronutrientsLogicImpl;

public class Macronutrients_UpdateActivity extends AppCompatActivity {

    private EditText protein_update, fat_update, carbs_update, fibre_update, salt_update;
    private Button update_button, delete_button;
    private String id, protein, fat, carbs, fibre, salt;
    private MacronutrientsLogic macroLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macronutrients_update);

        macroLogic = new MacronutrientsLogicImpl(this);
        protein_update = findViewById(R.id.protein_update);
        fat_update = findViewById(R.id.fat_update);
        carbs_update = findViewById(R.id.carbs_update);
        fibre_update = findViewById(R.id.fibre_update);
        salt_update = findViewById(R.id.salt_update);
        update_button = findViewById(R.id.update_macro_btn);
        delete_button = findViewById(R.id.delete_macro_btn);
        getAndSetMacronutrientsIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(id);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                protein = protein_update.getText().toString().trim();
                fat = fat_update.getText().toString().trim();
                carbs = carbs_update.getText().toString().trim();
                fibre = fibre_update.getText().toString().trim();
                salt = salt_update.getText().toString().trim();
                boolean isUpdateSuccess = macroLogic.updateMacroData(id, protein, fat, carbs, fibre, salt);

                if (!isUpdateSuccess) {
                    Toast.makeText(Macronutrients_UpdateActivity.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Macronutrients_UpdateActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
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

    public void getAndSetMacronutrientsIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("protein") &&
                getIntent().hasExtra("fat") && getIntent().hasExtra("carbs")
                && getIntent().hasExtra("fibre") && getIntent().hasExtra("salt")) {
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            protein = getIntent().getStringExtra("protein");
            fat = getIntent().getStringExtra("fat");
            carbs = getIntent().getStringExtra("carbs");
            fibre = getIntent().getStringExtra("fibre");
            salt = getIntent().getStringExtra("salt");


            // Setting intent data
            protein_update.setText(protein);
            fat_update.setText(fat);
            carbs_update.setText(carbs);
            fibre_update.setText(fibre);
            salt_update.setText(salt);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + id + " ?");
        builder.setMessage("Are you sure you want to delete this data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean isDeleteSuccess = macroLogic.deleteRowMacro(id);

                if (!isDeleteSuccess) {
                    Toast.makeText(Macronutrients_UpdateActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Macronutrients_UpdateActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
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
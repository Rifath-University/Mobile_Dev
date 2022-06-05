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

public class Macronutrients_UpdateActivity extends AppCompatActivity {

    EditText protein_update, fat_update, carbs_update, fibre_update, salt_update;
    Button update_button, delete_button;
    String id, protein, fat, carbs, fibre, salt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macronutrients_update);

        protein_update = findViewById(R.id.protein_update);
        fat_update = findViewById(R.id.fat_update);
        carbs_update = findViewById(R.id.carbs_update);
        fibre_update = findViewById(R.id.fibre_update);
        salt_update = findViewById(R.id.salt_update);
        update_button = findViewById(R.id.update_macro_btn);
        delete_button = findViewById(R.id.delete_macro_btn);
        getAndSetStepIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(id);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Macronutrients_MyDatabaseHelper updateMacros = new Macronutrients_MyDatabaseHelper(Macronutrients_UpdateActivity.this);
                protein = protein_update.getText().toString().trim();
                fat = fat_update.getText().toString().trim();
                carbs = carbs_update.getText().toString().trim();
                fibre = fibre_update.getText().toString().trim();
                salt = salt_update.getText().toString().trim();
                updateMacros.updateMacroData(id, protein, fat, carbs, fibre, salt);
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

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + id + " ?");
        builder.setMessage("Are you sure you want to delete this data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Macronutrients_MyDatabaseHelper deleteMacroRow = new Macronutrients_MyDatabaseHelper(Macronutrients_UpdateActivity.this);
                deleteMacroRow.deleteRowMacro(id);
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
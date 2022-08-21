package com.example.androidfitness.userinterface.micronutrients;

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
import com.example.androidfitness.datalayer.Micronutrients_MyDatabaseHelper;

public class Micronutrients_UpdateActivity extends AppCompatActivity {

    private EditText vitB_update, vitC_update, vitE_update, magnesium_update, zinc_update;
    private Button micro_update_button, micro_delete_button;
    private String vit_id, vitB, vitC, vitE, magnesium, zinc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micronutrients_update);

        vitB_update = findViewById(R.id.vitB_update);
        vitC_update = findViewById(R.id.vitC_update);
        vitE_update = findViewById(R.id.vitE_update);
        magnesium_update = findViewById(R.id.magnesium_update);
        zinc_update = findViewById(R.id.zinc_update);

        micro_update_button = findViewById(R.id.update_micro_btn);
        micro_delete_button = findViewById(R.id.delete_micro_btn);
        getAndSetMicroIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(vit_id);
        }

        micro_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Micronutrients_MyDatabaseHelper updateMicro = new Micronutrients_MyDatabaseHelper(Micronutrients_UpdateActivity.this);
                vitB = vitB_update.getText().toString().trim();
                vitC = vitC_update.getText().toString().trim();
                vitE = vitE_update.getText().toString().trim();
                magnesium = magnesium_update.getText().toString().trim();
                zinc = zinc_update.getText().toString().trim();

                updateMicro.updateMicroData(vit_id, vitB, vitC, vitE, magnesium, zinc);
            }
        });

        micro_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    public void getAndSetMicroIntentData() {
        if (getIntent().hasExtra("micro_id") && getIntent().hasExtra("vitB") &&
                getIntent().hasExtra("vitC") && getIntent().hasExtra("vitE")
                && getIntent().hasExtra("magnesium") && getIntent().hasExtra("zinc")) {
            //Getting data from intent
            vit_id = getIntent().getStringExtra("micro_id");
            vitB = getIntent().getStringExtra("vitB");
            vitC = getIntent().getStringExtra("vitC");
            vitE = getIntent().getStringExtra("vitE");
            magnesium = getIntent().getStringExtra("magnesium");
            zinc = getIntent().getStringExtra("zinc");


            // Setting intent data
            vitB_update.setText(vitB);
            vitC_update.setText(vitC);
            vitE_update.setText(vitE);
            magnesium_update.setText(magnesium);
            zinc_update.setText(zinc);


        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + vit_id + " ?");
        builder.setMessage("Are you sure you want to delete this data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Micronutrients_MyDatabaseHelper deleteMicroRow = new Micronutrients_MyDatabaseHelper(Micronutrients_UpdateActivity.this);
                deleteMicroRow.deleteRowMicro(vit_id);
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
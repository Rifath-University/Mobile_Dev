package com.example.androidfitness.userinterface.micronutrients;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidfitness.R;
import com.example.androidfitness.datalayer.micronutrients.MicronutrientsDbImpl;
import com.example.androidfitness.logic.micronutrients.MicronutrientsLogic;
import com.example.androidfitness.logic.micronutrients.MicronutrientsLogicImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Micronutrients_Activity extends AppCompatActivity {

    private RecyclerView recyclerView_micro;
    private FloatingActionButton micro_add_button_to_recycler, MicroInfoButton;
    private ArrayList<String> micro_id, vit_b, vit_c, vit_e, magnesium, zinc;
    private Micronutrients_CustomAdapter _micronutrients_customAdapter;
    private MicronutrientsLogic microLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micronutrients);
        recyclerView_micro = findViewById(R.id.micronutrientsRecyclerView);
        micro_add_button_to_recycler = findViewById(R.id.micronutrients_Add_Button_To_List);
        micro_add_button_to_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addMicroActivityIntent = new Intent(Micronutrients_Activity.this, Micronutrients_AddActivity.class);
                startActivity(addMicroActivityIntent);
            }
        });

        MicroInfoButton = findViewById(R.id.MicroInfoButton);
        MicroInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                microOpenDialog();
            }
        });

        microLogic = new MicronutrientsLogicImpl(Micronutrients_Activity.this);
        micro_id = new ArrayList<>();
        vit_b = new ArrayList<>();
        vit_c = new ArrayList<>();
        vit_e = new ArrayList<>();
        magnesium = new ArrayList<>();
        zinc = new ArrayList<>();

        storeDataInArrays();
        _micronutrients_customAdapter = new Micronutrients_CustomAdapter(Micronutrients_Activity.this,this , micro_id, vit_b, vit_c, vit_e, magnesium, zinc);
        recyclerView_micro.setAdapter(_micronutrients_customAdapter);
        recyclerView_micro.setLayoutManager(new LinearLayoutManager(Micronutrients_Activity.this));
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    public void microOpenDialog() {
        MicroInfoDialog MicroDialog = new MicroInfoDialog();
        MicroDialog.show(getSupportFragmentManager(), "Micronutrients Information");
    }

    private void storeDataInArrays() {
        Cursor cursor_micro = microLogic.microReadAllData();
        if (cursor_micro.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor_micro.moveToNext()) {
                micro_id.add(cursor_micro.getString(0));
                vit_b.add(cursor_micro.getString(1));
                vit_c.add(cursor_micro.getString(2));
                vit_e.add(cursor_micro.getString(3));
                magnesium.add(cursor_micro.getString(4));
                zinc.add(cursor_micro.getString(5));

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all the data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                microLogic.deleteAllMicroData();
                Intent deleteAll = new Intent(Micronutrients_Activity.this, Micronutrients_Activity.class);
                startActivity(deleteAll);
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
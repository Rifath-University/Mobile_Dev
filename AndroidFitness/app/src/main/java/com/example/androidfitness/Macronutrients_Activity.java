package com.example.androidfitness;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Macronutrients_Activity extends AppCompatActivity {

    RecyclerView recyclerView_Macronutrients;
    FloatingActionButton macronutrients_Add_Data_To_List, MacroInfoButton;
    Macronutrients_MyDatabaseHelper macroDB;
    ArrayList<String> macro_id, macro_protein, macro_fat, macro_carbs, macro_fibre, macro_salt;
    Macronutrients_CustomAdapter macroCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macronutrients);

        recyclerView_Macronutrients = findViewById(R.id.macronutrientsRecyclerView);
        macronutrients_Add_Data_To_List = findViewById(R.id.macronutrients_Add_Button_To_List);
        macronutrients_Add_Data_To_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent macroAddForm = new Intent(Macronutrients_Activity.this, Macronutrients_AddActivity.class);
                startActivity(macroAddForm);
            }
        });

        MacroInfoButton = findViewById(R.id.MacroInfoButton);
        MacroInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                macroOpenDialog();
            }
        });

        macroDB = new Macronutrients_MyDatabaseHelper(Macronutrients_Activity.this);
        macro_id = new ArrayList<>();
        macro_protein = new ArrayList<>();
        macro_fat = new ArrayList<>();
        macro_carbs = new ArrayList<>();
        macro_fibre = new ArrayList<>();
        macro_salt = new ArrayList<>();

        storeDataInArrays();
        macroCustomAdapter = new Macronutrients_CustomAdapter(Macronutrients_Activity.this,this , macro_id, macro_protein, macro_fat, macro_carbs, macro_fibre, macro_salt);
        recyclerView_Macronutrients.setAdapter(macroCustomAdapter);
        recyclerView_Macronutrients.setLayoutManager(new LinearLayoutManager(Macronutrients_Activity.this));
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    public void macroOpenDialog() {
        MacroInfoDialog MacroDialog = new MacroInfoDialog();
        MacroDialog.show(getSupportFragmentManager(),"Macronutrients Information");
    }

    void storeDataInArrays() {
        Cursor cursor_macronutrients = macroDB.macronutrientsReadAllData();
        if (cursor_macronutrients.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor_macronutrients.moveToNext()) {
                macro_id.add(cursor_macronutrients.getString(0));
                macro_protein.add(cursor_macronutrients.getString(1));
                macro_fat.add(cursor_macronutrients.getString(2));
                macro_carbs.add(cursor_macronutrients.getString(3));
                macro_fibre.add(cursor_macronutrients.getString(4));
                macro_salt.add(cursor_macronutrients.getString(5));
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

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all the data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Macronutrients_MyDatabaseHelper allMacroDB = new Macronutrients_MyDatabaseHelper(Macronutrients_Activity.this);
                allMacroDB.deleteAllMacroData();
                Intent deleteAll = new Intent(Macronutrients_Activity.this, Macronutrients_Activity.class);
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
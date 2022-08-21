package com.example.androidfitness.userinterface.steps;

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
import com.example.androidfitness.logic.steps.StepsLogic;
import com.example.androidfitness.logic.steps.StepsLogicImpl;
import com.example.androidfitness.userinterface.micronutrients.MicroInfoDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Steps_Activity extends AppCompatActivity {

    private RecyclerView recyclerView_steps;
    private FloatingActionButton steps_add_button_to_recycler, StepsInfoButton;
    private ArrayList<String> steps_id, steps_date, steps_daily, steps_calories;
    private Steps_CustomAdapter _stepsCustomAdapter;
    private StepsLogic stepsLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        recyclerView_steps = findViewById(R.id.stepsRecyclerView);
        steps_add_button_to_recycler = findViewById(R.id.steps_Add_Button_To_List);
        steps_add_button_to_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addStepsActivityIntent = new Intent(Steps_Activity.this, Steps_AddActivity.class);
                startActivity(addStepsActivityIntent);
            }
        });

        stepsLogic = new StepsLogicImpl(Steps_Activity.this);
        steps_id = new ArrayList<>();
        steps_date = new ArrayList<>();
        steps_daily = new ArrayList<>();
        steps_calories = new ArrayList<>();

        storeDataInArrays();
        _stepsCustomAdapter = new Steps_CustomAdapter(Steps_Activity.this,this , steps_id, steps_date, steps_daily, steps_calories);
        recyclerView_steps.setAdapter(_stepsCustomAdapter);
        recyclerView_steps.setLayoutManager(new LinearLayoutManager(Steps_Activity.this));

        StepsInfoButton = findViewById(R.id.StepsInfoButton);
        StepsInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepsOpenDialog();
            }
        });
    }

    public void stepsOpenDialog() {
        StepsInfoDialog stepsInfoDialogDialog = new StepsInfoDialog();
        stepsInfoDialogDialog.show(getSupportFragmentManager(), "Steps Information");
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    private void storeDataInArrays() {
        Cursor cursor_steps = stepsLogic.readAllStepsData();
        if (cursor_steps.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor_steps.moveToNext()) {
                steps_id.add(cursor_steps.getString(0));
                steps_date.add(cursor_steps.getString(1));
                steps_daily.add(cursor_steps.getString(2));
                steps_calories.add(cursor_steps.getString(3));
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

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all the data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stepsLogic.deleteAllSteps();
                Intent backToStepsActivity = new Intent(Steps_Activity.this, Steps_Activity.class);
                startActivity(backToStepsActivity);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        builder.create().show();
    }
}
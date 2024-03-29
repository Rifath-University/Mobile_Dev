package com.example.androidfitness.userinterface.sleep;

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
import com.example.androidfitness.datalayer.sleep.SleepDbImpl;
import com.example.androidfitness.logic.sleep.SleepLogic;
import com.example.androidfitness.logic.sleep.SleepLogicImpl;
import com.example.androidfitness.userinterface.micronutrients.MicroInfoDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Sleep_Activity extends AppCompatActivity {

    private RecyclerView recyclerView_Sleep;
    private FloatingActionButton sleep_Add_To_List, SleepInfoButton;
    private ArrayList<String> sleep_id, sleep_date, sleep_totalSleep, sleep_deepSleep;
    private Sleep_CustomAdapter _sleepCustomAdapter;
    private SleepLogic sleepLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        recyclerView_Sleep = findViewById(R.id.sleepRecyclerView);
        sleep_Add_To_List = findViewById(R.id.sleep_Add_Button_To_List);
        sleep_Add_To_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sleepAddForm = new Intent(Sleep_Activity.this, Sleep_AddActivity.class);
                startActivity(sleepAddForm);
            }
        });

        sleepLogic = new SleepLogicImpl(Sleep_Activity.this);
        sleep_id = new ArrayList<>();
        sleep_date = new ArrayList<>();
        sleep_totalSleep = new ArrayList<>();
        sleep_deepSleep = new ArrayList<>();

        storeDataInArrays();
        _sleepCustomAdapter = new Sleep_CustomAdapter(Sleep_Activity.this,this , sleep_id, sleep_date, sleep_totalSleep, sleep_deepSleep);
        recyclerView_Sleep.setAdapter(_sleepCustomAdapter);
        recyclerView_Sleep.setLayoutManager(new LinearLayoutManager(Sleep_Activity.this));

        SleepInfoButton = findViewById(R.id.SleepInfoButton);
        SleepInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sleepOpenDialog();
            }
        });
    }

    public void sleepOpenDialog() {
        SleepInfoDialog sleepInfoButton = new SleepInfoDialog();
        sleepInfoButton.show(getSupportFragmentManager(), "Sleeping Information");
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    private void storeDataInArrays() {
        Cursor cursor_sleep = sleepLogic.sleepReadAllData();
        if (cursor_sleep.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor_sleep.moveToNext()) {
                sleep_id.add(cursor_sleep.getString(0));
                sleep_date.add(cursor_sleep.getString(1));
                sleep_totalSleep.add(cursor_sleep.getString(2));
                sleep_deepSleep.add(cursor_sleep.getString(3));
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
                sleepLogic.deleteAllSleepData();
                Intent deleteAll = new Intent(Sleep_Activity.this, Sleep_Activity.class);
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
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

public class Sleep_Activity extends AppCompatActivity {

    RecyclerView recyclerView_Sleep;
    FloatingActionButton sleep_Add_To_List;
    Sleep_MyDatabaseHelper sleepDB;
    ArrayList<String> sleep_id, sleep_date, sleep_totalSleep, sleep_deepSleep;
    Sleep_CustomAdapter _sleepCustomAdapter;

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

        sleepDB = new Sleep_MyDatabaseHelper(Sleep_Activity.this);
        sleep_id = new ArrayList<>();
        sleep_date = new ArrayList<>();
        sleep_totalSleep = new ArrayList<>();
        sleep_deepSleep = new ArrayList<>();

        storeDataInArrays();
        _sleepCustomAdapter = new Sleep_CustomAdapter(Sleep_Activity.this,this , sleep_id, sleep_date, sleep_totalSleep, sleep_deepSleep);
        recyclerView_Sleep.setAdapter(_sleepCustomAdapter);
        recyclerView_Sleep.setLayoutManager(new LinearLayoutManager(Sleep_Activity.this));
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor_sleep = sleepDB.sleepReadAllData();
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

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all the data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Sleep_MyDatabaseHelper allSleepDB = new Sleep_MyDatabaseHelper(Sleep_Activity.this);
                allSleepDB.deleteAllSleepData();
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
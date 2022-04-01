package com.example.androidfitness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Activity_Steps extends AppCompatActivity {

    RecyclerView recyclerView_steps;
    FloatingActionButton steps_add_button_to_recycler;
    MyDatabaseHelper_Steps stepsDB;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    CustomAdapter_Steps customAdapter_steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        recyclerView_steps = findViewById(R.id.stepsRecyclerView);
        steps_add_button_to_recycler = findViewById(R.id.steps_Add_Button_To_List);
        steps_add_button_to_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addStepsActivityIntent = new Intent(Activity_Steps.this, AddActivity_Steps.class);
                startActivity(addStepsActivityIntent);
            }
        });

        stepsDB = new MyDatabaseHelper_Steps(Activity_Steps.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        storeDataInArrays();
        customAdapter_steps = new CustomAdapter_Steps(Activity_Steps.this,this , book_id, book_title, book_author, book_pages);
        recyclerView_steps.setAdapter(customAdapter_steps);
        recyclerView_steps.setLayoutManager(new LinearLayoutManager(Activity_Steps.this));
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor_steps = stepsDB.stepsReadAllData();
        if (cursor_steps.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor_steps.moveToNext()) {
                book_id.add(cursor_steps.getString(0));
                book_title.add(cursor_steps.getString(1));
                book_author.add(cursor_steps.getString(2));
                book_pages.add(cursor_steps.getString(3));
            }
        }
    }
}
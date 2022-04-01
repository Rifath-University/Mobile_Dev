package com.example.androidfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity_Steps extends AppCompatActivity {

    EditText title_input_update, author_input_update, pages_input_update;
    Button update_button, delete_button;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_update);

        title_input_update = findViewById(R.id.title_input_update);
        author_input_update = findViewById(R.id.author_input_update);
        pages_input_update = findViewById(R.id.pages_input_update);
        update_button = findViewById(R.id.update_steps_button);
        delete_button = findViewById(R.id.delete_steps_button);
        getAndSetStepIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper_Steps updateSteps = new MyDatabaseHelper_Steps(UpdateActivity_Steps.this);
                title = title_input_update.getText().toString().trim();
                author = author_input_update.getText().toString().trim();
                pages = pages_input_update.getText().toString().trim();
                updateSteps.updateStepsData(id, title, author, pages);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper_Steps deleteStepsRow = new MyDatabaseHelper_Steps(UpdateActivity_Steps.this);
                deleteStepsRow.deleteRowSteps(id);
            }
        });
    }

    void getAndSetStepIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
            getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            // Setting intent data
            title_input_update.setText(title);
            author_input_update.setText(author);
            pages_input_update.setText(pages);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

//    void confirmDialog() {
//        AlertDialog.Builder builder
//    }
}
package com.example.androidfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity_Steps extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button steps_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_add_form);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        steps_add_button = findViewById(R.id.save_steps_button);
        steps_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper_Steps stepsDB = new MyDatabaseHelper_Steps(AddActivity_Steps.this);
                stepsDB.addBook(title_input.getText().toString().trim(),
                                author_input.getText().toString().trim(),
                                Integer.valueOf(pages_input.getText().toString().trim()));
            }
        });

    }
}
package com.example.androidfitness;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Steps_CustomAdapter extends RecyclerView.Adapter<Steps_CustomAdapter.StepsViewHolder> {

    Context context;
    Activity activity_steps;
    ArrayList book_id, book_title, book_author, book_pages;


    Steps_CustomAdapter(Context context, Activity activity, ArrayList book_id,
                        ArrayList book_title, ArrayList book_author, ArrayList book_pages) {
        this.context = context;
        this.activity_steps = activity;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public Steps_CustomAdapter.StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater stepsInflater = LayoutInflater.from(context);
        View stepsView = stepsInflater.inflate(R.layout.activity_steps_recyclerview_row, parent, false);
        return new StepsViewHolder(stepsView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.book_id_text.setText(String.valueOf(book_id.get(position)));
        holder.book_title_text.setText(String.valueOf(book_title.get(position)));
        holder.book_author_text.setText(String.valueOf(book_author.get(position)));
        holder.book_pages_text.setText(String.valueOf(book_pages.get(position)));
        holder.stepsMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int requestCode = 1;
                Intent updateIntentActivity = new Intent(context, Steps_UpdateActivity.class);
                updateIntentActivity.putExtra("id", String.valueOf(book_id.get(position)));
                updateIntentActivity.putExtra("title", String.valueOf(book_title.get(position)));
                updateIntentActivity.putExtra("author", String.valueOf(book_author.get(position)));
                updateIntentActivity.putExtra("pages", String.valueOf(book_pages.get(position)));
                activity_steps.startActivityForResult(updateIntentActivity, requestCode);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_text, book_title_text, book_author_text, book_pages_text;
        LinearLayout stepsMainLayout;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_text = itemView.findViewById(R.id.book_id_text);
            book_title_text = itemView.findViewById(R.id.book_title_text);
            book_author_text = itemView.findViewById(R.id.book_author_text);
            book_pages_text = itemView.findViewById(R.id.book_pages_text);
            stepsMainLayout = itemView.findViewById(R.id.stepsMainLayout);
        }
    }
}

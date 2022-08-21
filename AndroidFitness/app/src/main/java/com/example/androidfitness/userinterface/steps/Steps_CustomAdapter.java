package com.example.androidfitness.userinterface.steps;

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

import com.example.androidfitness.R;

import java.util.ArrayList;

public class Steps_CustomAdapter extends RecyclerView.Adapter<Steps_CustomAdapter.StepsViewHolder> {

    private Context context;
    private Activity activity_steps;
    private ArrayList steps_id, steps_date, steps_daily, steps_calories;


    public Steps_CustomAdapter(Context context, Activity activity, ArrayList steps_id,
                        ArrayList steps_date, ArrayList steps_daily, ArrayList steps_calories) {
        this.context = context;
        this.activity_steps = activity;
        this.steps_id = steps_id;
        this.steps_date = steps_date;
        this.steps_daily = steps_daily;
        this.steps_calories = steps_calories;
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

        holder.steps_id_text.setText(String.valueOf(steps_id.get(position)));
        holder.steps_date_text.setText(String.valueOf(steps_date.get(position)));
        holder.steps_daily_text.setText(String.valueOf(steps_daily.get(position)));
        holder.steps_calories_text.setText(String.valueOf(steps_calories.get(position)));
        holder.stepsMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int requestCode = 1;
                Intent updateIntentActivity = new Intent(context, Steps_UpdateActivity.class);
                updateIntentActivity.putExtra("id", String.valueOf(steps_id.get(position)));
                updateIntentActivity.putExtra("date", String.valueOf(steps_date.get(position)));
                updateIntentActivity.putExtra("daily_steps", String.valueOf(steps_daily.get(position)));
                updateIntentActivity.putExtra("calories", String.valueOf(steps_calories.get(position)));
                activity_steps.startActivityForResult(updateIntentActivity, requestCode);
            }
        });
    }

    @Override
    public int getItemCount() {
        return steps_id.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder {

        TextView steps_id_text, steps_date_text, steps_daily_text, steps_calories_text;
        LinearLayout stepsMainLayout;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            steps_id_text = itemView.findViewById(R.id.steps_id_text);
            steps_date_text = itemView.findViewById(R.id.steps_date_text);
            steps_daily_text = itemView.findViewById(R.id.steps_daily_text);
            steps_calories_text = itemView.findViewById(R.id.steps_calories_text);
            stepsMainLayout = itemView.findViewById(R.id.stepsMainLayout);
        }
    }
}

package com.example.androidfitness.userinterface.sleep;

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

public class Sleep_CustomAdapter extends RecyclerView.Adapter<Sleep_CustomAdapter.SleepViewHolder>{

    private Context context;
    private Activity activity_sleep;
    private ArrayList sleep_id, sleep_date, sleep_totalSleep, sleep_deepSleep;

    public Sleep_CustomAdapter(Context context, Activity activity, ArrayList sleep_id,
                        ArrayList sleep_date, ArrayList sleep_totalSleep, ArrayList sleep_deepSleep) {
        this.context = context;
        this.activity_sleep = activity;
        this.sleep_id = sleep_id;
        this.sleep_date = sleep_date;
        this.sleep_totalSleep = sleep_totalSleep;
        this.sleep_deepSleep = sleep_deepSleep;
    }

    @NonNull
    @Override
    public Sleep_CustomAdapter.SleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater sleepInflater = LayoutInflater.from(context);
        View sleepView = sleepInflater.inflate(R.layout.activity_sleep_recyclerview_row, parent, false);
        return new Sleep_CustomAdapter.SleepViewHolder(sleepView);
    }

    @Override
    public void onBindViewHolder(@NonNull Sleep_CustomAdapter.SleepViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.sleep_id_text.setText(String.valueOf(sleep_id.get(position)));
        holder.sleep_date_text.setText(String.valueOf(sleep_date.get(position)));
        holder.sleep_totalSleep_text.setText(String.valueOf(sleep_totalSleep.get(position)));
        holder.sleep_deepSleep_text.setText(String.valueOf(sleep_deepSleep.get(position)));
        holder.sleepMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int requestCode = 1;
                Intent updateIntentActivity = new Intent(context, Sleep_UpdateActivity.class);
                updateIntentActivity.putExtra("id", String.valueOf(sleep_id.get(position)));
                updateIntentActivity.putExtra("date", String.valueOf(sleep_date.get(position)));
                updateIntentActivity.putExtra("totalSleep", String.valueOf(sleep_totalSleep.get(position)));
                updateIntentActivity.putExtra("deepSleep", String.valueOf(sleep_deepSleep.get(position)));
                activity_sleep.startActivityForResult(updateIntentActivity, requestCode);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sleep_id.size();
    }

    public class SleepViewHolder extends RecyclerView.ViewHolder {

        TextView sleep_id_text, sleep_date_text, sleep_totalSleep_text, sleep_deepSleep_text;
        LinearLayout sleepMainLayout;

        public SleepViewHolder(@NonNull View itemView) {
            super(itemView);
            sleep_id_text = itemView.findViewById(R.id.sleep_id_text);
            sleep_date_text = itemView.findViewById(R.id.sleep_date_text);
            sleep_totalSleep_text = itemView.findViewById(R.id.sleep_totalSleep_text);
            sleep_deepSleep_text = itemView.findViewById(R.id.sleep_deepSleep_text);
            sleepMainLayout = itemView.findViewById(R.id.sleepMainLayout);
        }
    }
}

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

public class Macronutrients_CustomAdapter extends RecyclerView.Adapter<Macronutrients_CustomAdapter.MacronutrientsViewHolder> {
    Context context;
    Activity activity_macronutrients;
    ArrayList macro_id, macro_protein, macro_fat, macro_carbs, macro_fibre, macro_salt;

    Macronutrients_CustomAdapter(Context context, Activity activity, ArrayList macro_id,
                                 ArrayList macro_protein, ArrayList macro_fat, ArrayList macro_carbs,
                                 ArrayList macro_fibre, ArrayList macro_salt) {
        this.context = context;
        this.activity_macronutrients = activity;
        this.macro_id = macro_id;
        this.macro_protein = macro_protein;
        this.macro_fat = macro_fat;
        this.macro_carbs = macro_carbs;
        this.macro_fibre = macro_fibre;
        this.macro_salt = macro_salt;
    }

    @NonNull
    @Override
    public MacronutrientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater macroInflater = LayoutInflater.from(context);
        View macroView = macroInflater.inflate(R.layout.activity_macronutrients_recyclerview_row, parent, false);
        return new MacronutrientsViewHolder(macroView);
    }

    public class MacronutrientsViewHolder extends RecyclerView.ViewHolder {

        TextView macro_id_text, protein_data, fat_data, carbs_data, fibre_data, salt_data;
        LinearLayout macroMainLayout;

        public MacronutrientsViewHolder(@NonNull View itemView) {
            super(itemView);
            macro_id_text = itemView.findViewById(R.id.macro_id_text);
            protein_data = itemView.findViewById(R.id.protein_data);
            fat_data = itemView.findViewById(R.id.fat_data);
            carbs_data = itemView.findViewById(R.id.carbs_data);
            fibre_data = itemView.findViewById(R.id.fibre_data);
            salt_data = itemView.findViewById(R.id.salt_data);
            macroMainLayout = itemView.findViewById(R.id.macroMainLayout);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MacronutrientsViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.macro_id_text.setText(String.valueOf(macro_id.get(position)));
        holder.protein_data.setText(String.valueOf(macro_protein.get(position)));
        holder.fat_data.setText(String.valueOf(macro_fat.get(position)));
        holder.carbs_data.setText(String.valueOf(macro_carbs.get(position)));
        holder.fibre_data.setText(String.valueOf(macro_fibre.get(position)));
        holder.salt_data.setText(String.valueOf(macro_salt.get(position)));
        holder.macroMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int requestCode = 1;
                Intent updateIntentActivity = new Intent(context, Macronutrients_UpdateActivity.class);
                updateIntentActivity.putExtra("id", String.valueOf(macro_id.get(position)));
                updateIntentActivity.putExtra("protein", String.valueOf(macro_protein.get(position)));
                updateIntentActivity.putExtra("fat", String.valueOf(macro_fat.get(position)));
                updateIntentActivity.putExtra("carbs", String.valueOf(macro_carbs.get(position)));
                updateIntentActivity.putExtra("fibre", String.valueOf(macro_fibre.get(position)));
                updateIntentActivity.putExtra("salt", String.valueOf(macro_salt.get(position)));

                activity_macronutrients.startActivityForResult(updateIntentActivity, requestCode);
            }
        });
    }

    @Override
    public int getItemCount() {
        return macro_id.size();
    }
}

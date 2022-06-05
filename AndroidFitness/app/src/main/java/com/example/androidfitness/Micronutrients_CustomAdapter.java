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

public class Micronutrients_CustomAdapter extends RecyclerView.Adapter<Micronutrients_CustomAdapter.MicroViewHolder>{

    private Context context;
    private Activity activity_micronutrients;
    private ArrayList micro_id, vit_b, vit_c, vit_e, magnesium, zinc;


    Micronutrients_CustomAdapter(Context context, Activity activity, ArrayList micro_id,
                        ArrayList vit_b, ArrayList vit_c, ArrayList vit_e,
                        ArrayList magnesium, ArrayList zinc) {
        this.context = context;
        this.activity_micronutrients = activity;
        this.micro_id = micro_id;
        this.vit_b = vit_b;
        this.vit_c = vit_c;
        this.vit_e = vit_e;
        this.magnesium = magnesium;
        this.zinc = zinc;
    }

    @NonNull
    @Override
    public Micronutrients_CustomAdapter.MicroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater microInflater = LayoutInflater.from(context);
        View microView = microInflater.inflate(R.layout.activity_micronutrients_recyclerview_row, parent, false);
        return new MicroViewHolder(microView);
    }

    @Override
    public void onBindViewHolder(@NonNull MicroViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.micro_id_text.setText(String.valueOf(micro_id.get(position)));
        holder.micro_vitB.setText(String.valueOf(vit_b.get(position)));
        holder.micro_vitC.setText(String.valueOf(vit_c.get(position)));
        holder.micro_vitE.setText(String.valueOf(vit_e.get(position)));
        holder.micro_mag.setText(String.valueOf(magnesium.get(position)));
        holder.micro_zinc.setText(String.valueOf(zinc.get(position)));

        holder.microMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int requestCode = 1;
                Intent updateMicroIntentActivity = new Intent(context, Micronutrients_UpdateActivity.class);
                updateMicroIntentActivity.putExtra("micro_id", String.valueOf(micro_id.get(position)));
                updateMicroIntentActivity.putExtra("vitB", String.valueOf(vit_b.get(position)));
                updateMicroIntentActivity.putExtra("vitC", String.valueOf(vit_c.get(position)));
                updateMicroIntentActivity.putExtra("vitE", String.valueOf(vit_e.get(position)));
                updateMicroIntentActivity.putExtra("magnesium", String.valueOf(magnesium.get(position)));
                updateMicroIntentActivity.putExtra("zinc", String.valueOf(zinc.get(position)));

                activity_micronutrients.startActivityForResult(updateMicroIntentActivity, requestCode);
            }
        });
    }

    @Override
    public int getItemCount() {
        return micro_id.size();
    }

    public class MicroViewHolder extends RecyclerView.ViewHolder {

        TextView micro_id_text, micro_vitB, micro_vitC, micro_vitE, micro_mag, micro_zinc;
        LinearLayout microMainLayout;

        public MicroViewHolder(@NonNull View itemView) {
            super(itemView);
            micro_id_text = itemView.findViewById(R.id.micro_id_text);
            micro_vitB = itemView.findViewById(R.id.vitB_data);
            micro_vitC = itemView.findViewById(R.id.vitC_data);
            micro_vitE = itemView.findViewById(R.id.vitE_data);
            micro_mag = itemView.findViewById(R.id.magnesium_data);
            micro_zinc = itemView.findViewById(R.id.zinc_data);
            microMainLayout = itemView.findViewById(R.id.microMainLayout);
        }
    }
}

package com.example.androidfitness.logic.micronutrients;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.androidfitness.datalayer.micronutrients.MicronutrientsDB;
import com.example.androidfitness.datalayer.micronutrients.MicronutrientsDbImpl;

public class MicronutrientsLogicImpl implements MicronutrientsLogic{

    private MicronutrientsDB microDB;

    public MicronutrientsLogicImpl(@Nullable Context context) {
        microDB = new MicronutrientsDbImpl(context);
    }

    @Override
    public boolean addMicronutrients(float vitb, float vitc, float vite, float magnesium, float zinc) {
        return microDB.addMicronutrients(vitb, vitc, vite, magnesium, zinc);
    }

    @Override
    public Cursor microReadAllData() {
        return microDB.microReadAllData();
    }

    @Override
    public boolean updateMicroData(String row_id, String vitb, String vitc, String vite, String magnesium, String zinc) {
        return microDB.updateMicroData(row_id, vitb, vitc, vite, magnesium, zinc);
    }

    @Override
    public boolean deleteRowMicro(String row_id) {
        return microDB.deleteRowMicro(row_id);
    }

    @Override
    public void deleteAllMicroData() {
        microDB.deleteAllMicroData();
    }
}

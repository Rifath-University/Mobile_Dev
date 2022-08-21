package com.example.androidfitness.logic.micronutrients;

import android.database.Cursor;

public interface MicronutrientsLogic {
    public boolean addMicronutrients(float vitb, float vitc, float vite, float magnesium, float zinc);

    public Cursor microReadAllData();

    public boolean updateMicroData(String row_id, String vitb, String vitc, String vite, String magnesium, String zinc);

    public boolean deleteRowMicro(String row_id);

    public void deleteAllMicroData();
}

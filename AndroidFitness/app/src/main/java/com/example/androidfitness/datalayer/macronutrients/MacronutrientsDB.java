package com.example.androidfitness.datalayer.macronutrients;

import android.database.Cursor;

public interface MacronutrientsDB {

    public boolean addMacronutrients(float protein, float fat, float carbohydrates, float fibre, float salt);

    public Cursor macronutrientsReadAllData();

    public boolean updateMacroData(String row_id, String protein, String fat, String carbohydrates, String fibre, String salt);

    public boolean deleteRowMacro(String row_id);

    public void deleteAllMacroData();

}

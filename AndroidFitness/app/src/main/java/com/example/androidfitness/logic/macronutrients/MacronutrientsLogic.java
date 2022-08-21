package com.example.androidfitness.logic.macronutrients;

import android.database.Cursor;

public interface MacronutrientsLogic {
    public boolean addMacronutrients(int protein, int fat, int carbohydrates, int fibre, int salt);

    public Cursor macronutrientsReadAllData();

    public boolean updateMacroData(String row_id, String protein, String fat, String carbohydrates, String fibre, String salt);

    public boolean deleteRowMacro(String row_id);

    public void deleteAllMacroData();

}

package com.example.androidfitness.logic.macronutrients;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.androidfitness.datalayer.macronutrients.MacronutrientsDbImpl;

public class MacronutrientsLogicImpl implements MacronutrientsLogic{

    private MacronutrientsDbImpl macroDB;

    public MacronutrientsLogicImpl(@Nullable Context context) {
        macroDB = new MacronutrientsDbImpl(context);
    }


    @Override
    public boolean addMacronutrients(float protein, float fat, float carbohydrates, float fibre, float salt) {
        return macroDB.addMacronutrients(protein, fat, carbohydrates, fibre, salt);
    }

    @Override
    public Cursor macronutrientsReadAllData() {
        return macroDB.macronutrientsReadAllData();
    }

    @Override
    public boolean updateMacroData(String row_id, String protein, String fat, String carbohydrates, String fibre, String salt) {
        return macroDB.updateMacroData(row_id, protein, fat, carbohydrates, fibre, salt);
    }

    @Override
    public boolean deleteRowMacro(String row_id) {
        return macroDB.deleteRowMacro(row_id);
    }

    @Override
    public void deleteAllMacroData() {
        macroDB.deleteAllMacroData();
    }
}

package com.example.androidfitness.logic.macronutrients;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.androidfitness.datalayer.Macronutrients_MyDatabaseHelper;

public class MacronutrientsLogicImpl {

    private Macronutrients_MyDatabaseHelper macroDB;

    public MacronutrientsLogicImpl(@Nullable Context context) {
        macroDB = new Macronutrients_MyDatabaseHelper(context);
    }

}

package com.example.androidfitness.businessLogic;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.androidfitness.Steps_MyDatabaseHelper;

public class StepsBusinessLogic {

    private Steps_MyDatabaseHelper stepsDB;

    public StepsBusinessLogic(@Nullable Context context) {
        stepsDB = new Steps_MyDatabaseHelper(context);
    }

    public void deleteAllSteps() {
        stepsDB.deleteAllStepsData();
    }

    public Cursor stepsReadAllData() {
        return stepsDB.stepsReadAllData();
    }
}

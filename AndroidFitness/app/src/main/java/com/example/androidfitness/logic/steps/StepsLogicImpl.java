package com.example.androidfitness.logic.steps;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.androidfitness.datalayer.steps.StepsDb;
import com.example.androidfitness.datalayer.steps.StepsDbImpl;

public class StepsLogicImpl implements StepsLogic {

    private StepsDb stepsDB;

    public StepsLogicImpl(@Nullable Context context) {
        stepsDB = new StepsDbImpl(context);
    }

    public void deleteAllSteps() {
        stepsDB.deleteAllSteps();
    }

    @Override
    public Cursor readAllStepsData() {
        return stepsDB.readAllStepsData();
    }

    @Override
    public boolean updateStepsData(String row_id, String date, String steps, String calories) {
        return stepsDB.updateStepsData(row_id, date, steps, calories);
    }

    @Override
    public boolean addSteps(String date, int steps, int calories) {
        return stepsDB.addSteps(date, steps, calories);
    }

    @Override
    public boolean deleteRowSteps(String row_id) {
        return stepsDB.deleteRowSteps(row_id);
    }
}

package com.example.androidfitness.logic.steps;

import android.database.Cursor;

import java.util.HashMap;

public interface StepsLogic {
    public boolean addSteps(String date, int steps, int calories);

    public Cursor readAllStepsData();

    public boolean updateStepsData(String row_id, String date, String steps, String calories);

    public boolean deleteRowSteps(String row_id);

    public void deleteAllSteps();
}

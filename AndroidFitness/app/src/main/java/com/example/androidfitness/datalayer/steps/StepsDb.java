package com.example.androidfitness.datalayer.steps;

import android.database.Cursor;

public interface StepsDb {
    public void deleteAllSteps();

    public Cursor readAllStepsData();

    public boolean updateStepsData(String row_id, String date, String steps, String calories);

    public boolean addSteps(String date, int steps, int calories);

    public boolean deleteRowSteps(String row_id);
}

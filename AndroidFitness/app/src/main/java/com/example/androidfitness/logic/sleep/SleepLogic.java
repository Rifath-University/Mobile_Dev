package com.example.androidfitness.logic.sleep;

import android.database.Cursor;

public interface SleepLogic {
    public boolean addSleep(String date, float totalSleep, float deepSleep);

    public Cursor sleepReadAllData();

    public boolean updateSleepData(String row_id, String date, String totalSleep, String deepSleep);

    public boolean deleteRowSleep(String row_id);

    public void deleteAllSleepData();
}

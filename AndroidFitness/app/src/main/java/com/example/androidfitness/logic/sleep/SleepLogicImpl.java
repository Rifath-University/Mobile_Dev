package com.example.androidfitness.logic.sleep;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.androidfitness.datalayer.sleep.SleepDb;
import com.example.androidfitness.datalayer.sleep.SleepDbImpl;
import com.example.androidfitness.datalayer.steps.StepsDb;
import com.example.androidfitness.datalayer.steps.StepsDbImpl;

public class SleepLogicImpl implements SleepLogic{

    private SleepDb sleepDb;

    public SleepLogicImpl(@Nullable Context context) {
        sleepDb = new SleepDbImpl(context);
    }

    @Override
    public boolean addSleep(String date, float totalSleep, float deepSleep) {
        return sleepDb.addSleep(date, totalSleep, deepSleep);
    }

    @Override
    public Cursor sleepReadAllData() {
        return sleepDb.sleepReadAllData();
    }

    @Override
    public boolean updateSleepData(String row_id, String date, String totalSleep, String deepSleep) {
        return sleepDb.updateSleepData(row_id, date, totalSleep, deepSleep);
    }

    @Override
    public boolean deleteRowSleep(String row_id) {
        return sleepDb.deleteRowSleep(row_id);
    }

    @Override
    public void deleteAllSleepData() {
        sleepDb.deleteAllSleepData();
    }
}

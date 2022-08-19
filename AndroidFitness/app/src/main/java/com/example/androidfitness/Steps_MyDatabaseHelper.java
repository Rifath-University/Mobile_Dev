package com.example.androidfitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Steps_MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "AndroidFitness.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "DailySteps";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_STEPS = "steps";
    private static final String COLUMN_CALORIES = "calories";

    public Steps_MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_STEPS + " INTEGER, " +
                        COLUMN_CALORIES + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    
    public void addSteps(String date, int steps, int calories) {
        SQLiteDatabase steps_db = this.getWritableDatabase();
        ContentValues steps_cv = new ContentValues();
        
        steps_cv.put(COLUMN_DATE, date);
        steps_cv.put(COLUMN_STEPS, steps);
        steps_cv.put(COLUMN_CALORIES, calories);
        long result = steps_db.insert(TABLE_NAME, null, steps_cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor stepsReadAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase stepsDB = this.getReadableDatabase();

        Cursor stepsCursor = null;
        if(stepsDB != null) {
            stepsCursor = stepsDB.rawQuery(query, null);
        }
        return stepsCursor;
    }

    public void updateStepsData(String row_id, String date, String steps, String calories) {
        SQLiteDatabase updateStepsDB = this.getWritableDatabase();
        ContentValues stepsCV = new ContentValues();
        stepsCV.put(COLUMN_DATE, date);
        stepsCV.put(COLUMN_STEPS, steps);
        stepsCV.put(COLUMN_CALORIES, calories);

        long updateStepsResult = updateStepsDB.update(TABLE_NAME, stepsCV, "_id=?", new String[]{row_id});
        if (updateStepsResult == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    public void deleteRowSteps(String row_id) {
        SQLiteDatabase deleteStepsDB = this.getWritableDatabase();
        long deleteResult = deleteStepsDB.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (deleteResult == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllStepsData() {
        SQLiteDatabase deleteAllStepsDB = this.getWritableDatabase();
        deleteAllStepsDB.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

package com.example.androidfitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Sleep_MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "AndroidFitnessSleep.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "DailySleep";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TOTALSLEEP = "totalSleep";
    private static final String COLUMN_DEEPSLEEP = "deepSleep";

    Sleep_MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TOTALSLEEP + " INTEGER, " +
                COLUMN_DEEPSLEEP + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addSleep(String date, int totalSleep, int deepSleep) {
        SQLiteDatabase sleep_db = this.getWritableDatabase();
        ContentValues sleep_cv = new ContentValues();

        sleep_cv.put(COLUMN_DATE, date);
        sleep_cv.put(COLUMN_TOTALSLEEP, totalSleep);
        sleep_cv.put(COLUMN_DEEPSLEEP, deepSleep);
        long result = sleep_db.insert(TABLE_NAME, null, sleep_cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor sleepReadAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sleepDB = this.getReadableDatabase();

        Cursor sleepCursor = null;
        if(sleepDB != null) {
            sleepCursor = sleepDB.rawQuery(query, null);
        }
        return sleepCursor;
    }

    void updateSleepData(String row_id, String date, String totalSleep, String deepSleep) {
        SQLiteDatabase updateSleepDB = this.getWritableDatabase();
        ContentValues sleepCV = new ContentValues();
        sleepCV.put(COLUMN_DATE, date);
        sleepCV.put(COLUMN_TOTALSLEEP, totalSleep);
        sleepCV.put(COLUMN_DEEPSLEEP, deepSleep);

        long updateSleepResult = updateSleepDB.update(TABLE_NAME, sleepCV, "_id=?", new String[]{row_id});
        if (updateSleepResult == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    void deleteRowSleep(String row_id) {
        SQLiteDatabase deleteSleepDB = this.getWritableDatabase();
        long deleteResult = deleteSleepDB.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (deleteResult == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllSleepData() {
        SQLiteDatabase deleteAllSleepDB = this.getWritableDatabase();
        deleteAllSleepDB.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

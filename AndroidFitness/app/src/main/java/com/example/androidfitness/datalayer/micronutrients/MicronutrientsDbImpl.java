package com.example.androidfitness.datalayer.micronutrients;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MicronutrientsDbImpl extends SQLiteOpenHelper implements MicronutrientsDB {

    private Context context;
    private static final String DATABASE_NAME = "AndroidFitnessMicronutrients.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "MicroNutrients";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_VITB = "vitB";
    private static final String COLUMN_VITC = "vitC";
    private static final String COLUMN_VITE = "vitE";
    private static final String COLUMN_MAGNESIUM = "magnesium";
    private static final String COLUMN_ZINC = "zinc";

    public MicronutrientsDbImpl(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VITB + " REAL, " +
                COLUMN_VITC + " REAL, " +
                COLUMN_VITE + " REAL, " +
                COLUMN_MAGNESIUM + " REAL, " +
                COLUMN_ZINC + " REAL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addMicronutrients(float vitb, float vitc, float vite, float magnesium, float zinc) {
        SQLiteDatabase micro_db = this.getWritableDatabase();
        ContentValues micro_cv = new ContentValues();

        micro_cv.put(COLUMN_VITB, vitb);
        micro_cv.put(COLUMN_VITC, vitc);
        micro_cv.put(COLUMN_VITE, vite);
        micro_cv.put(COLUMN_MAGNESIUM, magnesium);
        micro_cv.put(COLUMN_ZINC, zinc);
        long result = micro_db.insert(TABLE_NAME, null, micro_cv);
        return (result == -1) ? false : true;
    }

    public Cursor microReadAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase microDB = this.getReadableDatabase();

        Cursor microCursor = null;
        if(microDB != null) {
            microCursor = microDB.rawQuery(query, null);
        }
        return microCursor;
    }

    public boolean updateMicroData(String row_id, String vitb, String vitc, String vite, String magnesium, String zinc) {
        SQLiteDatabase updateMicroDB = this.getWritableDatabase();
        ContentValues microCV = new ContentValues();
        microCV.put(COLUMN_VITB, vitb);
        microCV.put(COLUMN_VITC, vitc);
        microCV.put(COLUMN_VITE, vite);
        microCV.put(COLUMN_MAGNESIUM, magnesium);
        microCV.put(COLUMN_ZINC, zinc);

        long updateMicroResult = updateMicroDB.update(TABLE_NAME, microCV, "_id=?", new String[]{row_id});
        return (updateMicroResult == 0) ? false : true;
    }

    public boolean deleteRowMicro(String row_id) {
        SQLiteDatabase deleteMicroDB = this.getWritableDatabase();
        long deleteResult = deleteMicroDB.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        return (deleteResult == 0) ? false : true;
    }

    public void deleteAllMicroData() {
        SQLiteDatabase deleteAllMicroDB = this.getWritableDatabase();
        deleteAllMicroDB.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

package com.example.androidfitness.datalayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Macronutrients_MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "AndroidFitnessMacronutrients.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "MacroNutrients";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PROTEIN = "protein";
    private static final String COLUMN_FAT = "fat";
    private static final String COLUMN_CARBOHYDRATE = "carbohydrate";
    private static final String COLUMN_FIBRE = "fibre";
    private static final String COLUMN_SALT = "salt";

    public Macronutrients_MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROTEIN + " STRING, " +
                COLUMN_FAT + " STRING, " +
                COLUMN_CARBOHYDRATE + " STRING, " +
                COLUMN_FIBRE + " STRING, " +
                COLUMN_SALT + " STRING);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addMacronutrients(int protein, int fat, int carbohydrates, int fibre, int salt) {
        SQLiteDatabase macroNutrients_db = this.getWritableDatabase();
        ContentValues macroNutrients_cv = new ContentValues();

        macroNutrients_cv.put(COLUMN_PROTEIN, protein);
        macroNutrients_cv.put(COLUMN_FAT, fat);
        macroNutrients_cv.put(COLUMN_CARBOHYDRATE, carbohydrates);
        macroNutrients_cv.put(COLUMN_FIBRE, fibre);
        macroNutrients_cv.put(COLUMN_SALT, salt);
        long result = macroNutrients_db.insert(TABLE_NAME, null, macroNutrients_cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor macronutrientsReadAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase macroNutrientsDB = this.getReadableDatabase();

        Cursor macroNutrientsCursor = null;
        if(macroNutrientsDB != null) {
            macroNutrientsCursor = macroNutrientsDB.rawQuery(query, null);
        }
        return macroNutrientsCursor;
    }

    public void updateMacroData(String row_id, String protein, String fat, String carbohydrates, String fibre, String salt) {
        SQLiteDatabase updateMacronutrientsDB = this.getWritableDatabase();
        ContentValues macroNutrientsCV = new ContentValues();
        macroNutrientsCV.put(COLUMN_PROTEIN, protein);
        macroNutrientsCV.put(COLUMN_FAT, fat);
        macroNutrientsCV.put(COLUMN_CARBOHYDRATE, carbohydrates);
        macroNutrientsCV.put(COLUMN_FIBRE, fibre);
        macroNutrientsCV.put(COLUMN_SALT, salt);

        long updateMacroResult = updateMacronutrientsDB.update(TABLE_NAME, macroNutrientsCV, "_id=?", new String[]{row_id});
        if (updateMacroResult == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    public void deleteRowMacro(String row_id) {
        SQLiteDatabase deleteMacronutrientsDB = this.getWritableDatabase();
        long deleteResult = deleteMacronutrientsDB.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (deleteResult == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllMacroData() {
        SQLiteDatabase deleteAllMacronutrientsDB = this.getWritableDatabase();
        deleteAllMacronutrientsDB.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

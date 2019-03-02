package com.example.mycalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DatabaseHelper class for Ladybug application.
 *
 * Right now only one table for Cycle is constructed
 *
 * Add more Tables inside the class.
 *
 * Add more methods for inserting different tables.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Ladybug.db";
    /**
     * All table's names declared here.
     */
    public static final String TABLE_NAME_CYCLE = "Cycle_table",TABLE_NAME_FOOD = "Food_table",TABLE_NAME_EXERCISE="Exercise_table",TABLE_NAME_SLEEP= "Sleep_table",TABLE_NAME_STRESS="Stress_table";
    public static final String TABLE_NAME_RECOMMEND= "Recommend_table",TABLE_NAME_SUMMARY= "Summary_table";

    /**
     * All table's column names declared here.
     */
    public static final String CYCLE_COL_1 = "cycle_id",CYCLE_COL_2 = "cycle_start_date",CYCLE_COL_3 = "cycle_end_date",CYCLE_COL_4 = "cycle_length",CYCLE_COL_5 = "period_length";
    public static final String FOOD_COL_1 = "food_date",FOOD_COL_2 = "is_breakfast",FOOD_COL_3 = "sugar_fat_level";
    public static final String EXERCISE_COL_1 = "exercise_date",EXERCISE_COL_2 = "type",EXERCISE_COL_3 = "time",EXERCISE_COL_4 = "weight_change";
    public static final String SLEEP_COL_1 = "sleep_date",SLEEP_COL_2 = "start_time",SLEEP_COL_3 = "end_time",SLEEP_COL_4 = "quality";
    public static final String STRESS_COL_1 = "stress_date",STRESS_COL_2 = "stress_level";
    public static final String RECOMMEND_COL_1 = "state",RECOMMEND_COL_2 = "recommendation";
    public static final String SUMMARY_COL_1 = "month",SUMMARY_COL_2 = "overall_state",SUMMARY_COL_3 = "food",SUMMARY_COL_4 = "sleep",SUMMARY_COL_5 = "stress",SUMMARY_COL_6 = "exercise",SUMMARY_COL_7 = "cycle_len_change";




    /**
     * Constructor for Main Database of this class.
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_CYCLE + " (cycle_id INTEGER PRIMARY KEY,cycle_start_date TEXT ,cycle_end_date TEXT ,cycle_length INTEGER,period_length INTEGER) ");
        db.execSQL("CREATE TABLE " + TABLE_NAME_FOOD + " (food_date TEXT PRIMARY KEY,is_breakfast INTEGER ,sugar_fat_level TEXT) ");
        db.execSQL("CREATE TABLE " + TABLE_NAME_EXERCISE + " (exercise_date TEXT PRIMARY KEY,type TEXT ,time INTEGER ,weight_change INTEGER) ");
        db.execSQL("CREATE TABLE " + TABLE_NAME_SLEEP + " (sleep_date TEXT PRIMARY KEY,start_time TEXT ,end_time TEXT ,quality TEXT)");

        //db.execSQL("CREATE TABLE " + TABLE_NAME_STRESS + " (cycle_id INTEGER PRIMARY KEY,cycle_start_date TEXT ,cycle_end_date TEXT ,cycle_length INTEGER,period_length INTEGER) ");
        //db.execSQL("CREATE TABLE " + TABLE_NAME_RECOMMEND + " (cycle_id INTEGER PRIMARY KEY,cycle_start_date TEXT ,cycle_end_date TEXT ,cycle_length INTEGER,period_length INTEGER) ");
        //db.execSQL("CREATE TABLE " + TABLE_NAME_SUMMARY + " (cycle_id INTEGER PRIMARY KEY,cycle_start_date TEXT ,cycle_end_date TEXT ,cycle_length INTEGER,period_length INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_CYCLE);
        onCreate(db);
    }


    /**
     *
     * @param cycle_id: Cycle id for table, primary key of the table,type is Int.
     * @param cycle_start_date: Start date for cycle, type is String.
     * @param cycle_end_date:Start date for cycle, type is String.
     * @param cycle_length:Start date for cycle, type is Int.
     * @param period_length:Start date for cycle, type is Int.
     * @return: return True if inserted complete, False otherwise.
     */
    public boolean insertData_cycle(int cycle_id,String cycle_start_date,String cycle_end_date,int cycle_length, int period_length){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CYCLE_COL_1,cycle_id);
        contentValues.put(CYCLE_COL_2,cycle_start_date);
        contentValues.put(CYCLE_COL_3,cycle_end_date);
        contentValues.put(CYCLE_COL_4,cycle_length);
        contentValues.put(CYCLE_COL_5,period_length);
        long result = db.insert(TABLE_NAME_CYCLE,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * This method would return the whole DB as result.
     * @return
     */
    public Cursor getAllData(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+ table,null);
        return result;
    }
}

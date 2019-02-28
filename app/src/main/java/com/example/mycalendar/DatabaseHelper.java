package com.example.mycalendar;

import android.content.ContentValues;
import android.content.Context;
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
    public static final String TABLE_NAME = "Cycle_table";
    public static final String COL_1 = "cycle_id";
    public static final String COL_2 = "cycle_start_date";
    public static final String COL_3 = "cycle_end_date";
    public static final String COL_4 = "cycle_length";
    public static final String COL_5 = "period_length";


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
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (cycle_id INTEGER PRIMARY KEY,cycle_start_date TEXT ,cycle_end_date TEXT ,cycle_length INTEGER,period_length INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
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
        contentValues.put(COL_1,cycle_id);
        contentValues.put(COL_2,cycle_start_date);
        contentValues.put(COL_3,cycle_end_date);
        contentValues.put(COL_4,cycle_length);
        contentValues.put(COL_5,period_length);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
}

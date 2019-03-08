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
    public static final String TABLE_NAME_RECOMMEND= "Recommend_table",TABLE_NAME_SUMMARY= "Summary_table",TABLE_NAME_PROFILE = "Profile_table";

    /**
     * All table's column names declared here.
     */
    public static final String CYCLE_COL_1 = "cycle_id",CYCLE_COL_2 = "cycle_start_date",CYCLE_COL_3 = "cycle_end_date",CYCLE_COL_4 = "cycle_length",CYCLE_COL_5 = "period_length";
    public static final String FOOD_COL_1 = "food_date",FOOD_COL_2 = "is_breakfast",FOOD_COL_3 = "sugar_fat_level";
    public static final String EXERCISE_COL_1 = "exercise_date",EXERCISE_COL_2 = "type",EXERCISE_COL_3 = "weight";
    public static final String SLEEP_COL_1 = "sleep_date",SLEEP_COL_2 = "start_time",SLEEP_COL_3 = "end_time",SLEEP_COL_4 = "sleep_length",SLEEP_COL_5 = "quality";
    public static final String STRESS_COL_1 = "stress_date",STRESS_COL_2 = "stress_level";
    public static final String RECOMMEND_COL_1 = "state",RECOMMEND_COL_2 = "recommendation";
    public static final String SUMMARY_COL_1 = "month",SUMMARY_COL_2 = "overall_state",SUMMARY_COL_3 = "food",SUMMARY_COL_4 = "sleep",SUMMARY_COL_5 = "stress",SUMMARY_COL_6 = "exercise",SUMMARY_COL_7 = "cycle_len_change";
    public static final String PROFILE_COL_1 = "user_id",PROFILE_COL_2 = "age",PROFILE_COL_3 = "height",PROFILE_COL_4 = "weight";




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
        db.execSQL("CREATE TABLE " + TABLE_NAME_EXERCISE + " (exercise_date TEXT PRIMARY KEY,type TEXT ,weight INTEGER) ");
        db.execSQL("CREATE TABLE " + TABLE_NAME_SLEEP + " (sleep_date TEXT PRIMARY KEY,start_time TEXT ,end_time TEXT ,sleep_length FLOAT(2), quality TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_STRESS + " (stress_date TEXT PRIMARY KEY,stress_level TEXT) ");
        db.execSQL("CREATE TABLE " + TABLE_NAME_RECOMMEND + " (state TEXT PRIMARY KEY,recommendation TEXT) ");
        db.execSQL("CREATE TABLE " + TABLE_NAME_SUMMARY + " (month TEXT PRIMARY KEY,overall_state TEXT ,food TEXT ,sleep TEXT,stress TEXT,exercise TEXT,cycle_len_change INTEGER) ");
        db.execSQL("CREATE TABLE " + TABLE_NAME_PROFILE + "(user_id TEXT PRIMARY KEY,age INTEGER, height INTEGER, weight INTEGER) ");
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
     *
     * @param food_date
     * @param is_breakfast
     * @param sugar_fat_level
     * @return
     */
    public boolean insertData_food(String food_date, int is_breakfast, String sugar_fat_level){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_COL_1,food_date);
        contentValues.put(FOOD_COL_2,is_breakfast);
        contentValues.put(FOOD_COL_3,sugar_fat_level);
        long result = db.insert(TABLE_NAME_FOOD,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    /**
     *
     * @param exercise_date
     * @param type
     * @param weight
     * @return
     */
    public boolean insertData_exercise(String exercise_date, String type,int weight){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISE_COL_1,exercise_date);
        contentValues.put(EXERCISE_COL_2,type);
        contentValues.put(EXERCISE_COL_3,weight);
        long result = db.insert(TABLE_NAME_EXERCISE,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     *
     * @param sleep_date
     * @param start_time
     * @param end_time
     * @param sleep_length
     * @oaram quality
     * @return
     */
    public boolean insertData_sleep(String sleep_date,String start_time,String end_time,float sleep_length, String quality){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SLEEP_COL_1,sleep_date);
        contentValues.put(SLEEP_COL_2,start_time);
        contentValues.put(SLEEP_COL_3,end_time);
        contentValues.put(SLEEP_COL_4,sleep_length);
        contentValues.put(SLEEP_COL_5,quality);
        long result = db.insert(TABLE_NAME_SLEEP,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    /**
     *
     * @param stress_date
     * @param stress_level
     * @return
     */
    public boolean insertData_stress(String stress_date,String stress_level){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STRESS_COL_1,stress_date);
        contentValues.put(STRESS_COL_2,stress_level);
        long result = db.insert(TABLE_NAME_STRESS,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    /**
     *
     * @param state
     * @param recommendation
     * @return
     */
    public boolean insertData_recommend(String state, String recommendation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECOMMEND_COL_1,state);
        contentValues.put(RECOMMEND_COL_2,recommendation);
        long result = db.insert(TABLE_NAME_RECOMMEND,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    /**
     *
     * @param month
     * @param overall_state
     * @param food
     * @param sleep
     * @param stress
     * @param exercise
     * @param cycle_len_change
     * @return
     */
    public boolean insertData_summary(String month,String overall_state,String food, String sleep, String stress, String exercise,int cycle_len_change){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUMMARY_COL_1,month);
        contentValues.put(SUMMARY_COL_2,overall_state);
        contentValues.put(SUMMARY_COL_3,food);
        contentValues.put(SUMMARY_COL_4,sleep);
        contentValues.put(SUMMARY_COL_5,stress);
        contentValues.put(SUMMARY_COL_6,exercise);
        contentValues.put(SUMMARY_COL_7,cycle_len_change);
        long result = db.insert(TABLE_NAME_SUMMARY,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     *
     * @param user_id:new user_id
     * @param age: user age
     * @param height: user height
     * @param weight: user weight
     * @return
     */
    public boolean insertData_profile(String user_id, int age, int height, int weight){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COL_1,user_id);
        contentValues.put(PROFILE_COL_2,age);
        contentValues.put(PROFILE_COL_3,height);
        contentValues.put(PROFILE_COL_4,weight);
        long result = db.insert(TABLE_NAME_PROFILE,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }




    /**
     * Delete rows from cycle table
     * no parameter required.
     * @return
     */
    public boolean deleteData_cycle(){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_CYCLE,"period_length=?", new String[]{Integer.toString(0)});
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Delete the last row of the cycle table.
     * @return
     */
    public boolean deleteData_cycle_last_row(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor r = db.rawQuery("select MAX(cycle_id) from "+ TABLE_NAME_CYCLE,null);
        String temp = r.getString(0);
        long result = db.delete(TABLE_NAME_CYCLE,"cycle_id = ?",new String[]{temp});
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Delete rows from sleep table
     * no parameter required.
     * @return
     */
    public boolean deleteData_sleep(){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_SLEEP,"end_time = ?",new String[]{"NULL"});
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     *
     * @param username:user_name of the user.
     * @return whether delete succeed.
     */
    public boolean deleteData_profile(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_PROFILE,"user_id = ?",new String[]{username});
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

package com.example.mycalendar;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Algorithm extends AppCompatActivity{

    String summary_date;
    String food_state;
    String exercise_state;
    String stress_state;
    String sleep_state;
    String Overall_state;

    private void food_summary(DatabaseHelper Db, String LastEndDate){

        int no_breakfast_count = 0;
        int high_foodlevel_count = 0;
        int low_foodlevel_count = 0;
        Cursor food_data = Db.getAllData(DatabaseHelper.TABLE_NAME_FOOD);
        int row_num  = food_data.getCount();
        if (row_num != 0){
            food_data.moveToLast();
            summary_date = food_data.getString(0);
            while (!food_data.getString(0).equals(LastEndDate)) {
                if (food_data.getInt(1) == 0){
                    no_breakfast_count += 1;
                }
                if (food_data.getString(2).equals("High fat and high sugar")){
                    high_foodlevel_count +=1;
                }
                if (food_data.getString(2).equals("Low fat and low sugar")){
                    low_foodlevel_count +=1;
                }
                food_data.moveToPrevious();
            }

            if (food_data.getInt(1) == 0){
                no_breakfast_count += 1;
            }
            if (food_data.getString(2).equals("High fat and high sugar")){
                high_foodlevel_count +=1;
            }
            if (food_data.getString(2).equals("Low fat and low sugar")){
                low_foodlevel_count +=1;
            }

            int max = Math.max(high_foodlevel_count,low_foodlevel_count);

            if (no_breakfast_count > 7){
                if (max > 7){
                    if (max == high_foodlevel_count){
                        food_state = "High fat and high sugar with No Breakfast";
                    }
                    else{
                        food_state = "Low fat and low sugar with No Breakfast";
                    }
                }
                else{
                    food_state = "No Breakfast";
                }

            }
            else{
                if (max > 7){
                    if (max == high_foodlevel_count){
                        food_state = "High fat and high sugar";
                    }
                    else{
                        food_state = "Low fat and low sugar";
                    }
                }
                else{
                    food_state = "Healthy";
                }
            }


        }
    }

    private void stress_summary(DatabaseHelper Db, String LastEndDate){

        int stressful_count = 0;
        Cursor stress_data = Db.getAllData(DatabaseHelper.TABLE_NAME_STRESS);
        int row_num  = stress_data.getCount();
        if (row_num != 0){
            stress_data.moveToLast();
            while (!stress_data.getString(0).equals(LastEndDate)) {
                if (stress_data.getString(1).equals("Stressful")){
                    stressful_count +=1;
                }
                stress_data.moveToPrevious();
            }

            if (stress_data.getString(1).equals("Stressful")){
                stressful_count +=1;
            }

            if (stressful_count > 7){
                stress_state = "Stressful";
            }
            else{
                stress_state = "Healthy";
            }
        }
    }

    private void sleep_summary(DatabaseHelper Db, String LastEndDate){

        int bad_sleep_count = 0;
        int insufficient_sleep_count = 0;
        Cursor sleep_data = Db.getAllData(DatabaseHelper.TABLE_NAME_SLEEP);
        int row_num  = sleep_data.getCount();
        if (row_num != 0){
            sleep_data.moveToLast();
            while (!sleep_data.getString(0).equals(LastEndDate)) {
                if (sleep_data.getString(4).equals("Bad")){
                    bad_sleep_count +=1;
                }
                if (sleep_data.getFloat(3) < 7){
                    insufficient_sleep_count +=1;
                }
                sleep_data.moveToPrevious();
            }

            if (sleep_data.getString(4).equals("Bad")){
                bad_sleep_count +=1;
            }
            if (sleep_data.getFloat(3) < 7){
                insufficient_sleep_count +=1;
            }

            if (bad_sleep_count > 7){
                if (insufficient_sleep_count > 7){
                    sleep_state = "Insufficient sleep with Bad sleep quality";
                }
                else{
                    sleep_state = "Bad sleep quality";
                }
            }
            else{
                if (insufficient_sleep_count > 7){
                    sleep_state = "Insufficient sleep";
                }
                else{
                    sleep_state = "Healthy";
                }

            }
        }
    }
    private void exercise_summary(DatabaseHelper Db, String LastEndDate){

        int strenuous_exercise_count = 0;
        int no_exercise_count = 0;
        int weight_change;
        int start_weight;
        int current_weight;

        Cursor exercise_data = Db.getAllData(DatabaseHelper.TABLE_NAME_EXERCISE);
        int row_num  = exercise_data.getCount();
        if (row_num != 0){
            exercise_data.moveToLast();
            current_weight = exercise_data.getInt(2);
            while (!exercise_data.getString(0).equals(LastEndDate)) {
                if (exercise_data.getString(1).equals("Strenuous exercise")){
                    strenuous_exercise_count += 1;
                }
                else if (exercise_data.getString(1).equals("No additional exercise")){
                    no_exercise_count += 1;
                }
                exercise_data.moveToPrevious();
            }

            if (exercise_data.getString(1).equals("Strenuous exercise")){
                strenuous_exercise_count += 1;
            }
            else if (exercise_data.getString(1).equals("No additional exercise")){
                no_exercise_count += 1;
            }
            start_weight = exercise_data.getInt(2);


            int max = Math.max(strenuous_exercise_count,no_exercise_count);
            weight_change = current_weight - start_weight;

            if (max > 7) {
                if (max == no_exercise_count) {
                    if (weight_change > 5) {
                        exercise_state = "No additional exercise with Increased weight";
                    } else if (max == strenuous_exercise_count) {
                        if (weight_change < -5) {
                            exercise_state = "Strenuous exercise with Reduced weight";
                        } else if (weight_change > 5) {
                            exercise_state = "Strenuous exercise with Increased weight";
                        } else {
                            exercise_state = "Strenuous exercise";
                        }
                    }
                } else {
                    if (weight_change > 5) {
                        exercise_state = "Increased weight";
                    }
                    if (weight_change < -5) {
                        exercise_state = "Reduced weight";
                    } else {
                        exercise_state = "Healthy";
                    }
                }
            }
        }
    }

    public void Record_Summary(DatabaseHelper Db, String LastEndDate, String CurrentDate){
        food_summary(Db, LastEndDate);
        stress_summary(Db, LastEndDate);
        sleep_summary(Db, LastEndDate);
        exercise_summary(Db, LastEndDate);
        if (food_state != null && exercise_state != null && stress_state != null && sleep_state != null){
            if (food_state.equals("Healthy")&& exercise_state.equals("Healthy")&& stress_state.equals("Healthy")&& sleep_state.equals("Healthy")){
                Overall_state = "Healthy/Regular";
            }
            else{
                Overall_state = "Unhealthy/Irregular";
            }
            Db.insertData_summary(CurrentDate,Overall_state,food_state,sleep_state ,stress_state,exercise_state,300);
        }

    }

    //private void add_data(String summary_date,String overall_state,String food, String sleep, String stress, String exercise,int cycle_len_change){

        //Db.insertData_summary(summary_date,overall_state, food, sleep, stress, exercise, cycle_len_change);
    //}
}



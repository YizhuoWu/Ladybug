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

    private void food_summary(DatabaseHelper Db){

        int no_breakfast_count = 0;
        int high_foodlevel_count = 0;
        int low_foodlevel_count = 0;
        Cursor food_data = Db.getAllData(DatabaseHelper.TABLE_NAME_FOOD);
        int row_num  = food_data.getCount();
        if (row_num != 0 && row_num % 30 == 0){
            food_data.moveToLast();
            summary_date = food_data.getString(0);
            while (food_data.isFirst() == false) {
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

            int max = Math.max(high_foodlevel_count,Math.max(low_foodlevel_count,no_breakfast_count));

            if (max > 7){
                if (max == high_foodlevel_count){
                    food_state = "High fat and high sugar";
                }
                else if (max == no_breakfast_count){
                    food_state = "No Breakfast";
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

    private void stress_summary(DatabaseHelper Db){

        int stressful_count = 0;
        Cursor stress_data = Db.getAllData(DatabaseHelper.TABLE_NAME_STRESS);
        int row_num  = stress_data.getCount();
        if (row_num != 0 && row_num % 30 == 0){
            stress_data.moveToLast();
            while (stress_data.isFirst() == false) {
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

    private void exercise_summary(DatabaseHelper Db){

        int strenuous_exercise_count = 0;
        int no_exercise_count = 0;
        int weight_change;
        int start_weight;
        int current_weight;

        Cursor exercise_data = Db.getAllData(DatabaseHelper.TABLE_NAME_EXERCISE);
        int row_num  = exercise_data.getCount();
        if (row_num != 0 && row_num % 30 == 0){
            exercise_data.moveToLast();
            current_weight = exercise_data.getInt(2);
            while (exercise_data.isFirst() == false) {
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

            if (max > 7){
                if (max == no_exercise_count && weight_change > 5){
                    exercise_state = "No additional exercise with increased weight";
                }
                else if (max == strenuous_exercise_count && weight_change < -5){
                    exercise_state = "Strenuous exercise with reduced weight";
                }
            }
            else{
                if (weight_change > 5){
                    exercise_state = "Increased weight";
                }
                if (weight_change < -5){
                    exercise_state = "Reduced weight";
                }
                else{
                    exercise_state = "Healthy";
                }
            }



        }
    }

    public void Record_Summary(DatabaseHelper Db){
        food_summary(Db);
        stress_summary(Db);
        exercise_summary(Db);
        if (food_state != null && exercise_state != null && stress_state != null){
            Db.insertData_summary(summary_date,"health1",food_state,"2" ,stress_state,exercise_state,300);
        }

    }

    //private void add_data(String summary_date,String overall_state,String food, String sleep, String stress, String exercise,int cycle_len_change){

        //Db.insertData_summary(summary_date,overall_state, food, sleep, stress, exercise, cycle_len_change);
    //}
}

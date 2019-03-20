package com.example.mycalendar;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class Algorithm extends AppCompatActivity{

    String summary_date;
    String food_state;
    String exercise_state;
    String stress_state;
    String sleep_state;
    String Overall_state;
    Recommendation recommendation = new Recommendation();
    HashMap<String, String> recommendation_dictionary = recommendation.Build();

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

    public void Record_Summary(DatabaseHelper Db, String LastEndDate, String CurrentDate, int cycle_len_change){
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

            String Recom = "";
            String arrays[] = {food_state,exercise_state,stress_state,sleep_state};
            for (String s: arrays) {
                for (Map.Entry<String, String> map : recommendation_dictionary.entrySet()) {
                    if (s.contains(map.getKey())) {
                        Recom += map.getValue();
                    }
                }
            }
            //Recom is Overall Recommendation
            //System.out.println(map.getKey() + " " + map.getValue());
            Db.insertData_summary(CurrentDate,Overall_state,food_state,sleep_state ,stress_state,exercise_state, cycle_len_change, Recom);
        }

    }

    public static ArrayList<String> next_period_info(String last_start_date, int cycle_length, int period_length){
        ArrayList<Integer> oneMoreDay = new ArrayList<>();
        oneMoreDay.add(1);
        oneMoreDay.add(3);
        oneMoreDay.add(5);
        oneMoreDay.add(7);
        oneMoreDay.add(8);
        oneMoreDay.add(10);
        ArrayList<String> returnList = new ArrayList<>();

        int startyear = Integer.parseInt((last_start_date.substring(0,4)));
        int startmonth = Integer.parseInt(last_start_date.substring(5,7));
        int startday = Integer.parseInt(last_start_date.substring(8,10));

        int cycle_length_int = cycle_length;
        int period_length_int = period_length;

        int next_start_date = startday + cycle_length_int;
        int next_end_date = startday + cycle_length_int+ period_length_int;

        if (oneMoreDay.contains(startmonth)){
            if (next_start_date <= 31){
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth));
                returnList.add(Integer.toString(next_start_date));
            }
            else{
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth+1));
                returnList.add(Integer.toString(next_start_date-31));
            }
            if (next_end_date <= 31){
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth));
                returnList.add(Integer.toString(next_end_date));
            }
            else{
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth+1));
                returnList.add(Integer.toString(next_end_date-31));
            }

        }
        else if(startmonth == 2){
            if (next_start_date <= 28){
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth));
                returnList.add(Integer.toString(next_start_date));
            }
            else{
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth+1));
                returnList.add(Integer.toString(next_start_date-28));
            }
            if (next_end_date <= 31){
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth));
                returnList.add(Integer.toString(next_end_date));
            }
            else{
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth+1));
                returnList.add(Integer.toString(next_end_date-31));
            }

        }
        else if(startmonth == 12){
            if (next_start_date <= 31){
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth));
                returnList.add(Integer.toString(next_start_date));
            }
            else{
                returnList.add(Integer.toString(startyear+1));
                returnList.add(Integer.toString(1));
                returnList.add(Integer.toString(next_start_date-31));
            }
            if (next_end_date <= 31){
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth));
                returnList.add(Integer.toString(next_end_date));
            }
            else{
                returnList.add(Integer.toString(startyear+1));
                returnList.add(Integer.toString(startmonth+1));
                returnList.add(Integer.toString(next_end_date-31));
            }

        }
        else{
            if (next_start_date <= 30){
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth));
                returnList.add(Integer.toString(next_start_date));
            }
            else{
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth+1));
                returnList.add(Integer.toString(next_start_date-30));
            }
            if (next_end_date <= 31){
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth));
                returnList.add(Integer.toString(next_end_date));
            }
            else{
                returnList.add(Integer.toString(startyear));
                returnList.add(Integer.toString(startmonth+1));
                returnList.add(Integer.toString(next_end_date-31));
            }

        }

        ArrayList<String> anotherReturnList = new ArrayList<>();

        String Rstartmonth = returnList.get(1);
        String Rstartday = returnList.get(2);
        String Rendmonth = returnList.get(4);
        String Rendday = returnList.get(5);

        if (Rstartmonth.length() == 1){
            Rstartmonth = "0" + Rstartmonth;
        }
        if (Rstartday.length() == 1){
            Rstartday = "0" + Rstartday;
        }
        if (Rendmonth.length() == 1){
            Rendmonth = "0" + Rendmonth;
        }
        if (Rendday.length() == 1){
            Rendday = "0" + Rendday;
        }


        String start = returnList.get(0)+ "-" + Rstartmonth + "-" + Rstartday;
        String end = returnList.get(3)+ "-" + Rendmonth + "-" + Rendday;
        anotherReturnList.add(start);
        anotherReturnList.add(end);

        return anotherReturnList;
    }



    public static void predict_next_period(DatabaseHelper db) {

        Cursor recommendation = db.getAllData(DatabaseHelper.TABLE_NAME_SUMMARY);


        recommendation.moveToLast();

        String overall_status = recommendation.getString(1); // Healthy/Regular Unhealthy/Irregular
        String food_status = recommendation.getString(2);  //Healthy
        String sleep_status = recommendation.getString(3);
        String stress_status = recommendation.getString(4);
        String exercise_status = recommendation.getString(5);


        //Calculate average data of cycle
        Cursor cycle_table = db.getAllData(DatabaseHelper.TABLE_NAME_CYCLE);
        int average_cycle = 0;
        int average_period = 0;
        cycle_table.moveToLast();

        String last_start_day = cycle_table.getString(1);
        String last_end_day = cycle_table.getString(2);
        int last_cycle_length = cycle_table.getInt(3);
        int last_period_length = cycle_table.getInt(4);

        while (cycle_table.isFirst() != true) {
            average_cycle += cycle_table.getInt(3);
            average_period += cycle_table.getInt(4);
            cycle_table.moveToPrevious();
        }
        average_cycle += cycle_table.getInt(3);
        average_period += cycle_table.getInt(4);

        average_cycle = average_cycle / cycle_table.getCount() - 1;
        average_period = average_period / cycle_table.getCount() - 1;

        if (overall_status == "Healthy/Regular") {
            ArrayList<String> period_INFO = Algorithm.next_period_info(last_start_day, average_cycle, average_period);
            db.insertData_cycle(10000, period_INFO.get(0), period_INFO.get(1), average_cycle, average_period);
        }
        else {
            int average_change = 0;
            int count = 0;
            if (recommendation.getCount() > 1) {
                recommendation.moveToPrevious();
                while (recommendation.isFirst() != true) {
                    if (food_status != "Healthy" && recommendation.getString(2) != "Healthy") {
                        average_change += recommendation.getInt(6);
                        count += 1;
                    } else if (sleep_status != "Healthy" && recommendation.getString(3) != "Healthy") {
                        average_change += recommendation.getInt(6);
                        count += 1;
                    } else if (stress_status != "Healthy" && recommendation.getString(4) != "Healthy") {
                        average_change += recommendation.getInt(6);
                        count += 1;
                    } else if (exercise_status != "Healthy" && recommendation.getString(5) != "Healthy") {
                        average_change += recommendation.getInt(6);
                        count += 1;
                    }
                    recommendation.moveToPrevious();
                }
            }
            if (count != 0) {
                average_change = average_change / count;

                ArrayList<String> period_INFO = Algorithm.next_period_info(last_start_day,
                            average_cycle + average_change, average_period);
                db.insertData_cycle(10000, period_INFO.get(0), period_INFO.get(1),
                            average_cycle + average_change, average_period);
            } else {
                int randomnumber = (int) (Math.random() * 10) + (-5);
                ArrayList<String> period_INFO = Algorithm.next_period_info(last_start_day,
                            average_cycle + randomnumber, average_period);
                db.insertData_cycle(10000, period_INFO.get(0), period_INFO.get(1),
                            average_cycle + randomnumber, average_period);
            }

        }
    }



    public static int period_differentce(String startdate, String enddate){
        ArrayList<Integer> oneMoreDay = new ArrayList<>();
        oneMoreDay.add(1);
        oneMoreDay.add(3);
        oneMoreDay.add(5);
        oneMoreDay.add(7);
        oneMoreDay.add(8);
        oneMoreDay.add(10);
        oneMoreDay.add(12);

        System.out.print(enddate);


        int startmonth = Integer.parseInt(startdate.substring(5,7));
        int endmonth = Integer.parseInt(enddate.substring(5,7));

        int startday = Integer.parseInt(startdate.substring(8,10));
        int endday = Integer.parseInt(enddate.substring(8,10));

        System.out.println(endday);
        System.out.println(startday);

        int difference = 0;

        if (startmonth != endmonth){
            if (oneMoreDay.contains(startmonth)){
                difference = (31-startday) + endday;
            }
            else if (startmonth == 2){
                difference = (28-startday) + endday;
            }
            else{
                difference = (30-startday) + endday;
            }

        }
        else{
            difference = endday - startday;
        }

        System.out.println(difference);

        return difference;


    }

    //private void add_data(String summary_date,String overall_state,String food, String sleep, String stress, String exercise,int cycle_len_change){

        //Db.insertData_summary(summary_date,overall_state, food, sleep, stress, exercise, cycle_len_change);
    //}
}



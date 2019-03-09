package com.example.mycalendar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Record_Cycle extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_cycle);

        myDb.insertData_cycle(1,"2019-02-06","2019-02-12",25,6);

    }

    public void StartonClick(View view){

        RadioButton start_true = findViewById(R.id.PeriodStart_yes);
        RadioButton start_false = findViewById(R.id.PeriodStart_no);

        if (start_true.isChecked()){
            Date date = Calendar.getInstance().getTime();
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(date);
            add_data(1000, today, "NULL", 0, 0);
        }

        Intent goHome = new Intent(this, MainActivity.class);
        startActivity(goHome);

    }

    public void EndonClick(View view){
        RadioButton end_true = findViewById(R.id.PeriodEnd_yes);
        RadioButton end_false = findViewById(R.id.PeriodEnd_no);

        if (end_true.isChecked()) {


            Date date = Calendar.getInstance().getTime();
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(date);

            //Get startString to type Date
            Cursor data = myDb.getAllData(DatabaseHelper.TABLE_NAME_CYCLE);
            data.moveToLast();


            String startdateString = data.getString(1);


            data.moveToPrevious();
            String laststartdateString = data.getString(1);
            int ID = data.getInt(0) + 1;

            int cycle_difference = period_differentce(laststartdateString, startdateString);


            int difference = period_differentce(startdateString, today);
            myDb.deleteData_cycle();


            myDb.insertData_cycle(ID, startdateString, today, cycle_difference, difference);
            //Get current time
        }

        Intent goHome = new Intent(this,MainActivity.class);
        startActivity(goHome);


    }

    private int period_differentce(String startdate, String enddate){
        ArrayList<Integer> oneMoreDay = new ArrayList<>();
        oneMoreDay.add(1);
        oneMoreDay.add(3);
        oneMoreDay.add(5);
        oneMoreDay.add(7);
        oneMoreDay.add(8);
        oneMoreDay.add(10);
        oneMoreDay.add(12);


        int startmonth = Integer.parseInt(startdate.substring(5,7));
        int endmonth = Integer.parseInt(enddate.substring(5,7));

        int startday = Integer.parseInt(startdate.substring(8,10));
        int endday = Integer.parseInt(enddate.substring(8,10));

        System.out.println(endday);

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

    private void add_data(int cycle_id, String start_date, String end_date,
                          int cycle_length, int period_length){
        myDb.insertData_cycle(cycle_id, start_date, end_date, cycle_length, period_length);
    }

}
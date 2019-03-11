package com.example.mycalendar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Record_Sleep extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_sleep);
    }

    public void SleeponClick(View view){
        RadioButton sleep_true = findViewById(R.id.SleepYes);
        if (sleep_true.isChecked()){
            Date Time = Calendar.getInstance().getTime();
            Format formatter = new SimpleDateFormat("HH:mm");
            String time = formatter.format(Time);

            add_data("NULL", time, "NULL", 0,"NULL");

            Intent goHome = new Intent(this,MainActivity.class);
            startActivity(goHome);
        }
        else{
            Toast.makeText(this, "plz check the Yes box ", Toast.LENGTH_SHORT).show();
        }
    }

    public void AwakeonClick(View view){
        RadioButton awake_true = findViewById(R.id.AwakeYes);
        RadioButton Bad_true = findViewById(R.id.SleepBad);
        RadioButton Normal_true = findViewById(R.id.SleepNormal);
        RadioButton Good_true = findViewById(R.id.SleepGood);

        if (awake_true.isChecked()) {
            Date date = Calendar.getInstance().getTime();
            Format formatter = new SimpleDateFormat("HH:mm");
            String time = formatter.format(date);

            Cursor data = myDb.getAllData(DatabaseHelper.TABLE_NAME_SLEEP);
            data.moveToLast();

            String sleepTimeString = data.getString(1);
            int sleepHour = Integer.parseInt(sleepTimeString.substring(0, 2));
            int awakeHour = Integer.parseInt(time.substring(0, 2));


            int sleepMin = Integer.parseInt(sleepTimeString.substring(3, 5));
            int awakeMin = Integer.parseInt(time.substring(3, 5));


            float MinDiff = 0;
            if ((awakeMin >= sleepMin) && (awakeMin - sleepMin) >= 30){
                MinDiff = 1;
            }else if ((awakeMin < sleepMin) && ((awakeMin + 60 - sleepMin) >= 30)){
                MinDiff = 1;
            }

            float HourDiff;
            if (awakeHour <= sleepHour){
                HourDiff = awakeHour + (24 - sleepHour) + MinDiff;
            }else{
                HourDiff = awakeHour - sleepHour + MinDiff;
            }

            String quality = "normal";
            if (Bad_true.isChecked()){
                quality = "bad";
            }else if (Normal_true.isChecked()){
                quality = "normal";
            }else if (Good_true.isChecked()) {
                quality = "good";
            }

            Date DATE = Calendar.getInstance().getTime();
            Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String today = dateFormat.format(DATE);
            myDb.deleteData_sleep();

            add_data(today, sleepTimeString, time, HourDiff, quality);

            Intent goHome = new Intent(this,MainActivity.class);
            startActivity(goHome);

        }
        else{
            Toast.makeText(this, "plz check the Yes box ", Toast.LENGTH_SHORT).show();
        }

    }

    private void add_data(String date, String sleep_time, String awake_time, float sleep_length, String quality){

        myDb.insertData_sleep(date,sleep_time, awake_time, sleep_length, quality);
    }


}

package com.example.mycalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Record_Stress extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);
    Algorithm myAlgorithm = new Algorithm();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_stress);

    }

    public void StressonClick(View view){

        /* 29 days*/
        myDb.insertData_stress("2019-02-10", "Medium");
        myDb.insertData_stress("2019-02-11", "Stressful");
        myDb.insertData_stress("2019-02-12", "Medium");
        myDb.insertData_stress("2019-02-13", "Medium");
        myDb.insertData_stress("2019-02-14", "Stressful");
        myDb.insertData_stress("2019-02-15", "Stressful");
        myDb.insertData_stress("2019-02-16", "Medium");
        myDb.insertData_stress("2019-02-17", "Medium");
        myDb.insertData_stress("2019-02-18", "Stressful");
        myDb.insertData_stress("2019-02-19", "Medium");
        myDb.insertData_stress("2019-02-20", "Medium");
        myDb.insertData_stress("2019-02-21", "Medium");
        myDb.insertData_stress("2019-02-22", "Medium");
        myDb.insertData_stress("2019-02-23", "Medium");
        myDb.insertData_stress("2019-02-24", "Medium");
        myDb.insertData_stress("2019-02-25", "Stressful");
        myDb.insertData_stress("2019-02-26", "Medium");
        myDb.insertData_stress("2019-02-27", "Medium");
        myDb.insertData_stress("2019-02-28", "Medium");
        myDb.insertData_stress("2019-03-01", "Medium");
        myDb.insertData_stress("2019-03-02", "Stressful");
        myDb.insertData_stress("2019-03-03", "Medium");
        myDb.insertData_stress("2019-03-04", "Medium");
        myDb.insertData_stress("2019-03-05", "Medium");
        myDb.insertData_stress("2019-03-06", "Medium");
        myDb.insertData_stress("2019-03-07", "Stressful");
        myDb.insertData_stress("2019-03-08", "Medium");
        myDb.insertData_stress("2019-03-09", "Medium");
        myDb.insertData_stress("2019-03-10", "Medium");

        SeekBar stress_seekBar = findViewById(R.id.emotion_seekBar);
        int stress_level = stress_seekBar.getProgress();
        stress_level+=1;

        add_data(stress_level);

        Intent goHome = new Intent(this,MainActivity.class);
        startActivity(goHome);

        //Toast.makeText(Record_Food.this,Integer.toString(food_level),Toast.LENGTH_LONG).show();
    }


    private void add_data(int stress_level){
        Date date = Calendar.getInstance().getTime();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);
        if (stress_level < 3){
            myDb.insertData_stress(today,"Relaxed");
        }
        else if (stress_level == 3){
            myDb.insertData_stress(today,"Medium");
        }
        else if (stress_level > 3){
            myDb.insertData_stress(today,"Stressful");
        }

    }

}

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_stress);

        myDb.insertData_stress("123","relaxed");




    }

    public void StressonClick(View view){
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
            myDb.insertData_stress(today,"relaxed");
        }
        else if (stress_level == 3){
            myDb.insertData_stress(today,"medium");
        }
        else if (stress_level > 3){
            myDb.insertData_stress(today,"stress");
        }

    }

}

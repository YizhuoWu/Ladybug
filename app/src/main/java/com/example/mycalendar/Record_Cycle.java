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

public class Record_Cycle extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_cycle);
    }

    public void StartonClick(View view){
        RadioButton start_true = findViewById(R.id.PeriodStart_yes);
        RadioButton start_false = findViewById(R.id.PeriodStart_no);

        if (start_false.isChecked()){
            //add_data();
        }

        if (start_true.isChecked()){
            //add_data();
        }

        Intent goHome = new Intent(this,MainActivity.class);
        startActivity(goHome);
    }

    public void EndonClick(View view){
        RadioButton end_true = findViewById(R.id.PeriodEnd_yes);
        RadioButton end_false = findViewById(R.id.PeriodEnd_no);

        if (end_false.isChecked()){
            //add_data();
        }

        if (end_true.isChecked()){
            //add_data();
        }

        Intent goHome = new Intent(this,MainActivity.class);
        startActivity(goHome);
    }

    private void add_data(){
        Date date = Calendar.getInstance().getTime();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);

        //myDb.insertData_cycle(today);

    }

}
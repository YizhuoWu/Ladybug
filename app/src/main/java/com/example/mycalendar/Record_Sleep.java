package com.example.mycalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Record_Sleep extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    RadioButton sleep_start = findViewById(R.id.radioButton);
    RadioButton sleep_end = findViewById(R.id.radioButton6);
    RadioButton quality_good = findViewById(R.id.radioButton2);
    RadioButton quality_normal = findViewById(R.id.radioButton3);
    RadioButton quality_bad = findViewById(R.id.radioButton4);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_sleep);
    }


}

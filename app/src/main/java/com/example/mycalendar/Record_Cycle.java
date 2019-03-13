package com.example.mycalendar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Record_Cycle extends AppCompatActivity {

    Algorithm myAlgorithm = new Algorithm();
    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_cycle);

        //myDb.insertData_cycle(1,"2019-02-06","2019-02-12",25,6);

    }

    public void StartonClick(View view){

        RadioButton start_true = findViewById(R.id.PeriodStart_yes);
        RadioButton start_false = findViewById(R.id.PeriodStart_no);
        //myDb.insertData_cycle(3,"2019-03-12","2019-03-18",28,7);
        if (start_true.isChecked()){
            Date date = Calendar.getInstance().getTime();
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(date);
            myDb.deleteData_cycle_predicted_row();
            add_data(0, today, "NULL", 0, 0);
        }

        //Inserting data for 1 month
        myDb.insertData_summary("2019-01-15","Unhealthy/Irregular","Low fat and low sugar with No Breakfast","healthy" ,"healthy","healthy",-5);

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
            String lastenddateString = data.getString(2);
            int ID = data.getInt(0) + 1;

            int cycle_difference = Algorithm.period_differentce(laststartdateString, startdateString);


            int difference = Algorithm.period_differentce(startdateString, today);
            myDb.deleteData_cycle();


            myDb.insertData_cycle(ID, startdateString, today, cycle_difference, difference);

            myAlgorithm.Record_Summary(myDb, lastenddateString, today);

            Algorithm.predict_next_period(myDb);
            //Get current time
        }

        Intent goHome = new Intent(this,MainActivity.class);
        startActivity(goHome);



    }



    private void add_data(int cycle_id, String start_date, String end_date,
                          int cycle_length, int period_length){
        myDb.insertData_cycle(cycle_id, start_date, end_date, cycle_length, period_length);
    }

}
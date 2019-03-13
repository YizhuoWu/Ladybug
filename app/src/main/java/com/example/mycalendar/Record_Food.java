package com.example.mycalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Record_Food extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);
    Algorithm myAlgorithm = new Algorithm();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record__food);
    }


    public void FoodonClick(View view){
        SeekBar food_seekBar = findViewById(R.id.food_seekBar);
        int food_level = food_seekBar.getProgress();
        food_level+=1;

        RadioButton breakfast_true = findViewById(R.id.breakfast_yes);
        RadioButton breakfast_false = findViewById(R.id.breakfast_no);
        //Date date = Calendar.getInstance().getTime();
        //String today = date.toString();

        /* 36 days*/
        myDb.insertData_food("2019-02-07",1,"Normal diet");
        myDb.insertData_food("2019-02-08",1,"Normal diet");
        myDb.insertData_food("2019-02-09",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-10",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-11",0,"Low fat and low sugar");
        myDb.insertData_food("2019-02-12",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-13",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-14",0,"Low fat and low sugar");
        myDb.insertData_food("2019-02-15",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-16",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-17",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-18",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-19",1,"High fat and high sugar");
        myDb.insertData_food("2019-02-20",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-21",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-22",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-23",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-24",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-25",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-26",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-27",1,"Low fat and low sugar");
        myDb.insertData_food("2019-02-28",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-01",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-02",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-03",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-04",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-05",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-06",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-07",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-08",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-09",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-10",1,"Low fat and low sugar");
        myDb.insertData_food("2019-03-11",1,"Normal diet");
        myDb.insertData_food("2019-03-12",1,"Normal diet");
        myDb.insertData_food("2019-03-13",1,"Normal diet");
        myDb.insertData_food("2019-03-14",1,"Normal diet");



        if (breakfast_false.isChecked()){
            add_data(0,food_level);
        }

        if (breakfast_true.isChecked()){
            add_data(1,food_level);
        }

        Intent goHome = new Intent(this,MainActivity.class);
        startActivity(goHome);

        //Toast.makeText(Record_Food.this,Integer.toString(food_level),Toast.LENGTH_LONG).show();
    }


    private void add_data(int breakfast, int food_level){
        Date date = Calendar.getInstance().getTime();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);
        if (food_level <= 3){
            myDb.insertData_food(today,breakfast,"Low fat and low sugar");
        }
        if (food_level <= 6 && food_level> 3){
            myDb.insertData_food(today,breakfast,"Normal diet");
        }
        else if (food_level >= 7){
            myDb.insertData_food(today,breakfast,"High fat and high sugar");
        }

    }


}

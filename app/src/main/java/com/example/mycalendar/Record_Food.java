package com.example.mycalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Record_Food extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record__food);
    }


    public void onClick(View view){
        SeekBar food_seekBar = findViewById(R.id.food_seekBar);
        int food_level = food_seekBar.getProgress();
        food_level+=1;

        RadioButton breakfast_true = findViewById(R.id.breakfast_yes);
        RadioButton breakfast_false = findViewById(R.id.breakfast_no);
        //Date date = Calendar.getInstance().getTime();
        //String today = date.toString();

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
            myDb.insertData_food(today,breakfast,"Low-fat and low sugar");
        }
        if (food_level <= 6 && food_level> 3){
            myDb.insertData_food(today,breakfast,"Normal diet");
        }
        else if (food_level >= 7){
            myDb.insertData_food(today,breakfast,"High fat and high sugar");
        }

    }


}

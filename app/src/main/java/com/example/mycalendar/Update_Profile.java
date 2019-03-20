package com.example.mycalendar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Float.parseFloat;


public class Update_Profile extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
    }

    public void ProfileonClick(View view){
        Button profile = (Button) findViewById(R.id.profile_record_button);
        EditText id = (EditText) findViewById(R.id.editName);
        EditText age = (EditText) findViewById(R.id.editAge);
        EditText weight = (EditText) findViewById(R.id.editWeight);
        EditText height = (EditText) findViewById(R.id.editHeight);


        String ID = id.getText().toString();
        String Age = age.getText().toString();
        String Weight = weight.getText().toString();
        String Height = height.getText().toString();

        while (true) {

            if (ID.matches("")) {
                Toast.makeText(this, "plz enter your name ", Toast.LENGTH_SHORT).show();
                break;
            } else if (Age.matches("")) {
                Toast.makeText(this, "plz enter your age ", Toast.LENGTH_SHORT).show();
                break;
            } else if (Weight.matches("")) {
                Toast.makeText(this, "plz enter your weight ", Toast.LENGTH_SHORT).show();
                break;
            } else if (Height.matches("")) {
                Toast.makeText(this, "plz enter your height ", Toast.LENGTH_SHORT).show();
                break;
            } else {
                int finalAge = Integer.parseInt(Age);
                int finalWeight = Integer.parseInt(Weight);
                Float finalHeight = parseFloat(Height);

                Intent goHome = new Intent(this, MainActivity.class);
                startActivity(goHome);

                myDb.insertData_profile(ID, finalAge, finalHeight, finalWeight);
                break;
            }
        }
    }

    public void FirstCycleonClick(View view){
        Button LastCycle = (Button) findViewById(R.id.cycle_record_button);
        EditText start = (EditText) findViewById(R.id.editStartDate);
        EditText end = (EditText) findViewById(R.id.editEndDate);
        EditText cycle = (EditText) findViewById(R.id.Cycle);
        EditText period = (EditText) findViewById(R.id.Period);


        String Start = start.getText().toString();
        String End = end.getText().toString();
        String Cycle = cycle.getText().toString();
        String Period = period.getText().toString();



        while (true) {

            if (Start.matches("")) {
                Toast.makeText(this, "plz enter your startdata ", Toast.LENGTH_SHORT).show();
                break;
            } else if (End.matches("")) {
                Toast.makeText(this, "plz enter your enddata ", Toast.LENGTH_SHORT).show();
                break;
            } else if (Cycle.matches("")) {
                Toast.makeText(this, "plz enter your cycle length ", Toast.LENGTH_SHORT).show();
                break;

            } else if (Period.matches("")) {
                Toast.makeText(this, "plz enter your period length ", Toast.LENGTH_SHORT).show();
                break;
            } else {
                int finalCycleLength = Integer.parseInt(Cycle);
                int finalPeriodLength = Integer.parseInt(Period);
                myDb.insertData_cycle(1, Start, End, finalCycleLength,finalPeriodLength);
                break;
            }
        }

        Cursor data = myDb.getAllData(DatabaseHelper.TABLE_NAME_CYCLE);
        if (data.getCount() != 0){
            data.moveToLast();
            String startdata = data.getString(1);
            int cycleLenghth = data.getInt(3);
            int periodLenghth = data.getInt(4);

            ArrayList<String> nextInfo = Algorithm.next_period_info(startdata,cycleLenghth,periodLenghth);

            myDb.insertData_cycle(10000,nextInfo.get(0), nextInfo.get(1),cycleLenghth,periodLenghth);
            System.out.print("Test");
            Intent goHome = new Intent(this, MainActivity.class);
            startActivity(goHome);}

    }
}

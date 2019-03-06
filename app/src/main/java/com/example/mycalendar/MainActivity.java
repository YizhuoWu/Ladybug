package com.example.mycalendar;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mainDb;

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        *Main data base constructed here.
        * Right now only one table -- "Cycle Table" constructed inside Main DB.
        * Variable Name = mainDb.
        * Sample data will be inserted to Main DB Cycle Table.
        * If success, the message "Sample Data Inserted" will show.
        * Else failed message will show.
        *
        *

        mainDb = new DatabaseHelper(this);
        boolean isInserted = mainDb.insertData_cycle(0,"Sample Start","Sample End",10,5);
        boolean isInserted_food = mainDb.insertData_food("20180201",1,"High High");
        boolean isInserted_sleep = mainDb.insertData_sleep("20180201","1920","0820","good");
        boolean isInserted_exercise = mainDb.insertData_exercise("20180201","yoga",20);
        boolean isInserted_stress = mainDb.insertData_stress("20180201",2);
        boolean isInserted_recommend = mainDb.insertData_recommend("good","late");
        boolean isInserted_summary = mainDb.insertData_summary("April","good","good","good","no","good",2);

        Toast.makeText(MainActivity.this,"All Sample Data Inserted",Toast.LENGTH_LONG).show();

        if(isInserted = true){
            Toast.makeText(MainActivity.this,"Sample Data Inserted",Toast.LENGTH_LONG).show();
        }
        if(isInserted = false){
            Toast.makeText(MainActivity.this,"Sample Data Not Inserted",Toast.LENGTH_LONG).show();
        }
        */
        //Sample Data inserted completed here.

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.getMenu().getItem(0).setCheckable(false);
        bottomNav.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);




        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        //Set an event for Teacher's Professional Day 2016 which is 21st of October

        Event ev1 = new Event(Color.RED,1551427200000L,"Teacher's Professional Day");

        compactCalendar.addEvent(ev1);


        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if (dateClicked.toString().compareTo("Fri Mar 01 00:00:00 PST 2019") == 0){
                    String temp = dateClicked.toString();
                    Log.d("mytag",temp);
                    Toast.makeText(context, "Teachers' Professional Day", Toast.LENGTH_SHORT).show();
                }
                else{
                    String temp = dateClicked.toString();
                    Log.d("mytag",temp);

                    Toast.makeText(context, "No Events Planned for that day", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override

                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch(menuItem.getItemId()){
                        case R.id.navigation_profile:
                            menuItem.setCheckable(true);
                            selectedFragment = new PorfileFragment();
                            break;
                        case R.id.navigation_food:
                            selectedFragment = new FoodFragment();
                            break;
                        case R.id.navigation_sleep:
                            selectedFragment = new SleepFragment();
                            break;
                        case R.id.navigation_exercise:
                            selectedFragment = new ExerciseFragment();
                            break;
                        case R.id.navigation_stress:
                            selectedFragment = new StressFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

}

package com.example.mycalendar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Calendar;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mainDb = new DatabaseHelper(this);
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    //new
    //private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.getMenu().getItem(0).setCheckable(false);
        bottomNav.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        Button Recom = (Button) findViewById(R.id.RecomButt);


        Cursor periodTable = mainDb.getAllData(DatabaseHelper.TABLE_NAME_CYCLE);
        if (periodTable.getCount() != 0) {
            TextView toNextPeriod = findViewById(R.id.to_period);

            periodTable.moveToLast();
            Date date = Calendar.getInstance().getTime();
            Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(date);
            toNextPeriod.setText(Integer.toString(
                    Algorithm.period_differentce(today, periodTable.getString(1))));

        }


        //Insert Cycle sample here
        DatabaseHelper myDb = new DatabaseHelper(this);


        add_all_from_db(myDb);

        final String s = "2019-03-03";

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();


                if (dateClicked.toString().compareTo(make_compare_str(s)) == 0){
                    String temp = dateClicked.toString();
                    Log.d("mytag",temp);
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }



                else{
                    String temp = dateClicked.toString();
                    Log.d("mytag",temp);

                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        }


        );


        upload_dataset();





    }

    private void upload_dataset(){
        Cursor profiletable = mainDb.getAllData(DatabaseHelper.TABLE_NAME_PROFILE);
        profiletable.moveToLast();
        if (profiletable.getCount()!=0){
            String username = profiletable.getString(0);
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference(username);

            //This is for uploading the cycle table.
            Cursor cycletable = mainDb.getAllData(DatabaseHelper.TABLE_NAME_CYCLE);
            cycletable.moveToLast();
            while (cycletable.isFirst() != true && cycletable.getCount() > 0){
                String ID = Integer.toString(cycletable.getInt(0));
                String Startday = cycletable.getString(1);
                String Endday = cycletable.getString(2);
                String cycleLength = cycletable.getString(3);
                String periodLength = cycletable.getString(4);
                myRef.child("Cycle").child(ID).child("StartDay").setValue(Startday);
                myRef.child("Cycle").child(ID).child("Endday").setValue(Endday);
                myRef.child("Cycle").child(ID).child("CycleLength").setValue(cycleLength);
                myRef.child("Cycle").child(ID).child("PeriodLength").setValue(periodLength);
                cycletable.moveToPrevious();
            }
            cycletable.close();

            //This is for uploading the Food table
            Cursor foodtable = mainDb.getAllData(DatabaseHelper.TABLE_NAME_FOOD);
            foodtable.moveToLast();
            while (foodtable.isFirst() != true && foodtable.getCount() > 0){
                String Date = foodtable.getString(0);
                String is_breakfast = Integer.toString(foodtable.getInt(1));
                String sugar_fat_level = foodtable.getString(2);
                myRef.child("Food").child(Date).child("is_breakfast").setValue(is_breakfast);
                myRef.child("Food").child(Date).child("sugar_fat_level").setValue(sugar_fat_level);
                foodtable.moveToPrevious();
            }
            foodtable.close();

            //This is for uploading the Exercise table
            Cursor exercisetable = mainDb.getAllData(DatabaseHelper.TABLE_NAME_EXERCISE);
            exercisetable.moveToLast();
            while (exercisetable.isFirst() != true && exercisetable.getCount() > 0){
                String Date = exercisetable.getString(0);
                String type = exercisetable.getString(1);
                String weight = Integer.toString(exercisetable.getInt(2));
                myRef.child("Exercise").child(Date).child("type").setValue(type);
                myRef.child("Exercise").child(Date).child("weight").setValue(weight);
                exercisetable.moveToPrevious();
            }
            exercisetable.close();

            //This is for uploading the Sleep table

            Cursor sleeptable = mainDb.getAllData(DatabaseHelper.TABLE_NAME_SLEEP);
            sleeptable.moveToLast();
            while (sleeptable.isFirst() != true && sleeptable.getCount() > 0){
                String Date = sleeptable.getString(0);
                String Startday = sleeptable.getString(1);
                String Endday = sleeptable.getString(2);
                String cycleLength = sleeptable.getString(3);
                String periodLength = sleeptable.getString(4);
                myRef.child("Sleep").child(Date).child("StartTime").setValue(Startday);
                myRef.child("Sleep").child(Date).child("EndTime").setValue(Endday);
                myRef.child("Sleep").child(Date).child("SleepLength").setValue(cycleLength);
                myRef.child("Sleep").child(Date).child("Quality").setValue(periodLength);
                sleeptable.moveToPrevious();
            }
            sleeptable.close();

            //This is for uploading the Stress table
            Cursor stresstable = mainDb.getAllData(DatabaseHelper.TABLE_NAME_STRESS);
            stresstable.moveToLast();
            while (stresstable.isFirst() != true && stresstable.getCount() > 0){
                String Date = stresstable.getString(0);
                String stress_level = stresstable.getString(1);
                myRef.child("Stress").child(Date).child("stress_level").setValue(stress_level);
                stresstable.moveToPrevious();
            }
            stresstable.close();


        }


    }

    public void changeButtonVisibility(boolean visibility){
        Button Recom = (Button) findViewById(R.id.RecomButt);
        if(visibility){
            Recom.setVisibility(View.VISIBLE);
        }else{
            Recom.setVisibility(View.GONE);
        }
    }

    public void RecomonClick(View view){
        Button Recom = (Button) findViewById(R.id.RecomButt);
        Intent goRecom = new Intent(this,Status_History.class);
        startActivity(goRecom);

        //Toast.makeText(Record_Food.this,Integer.toString(food_level),Toast.LENGTH_LONG).show();
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

    /**
     *
     * @param s:formatted data returned by mainDB. Format:yyyy-MM-DD.
     * @return a Date object used for getting seconds from 1970.01.01.
     */
    private Date get_date(String s){
        String y = s.substring(0,4);
        int year = Integer.parseInt(y) -1900;
        String m = s.substring(5,7);
        int month = Integer.parseInt(m)-1;
        String d = s.substring(8,10);
        int day = Integer.parseInt(d);
        Date date = new Date(year,month,day);
        return date;
    }

    /**
     *
     * @param s
     */
    private void add_event(String s){
        Date d = get_date(s);
        long long_date = d.getTime();
        Event ev1 = new Event(Color.RED,long_date,"Period day");
        compactCalendar.addEvent(ev1);
    }

    private void add_event_date(Date d){
        long long_date = d.getTime();
        Event ev1 = new Event(Color.RED,long_date,"Period day");
        compactCalendar.addEvent(ev1);
    }

    private void remove_date_str(String s){
        Date d = get_date(s);
        long long_date = d.getTime();
        compactCalendar.removeEvents(long_date);
    }

    private void remove_event_date(Date d){
        long long_date = d.getTime();
        compactCalendar.removeEvents(long_date);
    }

    /**
     *
     * @param s
     * @return
     */
    private String make_compare_str(String s){
        String result = "";
        Date d = get_date(s);
        SimpleDateFormat weekday = new SimpleDateFormat("E");
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        SimpleDateFormat timezone = new SimpleDateFormat("zzz");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        result += weekday.format(d);
        result += " ";
        result += month.format(d);
        result += " ";
        result += day.format(d);
        result += " 00:00:00 ";
        result += timezone.format(d);
        result += " ";
        result += year.format(d);
        return result;
    }

    private String get_start_date_from_db(DatabaseHelper db){
        Cursor data = db.getAllData(DatabaseHelper.TABLE_NAME_CYCLE);
        data.moveToLast();
        String start_date;
        start_date = data.getString(1);
        return start_date;
    }
    private String get_end_date_from_db(DatabaseHelper db){
        Cursor data = db.getAllData(DatabaseHelper.TABLE_NAME_CYCLE);
        data.moveToLast();
        String end_date;
        end_date = data.getString(2);
        return end_date;
    }

    public List<Date> addDatesBetween(Date startDate, Date endDate){
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);



            List<Event> L =  compactCalendar.getEvents(result);
            if(L.size() == 0) {
                add_event_date(result);
            }


            //add_event_date(result);
        }


        List<Event> L1 =  compactCalendar.getEvents(endDate);
        if(L1.size() == 0) {
            add_event_date(endDate);
        }

        //add_event_date(endDate);
        return datesInRange;
    }

    public List<Date> removeDatesBetween(Date startDate, Date endDate){
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);

            remove_event_date(result);

        }
        remove_event_date(endDate);
        return datesInRange;
    }

    public void add_all_from_db(DatabaseHelper db){
        Cursor data = db.getAllData(DatabaseHelper.TABLE_NAME_CYCLE);
        data.moveToLast();
        if (data.getCount() != 0){
            while(data.isFirst() == false){

                int edge = data.getInt(0);
                if (edge != 10000) {
                    String start_date;
                    start_date = data.getString(1);

                    String end_date;
                    end_date = data.getString(2);

                    if(start_date == null || end_date == null ) {}
                    else{
                        Date st = get_date(start_date);
                        Date ed = get_date(end_date);
                        addDatesBetween(st, ed);
                    }
                    data.moveToPrevious();
                }
                data.moveToPrevious();
            }
            int edge_1 = data.getInt(0);
            if (edge_1 != 10000) {
                String start_date_1;
                start_date_1 = data.getString(1);
                String end_date_1;
                end_date_1 = data.getString(2);

                if((!start_date_1.equals("NULL")) && !end_date_1.equals("NULL")) {

                        System.out.println("case1:");
                        System.out.println(start_date_1);
                        System.out.println(end_date_1);

                        Date st_1 = get_date(start_date_1);
                        Date ed_1 = get_date(end_date_1);
                        addDatesBetween(st_1, ed_1);

                }
                else{
                    System.out.println("case2:");
                    System.out.println(start_date_1);
                    System.out.println(end_date_1);
                }
            }
        }
    }


}

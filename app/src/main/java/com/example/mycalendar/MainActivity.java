package com.example.mycalendar;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Calendar;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mainDb;
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

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

        /*
        Cursor periodTable = mainDb.getAllData(DatabaseHelper.TABLE_NAME_CYCLE);
        if (periodTable.getCount() != 0) {
            TextView toNextPeriod = findViewById(R.id.to_period);

            periodTable.moveToFirst();
            Date date = Calendar.getInstance().getTime();
            Format formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(date);
            toNextPeriod.setText(Integer.toString(
                    Algorithm.period_differentce(today, periodTable.getString(1))));

        }
        */
        
        /*Inserr Cycle sample here*/
        DatabaseHelper myDb = new DatabaseHelper(this);
        boolean r = myDb.insertData_cycle(3,"2019-03-13","2019-03-18",28,7);
        String start = get_start_date_from_db(myDb);
        String end = get_end_date_from_db(myDb);
        Date st = get_date(start);
        Date e = get_date(end);
        getDatesBetween(st,e);
        final String s = "2019-03-03";

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();


                if (dateClicked.toString().compareTo(make_compare_str(s)) == 0){
                    String temp = dateClicked.toString();
                    Log.d("mytag",temp);
                    Toast.makeText(context, "This is a period day", Toast.LENGTH_SHORT).show();
                }



                else{
                    String temp = dateClicked.toString();
                    Log.d("mytag",temp);

                    Toast.makeText(context, "Not a Period day", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        }


        );
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

    public List<Date> getDatesBetween(Date startDate, Date endDate){
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);

            add_event_date(result);


        }
        add_event_date(endDate);
        return datesInRange;
    }


}

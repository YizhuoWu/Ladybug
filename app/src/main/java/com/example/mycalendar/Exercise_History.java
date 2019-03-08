package com.example.mycalendar;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Exercise_History extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_history);


        TextView exercise_history = findViewById(R.id.exercise_history);
        Cursor data = db.getAllData(DatabaseHelper.TABLE_NAME_EXERCISE);
        data.moveToLast();
        String print = "";

        if (data.getCount() != 0) {

            while (data.isFirst() == false) {
                print += data.getString(0);
                print += "     ";
                print += data.getString(1);
                print += "     ";
                print += data.getInt(2) + "\n";
                data.moveToPrevious();
            }

            print += data.getString(0);
            print += "     ";
            print += data.getString(1);
            print += "     ";
            print += data.getInt(2) + "\n";
            data.moveToPrevious();

            exercise_history.setText(print);
        }
        else{
            exercise_history.setText("You don't have any data");
        }
    }
}


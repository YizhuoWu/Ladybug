package com.example.mycalendar;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Cycle_History extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_history);

        TextView cycle_history = findViewById(R.id.cycle_history);
        Cursor data = db.getAllData(DatabaseHelper.TABLE_NAME_CYCLE);
        data.moveToLast();
        String print = "";
        while (data.isFirst() == false) {
            print += data.getInt(0);
            print += "     ";
            print += data.getString(1);
            print += "     ";
            print += data.getString(2);
            print += "     ";
            print += data.getInt(3);
            print += "     ";
            print += data.getInt(4) + "\n";
            data.moveToPrevious();
        }

        print += data.getInt(0);
        print += "     ";
        print += data.getString(1);
        print += "     ";
        print += data.getString(2);
        print += "     ";
        print += data.getInt(3);
        print += "     ";
        print += data.getInt(4) + "\n";
        cycle_history.setText(print);

    }
}

package com.example.mycalendar;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Stress_History extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_history);

        TextView stress_history = findViewById(R.id.stress_history);
        Cursor data = db.getAllData(DatabaseHelper.TABLE_NAME_STRESS);
        data.moveToLast();
        String print = "";

        if (data.getCount() != 0) {

            while (data.isFirst() == false) {
                print += data.getString(0);
                print += "     ";
                print += data.getString(1) + "\n";
                data.moveToPrevious();
            }
            print += data.getString(0);
            print += "     ";
            print += data.getString(1) + "\n";
            data.moveToPrevious();

            stress_history.setText(print);
        }
        else{
            stress_history.setText("You don't have any data");
        }
    }

}


package com.example.mycalendar;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Sleep_History extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_history);


        TextView sleep_history = findViewById(R.id.sleep_history);
        Cursor data = db.getAllData(DatabaseHelper.TABLE_NAME_SLEEP);
        data.moveToLast();
        String result = "Date           Start       End     Sleep length    Quality \n";

        if (data.getCount() != 0) {

            while (data.isFirst() == false) {
                //Get Sleep date (Columnn0)
                result += data.getString(0);
                result += "   ";

                //Get Sleep Start time
                result += data.getString(1);
                result += "       ";

                result += data.getString(2);
                result += "       ";

                result += data.getString(3);
                result += "             ";

                result += data.getString(4) + "\n";
                data.moveToPrevious();
            }
            result += data.getString(0);
            result += "   ";

            //Get Sleep Start time
            result += data.getString(1);
            result += "       ";

            result += data.getString(2);
            result += "       ";

            result += data.getString(3);
            result += "             ";

            result += data.getString(4) + "\n";
            data.moveToPrevious();

            sleep_history.setText(result);
        }
        else{
            sleep_history.setText("There is no history. Come and record your day!");
        }
    }

}
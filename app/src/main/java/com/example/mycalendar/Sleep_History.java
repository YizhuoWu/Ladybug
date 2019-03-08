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
        String result = "";

        if (data.getCount() != 0) {

            while (data.isFirst() == false) {
                //Get Sleep date (Columnn0)
                result += "Date: ";
                result += data.getString(0);
                result += "  ";

                //Get Sleep Start time
                result += "Start: ";
                result += data.getString(1);
                result += "  ";

                result += "End: ";
                result += data.getString(2) + '\n';

                result += "Sleep length: ";
                result += data.getString(3);
                result += "  ";

                result += "Quality: ";
                result += data.getString(4) + "\n\n";
                data.moveToPrevious();
            }
            result += "Date: ";
            result += data.getString(0);
            result += "  ";

            //Get Sleep Start time
            result += "Start: ";
            result += data.getString(1);
            result += "  ";

            result += "End: ";
            result += data.getString(2) + '\n';

            result += "Sleep length: ";
            result += data.getString(3);
            result += "  ";

            result += "Quality: ";
            result += data.getString(4) + "\n\n";
            data.moveToPrevious();

            sleep_history.setText(result);
        }
        else{
            sleep_history.setText("There is no history. Come and record your day!");
        }
    }

}
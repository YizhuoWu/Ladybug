package com.example.mycalendar;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Status_History extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_history);

        TextView status_history = findViewById(R.id.status_history);
        Cursor data = db.getAllData(DatabaseHelper.TABLE_NAME_SUMMARY);
        data.moveToLast();
        String result = "";

        if (data.getCount() != 0) {
            while (data.isFirst() == false) {
                //Get Sleep date (Columnn0)
                result += "Month:  ";
                result += data.getString(0).substring(0,7);
                result += "\n";

                //Get Sleep Start time
                result += "Health State: ";
                result += data.getString(1);
                result += "\n";

                result += "Substate--Food:  ";
                result += data.getString(2);
                result += " \n";


                result += "Substate--Sleep:  ";
                result += data.getString(3);
                result += "\n";

                result += "Substate--Stress:  ";
                result += data.getString(4);
                result += " \n";

                result += "Substate--Exercise:  ";
                result += data.getString(5) + "\n";

                result += "Recommendation:" + "\n";
                result += data.getString(7) + "\n\n";

                data.moveToPrevious();
            }
            result += "Month:  ";
            result += data.getString(0).substring(0,7);
            result += "\n";

            //Get Sleep Start time
            result += "Health State:  ";
            result += data.getString(1);
            result += "\n";

            result += "Substate--Food:  ";
            result += data.getString(2);
            result += "\n";




            result += "Substate--Sleep:  ";
            result += data.getString(3);
            result += "\n";

            result += "Substate--Stress:  ";
            result += data.getString(4);
            result += "\n";

            result += "Substate--Exercise:  ";
            result += data.getString(5) + "\n";

            result += "Recommendation:" + "\n";
            result += data.getString(7)  + "\n\n";

            status_history.setText(result);

        } else {
            status_history.setText("There is no history. Come and record your day!");
        }
    }
}


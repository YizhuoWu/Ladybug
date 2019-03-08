package com.example.mycalendar;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Food_History extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_history);

        TextView food_history = findViewById(R.id.food_history);
        Cursor data = db.getAllData(DatabaseHelper.TABLE_NAME_FOOD);
        if (data.getCount() != 0) {
            data.moveToLast();
            String print = "";
            while (data.isFirst() == false) {
                print += data.getString(0);
                print += "     ";
                if (data.getInt(1) == 0) {
                    print += "Don't Have Breakfast";
                    print += "    ";
                } else {
                    print += "Have Breakfast";
                    print += "              ";
                }
                print += data.getString(2) + "\n";
                data.moveToPrevious();
            }
            print += data.getString(0) + "  ";
            print += "     ";
            if (data.getInt(1) == 0) {
                print += "Don't Have Breakfast";
                print += "    ";
            } else {
                print += "Have Breakfast";
                print += "              ";
            }
            print += data.getString(2) + "\n";

            food_history.setText(print);
        }
        else{
            food_history.setText("There is no history");
        }
    }

}

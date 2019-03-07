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


        TextView test = findViewById(R.id.test);
        Cursor data = db.getAllData(DatabaseHelper.TABLE_NAME_FOOD);
        data.moveToNext();
        String print = "";
        while (data.isLast() == false){

            print += data.getString(0) + "  ";
            if (data.getInt(1) == 0) {
                print += "Don't Have Breakfast";
                print += "    ";
            }
            else{
                print += "Have Breakfast";
                print += "              ";
            }
            print += data.getString(2) + "\n";
            data.moveToNext();
        }
        print += data.getString(0) + "  ";
        if (data.getInt(1) == 0) {
            print += "Don't Have Breakfast";
            print += "    ";
        }
        else{
            print += "Have Breakfast";
            print += "              ";
        }
        print += data.getString(2) + "\n";

        test.setText(print);



    }

    public void Test(View view){

    }
}

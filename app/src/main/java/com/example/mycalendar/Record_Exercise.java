package com.example.mycalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Record_Exercise extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);
    Algorithm myAlgorithm = new Algorithm();
    Spinner ExerciseType;
    EditText E_time;
    EditText E_weight;
    Button E_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_exercise);



        ExerciseType = (Spinner) findViewById(R.id.exercise_type_spinner);
        final String[] Etypes = {"Please select:","Strenuous exercise","Chronic exercise","No additional exercise"};
        ArrayAdapter<String> TypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Etypes);
        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ExerciseType.setAdapter(TypeAdapter);

        E_time = (EditText) findViewById(R.id.Time_Input);
        E_weight = (EditText) findViewById(R.id.Weight_Input);
        E_update = (Button) findViewById(R.id.exercise_record_save);

    }

    public void onClick(View view){
        /* 29 days*/
        myDb.insertData_exercise("2019-02-09", "Strenuous exercise", 111);
        myDb.insertData_exercise("2019-02-10", "Strenuous exercise", 110);
        myDb.insertData_exercise("2019-02-11", "Strenuous exercise", 110);
        myDb.insertData_exercise("2019-02-12", "Strenuous exercise", 109);
        myDb.insertData_exercise("2019-02-13", "Strenuous exercise", 109);
        myDb.insertData_exercise("2019-02-14", "Strenuous exercise", 109);
        myDb.insertData_exercise("2019-02-15", "Chronic exercise", 110);
        myDb.insertData_exercise("2019-02-16", "Strenuous exercise", 108);
        myDb.insertData_exercise("2019-02-17", "Chronic exercise", 108);
        myDb.insertData_exercise("2019-02-18", "Strenuous exercise", 107);
        myDb.insertData_exercise("2019-02-19", "Strenuous exercise", 108);
        myDb.insertData_exercise("2019-02-20", "Strenuous exercise", 109);
        myDb.insertData_exercise("2019-02-21", "Strenuous exercise", 109);
        myDb.insertData_exercise("2019-02-22", "Strenuous exercise", 109);
        myDb.insertData_exercise("2019-02-23", "Strenuous exercise", 109);
        myDb.insertData_exercise("2019-02-24", "Chronic exercise", 109);
        myDb.insertData_exercise("2019-02-25", "Strenuous exercise", 109);
        myDb.insertData_exercise("2019-02-26", "No additional exercise", 110);
        myDb.insertData_exercise("2019-02-27", "Chronic exercise", 110);
        myDb.insertData_exercise("2019-02-28", "No additional exercise", 110);
        myDb.insertData_exercise("2019-03-01", "No additional exercise", 112);
        myDb.insertData_exercise("2019-03-02", "No additional exercise", 112);
        myDb.insertData_exercise("2019-03-03", "Strenuous exercise", 110);
        myDb.insertData_exercise("2019-03-04", "No additional exercise", 108);
        myDb.insertData_exercise("2019-03-05", "Strenuous exercise", 107);
        myDb.insertData_exercise("2019-03-06", "Strenuous exercise", 107);
        myDb.insertData_exercise("2019-03-07", "Chronic exercise", 106);
        myDb.insertData_exercise("2019-03-08", "Strenuous exercise", 106);
        myDb.insertData_exercise("2019-03-09", "Strenuous exercise", 104);

        while (true) {
            String weight = E_weight.getText().toString();
            String type = ExerciseType.getSelectedItem().toString();

            if (weight.matches("")) {
                Toast.makeText(this, "plz enter your weight ", Toast.LENGTH_SHORT).show();
                break;
            }
            else if (type.matches("Please select:")) {
                Toast.makeText(this, "plz enter your exercise status ", Toast.LENGTH_SHORT).show();
                break;
            }
            else if ((E_time.getText().toString()).matches("")) {
                Toast.makeText(this, "plz enter your time for exercise ", Toast.LENGTH_SHORT).show();
                break;
            }
            else{
                int finalWeight = Integer.parseInt(weight);
                Intent goHome = new Intent(this, MainActivity.class);
                startActivity(goHome);

                add_data(type, finalWeight);
                myAlgorithm.Record_Summary(myDb);
                break;
            }
        }




    }

    private void add_data(String exercise_type, int weight){
        Date date = Calendar.getInstance().getTime();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);
        String time = E_time.getText().toString();
        int finalTime =  Integer.parseInt(time);

        if (exercise_type == "Strenuous exercise")
        {
            if (finalTime >= 30) {
                exercise_type = "Strenuous exercise";
            } else {
                exercise_type = "Chronic exercise";
            }
        }
        else if (exercise_type == "Chronic exercise")
        {
            if (finalTime >= 45) {
                exercise_type = "Strenuous exercise";
            } else {
                exercise_type = "Chronic exercise";
            }
        }
        else {
            exercise_type = "No additional exercise";
        }

        myDb.insertData_exercise(today, exercise_type, weight);
    }

}




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

        myDb.insertData_exercise("2018-03-04", "S", 100);
        myDb.insertData_exercise("2018-03-05", "A", 120);
        myDb.insertData_exercise("2018-03-06", "D", 130);
        myDb.insertData_exercise("2018-03-07", "c", 140);


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




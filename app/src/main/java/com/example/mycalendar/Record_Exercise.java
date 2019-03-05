package com.example.mycalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Record_Exercise extends AppCompatActivity {

    DatabaseHelper myDb;
    Spinner ExerciseType;
    EditText E_time;
    EditText E_weight;
    Button E_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_exercise);

        ExerciseType = (Spinner) findViewById(R.id.exercise_type_spinner);
        final String[] Etypes = {"    ","Strenuous exercise","Chronic exercise","No additional exercise"};
        ArrayAdapter<String> TypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Etypes);
        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ExerciseType.setAdapter(TypeAdapter);

        E_time = (EditText) findViewById(R.id.Time_Input);
        E_weight = (EditText) findViewById(R.id.Weight_Input);
        E_update = (Button) findViewById(R.id.exercise_record_save);

    }

    public void AddDate(){
        E_update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String weight = E_weight.getText().toString();
                        int finalWeight = Integer.parseInt(weight);
                        String time = E_time.getText().toString();
                        int finalTime =  Integer.parseInt(time);

                        Date date = Calendar.getInstance().getTime();
                        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String s = formatter.format(date);
                        myDb.insertData_exercise(s, ExerciseType.getSelectedItem().toString(),
                                finalTime, finalWeight);
                    }
                }
        );
    }

}


package com.example.mycalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ExerciseFragment extends Fragment {
    ImageButton imageButton;
    Button ExerciseButton;
    Button ViewExerciseHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_exercise, container, false);
        imageButton = view.findViewById(R.id.HomeButt);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home = new Intent(getActivity(),MainActivity.class);
                startActivity(Home);
            }
        });

        View recordsleep =  inflater.inflate(R.layout.fragment_sleep, container, false);
        ExerciseButton = view.findViewById(R.id.exercise_record_button);
        ExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goExerciseRecord = new Intent(getActivity(),Record_Sleep.class);
                startActivity(goExerciseRecord);
            }
        });

        View exercise_history =  inflater.inflate(R.layout.fragment_food, container, false);
        ViewExerciseHistory = view.findViewById(R.id.exercise_history_button);
        ViewExerciseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goExerciseHistory = new Intent(getActivity(),Sleep_History.class);
                startActivity(goExerciseHistory);
            }
        });

        return view;}
}

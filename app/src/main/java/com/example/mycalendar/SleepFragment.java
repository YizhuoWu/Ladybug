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

public class SleepFragment extends Fragment {

    ImageButton imageButton;
    Button SleepButton;
    Button ViewFoodHistory;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sleep, container, false);
        imageButton = view.findViewById(R.id.HomeButt);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home = new Intent(getActivity(),MainActivity.class);
                startActivity(Home);
            }
        });

        View recordsleep =  inflater.inflate(R.layout.fragment_sleep, container, false);
        SleepButton = view.findViewById(R.id.sleep_record_button);
        SleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSleepRecord = new Intent(getActivity(),Record_Sleep.class);
                startActivity(goSleepRecord);
            }
        });

        View sleep_history =  inflater.inflate(R.layout.fragment_food, container, false);
        ViewFoodHistory = view.findViewById(R.id.sleep_history_button);
        ViewFoodHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSleepHistory = new Intent(getActivity(),Sleep_History.class);
                startActivity(goSleepHistory);
            }
        });
        return view;}
}

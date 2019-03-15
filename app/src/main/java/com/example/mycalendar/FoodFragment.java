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

public class FoodFragment extends Fragment {



    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    ImageButton imageButton;
    Button FoodButton;
    Button ViewFoodHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_food, container, false);
        imageButton = view.findViewById(R.id.HomeButt);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home = new Intent(getActivity(),MainActivity.class);
                startActivity(Home);
            }
        });

        View record_food =  inflater.inflate(R.layout.fragment_food, container, false);
        FoodButton = view.findViewById(R.id.food_record_button);
        FoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goFoodRecord = new Intent(getActivity(),Record_Food.class);
                startActivity(goFoodRecord);
            }
        });

        View food_history =  inflater.inflate(R.layout.fragment_food, container, false);
        ViewFoodHistory = view.findViewById(R.id.food_history_button);
        ViewFoodHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goFoodHistory = new Intent(getActivity(),Food_History.class);
                startActivity(goFoodHistory);
            }
        });

        MainActivity activity = (MainActivity) getActivity();
        activity.changeButtonVisibility(false);


        return view;
    }

}





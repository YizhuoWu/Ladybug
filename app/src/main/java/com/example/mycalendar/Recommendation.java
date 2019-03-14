package com.example.mycalendar;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;


public class Recommendation {

    public HashMap<String, String> Build () {
        HashMap<String, String> recommendation_dictionary = new HashMap<String, String>();
        recommendation_dictionary.put("Insufficient sleep", "    If your not pregnant, it would be better to adjust your sleep time to go to bed between 10:30 PM to 11:30PM and get a 8-hours-long high-quality sleep (To better fall asleep, you could drink a cup of hot milk or take a hot bath. If needed, you could also take a moderate amount of melatonin); In this way, your body would start to adjust the new schedule and regulate the menstrual cycles.\n");
        recommendation_dictionary.put("Bad sleep quality", "    To release your pressure, it would be better to try some chronic exercise to relax your body. In addition, taking a hot bath, drinking less water and doing some stretches before sleep would help you to get a better sleep quality. With the increase in sleep qualities, the menstrual cycle would be more regular.\n");
        recommendation_dictionary.put("High fat and high sugar", "     If you are not pregnant, it would be healthier to eat less high fat and high sugar food like burgers and fried chickens since these kinds of food are proved to induce irregular menstrual cycles. It could be hard to quit those junk food, but by watching your weight changes, you could try to set several cheat days per month to allow you to eat a certain amount of high fat and high sugar food.\n" + " Besides that,you could also do some chronic exercise to keep fit and regular metabolism and hormone secretion in order to keep that regular status or your menstrual cycle.\n");
        recommendation_dictionary.put("Low fat and high sugar", "    If you are not pregnant, it would be healthier to take enough food and obtain basic energy to maintain normal body activities and hormone secretion. There is the BMR (basal metabolic rate) based on Harris-Benedict Formula to show you the lowest calories you needed to eat in order to maintain the weight.\n" +
                "     The formulae is for women is BMR = 655 + ( 4.35 x weight in pounds ) + ( 4.7 x height in inches ) - ( 4.7 x age in years )\n" +
                "     In addition, try to avoid too many high-intensity workouts in order to maintain a regular hormone secretion.\n");
        recommendation_dictionary.put("No Breakfast", "     Breakfast is the most important meal in one day, to keep in good health try to get up earlier and have a healthy breakfast like pancakes with milk or eggs with coffee. Eating breakfast would give the body signals to start a brand new day with full energy, regular metabolism and therefore, a regular menstrual cycle.\n");
        recommendation_dictionary.put("Stressful", "     Try to choose a way you enjoy to release your pressure, for example, listen to some light music, talk to friends and families, watch a movie, eat something you like in order to reduce the pressure and obtain a more regular cycle.\n");
        recommendation_dictionary.put("Strenuous exercise", "     Without a pregnancy, it would be better to avoid too many high-intensity workouts every day especially if you are not currently physically active. For instance, you could limit the time for strenuous exercise less than 30 minutes followed by some chronic exercise with a moderate heart rate. Also,  for maintaining health, try to intake more energy from food especially protein after workouts.\n");
        recommendation_dictionary.put("Increased weight", "     It would be better to watch your weight changes regularly and try to eat light food with lower calories but giving a stronger feeling of fullness like potatoes or whole-wheat bread. Also, doing more chronic exercise to keep fit and stay at a normal weight will keep the menstrual cycle regular.\n");
        recommendation_dictionary.put("Reduced weight", "     Set an ideal and healthy goal weight for you to reach when you are trying to lose weight. More importantly, take your time to lose weight gradually. We would present the BMR (basal metabolic rate) based on Harris-Benedict Formula to show you the lowest calories you needed to keep in order to maintain the weight. The leak between the calories you consumed and the calories you eat should be less than 150 in order to make your body to have normal metabolism and hormone secretion.\n");


        return recommendation_dictionary;
        //for (String s: args) {

            //for (Map.Entry<String, String> map : recommendation_dictionary.entrySet()) {
            //if (s.contains(map.getKey())) {

            //Recom += map.getValue();


            //}

                //System.out.println(map.getKey() + " " + map.getValue());
        //}

    }

}

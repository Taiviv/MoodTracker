package com.chartier.virginie.moodtracker.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Virginie Chartier alias Taiviv on 18/06/2018.
 */
public class MoodPreferences {

    //Constants for shared preferences
    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String MOOD_DATA = "MOOD_DATA";



    //This method allow to save array in shared preferences using Gson library
    public static void saveData(Context context, ArrayList<Mood> mMoods) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mMoods);
        editor.putString(MOOD_DATA, json);
        editor.apply();
    }


    //This method allow to load array from shared preferences using Gson library
    public static ArrayList<Mood> loadData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(MOOD_DATA, null);
        Type type = new TypeToken<ArrayList<Mood>>() {
        }.getType();
        ArrayList<Mood> mMoods = gson.fromJson(json, type);
        return mMoods != null ? mMoods : new ArrayList<Mood>();
    }

}

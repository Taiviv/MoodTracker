package com.chartier.virginie.moodtracker.model;

import android.content.Context;
import android.util.Log;

import com.chartier.virginie.moodtracker.helpers.DateHelper;
import com.chartier.virginie.moodtracker.utils.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Virginie Chartier alias Taiviv on 29/05/2018.
 */

public class MoodData {
    /**
     * ---Au swipe de l'utilisateur---
     * enregister une humeur (notre objet Mood aura les variables suivantes de paramétrer à savoir int moodID et Date mDate. la variable commentaire sera égal a null):
     * si aucune humeur existe pour le jour d'aujourd'hui alors j'enregistre normalement mon humeur
     * si une humeur existe pour le jour d'aujourd'hui alors j'enregistre la nouvelle humeur
     *
     * ---A l'enregistrement d'un commentaire---
     * on enregistre une humeur , la date du jour et le commentaire (notre objet Mood aura toutes ces variables paramétrées)
     * si aucune humeur existe pour le jour d'aujourd'hui alors j'enregistre normalement mon humeur
     * si une humeur existe pour le jour d'aujourd'hui alors j'enregistre la nouvelle humeur
     *
     * --Humeur par default--
     * si  l'utilisateur n'a pas enregistré une humeur alors une humeur par default sera enregistré
     */


    // This method save a mood and a comment if the user validate a comment or save a mood without comment after a swipe
    public static void createAndSaveMood(int moodId, String comment, Context context) {
        ArrayList<Mood> savedMoods = MoodPreferences.loadData(context);
        String dateString = Constants.formatter.format(new Date());
        Date date = null;
        try {
            date = Constants.formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("TAG", "Mince, une erreur s'est produite avec le formattage de la date :", e); // TODO : Modification de Philippe
        }
        Log.e("TAG", "l'humeur pour la date "+ dateString +" contient (ou pas) le message "+comment+" et l'id "+moodId);
        if (!savedMoods.isEmpty()) {
            for (Mood mood : savedMoods) {
                if (mood.getmDate().equals(date)){
                    savedMoods.remove(mood);
                }
            }
        }
        savedMoods.add(new Mood(moodId, date, comment));
        MoodPreferences.saveData(context, savedMoods);
    }


    // This method save a default mood if the user has not registered a mood
    public static List<Mood> weekListMood(Context context) {
        ArrayList<Mood> weekList = new ArrayList<>();
        ArrayList<Mood> savedMoods = MoodPreferences.loadData(context);
        for(int i=7; i >= 0; i--) {
            boolean found = false;
            for(Mood mood : savedMoods) {
                if (DateHelper.getDateCalendar(mood.getmDate()).getTime().equals(DateHelper.getDateCalendarWithoutDays(i).getTime())) {
                    weekList.add(mood);
                    found = true;
                }
            }
            if(!found) {
                weekList.add(new Mood(2, DateHelper.getDateCalendarWithoutDays(i).getTime(), ""));
            }
        }
        return weekList;
    }
}

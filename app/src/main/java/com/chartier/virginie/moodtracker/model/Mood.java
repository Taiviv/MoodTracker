package com.chartier.virginie.moodtracker.model;

/**
 * Created by Virginie Chartier alias Taiviv on 29/05/2018.
 */


/* These constructor and methods are the bases of the adapter and the view and the array's data
 * All elements that we stored in the array is used to be showed thanks the recycler view and his adapter
 * We stored all the data which are set from all the items to their content
 * The methods below give the possibility to retrieve all the value needed to be save
 * */
public class Mood {

    private int mColor;
    private int mSmileyValue;
    private String mComment;
    private int mIconComment;
    private String mDate;


    public Mood(int color, int smileyValue, String comment, int iconComment, String date) {
        mColor = color;
        mSmileyValue = smileyValue;
        mComment = comment;
        mIconComment = iconComment;
        mDate = date;
    }


    public int getColor() {
        return mColor;
    }

    public int getSmileyValue() {
        return mSmileyValue;
    }

    public String getComment() {
        return mComment;
    }

    public int getIconComment() {
        return mIconComment;
    }


    /*This method is using a trick to update the "date value"
     * We get the item position into the value and we return the string of this position
     * */
    public String getDate(int value) {
        String dayTab[] = {"Hier", "Avant-hier",
                "trois jours", "quatre jours", "cinq jours", "six jours", "une semaine"};
        mDate = dayTab[value];

        if (value >= 2)
            return "Il y a " + mDate;

        return mDate;
    }
}




package com.chartier.virginie.moodtracker.model;

import java.util.Date;

/**
 * Created by Virginie Chartier alias Taiviv on 29/05/2018.
 */


/* These constructor and methods are the bases of the adapter and the view and the array's data
 * All elements that we stored in the array is used to be showed thanks the recycler view and his adapter
 * We stored all the data which are set from all the items to their content
 * The methods below give the possibility to retrieve all the value needed to be save
 * */
public class Mood {

    public static int ID_MOOD_HAPPY = 0;
    public static int ID_MOOD_SUPER_HAPPY = 1;
    public static int ID_MOOD_NORMAL = 2;
    public static int ID_MOOD_SAD = 3;
    public static int ID_MOOD_SUPER_SAD = 4;


    private int moodId;
    private Date mDate;
    private String mComment;



    public Mood(int moodId, Date mDate, String mComment) {
        this.moodId = moodId;
        this.mDate = mDate;
        this.mComment = mComment;

    }


    public int getMoodId() {
        return moodId;
    }


    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }


    public Date getmDate () {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }


    public String getmComment() {
        return mComment;
    }


    public void setmComment(String mComment) {
        this.mComment = mComment;
    }
}




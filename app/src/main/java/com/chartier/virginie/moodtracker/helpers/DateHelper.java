package com.chartier.virginie.moodtracker.helpers;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Virginie Chartier alias Taiviv on 19/07/2018.
 */


//This class groups methods of date manipulation
public class DateHelper {

    /* These two methods calculate the differences of days: finally there is only the first one which interests us the second
     * serves just to the separation of the code, one creates a method to which one passes 2 dates and
     * which returns us an integer corresponding to the numbers days between these two dates.
     */
    public static int daysBetween(Date startDate, Date endDate) {
        Calendar sDate = getDateCalendar(startDate);
        Calendar eDate = getDateCalendar(endDate);

        int daysBetween = 0;
        while (sDate.before(eDate)) {
            sDate.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    private static Calendar getDateCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
}

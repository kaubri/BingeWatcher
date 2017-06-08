package com.example.mikaila.bingewatcher;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dwilder1181 on 6/5/2017.
 */

public class TimeUtility {
    private int mDays;
    private int mHours;
    private int mMins;


    public static String converDateToFormatedString(Date date) {


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        return dateFormat.format(date);
    }
    public int getDays() {
        return mDays;
    }

    public void setDays(int days) {
        mDays = days;
    }
    //TODO Create a unit test for this class


    public static int hoursToMinutes(int hours){
        return hours * 60;
    }

    public static int daystToMinutes(int days) {
        return (days * 24 * 60);
    }


    public static String minsToBiggestUnitString(int minutes) {
        int hours= minutes/60;
        int remainingMins= minutes%60;
        int remainingHours= hours%24;
        int days = hours / 24;

        StringBuilder timeString = new StringBuilder();

        //TODO check to see if these work I made those ternary changes
        timeString.append(days + " days ");
        timeString.append(remainingHours + " hrs ");
        timeString.append(remainingMins + " mins ");

        return timeString.toString();
    }

    public static int millisecsToMins(long millisecs) {
        long secs= millisecs/1000L;
        return  (int)secs/60 ;

    }

    public static int getMins(int days, int hours, int mins) {
        int time=(1440 * days);
        time += (60 * hours);
        time+=mins;

        return time;
    }
    public static int getTotalTime(int numberOfEpisodes, int averageLenght) {
        return numberOfEpisodes* averageLenght;
    }
    public static int getMinsFromDates(Date startDate, Date endDate) {
        int minsBetweenDate = TimeUtility.millisecsToMins(endDate.getTime() - startDate.getTime());
        return minsBetweenDate;
    }
}

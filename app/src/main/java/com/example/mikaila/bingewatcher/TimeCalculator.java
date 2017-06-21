package com.example.mikaila.bingewatcher;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by dwilder1181 on 6/5/2017.
 */

public class TimeCalculator {

    /**
     * Returns the date you will complete a series base on how many episodes you watch in a day or week.
     */
    public static Date getFrequencyEndDate(Anime series, Date startDate, String frequency, int episodesWatched) {
        frequency = frequency.toLowerCase();
        int totalEpisodes = Integer.valueOf(series.get_total_episodes());
        GregorianCalendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);

        switch (frequency) {
            case "day":
                while (totalEpisodes > 0) {
                    totalEpisodes -= episodesWatched;
                    startCalendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                break;


            case "week":
                while (totalEpisodes > 0) {
                    totalEpisodes -= episodesWatched;
                    startCalendar.add(Calendar.WEEK_OF_MONTH, 1);
                }
                break;
            default:
                return null;
        }
        return startCalendar.getTime();

    }

}
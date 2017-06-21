package com.example.mikaila.otakubinge;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by dwilder1181 on 4/12/2017.
 * There are a couple of ways that you can dedduct te time
 */

public class BingeCalculator {
    /**
     * Returns the date you will complete a series base on how many episodes you watch in a day or week.
     */
    public static Date getFrequencyEndDate(Anime anime, Date startDate, String frequency, int episodesWatched, Context packageContext) {
        frequency = frequency.toLowerCase();
        int totalEpisodes= Integer.parseInt(anime.get_total_episodes());
        GregorianCalendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);

        if (frequency.equals("day")) {
            while (totalEpisodes > 0) {
                totalEpisodes-=episodesWatched;
                startCalendar.add(Calendar.DAY_OF_MONTH,1);
            }
        }

        if (frequency.equals("week")) {
            while (totalEpisodes > 0) {
                totalEpisodes-=episodesWatched;
                startCalendar.add(Calendar.WEEK_OF_MONTH,1);
            }
        }
        return  startCalendar.getTime();

    }
}

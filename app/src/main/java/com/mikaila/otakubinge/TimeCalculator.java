package com.mikaila.otakubinge;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * <h1>BingeCalculator</h1>
 * Calculates raw binge time and completion date
 *
 * @author  Donovan Wilder (dwilder1181)
 * @version 1.0
 * @since   2017-06-05
 */
public class TimeCalculator {

    /**
     * Returns the date you will complete a series based on how many episodes you watch in a day or week.
     * @param series Anime object with show details
     * @param startDate new Date object containing current date
     * @param frequency Watch frequency
     * @param episodesPerFreq Number of episodes to watch per {frequency}
     * @return Date that show will be completed
     */
    public static Date getFrequencyEndDate(Anime series, Date startDate, String frequency, int episodesPerFreq) {
        int totalEpisodes = Integer.valueOf(series.get_total_episodes());
        GregorianCalendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);

        switch (frequency.toLowerCase()) {
            case "day":
                startCalendar.add(Calendar.DAY_OF_YEAR, (int)Math.ceil(totalEpisodes/episodesPerFreq));
                break;
            case "week":
                startCalendar.add(Calendar.WEEK_OF_MONTH, (int)Math.ceil(totalEpisodes/episodesPerFreq));
                break;
            default:
                return null;
        }
        return startCalendar.getTime();

    }

    /**
     * Calculates the raw binge time of a series
     * @param minutes Total number of minutes in anime series
     * @return Raw binge time in days, hours, and minutes
     */
    public static String calculateRawBingeTime(int minutes) {
        int hours = minutes/60;
        int days = hours/24;
        int remainingHours = hours%24;
        int remainingMins = minutes%60;

        StringBuilder timeString = new StringBuilder();
        timeString.append(days + " days ");
        timeString.append(remainingHours + " hrs ");
        timeString.append(remainingMins + " mins ");

        return timeString.toString();
    }

    /**
     * Converts completion date to formatted string
     * @param date Completion date
     * @returns Date converted to MM/dd/yyyy format
     */
    public static String converDateToFormatedString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        return dateFormat.format(date);
    }
}
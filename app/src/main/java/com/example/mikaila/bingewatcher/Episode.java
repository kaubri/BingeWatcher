package com.example.mikaila.bingewatcher;

import java.util.Date;

/**
 * A model for the data of an episode.
 *
 * @author Donovan J. Wilder
 * @version 1.0
 * @since 2017-04-09
 */

public class Episode {

    private String mTitle;
    private String mSynopsis;
    private int mEpisodeNumber;
    private int mSeasonNumber;
    private int mSeasonEpisodeNumber;
    private Date mOriginalAirDate;
    private Date mEnglishAirDate;

    public Episode() {

    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public void setSynopsis(String synopsis) {
        mSynopsis = synopsis;
    }

    public int getEpisodeNumber() {
        return mEpisodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        mEpisodeNumber = episodeNumber;
    }

    public int getSeasonNumber() {
        return mSeasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        mSeasonNumber = seasonNumber;
    }

    public int getSeasonEpisodeNumber() {
        return mSeasonEpisodeNumber;
    }

    public void setSeasonEpisodeNumber(int seasonEpisodeNumber) {
        mSeasonEpisodeNumber = seasonEpisodeNumber;
    }

    public Date getOriginalAirDate() {
        return mOriginalAirDate;
    }

    public void setOriginalAirDate(Date originalAirDate) {
        mOriginalAirDate = originalAirDate;
    }

    public Date getEnglishAirDate() {
        return mEnglishAirDate;
    }

    public void setEnglishAirDate(Date englishAirDate) {
        mEnglishAirDate = englishAirDate;
    }

    public Episode(String title,
                   String synopsis,
                   int episodeNumber,
                   int seasonNumber,
                   int seasonEpisodeNumber,
                   Date originalAirDate,
                   Date englishAirDate
    ) {
        setTitle(title);
        setSynopsis(synopsis);
        setEpisodeNumber(episodeNumber);
        setSeasonNumber(seasonNumber);
        setSeasonEpisodeNumber(seasonEpisodeNumber);
        setOriginalAirDate(originalAirDate);
        setEnglishAirDate(englishAirDate);
    }
}

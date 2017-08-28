package com.mikaila.otakubinge;

import java.io.Serializable;

/**
 * <h1>Anime</h1>
 * Anime contains pertinent show information returned from AniListAPI search result
 *
 * @author  Mikaila Smith
 * @version 1.0
 * @since   2017-05-16
 */
public class Anime implements Serializable{
    private String title_romaji;
    private String title_english;
    private String description;
    private String image_url_lge;
    private String total_episodes;
    private String duration;

    /**
     * Getter for anime show title in romaji
     * @return Romaji show title
     */
    public String get_title_romaji() { return title_romaji; }

    /**
     * Getter for anime's show title in english
     * @return English show title
     */
    public String get_title_english() { return title_english; }

    /**
     * Getter for anime's description
     * @return Description of show
     */
    public String get_description() { return description; }

    /**
     * Getter for image of anime
     * @return URL to show image
     */
    public String get_image_url() { return image_url_lge; }

    /**
     * Getter for total number of episodes
     * @return Total number of episodes
     */
    public String get_total_episodes() { return total_episodes; }

    /**
     * Getter for anime's episode duration
     * @return Duration in minutes
     */
    public String get_duration() { return duration; }

    /**
     * Setter for romaji title
     * @param title Romaji
     */
    public void set_title_romaji(String title) { title_romaji = title; }

    /**
     * Setter for romaji title
     * @param title Romaji
     */
    public void set_title_english(String title) { title_english = title; }

    /**
     * Setter for anime show's description
     * @param desc Description
     */
    public void set_description(String desc) { description = desc; }

    /**
     * Setter for anime image URL
     * @param url Image URL
     */
    public void set_image_url(String url) { image_url_lge = url; }

    /**
     * Setter for total number of episodes
     * @param episodes Number of episodes
     */
    public void set_total_episodes(String episodes) { total_episodes = episodes; }

    /**
     * Setter for episode duration
     * @param runtime Episode duration in minutes
     */
    public void set_duration(String runtime) { duration = runtime; }
}

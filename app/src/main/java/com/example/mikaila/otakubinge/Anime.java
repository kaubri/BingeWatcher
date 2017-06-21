package com.example.mikaila.otakubinge;

import java.io.Serializable;

/**
 * Created by Mikaila on 4/27/2017.
 */

public class Anime implements Serializable{
    String title_romaji;
    String title_english;
    String description;
    String image_url_lge;
    String total_episodes;
    String duration;

    public String get_title_romaji() { return title_romaji; }
    public String get_title_english() { return title_english; }
    public String get_description() { return description; }
    public String get_image_url() { return image_url_lge; }
    public String get_total_episodes() { return total_episodes; }
    public String get_duration() { return duration; }

    public void set_title_romaji(String title) { title_romaji = title; }
    public void set_title_english(String title) { title_english = title; }
    public void set_description(String desc) { description = desc; }
    public void set_image_url(String url) { image_url_lge = url; }
    public void set_total_episodes(String episodes) { total_episodes = episodes; }
    public void set_duration(String runtime) { duration = runtime; }
}

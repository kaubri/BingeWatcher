package com.example.mikaila.bingewatcher;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mikaila on 4/23/2017.
 */

public class SearchAnime {
    Context context;
    AniListAPI aniList = new AniListAPI();

    public SearchAnime(Context c){
        context = c;
    }

    /* Performs search using user-provided query. Generates access token is needed.*/
    public List<Anime> search(String query) throws JSONException {
        if (!isTokenValid()) {
            aniList.generateAccessToken(context.getResources().getString(R.string.api_auth_url), context.getResources().getString(R.string.client_id),
                    context.getResources().getString(R.string.client_secret));
        }

        String results = aniList.searchAnime(context.getResources().getString(R.string.api_search_url), query);

        JSONArray results_array = new JSONArray(results);
        List<Anime> animeCollection = getAnimeCollection(results_array);

        return animeCollection;
    }

    /* Convert JSONArray of search results to a collection of Anime objects. */
    public List<Anime> getAnimeCollection(JSONArray array) throws JSONException {
        List<Anime> animeCollection = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            Anime anime = new Anime();

            JSONObject jsonObj  = array.getJSONObject(i);
            anime.set_title_romaji(jsonObj.getString("title_romaji"));
            anime.set_title_english(jsonObj.getString("title_english"));
            anime.set_description(jsonObj.getString("description"));
            anime.set_image_url(jsonObj.getString("image_url_lge"));
            anime.set_total_episodes(jsonObj.getString("total_episodes"));
            anime.set_duration(jsonObj.getString("duration"));

            animeCollection.add(anime);
        }

        return animeCollection;
    }

    /* Check is current access token is still valid. Returns false if there is no token found or if
     the token has expired */
    public boolean isTokenValid() {
        long timeMillis = System.currentTimeMillis();
        long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis);

        if (aniList.getAccessToken() != null) {
            return (timeSeconds < Long.valueOf(aniList.getTokenExpires()));
        }
        else {
            return false;
        }
    }
}

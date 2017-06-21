package com.example.mikaila.otakubinge;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mikaila on 4/15/2017.
 */

public class AniListAPI {
    private String accessToken = null;
    private String tokenExpiration = null;

    /**
     * Getter to retrieve access token
     *
     * @return Access token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Getter to retrieve token expiration time/date
     *
     * @return Token expiration time/date
     */
    public String getTokenExpires() {
        return tokenExpiration;
    }

    /**
     * Generates an access token (valid for 1 hour) for user by authenticating with application's
     * client id and secret.
     *
     * @param url URL to request access token for AniList API
     * @param id Client ID
     * @param secret Client secret
     * @return void
     */
    public void generateAccessToken(String url, String id, String secret) {
        String token = null;
        try {
            token = new RenewAccessToken().execute(url, id, secret).get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Parse return string to get access token and its time of expiration in seconds
        accessToken = token.split(":")[1].split(",")[0];
        accessToken = accessToken.substring(1, accessToken.length()-1);

        tokenExpiration = token.split(":")[4];
        tokenExpiration = tokenExpiration.substring(0, tokenExpiration.length()-1);
    }

    /**
     * Performs background search for anime
     *
     * @param url URL to search anime via AniList API
     * @param query User input search query
     * @return Anime search results as a string in JSON format
     */
    public String searchAnime(String url, String query) {
        String result = null;
        try {
            result = new GetSearchResult().execute(url, query).get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Makes a POST api call to request a new access token for authentication. It
     * performs this task in the background as an AsyncTask.
     *
     * @author Mikaila Smith
     */
    private class RenewAccessToken extends AsyncTask<String, Void, String> {
        /**
         * Requests a new access token using client id and secret for authentication
         *
         * @param params URL to authenticate, client id, and client secret
         * @return New access token
         */
        @Override
        protected String doInBackground(String... params) {
            String line;
            String result = "";

            try {
                URL url = new URL(params[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("User-Agent", "dev_client");

                OutputStreamWriter rw = new OutputStreamWriter(conn.getOutputStream());
                rw.write("grant_type=client_credentials&client_id=" + params[1] + "&client_secret=" + params[2]);
                rw.flush();

                BufferedReader rr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = rr.readLine()) != null) {
                    result += line;
                }
                rr.close();
                rw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }
    }

    /**
     * Makes a GET api call to perform a search result for matching anime.
     *
     * @author Mikaila Smith
     */
    private class GetSearchResult extends AsyncTask<String, Void, String> {
        /**
         * Performs background search for anime
         *
         * @param params URL to search anime via AniList API and user input query
         * @return Anime search results as a string in JSON format
         */
        @Override
        protected String doInBackground(String... params) {
            String data = "";
            String result = "";

            try {
                URL url = new URL(params[0] + params[1] + "?access_token=" + accessToken);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                // Read the input stream into a String
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                while ((data = reader.readLine()) != null){
                    result += data + "\n";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }
    }

}
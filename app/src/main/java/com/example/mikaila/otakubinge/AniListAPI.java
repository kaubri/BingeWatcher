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
    private String access_token = null;
    private String token_expires = null;

    public String getAccessToken() {
        return access_token;
    }

    public String getTokenExpires() {
        return token_expires;
    }

    public void generateAccessToken(String url, String id, String secret) {
        String token = null;
        try {
            token = new RenewAccessToken().execute(url, id, secret).get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Parse return string to get access token and its time of expiration in seconds
        access_token = token.split(":")[1].split(",")[0];
        access_token = access_token.substring(1, access_token.length()-1);

        token_expires = token.split(":")[4];
        token_expires = token_expires.substring(0, token_expires.length()-1);
    }

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

    private class RenewAccessToken extends AsyncTask<String, Void, String> {
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

    private class GetSearchResult extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String data = "";
            String result = "";

            try {
                URL url = new URL(params[0] + params[1] + "?access_token=" + access_token);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //conn.setDoOutput(true);
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
package com.example.mikaila.bingewatcher;

import javax.json.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dwilder1181 on 4/27/2017.
 */
public class WikipediaSeriesGrabber   {
    private String mJsonString;
    private String mTitle;
    private String mSynopsis;
    private int mNumberOfEpisodes;
    private int mTotalTime;

    public void setTotalTime(int totalTime) {
        mTotalTime = totalTime;
    }

    private List<Episode> mEpisodeList;
    private int mNumberOfSeasons;
    private Date mStartDate;
    private Date mEndDate;
    private int mAverageEpisodeLength;


    public String getTitle() {
        return mTitle;
    }

    public int getNumberOfEpisodes() {
        return mNumberOfEpisodes;
    }

    public static void main(String args[]) throws IOException, Exception {
        WikipediaSeriesGrabber seriesGrabber = new WikipediaSeriesGrabber("empire");
        System.out.println("The title is: " + seriesGrabber.getTitle());
        System.out.println("The number of episodes is: " + seriesGrabber.getNumberOfEpisodes());

    }

    public WikipediaSeriesGrabber(String seriesName) throws IOException,Exception {
        if(!findSeries(seriesName))
            throw new Exception("No series found");
    }

    private boolean getItems(String seriesTitle) throws IOException {
        String uriString = "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&rvprop=content&format=xmlfm&titles="+seriesTitle+"&rvsection=0&format=json";
        String jsonString = getUrlString(uriString);
        String pageIdString;
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        Pattern pageid = Pattern.compile("\"(\\d+)\"");
        Pattern title = Pattern.compile("title\\s*\\=\\s*(.*)\\s");
        Pattern episodes = Pattern.compile("episodes \\s*\\=\\s*(\\d*)");
        Pattern synopsis = Pattern.compile("");
        Pattern runtime = Pattern.compile("");
        Matcher matcher = pageid.matcher(jsonString);
        matcher.find();
        pageIdString = matcher.group(1);

        String infoText = jsonObject.getJsonObject("query")
                .getJsonObject("pages")
                .getJsonObject(pageIdString)
                .getJsonArray("revisions")
                .toString();
        mTitle= jsonObject.getJsonObject("query")
                .getJsonObject("pages")
                .getJsonObject(pageIdString)
                .getString("title");

        matcher = episodes.matcher(infoText);
        if (!matcher.find())
            return false;
        mNumberOfEpisodes=Integer.valueOf(matcher.group(1));


        return true;


    }
    public static String replaceSpaces(String string) {
        string.replace(' ', '_');
        return string;
    }
    public boolean findSeries(String seriesName) throws IOException {
        List<String> titles = getOpenSearchTitlesJsonString(seriesName);

        for (String title : titles) {
            if(getItems(title)){
                return true;
            }
        }
        return false;
    }
    private List<String> getOpenSearchTitlesJsonString(String searchString) {
        String jsonURI = "https://en.wikipedia.org/w/api.php?action=opensearch&search=" + searchString.replaceAll("\\s","_") + "&format=json";
        String jsonString=null;
        try {
          jsonString = getUrlString(jsonURI);
        } catch (IOException e) {
            e.printStackTrace();
        }


        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonArray jsonArray=jsonReader.readArray();
        jsonReader.close();

        JsonArray possibleUrls = jsonArray.getJsonArray(3);

        String patternString = "\\/\\/.*org\\/wiki\\/(.*)\"";
        Pattern pattern = Pattern.compile(patternString);
        List<String> titleList = new ArrayList<>();

        for (JsonValue value : possibleUrls) {
            Matcher matcher =pattern.matcher(value.toString());
            matcher.find();
            titleList.add(matcher.group(1));
        }


        return titleList;
    }


    private byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }
}

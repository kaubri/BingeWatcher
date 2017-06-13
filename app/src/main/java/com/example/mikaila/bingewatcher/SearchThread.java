package com.example.mikaila.bingewatcher;

import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by dwilder1181 on 5/29/2017.
 */

public class SearchThread implements Runnable {
    private String mSeriesQuery;
    private int mNumberOfEpisodes;
    private String mTitle;
    private String mSynopsis;

    public SearchThread(String seriesQuery) {
        mSeriesQuery=seriesQuery;
    }

    @Override
    public void run() {
//        try {
//            WikipediaSeriesGrabber grabber = new WikipediaSeriesGrabber(mSeriesQuery);
//            mNumberOfEpisodes=grabber.getNumberOfEpisodes();
//            mTitle= grabber.getTitle();
//            mOnInformationRecievedListener.onInformationRecieved(mNumberOfEpisodes, mTitle,  mSynopsis);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    private OnInformationRecievedListener mOnInformationRecievedListener;

    public void setOnInformationRecievedListener(OnInformationRecievedListener onInformationRecievedListener) {
        mOnInformationRecievedListener = onInformationRecievedListener;
    }

    interface OnInformationRecievedListener {
        public void onInformationRecieved(int mNumberOfEpisodes, String mTitle, @Nullable String mSynopsis);
    }
}

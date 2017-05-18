package com.example.mikaila.bingewatcher;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by dwilder1181 on 5/18/2017.
 */

public class ResultActivity extends AppCompatActivity {
    private TextView mEpisodesTextView;
    private TextView mRuntimeTextView;
    private TextView mSynopsisTextView;
    private TextView mRawBingeTimeTextView;
    private EditText mNumberOfEpisodesEditText;
    private Spinner mFrequencySpinner;
    private Button mCalculateButton;
    private TextView mCompletionDateTextView;
    private TextView mTitleTextView;
    private ImageView mImageView;


    //TODO test to ResultActivity to make sure the view are compiled correctly
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mEpisodesTextView = (TextView) findViewById(R.id.results_episodes_text_view);
        mRuntimeTextView = (TextView) findViewById(R.id.result_runtime_text_view);
        mSynopsisTextView = (TextView) findViewById(R.id.result_synopsis_text_view);
        mRawBingeTimeTextView = (TextView) findViewById(R.id.result_raw_binge_time_text_view);
        mNumberOfEpisodesEditText = (EditText) findViewById(R.id.result_number_of_episodes_edit_text);
        mFrequencySpinner = (Spinner) findViewById(R.id.result_frequency_spinner);
        mCalculateButton = (Button) findViewById(R.id.result_calculate_button);
        mCompletionDateTextView = (TextView) findViewById(R.id.result_completion_date_text_view);
        mTitleTextView = (TextView) findViewById(R.id.result_title_text_view);
        mImageView = (ImageView) findViewById(R.id.result_image_view);

    }

}

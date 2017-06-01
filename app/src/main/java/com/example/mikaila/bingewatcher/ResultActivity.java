package com.example.mikaila.bingewatcher;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private ImageView img;
    private TextView title;
    private TextView episodes;
    private TextView duration;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Anime anime = (Anime) intent.getSerializableExtra("anime");

        img = (ImageView) findViewById(R.id.result_image_view);
        title = (TextView) findViewById(R.id.result_title_text_view);
        episodes = (TextView) findViewById(R.id.results_episodes_text_view);
        duration = (TextView) findViewById(R.id.result_runtime_text_view);
        description = (TextView) findViewById(R.id.result_synopsis_text_view);

        new LoadImageFromUrl(img).execute(anime.get_image_url());
        title.setText(anime.get_title_english());
        episodes.setText(anime.get_total_episodes());
        duration.setText(anime.get_duration() + " mins");
        description.setText(anime.get_description());
    }
}

package com.example.mikaila.bingewatcher;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;

/**
 * Created by dwilder1181 on 5/29/2017.
 */

public class WikipediaSearchActivity extends AppCompatActivity {
    private SearchView mSearchView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_wikipedia_search);
        mSearchView = (SearchView) findViewById(R.id.search_view);
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery= mSearchView.getQuery().toString();
            }
        });
    }
}

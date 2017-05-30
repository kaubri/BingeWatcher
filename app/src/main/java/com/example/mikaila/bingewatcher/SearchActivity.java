package com.example.mikaila.bingewatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONException;

public class SearchActivity extends AppCompatActivity {

    // Create api object to interact with AniList api
    //AniListAPI api;
    SearchAnime searchAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wikipedia_search);

        // Enable logo to show in action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //api = new AniListAPI();
        searchAnime = new SearchAnime(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Makes search option visible in action bar upon options menu creation
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem item = menu.findItem(R.id.search);

        // Implement listener for user's search
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    Toast.makeText(getApplicationContext(), "Searching for " + query + "...", Toast.LENGTH_LONG).show();

                    try {
                        searchAnime.search(query);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

}

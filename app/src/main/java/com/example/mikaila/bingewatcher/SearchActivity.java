package com.example.mikaila.bingewatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import org.json.JSONException;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    // Create api object to interact with AniList api
    SearchAnime searchAnime;

    // To display search results
    RecyclerView recyclerView;
    SearchAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Anime> anime_results_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Enable logo to show in action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Prepare recycler view for showing search results
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

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
                    // Clear search results
                    recyclerView.removeAllViewsInLayout();

                    try {
                        // Search for anime based on query
                        List<Anime> anime_results_list = searchAnime.search(query);

                        // Show search results
                        adapter = new SearchAdapter(getApplicationContext(), anime_results_list);
                        recyclerView.setAdapter(adapter);
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

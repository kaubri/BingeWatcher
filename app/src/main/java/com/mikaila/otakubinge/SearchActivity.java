package com.mikaila.otakubinge;

import android.net.ConnectivityManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>SearchActivity</h1>
 * Main activity when app is launched.
 *
 * @author  Mikaila Smith
 * @version 1.0
 * @since   2017-04-07
 */
public class SearchActivity extends AppCompatActivity {

    // Create api object to interact with AniList api
    SearchAnime searchAnime;

    // To display search results
    RecyclerView recyclerView;
    SearchAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    // Search results - list of animes
    List<Anime> animeResultsList;

    /**
     * Executes when activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Enable logo to show in action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_otakubinge_tv);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Prepare recycler view for showing search results
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(SearchActivity.this.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null) { // If user has an internet connection
            searchAnime = new SearchAnime(this);
        }
        else {
            Toast.makeText(getApplicationContext(), "Internet connected required. Restart app after connection.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Executes once to create menu with searchView
     * @param menu Menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Makes search option visible in action bar upon options menu creation
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem item = menu.findItem(R.id.search);

        // For use by searchView's listeners - to trigger visibility
        final ImageView logo = (ImageView) findViewById(R.id.imgLogo);
        final TextView devInfo = (TextView) findViewById(R.id.txtDevelopment);
        final TextView copyrightInfo = (TextView) findViewById(R.id.txtCopyright);

        // Implement listener for user's search
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    // Clear search results
                    recyclerView.removeAllViewsInLayout();

                    try {
                        // Only perform search if device has internet access
                        ConnectivityManager cm = (ConnectivityManager) getSystemService(SearchActivity.this.CONNECTIVITY_SERVICE);

                        if (cm.getActiveNetworkInfo() != null) { // If user has an internet connection
                            // searchAnime will be null if user did not start app with internet connection
                            if (searchAnime == null) {
                                Toast.makeText(getApplicationContext(), "Please restart application.", Toast.LENGTH_LONG).show();
                                return false;
                            }

                            // Search for anime based on query
                            animeResultsList = searchAnime.search(query);

                            // Show search results
                            logo.setVisibility(View.INVISIBLE);
                            devInfo.setVisibility(View.INVISIBLE);
                            copyrightInfo.setVisibility(View.INVISIBLE);

                            if (animeResultsList.size() == 0) {
                                Toast.makeText(getApplicationContext(), "No anime found named " + query, Toast.LENGTH_SHORT).show();
                            }

                            adapter = new SearchAdapter(SearchActivity.this, animeResultsList);
                            recyclerView.setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Make sure you're connected to the internet!", Toast.LENGTH_LONG).show();
                            revertSearchActivityLayout(logo,devInfo,copyrightInfo);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("SearchActivity","JSON exception while parsing API results");
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerView.removeAllViewsInLayout();
                recyclerView.invalidate();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(SearchActivity.this.CONNECTIVITY_SERVICE);
                if (cm.getActiveNetworkInfo() == null) { // If user has an internet connection
                    revertSearchActivityLayout(logo,devInfo,copyrightInfo);
                }

                return false;
            }
        });

        // When user closes the search view, return to original view and erase search results
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                setSearchResultLayout(logo,devInfo,copyrightInfo);

                // Anytime search is opened, clear and empty adapter to prevent layout issue
                // where the logo and info and old search results overlap
                searchView.setQuery("", true);
                adapter = new SearchAdapter(SearchActivity.this, new ArrayList<Anime>());
                recyclerView.setAdapter(adapter);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                revertSearchActivityLayout(logo,devInfo,copyrightInfo);
                return true;
            }
        });

        return true;
    }

    /**
     * Clears recycler view and sets copyright/dev info and logo visible
     * @param logo Image view
     * @param dev Text view
     * @param copyright Text view
     */
    private void revertSearchActivityLayout(ImageView logo, TextView dev, TextView copyright) {
        // Clear search results
        recyclerView.removeAllViewsInLayout();
        recyclerView.invalidate();

        // Show main activity logo and dev/copyright info
        logo.setVisibility(View.VISIBLE);
        dev.setVisibility(View.VISIBLE);
        copyright.setVisibility(View.VISIBLE);
    }

    /**
     * Clears recycler view and sets copyright/dev info and logo invisible
     * @param logo Image view
     * @param dev Text view
     * @param copyright Text view
     */
    private void setSearchResultLayout(ImageView logo, TextView dev, TextView copyright) {
        // Clear search results
        recyclerView.removeAllViewsInLayout();
        recyclerView.invalidate();

        // Show main activity logo and dev/copyright info
        logo.setVisibility(View.INVISIBLE);
        dev.setVisibility(View.INVISIBLE);
        copyright.setVisibility(View.INVISIBLE);
    }
}

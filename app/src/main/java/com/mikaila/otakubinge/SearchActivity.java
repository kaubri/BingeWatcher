package com.mikaila.otakubinge;

import android.net.ConnectivityManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikaila.otakubinge.R;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.List;

/**
 * The HelloWorld program implements an application that
 * simply displays "Hello World!" to the standard output.
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

        searchAnime = new SearchAnime(this);
    }

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
                        // Only perform search if device has internet access
                        ConnectivityManager cm = (ConnectivityManager) getSystemService(SearchActivity.this.CONNECTIVITY_SERVICE);

                        if (cm.getActiveNetworkInfo() != null) { // If user has an internet connection
                            // Search for anime based on query
                            animeResultsList = searchAnime.search(query);

                            // Show search results
                            logo.setVisibility(View.INVISIBLE);
                            devInfo.setVisibility(View.INVISIBLE);
                            copyrightInfo.setVisibility(View.INVISIBLE);

                            adapter = new SearchAdapter(SearchActivity.this, animeResultsList);
                            recyclerView.setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Make sure you're connected to the internet!", Toast.LENGTH_LONG).show();
                        }
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

        // When user closes the search view, return to original view and erase search results
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Clear search results
                recyclerView.removeAllViewsInLayout();
                recyclerView.invalidate();

                // Show main activity logo and dev/copyright info
                logo.setVisibility(View.VISIBLE);
                devInfo.setVisibility(View.VISIBLE);
                copyrightInfo.setVisibility(View.VISIBLE);

                return true;
            }
        });

        return true;
    }
}

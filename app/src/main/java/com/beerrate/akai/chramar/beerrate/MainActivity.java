package com.beerrate.akai.chramar.beerrate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.beerrate.akai.chramar.beerrate.connector.ConnectorFacade;
import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String MY_LOG = "Kossa";
    public static final String PREF = "BeerPreferences";
    public static final String BEERS_NUM = "BeersNumber";
    public static final String FAVORITE_BEER = "FB";

    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RecyclerView mainActivityRecyclerView;
    private BestBeerRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Beer> theBest;
    public static ArrayList<String> favoriteBeers;
    private ActionBar actionBar;
    private ConnectorFacade connectorFacade;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(PREF, Context.MODE_PRIVATE);
        editor = preferences.edit();


        connectorFacade = new ConnectorFacade();
        theBest = connectorFacade.getAll();

        favoriteBeers = new ArrayList<>();
        int fn = preferences.getInt(BEERS_NUM, 0);

        favoriteBeers.clear();
        for (int i = 0; i < fn; i++) {
            String name = preferences.getString(FAVORITE_BEER + i, "");
            if (!name.contentEquals("")) {
                favoriteBeers.add(name);
            }
        }

        Log.d(MY_LOG, 79 + "");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setTitle("The best beers");

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add BeerActivity", Snackbar.LENGTH_LONG).show();
            }
        });

        Log.d(MY_LOG, 95 + "");

        mainActivityRecyclerView = findViewById(R.id.bestBeer_recyclerView);
        adapter = new BestBeerRecyclerViewAdapter(theBest, favoriteBeers,
                getApplicationContext().getResources().getDisplayMetrics().density);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        mainActivityRecyclerView.setLayoutManager(layoutManager);
        mainActivityRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Log.d(MY_LOG, 112 + "");
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Log.d(MY_LOG, 115 + "");
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_allBeers) {
            Intent recycler = new Intent(this, ListActivity.class);
            startActivity(recycler);
        } else if(id ==  R.id.nav_favoriteBeers) {
            //Otwieranie Activity z ulubionymi piwami
            Toast.makeText(getApplicationContext(), "Favorite Beers", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        favoriteBeers.clear();
        int fn = preferences.getInt(BEERS_NUM, 0);

        for (int i = 0; i < fn; i++) {
            String name = preferences.getString(FAVORITE_BEER + i, "");
            if (!name.contentEquals("")) {
                favoriteBeers.add(name);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < favoriteBeers.size(); i++) {
            editor.putString(FAVORITE_BEER + i, favoriteBeers.get(i));
        }
        Log.d(MY_LOG, "Fb length = " + favoriteBeers.size());
        editor.putInt(BEERS_NUM, favoriteBeers.size());
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (int i = 0; i < favoriteBeers.size(); i++) {
            editor.putString(FAVORITE_BEER + i, favoriteBeers.get(i));
        }
        Log.d(MY_LOG, "Fb length = " + favoriteBeers.size());
        editor.putInt(BEERS_NUM, favoriteBeers.size());
        editor.commit();
    }
}

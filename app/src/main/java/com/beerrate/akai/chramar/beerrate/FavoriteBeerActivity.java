package com.beerrate.akai.chramar.beerrate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.beerrate.akai.chramar.beerrate.Adapters.FavoriteBeerRecyclerViewAdapter;
import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import static com.beerrate.akai.chramar.beerrate.Constants.ID;
import static com.beerrate.akai.chramar.beerrate.Constants.NAME;
import static com.beerrate.akai.chramar.beerrate.Constants.PREF;

public class FavoriteBeerActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private RecyclerView favoriteRecyclerView;
    private FavoriteBeerRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_beer);
        Toolbar toolbar = findViewById(R.id.app_bar_favorite);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_favorite);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add BeerActivity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        preferences = getSharedPreferences(PREF, Context.MODE_PRIVATE);
        editor = preferences.edit();


        favoriteRecyclerView = findViewById(R.id.favoriteBeer_recyclerView);
        adapter = new FavoriteBeerRecyclerViewAdapter(this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        favoriteRecyclerView.setLayoutManager(layoutManager);
        favoriteRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    public void startDataActivity(int position) {
        Beer beer = MainActivity.favoriteBeers.get(position);

        Bundle b = new Bundle();
        b.putString(NAME, beer.getName());
        b.putLong(ID, beer.getId());
        /*Toast.makeText(
                getApplicationContext(),
                "Clicked " + recyclerView.getChildAdapterPosition(view), Toast.LENGTH_SHORT).show();
*/
        Intent intent = new Intent(this, DataActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }
}

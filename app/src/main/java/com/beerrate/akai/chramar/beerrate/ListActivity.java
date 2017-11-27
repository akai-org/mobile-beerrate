package com.beerrate.akai.chramar.beerrate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.beerrate.akai.chramar.beerrate.Adapters.BeerRecyclerViewAdapter;
import com.beerrate.akai.chramar.beerrate.RecyclerViewClickListener.BeerRecyclerViewItemListener;
import com.beerrate.akai.chramar.beerrate.RecyclerViewClickListener.ClickListener;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ClickListener {

    private RecyclerView recyclerView;
    private BeerRecyclerViewAdapter adapter;
    private List<Beer> beerList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ///Geting Beers List!!

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new BeerRecyclerViewAdapter(beerList, getApplicationContext().getResources().getDisplayMetrics().density);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new BeerRecyclerViewItemListener(
                        getApplicationContext(), recyclerView, this));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"CLick", Toast.LENGTH_SHORT);
    }

    @Override
    public void onLongClick(View view) {
        Toast.makeText(getApplicationContext(), "LongClick", Toast.LENGTH_SHORT);
    }

    @Override
    public void onLongClickEnd(View view) {
        Toast.makeText(getApplicationContext(), "LongClickEnd", Toast.LENGTH_SHORT);
    }
}

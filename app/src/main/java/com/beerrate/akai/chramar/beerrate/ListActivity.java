package com.beerrate.akai.chramar.beerrate;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.beerrate.akai.chramar.beerrate.Adapters.BeerRecyclerViewAdapter;
import com.beerrate.akai.chramar.beerrate.RecyclerViewClickListener.BeerRecyclerViewItemListener;
import com.beerrate.akai.chramar.beerrate.RecyclerViewClickListener.ClickListener;
import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListActivity extends AppCompatActivity implements ClickListener {

    private String MY_LOG = "Kossa";

    private RecyclerView recyclerView;
    private BeerRecyclerViewAdapter adapter;
    private List<Beer> beerList;
    private RecyclerView.LayoutManager layoutManager;

    private  float density;

    private ImageView row_imageView;

    private int DURATION = 200;
    private ValueAnimator imageView_animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        density = getApplicationContext().getResources().getDisplayMetrics().density;
        ///Geting Beers List!!
        beerList = new List<Beer>() {
            @Override
            public int size() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Beer> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(Beer beer) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Beer> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Beer> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public Beer get(int i) {
                return null;
            }

            @Override
            public Beer set(int i, Beer beer) {
                return null;
            }

            @Override
            public void add(int i, Beer beer) {

            }

            @Override
            public Beer remove(int i) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Beer> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Beer> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Beer> subList(int i, int i1) {
                return null;
            }
        };
        beerList.add(new Beer());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new BeerRecyclerViewAdapter(beerList, density);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.d("Kossa", "53");
        if(adapter != null)
            recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new BeerRecyclerViewItemListener(
                        getApplicationContext(), recyclerView, this));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"CLick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(final View row) {

        row_imageView = row.findViewById(R.id.row_imageView);
        Log.d(MY_LOG, "" + (int) convertDpToPx(75) + " " + (int) convertDpToPx(150));
        imageView_animator = ValueAnimator.ofInt((int) convertDpToPx(75), (int) convertDpToPx(150));
        imageView_animator.setDuration(DURATION);
        imageView_animator.setInterpolator(new AccelerateDecelerateInterpolator());
        imageView_animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                row_imageView.getLayoutParams().height = value;
                row_imageView.getLayoutParams().width = value;
                row_imageView.requestLayout();
            }
        });
        imageView_animator.start();

        Toast.makeText(getApplicationContext(), "LongClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClickEnd(View row) {

        //Zmiania wielkości imageView
        row_imageView = row.findViewById(R.id.row_imageView);
        imageView_animator = ValueAnimator.ofInt((int) convertDpToPx(150), (int) convertDpToPx(75));
        imageView_animator.setDuration(DURATION);
        imageView_animator.setInterpolator(new AccelerateDecelerateInterpolator());
        imageView_animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                row_imageView.getLayoutParams().height = value;
                row_imageView.getLayoutParams().width = value;
                row_imageView.requestLayout();
            }
        });
        imageView_animator.start();

        Toast.makeText(getApplicationContext(), "LongClickEnd", Toast.LENGTH_SHORT).show();
    }

    public float convertDpToPx(float dp){
        float px = dp * density;
        return px;
    }
}

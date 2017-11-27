package com.beerrate.akai.chramar.beerrate.RecyclerViewClickListener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.beerrate.akai.chramar.beerrate.Adapters.BeerRecyclerViewAdapter;

/**
 * Created by Dominik on 27.11.2017.
 */

public class BeerRecyclerViewItemListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private ClickListener clickListener;
    private boolean longClick = false;

    public BeerRecyclerViewItemListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
        this.clickListener = clickListener;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(child != null && clickListener != null){
                    clickListener.onLongClick(child);
                }
                longClick = true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
            clickListener.onClick(child);
        }
        else if(e.getAction() == MotionEvent.ACTION_UP && longClick){
            clickListener.onLongClickEnd(child);
            longClick = false;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

package com.beerrate.akai.chramar.beerrate.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.beerrate.akai.chramar.beerrate.R;

/**
 * Created by Dominik on 27.11.2017.
 */

public class BeerRecyclerViewAdapter extends RecyclerView.Adapter<BeerRecyclerViewAdapter.BeerViewHolder> {

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder{

        public ImageView row_imageView;

        public TextView row_name_textView;
        public TextView row_brawery_textView;
        public TextView row_style_textView;
        public TextView row_price_textView;
        public TextView row_place_textView;
        public TextView row_ibu_textView;
        public TextView row_alc_textView;

        public RatingBar row_ratingBar;

        public LinearLayout row_style_price_LL;
        public LinearLayout row_place_ibu_alc_LL;

        public BeerViewHolder(View itemView) {
            super(itemView);
            row_imageView = itemView.findViewById(R.id.row_imageView);

            row_name_textView = itemView.findViewById(R.id.row_name_textView);
            row_brawery_textView = itemView.findViewById(R.id.row_brewery_textView);
            row_style_textView = itemView.findViewById(R.id.row_style_textView);
            row_price_textView = itemView.findViewById(R.id.row_price_textView);
            row_place_textView = itemView.findViewById(R.id.row_place_textView);
            row_ibu_textView = itemView.findViewById(R.id.row_ibu_textView);
            row_alc_textView = itemView.findViewById(R.id.row_alc_textView);

            row_ratingBar = itemView.findViewById(R.id.row_ratingbar);

            row_style_price_LL = itemView.findViewById(R.id.row_style_price_LL);
            row_place_ibu_alc_LL = itemView.findViewById(R.id.row_place_ibu_alc_LL);

        }
    }
}

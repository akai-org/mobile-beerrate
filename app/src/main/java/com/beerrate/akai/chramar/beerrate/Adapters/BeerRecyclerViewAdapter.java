package com.beerrate.akai.chramar.beerrate.Adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.beerrate.akai.chramar.beerrate.R;
import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.List;

/**
 * Created by Dominik on 27.11.2017.
 */

public class BeerRecyclerViewAdapter extends RecyclerView.Adapter<BeerRecyclerViewAdapter.BeerViewHolder> {

    private List<Beer> beerList;
    private float density;

    ///Konstruktoe ustaiwający listę piw i współczynnik density odpowiedni dla ekranu użytkownika
    public BeerRecyclerViewAdapter(List<Beer> beerList, float density){
        this.beerList = beerList;
        this.density = density;
    }

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row, parent, false);
        return new BeerViewHolder(row);
    }

    /*Metoda ustawiająca wygląd poszczególnych itemów w recyclerView
    ustawia ona teksty oraz ilość gwiazdek w ratingBar'ze
     */
    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        /*Beer beer = beerList.get(position);
        holder.row_name_textView.setText(beer.getName());
        float name_width = holder.row_name_textView.getMeasuredWidth();
        float name_height = holder.row_name_textView.getMeasuredHeight();

        ViewGroup.MarginLayoutParams brawery_margin =
                (ViewGroup.MarginLayoutParams) holder.row_brawery_textView.getLayoutParams();
        brawery_margin.leftMargin = (int) (name_width + convertDpToPx(115));
        holder.row_brawery_textView.setLayoutParams(brawery_margin);
        holder.row_brawery_textView.requestLayout();
        holder.row_brawery_textView.setText(beer.getBrawery());

        ViewGroup.MarginLayoutParams ratingBar_margin =
                (ViewGroup.MarginLayoutParams) holder.row_ratingBar.getLayoutParams();
        ratingBar_margin.topMargin = (int)(name_height + convertDpToPx(24));
        holder.row_ratingBar.setLayoutParams(ratingBar_margin);
        holder.row_ratingBar.requestLayout();
        holder.row_ratingBar.setRating(beer.getNote());

        float ratingBar_height = holder.row_ratingBar.getMeasuredHeight();

        ViewGroup.MarginLayoutParams sp_ll_margin =
                (ViewGroup.MarginLayoutParams) holder.row_style_price_LL.getLayoutParams();
        sp_ll_margin.topMargin = (int) (name_height + ratingBar_height + convertDpToPx(40));
        holder.row_style_price_LL.setLayoutParams(sp_ll_margin);
        holder.row_style_price_LL.requestLayout();

        holder.row_style_textView.setText("Style: " + beer.getStyle());
        holder.row_price_textView.setText("Price: " + beer.getPrice());

        float sp_ll_height = holder.row_style_price_LL.getMeasuredHeight();

        ViewGroup.MarginLayoutParams pia_LL_margin =
                (ViewGroup.MarginLayoutParams) holder.row_place_ibu_alc_LL.getLayoutParams();
        pia_LL_margin.topMargin = (int) (name_height + ratingBar_height + sp_ll_height);
        holder.row_place_ibu_alc_LL.setLayoutParams(pia_LL_margin);
        holder.row_place_ibu_alc_LL.requestLayout();

        holder.row_place_textView.setText("Place: " + beer.getPlace());
        holder.row_ibu_textView.setText("IBU: " + beer.getIBU());
        holder.row_alc_textView.setText("Alc: " + beer.getAlc());
        */

    }

    ///Metoda zwracająca ilość obiektów Beer na liście beerList
    @Override
    public int getItemCount() {
        return beerList.size();
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
            row_imageView = (ImageView) itemView.findViewById(R.id.row_imageView);

            row_name_textView = (TextView) itemView.findViewById(R.id.row_name_textView);
            row_brawery_textView = (TextView) itemView.findViewById(R.id.row_brewery_textView);
            row_style_textView = (TextView) itemView.findViewById(R.id.row_style_textView);
            row_price_textView = (TextView) itemView.findViewById(R.id.row_price_textView);
            row_place_textView = (TextView) itemView.findViewById(R.id.row_place_textView);
            row_ibu_textView = (TextView) itemView.findViewById(R.id.row_ibu_textView);
            row_alc_textView = (TextView) itemView.findViewById(R.id.row_alc_textView);

            row_ratingBar = (RatingBar) itemView.findViewById(R.id.row_ratingbar);

            row_style_price_LL = (LinearLayout) itemView.findViewById(R.id.row_style_price_LL);
            row_place_ibu_alc_LL = (LinearLayout) itemView.findViewById(R.id.row_place_ibu_alc_LL);

        }
    }

    public float convertDpToPx(float dp){
        float px = dp * density;
        return px;
    }
}

package com.beerrate.akai.chramar.beerrate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 07.03.2018.
 */

public class BestBeerRecyclerViewAdapter
        extends RecyclerView.Adapter<BestBeerRecyclerViewAdapter.BestBeerViewHolder> {

    private List<Beer> beerArrayList;
    //private ArrayList<String> favoriteBeers;
    private float denisty;

    public BestBeerRecyclerViewAdapter(List<Beer> beerArrayList, ArrayList<String> favoriteBeers, float denisty) {
        this.beerArrayList = beerArrayList;
        this.denisty = denisty;
        // this.favoriteBeers = favoriteBeers;
    }

    @NonNull
    @Override
    public BestBeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.the_best_beer, parent, false);

        return new BestBeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestBeerViewHolder holder, int position) {
        Beer beer = beerArrayList.get(position);
        holder.nameTextView.setText(String.valueOf(position + 1) + ". " + beer.getName());
        holder.ratingBar.setRating(3.5f);
        if (MainActivity.favoriteBeers.contains(beer.getName())) {
            holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_accent_24dp);
        } else {
            holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_border_accent_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return beerArrayList.size();
    }

    public class BestBeerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView commentTextView;
        private TextView voteTextView;
        private ImageView voteImageView;
        private ImageView commentImageView;
        private TextView nameTextView;
        private RatingBar ratingBar;
        private ImageView favoriteImageView;

        public BestBeerViewHolder(View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.bestRatingBar);
            nameTextView = itemView.findViewById(R.id.bestName_textView);
            commentImageView = itemView.findViewById(R.id.image_comment);
            commentImageView.setOnClickListener(this);
            voteImageView = itemView.findViewById(R.id.image_vote);
            voteImageView.setOnClickListener(this);
            commentTextView = itemView.findViewById(R.id.commentTextView);
            commentTextView.setOnClickListener(this);
            voteTextView = itemView.findViewById(R.id.vote_textView);
            voteTextView.setOnClickListener(this);
            favoriteImageView = itemView.findViewById(R.id.favorite_imageView);
            favoriteImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.equals(commentImageView) || v.equals(commentTextView)) {
                Toast.makeText(v.getContext(), "Comment", Toast.LENGTH_SHORT).show();
            } else if(v.equals(voteImageView) || v.equals(voteTextView)) {
                Toast.makeText(v.getContext(), "Vote", Toast.LENGTH_SHORT).show();
            } else if (v.equals(favoriteImageView)) {
                boolean cont = false;
                int i;
                for (i = 0; i < MainActivity.favoriteBeers.size(); i++) {
                    if (MainActivity.favoriteBeers.get(i).contentEquals(beerArrayList.get(getPosition()).getName())) {
                        favoriteImageView.setImageResource(R.drawable.ic_favorite_border_accent_24dp);
                        cont = true;
                        break;
                    }
                }
                if (cont) {
                    MainActivity.favoriteBeers.remove(i);
                } else {
                    favoriteImageView.setImageResource(R.drawable.ic_favorite_accent_24dp);
                    MainActivity.favoriteBeers.add(beerArrayList.get(getPosition()).getName());
                }

                Toast.makeText(v.getContext(), "Favorite " + getPosition(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}

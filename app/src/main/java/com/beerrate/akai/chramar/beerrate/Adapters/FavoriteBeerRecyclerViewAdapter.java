package com.beerrate.akai.chramar.beerrate.Adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beerrate.akai.chramar.beerrate.FavoriteBeerActivity;
import com.beerrate.akai.chramar.beerrate.MainActivity;
import com.beerrate.akai.chramar.beerrate.R;
import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

/**
 * Created by Domink on 22.03.2018.
 */

public class FavoriteBeerRecyclerViewAdapter extends
        RecyclerView.Adapter<FavoriteBeerRecyclerViewAdapter.FavoriteBeerViewHolder> {

    private FavoriteBeerActivity parentActivity;

    public FavoriteBeerRecyclerViewAdapter(FavoriteBeerActivity parentActivity) {
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public FavoriteBeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.the_best_beer, parent, false);
        return new FavoriteBeerViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteBeerViewHolder holder, int position) {
        Beer beer = MainActivity.favoriteBeers.get(position);
        holder.nameTextView.setText(String.valueOf(position + 1) + ". " + beer.getName());
        holder.ratingBar.setRating(3.5f);
        if (MainActivity.favoriteBeersNames.contains(beer.getName())) {
            holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_accent_24dp);
        } else {
            holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_border_accent_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return MainActivity.favoriteBeers.size();
    }

    public class FavoriteBeerViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private FavoriteBeerRecyclerViewAdapter adapter;
        private TextView commentTextView;
        private TextView voteTextView;
        private ImageView voteImageView;
        private ImageView commentImageView;
        private TextView nameTextView;
        private RatingBar ratingBar;
        private ImageView favoriteImageView;
        private ImageView beerImageView;
        private ConstraintLayout layout;

        public FavoriteBeerViewHolder(View itemView, FavoriteBeerRecyclerViewAdapter adapter) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.bestRatingBar);
            nameTextView = itemView.findViewById(R.id.bestName_textView);
            nameTextView.setOnClickListener(this);
            nameTextView.setOnLongClickListener(this);
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
            beerImageView = itemView.findViewById(R.id.best_imageView);
            beerImageView.setOnClickListener(this);
            beerImageView.setOnLongClickListener(this);
            layout = itemView.findViewById(R.id.best_row);
            layout.setOnClickListener(this);
            layout.setOnLongClickListener(this);
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {
            if (v.equals(commentImageView) || v.equals(commentTextView)) {
                Toast.makeText(v.getContext(), "Comment", Toast.LENGTH_SHORT).show();
            } else if (v.equals(voteImageView) || v.equals(voteTextView)) {
                Toast.makeText(v.getContext(), "Vote", Toast.LENGTH_SHORT).show();
            } else if (v.equals(favoriteImageView)) {
                MainActivity.favoriteBeers.remove(getPosition());
                MainActivity.favoriteBeersNames.remove(getPosition());
                adapter.notifyDataSetChanged();
                Toast.makeText(v.getContext(), "Favorite " + getPosition(), Toast.LENGTH_SHORT).show();

            } else {
                parentActivity.startDataActivity(getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            //Toast.makeText(v.getContext(), "LongClick", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

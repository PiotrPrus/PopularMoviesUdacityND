package com.example.popularmoviesudacitynd.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.popularmoviesudacitynd.R;
import com.squareup.picasso.Picasso;

public class PosterViewHolder extends RecyclerView.ViewHolder implements PosterItemView {

    private ImageView posterImageView;

    public PosterViewHolder(View itemView) {
        super(itemView);
        posterImageView = itemView.findViewById(R.id.poster_iv);
    }

    @Override
    public void setImage(String imageUrl) {
        Picasso.with(posterImageView.getContext()).load(imageUrl).into(posterImageView);
    }
}

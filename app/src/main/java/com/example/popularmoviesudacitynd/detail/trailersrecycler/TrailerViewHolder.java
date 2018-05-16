package com.example.popularmoviesudacitynd.detail.trailersrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.popularmoviesudacitynd.R;
import com.squareup.picasso.Picasso;

public class TrailerViewHolder extends RecyclerView.ViewHolder implements TrailerItemView {

    private ImageView detailTrailerIV;

    public TrailerViewHolder(View itemView) {
        super(itemView);
        detailTrailerIV = itemView.findViewById(R.id.detail_trailer_iv);
    }

    @Override
    public void setThumbnail(String imageUrl) {
        Picasso.get().load(BASE_URL + imageUrl).into(detailTrailerIV);
    }
}

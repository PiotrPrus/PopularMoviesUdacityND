package com.example.popularmoviesudacitynd.detail.trailersrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.popularmoviesudacitynd.R;
import com.squareup.picasso.Picasso;

public class TrailerViewHolder extends RecyclerView.ViewHolder implements TrailerItemView {

    private static final String YT_THUMBNAIL_END = "/default.jpg";
    private static final String BASE_YT_URL = "http://img.youtube.com/vi/";
    private ImageView detailTrailerIV;

    public TrailerViewHolder(View itemView) {
        super(itemView);
        detailTrailerIV = itemView.findViewById(R.id.detail_trailer_iv);
    }

    @Override
    public void setThumbnail(String videoKey) {
        Picasso.get().load(BASE_YT_URL + videoKey + YT_THUMBNAIL_END).into(detailTrailerIV);
    }
}

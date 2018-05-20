package com.example.popularmoviesudacitynd.detail.trailersrecycler;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.network.MovieTrailer;

import java.util.List;

public class TrailerRecyclerAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    private static final String BASE_YT_URL = "http://www.youtube.com/watch?v=";
    private Activity activity;
    private List<MovieTrailer> data;
    private MovieTrailer movieTrailer;

    public TrailerRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<MovieTrailer> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrailerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_trailer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        movieTrailer = data.get(position);
        onBindTrailerItemViewAtPosition(holder);
        holder.itemView.setOnClickListener(view -> watchYoutubeVideo(movieTrailer.getKey()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void onBindTrailerItemViewAtPosition(TrailerItemView itemView) {
        itemView.setThumbnail(movieTrailer.getKey());
    }

    private void watchYoutubeVideo(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(BASE_YT_URL + id));
        try {
            activity.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            activity.startActivity(webIntent);
        }
    }
}

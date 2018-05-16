package com.example.popularmoviesudacitynd.detail.trailersrecycler;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.network.MovieTrailer;

import java.util.List;

public class TrailerRecyclerAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    private Activity activity;
    private List<MovieTrailer> data;

    public TrailerRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<MovieTrailer> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TrailerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.railer_item, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void onBindTrailerItemViewAtPosition(int position, TrailerItemView itemView){
        MovieTrailer movieTrailer = data.get(position);
        itemView.setThumbnail(movieTrailer.getPosterPath());
    }
}

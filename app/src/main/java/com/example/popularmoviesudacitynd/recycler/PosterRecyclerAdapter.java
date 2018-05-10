package com.example.popularmoviesudacitynd.recycler;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.detail.MovieDetailActivity;
import com.example.popularmoviesudacitynd.network.ResultsItem;

import java.util.List;

public class PosterRecyclerAdapter extends RecyclerView.Adapter<PosterViewHolder>{

    private Activity activity;
    private List<ResultsItem> data;

    public PosterRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<ResultsItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_poster_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        onBindPosterItemViewAtPosition(position, holder);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, MovieDetailActivity.class);
            ResultsItem item = data.get(position);
            intent.putExtra(ResultsItem.KEY_MOVIE_DATA, item);
            activity.getApplicationContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void onBindPosterItemViewAtPosition(int position, PosterItemView itemView){
        ResultsItem movie = data.get(position);
        itemView.setImage(movie.getPosterPath());
    }
}
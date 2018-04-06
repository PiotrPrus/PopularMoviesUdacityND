package com.example.popularmoviesudacitynd.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.network.ResultsItem;

import java.util.List;

import javax.xml.transform.Result;

public class PosterRecyclerAdapter extends RecyclerView.Adapter<PosterViewHolder>{

    private PosterGridPresenter presenter;

    public PosterRecyclerAdapter(List<ResultsItem> data) {
        presenter = new PosterGridPresenter(data);
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_poster_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        presenter.onBindPosterItemViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getPostersItemsCount();
    }
}
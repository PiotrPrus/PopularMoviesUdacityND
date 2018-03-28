package com.example.popularmoviesudacitynd.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.popularmoviesudacitynd.R;

public class PosterRecyclerAdapter extends RecyclerView.Adapter<PosterViewHolder>{

    private final PosterGridPresenter presenter;

    public PosterRecyclerAdapter(PosterGridPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_poster_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        presenter.onBindPosterItemViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getPostersItemsCount();
    }
}
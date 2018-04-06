package com.example.popularmoviesudacitynd.recycler;

import com.example.popularmoviesudacitynd.network.ResultsItem;

import java.util.List;

/**
 * Created by Piotr on 20.02.2018.
 */

public class PosterGridPresenter {

    private final List<ResultsItem> data;

    PosterGridPresenter(List<ResultsItem> data) {
        this.data = data;
    }

    public void onBindPosterItemViewAtPosition(int position, PosterItemView itemView){
        ResultsItem movie = data.get(position);
        itemView.setImage(movie.getPosterPath());
    }

    public int getPostersItemsCount() {
        return data.size();
    }
}

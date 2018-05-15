package com.example.popularmoviesudacitynd.detail;

import com.example.popularmoviesudacitynd.network.MovieTrailer;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

public interface DetailView extends MvpView {
    void onStartLoading();
    void onLoadCompleted(List<MovieTrailer> data);
    void onLoadError();
}

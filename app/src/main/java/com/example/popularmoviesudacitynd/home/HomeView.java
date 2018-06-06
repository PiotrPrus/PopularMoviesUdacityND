package com.example.popularmoviesudacitynd.home;

import com.example.popularmoviesudacitynd.network.Movie;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

interface HomeView extends MvpView {
    void onStartLoading();
    void onLoadCompleted(List<Movie> data);
    void onLoadError();
    void onInternetCheckFailed();
}

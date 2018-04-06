package com.example.popularmoviesudacitynd.home;

import com.example.popularmoviesudacitynd.network.ResultsItem;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

interface HomeView extends MvpView {
    //TODO: make use of those methods
    void onStartLoading();
    void onLoadCompleted(List<ResultsItem> data);
    void onLoadError();
}

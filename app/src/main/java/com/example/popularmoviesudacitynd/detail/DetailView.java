package com.example.popularmoviesudacitynd.detail;

import com.example.popularmoviesudacitynd.network.MovieTrailer;
import com.example.popularmoviesudacitynd.network.Review;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

public interface DetailView extends MvpView {
    void onStartLoading();
    void onTrailersLoadCompleted(List<MovieTrailer> data);
    void onReviewsLoadCompleted(List<Review> data);
    void onLoadError();
}

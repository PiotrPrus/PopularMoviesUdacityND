package com.example.popularmoviesudacitynd.detail;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.popularmoviesudacitynd.BuildConfig;
import com.example.popularmoviesudacitynd.network.MovieTrailer;
import com.example.popularmoviesudacitynd.network.MovieTrailerList;
import com.example.popularmoviesudacitynd.network.Review;
import com.example.popularmoviesudacitynd.network.ReviewList;
import com.example.popularmoviesudacitynd.network.TMDBService;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailPresenter extends MvpBasePresenter<DetailView> {
    private final static String TAG = DetailPresenter.class.getSimpleName();
    private List<MovieTrailer> trailerList;
    private List<Review> reviewList;
    private TMDBService service;
    private int movieId;

    public void loadData(int movieId, DetailDataType dataType) {
        this.movieId = movieId;
        Objects.requireNonNull(getView()). onStartLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TMDBService.class);
        if (dataType == DetailDataType.TRAILER) {
            fetchMovieTrailers();
        } else if (dataType == DetailDataType.REVIEW){
            fetchMovieReviews();
        }
    }

    private void fetchMovieReviews() {
        Call<ReviewList> call = service.getMovieReviewsJson(movieId, BuildConfig.TMDB_API_KEY);

        Objects.requireNonNull(call).enqueue(new Callback<ReviewList>() {
            @Override
            public void onResponse(Call<ReviewList> call, Response<ReviewList> response) {
                ReviewList jsonResponse = response.body();
                assert jsonResponse != null;
                reviewList = jsonResponse.getResults();
                Log.i(TAG, "Number of reviews fetched: " + reviewList.size());
                Objects.requireNonNull(getView()).onReviewsLoadCompleted(reviewList);

            }

            @Override
            public void onFailure(Call<ReviewList> call, Throwable t) {
                Log.w(TAG, "Error trying parse Json" + t.getMessage());
                Objects.requireNonNull(getView()).onLoadError();
            }
        });
    }

    private void fetchMovieTrailers() {
        Call<MovieTrailerList> call = service.getMovieTrailersJson(movieId, BuildConfig.TMDB_API_KEY);

        Objects.requireNonNull(call).enqueue(new Callback<MovieTrailerList>() {
            @Override
            public void onResponse(@NonNull Call<MovieTrailerList> call, @NonNull Response<MovieTrailerList> response) {

                MovieTrailerList jsonResponse = response.body();
                assert jsonResponse != null;
                trailerList = jsonResponse.getResults();
                Log.i(TAG, "Number of trailers fetched: " + trailerList.size());
                Objects.requireNonNull(getView()).onTrailersLoadCompleted(trailerList);
            }

            @Override
            public void onFailure(@NonNull Call<MovieTrailerList> call, @NonNull Throwable t) {
                Log.w(TAG, "Error trying parse Json" + t.getMessage());
                Objects.requireNonNull(getView()).onLoadError();
            }
        });
    }

    enum DetailDataType {
        TRAILER, REVIEW
    }

    public void handleFavourite() {

    }
}

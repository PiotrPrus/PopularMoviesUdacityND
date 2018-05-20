package com.example.popularmoviesudacitynd.detail;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.popularmoviesudacitynd.BuildConfig;
import com.example.popularmoviesudacitynd.home.HomePresenter;
import com.example.popularmoviesudacitynd.network.MovieTrailer;
import com.example.popularmoviesudacitynd.network.MovieTrailerList;
import com.example.popularmoviesudacitynd.network.PopularMoviePOJO;
import com.example.popularmoviesudacitynd.network.ResultsItem;
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
    private List<MovieTrailer> data;

    public void loadData(int movieId) {
        Objects.requireNonNull(getView()).onStartLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TMDBService service = retrofit.create(TMDBService.class);
        Call<MovieTrailerList> call = service.getMovieTrailersJson(movieId, BuildConfig.TMDB_API_KEY);

        Objects.requireNonNull(call).enqueue(new Callback<MovieTrailerList>() {
            @Override
            public void onResponse(@NonNull Call<MovieTrailerList> call, @NonNull Response<MovieTrailerList> response) {

                MovieTrailerList jsonResponse = response.body();
                assert jsonResponse != null;
                data = jsonResponse.getResults();
                Log.i(TAG, "Number of trailers fetched: " + data.size());
                Objects.requireNonNull(getView()).onLoadCompleted(data);
            }

            @Override
            public void onFailure(@NonNull Call<MovieTrailerList> call, @NonNull Throwable t) {
                Log.w(TAG, "Error trying parse Json" + t.getMessage());
                Objects.requireNonNull(getView()).onLoadError();
            }
        });
    }
}

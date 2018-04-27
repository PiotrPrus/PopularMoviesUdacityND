package com.example.popularmoviesudacitynd.home;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.popularmoviesudacitynd.BuildConfig;
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

public class HomePresenter extends MvpBasePresenter<HomeView> {

    private final static String TAG = HomePresenter.class.getSimpleName();
    private List<ResultsItem> data;

    public void loadData() {
        Objects.requireNonNull(getView()).onStartLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TMDBService service = retrofit.create(TMDBService.class);
        Call<PopularMoviePOJO> call = service.getJson(BuildConfig.TMDB_API_KEY);
        call.enqueue(new Callback<PopularMoviePOJO>() {
            @Override
            public void onResponse(@NonNull Call<PopularMoviePOJO> call, @NonNull Response<PopularMoviePOJO> response) {

                PopularMoviePOJO jsonResponse = response.body();
                assert jsonResponse != null;
                data = jsonResponse.getResults();
                Log.i(TAG, "Number of movies fetched: " + data.size());
                Objects.requireNonNull(getView()).onLoadCompleted(data);
            }

            @Override
            public void onFailure(@NonNull Call<PopularMoviePOJO> call, @NonNull Throwable t) {
                Log.w(TAG, "Error trying parse Json" + t.getMessage());
                Objects.requireNonNull(getView()).onLoadError();
            }
        });
    }
}

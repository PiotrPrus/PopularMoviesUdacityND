package com.example.popularmoviesudacitynd.home;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.popularmoviesudacitynd.BuildConfig;
import com.example.popularmoviesudacitynd.database.DatabaseManager;
import com.example.popularmoviesudacitynd.database.MoviesDatabaseManager;
import com.example.popularmoviesudacitynd.network.Movie;
import com.example.popularmoviesudacitynd.network.PopularMoviePOJO;
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
    private List<Movie> data;

    public void loadData(MovieListType listType) {
        Objects.requireNonNull(getView()).onStartLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TMDBService service = retrofit.create(TMDBService.class);
        Call<PopularMoviePOJO> call = null;
        if (listType == MovieListType.POPULAR) {
            call = service.getPopularMoviesJson(BuildConfig.TMDB_API_KEY);
        } else if (listType == MovieListType.TOP_RATED) {
            call = service.getTopRatedMoviesJson(BuildConfig.TMDB_API_KEY);
        }
        Objects.requireNonNull(call).enqueue(new Callback<PopularMoviePOJO>() {
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

    public void isNetworkConnection(ConnectivityManager connectivityManager) {

        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiInfo == null || !wifiInfo.isConnected()) && (mobileInfo == null || !mobileInfo.isConnected())) {
            Objects.requireNonNull(getView()).onInternetCheckFailed();
        }
    }

    public void loadFavourites(MoviesDatabaseManager dbManager) {
        List<Movie> movieList = dbManager.getFavouriteMoviesList();
        if (movieList.isEmpty()){
            Objects.requireNonNull(getView()).onLoadError();
        } else {
            Objects.requireNonNull(getView()).onLoadCompleted(movieList);
        }
    }

    enum MovieListType {
        POPULAR, TOP_RATED
    }
}

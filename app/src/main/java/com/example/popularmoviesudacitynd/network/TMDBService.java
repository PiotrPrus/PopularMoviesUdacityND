package com.example.popularmoviesudacitynd.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBService {
    @GET("3/movie/popular")
    Call<PopularMoviePOJO> getJson(@Query("api_key") String apiKey);
}

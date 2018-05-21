package com.example.popularmoviesudacitynd.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBService {
    @GET("3/movie/popular")
    Call<PopularMoviePOJO> getPopularMoviesJson(@Query("api_key") String apiKey);
    @GET("3/movie/top_rated")
    Call<PopularMoviePOJO> getTopRatedMoviesJson(@Query("api_key") String apiKey);
    @GET("3/movie/{id}/videos")
    Call<MovieTrailerList> getMovieTrailersJson(@Path("id") int movieId, @Query("api_key") String apiKey);
    @GET("3/movie/{id}/reviews")
    Call<ReviewList> getMovieReviewsJson(@Path("id") int movieId, @Query("api_key") String apiKey);
}

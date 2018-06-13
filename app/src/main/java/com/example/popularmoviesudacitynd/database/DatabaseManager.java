package com.example.popularmoviesudacitynd.database;

import com.example.popularmoviesudacitynd.network.Movie;

import java.util.List;

public interface DatabaseManager {

    void addMovieToFavourite(Movie movie);

    void removeMovieFromFavourite(String id);

    List<Movie> getFavouriteMoviesList();

    boolean isFavourite(String id);
}

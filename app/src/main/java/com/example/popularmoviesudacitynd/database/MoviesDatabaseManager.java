package com.example.popularmoviesudacitynd.database;

import android.content.ContentResolver;
import android.content.ContentValues;

import com.example.popularmoviesudacitynd.network.Movie;

import java.util.List;

public class MoviesDatabaseManager implements DatabaseManager {
    private ContentResolver contentResolver;

    public MoviesDatabaseManager(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public void addMovieToFavourite(Movie movie) {
        ContentValues contentValues = MovieContentValues.addMovie(movie);
        contentResolver.insert(MoviesContract.MoviesEntry.CONTENT_URI, contentValues);
    }

    @Override
    public void removeMovieFromFavourite(String id) {

    }

    @Override
    public List<Movie> getFavouriteMoviewsList() {
        return null;
    }

    @Override
    public boolean isFavourite(String id) {
        return false;
    }
}

package com.example.popularmoviesudacitynd.database;

import android.content.ContentValues;

import com.example.popularmoviesudacitynd.network.Movie;

public class MovieContentValues {

    public static ContentValues addMovie(Movie movie){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID, movie.getId());
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_NAME_TITLE, movie.getTitle());
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_NAME_POSTER_PATH, movie.getPosterPath());
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_NAME_BACKDROP_PATH, movie.getBackdropPath());
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_OVERVIEW, movie.getOverview());
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_DATE, movie.getReleaseDate());
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_RATING, movie.getVoteAverage());
        return contentValues;
    }

}

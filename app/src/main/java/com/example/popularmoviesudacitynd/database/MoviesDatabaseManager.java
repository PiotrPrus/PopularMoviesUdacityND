package com.example.popularmoviesudacitynd.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.popularmoviesudacitynd.network.Movie;

import java.util.ArrayList;
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
        String selection = MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID + " = ?";
        String[] selectionArgs = {id};
        contentResolver.delete(MoviesContract.MoviesEntry.CONTENT_URI, selection, selectionArgs);
    }

    @Override
    public List<Movie> getFavouriteMoviesList() {
        List<Movie> moviesList = new ArrayList<>();
        Cursor cursor = contentResolver.query(MoviesContract.MoviesEntry.CONTENT_URI, null, null, null, null);
        if (cursor != null){
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_NAME_TITLE));
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID));
                String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(MoviesContract.MoviesEntry.COLUMN_NAME_POSTER_PATH));
                String overview = cursor.getString(cursor.getColumnIndexOrThrow(MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_OVERVIEW));
                String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_DATE));
                String backdropPath = cursor.getString(cursor.getColumnIndexOrThrow(MoviesContract.MoviesEntry.COLUMN_NAME_BACKDROP_PATH));
                double movieRating = cursor.getDouble(cursor.getColumnIndexOrThrow(MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_RATING));
                Movie cursorMovie = new Movie();
                cursorMovie.setTitle(title);
                cursorMovie.setId(id);
                cursorMovie.setPosterPath(posterPath);
                cursorMovie.setOverview(overview);
                cursorMovie.setReleaseDate(releaseDate);
                cursorMovie.setBackdropPath(backdropPath);
                cursorMovie.setVoteAverage(movieRating);
                moviesList.add(cursorMovie);
            }
        }
        return moviesList;
    }

    @Override
    public boolean isFavourite(String id) {
        String selection = MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID + " = ? ";
        String[] selectionArgs = {id};
        String[] projection = {MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID};
        Cursor cursor = contentResolver.query(MoviesContract.MoviesEntry.CONTENT_URI, projection, selection, selectionArgs, null);
        assert cursor != null;
        return cursor.getCount() !=0;
    }
}

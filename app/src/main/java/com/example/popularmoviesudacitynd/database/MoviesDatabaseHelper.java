package com.example.popularmoviesudacitynd.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MoviesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    public MoviesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE " + MoviesContract.MoviesEntry.TABLE_NAME
                + " (" + MoviesContract.MoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID + " INTEGER NOT NULL, "
                + MoviesContract.MoviesEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL, "
                + MoviesContract.MoviesEntry.COLUMN_NAME_BACKDROP_PATH + " TEXT NOT NULL, "
                + MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_OVERVIEW + " TEXT NOT NULL, "
                + MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_DATE + " TEXT NOT NULL, "
                + MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_RATING + " DOUBLE NOT NULL, "
                + MoviesContract.MoviesEntry.COLUMN_NAME_POSTER_PATH + " TEXT NOT NULL"
                + ");";

        Log.d("DatabaseHelper", "Database query: " + SQL_CREATE_MOVIES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MoviesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

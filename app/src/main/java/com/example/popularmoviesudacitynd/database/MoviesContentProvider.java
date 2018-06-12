package com.example.popularmoviesudacitynd.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

import static com.example.popularmoviesudacitynd.database.MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID;
import static com.example.popularmoviesudacitynd.database.MoviesContract.MoviesEntry.CONTENT_URI;
import static com.example.popularmoviesudacitynd.database.MoviesContract.MoviesEntry.TABLE_NAME;

public class MoviesContentProvider extends ContentProvider {

    public static final int CODE_MOVIES = 100;
    public static final int CODE_MOVIES_ID = 101;

    private MoviesDatabaseHelper dbHelper;
    private static final UriMatcher uriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_MOVIES, CODE_MOVIES);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_MOVIES + "/#", CODE_MOVIES_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MoviesDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArg, @Nullable String sortOrder) {
        final SQLiteDatabase readableDb = dbHelper.getReadableDatabase();
        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case CODE_MOVIES:
                cursor = readableDb.query(
                        TABLE_NAME,
                        projection,
                        selection,
                        selectionArg,
                        null,
                        null,
                        sortOrder
                );
                break;
            case CODE_MOVIES_ID:
                String id = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{id};
                cursor = readableDb.query(
                        TABLE_NAME,
                        projection,
                        COLUMN_NAME_MOVIE_ID + " = ? ",
                        selectionArguments,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Uknown uri: " + uri);
        }
        cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase writableDb = dbHelper.getWritableDatabase();
        Uri returnUri;

        switch (uriMatcher.match(uri)) {
            case CODE_MOVIES:
                long id = writableDb.insert(TABLE_NAME, null, contentValues);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase writeDb = dbHelper.getWritableDatabase();
        int numberOfRowDeleted;

        switch (uriMatcher.match(uri)) {
            case CODE_MOVIES:
                numberOfRowDeleted = writeDb.delete(
                        TABLE_NAME,
                        selection,
                        selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return numberOfRowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}

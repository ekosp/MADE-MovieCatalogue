package com.ekosp.dicoding.moviecatalogue.feature.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ekosp.dicoding.moviecatalogue.database.MovieRoomDatabase;
import com.ekosp.dicoding.moviecatalogue.database.dao.MovieDao;
import com.ekosp.dicoding.moviecatalogue.database.dao.TvshowDao;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;

import java.util.ArrayList;

/**
 * Created by Eko.Purnomo on 2019-07-21.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class ItemContentProvider extends ContentProvider {
    /**
     * The authority of this content provider.
     */
    private static final String AUTHORITY = "com.ekosp.dicoding.moviecatalogue.feature.provider.ItemContentProvider";

    /**
     * The URI for the Movie table.
     */
    public static final Uri URI_MOVIE = Uri.parse(
            "content://" + AUTHORITY + "/" + GlobalVar.TABEL_MOVIE);

    /**
     * The URI for the Tv Show table.
     */
    public static final Uri URI_TVSHOW = Uri.parse(
            "content://" + AUTHORITY + "/" + GlobalVar.TABEL_TVSHOW);

    /**
     * The match code for some items in the Movie table.
     */
    private static final int CODE_MOVIE_DIR = 1;

    /**
     * The match code for an item in the Movie table.
     */
    private static final int CODE_MOVIE_ITEM = 2;

    /**
     * The match code for some items in the Tv Show table.
     */
    private static final int CODE_TVSHOW_DIR = 3;

    /**
     * The match code for an item in the Tv Show table.
     */
    private static final int CODE_TVSHOW_ITEM = 4;

    /**
     * The URI matcher.
     */
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, GlobalVar.TABEL_MOVIE, CODE_MOVIE_DIR);
        MATCHER.addURI(AUTHORITY, GlobalVar.TABEL_MOVIE + "/*", CODE_MOVIE_ITEM);

        MATCHER.addURI(AUTHORITY, GlobalVar.TABEL_TVSHOW, CODE_TVSHOW_DIR);
        MATCHER.addURI(AUTHORITY, GlobalVar.TABEL_TVSHOW + "/*", CODE_TVSHOW_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        final int code = MATCHER.match(uri);
        Cursor cursor;
        final Context context = getContext();
        if (context == null) {
            return null;
        }

        if (code == CODE_MOVIE_DIR || code == CODE_MOVIE_ITEM) {
            MovieDao movieDao = MovieRoomDatabase.getDatabase(context).movieDao();
            if (code == CODE_MOVIE_DIR) {
                cursor = movieDao.getAllCursorMovie();
            } else cursor = movieDao.getMovieById(ContentUris.parseId(uri));

        } else if (code == CODE_TVSHOW_DIR || code == CODE_TVSHOW_ITEM) {
            TvshowDao tvshowDao = MovieRoomDatabase.getDatabase(context).tvshowDao();
            if (code == CODE_TVSHOW_DIR) {
                cursor = tvshowDao.getAllCursorTvshow();
            } else cursor = tvshowDao.getTvshowById(ContentUris.parseId(uri));

        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        cursor.setNotificationUri(context.getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_MOVIE_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + GlobalVar.TABEL_MOVIE;
            case CODE_MOVIE_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + GlobalVar.TABEL_MOVIE;
            case CODE_TVSHOW_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + GlobalVar.TABEL_TVSHOW;
            case CODE_TVSHOW_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + GlobalVar.TABEL_TVSHOW;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {

        final Context context = getContext();

        switch (MATCHER.match(uri)) {
            case CODE_MOVIE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);

            case CODE_MOVIE_ITEM:
                if (context == null) {
                    return 0;
                }

                final int movieCount = MovieRoomDatabase.getDatabase(context).movieDao()
                        .deleteMovieById((int) ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return movieCount;

            case CODE_TVSHOW_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);

            case CODE_TVSHOW_ITEM:
                if (context == null) {
                    return 0;
                }

                final int tvshowCount = MovieRoomDatabase.getDatabase(context).tvshowDao()
                        .deleteTvshowById((int) ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return tvshowCount;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }

    @SuppressWarnings("RedundantThrows") /* This gets propagated up from the Callable */
    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(
            @NonNull final ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {

        final Context context = getContext();
        if (context == null) {
            return new ContentProviderResult[0];
        }

        final MovieRoomDatabase database = MovieRoomDatabase.getDatabase(context);

        return database.runInTransaction(() -> ItemContentProvider.super.applyBatch(operations));
    }


}
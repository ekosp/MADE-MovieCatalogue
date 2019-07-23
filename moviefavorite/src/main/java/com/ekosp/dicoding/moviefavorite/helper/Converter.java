package com.ekosp.dicoding.moviefavorite.helper;

import android.database.Cursor;

import com.ekosp.dicoding.moviefavorite.model.DetailObject;

/**
 * Created by Eko.Purnomo on 2019-07-21.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

class Converter {

    public static DetailObject fromMovieCursor(Cursor cursor) {
        DetailObject mov = new DetailObject();
        mov.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        mov.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
        mov.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow("backdrop_path")));
        mov.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow("poster_path")));
        mov.setOverview(cursor.getString(cursor.getColumnIndexOrThrow("overview")));
        mov.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow("release_date")));
        mov.setVoteAverage(cursor.getFloat(cursor.getColumnIndexOrThrow("vote_average")));
        return mov;
    }

    public static DetailObject fromTvCursor(Cursor cursor) {
        DetailObject mov = new DetailObject();
        mov.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        mov.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        mov.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow("backdrop_path")));
        mov.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow("poster_path")));
        mov.setOverview(cursor.getString(cursor.getColumnIndexOrThrow("overview")));
        mov.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow("first_air_date")));
        mov.setVoteAverage(cursor.getFloat(cursor.getColumnIndexOrThrow("vote_average")));
        return mov;
    }

}

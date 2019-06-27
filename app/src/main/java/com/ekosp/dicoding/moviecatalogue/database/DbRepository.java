package com.ekosp.dicoding.moviecatalogue.database;

import android.content.Context;
import android.os.AsyncTask;

import com.ekosp.dicoding.moviecatalogue.database.dao.MovieDao;
import com.ekosp.dicoding.moviecatalogue.database.dao.TvshowDao;
import com.ekosp.dicoding.moviecatalogue.database.entity.Movie;
import com.ekosp.dicoding.moviecatalogue.database.entity.Tvshow;

/**
 * Created by Eko S.P on 27/10/2018.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

public class DbRepository {

    private MovieDao mMovieiDao;
    private TvshowDao mTvshowDao;

    private MovieRoomDatabase db;

    public DbRepository(Context ctx) {

        db = MovieRoomDatabase.getDatabase(ctx);

        mMovieiDao = db.movieDao();
        mTvshowDao = db.tvshowDao();
    }

    public void deleteAllTable() {
        db.clearAllTables();
    }

    /**
     * Data Nutrisi
     */


    public void insertMovie(Movie movie) {
        new insertMovieAsyncTask(mMovieiDao).execute(movie);
    }

    private static class insertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertMovieAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void insertTvshow(Tvshow tv) {
        new insertTvshowAsyncTask(mTvshowDao).execute(tv);
    }

    private static class insertTvshowAsyncTask extends AsyncTask<Tvshow, Void, Void> {

        private TvshowDao mAsyncTaskDao;

        insertTvshowAsyncTask(TvshowDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Tvshow... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
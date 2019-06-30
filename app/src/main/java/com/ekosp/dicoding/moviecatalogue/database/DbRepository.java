package com.ekosp.dicoding.moviecatalogue.database;

import android.content.Context;
import android.os.AsyncTask;

import com.ekosp.dicoding.moviecatalogue.database.dao.MovieDao;
import com.ekosp.dicoding.moviecatalogue.database.dao.TvshowDao;
import com.ekosp.dicoding.moviecatalogue.database.entity.Movie;
import com.ekosp.dicoding.moviecatalogue.database.entity.Tvshow;

import java.util.List;

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


    public Long insertMovie(Movie movie) {
//        new insertMovieAsyncTask(mMovieiDao).execute(movie);
        return mMovieiDao.insert(movie);
    }

    private static class insertMovieAsyncTask extends AsyncTask<Movie, Void, Long> {

        private MovieDao mAsyncTaskDao;

        insertMovieAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Movie... params) {
            return mAsyncTaskDao.insert(params[0]);
        }
    }

    public List<Movie> getAllMovie(){
        return mMovieiDao.getAllMovie();
    }

    public Integer getMoviewById(Integer id){
        return mMovieiDao.getMovieById(id);
    }

    public Integer deleteMovieById(Integer id){
        return mMovieiDao.deleteMovieById(id);
    }

    public Long insertTvshow(Tvshow tv) {
//        return new insertTvshowAsyncTask(mTvshowDao).execute(tv);
        return mTvshowDao.insert(tv);

    }

    private static class insertTvshowAsyncTask extends AsyncTask<Tvshow, Void, Long> {

        private TvshowDao mAsyncTaskDao;

        insertTvshowAsyncTask(TvshowDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Tvshow... params) {
           return mAsyncTaskDao.insert(params[0]);
        }
    }

    public List<Tvshow> getAllTvshow(){
        return mTvshowDao.getAllTvshow();
    }

    public Integer getTvshowById(Integer id){
        return  mTvshowDao.getTvshowById(id);
    }

    public Integer deleteTvshowById(Integer id){
         return mTvshowDao.deleteTvshowById(id);
    }

}

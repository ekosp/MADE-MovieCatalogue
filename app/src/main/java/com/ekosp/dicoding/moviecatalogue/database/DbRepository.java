package com.ekosp.dicoding.moviecatalogue.database;

import android.content.Context;

import com.ekosp.dicoding.moviecatalogue.database.dao.MovieDao;
import com.ekosp.dicoding.moviecatalogue.database.dao.TvshowDao;
import com.ekosp.dicoding.moviecatalogue.database.entity.Movie;
import com.ekosp.dicoding.moviecatalogue.database.entity.Tvshow;

import java.util.List;

/**
 * Created by Eko S.P on 27/06/2019.
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

    /**
     * Data Nutrisi
     */


    public Long insertMovie(Movie movie) {
        return mMovieiDao.insert(movie);
    }

    public List<Movie> getAllMovie() {
        return mMovieiDao.getAllMovie();
    }

    public Integer getMoviewById(Integer id) {
        return mMovieiDao.getMovieById(id);
    }

    public Integer deleteMovieById(Integer id) {
        return mMovieiDao.deleteMovieById(id);
    }

    /**
     * @param tv model
     * @return long number of inserted object
     */
    public Long insertTvshow(Tvshow tv) {
        return mTvshowDao.insert(tv);

    }

    /**
     * @return list tv show
     */
    public List<Tvshow> getAllTvshow() {
        return mTvshowDao.getAllTvshow();
    }

    public Integer getTvshowById(Integer id) {
        return mTvshowDao.getTvshowById(id);
    }

    public Integer deleteTvshowById(Integer id) {
        return mTvshowDao.deleteTvshowById(id);
    }

}

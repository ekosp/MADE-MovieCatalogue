package com.ekosp.dicoding.moviecatalogue.database;

import android.content.Context;

import com.ekosp.dicoding.moviecatalogue.database.dao.MovieDao;
import com.ekosp.dicoding.moviecatalogue.database.dao.TvshowDao;
import com.ekosp.dicoding.moviecatalogue.model.Movie;
import com.ekosp.dicoding.moviecatalogue.model.TvShow;

import java.util.List;

/**
 * Created by Eko S.P on 27/06/2019.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

public class DbRepository {

    private final MovieDao mMovieiDao;
    private final TvshowDao mTvshowDao;

    public DbRepository(Context ctx) {

        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(ctx);

        mMovieiDao = db.movieDao();
        mTvshowDao = db.tvshowDao();
    }

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

    public Long insertTvshow(TvShow tv) {
        return mTvshowDao.insert(tv);
    }

    public List<TvShow> getAllTvshow() {
        return mTvshowDao.getAllTvshow();
    }

    public Integer getTvshowById(Integer id) {
        return mTvshowDao.getTvshowById(id);
    }

    public Integer deleteTvshowById(Integer id) {
        return mTvshowDao.deleteTvshowById(id);
    }

}

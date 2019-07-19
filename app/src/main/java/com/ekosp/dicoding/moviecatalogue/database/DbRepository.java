package com.ekosp.dicoding.moviecatalogue.database;

import android.content.Context;
import android.os.AsyncTask;

import com.ekosp.dicoding.moviecatalogue.database.dao.MovieDao;
import com.ekosp.dicoding.moviecatalogue.database.dao.TvshowDao;
import com.ekosp.dicoding.moviecatalogue.database.entity.NewMovie;
import com.ekosp.dicoding.moviecatalogue.database.entity.NewTvShow;

import java.util.List;

/**
 * Created by Eko S.P on 27/06/2019.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

public class DbRepository {

    private MovieDao mMovieiDao;
    private TvshowDao mTvshowDao;

    public DbRepository(Context ctx) {

        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(ctx);

        mMovieiDao = db.movieDao();
        mTvshowDao = db.tvshowDao();
    }


    public Long insertMovie(NewMovie movie) {
        return mMovieiDao.insert(movie);
    }

    public List<NewMovie> getAllMovie(){
        return mMovieiDao.getAllMovie();
    }

    public Integer getMoviewById(Integer id){
        return mMovieiDao.getMovieById(id);
    }

    public Integer deleteMovieById(Integer id){
        return mMovieiDao.deleteMovieById(id);
    }

    public Long insertTvshow(NewTvShow tv) {
        return mTvshowDao.insert(tv);
    }

    public List<NewTvShow> getAllTvshow(){
        return mTvshowDao.getAllTvshow();
    }

    public Integer getTvshowById(Integer id){
        return  mTvshowDao.getTvshowById(id);
    }

    public Integer deleteTvshowById(Integer id){
         return mTvshowDao.deleteTvshowById(id);
    }

}

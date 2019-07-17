package com.ekosp.dicoding.moviecatalogue.helper;

import com.ekosp.dicoding.moviecatalogue.api.MovieService;
import com.ekosp.dicoding.moviecatalogue.database.DbRepository;
import com.ekosp.dicoding.moviecatalogue.database.entity.Movie;
import com.ekosp.dicoding.moviecatalogue.model.MovieListResponse;

import io.reactivex.Observable;

/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class Repository {

    private MovieService movieService;
    private DbRepository dbRepository;

    public Repository(MovieService movieService, DbRepository dbRepository) {
        this.movieService = movieService;
        this.dbRepository = dbRepository;
    }

    /*
     * method to call login api
     * */
    public Observable<MovieListResponse> executeLogin() {
        return movieService.getPopularMovies(GlobalVar.moviedb_apikey);
    }

}

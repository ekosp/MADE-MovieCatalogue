package com.ekosp.dicoding.moviecatalogue.helper;

import com.ekosp.dicoding.moviecatalogue.api.MovieService;
import com.ekosp.dicoding.moviecatalogue.database.DbRepository;
import com.ekosp.dicoding.moviecatalogue.model.MovieListResponse;

import io.reactivex.Observable;

/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class Repository {

    private final MovieService movieService;

    public Repository(MovieService movieService, DbRepository dbRepository) {
        this.movieService = movieService;
        DbRepository dbRepository1 = dbRepository;
    }

    /*
     * method to call login api
     * */
    public Observable<MovieListResponse> executeLogin() {
        return movieService.getPopularMovies(GlobalVar.moviedb_apikey);
    }

}

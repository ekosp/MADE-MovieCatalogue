package com.ekosp.dicoding.moviecatalogue.helper;

import com.ekosp.dicoding.moviecatalogue.network.MovieService;
import com.ekosp.dicoding.moviecatalogue.database.DbRepository;


/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class Repository {

    public Repository(MovieService movieService, DbRepository dbRepository) {
        MovieService movieService1 = movieService;
        DbRepository dbRepository1 = dbRepository;
    }

//    List<NewMovie> getMovies(){
//        Call<MovieListResponse> call = movieService.getPopularMovies().e;
//        call.enqueue(new Callback<MovieListResponse>() {
//            @Override
//            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieListResponse> call, Throwable t) {
//
//            }
//
//        });
//    }

}

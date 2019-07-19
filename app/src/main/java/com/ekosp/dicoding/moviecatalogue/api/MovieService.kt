package com.ekosp.dicoding.moviecatalogue.api

import com.ekosp.dicoding.moviecatalogue.database.entity.NewMovie
import com.ekosp.dicoding.moviecatalogue.model.MovieListResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by Eko.Purnomo on 2019-07-07.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

interface MovieService {

    @GET("3/discover/movie")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<MovieListResponse>

    @GET("search/movie")
    fun searchMovies(@QueryMap options: Map<String, String>): Call<MovieListResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") id: String, @Query("api_key") apiKey: String): Call<NewMovie>
}
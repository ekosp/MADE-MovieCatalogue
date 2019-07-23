package com.ekosp.dicoding.moviecatalogue.network

import com.ekosp.dicoding.moviecatalogue.model.MovieListResponse
import com.ekosp.dicoding.moviecatalogue.model.TvshowListResponse
import com.ekosp.dicoding.moviecatalogue.model.UpcomingMovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Eko.Purnomo on 2019-07-07.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

interface MovieService {

    @GET("discover/movie")
    fun getPopularMovies(): Call<MovieListResponse>

    @GET("discover/tv")
    fun getPopularTvshow(): Call<TvshowListResponse>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String): Call<MovieListResponse>

    @GET("search/tv")
    fun searchTvShow(@Query("query") query: String): Call<TvshowListResponse>

    @GET("movie/upcoming")
    fun getUpcommingMovie(): Call<UpcomingMovieListResponse>
}
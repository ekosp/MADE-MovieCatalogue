package com.ekosp.dicoding.moviecatalogue.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Eko.Purnomo on 2019-07-07.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

class ApiClient {
    companion object {
        fun getClient() : MovieService {
            val baseUrl = "https://api.themoviedb.org/3/"
            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(MovieService::class.java)
        }
    }
}
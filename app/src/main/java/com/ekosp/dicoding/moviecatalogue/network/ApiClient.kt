package com.ekosp.dicoding.moviecatalogue.network

//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Eko.Purnomo on 2019-07-07.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

class ApiClient {
    companion object {
        fun getClient(): MovieService {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = HeaderInterceptor()

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(headerInterceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(GlobalVar.baseUrl)
                    .build()

            return retrofit.create(MovieService::class.java)
        }
    }
}
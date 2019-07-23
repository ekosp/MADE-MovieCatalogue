package com.ekosp.dicoding.moviecatalogue.di;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.ekosp.dicoding.moviecatalogue.database.DbRepository;
import com.ekosp.dicoding.moviecatalogue.helper.Repository;
import com.ekosp.dicoding.moviecatalogue.network.ApiClient;
import com.ekosp.dicoding.moviecatalogue.network.MovieService;
import com.ekosp.dicoding.moviecatalogue.helper.ViewModelFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

@Module(includes = {AppModule.class})
public class UtilsModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    @Singleton
    MovieService getApiCallInterface() {
        return ApiClient.Companion.getClient();
    }

    @Provides
    @Singleton
    DbRepository getDbRepository(Context context) {
        return new DbRepository(context);
    }

    @Provides
    @Singleton
    OkHttpClient getRequestHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .build();
            return chain.proceed(request);
        })
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);

        return httpClient.build();
    }

    @Provides
    @Singleton
    Repository getRepository(MovieService movieService, DbRepository dbRepository) {
        return new Repository(movieService, dbRepository);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(Repository myRepository) {
        return new ViewModelFactory(myRepository);
    }
}

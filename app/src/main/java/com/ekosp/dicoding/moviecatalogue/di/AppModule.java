package com.ekosp.dicoding.moviecatalogue.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }
}

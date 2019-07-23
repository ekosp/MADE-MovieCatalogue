package com.ekosp.dicoding.moviecatalogue;

import android.app.Application;
import android.content.Context;

import com.ekosp.dicoding.moviecatalogue.di.AppComponent;
import com.ekosp.dicoding.moviecatalogue.di.AppModule;
import com.ekosp.dicoding.moviecatalogue.di.DaggerAppComponent;
import com.ekosp.dicoding.moviecatalogue.di.UtilsModule;

/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MyApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}

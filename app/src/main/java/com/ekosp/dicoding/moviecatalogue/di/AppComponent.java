package com.ekosp.dicoding.moviecatalogue.di;



import com.ekosp.dicoding.moviecatalogue.fragment.movielist.MovieListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ${Saquib} on 03-05-2018.
 */

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(MovieListFragment movieListFragment);

}

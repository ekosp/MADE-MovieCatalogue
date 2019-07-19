package com.ekosp.dicoding.moviecatalogue.di;



import com.ekosp.dicoding.moviecatalogue.fragment.movielist.MovieListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(MovieListFragment movieListFragment);

}

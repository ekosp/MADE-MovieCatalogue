package com.ekosp.dicoding.moviecatalogue.helper;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ekosp.dicoding.moviecatalogue.fragment.movielist.MovieListViewModel;

import javax.inject.Inject;

/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieListViewModel.class)) {
            return (T) new MovieListViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown class name");
    }
}

package com.ekosp.dicoding.moviecatalogue.helper;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ekosp.dicoding.moviecatalogue.fragment.movielist.MovieListViewModel;

import javax.inject.Inject;

/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

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

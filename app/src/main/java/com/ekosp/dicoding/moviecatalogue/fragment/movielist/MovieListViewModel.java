package com.ekosp.dicoding.moviecatalogue.fragment.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ekosp.dicoding.moviecatalogue.api.ApiResponse;
import com.ekosp.dicoding.moviecatalogue.database.entity.Movie;
import com.ekosp.dicoding.moviecatalogue.helper.Repository;
import com.ekosp.dicoding.moviecatalogue.model.MovieListResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MovieListViewModel extends ViewModel {

    private Repository repository;
    private CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<MovieListResponse> responseLiveData = new MutableLiveData<>();


    public MovieListViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<MovieListResponse> movieResponse() {

//        if (responseLiveData == null){
//            responseLiveData = new MutableLiveData<>();
//            hitMovieApi();
//        }

        return responseLiveData;
    }

    /*
     * method to call normal login api with $(mobileNumber + password)
     * */
    public void hitMovieApi() {

        if (responseLiveData == null) {

            disposables.add(repository.executeLogin()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe((d) -> {
                    })
                    .subscribe(
                            result -> responseLiveData.setValue(result),
                            throwable -> {
                            }
                    ));
        }

    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

}


package com.ekosp.dicoding.moviecatalogue.fragment.movielist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ekosp.dicoding.moviecatalogue.helper.Repository;
import com.ekosp.dicoding.moviecatalogue.model.MovieListResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MovieListViewModel extends ViewModel {

    private final Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<MovieListResponse> responseLiveData = new MutableLiveData<>();


    public MovieListViewModel(Repository repository) {
        this.repository = repository;
    }

// --Commented out by Inspection START (2019-07-22 14:09):
//    public MutableLiveData<MovieListResponse> movieResponse() {
//
////        if (responseLiveData == null){
////            responseLiveData = new MutableLiveData<>();
////            hitMovieApi();
////        }
//
//        return responseLiveData;
//    }
// --Commented out by Inspection STOP (2019-07-22 14:09)

// --Commented out by Inspection START (2019-07-22 14:09):
//    /*
//     * method to call normal login api with $(mobileNumber + password)
//     * */
//    public void hitMovieApi() {
//
//        if (responseLiveData == null) {
//
//            disposables.add(repository.executeLogin()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnSubscribe((d) -> {
//                    })
//                    .subscribe(
//                            result -> responseLiveData.setValue(result),
//                            throwable -> {
//                            }
//                    ));
//        }
//
//    }
// --Commented out by Inspection STOP (2019-07-22 14:09)

    @Override
    protected void onCleared() {
        disposables.clear();
    }

}


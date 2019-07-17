package com.ekosp.dicoding.moviecatalogue.api;

import com.google.gson.JsonElement;

import io.reactivex.Observable;

/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class ApiRepository {

    private MovieService apiCallInterface;

    public ApiRepository(MovieService apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call login api
     * */
//    public Observable<JsonElement> executeLogin(String mobileNumber, String password) {
//        return apiCallInterface.login(mobileNumber, password);
//    }

}

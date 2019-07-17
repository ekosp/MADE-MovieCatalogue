package com.ekosp.dicoding.moviecatalogue.api;

import com.google.gson.JsonElement;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.ekosp.dicoding.moviecatalogue.api.Status.ERROR;
import static com.ekosp.dicoding.moviecatalogue.api.Status.LOADING;
import static com.ekosp.dicoding.moviecatalogue.api.Status.SUCCESS;


/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class ApiResponse {

    public final Status status;

    @Nullable
    public final JsonElement data;

    @Nullable
    public final Throwable error;

    private ApiResponse(Status status, @Nullable JsonElement data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse loading() {
        return new ApiResponse(LOADING, null, null);
    }

    public static ApiResponse success(@NonNull JsonElement data) {
        return new ApiResponse(SUCCESS, data, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(ERROR, null, error);
    }

}

package com.ekosp.dicoding.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eko.Purnomo on 2019-06-25.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class UpcomingMovieListResponse implements Parcelable {

    @SerializedName("results")
    @Expose
    private List<Movie> results;

    private UpcomingMovieListResponse(Parcel in) {
        results = in.createTypedArrayList(Movie.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UpcomingMovieListResponse> CREATOR = new Creator<UpcomingMovieListResponse>() {
        @Override
        public UpcomingMovieListResponse createFromParcel(Parcel in) {
            return new UpcomingMovieListResponse(in);
        }

        @Override
        public UpcomingMovieListResponse[] newArray(int size) {
            return new UpcomingMovieListResponse[size];
        }
    };

    public List<Movie> getResults() {
        return results;
    }

}

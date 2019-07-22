package com.ekosp.dicoding.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ekosp.dicoding.moviecatalogue.database.entity.NewTvShow;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eko.Purnomo on 2019-06-25.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class TvshowListResponse implements Parcelable {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<NewTvShow> results;

    private TvshowListResponse(Parcel in) {
        if (in.readByte() == 0) {
            page = null;
        } else {
            page = in.readInt();
        }
        if (in.readByte() == 0) {
            totalResults = null;
        } else {
            totalResults = in.readInt();
        }
        if (in.readByte() == 0) {
            totalPages = null;
        } else {
            totalPages = in.readInt();
        }
        results = in.createTypedArrayList(NewTvShow.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (page == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(page);
        }
        if (totalResults == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalResults);
        }
        if (totalPages == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalPages);
        }
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TvshowListResponse> CREATOR = new Creator<TvshowListResponse>() {
        @Override
        public TvshowListResponse createFromParcel(Parcel in) {
            return new TvshowListResponse(in);
        }

        @Override
        public TvshowListResponse[] newArray(int size) {
            return new TvshowListResponse[size];
        }
    };

    public List<NewTvShow> getResults() {
        return results;
    }

}

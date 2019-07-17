package com.ekosp.dicoding.moviecatalogue.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eko S.P on 27/10/2018.
 * contact ekosetyopurnomo@gmail.com
 */

@Entity(tableName = GlobalVar.TABEL_TVSHOW)
public class Tvshow implements Parcelable {

    @SerializedName("id")
    @PrimaryKey()
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("name")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    private String overview;

    @SerializedName("first_air_date")
    @ColumnInfo(name = "firstAiringDate")
    private String firstAiringDate;

    @SerializedName("vote_average")
    @ColumnInfo(name = "score")
    private Float voteAverage;

    @SerializedName("poster_path")
    @ColumnInfo(name = "coverUrl")
    private String posterPath;

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop")
    private String backdropPath;

    public Tvshow(Integer id, String title, String overview, String firstAiringDate, Float voteAverage, String posterPath, String backdropPath) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.firstAiringDate = firstAiringDate;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    protected Tvshow(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        overview = in.readString();
        firstAiringDate = in.readString();
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readFloat();
        }
        posterPath = in.readString();
        backdropPath = in.readString();
    }

    public Tvshow() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(firstAiringDate);
        if (voteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(voteAverage);
        }
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tvshow> CREATOR = new Creator<Tvshow>() {
        @Override
        public Tvshow createFromParcel(Parcel in) {
            return new Tvshow(in);
        }

        @Override
        public Tvshow[] newArray(int size) {
            return new Tvshow[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAiringDate() {
        return firstAiringDate;
    }

    public void setFirstAiringDate(String firstAiringDate) {
        this.firstAiringDate = firstAiringDate;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
}

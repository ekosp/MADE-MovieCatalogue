package com.ekosp.dicoding.moviecatalogue.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;

/**
 * Created by Eko S.P on 27/10/2018.
 * contact ekosetyopurnomo@gmail.com
 */

@Entity(tableName = GlobalVar.TABEL_MOVIE)
public class Movie {

    @PrimaryKey()
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "releaseDate")
    private String releaseDate;

    @ColumnInfo(name = "score")
    private Float score;

    @ColumnInfo(name = "coverUrl")
    private String coverUrl;

    @ColumnInfo(name = "backdrop")
    private String backdrop;

    public Movie(Integer id, String title, String overview, String releaseDate, Float score, String coverUrl, String backdrop) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.score = score;
        this.coverUrl = coverUrl;
        this.backdrop = backdrop;
    }

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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }
}

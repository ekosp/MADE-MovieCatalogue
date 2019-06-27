package com.ekosp.dicoding.moviecatalogue.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;

/**
 * Created by Eko S.P on 27/10/2018.
 * contact ekosetyopurnomo@gmail.com
 */

@Entity(tableName = GlobalVar.TABEL_TVSHOW)
public class Tvshow {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "firstAiringDate")
    private String firstAiringDate;

    @ColumnInfo(name = "score")
    private String score;

    @ColumnInfo(name = "coverUrl")
    private String coverUrl;

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}

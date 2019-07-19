package com.ekosp.dicoding.moviecatalogue.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ekosp.dicoding.moviecatalogue.database.entity.NewMovie;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;

import java.util.List;

/**
 * Created by Eko S.P on 27/10/2018.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(NewMovie movie);

    @Query("DELETE FROM " + GlobalVar.TABEL_MOVIE+" WHERE id= :id")
    int deleteMovieById(Integer id);

    @Query("SELECT * FROM "+ GlobalVar.TABEL_MOVIE+" WHERE id= :id")
    int getMovieById (Integer id);

    @Query("SELECT * from " + GlobalVar.TABEL_MOVIE)
    List<NewMovie> getAllMovie();

}

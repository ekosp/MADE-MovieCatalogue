package com.ekosp.dicoding.moviecatalogue.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ekosp.dicoding.moviecatalogue.database.entity.Tvshow;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;

import java.util.List;

/**
 * Created by Eko S.P on 27/10/2018.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

@Dao
public interface TvshowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Tvshow tvshow);

    @Query("DELETE FROM " + GlobalVar.TABEL_TVSHOW+" WHERE id= :id")
    int deleteTvshowById(Integer id);

    @Query("SELECT * FROM "+ GlobalVar.TABEL_TVSHOW+" WHERE id= :id")
    int getTvshowById (Integer id);

    @Query("SELECT * from " + GlobalVar.TABEL_TVSHOW)
    List<Tvshow> getAllTvshow();


}

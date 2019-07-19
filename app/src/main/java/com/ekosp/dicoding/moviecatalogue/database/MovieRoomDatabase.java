package com.ekosp.dicoding.moviecatalogue.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ekosp.dicoding.moviecatalogue.database.dao.MovieDao;
import com.ekosp.dicoding.moviecatalogue.database.dao.TvshowDao;
import com.ekosp.dicoding.moviecatalogue.database.entity.NewMovie;
import com.ekosp.dicoding.moviecatalogue.database.entity.NewTvShow;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;

/**
 * Created by Eko S.P on 27/05/2019.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

@Database(entities = {NewMovie.class, NewTvShow.class}, version = 4, exportSchema = false)
abstract class MovieRoomDatabase extends RoomDatabase {

    abstract MovieDao movieDao();

    abstract TvshowDao tvshowDao();

    private static MovieRoomDatabase INSTANCE;

    static MovieRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (MovieRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieRoomDatabase.class, GlobalVar.DB_NAME)
                            // not recomended on production apps
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    public static void destroyInstance() {
//        INSTANCE = null;
//    }

}
package com.ekosp.dicoding.moviecatalogue.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ekosp.dicoding.moviecatalogue.database.dao.MovieDao;
import com.ekosp.dicoding.moviecatalogue.database.dao.TvshowDao;
import com.ekosp.dicoding.moviecatalogue.model.Movie;
import com.ekosp.dicoding.moviecatalogue.model.TvShow;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;

/**
 * Created by Eko S.P on 27/05/2019.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

@Database(entities = {Movie.class, TvShow.class}, version = 4, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public abstract TvshowDao tvshowDao();

    private static MovieRoomDatabase INSTANCE;

    public static synchronized MovieRoomDatabase getDatabase(Context context) {
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

}
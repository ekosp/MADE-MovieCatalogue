package com.ekosp.dicoding.moviecatalogue.helper;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import com.ekosp.dicoding.moviecatalogue.database.DbRepository;
import com.ekosp.dicoding.moviecatalogue.model.Movie;
import com.ekosp.dicoding.moviecatalogue.network.ApiClient;
import com.ekosp.dicoding.moviecatalogue.network.MovieService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Eko.Purnomo on 2019-06-26.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MovieTaskLoader extends AsyncTaskLoader<List<Movie>> {

    private List<Movie> mData;
    private boolean mHasResult = false;
    private final boolean mFavorite;
    private final String stringQuery;
    private final DbRepository mRepository;

    public MovieTaskLoader(final Context context, final Boolean isFavorite, final String query) {
        super(context);
        this.mRepository = new DbRepository(context);
        this.mFavorite = isFavorite;
        this.stringQuery = query;
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
        } else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final List<Movie> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            mData = null;
            mHasResult = false;
        }
    }

    @Override
    public List<Movie> loadInBackground() {

        final List<Movie> movieItemList = new ArrayList<>();
        final MovieService service = ApiClient.Companion.getClient();

        if (mFavorite) {
            movieItemList.addAll(mRepository.getAllMovie());
        } else {
            try {
                if (!stringQuery.isEmpty()){ // if search
                    movieItemList.addAll(Objects.requireNonNull(service.searchMovies(stringQuery).execute().body()).getResults());

                } else { // normal discovery
                    movieItemList.addAll(Objects.requireNonNull(service.getPopularMovies().execute().body()).getResults());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return movieItemList;
    }

}
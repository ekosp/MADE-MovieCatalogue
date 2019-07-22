package com.ekosp.dicoding.moviecatalogue.helper;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import com.ekosp.dicoding.moviecatalogue.database.DbRepository;
import com.ekosp.dicoding.moviecatalogue.database.entity.NewMovie;
import com.ekosp.dicoding.moviecatalogue.model.MovieListResponse;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Eko.Purnomo on 2019-06-26.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MovieTaskLoader extends AsyncTaskLoader<List<NewMovie>> {

    private List<NewMovie> mData;
    private boolean mHasResult = false;
    private final boolean mFavorite;
    private final String stringQuery;
    private final DialogHelper dialogHelper;
    private final DbRepository mRepository;

    public MovieTaskLoader(final Context context, final Boolean isFavorite, final String query) {
        super(context);
        this.dialogHelper = new DialogHelper(context);
        this.mRepository = new DbRepository(context);
        this.mFavorite = isFavorite;
        this.stringQuery = query;
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
            dialogHelper.showProgressDialog();
        } else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final List<NewMovie> data) {
        mData = data;
        mHasResult = true;
        dialogHelper.dismissProgressDialog();
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
    public List<NewMovie> loadInBackground() {


        final List<NewMovie> movieItemList = new ArrayList<>();
        SyncHttpClient client = new SyncHttpClient();

        String MOVIE_URL;
        if (!stringQuery.isEmpty())
            MOVIE_URL = "https://api.themoviedb.org/3/search/movie?api_key=" +
                    GlobalVar.moviedb_apikey + "&language=en-US&query=" + stringQuery;
        else
            MOVIE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=" +
                    GlobalVar.moviedb_apikey + "&language=en-US";

        if (mFavorite) {
            movieItemList.addAll(mRepository.getAllMovie());
        } else {

            client.get(MOVIE_URL, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    super.onStart();
                    setUseSynchronousMode(true);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String result = new String(responseBody);
                        Gson gson = new Gson();
                        MovieListResponse response = gson.fromJson(result, MovieListResponse.class);
                        movieItemList.addAll(response.getResults());

                    } catch (Exception e) {
                        //Jika terjadi error pada saat parsing maka akan masuk ke catch()
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    //Jika response gagal maka , do nothing
                }
            });
        }

        return movieItemList;
    }

}
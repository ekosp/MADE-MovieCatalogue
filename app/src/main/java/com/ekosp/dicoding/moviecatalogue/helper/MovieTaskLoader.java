package com.ekosp.dicoding.moviecatalogue.helper;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import com.ekosp.dicoding.moviecatalogue.model.Movie;
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

public class MovieTaskLoader extends AsyncTaskLoader<List<Movie>> {

    private List<Movie> mData;
    private boolean mHasResult = false;
    private DialogHelper dialogHelper;

    public MovieTaskLoader(final Context context) {
        super(context);
        dialogHelper = new DialogHelper(context);
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
    public void deliverResult(final List<Movie> data) {
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
    public List<Movie> loadInBackground() {

        SyncHttpClient client = new SyncHttpClient();
        final List<Movie> movieItemList = new ArrayList<>();
        String MOVIE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=" + GlobalVar.moviedb_apikey + "&language=en-US";

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

        return movieItemList;
    }

}
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
    private boolean mFavorite;
    private DialogHelper dialogHelper;
    private DbRepository mRepository;


    public MovieTaskLoader(final Context context, final Boolean isFavorite) {
        super(context);
        dialogHelper = new DialogHelper(context);
        mRepository = new DbRepository(context);
        mFavorite = isFavorite;
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
        String MOVIE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=" + GlobalVar.moviedb_apikey + "&language=en-US";


        if (mFavorite){
            // load fata from db
//            List<com.ekosp.dicoding.moviecatalogue.database.entity.Movie> list = mRepository.getAllMovie();
//            for (com.ekosp.dicoding.moviecatalogue.database.entity.Movie m: list){
//                Movie movie = new Movie();
//                movie.setId(m.getId());
//                movie.setTitle(m.getTitle());
//                movie.setReleaseDate(m.getReleaseDate());
//                movie.setOverview(m.getOverview());
//                movie.setVoteAverage(m.getScore());
//                movie.setPosterPath(m.getCoverUrl());
//                movie.setBackdropPath(m.getBackdrop());
//                movieItemList.add(movie);
//            }
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
        }); }

        return movieItemList;
    }

}
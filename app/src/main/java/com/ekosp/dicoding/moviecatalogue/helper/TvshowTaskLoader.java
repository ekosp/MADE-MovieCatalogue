package com.ekosp.dicoding.moviecatalogue.helper;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import com.ekosp.dicoding.moviecatalogue.database.DbRepository;
import com.ekosp.dicoding.moviecatalogue.model.TvShow;
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

public class TvshowTaskLoader extends AsyncTaskLoader<List<TvShow>> {

    private List<TvShow> mData;
    private boolean mHasResult = false;
    private final boolean mFavorite;
    private final DialogHelper dialogHelper;
    private final DbRepository mRepository;
    private final String stringQuery;


    public TvshowTaskLoader(final Context context, final Boolean isFavorite, final String query) {
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
    public void deliverResult(final List<TvShow> data) {
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
    public List<TvShow> loadInBackground() {

        final List<TvShow> tvshowItemList = new ArrayList<>();
        final MovieService service = ApiClient.Companion.getClient();

        if (mFavorite) {
            tvshowItemList.addAll(mRepository.getAllTvshow());
        } else {

            try {
                if (!stringQuery.isEmpty()){ // if search
                    tvshowItemList.addAll(Objects.requireNonNull(service.searchTvShow(stringQuery).execute().body()).getResults());

                } else { // normal discovery
                    tvshowItemList.addAll(Objects.requireNonNull(service.getPopularTvshow().execute().body()).getResults());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tvshowItemList;
    }

}
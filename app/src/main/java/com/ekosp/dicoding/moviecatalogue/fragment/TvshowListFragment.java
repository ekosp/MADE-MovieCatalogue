package com.ekosp.dicoding.moviecatalogue.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.adapter.MoviesAdapter;
import com.ekosp.dicoding.moviecatalogue.adapter.TvshowAdapter;
import com.ekosp.dicoding.moviecatalogue.helper.DialogHelper;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;
import com.ekosp.dicoding.moviecatalogue.helper.MovieTaskLoader;
import com.ekosp.dicoding.moviecatalogue.helper.TvshowTaskLoader;
import com.ekosp.dicoding.moviecatalogue.model.Movie;
import com.ekosp.dicoding.moviecatalogue.model.Tvshow;
import com.ekosp.dicoding.moviecatalogue.model.TvshowListResponse;
import com.ekosp.dicoding.moviecatalogue.view.BaseFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-23.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class TvshowListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<List<Tvshow>> {

    private List<Tvshow> tvshows = new ArrayList<>();
    private TvshowAdapter adapter;

    private static final int MOVIE_LOADER = 21;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        adapter = new TvshowAdapter(tvshows, getActivity());
        adapter.notifyDataSetChanged();

//        getDialogHelper().initProgressDialog();
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setAdapter(adapter);

        getLoaderManager().initLoader(MOVIE_LOADER, null, this);

        return view;
    }


    @NonNull
    @Override
    public Loader<List<Tvshow>> onCreateLoader(int id, @Nullable Bundle args) {
//        getDialogHelper().showProgressDialog();
        return new TvshowTaskLoader(getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Tvshow>> loader, List<Tvshow> data) {
        adapter.setData(data);
//        getDialogHelper().dismissProgressDialog();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Tvshow>> loader) {
        adapter.setData(null);
    }



}
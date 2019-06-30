package com.ekosp.dicoding.moviecatalogue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.adapter.TvshowAdapter;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;
import com.ekosp.dicoding.moviecatalogue.helper.TvshowTaskLoader;
import com.ekosp.dicoding.moviecatalogue.model.Tvshow;
import com.ekosp.dicoding.moviecatalogue.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

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

    private static final int TVSHOW_LOADER = 51;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    public static TvshowListFragment newInstance(Boolean isFavorite) {
        TvshowListFragment fragment = new TvshowListFragment();

        Bundle args = new Bundle();
        args.putBoolean(GlobalVar.PARAM_IS_FAVORITE, isFavorite);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        adapter = new TvshowAdapter(tvshows, getActivity());
        adapter.notifyDataSetChanged();

        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setAdapter(adapter);

        getActivity().getSupportLoaderManager().initLoader(TVSHOW_LOADER, getArguments(), this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments().getBoolean(GlobalVar.PARAM_IS_FAVORITE))
            getActivity().getSupportLoaderManager().restartLoader(TVSHOW_LOADER, getArguments(), this);

    }


    @NonNull
    @Override
    public Loader<List<Tvshow>> onCreateLoader(int id, @Nullable Bundle args) {
        boolean isFavorite = args.getBoolean(GlobalVar.PARAM_IS_FAVORITE);
        return new TvshowTaskLoader(getActivity(), isFavorite);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Tvshow>> loader, List<Tvshow> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Tvshow>> loader) {
        adapter.setData(null);
    }


}
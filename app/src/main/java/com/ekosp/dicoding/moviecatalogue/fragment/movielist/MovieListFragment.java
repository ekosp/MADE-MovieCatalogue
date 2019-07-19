package com.ekosp.dicoding.moviecatalogue.fragment.movielist;

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
import com.ekosp.dicoding.moviecatalogue.adapter.MoviesAdapter;
import com.ekosp.dicoding.moviecatalogue.base.BaseFragment;
import com.ekosp.dicoding.moviecatalogue.database.entity.NewMovie;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;
import com.ekosp.dicoding.moviecatalogue.helper.MovieTaskLoader;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-23.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MovieListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<List<NewMovie>> {

    private List<NewMovie> movies = new ArrayList<>();
    private MoviesAdapter adapter;

    private static final int MOVIE_LOADER = 21;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    public static MovieListFragment newInstance(Boolean isFavorite) {
        MovieListFragment fragment = new MovieListFragment();

        Bundle args = new Bundle();
        args.putBoolean(GlobalVar.PARAM_IS_FAVORITE, isFavorite);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        adapter = new MoviesAdapter(movies, getActivity());
        adapter.notifyDataSetChanged();

        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setAdapter(adapter);

        Objects.requireNonNull(getActivity()).getSupportLoaderManager().initLoader(MOVIE_LOADER, getArguments(), this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null && getArguments().getBoolean(GlobalVar.PARAM_IS_FAVORITE))
            Objects.requireNonNull(getActivity()).getSupportLoaderManager().restartLoader(MOVIE_LOADER, getArguments(), this);
    }

    @NonNull
    @Override
    public Loader<List<NewMovie>> onCreateLoader(int id, @Nullable Bundle args) {

        boolean isFavorite = args != null && args.getBoolean(GlobalVar.PARAM_IS_FAVORITE);
        return new MovieTaskLoader(getActivity(), isFavorite);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewMovie>> loader, List<NewMovie> data) {
        adapter.setData(data);
        dismissLoading();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<NewMovie>> loader) {
        adapter.setData(null);
    }

}
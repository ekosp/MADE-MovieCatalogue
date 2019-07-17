package com.ekosp.dicoding.moviecatalogue.fragment.movielist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekosp.dicoding.moviecatalogue.MyApplication;
import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.adapter.MoviesAdapter;
import com.ekosp.dicoding.moviecatalogue.database.entity.Movie;
import com.ekosp.dicoding.moviecatalogue.helper.Constant;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;
import com.ekosp.dicoding.moviecatalogue.helper.MovieTaskLoader;
import com.ekosp.dicoding.moviecatalogue.helper.ViewModelFactory;
import com.ekosp.dicoding.moviecatalogue.base.BaseFragment;
import com.ekosp.dicoding.moviecatalogue.model.MovieListResponse;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-23.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MovieListFragment extends BaseFragment {

    private List<Movie> movies = new ArrayList<>();
    private MoviesAdapter adapter;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    Context context;

    MovieListViewModel viewModel;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MyApplication) getActivity().getApplicationContext()).getAppComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel.class);

        viewModel.movieResponse().observe(this, this::consumeResponse);

    }

    /*
     * method to handle response
     * */
    private void consumeResponse(MovieListResponse response) {
        adapter.setData(response.getResults());
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        adapter = new MoviesAdapter(movies, context);
//        adapter.notifyDataSetChanged();

        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setAdapter(adapter);


        if (!Constant.checkInternetConnection(context)) {
            Toast.makeText(context, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
        else
        viewModel.hitMovieApi();


        return view;
    }

}
package com.ekosp.dicoding.moviecatalogue.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.adapter.MoviesAdapter;
import com.ekosp.dicoding.moviecatalogue.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-23.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MovieListFragment extends Fragment {

    private ArrayList<Movie> movies;
    private String[] dataTitle;
    private String[] dataOverview;
    private String[] dataReleaseDate;
    private int[] dataScore;
    private TypedArray dataCover;
    private MoviesAdapter adapter;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        prepare();
        addItem();


        return  view;
    }

    private void addItem() {
        movies = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setCover(dataCover.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setOverview(dataOverview[i]);
            movie.setScore(dataScore[i]);
            movie.setReleaseDate(dataReleaseDate[i]);
            movies.add(movie);
        }

        adapter = new MoviesAdapter(movies, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvMovies.setLayoutManager(mLayoutManager);
        rvMovies.setItemAnimator(new DefaultItemAnimator());
        rvMovies.setAdapter(adapter);
    }

    private void prepare() {
        dataTitle = getResources().getStringArray(R.array.data_name);
        dataReleaseDate = getResources().getStringArray(R.array.data_release_date);
        dataOverview = getResources().getStringArray(R.array.data_description);
        dataScore = getResources().getIntArray(R.array.movie_score);
        dataCover = getResources().obtainTypedArray(R.array.data_photo);
    }
}
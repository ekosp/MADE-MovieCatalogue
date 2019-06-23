package com.ekosp.dicoding.moviecatalogue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.model.Movie;

import org.w3c.dom.Text;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-23.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MovieDetailFragment extends Fragment {

    @BindView(R.id.scoreView)
    CircleProgressView scoreView;
    @BindView(R.id.title_movie)
    TextView movieTitle;
    @BindView(R.id.txt_release_date)
    TextView releaseDate;
    @BindView(R.id.movie_overview)
    TextView movieOverview;
    @BindView(R.id.img_cover)
    ImageView movieCover;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment myFragment = new MovieDetailFragment();

        Bundle args = new Bundle();
        args.putParcelable("movie", movie);
        myFragment.setArguments(args);

        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        setMovieDetail(getArguments().getParcelable("movie"));

        return view;
    }

    void setMovieDetail(Movie movie) {
        movieTitle.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        movieOverview.setText(movie.getOverview());
        movieCover.setImageDrawable(getResources().getDrawable(movie.getCover()));
        scoreView.setValue((float) movie.getScore());
    }
}
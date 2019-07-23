package com.ekosp.dicoding.moviefavorite.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.ekosp.dicoding.moviefavorite.R;
import com.ekosp.dicoding.moviefavorite.helper.GlobalVar;
import com.ekosp.dicoding.moviefavorite.model.DetailObject;
import com.ekosp.dicoding.moviefavorite.view.BaseFragment;

import java.util.Objects;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-23.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MovieDetailFragment extends BaseFragment {

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

    private int pStatus = 0;
    private final Handler handler = new Handler();

    public static MovieDetailFragment newInstance(DetailObject movie) {
        MovieDetailFragment myFragment = new MovieDetailFragment();

        Bundle args = new Bundle();
        args.putParcelable("movie", movie);
        myFragment.setArguments(args);

        return myFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null)
            setMovieDetail(Objects.requireNonNull(getArguments().getParcelable("movie")));

        return view;
    }

    private void setMovieDetail(DetailObject movie) {
        movieTitle.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        movieOverview.setText(movie.getOverview());

        if (getActivity() != null)
            Glide.with(getActivity())
                    .load(GlobalVar.baseUrl_image500 + movie.getBackdropPath())
                    .centerCrop()
                    .into(movieCover);

        fillScorePoint(movie);
    }

    private void fillScorePoint(DetailObject movie) {
        new Thread(() -> {
            // TODO Auto-generated method stub
            while (pStatus < (movie.getVoteAverage() * 10)) {
                pStatus += 1;
                handler.post(() -> scoreView.setValue((float) pStatus));
                try {
                    // Sleep for 200 milliseconds.
                    // Just to display the progress slowly
                    Thread.sleep(40); //thread will take approx 3 seconds to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
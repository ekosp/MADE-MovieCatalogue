package com.ekosp.dicoding.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ekosp.dicoding.moviecatalogue.model.Movie;

import at.grabner.circleprogress.CircleProgressView;

public class MovieDetailActivity extends AppCompatActivity {

    CircleProgressView scoreView;
    TextView movieTitle;
    TextView movieOverview;
    TextView releaseDate;
    ImageView movieCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movie movie = getIntent().getParcelableExtra("movie");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Movie Detail");

        scoreView = findViewById(R.id.scoreView);
        movieTitle = findViewById(R.id.title_movie);
        releaseDate = findViewById(R.id.txt_release_date);
        movieOverview = findViewById(R.id.movie_overview);
        movieCover = findViewById(R.id.img_cover);

        movieTitle.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        movieOverview.setText(movie.getOverview());
        movieCover.setImageDrawable(getResources().getDrawable(movie.getCover()));
        scoreView.setValue((float) movie.getScore());
    }

}

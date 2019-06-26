package com.ekosp.dicoding.moviecatalogue.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.fragment.MovieDetailFragment;
import com.ekosp.dicoding.moviecatalogue.fragment.TvshowDetailFragment;
import com.ekosp.dicoding.moviecatalogue.model.Movie;
import com.ekosp.dicoding.moviecatalogue.model.Tvshow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.detail_info));

        String fragmentId = getIntent().getStringExtra("fragment_id");

        switch (fragmentId) {
            case "movie_detail":
                getSupportActionBar().setTitle(getResources().getString(R.string.detail_movie));
                Movie movie = getIntent().getParcelableExtra("movie");
                new MovieDetailFragment();
                changeFragment(MovieDetailFragment.newInstance(movie));
                break;
            case "tvshow_detail":
                getSupportActionBar().setTitle(getResources().getString(R.string.detail_tvshow));
                Tvshow show = getIntent().getParcelableExtra("tvshow");
                new TvshowDetailFragment();
                changeFragment(TvshowDetailFragment.newInstance(show));
                break;
        }
    }

    void changeFragment(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


}

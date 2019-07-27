package com.ekosp.dicoding.moviecatalogue.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.model.Movie;
import com.ekosp.dicoding.moviecatalogue.model.TvShow;
import com.ekosp.dicoding.moviecatalogue.view.fragment.MovieDetailFragment;
import com.ekosp.dicoding.moviecatalogue.view.fragment.TvshowDetailFragment;


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
       if (getSupportActionBar() != null){
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           getSupportActionBar().setDisplayShowHomeEnabled(true);
           getSupportActionBar().setTitle(getResources().getString(R.string.detail_info));
       }

        String fragmentId = getIntent().getStringExtra("fragment_id");

        if (fragmentId != null) {
            switch (fragmentId) {
                case "movie_detail":
                    getSupportActionBar().setTitle(getResources().getString(R.string.detail_movie));
                    Movie movie = getIntent().getParcelableExtra("movie");
                    new MovieDetailFragment();
                    changeFragment(MovieDetailFragment.newInstance(movie));
                    break;
                case "tvshow_detail":
                    getSupportActionBar().setTitle(getResources().getString(R.string.detail_tvshow));
                    TvShow show = getIntent().getParcelableExtra("tvshow");
                    new TvshowDetailFragment();
                    changeFragment(TvshowDetailFragment.newInstance(show));
                    break;
            }
        }
    }

    private void changeFragment(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Toast.makeText(this, getResources().getString(R.string.disable_back_btn), Toast.LENGTH_SHORT).show();
    }

}

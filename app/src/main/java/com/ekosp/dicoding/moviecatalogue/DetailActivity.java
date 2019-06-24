package com.ekosp.dicoding.moviecatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ekosp.dicoding.moviecatalogue.fragment.LanguageFragment;
import com.ekosp.dicoding.moviecatalogue.fragment.MovieDetailFragment;
import com.ekosp.dicoding.moviecatalogue.model.Movie;

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
        getSupportActionBar().setTitle("Detail Info");

        String fragmentId = getIntent().getStringExtra("fragment_id");

        switch (fragmentId) {
            case "movie_detail":
                Movie movie = getIntent().getParcelableExtra("movie");
                new MovieDetailFragment();
                changeFragment(MovieDetailFragment.newInstance(movie));
                break;
            case "settings":
                changeFragment(new LanguageFragment());
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

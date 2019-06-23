package com.ekosp.dicoding.moviecatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.ekosp.dicoding.moviecatalogue.adapter.TabAdapter;
import com.ekosp.dicoding.moviecatalogue.fragment.MovieDetailFragment;
import com.ekosp.dicoding.moviecatalogue.fragment.Tab1Fragment;
import com.ekosp.dicoding.moviecatalogue.fragment.Tab2Fragment;
import com.ekosp.dicoding.moviecatalogue.model.Movie;
import com.google.android.material.tabs.TabLayout;

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

//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Movie Detail");

        String fragmentId = getIntent().getStringExtra("fragment_id");

        switch (fragmentId) {
            case "movie_detail":
                Movie movie = getIntent().getParcelableExtra("movie");
                new MovieDetailFragment();
                changeFragment(MovieDetailFragment.newInstance(movie));
                break;
            case "":
                changeFragment(new Tab2Fragment());
                break;
        }

    }

    void changeFragment(Fragment newFragment) {
        // Create new fragment and transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


}

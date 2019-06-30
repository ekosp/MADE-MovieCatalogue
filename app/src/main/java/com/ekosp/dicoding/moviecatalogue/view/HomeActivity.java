package com.ekosp.dicoding.moviecatalogue.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.fragment.FavoritesFragment;
import com.ekosp.dicoding.moviecatalogue.fragment.MovieListFragment;
import com.ekosp.dicoding.moviecatalogue.fragment.TvshowListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bn_main)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Boolean shouldAllowBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // set default first opened fragment
        changeFragment(MovieListFragment.newInstance(false));
        setTitle(getResources().getString(R.string.movie_list));

    }

    void changeFragment(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.home_menu:
                fragment =  MovieListFragment.newInstance(false);
                setTitle(getResources().getString(R.string.movie_list));
                break;
            case R.id.search_menu:
                fragment =  TvshowListFragment.newInstance(false);
                setTitle(getResources().getString(R.string.tv_show_list));
                break;
            case R.id.favorite_menu:
                fragment = new FavoritesFragment();
                setTitle(getResources().getString(R.string.favorites_list));
                break;
        }

        changeFragment(fragment);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void setTitle(String s){
        getSupportActionBar().setTitle(s);
    }

    @Override
    public void onBackPressed() {
        if (!shouldAllowBack) {
            shouldAllowBack = true;
            Toast.makeText(this, R.string.exit_confirm, Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
            finish();
        }
    }
}



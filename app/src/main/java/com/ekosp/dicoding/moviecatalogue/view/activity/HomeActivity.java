package com.ekosp.dicoding.moviecatalogue.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.view.fragment.FavoritesFragment;
import com.ekosp.dicoding.moviecatalogue.view.fragment.MovieListFragment;
import com.ekosp.dicoding.moviecatalogue.view.fragment.TvshowListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bn_main)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Boolean shouldAllowBack = false;
    private SearchView mSearchView;

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

    private void changeFragment(Fragment newFragment) {
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
                fragment = MovieListFragment.newInstance(false);
                setTitle(getResources().getString(R.string.movie_list));
                break;
            case R.id.search_menu:
                fragment = TvshowListFragment.newInstance(false);
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

        MenuItem mSearch = menu.findItem(R.id.searchMenu);
        mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 2)
                    doSearching(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settings) {
            Intent mIntent = new Intent(this, PreferenceActivity.class);
            startActivity(mIntent);
            return true;
        }

        if (id == R.id.searchMenu) {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (f instanceof MovieListFragment) {
                mSearchView.setQueryHint(getResources().getString(R.string.search_movie));
            } else if (f instanceof TvshowListFragment) {
                mSearchView.setQueryHint(getResources().getString(R.string.search_tv_show));
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setTitle(String s) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(s);
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

    private void doSearching(String query) {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (f instanceof MovieListFragment) {
            ((MovieListFragment) f).doSearchMovie(query);
        } else if (f instanceof TvshowListFragment) {
            ( (TvshowListFragment) f).doSearchMovie(query);
        }
    }
}



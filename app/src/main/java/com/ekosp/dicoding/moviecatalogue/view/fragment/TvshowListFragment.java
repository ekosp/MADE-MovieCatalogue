package com.ekosp.dicoding.moviecatalogue.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.view.adapter.TvshowAdapter;
import com.ekosp.dicoding.moviecatalogue.view.base.BaseFragment;
import com.ekosp.dicoding.moviecatalogue.model.TvShow;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;
import com.ekosp.dicoding.moviecatalogue.helper.TvshowTaskLoader;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-23.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

@SuppressWarnings("deprecation")
public class TvshowListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<List<TvShow>> {

    private final List<TvShow> tvshows = new ArrayList<>();
    private TvshowAdapter adapter;

    private static final int TVSHOW_LOADER = 51;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;
    @BindView(R.id.ll_movie_list_empty)
    LinearLayout ll_empty;

    public static TvshowListFragment newInstance(Boolean isFavorite) {
        TvshowListFragment fragment = new TvshowListFragment();

        Bundle args = new Bundle();
        args.putBoolean(GlobalVar.PARAM_IS_FAVORITE, isFavorite);
        args.putString(GlobalVar.PARAM_SEARCH_QUERY, "");
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        adapter = new TvshowAdapter(tvshows, getActivity());
        adapter.notifyDataSetChanged();

        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setAdapter(adapter);

        Objects.requireNonNull(getActivity()).getSupportLoaderManager().initLoader(TVSHOW_LOADER, getArguments(), this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle args = new Bundle();
        if (getArguments() != null && !getArguments().getBoolean(GlobalVar.PARAM_IS_FAVORITE))
            args.putBoolean(GlobalVar.PARAM_IS_FAVORITE, false);

        Objects.requireNonNull(getActivity()).getSupportLoaderManager().restartLoader(TVSHOW_LOADER, getArguments(), this);
    }

    @NonNull
    @Override
    public Loader<List<TvShow>> onCreateLoader(int id, @Nullable Bundle args) {
        boolean isFavorite = Objects.requireNonNull(args).getBoolean(GlobalVar.PARAM_IS_FAVORITE);
        String query = args.getString(GlobalVar.PARAM_SEARCH_QUERY);

        return new TvshowTaskLoader(getActivity(), isFavorite, query);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<TvShow>> loader, List<TvShow> data) {
        dismissLoading();

        if (data.size() == 0){
            ll_empty.setVisibility(View.VISIBLE);
        } else {
            ll_empty.setVisibility(View.GONE);
            adapter.setData(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<TvShow>> loader) {
        adapter.setData(null);
    }

    public void doSearchMovie(String query) {
        Objects.requireNonNull(getArguments()).putString(GlobalVar.PARAM_SEARCH_QUERY, query);
        Objects.requireNonNull(getActivity()).getSupportLoaderManager().restartLoader(TVSHOW_LOADER, getArguments(), this);

    }

}
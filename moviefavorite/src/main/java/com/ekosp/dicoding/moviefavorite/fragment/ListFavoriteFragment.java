package com.ekosp.dicoding.moviefavorite.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekosp.dicoding.moviefavorite.R;
import com.ekosp.dicoding.moviefavorite.adapter.FavoriteAdapter;
import com.ekosp.dicoding.moviefavorite.helper.GlobalVar;
import com.ekosp.dicoding.moviefavorite.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-23.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

@SuppressWarnings("deprecation")
public class ListFavoriteFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private FavoriteAdapter adapter;
    private Context mContext;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;
    @BindView(R.id.ll_list_empty)
    LinearLayout ll_list_empty;

    public static ListFavoriteFragment newInstance(@NonNull Integer loaderId) {
        ListFavoriteFragment fragment = new ListFavoriteFragment();

        Bundle args = new Bundle();
        args.putInt(GlobalVar.LOADER_ID, loaderId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        adapter = new FavoriteAdapter(getActivity());
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setAdapter(adapter);
        mContext = getContext();

        if (getArguments() != null)
            getLoaderManager().initLoader(getArguments().getInt(GlobalVar.LOADER_ID), null, this);

        return view;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        getDialogHelper().showProgressDialog();
        switch (id) {
            case GlobalVar.MOVIE_LOADER:
                return new CursorLoader(mContext,
                        GlobalVar.URI_MOVIE,
                        null, null, null, null);
            case GlobalVar.TVSHOW_LOADER:
                return new CursorLoader(mContext,
                        GlobalVar.URI_TVSHOW,
                        null, null, null, null);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        getDialogHelper().dismissProgressDialog();

        if (data.getCount() == 0)
            ll_list_empty.setVisibility(View.VISIBLE);
        else {
            ll_list_empty.setVisibility(View.GONE);
            adapter.setData(data, loader.getId());
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.setData(null, loader.getId());
    }

}
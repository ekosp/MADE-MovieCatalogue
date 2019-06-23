package com.ekosp.dicoding.moviecatalogue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.model.Movie;

import java.util.ArrayList;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by Eko.Purnomo on 2019-06-22.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> moview;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setMoview(ArrayList<Movie> moview) {
        this.moview = moview;
    }

    @Override
    public int getCount() {
        return moview.size();
    }

    @Override
    public Object getItem(int position) {
        return moview.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        Movie movie = (Movie) getItem(i);
        viewHolder.bind(movie);

        return view;
    }

    private class ViewHolder {

        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;
        CircleProgressView scoreView;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPhoto = view.findViewById(R.id.img_photo);
            scoreView = view.findViewById(R.id.scoreView);
        }

        void bind(Movie movie) {
            txtName.setText(movie.getTitle());
            txtDescription.setText(movie.getOverview());
            imgPhoto.setImageResource(movie.getCover());

            scoreView.setValue((float) movie.getScore());

        }
    }
}
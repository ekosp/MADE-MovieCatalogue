package com.ekosp.dicoding.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;
import com.ekosp.dicoding.moviecatalogue.model.Movie;
import com.ekosp.dicoding.moviecatalogue.view.DetailActivity;

import java.util.List;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-22.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_photo)
        ImageView cover;
        @BindView(R.id.scoreView)
        CircleProgressView score;
        @BindView(R.id.txt_name)
        TextView title;
        @BindView(R.id.txt_release_date)
        TextView releaseDate;
        @BindView(R.id.txt_description)
        TextView description;


        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        void bind(Movie movie) {
            title.setText(movie.getTitle());
            description.setText(movie.getOverview());
            releaseDate.setText(movie.getReleaseDate());
            score.setValue(movie.getVoteAverage() * 10);

            Glide.with(context)
                    .load(GlobalVar.baseUrl_image98 + movie.getPosterPath())
                    .centerCrop()
                    .into(cover);
        }
    }

    public MoviesAdapter(List<Movie> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public void setData(List<Movie> items) {
        moviesList.clear();
        moviesList.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.bind(movie);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("fragment_id", "movie_detail");
            intent.putExtra("movie", movie);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
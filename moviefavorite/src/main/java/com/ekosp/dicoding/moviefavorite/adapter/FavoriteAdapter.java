package com.ekosp.dicoding.moviefavorite.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ekosp.dicoding.moviefavorite.R;
import com.ekosp.dicoding.moviefavorite.helper.GlobalVar;
import com.ekosp.dicoding.moviefavorite.model.DetailObject;
import com.ekosp.dicoding.moviefavorite.view.DetailActivity;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-22.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MovieHolder> {

    private final Context context;
    private Cursor mCursor;
    private Integer mLoaderid;

    class MovieHolder extends RecyclerView.ViewHolder {

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
        @BindView(R.id.movieCard)
        CardView cardView;

        private DetailObject object;

        MovieHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(Cursor cursor) {
            object = new DetailObject();

            if (mLoaderid == GlobalVar.MOVIE_LOADER) {
                String strTtl = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                title.setText(strTtl);
                object.setTitle(strTtl);

                String strDate = cursor.getString(cursor.getColumnIndexOrThrow("release_date"));
                releaseDate.setText(strDate);
                object.setReleaseDate(strDate);
            } else {
                String strName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                title.setText(strName);
                object.setTitle(strName);

                String strDate = cursor.getString(cursor.getColumnIndexOrThrow("first_air_date"));
                releaseDate.setText(strDate);
                object.setReleaseDate(strDate);
            }

            String strOverview = cursor.getString(cursor.getColumnIndexOrThrow("overview"));
            description.setText(strOverview);
            object.setOverview(strOverview);

            float fl = cursor.getFloat(cursor.getColumnIndexOrThrow("vote_average")) ;
            score.setValue(fl * 10);
            object.setVoteAverage(fl);


            String posterPath = cursor.getString(   cursor.getColumnIndexOrThrow("poster_path"));
            String backdrop = cursor.getString(   cursor.getColumnIndexOrThrow("backdrop_path"));

            object.setPosterPath(posterPath);
            object.setBackdropPath(backdrop);

            Glide.with(context)
                    .load(GlobalVar.baseUrl_image98 + posterPath)
                    .centerCrop()
                    .into(cover);

            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailActivity.class);

                if (mLoaderid == GlobalVar.MOVIE_LOADER) {
                    intent.putExtra("movie", object);
                } else {
                    intent.putExtra("movie", object);
                }

                context.startActivity(intent);
            });
        }
    }

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public void setData(Cursor cursor, Integer loaderId) {
        mCursor = cursor;
        mLoaderid = loaderId;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        if (mCursor.moveToPosition(position)) {
            holder.bind(mCursor);
        }
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }
}
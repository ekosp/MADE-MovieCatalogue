package com.ekosp.dicoding.moviecatalogue.view.adapter;

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
import com.ekosp.dicoding.moviecatalogue.model.TvShow;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;
import com.ekosp.dicoding.moviecatalogue.view.activity.DetailActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-22.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class TvshowAdapter extends RecyclerView.Adapter<TvshowAdapter.MyViewHolder> {

    private final List<TvShow> tvshowList;
    private final Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {

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

        void bind(TvShow show) {
            title.setText(show.getName());
            description.setText(show.getOverview());
            releaseDate.setText(show.getFirst_air_date());
            score.setValue(show.getVote_average() * 10);

            Glide.with(context)
                    .load(GlobalVar.baseUrl_image98 + show.getPoster_path())
                    .centerCrop()
                    .into(cover);
        }
    }


    public TvshowAdapter(List<TvShow> tvshowList, Context context) {
        this.tvshowList = tvshowList;
        this.context = context;
    }

    public void setData(List<TvShow> items) {
        if (items != null) {
            tvshowList.clear();
            tvshowList.addAll(items);
            notifyDataSetChanged();
        }
    }


    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        TvShow show = tvshowList.get(position);
        holder.bind(show);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("fragment_id", "tvshow_detail");
            intent.putExtra("tvshow", show);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tvshowList.size();
    }
}
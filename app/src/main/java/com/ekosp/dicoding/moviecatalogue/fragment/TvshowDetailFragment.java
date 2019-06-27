package com.ekosp.dicoding.moviecatalogue.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;
import com.ekosp.dicoding.moviecatalogue.model.Tvshow;
import com.ekosp.dicoding.moviecatalogue.view.BaseFragment;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-23.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class TvshowDetailFragment extends BaseFragment {

    @BindView(R.id.scoreView)
    CircleProgressView scoreView;
    @BindView(R.id.title_movie)
    TextView movieTitle;
    @BindView(R.id.txt_release_date)
    TextView releaseDate;
    @BindView(R.id.movie_overview)
    TextView movieOverview;
    @BindView(R.id.img_cover)
    ImageView movieCover;

    int pStatus = 0;
    private Handler handler = new Handler();

    public static TvshowDetailFragment newInstance(Tvshow tvshow) {
        TvshowDetailFragment myFragment = new TvshowDetailFragment();

        Bundle args = new Bundle();
        args.putParcelable("tvshow", tvshow);
        myFragment.setArguments(args);

        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        setTVshowDetail(getArguments().getParcelable("tvshow"));

        return view;
    }

    void setTVshowDetail(Tvshow show) {
        movieTitle.setText(show.getName());
        releaseDate.setText(show.getFirstAirDate());
        movieOverview.setText(show.getOverview());

        String coverUrl;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //Do some stuff
            coverUrl = show.getPosterPath();
        } else {
            coverUrl = show.getBackdropPath();
        }

        Glide.with(getActivity())
                .load(GlobalVar.baseUrl_image500+coverUrl)
                .centerCrop()
                .into(movieCover);

        fillScorePoint(show);
    }

    private void fillScorePoint(Tvshow show) {
        new Thread(() -> {
            // TODO Auto-generated method stub
            while (pStatus < (show.getVoteAverage() * 10)) {
                pStatus += 1;
                handler.post(() -> scoreView.setValue((float) pStatus));
                try {
                    // Sleep for 200 milliseconds.
                    // Just to display the progress slowly
                    Thread.sleep(50); //thread will take approx 3 seconds to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
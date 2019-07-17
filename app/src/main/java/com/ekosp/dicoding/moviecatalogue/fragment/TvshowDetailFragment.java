package com.ekosp.dicoding.moviecatalogue.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.database.DbRepository;
import com.ekosp.dicoding.moviecatalogue.database.entity.Tvshow;
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar;
import com.ekosp.dicoding.moviecatalogue.base.BaseFragment;

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
    @BindView(R.id.action_favorite)
    ImageView actionFavorite;

    int pStatus = 0;
    private Handler handler = new Handler();
    private Boolean isBookMarked = false;
    private DbRepository dbRepository;
    private Tvshow tvshow;
    private final String TAG = TvshowListFragment.class.getSimpleName();

    public static TvshowDetailFragment newInstance(Tvshow tvshow) {
        TvshowDetailFragment myFragment = new TvshowDetailFragment();

        Bundle args = new Bundle();
        args.putParcelable("tvshow", tvshow);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbRepository = new DbRepository(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        tvshow = getArguments().getParcelable("tvshow");
        setTVshowDetail();
        setAction();


        return view;
    }

    private void setAction() {
        isBookMarked = dbRepository.getTvshowById(tvshow.getId()) > 0;
        Log.e(TAG, "cek is favorite : " + tvshow.getId() + "? =" + isBookMarked);

        setActionFavoriteIcon();

        actionFavorite.setOnClickListener(v -> {
            if (!isBookMarked) {
                // new, save to favorite
                Log.e(TAG, "save tvshow with id: " + tvshow.getId());
                long result = dbRepository.insertTvshow(new com.ekosp.dicoding.moviecatalogue.database.entity.Tvshow(
                        tvshow.getId(),
                        tvshow.getTitle(),
                        tvshow.getOverview(),
                        tvshow.getFirstAiringDate(),
                        tvshow.getVoteAverage(),
                        tvshow.getPosterPath(),
                        tvshow.getBackdropPath()
                ));

                if (result > 1) {
                    isBookMarked = true;
                    Toast.makeText(getActivity(), "Saved to list favorite", Toast.LENGTH_SHORT).show();
                }
            } else {
                // remove from favorite
                Log.e(TAG, "delete tvshow with id: " + tvshow.getId());
                int result = dbRepository.deleteTvshowById(tvshow.getId());

                if (result > 0) {
                    isBookMarked = false;
                    Toast.makeText(getActivity(), "Removed from list favorite", Toast.LENGTH_SHORT).show();
                }

            }

            setActionFavoriteIcon();
        });
    }

    void setActionFavoriteIcon() {
        if (isBookMarked)
            actionFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_bookmark_white_24dp));
        else
            actionFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp));

    }

    void setTVshowDetail() {
        movieTitle.setText(tvshow.getTitle());
        releaseDate.setText(tvshow.getFirstAiringDate());
        movieOverview.setText(tvshow.getOverview());

        String coverUrl;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //Do some stuff
            coverUrl = tvshow.getPosterPath();
        } else {
            coverUrl = tvshow.getBackdropPath();
        }

        Glide.with(getActivity())
                .load(GlobalVar.baseUrl_image500+coverUrl)
                .centerCrop()
                .into(movieCover);

        fillScorePoint(tvshow);
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
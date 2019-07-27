package com.ekosp.dicoding.moviecatalogue.feature.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ekosp.dicoding.moviecatalogue.model.Movie;
import com.ekosp.dicoding.moviecatalogue.model.UpcomingMovieListResponse;
import com.ekosp.dicoding.moviecatalogue.network.ApiClient;
import com.ekosp.dicoding.moviecatalogue.network.MovieService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class ReleaseTodayAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        MovieService service = ApiClient.Companion.getClient();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        String TAG = "AlarmReceiver";
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {

                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);

                ReleaseTodayReminder.setReminder(context, ReleaseTodayAlarmReceiver.class, localData.get_hour(), localData.get_min()); // default at 08:00
                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        Call<UpcomingMovieListResponse> call = service.getUpcommingMovie();
        call.enqueue(new Callback<UpcomingMovieListResponse>() {
            @Override
            public void onResponse(Call<UpcomingMovieListResponse> call, Response<UpcomingMovieListResponse> response) {
                if (response.isSuccessful())

                    for (Movie movie : response.body().getResults()) {
                        if (movie.getRelease_date().equals(currentDate)){
                            ReleaseTodayReminder.showReleaseNotification(context,movie.getTitle());
                        }
                    }
            }

            @Override
            public void onFailure(Call<UpcomingMovieListResponse> call, Throwable t) {

            }

        });

    }
}



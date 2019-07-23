package com.ekosp.dicoding.moviecatalogue.feature.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ekosp.dicoding.moviecatalogue.R;

import java.util.Objects;

/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class DailyAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String TAG = "AlarmReceiver";

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {

                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                DailyReminder.setReminder(context, DailyAlarmReceiver.class, 7, 0);
                return;
            }
        }

        Log.d(TAG, "onReceive: ");
        //Trigger the daily reminder notification
        DailyReminder.showVisitNotification(context,
                Objects.requireNonNull(context).getResources().getString(R.string.app_name), context.getResources().getString(R.string.notif_daily_reminder));

    }
}



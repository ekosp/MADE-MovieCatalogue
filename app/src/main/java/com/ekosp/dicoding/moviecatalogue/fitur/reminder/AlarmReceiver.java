package com.ekosp.dicoding.moviecatalogue.fitur.reminder;

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

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        String TAG = "AlarmReceiver";
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);

                ReleaseTodayReminder.setReminder(context, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                DailyReminder.setReminder(context, AlarmReceiver.class, 22, 15);
                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        //Trigger the daily reminder notification
        DailyReminder.showVisitNotification(context,
                Objects.requireNonNull(context).getResources().getString(R.string.app_name), context.getResources().getString(R.string.notif_daily_reminder));

        //Trigger the daily reminder notification
        ReleaseTodayReminder.showReleaseNotification(context,
                context.getResources().getString(R.string.notif_dragon_ball_release));

    }
}



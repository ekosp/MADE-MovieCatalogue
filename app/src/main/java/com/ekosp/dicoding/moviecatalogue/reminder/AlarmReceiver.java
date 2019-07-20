package com.ekosp.dicoding.moviecatalogue.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.view.HomeActivity;

/**
 * Created by Jaison on 17/06/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

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
        DailyReminder.showVisitNotification(context, HomeActivity.class,
                context.getResources().getString(R.string.app_name), context.getResources().getString(R.string.notif_daily_reminder));

        //Trigger the daily reminder notification
        ReleaseTodayReminder.showReleaseNotification(context, HomeActivity.class,
                "Dragon Ball", context.getResources().getString(R.string.notif_dragon_ball_release));

    }
}



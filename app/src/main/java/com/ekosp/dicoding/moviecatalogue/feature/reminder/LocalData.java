package com.ekosp.dicoding.moviecatalogue.feature.reminder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Eko.Purnomo on 2019-07-14.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class LocalData {

    private static final String APP_SHARED_PREFS = "MovieCataloguePref";

    private final SharedPreferences appSharedPrefs;
    private final SharedPreferences.Editor prefsEditor;

    private static final String reminderStatus = "reminderStatus";
    private static final String dailyReminderStatus = "dailyReminderStatus";
    private static final String hour = "hour";
    private static final String min = "min";

    @SuppressLint("CommitPrefEdits")
    public LocalData(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public boolean getReminderStatus() {
        return appSharedPrefs.getBoolean(reminderStatus, false);
    }

    public boolean getDailyReminderStatus() {
        return appSharedPrefs.getBoolean(dailyReminderStatus, false);
    }

    public void setReminderStatus(boolean status) {
        prefsEditor.putBoolean(reminderStatus, status);
        prefsEditor.commit();
    }

    public void setDailyReminderStatus(boolean status) {
        prefsEditor.putBoolean(dailyReminderStatus, status);
        prefsEditor.commit();
    }

    public int get_hour() {
        return appSharedPrefs.getInt(hour, 8);
    }

    public void set_hour(int h) {
        prefsEditor.putInt(hour, h);
        prefsEditor.commit();
    }

    public int get_min() {
        return appSharedPrefs.getInt(min, 0);
    }

    public void set_min(int m) {
        prefsEditor.putInt(min, m);
        prefsEditor.commit();
    }

}

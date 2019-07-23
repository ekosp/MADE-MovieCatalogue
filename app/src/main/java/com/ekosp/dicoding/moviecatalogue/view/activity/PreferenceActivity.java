package com.ekosp.dicoding.moviecatalogue.view.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.ekosp.dicoding.moviecatalogue.R;
import com.ekosp.dicoding.moviecatalogue.feature.reminder.DailyAlarmReceiver;
import com.ekosp.dicoding.moviecatalogue.feature.reminder.ReleaseTodayAlarmReceiver;
import com.ekosp.dicoding.moviecatalogue.feature.reminder.DailyReminder;
import com.ekosp.dicoding.moviecatalogue.feature.reminder.LocalData;
import com.ekosp.dicoding.moviecatalogue.feature.reminder.ReleaseTodayReminder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class PreferenceActivity extends AppCompatActivity {

    private final String TAG = "RemindMe";
    private LocalData localData;
    private TextView tvTime;
    private LinearLayout ll_set_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        localData = new LocalData(getApplicationContext());


        ll_set_time = findViewById(R.id.ll_set_time);
        LinearLayout ll_terms = findViewById(R.id.ll_terms);
        LinearLayout ll_set_language = findViewById(R.id.ll_set_language);

        tvTime = findViewById(R.id.tv_reminder_time_desc);

        SwitchCompat reminderSwitch = findViewById(R.id.timerSwitch);
        SwitchCompat returnAppSwitch = findViewById(R.id.returnAppSwitch);

        int hour = localData.get_hour();
        int min = localData.get_min();

        tvTime.setText(getFormatedTime(hour, min));

        reminderSwitch.setChecked(localData.getReminderStatus());
        returnAppSwitch.setChecked(localData.getDailyReminderStatus());

        if (!localData.getReminderStatus())
            ll_set_time.setAlpha(0.4f);

        returnAppSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            localData.setDailyReminderStatus(isChecked);
            if (isChecked) {
                Log.d(TAG, "onCheckedChanged: true");
                DailyReminder.setReminder(PreferenceActivity.this, DailyAlarmReceiver.class, 7, 0);
                ll_set_time.setAlpha(1f);
            } else {
                Log.d(TAG, "onCheckedChanged: false");
                DailyReminder.cancelReminder(PreferenceActivity.this, DailyAlarmReceiver.class);
                ll_set_time.setAlpha(0.4f);
            }

        });

        reminderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            localData.setReminderStatus(isChecked);
            if (isChecked) {
                Log.d(TAG, "onCheckedChanged: true");
                ReleaseTodayReminder.setReminder(PreferenceActivity.this, ReleaseTodayAlarmReceiver.class, localData.get_hour(), localData.get_min());
                ll_set_time.setAlpha(1f);
            } else {
                Log.d(TAG, "onCheckedChanged: false");
                ReleaseTodayReminder.cancelReminder(PreferenceActivity.this, ReleaseTodayAlarmReceiver.class);
                ll_set_time.setAlpha(0.4f);
            }

        });

        ll_set_time.setOnClickListener(view -> {
            if (localData.getReminderStatus())
                showTimePickerDialog(localData.get_hour(), localData.get_min());
        });

        ll_terms.setOnClickListener(view -> {

        });

        ll_set_language.setOnClickListener(view -> {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        });


    }

    @SuppressLint("InflateParams")
    private void showTimePickerDialog(int h, int m) {

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.timepicker_header, null);

        TimePickerDialog builder = new TimePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                (timePicker, hour, min) -> {
                    Log.d(TAG, "onTimeSet: hour " + hour);
                    Log.d(TAG, "onTimeSet: min " + min);
                    localData.set_hour(hour);
                    localData.set_min(min);
                    tvTime.setText(getFormatedTime(hour, min));
                    ReleaseTodayReminder.setReminder(PreferenceActivity.this,
                            ReleaseTodayAlarmReceiver.class, localData.get_hour(), localData.get_min());


                }, h, m, false);

        builder.setCustomTitle(view);
        builder.show();

    }

    private String getFormatedTime(int h, int m) {
        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "hh:mm a";

        String oldDateString = h + ":" + m;
        String newDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, getCurrentLocale());
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(Objects.requireNonNull(d));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateString;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return getResources().getConfiguration().locale;
        }
    }
}

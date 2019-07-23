package com.ekosp.dicoding.moviecatalogue.di;

import com.ekosp.dicoding.moviecatalogue.feature.reminder.ReleaseTodayAlarmReceiver;
import com.ekosp.dicoding.moviecatalogue.view.fragment.MovieListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInject(MovieListFragment movieListFragment);

    void doInject(ReleaseTodayAlarmReceiver alarmReceiver);

}

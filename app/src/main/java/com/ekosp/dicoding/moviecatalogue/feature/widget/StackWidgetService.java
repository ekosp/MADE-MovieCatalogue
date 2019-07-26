package com.ekosp.dicoding.moviecatalogue.feature.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Eko S.P on 24/07/2019.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

public class StackWidgetService extends RemoteViewsService  {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FavoriteStackRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

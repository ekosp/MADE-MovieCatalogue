package com.ekosp.dicoding.moviecatalogue.fitur.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Eko.Purnomo on 2019-07-20.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext());
    }
}
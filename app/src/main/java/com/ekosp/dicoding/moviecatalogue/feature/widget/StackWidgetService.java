package com.ekosp.dicoding.moviecatalogue.feature.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by izadalab on 16/02/18.
 */

public class StackWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FavoriteStackRemoteViewsFactory(this.getApplicationContext(), intent);
    }

}

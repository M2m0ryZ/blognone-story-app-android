package com.panuwatjan.blognonestory.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by benznest on 19-Mar-18.
 */

public class MyBlognoneNodeListWidgetRemoteService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyBlognoneNodeListRemoteViewFactory(this.getApplicationContext(), intent);
    }
}

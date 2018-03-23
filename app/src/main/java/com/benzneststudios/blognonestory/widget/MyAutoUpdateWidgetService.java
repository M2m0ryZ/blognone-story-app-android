package com.benzneststudios.blognonestory.widget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by benznest on 19-Mar-18.
 */

public class MyAutoUpdateWidgetService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        run();
        return super.onStartCommand(intent, flags, startId);
    }

    private void run() {
        MyWidgetManager.updateWidget();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

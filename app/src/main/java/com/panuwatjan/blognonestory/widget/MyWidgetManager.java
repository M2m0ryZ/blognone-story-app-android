package com.panuwatjan.blognonestory.widget;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.panuwatjan.blognonestory.MyContextor;

/**
 * Created by benznest on 19-Mar-18.
 */

public class MyWidgetManager {
    public static void updateWidget() {
        updateWidget(MyBlognoneNodeListWidgetProvider.class);
    }

    public static void updateWidget(Class<?> classWidget) {
        try {
            Context context = MyContextor.getInstance();
            Application application = MyContextor.getApplication();

            Intent intent = new Intent(context, classWidget);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

            int[] ids = AppWidgetManager.getInstance(application)
                    .getAppWidgetIds(new ComponentName(application, classWidget));
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
            context.sendBroadcast(intent);
        } catch (Exception ignored) {

        }
    }
}

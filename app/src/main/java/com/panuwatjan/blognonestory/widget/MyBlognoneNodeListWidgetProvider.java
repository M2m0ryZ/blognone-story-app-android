package com.panuwatjan.blognonestory.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.panuwatjan.blognonestory.MyCache;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.activity.SplashScreenActivity;
import com.panuwatjan.blognonestory.dao.BlognoneNodeDao;
import com.panuwatjan.blognonestory.service.blognone.web.MyBlognoneManager;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by benznest on 19-Mar-18.
 */

public class MyBlognoneNodeListWidgetProvider extends AppWidgetProvider {

    private PendingIntent mPendingIntent = null;
    private Intent intentApp;
    private static RemoteViews rv = null;
    private ArrayList<BlognoneNodeDao> listNode = new ArrayList<>();

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

//        final AlarmManager m = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        final Calendar TIME = Calendar.getInstance();
//        TIME.set(Calendar.MINUTE, 0);
//        TIME.set(Calendar.SECOND, 0);
//        TIME.set(Calendar.MILLISECOND, 0);
//        final Intent i = new Intent(context, MyAutoUpdateWidgetService.class);
//
//        if (mPendingIntent == null) {
//            mPendingIntent = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
//        }
//
//        m.setRepeating(AlarmManager.RTC, TIME.getTime().getTime(), 60000, mPendingIntent);
        MyWidgetManager.updateWidget();
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        if (rv == null) {
            rv = new RemoteViews(context.getPackageName(), R.layout.widget_node_list);
        }

        MyBlognoneManager.loadNodeList(0, new MyBlognoneManager.OnLoadNodeListListener() {
            @Override
            public void onLoaded(ArrayList<BlognoneNodeDao> list) {
                Log.d("Benznest LOG", "Widget loadNodeList = " + list.size());
                MyCache.setNodeList(context, list);
                listNode = list;
                updateDataOnWidget(context, list, appWidgetIds, appWidgetManager);
            }

            @Override
            public void onFail() {
                //
            }
        });

    }

    private void updateDataOnWidget(Context context, ArrayList<BlognoneNodeDao> listNode, int[] appWidgetIds, AppWidgetManager appWidgetManager) {
        long time = Calendar.getInstance().getTimeInMillis();

        if (rv == null) {
            rv = new RemoteViews(context.getPackageName(), R.layout.widget_node_list);
        }

        // loop each App Widget.
        for (int i = 0; i < appWidgetIds.length; i++) {
            Intent intent = new Intent(context, MyBlognoneNodeListWidgetRemoteService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            rv.setRemoteAdapter(R.id.listView, intent);
            rv.setEmptyView(R.id.listView, R.id.tv_no_data);

//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(time);
//            rv.setTextViewText(R.id.tv_last_update, "Last update " + calendar.getTime());

            final Intent activityIntent = new Intent(context, SplashScreenActivity.class);
            final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setPendingIntentTemplate(R.id.listView, pendingIntent);

            //
            try {
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.listView);
                appWidgetManager.updateAppWidget(appWidgetIds, rv);
            } catch (Exception e) {
                //
            }
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
//            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//            int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(context, MyBlognoneNodeListWidgetProvider.class));
//            updateDataOnWidget(appWidgetIds, context, appWidgetManager);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
                                          int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        int[] appWidgetIds = {appWidgetId};
        onUpdate(context, appWidgetManager, appWidgetIds);
    }

}

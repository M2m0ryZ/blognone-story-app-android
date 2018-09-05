package com.benznestdeveloper.blognonestory.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.benznestdeveloper.blognonestory.MyCache;
import com.benznestdeveloper.blognonestory.MyConstants;
import com.benznestdeveloper.blognonestory.R;
import com.benznestdeveloper.blognonestory.dao.BlognoneNodeDao;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by benznest on 19-Mar-18.
 */

public class MyBlognoneNodeListRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<BlognoneNodeDao> listNode = new ArrayList<>();
    private Context mContext;
    private int mAppWidgetId;
    private int tabMode;

    public MyBlognoneNodeListRemoteViewFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        listNode = MyCache.getNodeList(context);
        if (listNode == null) {
            Log.d("BENZNEST LOG", "WIDGET listNode NULL");
        } else {
            Log.d("BENZNEST LOG", "WIDGET listNode = " + listNode.size());
        }
    }

    @Override
    public void onCreate() {
        updateData();
    }

    private void updateData() {
        int max = 30;
        if (listNode.size() > max) {
            listNode = new ArrayList<>(listNode.subList(0, max - 1));
        }
    }

    public RemoteViews getViewAt(int position) {
        BlognoneNodeDao node = listNode.get(position);
        // Construct a remote views item based on the app widget item XML file,
        // and set the text based on the position.
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.row_widget_node_title);
        rv.setTextViewText(R.id.tv_title, listNode.get(position).getTitle());
        rv.setTextViewText(R.id.tv_description, listNode.get(position).getInfo());
        rv.setTextViewText(R.id.tv_writer, "By " + listNode.get(position).getWriter());
        rv.setTextViewText(R.id.tv_date, listNode.get(position).getDate());
        rv.setTextViewText(R.id.tv_last_update, listNode.get(position).getCountComment() + " comments");

        if (node.isSticky()) {
            rv.setInt(R.id.ll_container, "setBackgroundResource", R.color.colorSticky);
            rv.setInt(R.id.ll_container_body_border, "setBackgroundResource", R.color.colorSticky);
            rv.setInt(R.id.ll_container_body, "setBackgroundResource", R.color.colorBackgroundSticky);
        } else if (node.isWorkplace()) {
            rv.setInt(R.id.ll_container, "setBackgroundResource", R.color.colorWorkplace);
            rv.setInt(R.id.ll_container_body_border, "setBackgroundResource", R.color.colorWorkplace);
            rv.setInt(R.id.ll_container_body, "setBackgroundResource", R.color.colorBackgroundWorkplace);
        }else{
            rv.setInt(R.id.ll_container, "setBackgroundResource", R.color.colorPrimary);
            rv.setInt(R.id.ll_container_body_border, "setBackgroundResource", R.color.colorSilver);
            rv.setInt(R.id.ll_container_body, "setBackgroundResource", R.color.colorWhite);
        }

//        Intent intentApp = new Intent(mContext, SplashScreenActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intentApp, 0);
//        rv.setOnClickPendingIntent(R.id.tv_title, pendingIntent);

        final Intent activityIntent = new Intent();
        activityIntent.putExtra(MyConstants.KEY_NODE_ID, node.getId());
        rv.setOnClickFillInIntent(R.id.tv_title, activityIntent);

        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(mContext)
                    .load(node.getUrlImage())
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            rv.setImageViewBitmap(R.id.img_node, bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void onDataSetChanged() {
        updateData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return listNode.size();
    }

}

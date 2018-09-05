package com.benznestdeveloper.blognonestory.firebase;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.TaskStackBuilder;

import com.benznestdeveloper.blognonestory.MyConstants;
import com.benznestdeveloper.blognonestory.MyNotificationUtil;
import com.benznestdeveloper.blognonestory.MySetting;
import com.benznestdeveloper.blognonestory.activity.SplashScreenActivity;
import com.benznestdeveloper.blognonestory.widget.MyWidgetManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.concurrent.ExecutionException;

/**
 * Created by benznest on 20-Mar-18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // when message recieve from FCM.

        int id = Integer.parseInt(remoteMessage.getData().get("id"));
        String title = remoteMessage.getData().get("title").trim();
        String content = remoteMessage.getData().get("content").trim();
        String image = remoteMessage.getData().get("image");

        if (MySetting.isSettingNotification(getApplicationContext())) {
            MyNotificationUtil.send(getApplicationContext(), id, title, content, getBitmapFromUrl(image), false,
                    getPendingIntent(getApplicationContext(), id));
        }

        MyWidgetManager.updateWidget();
    }

    private Bitmap getBitmapFromUrl(String url) {
        Bitmap bitmap = null;
        try {
            bitmap = Glide
                    .with(getApplicationContext())
                    .load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(-1, -1) // Width and height
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private static PendingIntent getPendingIntent(Context context, int nodeId) {
        Intent intent = new Intent(context, SplashScreenActivity.class);
        intent.putExtra(MyConstants.KEY_NODE_ID, nodeId);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(SplashScreenActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }
}

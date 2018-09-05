package com.benznestdeveloper.blognonestory;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by benznest on 20-Mar-18.
 */

public class MyNotificationUtil {
    //    public static void send(Context context) {
//        send(context,
//                MyCache.getNotificationStickyTitle(context),
//                MyCache.getNotificationStickyBody(context),
//                false);
//    }
//
//    public static void send(Context context, boolean sticky) {
//        send(context,
//                MyCache.getNotificationStickyTitle(context),
//                MyCache.getNotificationStickyBody(context),
//                sticky);
//    }
//    public static void send(Context context, String title, String body) {
//        send(context, title, body, false);
//    }

//    public static void send(Context context, String title, String body, boolean sticky,PendingIntent pt) {
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
//                R.mipmap.ic_app);
//        send(context, title, body, bitmap, sticky,pt);
//    }

    public static void send(Context context,int id, String title, String body, Bitmap bitmap, boolean sticky,PendingIntent pt) {
//        if (!MyCache.isEnableNotification(context)) {
//            return;
//        }

        int color = ContextCompat.getColor(context, R.color.colorTurquoise);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(android.R.color.transparent, "Read more"
                        , pt).build();

        NotificationCompat.BigTextStyle inboxStyle =
                new NotificationCompat.BigTextStyle();
        inboxStyle.bigText(body);
        inboxStyle.setSummaryText("Blognone Story");
        inboxStyle.setBigContentTitle(title);

        Notification notification =
                new NotificationCompat.Builder(context) // this is context
                        .setSmallIcon(R.mipmap.ic_app_w)
                        .setLargeIcon(bitmap)
                        .setContentTitle("[Blognone] "+title)
                        .setContentText(body)
                        .setStyle(inboxStyle)
                        .setAutoCancel(false)
                        .setColor(color)
                        .setOngoing(sticky)
                        .setContentIntent(pt)
                        .setVibrate(new long[]{100, 500, 100})
                        .setLights(color, 3000, 3000)
                        .addAction(action)
                        .build();

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (sticky) {
//            notification.flags |= Notification.FLAG_NO_CLEAR | Notification.FLAG_ONGOING_EVENT;
//            notificationManager.notify(MyConstant.ID_NOTIFICATION_STICKY, notification);
//        } else {
        if (notificationManager != null) {
            notificationManager.notify(id, notification);
        }
//        }
    }

}

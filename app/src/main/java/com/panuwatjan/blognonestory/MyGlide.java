package com.panuwatjan.blognonestory;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyGlide {
    public static void load(Context context, ImageView imgView, String url) {
        load(context, imgView, url, 0);
    }

    public static void load(Context context, ImageView imgView, String url, int errorImage) {
        if (url == null) {
            url = "";
        }

        if (Build.VERSION.SDK_INT < 21) {
            url = url.replace("https:", "http:");
        }

        if (context != null) {
            DrawableTypeRequest<String> builder = Glide.with(context)
                    .load(url);

            if (errorImage != 0) {
                builder.error(errorImage);
            }

            builder.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgView);
        }
    }
}

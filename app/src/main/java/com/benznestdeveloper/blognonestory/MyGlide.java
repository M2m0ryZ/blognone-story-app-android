package com.benznestdeveloper.blognonestory;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyGlide {
    public static void load(Context context, ImageView imgView, String url) {
        if (url == null) {
            url = "";
        }

        if (Build.VERSION.SDK_INT < 21) {
            url = url.replace("https:", "http:");
        }

        if (context != null) {
            Glide.with(context)
                    .load(url)
                    .error(android.R.color.transparent)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgView);
        }
    }
}

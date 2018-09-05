package com.benznestdeveloper.blognonestory;

import android.content.Context;
import android.webkit.WebSettings;

/**
 * Created by benznest on 21-Mar-18.
 */

public class MyFontSize {
    public static final int FONT_SIZE_SMALL = 0;
    public static final int FONT_SIZE_NORMAL = 1;
    public static final int FONT_SIZE_LATGE = 1;
    public static final int FONT_SIZE_EXTRA = 2;

    public static int[] RES_FONT_SIZE_NAME = {
            R.string.small,
            R.string.normal,
            R.string.large,
            R.string.large_extra
    };

    public static double[] FACTOR_FONT_SIZE = {
            0.8,
            1,
            1.5,
            2
    };

    public static WebSettings.TextSize[] WEBVIEW_FONT_SIZE = {
            WebSettings.TextSize.SMALLER,
            WebSettings.TextSize.NORMAL,
            WebSettings.TextSize.LARGER,
            WebSettings.TextSize.LARGEST
    };

    public static String[] getFontSizeName(Context context) {
        String[] str = new String[RES_FONT_SIZE_NAME.length];
        for (int i = 0; i < RES_FONT_SIZE_NAME.length; i++) {
            str[i] = context.getString(RES_FONT_SIZE_NAME[i]);
        }
        return str;
    }

    public static String getFontSizeName(Context context, int index) {
        return context.getString(RES_FONT_SIZE_NAME[index]);
    }

    public static String getFontSizeNameCurrent(Context context) {
        return context.getString(RES_FONT_SIZE_NAME[MySetting.getSettingFontSize(context)]);
    }

    public static double getFontSizeFactorCurrent(Context context) {
        return FACTOR_FONT_SIZE[MySetting.getSettingFontSize(context)];
    }

    public static WebSettings.TextSize getTextSizeWebViewCurrent(Context context) {
        int index = MySetting.getSettingFontSize(context);
        return WEBVIEW_FONT_SIZE[index];
    }
}

package com.benznestdeveloper.blognonestory;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyUtils {

    public static String comma(String number) {
        try {
            double amount = Double.parseDouble(number);
            DecimalFormat formatter = new DecimalFormat("#,###");
            return formatter.format(amount);
        } catch (Exception e) {

        }
        return "";
    }

    public static void log(String str) {
        Log.d("BENZNEST LOG", str);
    }

    public static void vibrate() {
        vibrate(50);
    }

    public static void vibrate(long t) {
        if (MySetting.isSettingVibration(MyContextor.getInstance())) {
            Vibrator v = (Vibrator) MyContextor.getInstance().getSystemService(Context.VIBRATOR_SERVICE);
            if (v != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(t, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(t);
                }
            }
        }
    }

    public static boolean isTablet() {
        return MyContextor.getInstance().getResources().getBoolean(R.bool.is_tablet);
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static void shareLink(Activity activity, String url) {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Blognone");
            i.putExtra(Intent.EXTRA_TEXT, url);
            activity.startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    public static void shareLink(Activity activity, int nodeId) {
        shareLink(activity, "https://www.blognone.com/node/" + nodeId);
    }

    public static void openBrowser(Activity activity, int nodeId) {
        openBrowser(activity, "https://www.blognone.com/node/" + nodeId);
    }

    public static void openBrowser(Activity activity, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(browserIntent);
    }

    public static void copyToClipboard(Context context, int nodeId) {
        String url = "https://www.blognone.com/node/" + nodeId;
        copyToClipboard(context, url);
    }

    public static void copyToClipboard(Context context, String str) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(str, "");
        clipboard.setPrimaryClip(clip);

        Toast.makeText(context, str + " is copied.", Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, String str) {
        try {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }

    public static void openPlayStore(Context context) {
        final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }


    public static void openFacebookGroupURL(Context context, String facebookGroupId) {
        String facebookUrl = getFacebookUri(context, facebookGroupId);

        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        facebookIntent.setData(Uri.parse(facebookUrl));
        context.startActivity(facebookIntent);
    }


    private static String getFacebookUri(Context context, String facebookGroupId) {
        String FACEBOOK_URL = "https://www.facebook.com/groups/" + facebookGroupId;
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://group/" + facebookGroupId;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

}

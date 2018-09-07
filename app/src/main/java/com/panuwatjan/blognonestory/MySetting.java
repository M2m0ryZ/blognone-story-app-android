package com.panuwatjan.blognonestory;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MySetting {
    private static final String SHARED_PREFERENCES_NAME = "BLOGNONE_STORY_SETTING";
    private static SharedPreferences sp;

    public static String KEY_SETTING_GENERAL_AUTO_CACHE = "KEY_SETTING_GENERAL_AUTO_CACHE";
    public static String KEY_SETTING_DISPLAY_TOPIC_OPTION = "KEY_SETTING_DISPLAY_TOPIC_OPTION";
    public static String KEY_SETTING_ENABLE_NOTIFICATION = "KEY_SETTING_ENABLE_NOTIFICATION";
    public static String KEY_SETTING_ENABLE_VIBRATION = "KEY_SETTING_ENABLE_VIBRATION";
    public static String KEY_SETTING_ENABLE_SHOW_NEW_COMMENT = "KEY_SETTING_ENABLE_SHOW_NEW_COMMENT";
    public static String KEY_SETTING_ENABLE_SHOW_DETAIL_TOPIC = "KEY_SETTING_ENABLE_SHOW_DETAIL_TOPIC";
    public static String KEY_SETTING_FONT_SIZE = "KEY_SETTING_FONT_SIZE";
    public static String KEY_SETTING_ENABLE_TOPIC_COMMENT_TAB = "KEY_SETTING_ENABLE_TOPIC_COMMENT_TAB";

    private static void setContext(Context context) {
        if (context == null) {
            context = MyContextor.getInstance();
        }
        sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isSettingAutoCacheTopic(Context context) {
        setContext(context);
        return sp.getBoolean(KEY_SETTING_GENERAL_AUTO_CACHE, false);
    }

    public static void setSettingAutoCacheTopic(Context context, boolean is) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_SETTING_GENERAL_AUTO_CACHE, is);
        editor.commit();
    }

    public static boolean isSettingDisplayTabOption(Context context) {
        setContext(context);
        return sp.getBoolean(KEY_SETTING_DISPLAY_TOPIC_OPTION, true);
    }

    public static void setSettingDisplayTabOption(Context context, boolean is) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_SETTING_DISPLAY_TOPIC_OPTION, is);
        editor.commit();
    }

    public static boolean isSettingNotification(Context context) {
        setContext(context);
        return sp.getBoolean(KEY_SETTING_ENABLE_NOTIFICATION, true);
    }

    public static void setSettingNotification(Context context, boolean is) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_SETTING_ENABLE_NOTIFICATION, is);
        editor.commit();
    }

    public static boolean isSettingVibration(Context context) {
        setContext(context);
        return sp.getBoolean(KEY_SETTING_ENABLE_VIBRATION, true);
    }

    public static void setSettingVibration(Context context, boolean is) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_SETTING_ENABLE_VIBRATION, is);
        editor.commit();
    }
    public static boolean isSettingShowNewComment(Context context) {
        setContext(context);
        return sp.getBoolean(KEY_SETTING_ENABLE_SHOW_NEW_COMMENT, true);
    }

    public static void setSettingShowNewComment(Context context, boolean is) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_SETTING_ENABLE_SHOW_NEW_COMMENT, is);
        editor.commit();
    }

    public static boolean isSettingShowDetailOfTopic(Context context) {
        setContext(context);
        return sp.getBoolean(KEY_SETTING_ENABLE_SHOW_DETAIL_TOPIC, true);
    }

    public static void setSettingShowDetailOfTopic(Context context, boolean is) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_SETTING_ENABLE_SHOW_DETAIL_TOPIC, is);
        editor.commit();
    }

    public static boolean isSettingEnableTopicCommentTab(Context context) {
        setContext(context);
        return sp.getBoolean(KEY_SETTING_ENABLE_TOPIC_COMMENT_TAB, false);
    }

    public static void setSettingEnableTopicCommentTab(Context context, boolean is) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_SETTING_ENABLE_TOPIC_COMMENT_TAB, is);
        editor.commit();
    }

    public static int getSettingFontSize(Context context) {
        setContext(context);
        return sp.getInt(KEY_SETTING_FONT_SIZE, MyFontSize.FONT_SIZE_NORMAL);
    }

    public static void setSettingFontSize(Context context, int is) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_SETTING_FONT_SIZE, is);
        editor.commit();
    }
}

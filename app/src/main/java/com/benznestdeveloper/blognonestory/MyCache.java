package com.benznestdeveloper.blognonestory;

import android.content.Context;
import android.content.SharedPreferences;

import com.benznestdeveloper.blognonestory.dao.BlognoneNodeDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyCache {
    private static final String SHARED_PREFERENCES_NAME = "BLOGNONE_STORY_CACHE";
    private static SharedPreferences sp;

    public static String KEY_FIRST_VISIT = "KEY_FIRST_VISIT";
    public static String KEY_LAST_VERSION = "KEY_LAST_VERSION";
    public static String KEY_LANGUAGE = "KEY_LANGUAGE";
    public static String KEY_THEME = "KEY_THEME";
    public static String KEY_NODE_LIST = "KEY_NODE_LIST";

    private static void setContext(Context context) {
        if (context == null) {
            context = MyContextor.getInstance();
        }
        sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void setFirstVisit(Context context, boolean is) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_FIRST_VISIT, is);
        editor.commit();
    }

    public static boolean isFirstVisit(Context context) {
        setContext(context);
        return sp.getBoolean(KEY_FIRST_VISIT, true);
    }

    public static void setLastVersion(Context context, int v) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_LAST_VERSION, v);
        editor.commit();
    }

    public static int getLastVersion(Context context) {
        setContext(context);
        return sp.getInt(KEY_LAST_VERSION, 0);
    }

    public static void setLanguage(Context context, int v) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_LANGUAGE, v);
        editor.commit();
    }

    public static int getLanguage(Context context) {
        setContext(context);
        return sp.getInt(KEY_LANGUAGE, 0);
    }

    public static int getTheme(Context context) {
        setContext(context);
        return sp.getInt(KEY_THEME, MyThemes.getDefaultThemeBuildVariant());
    }

    public static void setTheme(Context context, int theme_index) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_THEME, theme_index);
        editor.commit();
    }

    public static void setNodeList(Context context, ArrayList<BlognoneNodeDao> list) {
        setContext(context);

        Gson gson = new Gson();
        String jsonText = gson.toJson(list);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_NODE_LIST, jsonText);
        editor.commit();
    }

    public static ArrayList<BlognoneNodeDao> getNodeList(Context context) {
        setContext(context);

        Gson gson = new Gson();
        String jsonText = sp.getString(KEY_NODE_LIST, "");
        ArrayList<BlognoneNodeDao> list = new ArrayList<>();
        Type type = new TypeToken<ArrayList<BlognoneNodeDao>>() {
        }.getType();
        list = gson.fromJson(jsonText, type);

        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
}

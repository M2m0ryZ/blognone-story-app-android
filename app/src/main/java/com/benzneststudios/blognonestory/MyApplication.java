package com.benzneststudios.blognonestory;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.benzneststudios.blognonestory.database.BlognoneFavoriteTopicDatabase;
import com.benzneststudios.blognonestory.database.BlognoneHistoryTopicDatabase;
import com.benzneststudios.blognonestory.database.BlognoneTagsDatabase;
import com.benzneststudios.blognonestory.database.BlognoneTopicCacheDatabase;
import com.benzneststudios.blognonestory.service.blognone.MyBlognoneForum;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyApplication extends Application {
    private DaoSession mDaoSession;
    public static int fontSize;
    public static double factorFontSize;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        MyContextor.setApplication(this);
        MyContextor.setContext(getApplicationContext());

        fontSize = MySetting.getSettingFontSize(this);
        factorFontSize = MyFontSize.getFontSizeFactorCurrent(this);

        initFont();
        MyThemes.init();
        MyBlognoneForum.init();
        initDatabase();
    }

    public void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CSChatThaiUI.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public void initDatabase() {
        DaoMaster.DevOpenHelper helper = null;
        helper = new DaoMaster.DevOpenHelper(this, "BlognoneStory.db", null);

        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();

        mDaoSession.getBlognoneTagsDao().createTable(db, true);
        mDaoSession.getBlognoneTopicCacheDao().createTable(db, true);
        mDaoSession.getBlognoneFavoriteTopicDao().createTable(db, true);
        mDaoSession.getBlognoneHistoryTopicDao().createTable(db, true);

        //init database.
        BlognoneTagsDatabase.initialize(getApplicationContext(), this, mDaoSession);
        BlognoneFavoriteTopicDatabase.initialize(getApplicationContext(), this, mDaoSession);
        BlognoneTopicCacheDatabase.initialize(getApplicationContext(), this, mDaoSession);
        BlognoneHistoryTopicDatabase.initialize(getApplicationContext(), this, mDaoSession);

        something(db, "ALTER TABLE BLOGNONE_HISTORY_TOPIC ADD COLUMN COUNT_COMMENT INTEGER DEFAULT 0");

        BlognoneTagsDatabase.initData();
    }

    private void something(SQLiteDatabase db, String sql) {
        try {
            db.execSQL(sql);
            MyUtils.log("SQL execute = " + sql);
        } catch (Exception e) {
            MyUtils.log("Error about '" + sql + "' because " + e.getCause());
        }
    }
}

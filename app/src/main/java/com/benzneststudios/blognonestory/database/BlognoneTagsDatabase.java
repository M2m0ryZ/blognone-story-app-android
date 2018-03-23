package com.benzneststudios.blognonestory.database;

import android.content.Context;

import com.benzneststudios.blognonestory.BlognoneTags;
import com.benzneststudios.blognonestory.BlognoneTagsDao;
import com.benzneststudios.blognonestory.DaoSession;
import com.benzneststudios.blognonestory.MyApplication;
import com.benzneststudios.blognonestory.MyBlognoneTags;

import java.util.ArrayList;

/**
 * Created by benznest on 18-Mar-18.
 */

public class BlognoneTagsDatabase {
    private static Context context;
    private static MyApplication mApplication;
    private static DaoSession mDaoSession;
    private static BlognoneTagsDao mBlognoneTagsDao;

    public static void initialize(Context context, MyApplication application, DaoSession daoSession) {
        BlognoneTagsDatabase.context = context;
        mApplication = application;
        mDaoSession = daoSession;
        mBlognoneTagsDao = mDaoSession.getBlognoneTagsDao();
    }

    public static void initData() {
        if (mBlognoneTagsDao.count() <= 0) {
            mBlognoneTagsDao.insertInTx(MyBlognoneTags.getTagDefault());
        }
    }

    public static ArrayList<BlognoneTags> getAll() {
        return getAll(null);
    }

    public static ArrayList<BlognoneTags> getAll(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return new ArrayList<>(mBlognoneTagsDao.queryBuilder()
                    .orderAsc(BlognoneTagsDao.Properties.Id)
                    .list());
        } else {
            return new ArrayList<>(mBlognoneTagsDao.queryBuilder()
                    .where(BlognoneTagsDao.Properties.Name.like("%" + keyword + "%"))
                    .orderAsc(BlognoneTagsDao.Properties.Id)
                    .list());
        }
    }

    public static ArrayList<BlognoneTags> getAllFavorite(boolean favorite) {
        return getAllFavorite(favorite);
    }

    public static ArrayList<BlognoneTags> getAllFavorite(boolean favorite, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return new ArrayList<>(mBlognoneTagsDao.queryBuilder()
                    .where(BlognoneTagsDao.Properties.Favorite.eq(favorite))
                    .orderAsc(BlognoneTagsDao.Properties.Id)
                    .list());
        } else {
            return new ArrayList<>(mBlognoneTagsDao.queryBuilder()
                    .where(BlognoneTagsDao.Properties.Name.like("%" + keyword + "%"))
                    .where(BlognoneTagsDao.Properties.Favorite.eq(favorite))
                    .orderAsc(BlognoneTagsDao.Properties.Id)
                    .list());
        }
    }

//
//    public static ArrayList<BlognoneTags> getFromExchangePairId(long id) {
//        return new ArrayList<>(mBlognoneTagsDao.queryBuilder()
//                .where(BlognoneTagsDao.Properties.Exchange_pair_id.eq(id))
//                .orderAsc(BlognoneTagsDao.Properties.Id)
//                .list());
//    }

    public static long insert(BlognoneTags c) {
        return mBlognoneTagsDao.insert(c);
    }

    public static void remove(BlognoneTags c) {
        mBlognoneTagsDao.delete(c);
    }

    public static void update(BlognoneTags c) {
        mBlognoneTagsDao.update(c);
    }

}

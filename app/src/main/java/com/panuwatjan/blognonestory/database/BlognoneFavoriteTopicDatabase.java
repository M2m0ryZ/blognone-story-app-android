package com.panuwatjan.blognonestory.database;

import android.content.Context;

import com.panuwatjan.blognonestory.BlognoneFavoriteTopic;
import com.panuwatjan.blognonestory.BlognoneFavoriteTopicDao;
import com.panuwatjan.blognonestory.DaoSession;
import com.panuwatjan.blognonestory.MyApplication;
import com.panuwatjan.blognonestory.dao.BlognoneNodeDao;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by benznest on 18-Mar-18.
 */

public class BlognoneFavoriteTopicDatabase {
    private static Context context;
    private static MyApplication mApplication;
    private static DaoSession mDaoSession;
    private static BlognoneFavoriteTopicDao mBlognoneFavoriteTopicDao;

    public static void initialize(Context context, MyApplication application, DaoSession daoSession) {
        BlognoneFavoriteTopicDatabase.context = context;
        mApplication = application;
        mDaoSession = daoSession;
        mBlognoneFavoriteTopicDao = mDaoSession.getBlognoneFavoriteTopicDao();
    }

    public static void initData() {

    }

    public static ArrayList<BlognoneFavoriteTopic> getAll(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return new ArrayList<>(mBlognoneFavoriteTopicDao.queryBuilder()
                    .orderAsc(BlognoneFavoriteTopicDao.Properties.Datetime)
                    .list());
        } else {
            return new ArrayList<>(mBlognoneFavoriteTopicDao.queryBuilder()
                    .where(BlognoneFavoriteTopicDao.Properties.Data.like("%" + keyword + "%"))
                    .orderAsc(BlognoneFavoriteTopicDao.Properties.Datetime)
                    .list());
        }
    }

    public static ArrayList<BlognoneNodeDao> getAllOnlyNode(String keyword) {
        Gson gson = new Gson();
        ArrayList<BlognoneNodeDao> listNode = new ArrayList<>();
        ArrayList<BlognoneFavoriteTopic> listFavorite = getAll(keyword);
        for (BlognoneFavoriteTopic favoriteTopic : listFavorite) {
            BlognoneNodeDao node = gson.fromJson(favoriteTopic.getData(), BlognoneNodeDao.class);
            listNode.add(node);
        }
        return listNode;
    }

    public static boolean isFavorite(long nodeId) {
        BlognoneFavoriteTopic topic = mBlognoneFavoriteTopicDao.load(nodeId);
        if (topic != null) {
            return true;
        }
        return false;
    }

    public static long insert(long nodeId, BlognoneNodeDao node) {
        Gson gson = new Gson();

        BlognoneFavoriteTopic topic = new BlognoneFavoriteTopic();
        topic.setId(nodeId);
        topic.setDatetime(Calendar.getInstance().getTimeInMillis());
        topic.setData(gson.toJson(node));
        return insert(topic);
    }

    public static long insert(BlognoneFavoriteTopic c) {
        return mBlognoneFavoriteTopicDao.insertOrReplace(c);
    }

    public static void delete(BlognoneFavoriteTopic c) {
        mBlognoneFavoriteTopicDao.delete(c);
    }

    public static void delete(long nodeId) {
        mBlognoneFavoriteTopicDao.deleteByKey(nodeId);
    }

    public static void update(BlognoneFavoriteTopic c) {
        mBlognoneFavoriteTopicDao.update(c);
    }

}

package com.benznestdeveloper.blognonestory.database;

import android.content.Context;

import com.benznestdeveloper.blognonestory.BlognoneTopicCache;
import com.benznestdeveloper.blognonestory.BlognoneTopicCacheDao;
import com.benznestdeveloper.blognonestory.DaoSession;
import com.benznestdeveloper.blognonestory.MyApplication;
import com.benznestdeveloper.blognonestory.dao.BlognoneNodeDao;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by benznest on 18-Mar-18.
 */

public class BlognoneTopicCacheDatabase {
    private static Context context;
    private static MyApplication mApplication;
    private static DaoSession mDaoSession;
    private static BlognoneTopicCacheDao mBlognoneTopicCache;

    public static void initialize(Context context, MyApplication application, DaoSession daoSession) {
        BlognoneTopicCacheDatabase.context = context;
        mApplication = application;
        mDaoSession = daoSession;
        mBlognoneTopicCache = mDaoSession.getBlognoneTopicCacheDao();
    }

    public static ArrayList<BlognoneTopicCache> getAll(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return new ArrayList<>(mBlognoneTopicCache.queryBuilder()
                    .orderAsc(BlognoneTopicCacheDao.Properties.Datetime)
                    .list());
        } else {
            return new ArrayList<>(mBlognoneTopicCache.queryBuilder()
                    .where(BlognoneTopicCacheDao.Properties.Data.like("%" + keyword + "%"))
                    .orderAsc(BlognoneTopicCacheDao.Properties.Datetime)
                    .list());
        }
    }

    public static boolean isCache(long nodeId) {
        BlognoneTopicCache topic = mBlognoneTopicCache.load(nodeId);
        if (topic != null) {
            return true;
        }
        return false;
    }


    public static BlognoneTopicCache get(long nodeId) {
        return mBlognoneTopicCache.load(nodeId);
    }

    public static BlognoneNodeDao getOnlyNode(long nodeId) {
        BlognoneTopicCache topicCache = get(nodeId);
        if (topicCache != null) {
            Gson gson = new Gson();
            BlognoneNodeDao node = gson.fromJson(topicCache.getData(), BlognoneNodeDao.class);
            return node;
        }
        return null;
    }

    public static ArrayList<BlognoneNodeDao> getAllOnlyNode(String searchKeyword) {
        Gson gson = new Gson();
        ArrayList<BlognoneNodeDao> listNode = new ArrayList<>();
        ArrayList<BlognoneTopicCache> listNodeCache = getAll(searchKeyword);
        for (BlognoneTopicCache t : listNodeCache) {
            BlognoneNodeDao node = gson.fromJson(t.getData(), BlognoneNodeDao.class);
            listNode.add(node);
        }
        return listNode;
    }

//
//    public static ArrayList<BlognoneTags> getFromExchangePairId(long id) {
//        return new ArrayList<>(mBlognoneTagsDao.queryBuilder()
//                .where(BlognoneTagsDao.Properties.Exchange_pair_id.eq(id))
//                .orderAsc(BlognoneTagsDao.Properties.Id)
//                .list());
//    }

    public static long insert(long nodeId, BlognoneNodeDao node) {
        Gson gson = new Gson();

        BlognoneTopicCache topic = new BlognoneTopicCache();
        topic.setId(nodeId);
        topic.setDatetime(Calendar.getInstance().getTimeInMillis());
        topic.setData(gson.toJson(node));
        return insert(topic);
    }

    public static long insert(BlognoneTopicCache c) {
        return mBlognoneTopicCache.insertOrReplace(c);
    }

    public static void delete(long nodeId) {
        mBlognoneTopicCache.deleteByKey(nodeId);
    }

    public static void delete(BlognoneTopicCache c) {
        mBlognoneTopicCache.delete(c);
    }

    public static void update(BlognoneTopicCache c) {
        mBlognoneTopicCache.update(c);
    }

}

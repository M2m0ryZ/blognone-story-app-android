package com.panuwatjan.blognonestory.database;

import android.content.Context;

import com.google.gson.Gson;
import com.panuwatjan.blognonestory.BlognoneJobsCache;
import com.panuwatjan.blognonestory.BlognoneJobsCacheDao;
import com.panuwatjan.blognonestory.BlognoneTopicCacheDao;
import com.panuwatjan.blognonestory.DaoSession;
import com.panuwatjan.blognonestory.MyApplication;
import com.panuwatjan.blognonestory.dao.jobs.JobDataDao;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by benznest on 18-Mar-18.
 */

public class BlognoneJobsCacheDatabase {
    private static Context context;
    private static MyApplication mApplication;
    private static DaoSession mDaoSession;
    private static BlognoneJobsCacheDao mBlognoneJobsCache;

    public static void initialize(Context context, MyApplication application, DaoSession daoSession) {
        BlognoneJobsCacheDatabase.context = context;
        mApplication = application;
        mDaoSession = daoSession;
        mBlognoneJobsCache = mDaoSession.getBlognoneJobsCacheDao();
    }

    public static ArrayList<BlognoneJobsCache> getAll(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return new ArrayList<>(mBlognoneJobsCache.queryBuilder()
                    .orderAsc(BlognoneJobsCacheDao.Properties.Datetime)
                    .list());
        } else {
            return new ArrayList<>(mBlognoneJobsCache.queryBuilder()
                    .where(BlognoneTopicCacheDao.Properties.Data.like("%" + keyword + "%"))
                    .orderAsc(BlognoneJobsCacheDao.Properties.Datetime)
                    .list());
        }
    }

    public static boolean isCache(long nodeId) {
        BlognoneJobsCache job = mBlognoneJobsCache.load(nodeId);
        if (job != null) {
            return true;
        }
        return false;
    }


    public static BlognoneJobsCache get(long nodeId) {
        return mBlognoneJobsCache.load(nodeId);
    }

    public static JobDataDao getOnlyNode(long nodeId) {
        BlognoneJobsCache job = get(nodeId);
        if (job != null) {
            Gson gson = new Gson();
            JobDataDao node = gson.fromJson(job.getData(), JobDataDao.class);
            return node;
        }
        return null;
    }

    public static ArrayList<JobDataDao> getAllOnlyNode(String searchKeyword) {
        Gson gson = new Gson();
        ArrayList<JobDataDao> listNode = new ArrayList<>();
        ArrayList<BlognoneJobsCache> listNodeCache = getAll(searchKeyword);
        for (BlognoneJobsCache t : listNodeCache) {
            JobDataDao node = gson.fromJson(t.getData(), JobDataDao.class);
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

    public static long insert(long nodeId, JobDataDao node) {
        Gson gson = new Gson();

        BlognoneJobsCache job = new BlognoneJobsCache();
        job.setId(nodeId);
        job.setDatetime(Calendar.getInstance().getTimeInMillis());
        job.setData(gson.toJson(node));
        return insert(job);
    }

    public static long insert(BlognoneJobsCache c) {
        return mBlognoneJobsCache.insertOrReplace(c);
    }

    public static void delete(long nodeId) {
        mBlognoneJobsCache.deleteByKey(nodeId);
    }

    public static void delete(BlognoneJobsCache c) {
        mBlognoneJobsCache.delete(c);
    }

    public static void update(BlognoneJobsCache c) {
        mBlognoneJobsCache.update(c);
    }

}

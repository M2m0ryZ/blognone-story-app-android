package com.panuwatjan.blognonestory.database;

import android.content.Context;

import com.google.gson.Gson;
import com.panuwatjan.blognonestory.BlognoneFavoriteJobs;
import com.panuwatjan.blognonestory.BlognoneFavoriteJobsDao;
import com.panuwatjan.blognonestory.BlognoneFavoriteTopicDao;
import com.panuwatjan.blognonestory.DaoSession;
import com.panuwatjan.blognonestory.MyApplication;
import com.panuwatjan.blognonestory.dao.jobs.JobsDao;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by benznest on 18-Mar-18.
 */

public class BlognoneFavoriteJobsDatabase {
    private static Context context;
    private static MyApplication mApplication;
    private static DaoSession mDaoSession;
    private static BlognoneFavoriteJobsDao mBlognoneFavoriteJobsDao;

    public static void initialize(Context context, MyApplication application, DaoSession daoSession) {
        BlognoneFavoriteJobsDatabase.context = context;
        mApplication = application;
        mDaoSession = daoSession;
        mBlognoneFavoriteJobsDao = mDaoSession.getBlognoneFavoriteJobsDao();
    }

    public static void initData() {

    }

    public static ArrayList<BlognoneFavoriteJobs> getAll(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return new ArrayList<>(mBlognoneFavoriteJobsDao.queryBuilder()
                    .orderAsc(BlognoneFavoriteJobsDao.Properties.Datetime)
                    .list());
        } else {
            return new ArrayList<>(mBlognoneFavoriteJobsDao.queryBuilder()
                    .where(BlognoneFavoriteTopicDao.Properties.Data.like("%" + keyword + "%"))
                    .orderAsc(BlognoneFavoriteJobsDao.Properties.Datetime)
                    .list());
        }
    }

    public static ArrayList<JobsDao> getAllOnlyNode(String keyword) {
        Gson gson = new Gson();
        ArrayList<JobsDao> listNode = new ArrayList<>();
        ArrayList<BlognoneFavoriteJobs> listFavorite = getAll(keyword);
        for (BlognoneFavoriteJobs favoriteJobs : listFavorite) {
            JobsDao node = gson.fromJson(favoriteJobs.getData(), JobsDao.class);
            listNode.add(node);
        }
        return listNode;
    }

    public static boolean isFavorite(long nodeId) {
        BlognoneFavoriteJobs job = mBlognoneFavoriteJobsDao.load(nodeId);
        if (job != null) {
            return true;
        }
        return false;
    }

    public static long insert(long nodeId, JobsDao node) {
        Gson gson = new Gson();

        BlognoneFavoriteJobs job = new BlognoneFavoriteJobs();
        job.setId(nodeId);
        job.setDatetime(Calendar.getInstance().getTimeInMillis());
        job.setData(gson.toJson(node));
        return insert(job);
    }

    public static long insert(BlognoneFavoriteJobs c) {
        return mBlognoneFavoriteJobsDao.insertOrReplace(c);
    }

    public static void delete(BlognoneFavoriteJobs c) {
        mBlognoneFavoriteJobsDao.delete(c);
    }

    public static void delete(long nodeId) {
        mBlognoneFavoriteJobsDao.deleteByKey(nodeId);
    }

    public static void update(BlognoneFavoriteJobs c) {
        mBlognoneFavoriteJobsDao.update(c);
    }

}

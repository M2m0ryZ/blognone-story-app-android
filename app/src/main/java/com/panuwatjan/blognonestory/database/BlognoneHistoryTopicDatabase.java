package com.panuwatjan.blognonestory.database;

import android.content.Context;

import com.panuwatjan.blognonestory.BlognoneHistoryTopic;
import com.panuwatjan.blognonestory.BlognoneHistoryTopicDao;
import com.panuwatjan.blognonestory.DaoSession;
import com.panuwatjan.blognonestory.MyApplication;
import com.panuwatjan.blognonestory.dao.BlognoneNodeDao;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by benznest on 18-Mar-18.
 */

public class BlognoneHistoryTopicDatabase {
    private static Context context;
    private static MyApplication mApplication;
    private static DaoSession mDaoSession;
    private static BlognoneHistoryTopicDao mBlognoneHistoryTopicDao;

    public static void initialize(Context context, MyApplication application, DaoSession daoSession) {
        BlognoneHistoryTopicDatabase.context = context;
        mApplication = application;
        mDaoSession = daoSession;
        mBlognoneHistoryTopicDao = mDaoSession.getBlognoneHistoryTopicDao();
    }

    public static ArrayList<BlognoneHistoryTopic> getAll(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return new ArrayList<>(mBlognoneHistoryTopicDao.queryBuilder()
                    .orderAsc(BlognoneHistoryTopicDao.Properties.Datetime)
                    .list());
        } else {
            return new ArrayList<>(mBlognoneHistoryTopicDao.queryBuilder()
                    .where(BlognoneHistoryTopicDao.Properties.Data.like("%" + keyword + "%"))
                    .orderAsc(BlognoneHistoryTopicDao.Properties.Datetime)
                    .list());
        }
    }

    public static List<BlognoneHistoryTopic> get(long nodeId) {
        List<BlognoneHistoryTopic> list = new ArrayList<>();

        list = mBlognoneHistoryTopicDao.queryBuilder()
                .where(BlognoneHistoryTopicDao.Properties.NodeId.eq(nodeId))
                .orderAsc(BlognoneHistoryTopicDao.Properties.Datetime)
                .list();
        return list;
    }

    public static boolean isViewed(long nodeId) {
        if (!get(nodeId).isEmpty()) {
            return true;
        }
        return false;
    }

    public static ArrayList<BlognoneNodeDao> getAllOnlyNode(String searchKeyword) {
        Gson gson = new Gson();
        ArrayList<BlognoneNodeDao> listNode = new ArrayList<>();
        ArrayList<BlognoneHistoryTopic> listNodeHistory = getAll(searchKeyword);
        for (BlognoneHistoryTopic t : listNodeHistory) {
            BlognoneNodeDao node = gson.fromJson(t.getData(), BlognoneNodeDao.class);
            listNode.add(node);
        }
        return listNode;
    }

    public static long insert(long nodeId, BlognoneNodeDao node) {
        Gson gson = new Gson();

        BlognoneHistoryTopic topic = new BlognoneHistoryTopic();
        topic.setNodeId(nodeId);
        topic.setDatetime(Calendar.getInstance().getTimeInMillis());
        topic.setData(gson.toJson(node));
        topic.setCountComment(node.getCountComment());
        return insert(topic);
    }

    public static long insert(BlognoneHistoryTopic c) {
        return mBlognoneHistoryTopicDao.insertOrReplace(c);
    }

    public static void delete(BlognoneHistoryTopic c) {
        mBlognoneHistoryTopicDao.delete(c);
    }

    public static void update(BlognoneHistoryTopic c) {
        mBlognoneHistoryTopicDao.update(c);
    }

}

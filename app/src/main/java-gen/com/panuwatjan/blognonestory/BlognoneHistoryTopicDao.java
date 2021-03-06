package com.panuwatjan.blognonestory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.panuwatjan.blognonestory.BlognoneHistoryTopic;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table BLOGNONE_HISTORY_TOPIC.
*/
public class BlognoneHistoryTopicDao extends AbstractDao<BlognoneHistoryTopic, Long> {

    public static final String TABLENAME = "BLOGNONE_HISTORY_TOPIC";

    /**
     * Properties of entity BlognoneHistoryTopic.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property NodeId = new Property(1, Long.class, "nodeId", false, "NODE_ID");
        public final static Property Data = new Property(2, String.class, "data", false, "DATA");
        public final static Property Datetime = new Property(3, Long.class, "datetime", false, "DATETIME");
        public final static Property CountComment = new Property(4, Integer.class, "countComment", false, "COUNT_COMMENT");
    };


    public BlognoneHistoryTopicDao(DaoConfig config) {
        super(config);
    }
    
    public BlognoneHistoryTopicDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'BLOGNONE_HISTORY_TOPIC' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NODE_ID' INTEGER," + // 1: nodeId
                "'DATA' TEXT," + // 2: data
                "'DATETIME' INTEGER," + // 3: datetime
                "'COUNT_COMMENT' INTEGER);"); // 4: countComment
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'BLOGNONE_HISTORY_TOPIC'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BlognoneHistoryTopic entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long nodeId = entity.getNodeId();
        if (nodeId != null) {
            stmt.bindLong(2, nodeId);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(3, data);
        }
 
        Long datetime = entity.getDatetime();
        if (datetime != null) {
            stmt.bindLong(4, datetime);
        }
 
        Integer countComment = entity.getCountComment();
        if (countComment != null) {
            stmt.bindLong(5, countComment);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public BlognoneHistoryTopic readEntity(Cursor cursor, int offset) {
        BlognoneHistoryTopic entity = new BlognoneHistoryTopic( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // nodeId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // data
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // datetime
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4) // countComment
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BlognoneHistoryTopic entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNodeId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setData(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDatetime(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setCountComment(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(BlognoneHistoryTopic entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(BlognoneHistoryTopic entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}

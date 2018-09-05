package com.benznestdeveloper.blognonestory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table BLOGNONE_TOPIC_CACHE.
*/
public class BlognoneTopicCacheDao extends AbstractDao<BlognoneTopicCache, Long> {

    public static final String TABLENAME = "BLOGNONE_TOPIC_CACHE";

    /**
     * Properties of entity BlognoneTopicCache.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Data = new Property(1, String.class, "data", false, "DATA");
        public final static Property Datetime = new Property(2, Long.class, "datetime", false, "DATETIME");
    };


    public BlognoneTopicCacheDao(DaoConfig config) {
        super(config);
    }
    
    public BlognoneTopicCacheDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'BLOGNONE_TOPIC_CACHE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'DATA' TEXT," + // 1: data
                "'DATETIME' INTEGER);"); // 2: datetime
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'BLOGNONE_TOPIC_CACHE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BlognoneTopicCache entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(2, data);
        }
 
        Long datetime = entity.getDatetime();
        if (datetime != null) {
            stmt.bindLong(3, datetime);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public BlognoneTopicCache readEntity(Cursor cursor, int offset) {
        BlognoneTopicCache entity = new BlognoneTopicCache( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // data
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // datetime
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BlognoneTopicCache entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setData(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDatetime(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(BlognoneTopicCache entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(BlognoneTopicCache entity) {
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
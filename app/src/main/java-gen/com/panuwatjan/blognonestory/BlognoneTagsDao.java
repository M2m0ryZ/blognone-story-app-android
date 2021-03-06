package com.panuwatjan.blognonestory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.panuwatjan.blognonestory.BlognoneTags;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table BLOGNONE_TAGS.
*/
public class BlognoneTagsDao extends AbstractDao<BlognoneTags, Long> {

    public static final String TABLENAME = "BLOGNONE_TAGS";

    /**
     * Properties of entity BlognoneTags.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Icon = new Property(2, String.class, "icon", false, "ICON");
        public final static Property Favorite = new Property(3, Boolean.class, "favorite", false, "FAVORITE");
        public final static Property Endpoint = new Property(4, String.class, "endpoint", false, "ENDPOINT");
    };


    public BlognoneTagsDao(DaoConfig config) {
        super(config);
    }
    
    public BlognoneTagsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'BLOGNONE_TAGS' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NAME' TEXT," + // 1: name
                "'ICON' TEXT," + // 2: icon
                "'FAVORITE' INTEGER," + // 3: favorite
                "'ENDPOINT' TEXT);"); // 4: endpoint
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'BLOGNONE_TAGS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BlognoneTags entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(3, icon);
        }
 
        Boolean favorite = entity.getFavorite();
        if (favorite != null) {
            stmt.bindLong(4, favorite ? 1l: 0l);
        }
 
        String endpoint = entity.getEndpoint();
        if (endpoint != null) {
            stmt.bindString(5, endpoint);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public BlognoneTags readEntity(Cursor cursor, int offset) {
        BlognoneTags entity = new BlognoneTags( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // icon
            cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0, // favorite
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // endpoint
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BlognoneTags entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIcon(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFavorite(cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0);
        entity.setEndpoint(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(BlognoneTags entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(BlognoneTags entity) {
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

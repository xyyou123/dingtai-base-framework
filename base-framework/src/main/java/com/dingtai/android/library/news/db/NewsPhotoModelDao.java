package com.dingtai.android.library.news.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.dingtai.android.library.news.model.NewsPhotoModel;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "NEWS_PHOTO_MODEL".
*/
public class NewsPhotoModelDao extends AbstractDao<NewsPhotoModel, Long> {

    public static final String TABLENAME = "NEWS_PHOTO_MODEL";

    /**
     * Properties of entity NewsPhotoModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property RID = new Property(1, String.class, "RID", false, "RID");
        public final static Property PhotoTitle = new Property(2, String.class, "PhotoTitle", false, "PHOTO_TITLE");
        public final static Property PhotoUrl = new Property(3, String.class, "PhotoUrl", false, "PHOTO_URL");
        public final static Property PhotoDescription = new Property(4, String.class, "PhotoDescription", false, "PHOTO_DESCRIPTION");
        public final static Property CreateTime = new Property(5, String.class, "CreateTime", false, "CREATE_TIME");
    }


    public NewsPhotoModelDao(DaoConfig config) {
        super(config);
    }
    
    public NewsPhotoModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_PHOTO_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"RID\" TEXT," + // 1: RID
                "\"PHOTO_TITLE\" TEXT," + // 2: PhotoTitle
                "\"PHOTO_URL\" TEXT," + // 3: PhotoUrl
                "\"PHOTO_DESCRIPTION\" TEXT," + // 4: PhotoDescription
                "\"CREATE_TIME\" TEXT);"); // 5: CreateTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_PHOTO_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NewsPhotoModel entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String RID = entity.getRID();
        if (RID != null) {
            stmt.bindString(2, RID);
        }
 
        String PhotoTitle = entity.getPhotoTitle();
        if (PhotoTitle != null) {
            stmt.bindString(3, PhotoTitle);
        }
 
        String PhotoUrl = entity.getPhotoUrl();
        if (PhotoUrl != null) {
            stmt.bindString(4, PhotoUrl);
        }
 
        String PhotoDescription = entity.getPhotoDescription();
        if (PhotoDescription != null) {
            stmt.bindString(5, PhotoDescription);
        }
 
        String CreateTime = entity.getCreateTime();
        if (CreateTime != null) {
            stmt.bindString(6, CreateTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NewsPhotoModel entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String RID = entity.getRID();
        if (RID != null) {
            stmt.bindString(2, RID);
        }
 
        String PhotoTitle = entity.getPhotoTitle();
        if (PhotoTitle != null) {
            stmt.bindString(3, PhotoTitle);
        }
 
        String PhotoUrl = entity.getPhotoUrl();
        if (PhotoUrl != null) {
            stmt.bindString(4, PhotoUrl);
        }
 
        String PhotoDescription = entity.getPhotoDescription();
        if (PhotoDescription != null) {
            stmt.bindString(5, PhotoDescription);
        }
 
        String CreateTime = entity.getCreateTime();
        if (CreateTime != null) {
            stmt.bindString(6, CreateTime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NewsPhotoModel readEntity(Cursor cursor, int offset) {
        NewsPhotoModel entity = new NewsPhotoModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // RID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // PhotoTitle
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // PhotoUrl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // PhotoDescription
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // CreateTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NewsPhotoModel entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPhotoTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPhotoUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhotoDescription(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCreateTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NewsPhotoModel entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NewsPhotoModel entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NewsPhotoModel entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
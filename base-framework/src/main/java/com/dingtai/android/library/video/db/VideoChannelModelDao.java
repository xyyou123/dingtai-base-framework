package com.dingtai.android.library.video.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.dingtai.android.library.video.model.VideoChannelModel;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "VIDEO_CHANNEL_MODEL".
*/
public class VideoChannelModelDao extends AbstractDao<VideoChannelModel, Long> {

    public static final String TABLENAME = "VIDEO_CHANNEL_MODEL";

    /**
     * Properties of entity VideoChannelModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property ID = new Property(1, String.class, "ID", false, "ID");
        public final static Property CreateTime = new Property(2, String.class, "CreateTime", false, "CREATE_TIME");
        public final static Property Name = new Property(3, String.class, "Name", false, "NAME");
        public final static Property ImageUrl = new Property(4, String.class, "ImageUrl", false, "IMAGE_URL");
        public final static Property Tag = new Property(5, String.class, "Tag", false, "TAG");
        public final static Property Detail = new Property(6, String.class, "Detail", false, "DETAIL");
        public final static Property Number = new Property(7, String.class, "Number", false, "NUMBER");
        public final static Property FileCount = new Property(8, String.class, "FileCount", false, "FILE_COUNT");
        public final static Property NewCount = new Property(9, String.class, "NewCount", false, "NEW_COUNT");
        public final static Property AdID = new Property(10, String.class, "AdID", false, "AD_ID");
        public final static Property ParentID = new Property(11, String.class, "ParentID", false, "PARENT_ID");
    }


    public VideoChannelModelDao(DaoConfig config) {
        super(config);
    }
    
    public VideoChannelModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VIDEO_CHANNEL_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"ID\" TEXT," + // 1: ID
                "\"CREATE_TIME\" TEXT," + // 2: CreateTime
                "\"NAME\" TEXT," + // 3: Name
                "\"IMAGE_URL\" TEXT," + // 4: ImageUrl
                "\"TAG\" TEXT," + // 5: Tag
                "\"DETAIL\" TEXT," + // 6: Detail
                "\"NUMBER\" TEXT," + // 7: Number
                "\"FILE_COUNT\" TEXT," + // 8: FileCount
                "\"NEW_COUNT\" TEXT," + // 9: NewCount
                "\"AD_ID\" TEXT," + // 10: AdID
                "\"PARENT_ID\" TEXT);"); // 11: ParentID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VIDEO_CHANNEL_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VideoChannelModel entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(2, ID);
        }
 
        String CreateTime = entity.getCreateTime();
        if (CreateTime != null) {
            stmt.bindString(3, CreateTime);
        }
 
        String Name = entity.getName();
        if (Name != null) {
            stmt.bindString(4, Name);
        }
 
        String ImageUrl = entity.getImageUrl();
        if (ImageUrl != null) {
            stmt.bindString(5, ImageUrl);
        }
 
        String Tag = entity.getTag();
        if (Tag != null) {
            stmt.bindString(6, Tag);
        }
 
        String Detail = entity.getDetail();
        if (Detail != null) {
            stmt.bindString(7, Detail);
        }
 
        String Number = entity.getNumber();
        if (Number != null) {
            stmt.bindString(8, Number);
        }
 
        String FileCount = entity.getFileCount();
        if (FileCount != null) {
            stmt.bindString(9, FileCount);
        }
 
        String NewCount = entity.getNewCount();
        if (NewCount != null) {
            stmt.bindString(10, NewCount);
        }
 
        String AdID = entity.getAdID();
        if (AdID != null) {
            stmt.bindString(11, AdID);
        }
 
        String ParentID = entity.getParentID();
        if (ParentID != null) {
            stmt.bindString(12, ParentID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VideoChannelModel entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(2, ID);
        }
 
        String CreateTime = entity.getCreateTime();
        if (CreateTime != null) {
            stmt.bindString(3, CreateTime);
        }
 
        String Name = entity.getName();
        if (Name != null) {
            stmt.bindString(4, Name);
        }
 
        String ImageUrl = entity.getImageUrl();
        if (ImageUrl != null) {
            stmt.bindString(5, ImageUrl);
        }
 
        String Tag = entity.getTag();
        if (Tag != null) {
            stmt.bindString(6, Tag);
        }
 
        String Detail = entity.getDetail();
        if (Detail != null) {
            stmt.bindString(7, Detail);
        }
 
        String Number = entity.getNumber();
        if (Number != null) {
            stmt.bindString(8, Number);
        }
 
        String FileCount = entity.getFileCount();
        if (FileCount != null) {
            stmt.bindString(9, FileCount);
        }
 
        String NewCount = entity.getNewCount();
        if (NewCount != null) {
            stmt.bindString(10, NewCount);
        }
 
        String AdID = entity.getAdID();
        if (AdID != null) {
            stmt.bindString(11, AdID);
        }
 
        String ParentID = entity.getParentID();
        if (ParentID != null) {
            stmt.bindString(12, ParentID);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VideoChannelModel readEntity(Cursor cursor, int offset) {
        VideoChannelModel entity = new VideoChannelModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // CreateTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // ImageUrl
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // Tag
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // Detail
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // Number
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // FileCount
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // NewCount
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // AdID
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11) // ParentID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VideoChannelModel entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreateTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setImageUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTag(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDetail(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setNumber(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFileCount(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setNewCount(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setAdID(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setParentID(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VideoChannelModel entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VideoChannelModel entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VideoChannelModel entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

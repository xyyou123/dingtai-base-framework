package com.dingtai.android.library.model.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.dingtai.android.library.model.models.NewsCollectModel;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "NEWS_COLLECT_MODEL".
*/
public class NewsCollectModelDao extends AbstractDao<NewsCollectModel, Long> {

    public static final String TABLENAME = "NEWS_COLLECT_MODEL";

    /**
     * Properties of entity NewsCollectModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property ID = new Property(1, String.class, "ID", false, "ID");
        public final static Property CollectID = new Property(2, String.class, "CollectID", false, "COLLECT_ID");
        public final static Property UploadPicNames = new Property(3, String.class, "UploadPicNames", false, "UPLOAD_PIC_NAMES");
        public final static Property ChID = new Property(4, String.class, "ChID", false, "CH_ID");
        public final static Property UpdateTime = new Property(5, String.class, "UpdateTime", false, "UPDATE_TIME");
        public final static Property IsComment = new Property(6, String.class, "IsComment", false, "IS_COMMENT");
        public final static Property ResourceGUID = new Property(7, String.class, "ResourceGUID", false, "RESOURCE_GUID");
        public final static Property Title = new Property(8, String.class, "Title", false, "TITLE");
        public final static Property ShowOrder = new Property(9, String.class, "ShowOrder", false, "SHOW_ORDER");
        public final static Property Summary = new Property(10, String.class, "Summary", false, "SUMMARY");
        public final static Property ReadNo = new Property(11, String.class, "ReadNo", false, "READ_NO");
        public final static Property SmallPicUrl = new Property(12, String.class, "SmallPicUrl", false, "SMALL_PIC_URL");
        public final static Property AuditTime = new Property(13, String.class, "AuditTime", false, "AUDIT_TIME");
        public final static Property ResourceType = new Property(14, String.class, "ResourceType", false, "RESOURCE_TYPE");
        public final static Property RPID = new Property(15, String.class, "RPID", false, "RPID");
        public final static Property CreateTime = new Property(16, String.class, "CreateTime", false, "CREATE_TIME");
        public final static Property Latitude = new Property(17, String.class, "Latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(18, String.class, "Longitude", false, "LONGITUDE");
        public final static Property ResourceFlag = new Property(19, String.class, "ResourceFlag", false, "RESOURCE_FLAG");
    }


    public NewsCollectModelDao(DaoConfig config) {
        super(config);
    }
    
    public NewsCollectModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_COLLECT_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"ID\" TEXT UNIQUE ," + // 1: ID
                "\"COLLECT_ID\" TEXT," + // 2: CollectID
                "\"UPLOAD_PIC_NAMES\" TEXT," + // 3: UploadPicNames
                "\"CH_ID\" TEXT," + // 4: ChID
                "\"UPDATE_TIME\" TEXT," + // 5: UpdateTime
                "\"IS_COMMENT\" TEXT," + // 6: IsComment
                "\"RESOURCE_GUID\" TEXT," + // 7: ResourceGUID
                "\"TITLE\" TEXT," + // 8: Title
                "\"SHOW_ORDER\" TEXT," + // 9: ShowOrder
                "\"SUMMARY\" TEXT," + // 10: Summary
                "\"READ_NO\" TEXT," + // 11: ReadNo
                "\"SMALL_PIC_URL\" TEXT," + // 12: SmallPicUrl
                "\"AUDIT_TIME\" TEXT," + // 13: AuditTime
                "\"RESOURCE_TYPE\" TEXT," + // 14: ResourceType
                "\"RPID\" TEXT," + // 15: RPID
                "\"CREATE_TIME\" TEXT," + // 16: CreateTime
                "\"LATITUDE\" TEXT," + // 17: Latitude
                "\"LONGITUDE\" TEXT," + // 18: Longitude
                "\"RESOURCE_FLAG\" TEXT);"); // 19: ResourceFlag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_COLLECT_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NewsCollectModel entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(2, ID);
        }
 
        String CollectID = entity.getCollectID();
        if (CollectID != null) {
            stmt.bindString(3, CollectID);
        }
 
        String UploadPicNames = entity.getUploadPicNames();
        if (UploadPicNames != null) {
            stmt.bindString(4, UploadPicNames);
        }
 
        String ChID = entity.getChID();
        if (ChID != null) {
            stmt.bindString(5, ChID);
        }
 
        String UpdateTime = entity.getUpdateTime();
        if (UpdateTime != null) {
            stmt.bindString(6, UpdateTime);
        }
 
        String IsComment = entity.getIsComment();
        if (IsComment != null) {
            stmt.bindString(7, IsComment);
        }
 
        String ResourceGUID = entity.getResourceGUID();
        if (ResourceGUID != null) {
            stmt.bindString(8, ResourceGUID);
        }
 
        String Title = entity.getTitle();
        if (Title != null) {
            stmt.bindString(9, Title);
        }
 
        String ShowOrder = entity.getShowOrder();
        if (ShowOrder != null) {
            stmt.bindString(10, ShowOrder);
        }
 
        String Summary = entity.getSummary();
        if (Summary != null) {
            stmt.bindString(11, Summary);
        }
 
        String ReadNo = entity.getReadNo();
        if (ReadNo != null) {
            stmt.bindString(12, ReadNo);
        }
 
        String SmallPicUrl = entity.getSmallPicUrl();
        if (SmallPicUrl != null) {
            stmt.bindString(13, SmallPicUrl);
        }
 
        String AuditTime = entity.getAuditTime();
        if (AuditTime != null) {
            stmt.bindString(14, AuditTime);
        }
 
        String ResourceType = entity.getResourceType();
        if (ResourceType != null) {
            stmt.bindString(15, ResourceType);
        }
 
        String RPID = entity.getRPID();
        if (RPID != null) {
            stmt.bindString(16, RPID);
        }
 
        String CreateTime = entity.getCreateTime();
        if (CreateTime != null) {
            stmt.bindString(17, CreateTime);
        }
 
        String Latitude = entity.getLatitude();
        if (Latitude != null) {
            stmt.bindString(18, Latitude);
        }
 
        String Longitude = entity.getLongitude();
        if (Longitude != null) {
            stmt.bindString(19, Longitude);
        }
 
        String ResourceFlag = entity.getResourceFlag();
        if (ResourceFlag != null) {
            stmt.bindString(20, ResourceFlag);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NewsCollectModel entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(2, ID);
        }
 
        String CollectID = entity.getCollectID();
        if (CollectID != null) {
            stmt.bindString(3, CollectID);
        }
 
        String UploadPicNames = entity.getUploadPicNames();
        if (UploadPicNames != null) {
            stmt.bindString(4, UploadPicNames);
        }
 
        String ChID = entity.getChID();
        if (ChID != null) {
            stmt.bindString(5, ChID);
        }
 
        String UpdateTime = entity.getUpdateTime();
        if (UpdateTime != null) {
            stmt.bindString(6, UpdateTime);
        }
 
        String IsComment = entity.getIsComment();
        if (IsComment != null) {
            stmt.bindString(7, IsComment);
        }
 
        String ResourceGUID = entity.getResourceGUID();
        if (ResourceGUID != null) {
            stmt.bindString(8, ResourceGUID);
        }
 
        String Title = entity.getTitle();
        if (Title != null) {
            stmt.bindString(9, Title);
        }
 
        String ShowOrder = entity.getShowOrder();
        if (ShowOrder != null) {
            stmt.bindString(10, ShowOrder);
        }
 
        String Summary = entity.getSummary();
        if (Summary != null) {
            stmt.bindString(11, Summary);
        }
 
        String ReadNo = entity.getReadNo();
        if (ReadNo != null) {
            stmt.bindString(12, ReadNo);
        }
 
        String SmallPicUrl = entity.getSmallPicUrl();
        if (SmallPicUrl != null) {
            stmt.bindString(13, SmallPicUrl);
        }
 
        String AuditTime = entity.getAuditTime();
        if (AuditTime != null) {
            stmt.bindString(14, AuditTime);
        }
 
        String ResourceType = entity.getResourceType();
        if (ResourceType != null) {
            stmt.bindString(15, ResourceType);
        }
 
        String RPID = entity.getRPID();
        if (RPID != null) {
            stmt.bindString(16, RPID);
        }
 
        String CreateTime = entity.getCreateTime();
        if (CreateTime != null) {
            stmt.bindString(17, CreateTime);
        }
 
        String Latitude = entity.getLatitude();
        if (Latitude != null) {
            stmt.bindString(18, Latitude);
        }
 
        String Longitude = entity.getLongitude();
        if (Longitude != null) {
            stmt.bindString(19, Longitude);
        }
 
        String ResourceFlag = entity.getResourceFlag();
        if (ResourceFlag != null) {
            stmt.bindString(20, ResourceFlag);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NewsCollectModel readEntity(Cursor cursor, int offset) {
        NewsCollectModel entity = new NewsCollectModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // CollectID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // UploadPicNames
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // ChID
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // UpdateTime
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // IsComment
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // ResourceGUID
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // Title
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // ShowOrder
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // Summary
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // ReadNo
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // SmallPicUrl
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // AuditTime
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // ResourceType
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // RPID
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // CreateTime
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // Latitude
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // Longitude
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19) // ResourceFlag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NewsCollectModel entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCollectID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUploadPicNames(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setChID(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUpdateTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIsComment(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setResourceGUID(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTitle(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setShowOrder(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSummary(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setReadNo(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setSmallPicUrl(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setAuditTime(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setResourceType(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setRPID(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setCreateTime(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setLatitude(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setLongitude(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setResourceFlag(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NewsCollectModel entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NewsCollectModel entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NewsCollectModel entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

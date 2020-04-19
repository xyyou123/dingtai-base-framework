package com.lnr.android.base.framework.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.dingtai.android.library.video.model.VodProgramModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VOD_PROGRAM_MODEL".
*/
public class VodProgramModelDao extends AbstractDao<VodProgramModel, Long> {

    public static final String TABLENAME = "VOD_PROGRAM_MODEL";

    /**
     * Properties of entity VodProgramModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property ID = new Property(1, String.class, "ID", false, "ID");
        public final static Property CreateTime = new Property(2, String.class, "CreateTime", false, "CREATE_TIME");
        public final static Property ProgramContentName = new Property(3, String.class, "ProgramContentName", false, "PROGRAM_CONTENT_NAME");
        public final static Property ProgramContentUrl = new Property(4, String.class, "ProgramContentUrl", false, "PROGRAM_CONTENT_URL");
        public final static Property ProgramDate = new Property(5, String.class, "ProgramDate", false, "PROGRAM_DATE");
        public final static Property ProgramContentLogo = new Property(6, String.class, "ProgramContentLogo", false, "PROGRAM_CONTENT_LOGO");
        public final static Property ProgramCommentNum = new Property(7, String.class, "ProgramCommentNum", false, "PROGRAM_COMMENT_NUM");
        public final static Property VODName = new Property(8, String.class, "VODName", false, "VODNAME");
        public final static Property VODType = new Property(9, String.class, "VODType", false, "VODTYPE");
        public final static Property GoodPoint = new Property(10, String.class, "GoodPoint", false, "GOOD_POINT");
        public final static Property VODCPID = new Property(11, String.class, "VODCPID", false, "VODCPID");
        public final static Property Status = new Property(12, String.class, "Status", false, "STATUS");
        public final static Property ReMark = new Property(13, String.class, "ReMark", false, "RE_MARK");
        public final static Property ReadNo = new Property(14, String.class, "ReadNo", false, "READ_NO");
        public final static Property Authenticationflag = new Property(15, String.class, "authenticationflag", false, "AUTHENTICATIONFLAG");
    }


    public VodProgramModelDao(DaoConfig config) {
        super(config);
    }
    
    public VodProgramModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VOD_PROGRAM_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"ID\" TEXT," + // 1: ID
                "\"CREATE_TIME\" TEXT," + // 2: CreateTime
                "\"PROGRAM_CONTENT_NAME\" TEXT," + // 3: ProgramContentName
                "\"PROGRAM_CONTENT_URL\" TEXT," + // 4: ProgramContentUrl
                "\"PROGRAM_DATE\" TEXT," + // 5: ProgramDate
                "\"PROGRAM_CONTENT_LOGO\" TEXT," + // 6: ProgramContentLogo
                "\"PROGRAM_COMMENT_NUM\" TEXT," + // 7: ProgramCommentNum
                "\"VODNAME\" TEXT," + // 8: VODName
                "\"VODTYPE\" TEXT," + // 9: VODType
                "\"GOOD_POINT\" TEXT," + // 10: GoodPoint
                "\"VODCPID\" TEXT," + // 11: VODCPID
                "\"STATUS\" TEXT," + // 12: Status
                "\"RE_MARK\" TEXT," + // 13: ReMark
                "\"READ_NO\" TEXT," + // 14: ReadNo
                "\"AUTHENTICATIONFLAG\" TEXT);"); // 15: authenticationflag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VOD_PROGRAM_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VodProgramModel entity) {
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
 
        String ProgramContentName = entity.getProgramContentName();
        if (ProgramContentName != null) {
            stmt.bindString(4, ProgramContentName);
        }
 
        String ProgramContentUrl = entity.getProgramContentUrl();
        if (ProgramContentUrl != null) {
            stmt.bindString(5, ProgramContentUrl);
        }
 
        String ProgramDate = entity.getProgramDate();
        if (ProgramDate != null) {
            stmt.bindString(6, ProgramDate);
        }
 
        String ProgramContentLogo = entity.getProgramContentLogo();
        if (ProgramContentLogo != null) {
            stmt.bindString(7, ProgramContentLogo);
        }
 
        String ProgramCommentNum = entity.getProgramCommentNum();
        if (ProgramCommentNum != null) {
            stmt.bindString(8, ProgramCommentNum);
        }
 
        String VODName = entity.getVODName();
        if (VODName != null) {
            stmt.bindString(9, VODName);
        }
 
        String VODType = entity.getVODType();
        if (VODType != null) {
            stmt.bindString(10, VODType);
        }
 
        String GoodPoint = entity.getGoodPoint();
        if (GoodPoint != null) {
            stmt.bindString(11, GoodPoint);
        }
 
        String VODCPID = entity.getVODCPID();
        if (VODCPID != null) {
            stmt.bindString(12, VODCPID);
        }
 
        String Status = entity.getStatus();
        if (Status != null) {
            stmt.bindString(13, Status);
        }
 
        String ReMark = entity.getReMark();
        if (ReMark != null) {
            stmt.bindString(14, ReMark);
        }
 
        String ReadNo = entity.getReadNo();
        if (ReadNo != null) {
            stmt.bindString(15, ReadNo);
        }
 
        String authenticationflag = entity.getAuthenticationflag();
        if (authenticationflag != null) {
            stmt.bindString(16, authenticationflag);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VodProgramModel entity) {
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
 
        String ProgramContentName = entity.getProgramContentName();
        if (ProgramContentName != null) {
            stmt.bindString(4, ProgramContentName);
        }
 
        String ProgramContentUrl = entity.getProgramContentUrl();
        if (ProgramContentUrl != null) {
            stmt.bindString(5, ProgramContentUrl);
        }
 
        String ProgramDate = entity.getProgramDate();
        if (ProgramDate != null) {
            stmt.bindString(6, ProgramDate);
        }
 
        String ProgramContentLogo = entity.getProgramContentLogo();
        if (ProgramContentLogo != null) {
            stmt.bindString(7, ProgramContentLogo);
        }
 
        String ProgramCommentNum = entity.getProgramCommentNum();
        if (ProgramCommentNum != null) {
            stmt.bindString(8, ProgramCommentNum);
        }
 
        String VODName = entity.getVODName();
        if (VODName != null) {
            stmt.bindString(9, VODName);
        }
 
        String VODType = entity.getVODType();
        if (VODType != null) {
            stmt.bindString(10, VODType);
        }
 
        String GoodPoint = entity.getGoodPoint();
        if (GoodPoint != null) {
            stmt.bindString(11, GoodPoint);
        }
 
        String VODCPID = entity.getVODCPID();
        if (VODCPID != null) {
            stmt.bindString(12, VODCPID);
        }
 
        String Status = entity.getStatus();
        if (Status != null) {
            stmt.bindString(13, Status);
        }
 
        String ReMark = entity.getReMark();
        if (ReMark != null) {
            stmt.bindString(14, ReMark);
        }
 
        String ReadNo = entity.getReadNo();
        if (ReadNo != null) {
            stmt.bindString(15, ReadNo);
        }
 
        String authenticationflag = entity.getAuthenticationflag();
        if (authenticationflag != null) {
            stmt.bindString(16, authenticationflag);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VodProgramModel readEntity(Cursor cursor, int offset) {
        VodProgramModel entity = new VodProgramModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // CreateTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // ProgramContentName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // ProgramContentUrl
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // ProgramDate
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // ProgramContentLogo
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // ProgramCommentNum
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // VODName
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // VODType
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // GoodPoint
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // VODCPID
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // Status
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // ReMark
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // ReadNo
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15) // authenticationflag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VodProgramModel entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreateTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProgramContentName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProgramContentUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProgramDate(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setProgramContentLogo(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setProgramCommentNum(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setVODName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setVODType(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setGoodPoint(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setVODCPID(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setStatus(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setReMark(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setReadNo(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setAuthenticationflag(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VodProgramModel entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VodProgramModel entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VodProgramModel entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
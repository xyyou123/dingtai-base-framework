package com.dingtai.android.library.video.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.dingtai.android.library.video.model.LiveProgramModel;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "LIVE_PROGRAM_MODEL".
*/
public class LiveProgramModelDao extends AbstractDao<LiveProgramModel, Long> {

    public static final String TABLENAME = "LIVE_PROGRAM_MODEL";

    /**
     * Properties of entity LiveProgramModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property ID = new Property(1, String.class, "ID", false, "ID");
        public final static Property CreateTime = new Property(2, String.class, "CreateTime", false, "CREATE_TIME");
        public final static Property ProgramName = new Property(3, String.class, "ProgramName", false, "PROGRAM_NAME");
        public final static Property Week = new Property(4, String.class, "Week", false, "WEEK");
        public final static Property PlayTime = new Property(5, String.class, "PlayTime", false, "PLAY_TIME");
        public final static Property EndTime = new Property(6, String.class, "EndTime", false, "END_TIME");
        public final static Property LChID = new Property(7, String.class, "LChID", false, "LCH_ID");
        public final static Property Status = new Property(8, String.class, "Status", false, "STATUS");
        public final static Property ReMark = new Property(9, String.class, "ReMark", false, "RE_MARK");
        public final static Property Url = new Property(10, String.class, "Url", false, "URL");
        public final static Property NowWeek = new Property(11, String.class, "NowWeek", false, "NOW_WEEK");
        public final static Property Authenticationflag = new Property(12, String.class, "authenticationflag", false, "AUTHENTICATIONFLAG");
    }


    public LiveProgramModelDao(DaoConfig config) {
        super(config);
    }
    
    public LiveProgramModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LIVE_PROGRAM_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"ID\" TEXT," + // 1: ID
                "\"CREATE_TIME\" TEXT," + // 2: CreateTime
                "\"PROGRAM_NAME\" TEXT," + // 3: ProgramName
                "\"WEEK\" TEXT," + // 4: Week
                "\"PLAY_TIME\" TEXT," + // 5: PlayTime
                "\"END_TIME\" TEXT," + // 6: EndTime
                "\"LCH_ID\" TEXT," + // 7: LChID
                "\"STATUS\" TEXT," + // 8: Status
                "\"RE_MARK\" TEXT," + // 9: ReMark
                "\"URL\" TEXT," + // 10: Url
                "\"NOW_WEEK\" TEXT," + // 11: NowWeek
                "\"AUTHENTICATIONFLAG\" TEXT);"); // 12: authenticationflag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LIVE_PROGRAM_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LiveProgramModel entity) {
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
 
        String ProgramName = entity.getProgramName();
        if (ProgramName != null) {
            stmt.bindString(4, ProgramName);
        }
 
        String Week = entity.getWeek();
        if (Week != null) {
            stmt.bindString(5, Week);
        }
 
        String PlayTime = entity.getPlayTime();
        if (PlayTime != null) {
            stmt.bindString(6, PlayTime);
        }
 
        String EndTime = entity.getEndTime();
        if (EndTime != null) {
            stmt.bindString(7, EndTime);
        }
 
        String LChID = entity.getLChID();
        if (LChID != null) {
            stmt.bindString(8, LChID);
        }
 
        String Status = entity.getStatus();
        if (Status != null) {
            stmt.bindString(9, Status);
        }
 
        String ReMark = entity.getReMark();
        if (ReMark != null) {
            stmt.bindString(10, ReMark);
        }
 
        String Url = entity.getUrl();
        if (Url != null) {
            stmt.bindString(11, Url);
        }
 
        String NowWeek = entity.getNowWeek();
        if (NowWeek != null) {
            stmt.bindString(12, NowWeek);
        }
 
        String authenticationflag = entity.getAuthenticationflag();
        if (authenticationflag != null) {
            stmt.bindString(13, authenticationflag);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LiveProgramModel entity) {
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
 
        String ProgramName = entity.getProgramName();
        if (ProgramName != null) {
            stmt.bindString(4, ProgramName);
        }
 
        String Week = entity.getWeek();
        if (Week != null) {
            stmt.bindString(5, Week);
        }
 
        String PlayTime = entity.getPlayTime();
        if (PlayTime != null) {
            stmt.bindString(6, PlayTime);
        }
 
        String EndTime = entity.getEndTime();
        if (EndTime != null) {
            stmt.bindString(7, EndTime);
        }
 
        String LChID = entity.getLChID();
        if (LChID != null) {
            stmt.bindString(8, LChID);
        }
 
        String Status = entity.getStatus();
        if (Status != null) {
            stmt.bindString(9, Status);
        }
 
        String ReMark = entity.getReMark();
        if (ReMark != null) {
            stmt.bindString(10, ReMark);
        }
 
        String Url = entity.getUrl();
        if (Url != null) {
            stmt.bindString(11, Url);
        }
 
        String NowWeek = entity.getNowWeek();
        if (NowWeek != null) {
            stmt.bindString(12, NowWeek);
        }
 
        String authenticationflag = entity.getAuthenticationflag();
        if (authenticationflag != null) {
            stmt.bindString(13, authenticationflag);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LiveProgramModel readEntity(Cursor cursor, int offset) {
        LiveProgramModel entity = new LiveProgramModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // CreateTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // ProgramName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Week
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // PlayTime
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // EndTime
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // LChID
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // Status
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // ReMark
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // Url
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // NowWeek
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // authenticationflag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LiveProgramModel entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreateTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProgramName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setWeek(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPlayTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setEndTime(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLChID(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setStatus(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setReMark(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setUrl(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setNowWeek(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setAuthenticationflag(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LiveProgramModel entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LiveProgramModel entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LiveProgramModel entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

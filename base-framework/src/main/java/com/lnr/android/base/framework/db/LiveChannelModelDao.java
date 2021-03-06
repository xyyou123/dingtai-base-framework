package com.lnr.android.base.framework.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.dingtai.android.library.video.model.LiveChildTab.LiveChildTabConConverter;
import java.util.List;

import com.dingtai.android.library.video.model.LiveChannelModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LIVE_CHANNEL_MODEL".
*/
public class LiveChannelModelDao extends AbstractDao<LiveChannelModel, Long> {

    public static final String TABLENAME = "LIVE_CHANNEL_MODEL";

    /**
     * Properties of entity LiveChannelModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property ID = new Property(1, String.class, "ID", false, "ID");
        public final static Property CreateTime = new Property(2, String.class, "CreateTime", false, "CREATE_TIME");
        public final static Property LiveChannelName = new Property(3, String.class, "LiveChannelName", false, "LIVE_CHANNEL_NAME");
        public final static Property LiveImageUrl = new Property(4, String.class, "LiveImageUrl", false, "LIVE_IMAGE_URL");
        public final static Property LiveProgramName = new Property(5, String.class, "LiveProgramName", false, "LIVE_PROGRAM_NAME");
        public final static Property LiveProgramDate = new Property(6, String.class, "LiveProgramDate", false, "LIVE_PROGRAM_DATE");
        public final static Property ParentID = new Property(7, String.class, "ParentID", false, "PARENT_ID");
        public final static Property IsHide = new Property(8, String.class, "IsHide", false, "IS_HIDE");
        public final static Property IsAD = new Property(9, String.class, "IsAD", false, "IS_AD");
        public final static Property IsShowHome = new Property(10, String.class, "IsShowHome", false, "IS_SHOW_HOME");
        public final static Property IsDel = new Property(11, String.class, "IsDel", false, "IS_DEL");
        public final static Property StID = new Property(12, String.class, "StID", false, "ST_ID");
        public final static Property ShowOrder = new Property(13, String.class, "ShowOrder", false, "SHOW_ORDER");
        public final static Property PicPath = new Property(14, String.class, "PicPath", false, "PIC_PATH");
        public final static Property VideoUrl = new Property(15, String.class, "VideoUrl", false, "VIDEO_URL");
        public final static Property LiveRandomNum = new Property(16, String.class, "LiveRandomNum", false, "LIVE_RANDOM_NUM");
        public final static Property LiveNativeRandomNum = new Property(17, String.class, "LiveNativeRandomNum", false, "LIVE_NATIVE_RANDOM_NUM");
        public final static Property Status = new Property(18, String.class, "Status", false, "STATUS");
        public final static Property ReMark = new Property(19, String.class, "ReMark", false, "RE_MARK");
        public final static Property LiveRTMPUrl = new Property(20, String.class, "LiveRTMPUrl", false, "LIVE_RTMPURL");
        public final static Property CommentsNum = new Property(21, String.class, "CommentsNum", false, "COMMENTS_NUM");
        public final static Property LiveType = new Property(22, String.class, "LiveType", false, "LIVE_TYPE");
        public final static Property Week = new Property(23, String.class, "Week", false, "WEEK");
        public final static Property LiveBeginDate = new Property(24, String.class, "LiveBeginDate", false, "LIVE_BEGIN_DATE");
        public final static Property LiveBeginLogo = new Property(25, String.class, "LiveBeginLogo", false, "LIVE_BEGIN_LOGO");
        public final static Property LiveNewChID = new Property(26, String.class, "LiveNewChID", false, "LIVE_NEW_CH_ID");
        public final static Property LiveEventID = new Property(27, String.class, "LiveEventID", false, "LIVE_EVENT_ID");
        public final static Property LiveEndDate = new Property(28, String.class, "LiveEndDate", false, "LIVE_END_DATE");
        public final static Property IsLive = new Property(29, String.class, "IsLive", false, "IS_LIVE");
        public final static Property ReadNo = new Property(30, String.class, "ReadNo", false, "READ_NO");
        public final static Property LiveLink = new Property(31, String.class, "LiveLink", false, "LIVE_LINK");
        public final static Property LiveBeginStatus = new Property(32, String.class, "LiveBeginStatus", false, "LIVE_BEGIN_STATUS");
        public final static Property LiveChannleLogo = new Property(33, String.class, "LiveChannleLogo", false, "LIVE_CHANNLE_LOGO");
        public final static Property LiveBeginMedia = new Property(34, String.class, "LiveBeginMedia", false, "LIVE_BEGIN_MEDIA");
        public final static Property LiveBeginType = new Property(35, String.class, "LiveBeginType", false, "LIVE_BEGIN_TYPE");
        public final static Property BannerImgUrl = new Property(36, String.class, "BannerImgUrl", false, "BANNER_IMG_URL");
        public final static Property LiveIntroduce = new Property(37, String.class, "LiveIntroduce", false, "LIVE_INTRODUCE");
        public final static Property LiveVideoLogo = new Property(38, String.class, "LiveVideoLogo", false, "LIVE_VIDEO_LOGO");
        public final static Property LiveVideoLogoPosition = new Property(39, String.class, "LiveVideoLogoPosition", false, "LIVE_VIDEO_LOGO_POSITION");
        public final static Property LiveEndType = new Property(40, String.class, "LiveEndType", false, "LIVE_END_TYPE");
        public final static Property LiveEndMedia = new Property(41, String.class, "LiveEndMedia", false, "LIVE_END_MEDIA");
        public final static Property LiveEndLogo = new Property(42, String.class, "LiveEndLogo", false, "LIVE_END_LOGO");
        public final static Property IsIntroduce = new Property(43, String.class, "IsIntroduce", false, "IS_INTRODUCE");
        public final static Property IsTopAD = new Property(44, String.class, "IsTopAD", false, "IS_TOP_AD");
        public final static Property GetGoodPoint = new Property(45, String.class, "GetGoodPoint", false, "GET_GOOD_POINT");
        public final static Property Iswebview = new Property(46, String.class, "Iswebview", false, "ISWEBVIEW");
        public final static Property Webview = new Property(47, String.class, "Webview", false, "WEBVIEW");
        public final static Property GoodUsers = new Property(48, String.class, "GoodUsers", false, "GOOD_USERS");
        public final static Property IsZaned = new Property(49, String.class, "isZaned", false, "IS_ZANED");
        public final static Property UserUrl = new Property(50, String.class, "UserUrl", false, "USER_URL");
        public final static Property Authenticationflag = new Property(51, String.class, "authenticationflag", false, "AUTHENTICATIONFLAG");
        public final static Property TabList = new Property(52, String.class, "TabList", false, "TAB_LIST");
    }

    private final LiveChildTabConConverter TabListConverter = new LiveChildTabConConverter();

    public LiveChannelModelDao(DaoConfig config) {
        super(config);
    }
    
    public LiveChannelModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LIVE_CHANNEL_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"ID\" TEXT," + // 1: ID
                "\"CREATE_TIME\" TEXT," + // 2: CreateTime
                "\"LIVE_CHANNEL_NAME\" TEXT," + // 3: LiveChannelName
                "\"LIVE_IMAGE_URL\" TEXT," + // 4: LiveImageUrl
                "\"LIVE_PROGRAM_NAME\" TEXT," + // 5: LiveProgramName
                "\"LIVE_PROGRAM_DATE\" TEXT," + // 6: LiveProgramDate
                "\"PARENT_ID\" TEXT," + // 7: ParentID
                "\"IS_HIDE\" TEXT," + // 8: IsHide
                "\"IS_AD\" TEXT," + // 9: IsAD
                "\"IS_SHOW_HOME\" TEXT," + // 10: IsShowHome
                "\"IS_DEL\" TEXT," + // 11: IsDel
                "\"ST_ID\" TEXT," + // 12: StID
                "\"SHOW_ORDER\" TEXT," + // 13: ShowOrder
                "\"PIC_PATH\" TEXT," + // 14: PicPath
                "\"VIDEO_URL\" TEXT," + // 15: VideoUrl
                "\"LIVE_RANDOM_NUM\" TEXT," + // 16: LiveRandomNum
                "\"LIVE_NATIVE_RANDOM_NUM\" TEXT," + // 17: LiveNativeRandomNum
                "\"STATUS\" TEXT," + // 18: Status
                "\"RE_MARK\" TEXT," + // 19: ReMark
                "\"LIVE_RTMPURL\" TEXT," + // 20: LiveRTMPUrl
                "\"COMMENTS_NUM\" TEXT," + // 21: CommentsNum
                "\"LIVE_TYPE\" TEXT," + // 22: LiveType
                "\"WEEK\" TEXT," + // 23: Week
                "\"LIVE_BEGIN_DATE\" TEXT," + // 24: LiveBeginDate
                "\"LIVE_BEGIN_LOGO\" TEXT," + // 25: LiveBeginLogo
                "\"LIVE_NEW_CH_ID\" TEXT," + // 26: LiveNewChID
                "\"LIVE_EVENT_ID\" TEXT," + // 27: LiveEventID
                "\"LIVE_END_DATE\" TEXT," + // 28: LiveEndDate
                "\"IS_LIVE\" TEXT," + // 29: IsLive
                "\"READ_NO\" TEXT," + // 30: ReadNo
                "\"LIVE_LINK\" TEXT," + // 31: LiveLink
                "\"LIVE_BEGIN_STATUS\" TEXT," + // 32: LiveBeginStatus
                "\"LIVE_CHANNLE_LOGO\" TEXT," + // 33: LiveChannleLogo
                "\"LIVE_BEGIN_MEDIA\" TEXT," + // 34: LiveBeginMedia
                "\"LIVE_BEGIN_TYPE\" TEXT," + // 35: LiveBeginType
                "\"BANNER_IMG_URL\" TEXT," + // 36: BannerImgUrl
                "\"LIVE_INTRODUCE\" TEXT," + // 37: LiveIntroduce
                "\"LIVE_VIDEO_LOGO\" TEXT," + // 38: LiveVideoLogo
                "\"LIVE_VIDEO_LOGO_POSITION\" TEXT," + // 39: LiveVideoLogoPosition
                "\"LIVE_END_TYPE\" TEXT," + // 40: LiveEndType
                "\"LIVE_END_MEDIA\" TEXT," + // 41: LiveEndMedia
                "\"LIVE_END_LOGO\" TEXT," + // 42: LiveEndLogo
                "\"IS_INTRODUCE\" TEXT," + // 43: IsIntroduce
                "\"IS_TOP_AD\" TEXT," + // 44: IsTopAD
                "\"GET_GOOD_POINT\" TEXT," + // 45: GetGoodPoint
                "\"ISWEBVIEW\" TEXT," + // 46: Iswebview
                "\"WEBVIEW\" TEXT," + // 47: Webview
                "\"GOOD_USERS\" TEXT," + // 48: GoodUsers
                "\"IS_ZANED\" TEXT," + // 49: isZaned
                "\"USER_URL\" TEXT," + // 50: UserUrl
                "\"AUTHENTICATIONFLAG\" TEXT," + // 51: authenticationflag
                "\"TAB_LIST\" TEXT);"); // 52: TabList
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LIVE_CHANNEL_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LiveChannelModel entity) {
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
 
        String LiveChannelName = entity.getLiveChannelName();
        if (LiveChannelName != null) {
            stmt.bindString(4, LiveChannelName);
        }
 
        String LiveImageUrl = entity.getLiveImageUrl();
        if (LiveImageUrl != null) {
            stmt.bindString(5, LiveImageUrl);
        }
 
        String LiveProgramName = entity.getLiveProgramName();
        if (LiveProgramName != null) {
            stmt.bindString(6, LiveProgramName);
        }
 
        String LiveProgramDate = entity.getLiveProgramDate();
        if (LiveProgramDate != null) {
            stmt.bindString(7, LiveProgramDate);
        }
 
        String ParentID = entity.getParentID();
        if (ParentID != null) {
            stmt.bindString(8, ParentID);
        }
 
        String IsHide = entity.getIsHide();
        if (IsHide != null) {
            stmt.bindString(9, IsHide);
        }
 
        String IsAD = entity.getIsAD();
        if (IsAD != null) {
            stmt.bindString(10, IsAD);
        }
 
        String IsShowHome = entity.getIsShowHome();
        if (IsShowHome != null) {
            stmt.bindString(11, IsShowHome);
        }
 
        String IsDel = entity.getIsDel();
        if (IsDel != null) {
            stmt.bindString(12, IsDel);
        }
 
        String StID = entity.getStID();
        if (StID != null) {
            stmt.bindString(13, StID);
        }
 
        String ShowOrder = entity.getShowOrder();
        if (ShowOrder != null) {
            stmt.bindString(14, ShowOrder);
        }
 
        String PicPath = entity.getPicPath();
        if (PicPath != null) {
            stmt.bindString(15, PicPath);
        }
 
        String VideoUrl = entity.getVideoUrl();
        if (VideoUrl != null) {
            stmt.bindString(16, VideoUrl);
        }
 
        String LiveRandomNum = entity.getLiveRandomNum();
        if (LiveRandomNum != null) {
            stmt.bindString(17, LiveRandomNum);
        }
 
        String LiveNativeRandomNum = entity.getLiveNativeRandomNum();
        if (LiveNativeRandomNum != null) {
            stmt.bindString(18, LiveNativeRandomNum);
        }
 
        String Status = entity.getStatus();
        if (Status != null) {
            stmt.bindString(19, Status);
        }
 
        String ReMark = entity.getReMark();
        if (ReMark != null) {
            stmt.bindString(20, ReMark);
        }
 
        String LiveRTMPUrl = entity.getLiveRTMPUrl();
        if (LiveRTMPUrl != null) {
            stmt.bindString(21, LiveRTMPUrl);
        }
 
        String CommentsNum = entity.getCommentsNum();
        if (CommentsNum != null) {
            stmt.bindString(22, CommentsNum);
        }
 
        String LiveType = entity.getLiveType();
        if (LiveType != null) {
            stmt.bindString(23, LiveType);
        }
 
        String Week = entity.getWeek();
        if (Week != null) {
            stmt.bindString(24, Week);
        }
 
        String LiveBeginDate = entity.getLiveBeginDate();
        if (LiveBeginDate != null) {
            stmt.bindString(25, LiveBeginDate);
        }
 
        String LiveBeginLogo = entity.getLiveBeginLogo();
        if (LiveBeginLogo != null) {
            stmt.bindString(26, LiveBeginLogo);
        }
 
        String LiveNewChID = entity.getLiveNewChID();
        if (LiveNewChID != null) {
            stmt.bindString(27, LiveNewChID);
        }
 
        String LiveEventID = entity.getLiveEventID();
        if (LiveEventID != null) {
            stmt.bindString(28, LiveEventID);
        }
 
        String LiveEndDate = entity.getLiveEndDate();
        if (LiveEndDate != null) {
            stmt.bindString(29, LiveEndDate);
        }
 
        String IsLive = entity.getIsLive();
        if (IsLive != null) {
            stmt.bindString(30, IsLive);
        }
 
        String ReadNo = entity.getReadNo();
        if (ReadNo != null) {
            stmt.bindString(31, ReadNo);
        }
 
        String LiveLink = entity.getLiveLink();
        if (LiveLink != null) {
            stmt.bindString(32, LiveLink);
        }
 
        String LiveBeginStatus = entity.getLiveBeginStatus();
        if (LiveBeginStatus != null) {
            stmt.bindString(33, LiveBeginStatus);
        }
 
        String LiveChannleLogo = entity.getLiveChannleLogo();
        if (LiveChannleLogo != null) {
            stmt.bindString(34, LiveChannleLogo);
        }
 
        String LiveBeginMedia = entity.getLiveBeginMedia();
        if (LiveBeginMedia != null) {
            stmt.bindString(35, LiveBeginMedia);
        }
 
        String LiveBeginType = entity.getLiveBeginType();
        if (LiveBeginType != null) {
            stmt.bindString(36, LiveBeginType);
        }
 
        String BannerImgUrl = entity.getBannerImgUrl();
        if (BannerImgUrl != null) {
            stmt.bindString(37, BannerImgUrl);
        }
 
        String LiveIntroduce = entity.getLiveIntroduce();
        if (LiveIntroduce != null) {
            stmt.bindString(38, LiveIntroduce);
        }
 
        String LiveVideoLogo = entity.getLiveVideoLogo();
        if (LiveVideoLogo != null) {
            stmt.bindString(39, LiveVideoLogo);
        }
 
        String LiveVideoLogoPosition = entity.getLiveVideoLogoPosition();
        if (LiveVideoLogoPosition != null) {
            stmt.bindString(40, LiveVideoLogoPosition);
        }
 
        String LiveEndType = entity.getLiveEndType();
        if (LiveEndType != null) {
            stmt.bindString(41, LiveEndType);
        }
 
        String LiveEndMedia = entity.getLiveEndMedia();
        if (LiveEndMedia != null) {
            stmt.bindString(42, LiveEndMedia);
        }
 
        String LiveEndLogo = entity.getLiveEndLogo();
        if (LiveEndLogo != null) {
            stmt.bindString(43, LiveEndLogo);
        }
 
        String IsIntroduce = entity.getIsIntroduce();
        if (IsIntroduce != null) {
            stmt.bindString(44, IsIntroduce);
        }
 
        String IsTopAD = entity.getIsTopAD();
        if (IsTopAD != null) {
            stmt.bindString(45, IsTopAD);
        }
 
        String GetGoodPoint = entity.getGetGoodPoint();
        if (GetGoodPoint != null) {
            stmt.bindString(46, GetGoodPoint);
        }
 
        String Iswebview = entity.getIswebview();
        if (Iswebview != null) {
            stmt.bindString(47, Iswebview);
        }
 
        String Webview = entity.getWebview();
        if (Webview != null) {
            stmt.bindString(48, Webview);
        }
 
        String GoodUsers = entity.getGoodUsers();
        if (GoodUsers != null) {
            stmt.bindString(49, GoodUsers);
        }
 
        String isZaned = entity.getIsZaned();
        if (isZaned != null) {
            stmt.bindString(50, isZaned);
        }
 
        String UserUrl = entity.getUserUrl();
        if (UserUrl != null) {
            stmt.bindString(51, UserUrl);
        }
 
        String authenticationflag = entity.getAuthenticationflag();
        if (authenticationflag != null) {
            stmt.bindString(52, authenticationflag);
        }
 
        List TabList = entity.getTabList();
        if (TabList != null) {
            stmt.bindString(53, TabListConverter.convertToDatabaseValue(TabList));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LiveChannelModel entity) {
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
 
        String LiveChannelName = entity.getLiveChannelName();
        if (LiveChannelName != null) {
            stmt.bindString(4, LiveChannelName);
        }
 
        String LiveImageUrl = entity.getLiveImageUrl();
        if (LiveImageUrl != null) {
            stmt.bindString(5, LiveImageUrl);
        }
 
        String LiveProgramName = entity.getLiveProgramName();
        if (LiveProgramName != null) {
            stmt.bindString(6, LiveProgramName);
        }
 
        String LiveProgramDate = entity.getLiveProgramDate();
        if (LiveProgramDate != null) {
            stmt.bindString(7, LiveProgramDate);
        }
 
        String ParentID = entity.getParentID();
        if (ParentID != null) {
            stmt.bindString(8, ParentID);
        }
 
        String IsHide = entity.getIsHide();
        if (IsHide != null) {
            stmt.bindString(9, IsHide);
        }
 
        String IsAD = entity.getIsAD();
        if (IsAD != null) {
            stmt.bindString(10, IsAD);
        }
 
        String IsShowHome = entity.getIsShowHome();
        if (IsShowHome != null) {
            stmt.bindString(11, IsShowHome);
        }
 
        String IsDel = entity.getIsDel();
        if (IsDel != null) {
            stmt.bindString(12, IsDel);
        }
 
        String StID = entity.getStID();
        if (StID != null) {
            stmt.bindString(13, StID);
        }
 
        String ShowOrder = entity.getShowOrder();
        if (ShowOrder != null) {
            stmt.bindString(14, ShowOrder);
        }
 
        String PicPath = entity.getPicPath();
        if (PicPath != null) {
            stmt.bindString(15, PicPath);
        }
 
        String VideoUrl = entity.getVideoUrl();
        if (VideoUrl != null) {
            stmt.bindString(16, VideoUrl);
        }
 
        String LiveRandomNum = entity.getLiveRandomNum();
        if (LiveRandomNum != null) {
            stmt.bindString(17, LiveRandomNum);
        }
 
        String LiveNativeRandomNum = entity.getLiveNativeRandomNum();
        if (LiveNativeRandomNum != null) {
            stmt.bindString(18, LiveNativeRandomNum);
        }
 
        String Status = entity.getStatus();
        if (Status != null) {
            stmt.bindString(19, Status);
        }
 
        String ReMark = entity.getReMark();
        if (ReMark != null) {
            stmt.bindString(20, ReMark);
        }
 
        String LiveRTMPUrl = entity.getLiveRTMPUrl();
        if (LiveRTMPUrl != null) {
            stmt.bindString(21, LiveRTMPUrl);
        }
 
        String CommentsNum = entity.getCommentsNum();
        if (CommentsNum != null) {
            stmt.bindString(22, CommentsNum);
        }
 
        String LiveType = entity.getLiveType();
        if (LiveType != null) {
            stmt.bindString(23, LiveType);
        }
 
        String Week = entity.getWeek();
        if (Week != null) {
            stmt.bindString(24, Week);
        }
 
        String LiveBeginDate = entity.getLiveBeginDate();
        if (LiveBeginDate != null) {
            stmt.bindString(25, LiveBeginDate);
        }
 
        String LiveBeginLogo = entity.getLiveBeginLogo();
        if (LiveBeginLogo != null) {
            stmt.bindString(26, LiveBeginLogo);
        }
 
        String LiveNewChID = entity.getLiveNewChID();
        if (LiveNewChID != null) {
            stmt.bindString(27, LiveNewChID);
        }
 
        String LiveEventID = entity.getLiveEventID();
        if (LiveEventID != null) {
            stmt.bindString(28, LiveEventID);
        }
 
        String LiveEndDate = entity.getLiveEndDate();
        if (LiveEndDate != null) {
            stmt.bindString(29, LiveEndDate);
        }
 
        String IsLive = entity.getIsLive();
        if (IsLive != null) {
            stmt.bindString(30, IsLive);
        }
 
        String ReadNo = entity.getReadNo();
        if (ReadNo != null) {
            stmt.bindString(31, ReadNo);
        }
 
        String LiveLink = entity.getLiveLink();
        if (LiveLink != null) {
            stmt.bindString(32, LiveLink);
        }
 
        String LiveBeginStatus = entity.getLiveBeginStatus();
        if (LiveBeginStatus != null) {
            stmt.bindString(33, LiveBeginStatus);
        }
 
        String LiveChannleLogo = entity.getLiveChannleLogo();
        if (LiveChannleLogo != null) {
            stmt.bindString(34, LiveChannleLogo);
        }
 
        String LiveBeginMedia = entity.getLiveBeginMedia();
        if (LiveBeginMedia != null) {
            stmt.bindString(35, LiveBeginMedia);
        }
 
        String LiveBeginType = entity.getLiveBeginType();
        if (LiveBeginType != null) {
            stmt.bindString(36, LiveBeginType);
        }
 
        String BannerImgUrl = entity.getBannerImgUrl();
        if (BannerImgUrl != null) {
            stmt.bindString(37, BannerImgUrl);
        }
 
        String LiveIntroduce = entity.getLiveIntroduce();
        if (LiveIntroduce != null) {
            stmt.bindString(38, LiveIntroduce);
        }
 
        String LiveVideoLogo = entity.getLiveVideoLogo();
        if (LiveVideoLogo != null) {
            stmt.bindString(39, LiveVideoLogo);
        }
 
        String LiveVideoLogoPosition = entity.getLiveVideoLogoPosition();
        if (LiveVideoLogoPosition != null) {
            stmt.bindString(40, LiveVideoLogoPosition);
        }
 
        String LiveEndType = entity.getLiveEndType();
        if (LiveEndType != null) {
            stmt.bindString(41, LiveEndType);
        }
 
        String LiveEndMedia = entity.getLiveEndMedia();
        if (LiveEndMedia != null) {
            stmt.bindString(42, LiveEndMedia);
        }
 
        String LiveEndLogo = entity.getLiveEndLogo();
        if (LiveEndLogo != null) {
            stmt.bindString(43, LiveEndLogo);
        }
 
        String IsIntroduce = entity.getIsIntroduce();
        if (IsIntroduce != null) {
            stmt.bindString(44, IsIntroduce);
        }
 
        String IsTopAD = entity.getIsTopAD();
        if (IsTopAD != null) {
            stmt.bindString(45, IsTopAD);
        }
 
        String GetGoodPoint = entity.getGetGoodPoint();
        if (GetGoodPoint != null) {
            stmt.bindString(46, GetGoodPoint);
        }
 
        String Iswebview = entity.getIswebview();
        if (Iswebview != null) {
            stmt.bindString(47, Iswebview);
        }
 
        String Webview = entity.getWebview();
        if (Webview != null) {
            stmt.bindString(48, Webview);
        }
 
        String GoodUsers = entity.getGoodUsers();
        if (GoodUsers != null) {
            stmt.bindString(49, GoodUsers);
        }
 
        String isZaned = entity.getIsZaned();
        if (isZaned != null) {
            stmt.bindString(50, isZaned);
        }
 
        String UserUrl = entity.getUserUrl();
        if (UserUrl != null) {
            stmt.bindString(51, UserUrl);
        }
 
        String authenticationflag = entity.getAuthenticationflag();
        if (authenticationflag != null) {
            stmt.bindString(52, authenticationflag);
        }
 
        List TabList = entity.getTabList();
        if (TabList != null) {
            stmt.bindString(53, TabListConverter.convertToDatabaseValue(TabList));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LiveChannelModel readEntity(Cursor cursor, int offset) {
        LiveChannelModel entity = new LiveChannelModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // CreateTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // LiveChannelName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // LiveImageUrl
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // LiveProgramName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // LiveProgramDate
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // ParentID
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // IsHide
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // IsAD
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // IsShowHome
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // IsDel
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // StID
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // ShowOrder
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // PicPath
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // VideoUrl
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // LiveRandomNum
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // LiveNativeRandomNum
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // Status
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // ReMark
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // LiveRTMPUrl
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // CommentsNum
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // LiveType
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // Week
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // LiveBeginDate
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // LiveBeginLogo
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // LiveNewChID
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // LiveEventID
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // LiveEndDate
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // IsLive
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // ReadNo
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // LiveLink
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // LiveBeginStatus
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // LiveChannleLogo
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // LiveBeginMedia
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // LiveBeginType
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // BannerImgUrl
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // LiveIntroduce
            cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // LiveVideoLogo
            cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39), // LiveVideoLogoPosition
            cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40), // LiveEndType
            cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41), // LiveEndMedia
            cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42), // LiveEndLogo
            cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43), // IsIntroduce
            cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44), // IsTopAD
            cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45), // GetGoodPoint
            cursor.isNull(offset + 46) ? null : cursor.getString(offset + 46), // Iswebview
            cursor.isNull(offset + 47) ? null : cursor.getString(offset + 47), // Webview
            cursor.isNull(offset + 48) ? null : cursor.getString(offset + 48), // GoodUsers
            cursor.isNull(offset + 49) ? null : cursor.getString(offset + 49), // isZaned
            cursor.isNull(offset + 50) ? null : cursor.getString(offset + 50), // UserUrl
            cursor.isNull(offset + 51) ? null : cursor.getString(offset + 51), // authenticationflag
            cursor.isNull(offset + 52) ? null : TabListConverter.convertToEntityProperty(cursor.getString(offset + 52)) // TabList
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LiveChannelModel entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreateTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLiveChannelName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLiveImageUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLiveProgramName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLiveProgramDate(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setParentID(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setIsHide(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setIsAD(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setIsShowHome(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setIsDel(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setStID(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setShowOrder(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setPicPath(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setVideoUrl(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setLiveRandomNum(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setLiveNativeRandomNum(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setStatus(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setReMark(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setLiveRTMPUrl(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setCommentsNum(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setLiveType(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setWeek(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setLiveBeginDate(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setLiveBeginLogo(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setLiveNewChID(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setLiveEventID(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setLiveEndDate(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setIsLive(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setReadNo(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setLiveLink(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setLiveBeginStatus(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setLiveChannleLogo(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setLiveBeginMedia(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setLiveBeginType(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setBannerImgUrl(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setLiveIntroduce(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setLiveVideoLogo(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setLiveVideoLogoPosition(cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39));
        entity.setLiveEndType(cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40));
        entity.setLiveEndMedia(cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41));
        entity.setLiveEndLogo(cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42));
        entity.setIsIntroduce(cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43));
        entity.setIsTopAD(cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44));
        entity.setGetGoodPoint(cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45));
        entity.setIswebview(cursor.isNull(offset + 46) ? null : cursor.getString(offset + 46));
        entity.setWebview(cursor.isNull(offset + 47) ? null : cursor.getString(offset + 47));
        entity.setGoodUsers(cursor.isNull(offset + 48) ? null : cursor.getString(offset + 48));
        entity.setIsZaned(cursor.isNull(offset + 49) ? null : cursor.getString(offset + 49));
        entity.setUserUrl(cursor.isNull(offset + 50) ? null : cursor.getString(offset + 50));
        entity.setAuthenticationflag(cursor.isNull(offset + 51) ? null : cursor.getString(offset + 51));
        entity.setTabList(cursor.isNull(offset + 52) ? null : TabListConverter.convertToEntityProperty(cursor.getString(offset + 52)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LiveChannelModel entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LiveChannelModel entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LiveChannelModel entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

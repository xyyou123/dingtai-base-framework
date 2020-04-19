package com.dingtai.android.library.news.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.dingtai.android.library.news.model.NewsListModel;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "NEWS_LIST_MODEL".
*/
public class NewsListModelDao extends AbstractDao<NewsListModel, Long> {

    public static final String TABLENAME = "NEWS_LIST_MODEL";

    /**
     * Properties of entity NewsListModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property ChannelLogo = new Property(1, String.class, "ChannelLogo", false, "CHANNEL_LOGO");
        public final static Property ResourceGUID = new Property(2, String.class, "ResourceGUID", false, "RESOURCE_GUID");
        public final static Property ChannelName = new Property(3, String.class, "ChannelName", false, "CHANNEL_NAME");
        public final static Property Title = new Property(4, String.class, "Title", false, "TITLE");
        public final static Property Summary = new Property(5, String.class, "Summary", false, "SUMMARY");
        public final static Property SourceForm = new Property(6, String.class, "SourceForm", false, "SOURCE_FORM");
        public final static Property CreateTime = new Property(7, String.class, "CreateTime", false, "CREATE_TIME");
        public final static Property UpdateTime = new Property(8, String.class, "UpdateTime", false, "UPDATE_TIME");
        public final static Property AuditTime = new Property(9, String.class, "AuditTime", false, "AUDIT_TIME");
        public final static Property IsComment = new Property(10, String.class, "IsComment", false, "IS_COMMENT");
        public final static Property IsCommentNoName = new Property(11, String.class, "IsCommentNoName", false, "IS_COMMENT_NO_NAME");
        public final static Property ResourceType = new Property(12, String.class, "ResourceType", false, "RESOURCE_TYPE");
        public final static Property UploadPicNames = new Property(13, String.class, "UploadPicNames", false, "UPLOAD_PIC_NAMES");
        public final static Property SmallPicUrl = new Property(14, String.class, "SmallPicUrl", false, "SMALL_PIC_URL");
        public final static Property ChID = new Property(15, String.class, "ChID", false, "CH_ID");
        public final static Property ParentID = new Property(16, String.class, "parentID", false, "PARENT_ID");
        public final static Property ShowOrder = new Property(17, String.class, "ShowOrder", false, "SHOW_ORDER");
        public final static Property Longitude = new Property(18, String.class, "Longitude", false, "LONGITUDE");
        public final static Property Latitude = new Property(19, String.class, "Latitude", false, "LATITUDE");
        public final static Property ReadNo = new Property(20, String.class, "ReadNo", false, "READ_NO");
        public final static Property BandChID = new Property(21, String.class, "BandChID", false, "BAND_CH_ID");
        public final static Property CommentNum = new Property(22, String.class, "CommentNum", false, "COMMENT_NUM");
        public final static Property RPID = new Property(23, String.class, "RPID", false, "RPID");
        public final static Property RPNum = new Property(24, String.class, "RPNum", false, "RPNUM");
        public final static Property ResourceFlag = new Property(25, String.class, "ResourceFlag", false, "RESOURCE_FLAG");
        public final static Property ResourceUrl = new Property(26, String.class, "ResourceUrl", false, "RESOURCE_URL");
        public final static Property FakeReadNo = new Property(27, String.class, "FakeReadNo", false, "FAKE_READ_NO");
        public final static Property ThemeID = new Property(28, String.class, "ThemeID", false, "THEME_ID");
        public final static Property ResourceCSS = new Property(29, String.class, "ResourceCSS", false, "RESOURCE_CSS");
        public final static Property GetGoodPoint = new Property(30, String.class, "GetGoodPoint", false, "GET_GOOD_POINT");
        public final static Property PicPath = new Property(31, String.class, "PicPath", false, "PIC_PATH");
        public final static Property CommunityName = new Property(32, String.class, "CommunityName", false, "COMMUNITY_NAME");
        public final static Property IsNewTopice = new Property(33, String.class, "IsNewTopice", false, "IS_NEW_TOPICE");
        public final static Property IsRead = new Property(34, boolean.class, "isRead", false, "IS_READ");
        public final static Property VoteType = new Property(35, String.class, "VoteType", false, "VOTE_TYPE");
        public final static Property VoteRemark = new Property(36, String.class, "VoteRemark", false, "VOTE_REMARK");
        public final static Property VoteSubject1Name = new Property(37, String.class, "VoteSubject1Name", false, "VOTE_SUBJECT1_NAME");
        public final static Property VoteSubject2Name = new Property(38, String.class, "VoteSubject2Name", false, "VOTE_SUBJECT2_NAME");
        public final static Property VoteNum = new Property(39, String.class, "VoteNum", false, "VOTE_NUM");
        public final static Property VoteSubject1 = new Property(40, String.class, "VoteSubject1", false, "VOTE_SUBJECT1");
        public final static Property VoteSubject2 = new Property(41, String.class, "VoteSubject2", false, "VOTE_SUBJECT2");
        public final static Property VoteSubject1Percent = new Property(42, String.class, "VoteSubject1Percent", false, "VOTE_SUBJECT1_PERCENT");
        public final static Property VoteSubject2Percent = new Property(43, String.class, "VoteSubject2Percent", false, "VOTE_SUBJECT2_PERCENT");
        public final static Property HornName = new Property(44, String.class, "HornName", false, "HORN_NAME");
        public final static Property HornColor = new Property(45, String.class, "HornColor", false, "HORN_COLOR");
        public final static Property ResourcePdForm = new Property(46, String.class, "ResourcePdForm", false, "RESOURCE_PD_FORM");
        public final static Property Topice = new Property(47, String.class, "Topice", false, "TOPICE");
        public final static Property ChannelName1 = new Property(48, String.class, "channelName1", false, "CHANNEL_NAME1");
        public final static Property ChannelID = new Property(49, String.class, "channelID", false, "CHANNEL_ID");
        public final static Property MediaLogo = new Property(50, String.class, "MediaLogo", false, "MEDIA_LOGO");
        public final static Property MediaUrl = new Property(51, String.class, "MediaUrl", false, "MEDIA_URL");
        public final static Property MediaName = new Property(52, String.class, "MediaName", false, "MEDIA_NAME");
        public final static Property RedirectType = new Property(53, String.class, "RedirectType", false, "REDIRECT_TYPE");
        public final static Property JsonKey = new Property(54, String.class, "JsonKey", false, "JSON_KEY");
        public final static Property ResUnitName = new Property(55, String.class, "ResUnitName", false, "RES_UNIT_NAME");
        public final static Property Address = new Property(56, String.class, "Address", false, "ADDRESS");
    }


    public NewsListModelDao(DaoConfig config) {
        super(config);
    }
    
    public NewsListModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_LIST_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"CHANNEL_LOGO\" TEXT," + // 1: ChannelLogo
                "\"RESOURCE_GUID\" TEXT," + // 2: ResourceGUID
                "\"CHANNEL_NAME\" TEXT," + // 3: ChannelName
                "\"TITLE\" TEXT," + // 4: Title
                "\"SUMMARY\" TEXT," + // 5: Summary
                "\"SOURCE_FORM\" TEXT," + // 6: SourceForm
                "\"CREATE_TIME\" TEXT," + // 7: CreateTime
                "\"UPDATE_TIME\" TEXT," + // 8: UpdateTime
                "\"AUDIT_TIME\" TEXT," + // 9: AuditTime
                "\"IS_COMMENT\" TEXT," + // 10: IsComment
                "\"IS_COMMENT_NO_NAME\" TEXT," + // 11: IsCommentNoName
                "\"RESOURCE_TYPE\" TEXT," + // 12: ResourceType
                "\"UPLOAD_PIC_NAMES\" TEXT," + // 13: UploadPicNames
                "\"SMALL_PIC_URL\" TEXT," + // 14: SmallPicUrl
                "\"CH_ID\" TEXT," + // 15: ChID
                "\"PARENT_ID\" TEXT," + // 16: parentID
                "\"SHOW_ORDER\" TEXT," + // 17: ShowOrder
                "\"LONGITUDE\" TEXT," + // 18: Longitude
                "\"LATITUDE\" TEXT," + // 19: Latitude
                "\"READ_NO\" TEXT," + // 20: ReadNo
                "\"BAND_CH_ID\" TEXT," + // 21: BandChID
                "\"COMMENT_NUM\" TEXT," + // 22: CommentNum
                "\"RPID\" TEXT," + // 23: RPID
                "\"RPNUM\" TEXT," + // 24: RPNum
                "\"RESOURCE_FLAG\" TEXT," + // 25: ResourceFlag
                "\"RESOURCE_URL\" TEXT," + // 26: ResourceUrl
                "\"FAKE_READ_NO\" TEXT," + // 27: FakeReadNo
                "\"THEME_ID\" TEXT," + // 28: ThemeID
                "\"RESOURCE_CSS\" TEXT," + // 29: ResourceCSS
                "\"GET_GOOD_POINT\" TEXT," + // 30: GetGoodPoint
                "\"PIC_PATH\" TEXT," + // 31: PicPath
                "\"COMMUNITY_NAME\" TEXT," + // 32: CommunityName
                "\"IS_NEW_TOPICE\" TEXT," + // 33: IsNewTopice
                "\"IS_READ\" INTEGER NOT NULL ," + // 34: isRead
                "\"VOTE_TYPE\" TEXT," + // 35: VoteType
                "\"VOTE_REMARK\" TEXT," + // 36: VoteRemark
                "\"VOTE_SUBJECT1_NAME\" TEXT," + // 37: VoteSubject1Name
                "\"VOTE_SUBJECT2_NAME\" TEXT," + // 38: VoteSubject2Name
                "\"VOTE_NUM\" TEXT," + // 39: VoteNum
                "\"VOTE_SUBJECT1\" TEXT," + // 40: VoteSubject1
                "\"VOTE_SUBJECT2\" TEXT," + // 41: VoteSubject2
                "\"VOTE_SUBJECT1_PERCENT\" TEXT," + // 42: VoteSubject1Percent
                "\"VOTE_SUBJECT2_PERCENT\" TEXT," + // 43: VoteSubject2Percent
                "\"HORN_NAME\" TEXT," + // 44: HornName
                "\"HORN_COLOR\" TEXT," + // 45: HornColor
                "\"RESOURCE_PD_FORM\" TEXT," + // 46: ResourcePdForm
                "\"TOPICE\" TEXT," + // 47: Topice
                "\"CHANNEL_NAME1\" TEXT," + // 48: channelName1
                "\"CHANNEL_ID\" TEXT," + // 49: channelID
                "\"MEDIA_LOGO\" TEXT," + // 50: MediaLogo
                "\"MEDIA_URL\" TEXT," + // 51: MediaUrl
                "\"MEDIA_NAME\" TEXT," + // 52: MediaName
                "\"REDIRECT_TYPE\" TEXT," + // 53: RedirectType
                "\"JSON_KEY\" TEXT," + // 54: JsonKey
                "\"RES_UNIT_NAME\" TEXT," + // 55: ResUnitName
                "\"ADDRESS\" TEXT);"); // 56: Address
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_LIST_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NewsListModel entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String ChannelLogo = entity.getChannelLogo();
        if (ChannelLogo != null) {
            stmt.bindString(2, ChannelLogo);
        }
 
        String ResourceGUID = entity.getResourceGUID();
        if (ResourceGUID != null) {
            stmt.bindString(3, ResourceGUID);
        }
 
        String ChannelName = entity.getChannelName();
        if (ChannelName != null) {
            stmt.bindString(4, ChannelName);
        }
 
        String Title = entity.getTitle();
        if (Title != null) {
            stmt.bindString(5, Title);
        }
 
        String Summary = entity.getSummary();
        if (Summary != null) {
            stmt.bindString(6, Summary);
        }
 
        String SourceForm = entity.getSourceForm();
        if (SourceForm != null) {
            stmt.bindString(7, SourceForm);
        }
 
        String CreateTime = entity.getCreateTime();
        if (CreateTime != null) {
            stmt.bindString(8, CreateTime);
        }
 
        String UpdateTime = entity.getUpdateTime();
        if (UpdateTime != null) {
            stmt.bindString(9, UpdateTime);
        }
 
        String AuditTime = entity.getAuditTime();
        if (AuditTime != null) {
            stmt.bindString(10, AuditTime);
        }
 
        String IsComment = entity.getIsComment();
        if (IsComment != null) {
            stmt.bindString(11, IsComment);
        }
 
        String IsCommentNoName = entity.getIsCommentNoName();
        if (IsCommentNoName != null) {
            stmt.bindString(12, IsCommentNoName);
        }
 
        String ResourceType = entity.getResourceType();
        if (ResourceType != null) {
            stmt.bindString(13, ResourceType);
        }
 
        String UploadPicNames = entity.getUploadPicNames();
        if (UploadPicNames != null) {
            stmt.bindString(14, UploadPicNames);
        }
 
        String SmallPicUrl = entity.getSmallPicUrl();
        if (SmallPicUrl != null) {
            stmt.bindString(15, SmallPicUrl);
        }
 
        String ChID = entity.getChID();
        if (ChID != null) {
            stmt.bindString(16, ChID);
        }
 
        String parentID = entity.getParentID();
        if (parentID != null) {
            stmt.bindString(17, parentID);
        }
 
        String ShowOrder = entity.getShowOrder();
        if (ShowOrder != null) {
            stmt.bindString(18, ShowOrder);
        }
 
        String Longitude = entity.getLongitude();
        if (Longitude != null) {
            stmt.bindString(19, Longitude);
        }
 
        String Latitude = entity.getLatitude();
        if (Latitude != null) {
            stmt.bindString(20, Latitude);
        }
 
        String ReadNo = entity.getReadNo();
        if (ReadNo != null) {
            stmt.bindString(21, ReadNo);
        }
 
        String BandChID = entity.getBandChID();
        if (BandChID != null) {
            stmt.bindString(22, BandChID);
        }
 
        String CommentNum = entity.getCommentNum();
        if (CommentNum != null) {
            stmt.bindString(23, CommentNum);
        }
 
        String RPID = entity.getRPID();
        if (RPID != null) {
            stmt.bindString(24, RPID);
        }
 
        String RPNum = entity.getRPNum();
        if (RPNum != null) {
            stmt.bindString(25, RPNum);
        }
 
        String ResourceFlag = entity.getResourceFlag();
        if (ResourceFlag != null) {
            stmt.bindString(26, ResourceFlag);
        }
 
        String ResourceUrl = entity.getResourceUrl();
        if (ResourceUrl != null) {
            stmt.bindString(27, ResourceUrl);
        }
 
        String FakeReadNo = entity.getFakeReadNo();
        if (FakeReadNo != null) {
            stmt.bindString(28, FakeReadNo);
        }
 
        String ThemeID = entity.getThemeID();
        if (ThemeID != null) {
            stmt.bindString(29, ThemeID);
        }
 
        String ResourceCSS = entity.getResourceCSS();
        if (ResourceCSS != null) {
            stmt.bindString(30, ResourceCSS);
        }
 
        String GetGoodPoint = entity.getGetGoodPoint();
        if (GetGoodPoint != null) {
            stmt.bindString(31, GetGoodPoint);
        }
 
        String PicPath = entity.getPicPath();
        if (PicPath != null) {
            stmt.bindString(32, PicPath);
        }
 
        String CommunityName = entity.getCommunityName();
        if (CommunityName != null) {
            stmt.bindString(33, CommunityName);
        }
 
        String IsNewTopice = entity.getIsNewTopice();
        if (IsNewTopice != null) {
            stmt.bindString(34, IsNewTopice);
        }
        stmt.bindLong(35, entity.getIsRead() ? 1L: 0L);
 
        String VoteType = entity.getVoteType();
        if (VoteType != null) {
            stmt.bindString(36, VoteType);
        }
 
        String VoteRemark = entity.getVoteRemark();
        if (VoteRemark != null) {
            stmt.bindString(37, VoteRemark);
        }
 
        String VoteSubject1Name = entity.getVoteSubject1Name();
        if (VoteSubject1Name != null) {
            stmt.bindString(38, VoteSubject1Name);
        }
 
        String VoteSubject2Name = entity.getVoteSubject2Name();
        if (VoteSubject2Name != null) {
            stmt.bindString(39, VoteSubject2Name);
        }
 
        String VoteNum = entity.getVoteNum();
        if (VoteNum != null) {
            stmt.bindString(40, VoteNum);
        }
 
        String VoteSubject1 = entity.getVoteSubject1();
        if (VoteSubject1 != null) {
            stmt.bindString(41, VoteSubject1);
        }
 
        String VoteSubject2 = entity.getVoteSubject2();
        if (VoteSubject2 != null) {
            stmt.bindString(42, VoteSubject2);
        }
 
        String VoteSubject1Percent = entity.getVoteSubject1Percent();
        if (VoteSubject1Percent != null) {
            stmt.bindString(43, VoteSubject1Percent);
        }
 
        String VoteSubject2Percent = entity.getVoteSubject2Percent();
        if (VoteSubject2Percent != null) {
            stmt.bindString(44, VoteSubject2Percent);
        }
 
        String HornName = entity.getHornName();
        if (HornName != null) {
            stmt.bindString(45, HornName);
        }
 
        String HornColor = entity.getHornColor();
        if (HornColor != null) {
            stmt.bindString(46, HornColor);
        }
 
        String ResourcePdForm = entity.getResourcePdForm();
        if (ResourcePdForm != null) {
            stmt.bindString(47, ResourcePdForm);
        }
 
        String Topice = entity.getTopice();
        if (Topice != null) {
            stmt.bindString(48, Topice);
        }
 
        String channelName1 = entity.getChannelName1();
        if (channelName1 != null) {
            stmt.bindString(49, channelName1);
        }
 
        String channelID = entity.getChannelID();
        if (channelID != null) {
            stmt.bindString(50, channelID);
        }
 
        String MediaLogo = entity.getMediaLogo();
        if (MediaLogo != null) {
            stmt.bindString(51, MediaLogo);
        }
 
        String MediaUrl = entity.getMediaUrl();
        if (MediaUrl != null) {
            stmt.bindString(52, MediaUrl);
        }
 
        String MediaName = entity.getMediaName();
        if (MediaName != null) {
            stmt.bindString(53, MediaName);
        }
 
        String RedirectType = entity.getRedirectType();
        if (RedirectType != null) {
            stmt.bindString(54, RedirectType);
        }
 
        String JsonKey = entity.getJsonKey();
        if (JsonKey != null) {
            stmt.bindString(55, JsonKey);
        }
 
        String ResUnitName = entity.getResUnitName();
        if (ResUnitName != null) {
            stmt.bindString(56, ResUnitName);
        }
 
        String Address = entity.getAddress();
        if (Address != null) {
            stmt.bindString(57, Address);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NewsListModel entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String ChannelLogo = entity.getChannelLogo();
        if (ChannelLogo != null) {
            stmt.bindString(2, ChannelLogo);
        }
 
        String ResourceGUID = entity.getResourceGUID();
        if (ResourceGUID != null) {
            stmt.bindString(3, ResourceGUID);
        }
 
        String ChannelName = entity.getChannelName();
        if (ChannelName != null) {
            stmt.bindString(4, ChannelName);
        }
 
        String Title = entity.getTitle();
        if (Title != null) {
            stmt.bindString(5, Title);
        }
 
        String Summary = entity.getSummary();
        if (Summary != null) {
            stmt.bindString(6, Summary);
        }
 
        String SourceForm = entity.getSourceForm();
        if (SourceForm != null) {
            stmt.bindString(7, SourceForm);
        }
 
        String CreateTime = entity.getCreateTime();
        if (CreateTime != null) {
            stmt.bindString(8, CreateTime);
        }
 
        String UpdateTime = entity.getUpdateTime();
        if (UpdateTime != null) {
            stmt.bindString(9, UpdateTime);
        }
 
        String AuditTime = entity.getAuditTime();
        if (AuditTime != null) {
            stmt.bindString(10, AuditTime);
        }
 
        String IsComment = entity.getIsComment();
        if (IsComment != null) {
            stmt.bindString(11, IsComment);
        }
 
        String IsCommentNoName = entity.getIsCommentNoName();
        if (IsCommentNoName != null) {
            stmt.bindString(12, IsCommentNoName);
        }
 
        String ResourceType = entity.getResourceType();
        if (ResourceType != null) {
            stmt.bindString(13, ResourceType);
        }
 
        String UploadPicNames = entity.getUploadPicNames();
        if (UploadPicNames != null) {
            stmt.bindString(14, UploadPicNames);
        }
 
        String SmallPicUrl = entity.getSmallPicUrl();
        if (SmallPicUrl != null) {
            stmt.bindString(15, SmallPicUrl);
        }
 
        String ChID = entity.getChID();
        if (ChID != null) {
            stmt.bindString(16, ChID);
        }
 
        String parentID = entity.getParentID();
        if (parentID != null) {
            stmt.bindString(17, parentID);
        }
 
        String ShowOrder = entity.getShowOrder();
        if (ShowOrder != null) {
            stmt.bindString(18, ShowOrder);
        }
 
        String Longitude = entity.getLongitude();
        if (Longitude != null) {
            stmt.bindString(19, Longitude);
        }
 
        String Latitude = entity.getLatitude();
        if (Latitude != null) {
            stmt.bindString(20, Latitude);
        }
 
        String ReadNo = entity.getReadNo();
        if (ReadNo != null) {
            stmt.bindString(21, ReadNo);
        }
 
        String BandChID = entity.getBandChID();
        if (BandChID != null) {
            stmt.bindString(22, BandChID);
        }
 
        String CommentNum = entity.getCommentNum();
        if (CommentNum != null) {
            stmt.bindString(23, CommentNum);
        }
 
        String RPID = entity.getRPID();
        if (RPID != null) {
            stmt.bindString(24, RPID);
        }
 
        String RPNum = entity.getRPNum();
        if (RPNum != null) {
            stmt.bindString(25, RPNum);
        }
 
        String ResourceFlag = entity.getResourceFlag();
        if (ResourceFlag != null) {
            stmt.bindString(26, ResourceFlag);
        }
 
        String ResourceUrl = entity.getResourceUrl();
        if (ResourceUrl != null) {
            stmt.bindString(27, ResourceUrl);
        }
 
        String FakeReadNo = entity.getFakeReadNo();
        if (FakeReadNo != null) {
            stmt.bindString(28, FakeReadNo);
        }
 
        String ThemeID = entity.getThemeID();
        if (ThemeID != null) {
            stmt.bindString(29, ThemeID);
        }
 
        String ResourceCSS = entity.getResourceCSS();
        if (ResourceCSS != null) {
            stmt.bindString(30, ResourceCSS);
        }
 
        String GetGoodPoint = entity.getGetGoodPoint();
        if (GetGoodPoint != null) {
            stmt.bindString(31, GetGoodPoint);
        }
 
        String PicPath = entity.getPicPath();
        if (PicPath != null) {
            stmt.bindString(32, PicPath);
        }
 
        String CommunityName = entity.getCommunityName();
        if (CommunityName != null) {
            stmt.bindString(33, CommunityName);
        }
 
        String IsNewTopice = entity.getIsNewTopice();
        if (IsNewTopice != null) {
            stmt.bindString(34, IsNewTopice);
        }
        stmt.bindLong(35, entity.getIsRead() ? 1L: 0L);
 
        String VoteType = entity.getVoteType();
        if (VoteType != null) {
            stmt.bindString(36, VoteType);
        }
 
        String VoteRemark = entity.getVoteRemark();
        if (VoteRemark != null) {
            stmt.bindString(37, VoteRemark);
        }
 
        String VoteSubject1Name = entity.getVoteSubject1Name();
        if (VoteSubject1Name != null) {
            stmt.bindString(38, VoteSubject1Name);
        }
 
        String VoteSubject2Name = entity.getVoteSubject2Name();
        if (VoteSubject2Name != null) {
            stmt.bindString(39, VoteSubject2Name);
        }
 
        String VoteNum = entity.getVoteNum();
        if (VoteNum != null) {
            stmt.bindString(40, VoteNum);
        }
 
        String VoteSubject1 = entity.getVoteSubject1();
        if (VoteSubject1 != null) {
            stmt.bindString(41, VoteSubject1);
        }
 
        String VoteSubject2 = entity.getVoteSubject2();
        if (VoteSubject2 != null) {
            stmt.bindString(42, VoteSubject2);
        }
 
        String VoteSubject1Percent = entity.getVoteSubject1Percent();
        if (VoteSubject1Percent != null) {
            stmt.bindString(43, VoteSubject1Percent);
        }
 
        String VoteSubject2Percent = entity.getVoteSubject2Percent();
        if (VoteSubject2Percent != null) {
            stmt.bindString(44, VoteSubject2Percent);
        }
 
        String HornName = entity.getHornName();
        if (HornName != null) {
            stmt.bindString(45, HornName);
        }
 
        String HornColor = entity.getHornColor();
        if (HornColor != null) {
            stmt.bindString(46, HornColor);
        }
 
        String ResourcePdForm = entity.getResourcePdForm();
        if (ResourcePdForm != null) {
            stmt.bindString(47, ResourcePdForm);
        }
 
        String Topice = entity.getTopice();
        if (Topice != null) {
            stmt.bindString(48, Topice);
        }
 
        String channelName1 = entity.getChannelName1();
        if (channelName1 != null) {
            stmt.bindString(49, channelName1);
        }
 
        String channelID = entity.getChannelID();
        if (channelID != null) {
            stmt.bindString(50, channelID);
        }
 
        String MediaLogo = entity.getMediaLogo();
        if (MediaLogo != null) {
            stmt.bindString(51, MediaLogo);
        }
 
        String MediaUrl = entity.getMediaUrl();
        if (MediaUrl != null) {
            stmt.bindString(52, MediaUrl);
        }
 
        String MediaName = entity.getMediaName();
        if (MediaName != null) {
            stmt.bindString(53, MediaName);
        }
 
        String RedirectType = entity.getRedirectType();
        if (RedirectType != null) {
            stmt.bindString(54, RedirectType);
        }
 
        String JsonKey = entity.getJsonKey();
        if (JsonKey != null) {
            stmt.bindString(55, JsonKey);
        }
 
        String ResUnitName = entity.getResUnitName();
        if (ResUnitName != null) {
            stmt.bindString(56, ResUnitName);
        }
 
        String Address = entity.getAddress();
        if (Address != null) {
            stmt.bindString(57, Address);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NewsListModel readEntity(Cursor cursor, int offset) {
        NewsListModel entity = new NewsListModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ChannelLogo
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // ResourceGUID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // ChannelName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Title
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // Summary
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // SourceForm
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // CreateTime
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // UpdateTime
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // AuditTime
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // IsComment
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // IsCommentNoName
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // ResourceType
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // UploadPicNames
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // SmallPicUrl
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // ChID
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // parentID
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // ShowOrder
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // Longitude
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // Latitude
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // ReadNo
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // BandChID
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // CommentNum
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // RPID
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // RPNum
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // ResourceFlag
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // ResourceUrl
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // FakeReadNo
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // ThemeID
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // ResourceCSS
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // GetGoodPoint
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // PicPath
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // CommunityName
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // IsNewTopice
            cursor.getShort(offset + 34) != 0, // isRead
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // VoteType
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // VoteRemark
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // VoteSubject1Name
            cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // VoteSubject2Name
            cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39), // VoteNum
            cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40), // VoteSubject1
            cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41), // VoteSubject2
            cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42), // VoteSubject1Percent
            cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43), // VoteSubject2Percent
            cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44), // HornName
            cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45), // HornColor
            cursor.isNull(offset + 46) ? null : cursor.getString(offset + 46), // ResourcePdForm
            cursor.isNull(offset + 47) ? null : cursor.getString(offset + 47), // Topice
            cursor.isNull(offset + 48) ? null : cursor.getString(offset + 48), // channelName1
            cursor.isNull(offset + 49) ? null : cursor.getString(offset + 49), // channelID
            cursor.isNull(offset + 50) ? null : cursor.getString(offset + 50), // MediaLogo
            cursor.isNull(offset + 51) ? null : cursor.getString(offset + 51), // MediaUrl
            cursor.isNull(offset + 52) ? null : cursor.getString(offset + 52), // MediaName
            cursor.isNull(offset + 53) ? null : cursor.getString(offset + 53), // RedirectType
            cursor.isNull(offset + 54) ? null : cursor.getString(offset + 54), // JsonKey
            cursor.isNull(offset + 55) ? null : cursor.getString(offset + 55), // ResUnitName
            cursor.isNull(offset + 56) ? null : cursor.getString(offset + 56) // Address
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NewsListModel entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setChannelLogo(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setResourceGUID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setChannelName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSummary(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSourceForm(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCreateTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setUpdateTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAuditTime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setIsComment(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setIsCommentNoName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setResourceType(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setUploadPicNames(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setSmallPicUrl(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setChID(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setParentID(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setShowOrder(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setLongitude(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setLatitude(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setReadNo(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setBandChID(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setCommentNum(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setRPID(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setRPNum(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setResourceFlag(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setResourceUrl(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setFakeReadNo(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setThemeID(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setResourceCSS(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setGetGoodPoint(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setPicPath(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setCommunityName(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setIsNewTopice(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setIsRead(cursor.getShort(offset + 34) != 0);
        entity.setVoteType(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setVoteRemark(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setVoteSubject1Name(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setVoteSubject2Name(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setVoteNum(cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39));
        entity.setVoteSubject1(cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40));
        entity.setVoteSubject2(cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41));
        entity.setVoteSubject1Percent(cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42));
        entity.setVoteSubject2Percent(cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43));
        entity.setHornName(cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44));
        entity.setHornColor(cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45));
        entity.setResourcePdForm(cursor.isNull(offset + 46) ? null : cursor.getString(offset + 46));
        entity.setTopice(cursor.isNull(offset + 47) ? null : cursor.getString(offset + 47));
        entity.setChannelName1(cursor.isNull(offset + 48) ? null : cursor.getString(offset + 48));
        entity.setChannelID(cursor.isNull(offset + 49) ? null : cursor.getString(offset + 49));
        entity.setMediaLogo(cursor.isNull(offset + 50) ? null : cursor.getString(offset + 50));
        entity.setMediaUrl(cursor.isNull(offset + 51) ? null : cursor.getString(offset + 51));
        entity.setMediaName(cursor.isNull(offset + 52) ? null : cursor.getString(offset + 52));
        entity.setRedirectType(cursor.isNull(offset + 53) ? null : cursor.getString(offset + 53));
        entity.setJsonKey(cursor.isNull(offset + 54) ? null : cursor.getString(offset + 54));
        entity.setResUnitName(cursor.isNull(offset + 55) ? null : cursor.getString(offset + 55));
        entity.setAddress(cursor.isNull(offset + 56) ? null : cursor.getString(offset + 56));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NewsListModel entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NewsListModel entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NewsListModel entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
package com.lnr.android.base.framework.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.dingtai.android.library.news.model.NewsDetailModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEWS_DETAIL_MODEL".
*/
public class NewsDetailModelDao extends AbstractDao<NewsDetailModel, Long> {

    public static final String TABLENAME = "NEWS_DETAIL_MODEL";

    /**
     * Properties of entity NewsDetailModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property ID = new Property(1, String.class, "ID", false, "ID");
        public final static Property ResourceGUID = new Property(2, String.class, "ResourceGUID", false, "RESOURCE_GUID");
        public final static Property Title = new Property(3, String.class, "Title", false, "TITLE");
        public final static Property SubTitle = new Property(4, String.class, "subTitle", false, "SUB_TITLE");
        public final static Property Summary = new Property(5, String.class, "Summary", false, "SUMMARY");
        public final static Property Content = new Property(6, String.class, "Content", false, "CONTENT");
        public final static Property SourceForm = new Property(7, String.class, "SourceForm", false, "SOURCE_FORM");
        public final static Property UpdateTime = new Property(8, String.class, "UpdateTime", false, "UPDATE_TIME");
        public final static Property AuditTime = new Property(9, String.class, "AuditTime", false, "AUDIT_TIME");
        public final static Property CreateTime = new Property(10, String.class, "CreateTime", false, "CREATE_TIME");
        public final static Property ResourceType = new Property(11, String.class, "ResourceType", false, "RESOURCE_TYPE");
        public final static Property SmallPicUrl = new Property(12, String.class, "SmallPicUrl", false, "SMALL_PIC_URL");
        public final static Property UploadPicNames = new Property(13, String.class, "UploadPicNames", false, "UPLOAD_PIC_NAMES");
        public final static Property IsComment = new Property(14, String.class, "IsComment", false, "IS_COMMENT");
        public final static Property ChID = new Property(15, String.class, "ChID", false, "CH_ID");
        public final static Property ShowOrder = new Property(16, String.class, "ShowOrder", false, "SHOW_ORDER");
        public final static Property Longitude = new Property(17, String.class, "Longitude", false, "LONGITUDE");
        public final static Property Latitude = new Property(18, String.class, "Latitude", false, "LATITUDE");
        public final static Property ReadNo = new Property(19, String.class, "ReadNo", false, "READ_NO");
        public final static Property BandChID = new Property(20, String.class, "BandChID", false, "BAND_CH_ID");
        public final static Property CommentNum = new Property(21, String.class, "CommentNum", false, "COMMENT_NUM");
        public final static Property Journalist = new Property(22, String.class, "Journalist", false, "JOURNALIST");
        public final static Property ChannelName = new Property(23, String.class, "ChannelName", false, "CHANNEL_NAME");
        public final static Property ThemeName = new Property(24, String.class, "ThemeName", false, "THEME_NAME");
        public final static Property IsCommentNoName = new Property(25, String.class, "IsCommentNoName", false, "IS_COMMENT_NO_NAME");
        public final static Property ResourceFlag = new Property(26, String.class, "ResourceFlag", false, "RESOURCE_FLAG");
        public final static Property ResourceUrl = new Property(27, String.class, "ResourceUrl", false, "RESOURCE_URL");
        public final static Property Comments = new Property(28, String.class, "Comments", false, "COMMENTS");
        public final static Property FakeReadNo = new Property(29, String.class, "FakeReadNo", false, "FAKE_READ_NO");
        public final static Property NewsGetGoodPoint = new Property(30, String.class, "NewsGetGoodPoint", false, "NEWS_GET_GOOD_POINT");
        public final static Property ThemeID = new Property(31, String.class, "ThemeID", false, "THEME_ID");
        public final static Property AudioUrl = new Property(32, String.class, "AudioUrl", false, "AUDIO_URL");
        public final static Property AudioLength = new Property(33, String.class, "AudioLength", false, "AUDIO_LENGTH");
        public final static Property RPNum = new Property(34, String.class, "RPNum", false, "RPNUM");
        public final static Property KeyWords = new Property(35, String.class, "KeyWords", false, "KEY_WORDS");
        public final static Property Author = new Property(36, String.class, "Author", false, "AUTHOR");
        public final static Property NewsDetailStyle = new Property(37, String.class, "NewsDetailStyle", false, "NEWS_DETAIL_STYLE");
        public final static Property NoHtmlContent = new Property(38, String.class, "NoHtmlContent", false, "NO_HTML_CONTENT");
        public final static Property ResourceCSS = new Property(39, String.class, "ResourceCSS", false, "RESOURCE_CSS");
        public final static Property VoteType = new Property(40, String.class, "VoteType", false, "VOTE_TYPE");
        public final static Property VoteRemark = new Property(41, String.class, "VoteRemark", false, "VOTE_REMARK");
        public final static Property VoteSubject1Name = new Property(42, String.class, "VoteSubject1Name", false, "VOTE_SUBJECT1_NAME");
        public final static Property VoteSubject2Name = new Property(43, String.class, "VoteSubject2Name", false, "VOTE_SUBJECT2_NAME");
        public final static Property VoteNum = new Property(44, String.class, "VoteNum", false, "VOTE_NUM");
        public final static Property VoteSubject1 = new Property(45, String.class, "VoteSubject1", false, "VOTE_SUBJECT1");
        public final static Property VoteSubject2 = new Property(46, String.class, "VoteSubject2", false, "VOTE_SUBJECT2");
        public final static Property VoteSubject1Percent = new Property(47, String.class, "VoteSubject1Percent", false, "VOTE_SUBJECT1_PERCENT");
        public final static Property VoteSubject2Percent = new Property(48, String.class, "VoteSubject2Percent", false, "VOTE_SUBJECT2_PERCENT");
        public final static Property ADLinkUrl = new Property(49, String.class, "ADLinkUrl", false, "ADLINK_URL");
        public final static Property ADimgUrl = new Property(50, String.class, "ADimgUrl", false, "ADIMG_URL");
        public final static Property ADName = new Property(51, String.class, "ADName", false, "ADNAME");
    }


    public NewsDetailModelDao(DaoConfig config) {
        super(config);
    }
    
    public NewsDetailModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_DETAIL_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"ID\" TEXT," + // 1: ID
                "\"RESOURCE_GUID\" TEXT UNIQUE ," + // 2: ResourceGUID
                "\"TITLE\" TEXT," + // 3: Title
                "\"SUB_TITLE\" TEXT," + // 4: subTitle
                "\"SUMMARY\" TEXT," + // 5: Summary
                "\"CONTENT\" TEXT," + // 6: Content
                "\"SOURCE_FORM\" TEXT," + // 7: SourceForm
                "\"UPDATE_TIME\" TEXT," + // 8: UpdateTime
                "\"AUDIT_TIME\" TEXT," + // 9: AuditTime
                "\"CREATE_TIME\" TEXT," + // 10: CreateTime
                "\"RESOURCE_TYPE\" TEXT," + // 11: ResourceType
                "\"SMALL_PIC_URL\" TEXT," + // 12: SmallPicUrl
                "\"UPLOAD_PIC_NAMES\" TEXT," + // 13: UploadPicNames
                "\"IS_COMMENT\" TEXT," + // 14: IsComment
                "\"CH_ID\" TEXT," + // 15: ChID
                "\"SHOW_ORDER\" TEXT," + // 16: ShowOrder
                "\"LONGITUDE\" TEXT," + // 17: Longitude
                "\"LATITUDE\" TEXT," + // 18: Latitude
                "\"READ_NO\" TEXT," + // 19: ReadNo
                "\"BAND_CH_ID\" TEXT," + // 20: BandChID
                "\"COMMENT_NUM\" TEXT," + // 21: CommentNum
                "\"JOURNALIST\" TEXT," + // 22: Journalist
                "\"CHANNEL_NAME\" TEXT," + // 23: ChannelName
                "\"THEME_NAME\" TEXT," + // 24: ThemeName
                "\"IS_COMMENT_NO_NAME\" TEXT," + // 25: IsCommentNoName
                "\"RESOURCE_FLAG\" TEXT," + // 26: ResourceFlag
                "\"RESOURCE_URL\" TEXT," + // 27: ResourceUrl
                "\"COMMENTS\" TEXT," + // 28: Comments
                "\"FAKE_READ_NO\" TEXT," + // 29: FakeReadNo
                "\"NEWS_GET_GOOD_POINT\" TEXT," + // 30: NewsGetGoodPoint
                "\"THEME_ID\" TEXT," + // 31: ThemeID
                "\"AUDIO_URL\" TEXT," + // 32: AudioUrl
                "\"AUDIO_LENGTH\" TEXT," + // 33: AudioLength
                "\"RPNUM\" TEXT," + // 34: RPNum
                "\"KEY_WORDS\" TEXT," + // 35: KeyWords
                "\"AUTHOR\" TEXT," + // 36: Author
                "\"NEWS_DETAIL_STYLE\" TEXT," + // 37: NewsDetailStyle
                "\"NO_HTML_CONTENT\" TEXT," + // 38: NoHtmlContent
                "\"RESOURCE_CSS\" TEXT," + // 39: ResourceCSS
                "\"VOTE_TYPE\" TEXT," + // 40: VoteType
                "\"VOTE_REMARK\" TEXT," + // 41: VoteRemark
                "\"VOTE_SUBJECT1_NAME\" TEXT," + // 42: VoteSubject1Name
                "\"VOTE_SUBJECT2_NAME\" TEXT," + // 43: VoteSubject2Name
                "\"VOTE_NUM\" TEXT," + // 44: VoteNum
                "\"VOTE_SUBJECT1\" TEXT," + // 45: VoteSubject1
                "\"VOTE_SUBJECT2\" TEXT," + // 46: VoteSubject2
                "\"VOTE_SUBJECT1_PERCENT\" TEXT," + // 47: VoteSubject1Percent
                "\"VOTE_SUBJECT2_PERCENT\" TEXT," + // 48: VoteSubject2Percent
                "\"ADLINK_URL\" TEXT," + // 49: ADLinkUrl
                "\"ADIMG_URL\" TEXT," + // 50: ADimgUrl
                "\"ADNAME\" TEXT);"); // 51: ADName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_DETAIL_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NewsDetailModel entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(2, ID);
        }
 
        String ResourceGUID = entity.getResourceGUID();
        if (ResourceGUID != null) {
            stmt.bindString(3, ResourceGUID);
        }
 
        String Title = entity.getTitle();
        if (Title != null) {
            stmt.bindString(4, Title);
        }
 
        String subTitle = entity.getSubTitle();
        if (subTitle != null) {
            stmt.bindString(5, subTitle);
        }
 
        String Summary = entity.getSummary();
        if (Summary != null) {
            stmt.bindString(6, Summary);
        }
 
        String Content = entity.getContent();
        if (Content != null) {
            stmt.bindString(7, Content);
        }
 
        String SourceForm = entity.getSourceForm();
        if (SourceForm != null) {
            stmt.bindString(8, SourceForm);
        }
 
        String UpdateTime = entity.getUpdateTime();
        if (UpdateTime != null) {
            stmt.bindString(9, UpdateTime);
        }
 
        String AuditTime = entity.getAuditTime();
        if (AuditTime != null) {
            stmt.bindString(10, AuditTime);
        }
 
        String CreateTime = entity.getCreateTime();
        if (CreateTime != null) {
            stmt.bindString(11, CreateTime);
        }
 
        String ResourceType = entity.getResourceType();
        if (ResourceType != null) {
            stmt.bindString(12, ResourceType);
        }
 
        String SmallPicUrl = entity.getSmallPicUrl();
        if (SmallPicUrl != null) {
            stmt.bindString(13, SmallPicUrl);
        }
 
        String UploadPicNames = entity.getUploadPicNames();
        if (UploadPicNames != null) {
            stmt.bindString(14, UploadPicNames);
        }
 
        String IsComment = entity.getIsComment();
        if (IsComment != null) {
            stmt.bindString(15, IsComment);
        }
 
        String ChID = entity.getChID();
        if (ChID != null) {
            stmt.bindString(16, ChID);
        }
 
        String ShowOrder = entity.getShowOrder();
        if (ShowOrder != null) {
            stmt.bindString(17, ShowOrder);
        }
 
        String Longitude = entity.getLongitude();
        if (Longitude != null) {
            stmt.bindString(18, Longitude);
        }
 
        String Latitude = entity.getLatitude();
        if (Latitude != null) {
            stmt.bindString(19, Latitude);
        }
 
        String ReadNo = entity.getReadNo();
        if (ReadNo != null) {
            stmt.bindString(20, ReadNo);
        }
 
        String BandChID = entity.getBandChID();
        if (BandChID != null) {
            stmt.bindString(21, BandChID);
        }
 
        String CommentNum = entity.getCommentNum();
        if (CommentNum != null) {
            stmt.bindString(22, CommentNum);
        }
 
        String Journalist = entity.getJournalist();
        if (Journalist != null) {
            stmt.bindString(23, Journalist);
        }
 
        String ChannelName = entity.getChannelName();
        if (ChannelName != null) {
            stmt.bindString(24, ChannelName);
        }
 
        String ThemeName = entity.getThemeName();
        if (ThemeName != null) {
            stmt.bindString(25, ThemeName);
        }
 
        String IsCommentNoName = entity.getIsCommentNoName();
        if (IsCommentNoName != null) {
            stmt.bindString(26, IsCommentNoName);
        }
 
        String ResourceFlag = entity.getResourceFlag();
        if (ResourceFlag != null) {
            stmt.bindString(27, ResourceFlag);
        }
 
        String ResourceUrl = entity.getResourceUrl();
        if (ResourceUrl != null) {
            stmt.bindString(28, ResourceUrl);
        }
 
        String Comments = entity.getComments();
        if (Comments != null) {
            stmt.bindString(29, Comments);
        }
 
        String FakeReadNo = entity.getFakeReadNo();
        if (FakeReadNo != null) {
            stmt.bindString(30, FakeReadNo);
        }
 
        String NewsGetGoodPoint = entity.getNewsGetGoodPoint();
        if (NewsGetGoodPoint != null) {
            stmt.bindString(31, NewsGetGoodPoint);
        }
 
        String ThemeID = entity.getThemeID();
        if (ThemeID != null) {
            stmt.bindString(32, ThemeID);
        }
 
        String AudioUrl = entity.getAudioUrl();
        if (AudioUrl != null) {
            stmt.bindString(33, AudioUrl);
        }
 
        String AudioLength = entity.getAudioLength();
        if (AudioLength != null) {
            stmt.bindString(34, AudioLength);
        }
 
        String RPNum = entity.getRPNum();
        if (RPNum != null) {
            stmt.bindString(35, RPNum);
        }
 
        String KeyWords = entity.getKeyWords();
        if (KeyWords != null) {
            stmt.bindString(36, KeyWords);
        }
 
        String Author = entity.getAuthor();
        if (Author != null) {
            stmt.bindString(37, Author);
        }
 
        String NewsDetailStyle = entity.getNewsDetailStyle();
        if (NewsDetailStyle != null) {
            stmt.bindString(38, NewsDetailStyle);
        }
 
        String NoHtmlContent = entity.getNoHtmlContent();
        if (NoHtmlContent != null) {
            stmt.bindString(39, NoHtmlContent);
        }
 
        String ResourceCSS = entity.getResourceCSS();
        if (ResourceCSS != null) {
            stmt.bindString(40, ResourceCSS);
        }
 
        String VoteType = entity.getVoteType();
        if (VoteType != null) {
            stmt.bindString(41, VoteType);
        }
 
        String VoteRemark = entity.getVoteRemark();
        if (VoteRemark != null) {
            stmt.bindString(42, VoteRemark);
        }
 
        String VoteSubject1Name = entity.getVoteSubject1Name();
        if (VoteSubject1Name != null) {
            stmt.bindString(43, VoteSubject1Name);
        }
 
        String VoteSubject2Name = entity.getVoteSubject2Name();
        if (VoteSubject2Name != null) {
            stmt.bindString(44, VoteSubject2Name);
        }
 
        String VoteNum = entity.getVoteNum();
        if (VoteNum != null) {
            stmt.bindString(45, VoteNum);
        }
 
        String VoteSubject1 = entity.getVoteSubject1();
        if (VoteSubject1 != null) {
            stmt.bindString(46, VoteSubject1);
        }
 
        String VoteSubject2 = entity.getVoteSubject2();
        if (VoteSubject2 != null) {
            stmt.bindString(47, VoteSubject2);
        }
 
        String VoteSubject1Percent = entity.getVoteSubject1Percent();
        if (VoteSubject1Percent != null) {
            stmt.bindString(48, VoteSubject1Percent);
        }
 
        String VoteSubject2Percent = entity.getVoteSubject2Percent();
        if (VoteSubject2Percent != null) {
            stmt.bindString(49, VoteSubject2Percent);
        }
 
        String ADLinkUrl = entity.getADLinkUrl();
        if (ADLinkUrl != null) {
            stmt.bindString(50, ADLinkUrl);
        }
 
        String ADimgUrl = entity.getADimgUrl();
        if (ADimgUrl != null) {
            stmt.bindString(51, ADimgUrl);
        }
 
        String ADName = entity.getADName();
        if (ADName != null) {
            stmt.bindString(52, ADName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NewsDetailModel entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(2, ID);
        }
 
        String ResourceGUID = entity.getResourceGUID();
        if (ResourceGUID != null) {
            stmt.bindString(3, ResourceGUID);
        }
 
        String Title = entity.getTitle();
        if (Title != null) {
            stmt.bindString(4, Title);
        }
 
        String subTitle = entity.getSubTitle();
        if (subTitle != null) {
            stmt.bindString(5, subTitle);
        }
 
        String Summary = entity.getSummary();
        if (Summary != null) {
            stmt.bindString(6, Summary);
        }
 
        String Content = entity.getContent();
        if (Content != null) {
            stmt.bindString(7, Content);
        }
 
        String SourceForm = entity.getSourceForm();
        if (SourceForm != null) {
            stmt.bindString(8, SourceForm);
        }
 
        String UpdateTime = entity.getUpdateTime();
        if (UpdateTime != null) {
            stmt.bindString(9, UpdateTime);
        }
 
        String AuditTime = entity.getAuditTime();
        if (AuditTime != null) {
            stmt.bindString(10, AuditTime);
        }
 
        String CreateTime = entity.getCreateTime();
        if (CreateTime != null) {
            stmt.bindString(11, CreateTime);
        }
 
        String ResourceType = entity.getResourceType();
        if (ResourceType != null) {
            stmt.bindString(12, ResourceType);
        }
 
        String SmallPicUrl = entity.getSmallPicUrl();
        if (SmallPicUrl != null) {
            stmt.bindString(13, SmallPicUrl);
        }
 
        String UploadPicNames = entity.getUploadPicNames();
        if (UploadPicNames != null) {
            stmt.bindString(14, UploadPicNames);
        }
 
        String IsComment = entity.getIsComment();
        if (IsComment != null) {
            stmt.bindString(15, IsComment);
        }
 
        String ChID = entity.getChID();
        if (ChID != null) {
            stmt.bindString(16, ChID);
        }
 
        String ShowOrder = entity.getShowOrder();
        if (ShowOrder != null) {
            stmt.bindString(17, ShowOrder);
        }
 
        String Longitude = entity.getLongitude();
        if (Longitude != null) {
            stmt.bindString(18, Longitude);
        }
 
        String Latitude = entity.getLatitude();
        if (Latitude != null) {
            stmt.bindString(19, Latitude);
        }
 
        String ReadNo = entity.getReadNo();
        if (ReadNo != null) {
            stmt.bindString(20, ReadNo);
        }
 
        String BandChID = entity.getBandChID();
        if (BandChID != null) {
            stmt.bindString(21, BandChID);
        }
 
        String CommentNum = entity.getCommentNum();
        if (CommentNum != null) {
            stmt.bindString(22, CommentNum);
        }
 
        String Journalist = entity.getJournalist();
        if (Journalist != null) {
            stmt.bindString(23, Journalist);
        }
 
        String ChannelName = entity.getChannelName();
        if (ChannelName != null) {
            stmt.bindString(24, ChannelName);
        }
 
        String ThemeName = entity.getThemeName();
        if (ThemeName != null) {
            stmt.bindString(25, ThemeName);
        }
 
        String IsCommentNoName = entity.getIsCommentNoName();
        if (IsCommentNoName != null) {
            stmt.bindString(26, IsCommentNoName);
        }
 
        String ResourceFlag = entity.getResourceFlag();
        if (ResourceFlag != null) {
            stmt.bindString(27, ResourceFlag);
        }
 
        String ResourceUrl = entity.getResourceUrl();
        if (ResourceUrl != null) {
            stmt.bindString(28, ResourceUrl);
        }
 
        String Comments = entity.getComments();
        if (Comments != null) {
            stmt.bindString(29, Comments);
        }
 
        String FakeReadNo = entity.getFakeReadNo();
        if (FakeReadNo != null) {
            stmt.bindString(30, FakeReadNo);
        }
 
        String NewsGetGoodPoint = entity.getNewsGetGoodPoint();
        if (NewsGetGoodPoint != null) {
            stmt.bindString(31, NewsGetGoodPoint);
        }
 
        String ThemeID = entity.getThemeID();
        if (ThemeID != null) {
            stmt.bindString(32, ThemeID);
        }
 
        String AudioUrl = entity.getAudioUrl();
        if (AudioUrl != null) {
            stmt.bindString(33, AudioUrl);
        }
 
        String AudioLength = entity.getAudioLength();
        if (AudioLength != null) {
            stmt.bindString(34, AudioLength);
        }
 
        String RPNum = entity.getRPNum();
        if (RPNum != null) {
            stmt.bindString(35, RPNum);
        }
 
        String KeyWords = entity.getKeyWords();
        if (KeyWords != null) {
            stmt.bindString(36, KeyWords);
        }
 
        String Author = entity.getAuthor();
        if (Author != null) {
            stmt.bindString(37, Author);
        }
 
        String NewsDetailStyle = entity.getNewsDetailStyle();
        if (NewsDetailStyle != null) {
            stmt.bindString(38, NewsDetailStyle);
        }
 
        String NoHtmlContent = entity.getNoHtmlContent();
        if (NoHtmlContent != null) {
            stmt.bindString(39, NoHtmlContent);
        }
 
        String ResourceCSS = entity.getResourceCSS();
        if (ResourceCSS != null) {
            stmt.bindString(40, ResourceCSS);
        }
 
        String VoteType = entity.getVoteType();
        if (VoteType != null) {
            stmt.bindString(41, VoteType);
        }
 
        String VoteRemark = entity.getVoteRemark();
        if (VoteRemark != null) {
            stmt.bindString(42, VoteRemark);
        }
 
        String VoteSubject1Name = entity.getVoteSubject1Name();
        if (VoteSubject1Name != null) {
            stmt.bindString(43, VoteSubject1Name);
        }
 
        String VoteSubject2Name = entity.getVoteSubject2Name();
        if (VoteSubject2Name != null) {
            stmt.bindString(44, VoteSubject2Name);
        }
 
        String VoteNum = entity.getVoteNum();
        if (VoteNum != null) {
            stmt.bindString(45, VoteNum);
        }
 
        String VoteSubject1 = entity.getVoteSubject1();
        if (VoteSubject1 != null) {
            stmt.bindString(46, VoteSubject1);
        }
 
        String VoteSubject2 = entity.getVoteSubject2();
        if (VoteSubject2 != null) {
            stmt.bindString(47, VoteSubject2);
        }
 
        String VoteSubject1Percent = entity.getVoteSubject1Percent();
        if (VoteSubject1Percent != null) {
            stmt.bindString(48, VoteSubject1Percent);
        }
 
        String VoteSubject2Percent = entity.getVoteSubject2Percent();
        if (VoteSubject2Percent != null) {
            stmt.bindString(49, VoteSubject2Percent);
        }
 
        String ADLinkUrl = entity.getADLinkUrl();
        if (ADLinkUrl != null) {
            stmt.bindString(50, ADLinkUrl);
        }
 
        String ADimgUrl = entity.getADimgUrl();
        if (ADimgUrl != null) {
            stmt.bindString(51, ADimgUrl);
        }
 
        String ADName = entity.getADName();
        if (ADName != null) {
            stmt.bindString(52, ADName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NewsDetailModel readEntity(Cursor cursor, int offset) {
        NewsDetailModel entity = new NewsDetailModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // ResourceGUID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // subTitle
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // Summary
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // Content
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // SourceForm
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // UpdateTime
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // AuditTime
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // CreateTime
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // ResourceType
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // SmallPicUrl
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // UploadPicNames
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // IsComment
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // ChID
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // ShowOrder
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // Longitude
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // Latitude
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // ReadNo
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // BandChID
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // CommentNum
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // Journalist
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // ChannelName
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // ThemeName
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // IsCommentNoName
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // ResourceFlag
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // ResourceUrl
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // Comments
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // FakeReadNo
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // NewsGetGoodPoint
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // ThemeID
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // AudioUrl
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // AudioLength
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // RPNum
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // KeyWords
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // Author
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // NewsDetailStyle
            cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // NoHtmlContent
            cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39), // ResourceCSS
            cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40), // VoteType
            cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41), // VoteRemark
            cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42), // VoteSubject1Name
            cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43), // VoteSubject2Name
            cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44), // VoteNum
            cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45), // VoteSubject1
            cursor.isNull(offset + 46) ? null : cursor.getString(offset + 46), // VoteSubject2
            cursor.isNull(offset + 47) ? null : cursor.getString(offset + 47), // VoteSubject1Percent
            cursor.isNull(offset + 48) ? null : cursor.getString(offset + 48), // VoteSubject2Percent
            cursor.isNull(offset + 49) ? null : cursor.getString(offset + 49), // ADLinkUrl
            cursor.isNull(offset + 50) ? null : cursor.getString(offset + 50), // ADimgUrl
            cursor.isNull(offset + 51) ? null : cursor.getString(offset + 51) // ADName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NewsDetailModel entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setResourceGUID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSubTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSummary(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setContent(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSourceForm(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setUpdateTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAuditTime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCreateTime(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setResourceType(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setSmallPicUrl(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setUploadPicNames(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setIsComment(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setChID(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setShowOrder(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setLongitude(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setLatitude(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setReadNo(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setBandChID(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setCommentNum(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setJournalist(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setChannelName(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setThemeName(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setIsCommentNoName(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setResourceFlag(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setResourceUrl(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setComments(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setFakeReadNo(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setNewsGetGoodPoint(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setThemeID(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setAudioUrl(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setAudioLength(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setRPNum(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setKeyWords(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setAuthor(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setNewsDetailStyle(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setNoHtmlContent(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setResourceCSS(cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39));
        entity.setVoteType(cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40));
        entity.setVoteRemark(cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41));
        entity.setVoteSubject1Name(cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42));
        entity.setVoteSubject2Name(cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43));
        entity.setVoteNum(cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44));
        entity.setVoteSubject1(cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45));
        entity.setVoteSubject2(cursor.isNull(offset + 46) ? null : cursor.getString(offset + 46));
        entity.setVoteSubject1Percent(cursor.isNull(offset + 47) ? null : cursor.getString(offset + 47));
        entity.setVoteSubject2Percent(cursor.isNull(offset + 48) ? null : cursor.getString(offset + 48));
        entity.setADLinkUrl(cursor.isNull(offset + 49) ? null : cursor.getString(offset + 49));
        entity.setADimgUrl(cursor.isNull(offset + 50) ? null : cursor.getString(offset + 50));
        entity.setADName(cursor.isNull(offset + 51) ? null : cursor.getString(offset + 51));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NewsDetailModel entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NewsDetailModel entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NewsDetailModel entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

package com.dingtai.android.library.video.api;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.github.lnr.inject.annotation.AsynCall;
import com.github.lnr.inject.annotation.Task;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author:lnr
 * date:2018/8/29
 */
@AsynCall
public interface VideoApi {

    /**
     * 点播频道列表
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/VodAPI.ashx?action=GetResByTrueProgram")
    Observable<JSONObject> GetResByTrueProgram(@Query("RecType") String RecType,
                                               @Query("STid") String STid, @Query("sign") String sign);


    /**
     * 添加点击量
     *
     * @param ID 视频id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/VodAPI.ashx?action=AddReadNum&")
    Observable<Object> AddReadNum(@Query("ID") String ID,
                                  @Query("STid") String STid, @Query("sign") String sign);


    /**
     * 获取电视点播频道子列表  刷新
     *
     * @param ID  频道id
     * @param top 获取的数量
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/VodAPI.ashx?action=GetDownConetent")
    Observable<JSONObject> GetDownConetent(@Query("ID") String ID, @Query("top") String top,
                                           @Query("STid") String STid, @Query("sign") String sign);

    /**
     * 获取电视点播频道子列表 加载更多
     *
     * @param ID   频道id
     * @param top  获取的数量
     * @param dtop 偏移量
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/VodAPI.ashx?action=GetUpContent")
    Observable<JSONObject> GetUpContent(@Query("ID") String ID, @Query("top") String top, @Query("dtop") String dtop,
                                        @Query("STid") String STid, @Query("sign") String sign);

    /**
     * 直播点赞
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/LiveAPI.ashx?action=AddLiveGoodPoint")
    Observable<JSONObject> addLiveZan(@Query("ID") String ID, @Query("UserGUID") String UserGUID,
                                      @Query("NickName") String NickName);

    /**
     * 获取直播频道列表
     *
     * @param type 频道类型
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/LiveAPI.ashx?action=GetLiveByType")
    Observable<JSONObject> GetLiveByType(@Query("type") String type, @Query("top") String top,
                                         @Query("dtop") String dtop,
                                         @Query("STid") String STid, @Query("sign") String sign,
                                         @Query("channelType") String channelType);

    /**
     * 获取直播频道列表 （永州新版）
     *
     * @param type 频道类型
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/LiveAPI.ashx?action=GetLiveByTypeNew")
    Observable<JSONObject> GetLiveByTypeNew(@Query("type") String type, @Query("top") String top,
                                            @Query("dtop") String dtop,
                                            @Query("STid") String STid, @Query("sign") String sign);


    /**
     * 获取直播频道列表 （今日郴州版）
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/LiveAPI.ashx?action=GetLive")
    Observable<JSONObject> GetLive(@Query("top") String top,
                                   @Query("dtop") String dtop,
                                   @Query("STid") String STid);

    /**
     * 获取电视直播 评论列表
     *
     * @param LiveID  直播id
     * @param Num     获取数量
     * @param dtop    已经有的数量  即偏移量
     * @param TabCode 不知道
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/LiveAPI.ashx?action=GetNewsComment")
    Observable<JSONObject> GetNewsComment(@Query("LiveID") String LiveID, @Query("Num") String Num,
                                          @Query("dtop") String dtop, @Query("TabCode") String TabCode,
                                          @Query("STid") String STid, @Query("sign") String sign);

    /**
     * 获取直播 评论列表
     *
     * @param LiveID  直播id
     * @param Num     获取数量
     * @param dtop    已经有的数量  即偏移量
     * @param TabCode 不知道
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/NewsLiveCommentAPI.ashx?action=GetNewsLiveComment")
    Observable<JSONObject> GetNewsLiveComment(@Query("eid") String LiveID, @Query("Num") String Num,
                                              @Query("dtop") String dtop, @Query("TabCode") String TabCode,
                                              @Query("STid") String STid, @Query("sign") String sign);


    /* 发布评论 start  */

    /**
     * 发布评论 适用类型  直播type = 1、2 评论类型为基础评论
     *
     * @param LiveID 直播id
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/LiveAPI.ashx?action=AddNewComment")
    Observable<JSONObject> addCommentType12(@Query("LID") String LiveID, @Query("CommentContent") String content,
                                            @Query("UserGUID") String userid,
                                            @Query("STid") String STid, @Query("sign") String sign);

    /**
     * 发布评论 适用类型  直播type = 3、4 评论类型为基础评论
     *
     * @param LiveID 直播id
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/NewsLiveCommentSubAPI.ashx?action=AddNewComment")
    Observable<JSONObject> addCommentType34(@Query("LID") String LiveID, @Query("CommentContent") String content,
                                            @Query("UserGUID") String userid,
                                            @Query("STid") String STid, @Query("sign") String sign);


    /**
     * 发布评论 适用类型  直播type = 1、2 评论类型为子评论
     *
     * @param rid 评论id
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/LiveAPI.ashx?action=AddNewSubComment")
    Observable<JSONObject> addCommentType12Child(@Query("LCID") String rid, @Query("CommentContent") String content,
                                                 @Query("UserGUID") String userid,
                                                 @Query("STid") String STid, @Query("sign") String sign);


    /**
     * 发布评论 适用类型  直播type = 3、4 评论类型为子评论
     *
     * @param rid 评论id
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/NewsLiveCommentSubAPI.ashx?action=AddNewSubComment")
    Observable<JSONObject> addCommentType34Child(@Query("LCID") String rid, @Query("CommentContent") String content,
                                                 @Query("UserGUID") String userid,
                                                 @Query("STid") String STid, @Query("sign") String sign);



    /* 发布评论end  */


    /**
     * 获取直播 图文列表
     *
     * @param LiveID  直播id
     * @param Num     获取数量
     * @param dtop    已经有的数量  即偏移量
     * @param TabCode 不知道
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/NewsLiveEventsAPI.ashx?action=GetNewsContent")
    Observable<JSONObject> getLiveImageTextList(@Query("eid") String LiveID, @Query("num") String Num,
                                                @Query("dtop") String dtop, @Query("TabCode") String TabCode,
                                                @Query("STid") String STid, @Query("sign") String sign);

    /**
     * 获取直播 节目单
     *
     * @param LiveID  直播id
     * @param week    星期
     * @param TabCode 不知道
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/LiveAPI.ashx?action=GetProgramList")
    Observable<JSONObject> GetProgramList(@Query("LChID") String LiveID, @Query("week") String week,
                                          @Query("TabCode") String TabCode,
                                          @Query("STid") String STid, @Query("sign") String sign);

    /**
     * 获取直播 互动游戏
     *
     * @param LiveID   直播id
     * @param GameType 类型
     * @param TabCode  不知道
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/LiveLinkAPI.ashx?action=GetGame")
    Observable<JSONObject> GetGame(@Query("LiveID") String LiveID, @Query("GameType") String GameType,
                                   @Query("TabCode") String TabCode,
                                   @Query("STid") String STid, @Query("sign") String sign);

    /**
     * 添加点击量
     *
     * @param ID 视频id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/LiveAPI.ashx?action=AddReadNum")
    Observable<Object> AddLiveReadNum(@Query("ID") String ID,
                                      @Query("STid") String STid, @Query("sign") String sign);


    /**
     * 发布评论
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/NewsLiveEventsAPI.ashx?action=AddContentCommnet")
    Observable<JSONObject> addLiveComment(@Query("ContentID") String ID, @Query("CommentContent") String CommentContent
            , @Query("UserGUID") String UserGUID, @Query("StID") String StID);

    /**
     * 获取图文直播类型
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/NewsLiveEventsAPI.ashx?action=GetLiveDownEventList&num=999")
    Observable<JSONObject> getPublishImageTextLiveType(@Query("stid") String STid);

    /**
     * 发布图文直播
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/NewsLiveEventsAPI.ashx?action=InserNewsContent")
    Observable<JSONObject> InserNewsContent(@Query("UserGUID") String UserGUID,
                                            @Query("UserPhone") String UserPhone,
                                            @Query("EventID") String EventID,
                                            @Query("LiveType") String LiveType,
                                            @Query("Title") String Title,
                                            @Query("Content") String Content,
                                            @Query("PicUrl") String PicUrl,
                                            @Query("VideoUrl") String VideoUrl,
                                            @Query("FileDate") String FileDate,
                                            @Query("STid") String StID,
                                            @Query("Sign") String Sign);

    /**
     * 获取视频列表数据
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/MediaAPI.ashx")
    Observable<JSONObject> getVideoList(@Query("action") String action, @Query("ChannelID") String ChannelID,
                                        @Query("top") String top, @Query("dtop") String dtop, @Query("UserGUID") String UserGUID
            , @Query("StID") String StID);

    /**
     * 热播点赞
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/MediaAPI.ashx?action=AddGoodPointMTM")
    Observable<JSONObject> addVideoZan(@Query("ID") String ID, @Query("UserGUID") String UserGUID
            , @Query("StID") String StID);

    /**
     * 删除热播点赞
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/MediaAPI.ashx?action=DelGoodPointMTM")
    Observable<JSONObject> delVideoZan(@Query("ID") String ID, @Query("UserGUID") String UserGUID
            , @Query("StID") String StID);

    /**
     * 获取热播详细信息
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/MediaAPI.ashx?action=GetVideoInfobyId")
    Observable<JSONObject> getVideoDetails(@Query("VideoID") String VideoID
            , @Query("StID") String StID);

    /**
     * 获取热播评论数据
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/MediaCommentAPI.ashx")
    Observable<JSONObject> getVideoCommentList(@Query("action") String action, @Query("MID") String MID,
                                               @Query("top") String top, @Query("dtop") String dtop
            , @Query("StID") String StID, @Query("sign") String sign);

    /**
     * 热播 发布评论
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/MediaCommentAPI.ashx?action=InsertMediaComment")
    Observable<JSONObject> addVideoComment(@Query("MID") String MID
            , @Query("userGUID") String userGUID, @Query("commentContent") String commentContent
            , @Query("StID") String StID);

    /**
     * 获取视频tab
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/MediaChannels.ashx?action=GetMediaChannelsList")
    Observable<JSONObject> getMediaChannelsList(@Query("ParentID") String ParentID, @Query("StID") String StID);

    /**
     * 获取 我上传的视频
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/MediaAPI.ashx?action=GetVideoListbyUserGUID")
    Observable<JSONObject> getMyUploadVideoList(@Query("UserGUID") String UserGUID, @Query("sign") String sign);

    /**
     * 获取 删除上传的视频
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/MediaAPI.ashx?action=DelMedia")
    Observable<JSONObject> delMyUploadVideoList(@Query("MediaGUID") String MediaGUID, @Query("sign") String sign);

    /**
     * 获取 发布视频
     */
    @Task(url = Resource.API.BASE)
    @POST("interface/MediaAPI.ashx?action=InsertMediaInfo")
    @FormUrlEncoded
    Observable<JSONObject> publishVideo(@Field("ChannelID") String ChannelID, @Field("UserGUID") String UserGUID,
                                        @Field("UserName") String UserName, @Field("Name") String Name,
                                        @Field("ImageUrl") String ImageUrl, @Field("Detail") String Detail,
                                        @Field("UploadType") String UploadType, @Field("FileDate") String FileDate,
                                        @Field("FileName") String FileName, @Field("FileExt") String FileExt,
                                        @Field("StID") String StID, @Field("sign") String sign);


    /**
     * 获取 点播评论
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/VodAPI.ashx?action=GetVodComment")
    Observable<JSONObject> getVodCommentList(@Query("VodID") String VodID, @Query("STid") String STid);


    /**
     * 点播评论点赞
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/VodAPI.ashx?action=AddGoodPoint")
    Observable<JSONObject> addVodCommentGoodPoint(@Query("ID") String ID, @Query("STid") String STid);

    /**
     * 点播发布评论
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/VodAPI.ashx?action=AddComment")
    Observable<JSONObject> addVodComment(@Query("VodID") String VodID, @Query("CommentContent") String CommentContent,
                                         @Query("UserGUID") String UserGUID, @Query("STid") String STid);

    /**
     * 点播回复评论
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/VodAPI.ashx?action=AddSubComment")
    Observable<JSONObject> addVodReplyComment(@Query("LCID") String LCID, @Query("CommentContent") String CommentContent,
                                              @Query("UserGUID") String UserGUID, @Query("STid") String STid);


    /**
     * 获取短视频列表数据
     * http://47.99.113.47:7080/Interface/PublicVideoAPI.ashx?action=GetVideoList&num=10&dtop=0
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/PublicVideoAPI.ashx?action=GetVideoList")
    Observable<JSONObject> getShortVideoList(@Query("num") String num, @Query("dtop") String dtop, @Query("StID") String StID);

    /**
     * 获取短视频主个人信息
     * http://47.99.113.47:7080/Interface/PublicVideoAPI.ashx?action=GetVideoList&num=10&dtop=0
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/PublicVideoAPI.ashx?action=GetPersonalInfo")
    Observable<JSONObject> getShortVideoUserInfo(@Query("UserGUID") String userGUID, @Query("StID") String StID);

    /**
     * 获取个人视频列表
     * http://47.99.113.47:7080/Interface/PublicVideoAPI.ashx?action=GetVideoList&num=10&dtop=0
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/PublicVideoAPI.ashx?action=GetPersonalVideos")
    Observable<JSONObject> getPersionShortVideoList(@Query("UserGUID") String userGUID, @Query("num") String num, @Query("dtop") String dtop, @Query("StID") String StID);

}

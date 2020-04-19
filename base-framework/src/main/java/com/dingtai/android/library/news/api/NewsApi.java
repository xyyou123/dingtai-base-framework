package com.dingtai.android.library.news.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.github.lnr.inject.annotation.AsynCall;
import com.github.lnr.inject.annotation.Task;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

@AsynCall
public interface NewsApi {


    /**
     * 获取启动广告
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/OpenPicAPI.ashx?action=GetOpenPicByStID")
    Observable<JSONObject> GetOpenPicByStID(@Query("ForApp") String ForApp, @Query("StID") String STid, @Query("sign") String sign);


    /**
     * 获取搜索关键字
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/NewsAPI.ashx?action=GetNewsKeyWord")
    Observable<ResponseBody> GetNewsKeyWord(@Query("StID") String STid, @Query("sign") String sign);


    /**
     * 根据关键字搜索新闻
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/NewsAPI.ashx?action=GetNewsKeyWordShangLa")
    Observable<JSONObject> GetNewsKeyWordShangLa(@Query("keywords") String keywords, @Query("top") String top,
                                                 @Query("dtop") String dtop, @Query("StID") String STid, @Query("sign") String sign);


    /**
     * 获取栏目
     * @param parentID 父栏目id 0=顶级栏目
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/NewsChannelAPI.ashx")
    Observable<JSONObject> GetNewsChannelList(@Query("action") String action, @Query("parentID") String parentID,
                                              @Query("STiD") String STid);

    /**
     * 获取广告
     * @param ChID 栏目id
     * @param sign 签名
     * @param ADType 类型
     * @param ADFor 未知(不知道什么鬼)
     * @param IsIndexAD 未知(不知道什么鬼)
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/ADsAPI.ashx?action=ListAd")
    Observable<JSONObject> getListAd(@Query("Chid") String ChID, @Query("sign") String sign,
                                     @Query("ADtype") String ADType, @Query("ADFor") String ADFor,
                                     @Query("IsIndexAD") String IsIndexAD, @Query("StID") String STid);

    /**
     * 广告点击统计
     * @param OPID 广告id
     * @param sign 签名
     * @param UserGUID 用户id
     * @param System 系统版本号
     * @param Device 设备型号
     * @param Resolution 分辨率
     * @param Network 网络类型
     * @param CarrierOperator 运营商
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/StatisticsAPI.ashx?action=InsertADStatistics")
    Observable<JSONObject> InsertADStatistics(@Query("OPID") String OPID, @Query("OPType") String OPType,
                                              @Query("sign") String sign, @Query("UserGUID") String UserGUID,
                                              @Query("System") String System, @Query("Device") String Device,
                                              @Query("Resolution") String Resolution, @Query("Network") String Network,
                                              @Query("CarrierOperator") String CarrierOperator, @Query("STiD") String STid);

    /**
     * 获取广告随机数
     * @param ChID 栏目id
     * @param sign 签名
     * @param ADType 类型
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/ADsAPI.ashx?action=ListAd")
    Observable<JSONArray> getCompareData(@Query("ChID") String ChID, @Query("sign") String sign, @Query("ADType") String ADType
            , @Query("STiD") String STid);

    /**
     * 刷新新闻列表
     * @param num 获取数量
     * @param sign 签名
     * @param chid 新闻类型id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/newsApi.ashx?action=GetNewsList")
    Observable<JSONObject> refreshGetNewsList(@Query("num") String num, @Query("sign") String sign, @Query("chid") String chid
            , @Query("STiD") String STid);

    /**
     * 刷新新闻列表
     * @param top 获取数量
     * @param sign 签名
     * @param chid 新闻类型id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/NewsChildAPI.ashx?action=GetNewsChildDownList")
    Observable<JSONObject> refreshGetNewsChildDownList(@Query("top") String top, @Query("sign") String sign, @Query("chid") String chid
            , @Query("STiD") String STid);

    /**
     * 刷新新闻列表
     * @param top 获取数量
     * @param dtop 本地数量
     * @param sign 签名
     * @param chid 新闻类型id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/NewsChildAPI.ashx?action=GetNewsChildUpList")
    Observable<JSONObject> loadGetNewsChildUpList(@Query("top") String top, @Query("dtop") String dtop, @Query("sign") String sign, @Query("chid") String chid
            , @Query("STiD") String STid);

    /**
     * 增加新闻分享次数
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/newsapi.ashx?action=AddShareNum")
    Observable<JSONObject> AddShareNum(@Query("ResourceGUID") String guid, @Query("STiD") String STid);

    /**
     * 获取新闻推荐页 更多新闻
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/AppNewHHIndexAPI.ashx?action=GetMoreNewsByType")
    Observable<JSONObject> getNewsFirstMoreNews(@Query("type") String type, @Query("top") String top,
                                                @Query("dtop") String dtop, @Query("StID") String StID);

    /**
     * 获取新闻详情
     * @param guid 新闻id
     * @param sign 签名
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/newsApi.ashx?action=NewsInfo")
    Observable<JSONObject> getNewsInfo(@Query("guid") String guid, @Query("sign") String sign
            , @Query("STiD") String STid);


    /**
     * 获取新闻评论列表
     * @param rid 新闻id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/CommentAPI.ashx?action=GetUp")
    Observable<JSONObject> getNewsCommentList(@Query("rid") String rid, @Query("num") String num
            , @Query("drop") String drop, @Query("FORAPP") String FORAPP
            , @Query("STiD") String STid, @Query("Sign") String sign);

    /**
     * 发布评论
     * @param userGUID 用户id
     * @param commentContent 内容
     * @param GetGoodPoint 不知道 以前传0
     * @param pid 不知道  以前传的null
     * @param rid 新闻id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/CommentAPI.ashx?action=InsertContent")
    Observable<JSONObject> InsertContent(@Query("userGUID") String userGUID, @Query("commentContent") String commentContent
            , @Query("GetGoodPoint") String GetGoodPoint, @Query("pid") String pid,
                                         @Query("rid") String rid
            , @Query("STiD") String STid, @Query("Sign") String sign);


    /**
     * 评论回复
     * @param userGUID 用户id
     * @param commentContent 内容
     * @param GetGoodPoint 不知道 以前传0
     * @param pid 回复的id
     * @param rid 新闻id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/CommentAPI.ashx?action=InsertContent")
    Observable<JSONObject> replyContent(@Query("userGUID") String userGUID, @Query("commentContent") String commentContent
            , @Query("GetGoodPoint") String GetGoodPoint, @Query("pid") String pid,
                                        @Query("rid") String rid
            , @Query("STiD") String STid, @Query("Sign") String sign);

    /**
     * 评论 点赞
     * @param Cid 评论id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/CommentAPI.ashx?action=addGoodPoint")
    Observable<JSONObject> addGoodPoint(@Query("Cid") String Cid
            , @Query("STiD") String STid, @Query("Sign") String sign);

    /**
     * 评论 回复点赞
     * @param Cid 评论id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/CommentAPI.ashx?action=AddSubGoodPoint")
    Observable<JSONObject> addSubGoodPoint(@Query("Sid") String Cid
            , @Query("STiD") String STid, @Query("Sign") String sign);

    /**
     * 点赞
     * @param Cid 评论id
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/newsApi.ashx?action=AddGoodPoint")
    Observable<JSONObject> addNewsGoodPoint(@Query("Cid") String Cid, @Query("UserGUID") String UserGUID
            , @Query("STiD") String STid, @Query("Sign") String sign);

    /**
     * 收藏
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/RegisterUserCollectAPI.ashx?action=InsertUserCollect")
    Observable<JSONObject> InsertUserCollect(@Query("CollectType") String CollectType
            , @Query("CollectID") String CollectID, @Query("UserGUID") String UserGUID
            , @Query("userName") String userName
            , @Query("STiD") String STid, @Query("Sign") String sign);

    /**
     * 删除收藏
     * @param ID ID
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/RegisterUserCollectAPI.ashx?action=DelUserCollect")
    Observable<JSONObject> DelUserCollect(@Query("ID") String ID
            , @Query("StID") String STid, @Query("sign") String sign);


    /**
     * 获取旧专题列表 刷新
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/newsApi.ashx?action=GetNewsList")
    Observable<JSONObject> getSubjectListRefresh(@Query("chid") String chid
            , @Query("num") String num
            , @Query("STID") String STid, @Query("sign") String sign);

    /**
     * 获取旧专题列表 加载更多
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/newsApi.ashx?action=ShangLa")
    Observable<JSONObject> getSubjectListLoadMore(@Query("chid") String chid
            , @Query("top") String top, @Query("dtop") String dtop
            , @Query("STID") String STid, @Query("sign") String sign);


    /**
     * 获取新专题
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/NewsTopicsAPI.ashx?action=GetChannAndNews")
    Observable<JSONObject> GetChannAndNews(@Query("ParentID") String ParentID
            , @Query("STID") String STid, @Query("sign") String sign);


    /**
     * 获取新专题 更多
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/NewsTopicsAPI.ashx?")
    Observable<JSONObject> GetMoreNewByChannUpList(@Query("action") String action,
                                                   @Query("ChID") String ChID, @Query("DeptID") String DeptID,
                                                   @Query("top") String top, @Query("dtop") String dtop
            , @Query("StID") String STid, @Query("sign") String sign);


    /**
     * 获取直播 相关新闻
     * @param LiveID 直播id
     */
    @Task(url = Resource.API.BASE)
    @GET("/interface/NewsChildAPI.ashx?action=GetLiveNews")
    Observable<JSONObject> GetLiveNews(@Query("liveid") String LiveID
            , @Query("STid") String STid);


     /**
     * 获取图集
     */
    @Task(url = Resource.API.BASE)
    @GET("Interface/D5MTImgsApi.ashx?action=GetImgsByPhotosId")
    Observable<JSONObject> GetImgsByPhotosId(@Query("PhotosID") String PhotosID
            , @Query("sign") String sign);

    /**
     * 投票
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/VoteAPI.ashx?action=AddVoteAndResMTM")
    Observable<JSONObject> AddVoteAndResMTM(@Query("UserGUID") String UserGUID
            , @Query("ResGUID") String ResGUID, @Query("VoteSubject") String VoteSubject);

}

package com.lnr.android.base.framework.mvp.call;

import com.alibaba.fastjson.JSONArray;
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
 * date:2018/11/9
 */
@AsynCall
public interface Api {

    /**
     * 文件上传
     */
    @Task(url = Resource.API.BASE)
    @POST("interface/RevelationManagementAPI.ashx?action=InserBM")
    @FormUrlEncoded
    Observable<JSONArray> uploadFile(@Field("PicUrl") String PicUrl,
                                     @Field("VideoUrl") String VideoUrl,
                                     @Field("FileDate") String FileDate,
                                     @Field("StID") String StID);


    /**
     * 获取站点信息
     */
    @Task(url = Resource.API.BASE)
    @GET("interface/SiteAPI.ashx?action=GetSiteList")
    Observable<JSONObject> getSiteList(@Query("sid") String sid);


}

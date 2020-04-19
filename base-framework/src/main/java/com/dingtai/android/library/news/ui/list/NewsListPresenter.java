package com.dingtai.android.library.news.ui.list;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONArray;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.model.models.ADModel;
import com.dingtai.android.library.news.api.impl.AddVoteAndResMTMAsynCall;
import com.dingtai.android.library.news.api.impl.GetCompareDataAsynCall;
import com.dingtai.android.library.news.api.impl.GetListAdAsynCall;
import com.dingtai.android.library.news.api.impl.InsertADStatisticsAsynCall;
import com.dingtai.android.library.news.api.impl.LoadGetNewsChildUpListAsynCall;
import com.dingtai.android.library.news.api.impl.RefreshGetNewsChildDownListAsynCall;
import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.call.impl.UpdateModelStatusAsynCall;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;
import com.lnr.android.base.framework.uitl.DeviceUtils;
import com.lnr.android.base.framework.uitl.DimenUtil;
import com.lnr.android.base.framework.uitl.logger.Logger;
import com.lnr.android.base.framework.uitl.net.NetworkUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-08-21
 */
@ActivityScope
public class NewsListPresenter extends AbstractPresenter<NewsListContract.View> implements NewsListContract.Presenter {

    @Inject
    GetCompareDataAsynCall mGetCompareDataAsynCall;
    @Inject
    GetListAdAsynCall mGetListAdAsynCall;
    @Inject
    InsertADStatisticsAsynCall mInsertADStatisticsAsynCall;

    @Inject
    RefreshGetNewsChildDownListAsynCall mRefreshGetNewsChildDownListAsynCall;
    @Inject
    LoadGetNewsChildUpListAsynCall mLoadGetNewsChildUpListAsynCall;

    @Inject
    UpdateModelStatusAsynCall mUpdateModelStatusAsynCall;

    @Inject
    AddVoteAndResMTMAsynCall mAddVoteAndResMTMAsynCall;

    @Inject
    public NewsListPresenter(){}

    //具体业务逻辑

    @Override
    public void getCompareData(String ChID, String sign, String ADType) {
        AsynParams params = AsynParams.create("sign", sign).put("ChID", ChID).put("ADType", ADType);
        excuteNoLoading(mGetCompareDataAsynCall, params, new AsynCallback<JSONArray>() {
            @Override
            public void onCallResponse(JSONArray r) {

            }

            @Override
            public void onCallError(Throwable ex) {
                Logger.log("GetCompareDataAsynCall", ex);
            }
        });
    }

    @Override
    public void getListAd(String Chid, String sign, String ADType, String ADFor, String IsIndexAD) {
        if(TextUtils.isEmpty(ADType)) {
            ADType = "2";
        }
        if(TextUtils.isEmpty(ADFor)) {
            ADFor = "1";
        }
        if(TextUtils.isEmpty(IsIndexAD)) {
            IsIndexAD = "False";
        }
        AsynParams params = AsynParams.create("sign", sign).put("Chid", Chid).put("ADType", ADType)
                .put("ADFor", ADFor).put("IsIndexAD", IsIndexAD);
        excuteNoLoading(mGetListAdAsynCall, params, new AsynCallback<List<ADModel>>() {
            @Override
            public void onCallResponse(List<ADModel> r) {
                view().getListAd(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().getListAd(false, null, null);
            }
        });
    }

    @Override
    public void InsertADStatistics(String OPID, String OPType, String sign) {
        int[] size = DimenUtil.getScreenSize(Framework.getInstance().getApplication());
        AsynParams params = AsynParams.create("sign", sign)
                .put("OPID", OPID)
                .put("OPType", OPType)
                .put("UserGUID", AccountHelper.getInstance().getUserId())
                .put("System", DeviceUtils.getSystemVersion())
                .put("Device", DeviceUtils.getModel())
                .put("Resolution", size[0] + "*" + size[1])
                .put("Network", NetworkUtil.getNetworkType() == NetworkUtil.NetworkType.NETWORK_WIFI ? "1" : "2")
                .put("CarrierOperator", NetworkUtil.getNetworkOperatorName());
        excuteNoLoading(mInsertADStatisticsAsynCall, params, null);
    }



    @Override
    public void updateStatus(String key, int status) {
        excuteNoLoading(mUpdateModelStatusAsynCall, AsynParams.create("key", key).put("status", status), null);
    }

    @Override
    public void refresh(AsynParams params) {
        excuteNoLoading(mRefreshGetNewsChildDownListAsynCall, params, new AsynCallback<List<NewsListModel>>() {
            @Override
            public void onCallResponse(List<NewsListModel> r) {
                view().refresh(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().refresh(false, ex.getMessage(), null);
            }
        });
    }

    @Override
    public void load(AsynParams params) {
        excuteNoLoading(mLoadGetNewsChildUpListAsynCall, params, new AsynCallback<List<NewsListModel>>() {
            @Override
            public void onCallResponse(List<NewsListModel> r) {
                view().load(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().load(false, ex.getMessage(), null);
            }
        });
    }

    @Override
    public void addVoteAndResMTM(final NewsListModel model, int type) {
        excuteWithLoading(mAddVoteAndResMTMAsynCall, AsynParams.create("ResGUID", model.getResourceGUID()).put("type", type), new AsynCallback<Integer>() {
            @Override
            public void onCallResponse(Integer integer) {
                view().addVoteAndResMTM(model, integer);
            }

            @Override
            public void onCallError(Throwable throwable) {
                view().addVoteAndResMTM(model, -1);
            }
        });
    }
}

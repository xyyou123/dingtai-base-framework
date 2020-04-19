package com.dingtai.android.library.news.ui.list;

import com.dingtai.android.library.model.models.ADModel;
import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

import java.util.List;

/**
 * author:lnr
 * date:2018-08-21
 */
public interface NewsListContract {

    interface View extends RecyclerViewConract.View {

        void getListAd(boolean result, String message, List<ADModel> list);

        /**
         * 投标结果
         * @param result 0-成功 1-已经投票 其他-失败
         */
        void addVoteAndResMTM(NewsListModel model, int result);
    }

    interface  Presenter extends IPresenter<View> {

        void getListAd(String ChID, String sign, String ADType, String ADFor, String IsIndexAD);
        void getCompareData(String ChID, String sign, String ADType);

        void InsertADStatistics(String OPID, String OPType, String sign);

        void updateStatus(String key, int value);


        void refresh(AsynParams params);

        void load(AsynParams params);

        /**
         * 投票
         * @param type 左边-1 右边1
         */
        void addVoteAndResMTM(NewsListModel model, int type);
    }
}

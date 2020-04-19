package com.dingtai.android.library.news.ui.list.more;

import com.dingtai.android.library.news.api.impl.GetMoreNewByChannUpListAsynCall;
import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-18
 */
@ActivityScope
public class NewsListMorePresenter extends AbstractPresenter<NewsListMoreContract.View> implements NewsListMoreContract.Presenter {

    @Inject
    protected GetMoreNewByChannUpListAsynCall mGetMoreNewByChannUpListAsynCall;

    @Inject
    public NewsListMorePresenter(){}

    //具体业务逻辑

    @Override
    public void refresh(String action, String CHID, String DeptID, String top) {
        AsynParams params = AsynParams.create("ChID", CHID).put("DeptID", DeptID)
                .put("top", top).put("dtop", "0").put("action", action);
        excuteNoLoading(mGetMoreNewByChannUpListAsynCall, params, new AsynCallback<List<NewsListModel>>() {
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
    public void load(String action, String CHID, String DeptID, String top, String dtop) {
        AsynParams params = AsynParams.create("ChID", CHID).put("DeptID", DeptID)
                .put("top", top).put("dtop", dtop).put("action", action);
        excuteNoLoading(mGetMoreNewByChannUpListAsynCall, params, new AsynCallback<List<NewsListModel>>() {
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
}

package com.dingtai.android.library.news.ui.home.more;

import com.dingtai.android.library.news.api.impl.GetNewsFirstMoreNewsAsynCall;
import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-11-08
 */
@ActivityScope
public class NewsFirstMorePresenter extends AbstractPresenter<RecyclerViewConract.View> implements NewsFirstMoreContract.Presenter {

    @Inject
    GetNewsFirstMoreNewsAsynCall mGetNewsFirstMoreNewsAsynCall;

    @Inject
    public NewsFirstMorePresenter(){}

    @Override
    public void getNewsFirstMoreNews(String type, String top, final String dtop) {
        excuteNoLoading(mGetNewsFirstMoreNewsAsynCall, AsynParams.create("type", type).put("top", top).put("dtop", dtop), new AsynCallback<List<NewsListModel>>() {
            @Override
            public void onCallResponse(List<NewsListModel> list) {
                if("0".equalsIgnoreCase(dtop)) {
                    view().refresh(true, null, list);
                }else {
                    view().load(true, null, list);
                }
            }

            @Override
            public void onCallError(Throwable throwable) {
                if("0".equalsIgnoreCase(dtop)) {
                    view().refresh(false, null, null);
                }else {
                    view().load(false, null, null);
                }
            }
        });
    }
}
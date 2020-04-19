package com.dingtai.android.library.news.ui.image;

import com.dingtai.android.library.news.api.impl.GetImgsByPhotosIdAsynCall;
import com.dingtai.android.library.news.model.NewsImageModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-30
 */
@ActivityScope
public class NewsImagePresenter extends AbstractPresenter<NewsImageContract.View> implements NewsImageContract.Presenter {

    @Inject
    protected GetImgsByPhotosIdAsynCall mGetImgsByPhotosIdAsynCall;

    @Inject
    public NewsImagePresenter(){}

    @Override
    public void GetImgsByPhotosId(String id) {
        excuteNoLoading(mGetImgsByPhotosIdAsynCall, AsynParams.create("PhotosID", id), new AsynCallback<List<NewsImageModel>>() {
            @Override
            public void onCallResponse(List<NewsImageModel> list) {
                view().GetImgsByPhotosId(list);
            }

            @Override
            public void onCallError(Throwable throwable) {
                view().GetImgsByPhotosId(null);
            }
        });
    }
}

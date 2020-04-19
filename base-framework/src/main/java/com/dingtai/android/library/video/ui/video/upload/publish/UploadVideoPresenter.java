package com.dingtai.android.library.video.ui.video.upload.publish;

import com.dingtai.android.library.video.api.impl.GetMediaChannelsListAsynCall;
import com.dingtai.android.library.video.db.VideoChannelModelDao;
import com.dingtai.android.library.video.model.VideoChannelModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapterProvider;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapterType;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-12-04
 */
@ActivityScope
public class UploadVideoPresenter extends AbstractPresenter<UploadVideoContract.View> implements UploadVideoContract.Presenter {

    @Inject
    GetMediaChannelsListAsynCall mGetMediaChannelsListAsynCall;

    @Inject
    public UploadVideoPresenter(){}

    @Override
    public void getMediaChannelsList(String parentId) {
        List<VideoChannelModel> list = AsynCallAdapterProvider.getInstance().get(AsynCallAdapterType.DATABASE)
                .call(VideoChannelModelDao.class)
                .queryBuilder()
                .list();
        if(list == null || list.isEmpty()) {
            excuteNoLoading(mGetMediaChannelsListAsynCall, AsynParams.create("ParentID", parentId), new AsynCallback<List<VideoChannelModel>>() {
                @Override
                public void onCallResponse(List<VideoChannelModel> list) {
                    view().getMediaChannelsList(list);
                }

                @Override
                public void onCallError(Throwable throwable) {
                    view().getMediaChannelsList(null);
                }
            });
        }else {
            view().getMediaChannelsList(list);
        }
    }
}
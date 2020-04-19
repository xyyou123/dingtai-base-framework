package com.dingtai.android.library.video.ui.publish;

import com.dingtai.android.library.video.api.impl.GetPublishImageTextLiveTypeAsynCall;
import com.dingtai.android.library.video.api.impl.InserNewsContentAsynCall;
import com.dingtai.android.library.video.model.PublishLiveTypeModel;
import com.lnr.android.base.framework.common.upload.Uploader;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-11-09
 */
@ActivityScope
public class PublishImageTextLivePresenter extends AbstractPresenter<PublishImageTextLiveContract.View> implements PublishImageTextLiveContract.Presenter {

    @Inject
    GetPublishImageTextLiveTypeAsynCall mGetPublishImageTextLiveTypeAsynCall;

    @Inject
    InserNewsContentAsynCall mInserNewsContentAsynCall;

    protected Uploader mUploader;

    @Inject
    public PublishImageTextLivePresenter(){}

    @Override
    public void getPublishImageTextLiveType() {
        excuteNoLoading(mGetPublishImageTextLiveTypeAsynCall, null, new AsynCallback<List<PublishLiveTypeModel>>() {
            @Override
            public void onCallResponse(List<PublishLiveTypeModel> liveTypeModels) {
                view().getPublishImageTextLiveType(liveTypeModels);
            }

            @Override
            public void onCallError(Throwable throwable) {
                view().getPublishImageTextLiveType(null);
            }
        });
    }

    @Override
    public void publish(String EventID, String LiveType, String Title, String Content, String PicUrl, String VideoUrl, String FileDate, List<String> paths) {
        final AsynParams params = AsynParams.create("EventID", EventID)
                .put("LiveType", LiveType).put("Title", Title)
                .put("Content", Content).put("PicUrl", PicUrl)
                .put("VideoUrl", VideoUrl).put("FileDate", FileDate);

        if(mUploader == null) {
            mUploader = new Uploader();
        }else {
            mUploader.cancel();
        }

        if(paths.isEmpty()) {
            publish(params);
            return;
        }

        mUploader.upload(paths, new Uploader.OnUploadCallback() {
            @Override
            public void onBegin() {
                view().showLoading();
            }

            @Override
            public void onUploading(int index, int progress) {
                view().updateProgress(progress);
            }

            @Override
            public void onSucceed() {
                view().uploadFileSucceed();
                publish(params);
            }

            @Override
            public void onFailed(int code, String message) {
                view().hideLoading();
                view().publish(false, "文件上传失败！");
            }
        });
    }


    private void publish(AsynParams params) {
        excuteWithLoading(mInserNewsContentAsynCall, params, new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean r) {
                view().publish(r, null);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().publish(true, "发布失败！");
            }
        });
    }

    @Override
    public void unBindView() {
        if(mUploader != null) mUploader.release();
        super.unBindView();
    }
}
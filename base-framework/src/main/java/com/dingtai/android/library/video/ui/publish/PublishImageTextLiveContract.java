package com.dingtai.android.library.video.ui.publish;

import com.dingtai.android.library.video.model.PublishLiveTypeModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-11-09
 */
public interface PublishImageTextLiveContract {

    interface View extends IView {
        void getPublishImageTextLiveType(List<PublishLiveTypeModel> list);

        void publish(boolean result, String message);

        void uploadFileSucceed();
    }

    interface  Presenter extends IPresenter<View> {
        void getPublishImageTextLiveType();
        void publish(String EventID, String LiveType, String Title, String Content, String PicUrl, String VideoUrl,
                     String FileDate, List<String> paths);
    }
}
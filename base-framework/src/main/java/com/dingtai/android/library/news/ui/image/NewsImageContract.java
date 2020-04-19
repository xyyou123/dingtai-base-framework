package com.dingtai.android.library.news.ui.image;

import com.dingtai.android.library.news.model.NewsImageModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-09-30
 */
public interface NewsImageContract {

    interface View extends IView {
        void GetImgsByPhotosId(List<NewsImageModel> list);
    }

    interface  Presenter extends IPresenter<View> {

        void GetImgsByPhotosId(String id);
    }
}

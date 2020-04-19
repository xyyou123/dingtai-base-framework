package com.dingtai.android.library.video.ui.video.list.details.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.model.VideoDetailsModel;
import com.dingtai.android.library.video.model.VideoModel;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.fragment.EmptyStatusFragment;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/11/6
 */
@Contract(name = "HotVideoInfo")
@Route(path = "/video/video/list/details/info")
public class VideoInfoFragment extends EmptyStatusFragment implements HotVideoInfoContract.View {

    private TextView textName, textType, textTime, textDesc;

    @Inject
    protected HotVideoInfoPresenter mHotVideoInfoPresenter;

    @Autowired
    protected VideoModel model;

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mHotVideoInfoPresenter);
    }

    @Override
    protected void inject(ApplicationComponent applicationComponent) {
        DaggerVideoDagger.builder().applicationComponent(applicationComponent)
                .asynCallModule(new AsynCallModule(this)).build().inject(this);
    }


    @Override
    protected int contentLayoutResId() {
        return R.layout.fragment_hotvideo_info;
    }

    @Override
    protected void retry() {
        mHotVideoInfoPresenter.getHotVideoDetails(model.getID());
    }

    @Override
    protected void initView(View view, @Nullable Bundle bundle) {
        textName = findViewById(R.id.text_name);
        textType = findViewById(R.id.text_type);
        textTime = findViewById(R.id.text_time);
        textDesc = findViewById(R.id.text_desc);
    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle bundle) {
        mHotVideoInfoPresenter.getHotVideoDetails(model.getID());

    }

    @Override
    public void getHotVideoDetails(VideoDetailsModel model) {
        if(model == null) {
            mStatusLayoutManager.showError();
        }else {
            mStatusLayoutManager.showContent();

            if(TextUtils.isEmpty(model.getName())) {
                textName.setText("暂无");
            }else {
                textName.setText(model.getName());
            }

            if(TextUtils.isEmpty(model.getChannelName())) {
                textType.setText("类型：暂无");
            }else {
                textType.setText("类型：" + model.getChannelName());
            }

            if(TextUtils.isEmpty(model.getUploadDate())) {
                textTime.setText("时间：暂无");
            }else {
                textTime.setText("时间：" + model.getUploadDate());
            }


            if(TextUtils.isEmpty(model.getDetail())) {
                textDesc.setText("剧情简介：暂无");
            }else {
                textDesc.setText("剧情简介：" + model.getDetail());
            }
        }
    }
}

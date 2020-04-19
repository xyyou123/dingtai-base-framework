package com.lnr.android.base.framework.ui.control.web;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.resource.GlobalConfig;
import com.gyf.barlibrary.BarHide;
import com.just.agentwebX5.ChromeClientCallbackManager;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.StatusToolbarActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.web.file.UploadFileWebIntercepotor;
import com.tencent.smtt.sdk.WebView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/9/14
 */
@Route(path = "/framework/web/base/notoolbar")
public class BaseWebActivity extends BaseToolbarWebActivity {

    @Override
    protected void initView() {
        super.initView();
        toolbar().setVisibility(View.GONE);
    }
}

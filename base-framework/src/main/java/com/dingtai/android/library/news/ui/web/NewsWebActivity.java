package com.dingtai.android.library.news.ui.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.umeng.ShareMenu;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.web.BaseToolbarWebActivity;

/**
 * author:lnr
 * date:2018/10/8
 */
@Route(path = "/news/web")
public class NewsWebActivity extends BaseToolbarWebActivity {

    protected ShareMenu menu;

    @Autowired
    protected String logo;

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        super.afterInitView(savedInstanceState);

        toolbar().setRightImage(R.drawable.icon_share);
        toolbar().setRightListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
//                if(menu == null) {
//                    UMWeb web = UMengShare.createWeb(url, title, )
//                    menu = new ShareMenu(mActivity, )
//
//
//                }
            }
        });

    }
}

package androidlibrary;

import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lnr.android.base.framework.ui.control.web.BaseToolbarWebActivity;
import com.lnr.android.base.framework.uitl.AssetsUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tencent.smtt.sdk.WebView;

import library.dingtai.com.androidlibrary.R;

/**
 * author:lnr
 * date:2019/1/2
 */
@Route(path = "/app/scrollweb")
public class ScrollWeb extends BaseToolbarWebActivity {

    private View header;

    @Override
    protected int rootLayoutResId() {
        return super.rootLayoutResId();
    }

    @Override
    protected int contentLayoutResId() {
        return R.layout.activity_scrollwev;
    }

    @Override
    protected void initAgentWebX5() {

        final  String js = AssetsUtil.readAll(this, "web.js");

        SmartRefreshLayout smartRefreshLayout = findViewById(R.id.SmartRefreshLayout);
        smartRefreshLayout.setEnableLoadMore(false);
        header = findViewById(R.id.header);
        NestedScrollView view = findViewById(R.id.NestedScrollView);
        view.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

            private int lastHeight;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                WebView webView = mWebWrapper.getWebView();
                if(webView == null) return;

                int height = v.getHeight() + scrollY + header.getHeight();
                if(height > webView.getHeight() && lastHeight != webView.getHeight()) {
                    mWebWrapper.load(js);
                    lastHeight = webView.getHeight();
                }
            }
        });


        url = "http://dev.hunanzx.gov.cn/syzj/zx.html?userid=81&siteid=9";
        initAgentWebX5((ViewGroup) findViewById(R.id.container), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT), 1);
        mWebWrapper.setAutoResizeHeight(true);

    }


    @Override
    protected void load() {
        super.load();
    }


}

package com.dingtai.android.library.news.ui.details.web;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.model.models.ScoreModel;
import com.dingtai.android.library.news.DaggerNewsDagger;

import com.dingtai.android.library.news.model.NewsCommentModel;
import com.dingtai.android.library.news.model.NewsDetailModel;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.dingtai.android.library.news.ui.details.base.BaseNewsActivity;
import com.dingtai.android.library.news.ui.details.comment.NewsCommentAdapter;
import com.dingtai.android.library.news.ui.details.component.CommentComponent;
import com.dingtai.android.library.news.ui.details.component.RelevantReaderComponent;
import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.resource.Routes;
import com.dingtai.android.library.resource.Score;
import com.github.lnr.inject.annotation.Contract;
import com.gyf.barlibrary.BarHide;
import com.just.agentwebX5.AgentWebX5Compat;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.look.ImageLook;
import com.lnr.android.base.framework.common.umeng.ShareMenu;
import com.lnr.android.base.framework.common.umeng.UmengAction;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.view.Toolbar;
import com.lnr.android.base.framework.ui.control.web.AbstractWebViewInterceptor;
import com.lnr.android.base.framework.ui.control.web.ShareComponent;
import com.lnr.android.base.framework.ui.control.web.WebCallback;
import com.lnr.android.base.framework.ui.control.web.WebWrapper;
import com.lnr.android.base.framework.ui.control.web.file.UploadFileWebIntercepotor;
import com.lnr.android.base.framework.uitl.AssetsUtil;
import com.lnr.android.base.framework.uitl.MaiDianUtils;
import com.tencent.smtt.sdk.WebView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

@Route(path = "/news/details")
@Contract(name = "NewsDetails")
public class NewsDetailsActivity extends BaseNewsActivity implements NewsDetailsContract.View, WebCallback {

    @Inject
    protected NewsDetailsPresenter mNewsDetailsPresenter;

    protected LinearLayout mWebLayout;

    protected Toolbar mToolbar;

    protected WebWrapper mWebWrapper;
    /**
     * 新闻详情u
     */
    protected NewsDetailModel mNewsDetailModel;

    protected RelevantReaderComponent mRelevantReaderComponent;
    protected CommentComponent mCommentComponent;
    protected ShareComponent shareComponent;

    @Override
    protected void retry() {
        mNewsDetailsPresenter.getNewsInfo(model.getResourceGUID(), Resource.API.SIGN);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        List<IPresenter> iPresenters = super.getIPresenters();
        iPresenters.add(mNewsDetailsPresenter);
        return iPresenters;
    }


    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component)
                .asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_news_web;
    }


    @Override
    protected boolean isWhiteTheme() {
        return true;
    }

    @Override
    protected int rootLayoutResId() {
        return R.layout.root_layout_toolbar;
    }

    @Override
    protected void initView() {
        super.initView();

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setLeftImage(Resource.Drawable.TOOLBAR_BACK);
        mToolbar.setLeftListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                finish();
            }
        });
        mToolbar.setRightImage(R.drawable.icon_share);
        mToolbar.setRightListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                share();
            }
        });

        mWebLayout = findViewById(R.id.layout_container);
        mWebLayout.setVisibility(View.GONE);

        //添加分享组件


        mRelevantReaderComponent = new RelevantReaderComponent(mActivity);
        mRelevantReaderComponent.init();
        mRelevantReaderComponent.setVisibility(View.GONE);


        mCommentComponent = new CommentComponent(mActivity);
        mCommentComponent.init(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (!AccountHelper.getInstance().isLogin()) {
                    navigation(Routes.Account.LOGIN).navigation();
                    return;
                }
                NewsCommentModel item = (NewsCommentModel) adapter.getItem(position);
                if (item == null) return;
                if (view.getId() == R.id.item_zan_image) {
                    mBaseNewsPresenter.addCommentZan(null, item);
                } else if (view.getId() == R.id.item_edit) {
                    String name = TextUtils.isEmpty(item.getUserNickName()) ? item.getUserName() : item.getUserNickName();
                    if (TextUtils.isEmpty(name)) {
                        name = "匿名用户";
                    }
                    showCommentDialog(item.getID(), "回复 " + name);
                } else if (view.getId() == R.id.item_sublist_hint) {
                    item.setExpandAllSubList(!item.isExpandAllSubList());
                    adapter.notifyItemChanged(position);
                }
            }
        }, new NewsCommentAdapter.OnSubChildZanClickListener() {
            @Override
            public void onSubChildZanClick(NewsCommentModel parent, NewsCommentModel model) {
                if (!AccountHelper.getInstance().isLogin()) {
                    navigation(Routes.Account.LOGIN).navigation();
                    return;
                }
                mBaseNewsPresenter.addCommentZan(parent, model);
            }
        });

        mCommentComponent.setVisibility(View.GONE);
        shareComponent = new ShareComponent(mActivity).init(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onShareActionClick((UmengAction) adapter.getItem(position));
            }
        });
        shareComponent.setVisibility(View.GONE);
        layoutComponents();

        initWebView();
    }

    protected void layoutComponents() {


        addComponent(shareComponent);

        addComponent(mRelevantReaderComponent);

        addComponent(mCommentComponent);
    }

    @Override
    protected void share() {
        if (mShareMenu != null && !mShareMenu.isShowing())
            mShareMenu.show(new ShareMenu.OnShareListener() {
                @Override
                public void onShare(SHARE_MEDIA share_media) {
                    mBaseNewsPresenter.addShareNum(model.getResourceGUID());
                    if (mNewsDetailModel != null) {
                        MaiDianUtils.forward(mNewsDetailModel.getResourceGUID());
                    }
                }
            });
            
    }

    protected void initWebView() {
        mWebWrapper = WebWrapper.with(this);
        mWebWrapper.init((ViewGroup) findViewById(R.id.layout_web),
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT), this);
        mWebWrapper.setAutoResizeHeight(true);
        mWebWrapper.addInterceptor(new UploadFileWebIntercepotor(this, new UploadFileWebIntercepotor.Callback() {
            @Override
            public void requestPermission(Consumer<Boolean> consumer, String... permissions) {
                NewsDetailsActivity.this.requestPermission(permissions).request(consumer);
            }

            @Override
            public LifecycleTransformer bindLife() {
                return bindToLifecycle();
            }
        }));

        mWebWrapper.addInterceptor(new AbstractWebViewInterceptor(null) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {

                if (!TextUtils.isEmpty(s)) {
                    if (s.endsWith(".mp4") ||s.endsWith(".pdf") ||s.endsWith(".zip") ||s.endsWith(".rar") ||s.endsWith(".doc") ||s.endsWith(".docx") ||s.endsWith(".xls")  ) {
                        Uri uri = Uri.parse(s);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    } else {
                        ARouter.getInstance().build(Routes.Modules.WEB)
                                .withString("url", s).navigation();
                    }

                    return true;
                }
                return false;
            }

            @Override
            public void onActivityResult(int i, int i1, Intent intent) {

            }
        });
    }

    protected void localInterceptRequest(String[] requst) {
        if (requst.length != 2) return;
        String[] data = requst[1].split(",");
        if ("NewsDetail".equals(requst[0]) && data.length == 2) {
            if (model.getResourceGUID().equals(data[1])) return;
            NewsListModel model = new NewsListModel();
            model.setResourceType(data[0]);
            model.setResourceGUID(data[1]);
            NewsNavigation.details(model);
        }
    }

    protected void loadData(String data) {

        mWebWrapper.load(data, "text/html; charset=UTF-8", null);
        mWebWrapper.getAgentWebX5().getWebLifeCycle().onResume();
        mWebWrapper.addJs("web_request", new AgentWebX5Compat() {
            @JavascriptInterface
            public void request(final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NewsDetailsActivity.this.localInterceptRequest(data.split("@"));
                    }
                });
            }
        });

        mWebWrapper.addJs("look", new AgentWebX5Compat() {
            @JavascriptInterface
            public void lookImage(final String current, final String[] list) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageLook.look(current, Arrays.asList(list), mNewsDetailModel.getTitle());
                    }
                });
            }
        });

        mToolbar.addRightImage(R.drawable.icon_change_text_size, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                mWebWrapper.showResizeTextSizeZoomDialog(mActivity);
            }
        });

        mStatusLayoutManager.showContent();
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {

        mNewsDetailsPresenter.getNewsInfo(model.getResourceGUID(), Resource.API.SIGN);
        mBaseNewsPresenter.getNewsCommentList(model.getResourceGUID(), forapp, String.valueOf(Resource.API.PAGE), "0");
    }

    /**
     * 添加组件
     *
     * @param view 组件视图
     */
    public void addComponent(View view) {
        mWebLayout.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void getNewsInfo(boolean result, String message, NewsDetailModel model) {
        if (result) {
            mToolbar.setTitle(model.getTitle());
            mNewsDetailModel = model;
            MaiDianUtils.comeIn(mNewsDetailModel.getResourceGUID());
            String content = model.getContent();
            if (content == null) {
                mStatusLayoutManager.showError();
                return;
            }

            if (!content.toLowerCase().startsWith("http")) {
                content = formatHtml(content);
            }

            loadData(content);
            this.model.setCommentNum(mNewsDetailModel.getCommentNum());
            this.model.setGetGoodPoint(mNewsDetailModel.getNewsGetGoodPoint());
            initActionbar(model.getCommentNum(), this.model.getGetGoodPoint());

            if (mNewsDetailModel.getRelatedNews() != null) {
                mRelevantReaderComponent.setNewsData(mNewsDetailModel.getRelatedNews());
            } else {
                //mRelevantReaderComponent.setNewsData(mNewsDetailModel.getRelatedNewLists());
            }

            RxBus.getDefault().post(new ScoreModel(Score.SCORE_LOOK_NEWS));
        } else {
            mStatusLayoutManager.showError();
        }
    }

    protected String formatHtml(String src) {
        String title = AssetsUtil.readAll(this, "news.html");
        return title.replace("#{title}", mNewsDetailModel.getTitle())
                .replace("#{time}", mNewsDetailModel.getUpdateTime())
                .replace("#{content}", src.replace(AssetsUtil.readAll(this, "html_delete"), ""));
    }

    @Override
    public void getNewsCommentList(boolean result, boolean refresh, List<NewsCommentModel> list) {
        if (list != null) {
            mCommentComponent.setNewsData(list);
        }
    }

    @Override
    public void addCommentZan(boolean result, NewsCommentModel parent, NewsCommentModel comment) {
        super.addCommentZan(result, parent, comment);
        if (mCommentComponent != null) {
            NewsCommentAdapter adapter = mCommentComponent.getAdapter();
            if (adapter != null) {
                adapter.notifyData(parent, comment);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebWrapper != null && mWebWrapper.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        if (mWebWrapper != null) mWebWrapper.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebWrapper != null) mWebWrapper.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mImmersionBar.reset().hideBar(BarHide.FLAG_HIDE_BAR).init();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mImmersionBar.reset().fitsSystemWindows(true).statusBarColor(com.lnr.android.base.framework.R.color.statusbar)
                    .statusBarDarkFont(GlobalConfig.STATUSBAR_TEXT_DART)
                    .flymeOSStatusBarFontColor(GlobalConfig.STATUSBAR_TEXT_COLOR)
                    .init();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mWebWrapper != null) mWebWrapper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        if (mWebWrapper != null) mWebWrapper.onDestroy();
        if (mNewsDetailModel != null) {
            MaiDianUtils.leave(mNewsDetailModel.getResourceGUID());
        }
        super.onDestroy();
    }

    @Override
    public void onReceivedIcon(WebView webView, Bitmap bitmap) {

    }

    @Override
    public void onResizeHeight(float v) {
        if (mWebLayout.getVisibility() == View.GONE) {
            mWebLayout.setVisibility(View.VISIBLE);

            mToolbar.requestFocus();
            mToolbar.postDelayed(new Runnable() {
                @Override
                public void run() {
                    shareComponent.setVisibility(View.VISIBLE);
                    mRelevantReaderComponent.setVisibility(View.VISIBLE);
                    mCommentComponent.setVisibility(View.VISIBLE);
                }
            }, 10);
            mToolbar.postDelayed(new Runnable() {
                @Override
                public void run() {
                    NestedScrollView view = findViewById(R.id.NestedScrollView);
                    if (view != null) view.scrollTo(0, 0);
                }
            }, 20);
        }
    }

    @Override
    public void onReceivedTitle(WebView webView, String s) {

    }

    @Override
    public void addNewsZan(boolean result) {
        if (result && mNewsDetailModel != null) {
            MaiDianUtils.praise(mNewsDetailModel.getResourceGUID());
        }
        super.addNewsZan(result);
    }

    @Override
    public void addNewsCollect(boolean result) {
        super.addNewsCollect(result);
    }
}

package com.dingtai.android.library.news.ui.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.models.ScoreModel;
import com.dingtai.android.library.news.DaggerNewsDagger;

import com.dingtai.android.library.news.model.NewsImageModel;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.details.base.BaseNewsActivity;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.resource.Score;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.common.umeng.ShareMenu;
import com.lnr.android.base.framework.common.umeng.UmengAction;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.data.asyn.http.retrofit.progress.ProgressListener;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.RingProgressBar;
import com.lnr.android.base.framework.ui.control.view.Toolbar;
import com.lnr.android.base.framework.ui.control.view.viewpager.BaseViewPagerAdapter;

import java.text.MessageFormat;
import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/30
 */
@Route(path = "/news/image")
@Contract(name = "NewsImage")
public class NewsImageActivity extends BaseNewsActivity implements NewsImageContract.View {

    @Inject
    protected NewsImagePresenter mNewsImagePresenter;

    @Autowired
    protected NewsListModel model;

    private List<NewsImageModel> imageModelList;

    protected Toolbar mToolbar;
    protected ViewPager mViewPager;

    protected View mBottoLayout;
    protected TextView textCount, textContent;

    protected RequestOptions options = new RequestOptions()
            .error(GlideHelper.error)
            .diskCacheStrategy(DiskCacheStrategy.ALL);

    @Override
    protected int layoutId() {
        return R.layout.activity_news_image;
    }

    @Override
    protected boolean isWhiteTheme() {
        return false;
    }

    @Override
    protected ShareMenu createShareMenu() {
        return new ShareMenu(mActivity, new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onShareActionClick((UmengAction) adapter.getItem(position));
            }
        });
    }

    @Override
    protected int rootLayoutResId() {
        return R.layout.root_layout_toolbar;
    }

    @Override
    protected int contentLayoutResId() {
        return R.layout.layout_image_container;
    }

    @Override
    protected void retry() {
        mNewsImagePresenter.GetImgsByPhotosId(model.getRPID());
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        List<IPresenter> iPresenters = super.getIPresenters();
        iPresenters.add(mNewsImagePresenter);
        return iPresenters;
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void initView() {
        super.initView();

        mViewPager = findViewById(R.id.image_pager);
        mBottoLayout = findViewById(R.id.layout_bottom);
        textCount = findViewById(R.id.text_count);
        textContent = findViewById(R.id.text_content);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(model.getTitle());
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
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        mNewsImagePresenter.GetImgsByPhotosId(model.getRPID());
        RxBus.getDefault().post(new ScoreModel(Score.SCORE_LOOK_PICTURE));
    }

    @Override
    public void GetImgsByPhotosId(List<NewsImageModel> list) {
        if(list == null) {
            mStatusLayoutManager.showError();
            return;
        }
        mStatusLayoutManager.showContent();
        imageModelList = list;
        mViewPager.setAdapter(new BaseViewPagerAdapter<NewsImageModel>(imageModelList) {
            @Override
            protected View createView(ViewGroup viewGroup, int i, NewsImageModel newsImageModel) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_look_image, viewGroup, false);
                ViewListen.setClick(view.findViewById(R.id.PhotoView), new OnClickListener() {
                    @Override
                    protected void onSafeClick(View v) {
                        switchBottom();
                    }
                });
                return view;
            }

            protected void convert(final View view, int position, final NewsImageModel s) {
                GlideHelper.loadUseProgress((ImageView) view.findViewById(R.id.PhotoView), s.getPicturePath(), options, new ProgressListener() {
                    @Override
                    public void onProgress(final int progress) {
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                RingProgressBar bar = view.findViewById(com.lnr.android.base.framework.R.id.RingProgressBar);
                                if(bar == null) return;
                                bar.setProgress(progress);

                                if(progress >= 100) {
                                    bar.setVisibility(View.GONE);
                                }else if(bar.getVisibility() != View.VISIBLE) {
                                    bar.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }
                });
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (imageModelList.size() > 0) {
                   textContent.setText(imageModelList.get(position).getPhotoDescription());
                }

                textCount.setText(MessageFormat.format("{0}/{1}", position + 1, imageModelList.size()));
            }
        });
        if (this.imageModelList.size() > 0) {
            this.textContent.setText(imageModelList.get(0).getPhotoDescription());
            textContent.setVisibility(View.VISIBLE);
        }else {
            textContent.setVisibility(View.GONE);
        }

        this.textCount.setText(MessageFormat.format("{0}/{1}", 1, this.imageModelList.size()));
        this.mViewPager.setCurrentItem(0);
    }

    protected void switchBottom() {
        if(imageModelList == null) {
            return;
        }
        if(mBottoLayout.getVisibility() == View.VISIBLE) {
            mBottoLayout.setVisibility(View.GONE);
        }else {
            mBottoLayout.setVisibility(View.VISIBLE);
        }
    }
}

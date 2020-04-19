package com.dingtai.android.library.news.ui.subject.neo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.news.DaggerNewsDagger;

import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.model.SubjectNeoModel;
import com.dingtai.android.library.news.model.SubjectNeoRootModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.dingtai.android.library.news.ui.list.more.NewsListMoreActivity;
import com.dingtai.android.library.resource.GlobalConfig;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.common.umeng.ShareMenu;
import com.lnr.android.base.framework.common.umeng.UMengShare;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.rx.event.AddReadingHistoryEvent;
import com.lnr.android.base.framework.ui.base.avtivity.StatusToolbarActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.FlowLayout;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.uitl.ContextUtil;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/14
 * 专题列表  -- 新专题
 */
@Contract(name = "SubjectNeoList")
@Route(path = "/news/subject/neo/list")
public class SubjectNeoListActivity extends StatusToolbarActivity implements SubjectNeoListContract.View {

    @Inject
    protected SubjectNeoListPresenter mSubjectNeoListPresenter;

    private SubjectNeoAdapter mSubjectNeoAdapter;

    @Autowired
    protected String ChID;

    @Autowired
    protected String GUID;

    @Autowired
    protected String title;

    protected ImageView imagePic;
    protected TextView textTitle;
    protected TextView textContent;

    protected FlowLayout flowLayout;
    protected RecyclerView recyclerView;

    protected ShareMenu shareMenu;


    protected SubjectNeoRootModel model;
    protected List<SubjectNeoSectionEntity> sectionEntities = new ArrayList<>();

    @Override
    protected int contentLayoutResId() {
        return R.layout.activity_subject_neo_list;
    }

    @Override
    protected void retry() {
        mSubjectNeoListPresenter.GetChannAndNews(ChID);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mSubjectNeoListPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void initView() {
        super.initView();

        toolbar().setRightImage(R.drawable.icon_share);

        if (!TextUtils.isEmpty(GlobalConfig.SHARE_URL_SUJECT_OLD.replace(GlobalConfig.SHARE_URL, ""))) {
            toolbar().setRightListener(new OnClickListener() {
                @Override
                protected void onSafeClick(View v) {
                    if (model == null) return;
                    if (shareMenu == null) {
                        shareMenu = createShareMenu(model);
                    }
                    if (!shareMenu.isShowing()) shareMenu.show();
                }
            });
        }

        recyclerView = findViewById(R.id.RecyclerView);
        mSubjectNeoAdapter = new SubjectNeoAdapter();

        recyclerView.setLayoutManager(new AdvertiseLinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(mSubjectNeoAdapter);

        View headLayout = View.inflate(this, R.layout.layout_header_subject_neo_list, null);
        imagePic = headLayout.findViewById(R.id.image_pic);
        textTitle = headLayout.findViewById(R.id.text_title);
        textContent = headLayout.findViewById(R.id.text_content);
        flowLayout = headLayout.findViewById(R.id.FlowLayout);
        flowLayout.setAutoSetOnclickListener(false);
        mSubjectNeoAdapter.addHeaderView(headLayout);

        mSubjectNeoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SubjectNeoSectionEntity item = mSubjectNeoAdapter.getItem(position);
                if (item == null || item.isHeader) {
                    return;
                }
                NewsNavigation.subjectItemNavigation(item.t);
            }
        });

        mSubjectNeoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() != R.id.item_more) return;
                SubjectNeoSectionEntity item = mSubjectNeoAdapter.getItem(position);
                if (item == null || !item.isHeader) return;
                NewsNavigation.newsListMore(NewsListMoreActivity.ACTION_SUBJECT, item.getId(), null, item.getTitle());
            }
        });
    }

    protected ShareMenu createShareMenu(SubjectNeoRootModel model) {
        UMImage image = TextUtils.isEmpty(model.getLogo())
                ? new UMImage(mActivity, GlobalConfig.SHARE_ICON)
                : new UMImage(mActivity, model.getLogo());

        UMWeb web = UMengShare.createWeb(GlobalConfig.SHARE_URL_SUJECT_OLD + ChID, model.getTitle(), null, image);
        return new ShareMenu(mActivity, web);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        toolbar().setTitle(title);
        mSubjectNeoListPresenter.GetChannAndNews(ChID);
        RxBus.getDefault().post(new AddReadingHistoryEvent(GUID));
    }

    @Override
    public void GetChannAndNews(boolean result, String message, SubjectNeoRootModel model) {
        if (result) {
            this.model = model;
            mStatusLayoutManager.showContent();

            GlideHelper.load(imagePic, model.getLogo());
            textTitle.setText(model.getTitle());
            textContent.setText(model.getReMark());
            if (TextUtils.isEmpty(model.getReMark())) {
                textContent.setVisibility(View.GONE);
            } else {
                textContent.setVisibility(View.VISIBLE);
            }

            toolbar().setTitle(model.getTitle());

            sectionEntities = new ArrayList<>();
            flowLayout.removeAllViews();
            List<SubjectNeoModel> topics = model.getTopics();
            int count = topics.size();
            for (int i = 0; i < count; i++) {
                SubjectNeoModel subject = topics.get(i);

                SubjectNeoSectionEntity entity = new SubjectNeoSectionEntity(i, count, subject.getTopicsID(), subject.getTopicsName());
                flowLayout.addView(createFlowItem(sectionEntities.size(), entity));
                sectionEntities.add(entity);
                for (NewsListModel news : subject.getTopicsNews()) {
                    sectionEntities.add(new SubjectNeoSectionEntity(news));
                }
            }
            mSubjectNeoAdapter.setNewData(sectionEntities);
        } else if (this.model == null) {
            mStatusLayoutManager.showError();
        }
    }

    protected View createFlowItem(final int position, final SubjectNeoSectionEntity entity) {
        TextView textView = (TextView) View.inflate(this, R.layout.view_subject_neo_flow_item, null);
        textView.setText(entity.getTitle());
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = ContextUtil.getDimen(R.dimen.dp_4);
        params.setMargins(margin, margin, margin, margin);
        textView.setLayoutParams(params);
        ViewListen.setClick(textView, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                recyclerView.smoothScrollToPosition(position + 1);
            }
        });
        return textView;
    }
}

package com.dingtai.android.library.news.ui.details.component;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.model.RelatedReaderModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.dingtai.android.library.news.ui.list.adapter.convertor.NewsItemConvertor;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.FixImageView;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/14
 * 相关阅读
 */
public class RelevantReaderComponent extends LinearLayout {

    protected RecyclerView recyclerView;
    protected boolean vertical = true;
    private Adapter mNewsListAdapter;

    public RelevantReaderComponent(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setBackgroundResource(com.lnr.android.base.framework.R.color.white);
    }

    public RelevantReaderComponent init() {
        Context context = getContext();
        int p = getResources().getDimensionPixelOffset(com.lnr.android.base.framework.R.dimen.dp_8);
        View view = new View(context);
        view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        view.setBackgroundResource(com.lnr.android.base.framework.R.color.line);
        view.setPadding(0, p, 0, p);
        addView(view);


        TextView textView = new TextView(context);
        textView.setTextSize(16);
        textView.setTextColor(getResources().getColor(R.color.textcolor_Body2));
        textView.setText("相关阅读");
        textView.setPadding(p, p, p, 0);
        addView(textView);

        recyclerView = new RecyclerView(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManagerWrapper(getContext()));
        recyclerView.setPadding(p, 0, p, p);

        mNewsListAdapter = new Adapter();
        mNewsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RelatedReaderModel model = (RelatedReaderModel) adapter.getItem(position);
                if(model == null) return;

                NewsListModel listModel = new NewsListModel();
                listModel.setResourceType(model.getRelatedResourceType());
                listModel.setResourceGUID(model.getRelatedResourceGUID());
                listModel.setRPID(model.getRelatedRPID());
                listModel.setIsNewTopice(model.getRelatedIsNewTopice());
                listModel.setChID(model.getRelatedChID());
                listModel.setTitle(model.getRelatedTitle());
                listModel.setSmallPicUrl(model.getRelatedSmallPicUrl());
                NewsNavigation.listItemNavigation(listModel);
            }
        });
        recyclerView.setAdapter(mNewsListAdapter);
        addView(recyclerView);

        setVisibility(GONE);
        return this;
    }

    public void setNewsData(List<RelatedReaderModel> data) {
        mNewsListAdapter.setNewData(data);

        if(data != null && !data.isEmpty()) {
            setVisibility(VISIBLE);
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setLayoutManager(boolean vertical) {
        this.vertical = vertical;
        if(vertical) {
            recyclerView.setLayoutManager(new LinearLayoutManagerWrapper(getContext()));
        }else {
            recyclerView.setLayoutManager(new LinearLayoutManagerWrapper(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
    }

    private class Adapter extends BaseAdapter<RelatedReaderModel> {

        @Override
        protected ItemConverter<RelatedReaderModel> createItemConverter(int i) {
            return new ItemConverter<RelatedReaderModel>() {
                @Override
                public int layoutId() {
                    return vertical ? R.layout.item_news_list_relevannt_reader : R.layout.item_news_list_relevannt_reader_horizontal;
                }

                @Override
                public void convert(BaseViewHolder baseViewHolder, int i, RelatedReaderModel model) {
                    FixImageView imageView = baseViewHolder.getView(R.id.item_image);
                    if(NewsItemConvertor.ROUND_IMAGE > 0) {
                        GlideHelper.loadRound(imageView, model.getRelatedSmallPicUrl(), NewsItemConvertor.ROUND_IMAGE);
                    }else {
                        GlideHelper.load(baseViewHolder.getView(R.id.item_image), model.getRelatedSmallPicUrl());
                    }
                    baseViewHolder.setText(R.id.item_title, model.getRelatedTitle());
                }
            };
        }
    }
}

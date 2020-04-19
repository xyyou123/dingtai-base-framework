package com.dingtai.android.library.news.ui.details.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.dingtai.android.library.news.model.NewsCommentModel;
import com.dingtai.android.library.news.ui.details.comment.NewsCommentAdapter;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/14
 * 热门评论
 */
public class CommentComponent extends LinearLayout {

    private NewsCommentAdapter mNewsCommentAdapter;

    public CommentComponent(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setBackgroundResource(R.color.white);
    }

    public CommentComponent init(BaseQuickAdapter.OnItemChildClickListener childClickListener, NewsCommentAdapter.OnSubChildZanClickListener listener) {
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
        textView.setText("热门评论");
        textView.setPadding(p, p, p, 0);
        addView(textView);

        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(context));
        recyclerView.setLayoutManager(new LinearLayoutManagerWrapper(getContext()));
        recyclerView.setPadding(p, 0, p, p);

        if(mNewsCommentAdapter == null) {
            mNewsCommentAdapter = new NewsCommentAdapter(listener);
        }

        mNewsCommentAdapter.setOnItemChildClickListener(childClickListener);
        recyclerView.setAdapter(mNewsCommentAdapter);
        addView(recyclerView);
        return this;
    }

    public void setNewsData(List<NewsCommentModel> data) {
        mNewsCommentAdapter.setNewData(data);
    }

    public NewsCommentAdapter getAdapter() {
        return mNewsCommentAdapter;
    }
}

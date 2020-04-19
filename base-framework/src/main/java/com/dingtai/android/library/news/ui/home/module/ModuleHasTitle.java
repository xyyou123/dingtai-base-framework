package com.dingtai.android.library.news.ui.home.module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;

import java.util.List;

public class ModuleHasTitle extends LinearLayout {

    protected View mTitleLayout;
    protected ImageView mIcon;
    protected TextView mTitle;

    protected RecyclerView mRecyclerView;

    public ModuleHasTitle(Context context) {
        this(context, true);
    }

    public ModuleHasTitle(Context context, boolean topmargin) {
        super(context);
        init(context, topmargin);
    }

    protected void init(Context context, boolean topmargin) {
        setOrientation(VERTICAL);
        setBackgroundResource(R.color.white);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = getResources().getDimensionPixelOffset(R.dimen.dp_4);
        params.setMargins(0, topmargin ? margin : 0, 0, margin);
        setLayoutParams(params);
        inflate(context, R.layout.layout_module_has_title, this);

        mTitleLayout = findViewById(R.id.module_title_layout);
        mIcon = findViewById(R.id.module_icon);
        mTitle = findViewById(R.id.module_title);

        mRecyclerView = findViewById(R.id.RecyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    public void setTitle(Object iconPath, String title, com.lnr.android.base.framework.ui.control.listener.OnClickListener listener) {
        GlideHelper.load(mIcon, iconPath);
        mTitle.setText(title);
        ViewListen.setClick(mTitleLayout, listener);
    }

    public void setData(RecyclerView.LayoutManager manager, BaseAdapter adapter) {
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
    }

    public void setNewData(List list) {
        BaseAdapter adapter = (BaseAdapter) mRecyclerView.getAdapter();
        adapter.setNewData(list);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public TextView getTitle() {
        return mTitle;
    }
}

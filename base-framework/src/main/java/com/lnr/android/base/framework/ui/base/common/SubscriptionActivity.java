package com.lnr.android.base.framework.ui.base.common;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarActivity;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseDraggableAdapter;

/**
 * author:lnr
 * date:2018/9/27
 * 栏目订阅
 */
public abstract class SubscriptionActivity<T> extends ToolbarActivity {

    protected RecyclerView recyclerViewFixed;
    protected RecyclerView recyclerViewMore;

    protected BaseDraggableAdapter<T> fixedAdapter;
    protected BaseAdapter<T> moreAdapter;

    @Override
    protected View contentView() {
        return View.inflate(this, R.layout.activity_subscription, null);
    }

    @Override
    protected void initView() {
        toolbar().setTitle("订阅");

        recyclerViewFixed = findViewById(R.id.RecyclerView_fixed);
        recyclerViewMore = findViewById(R.id.RecyclerView_more);

        recyclerViewFixed.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerViewMore.setLayoutManager(new GridLayoutManager(this, 4));

        fixedAdapter = fixedAdapter();
        moreAdapter = moreAdapter();

        recyclerViewFixed.setAdapter(fixedAdapter);
        recyclerViewMore.setAdapter(moreAdapter);

        BaseItemDraggableAdapter adapter = fixedAdapter;
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = dragAndSwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewFixed);

        fixedAdapter.enableDragItem(itemTouchHelper, toggleViewId(), true);
    }

    protected abstract BaseDraggableAdapter<T> fixedAdapter();
    protected abstract BaseAdapter<T> moreAdapter();
    protected abstract ItemDragAndSwipeCallback dragAndSwipeCallback(BaseItemDraggableAdapter adapter);
    protected abstract int toggleViewId();


}

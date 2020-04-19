package com.lnr.android.base.framework.ui.control.web;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.umeng.UmengAction;
import com.lnr.android.base.framework.common.umeng.UmengData;
import com.lnr.android.base.framework.data.models.ShareAction;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

import java.util.List;

public class ShareComponent extends LinearLayout {

    public ShareComponent(Context context) {
       this(context, null);
    }

    public ShareComponent(Context context, List<ShareAction> shareMediaList) {
        super(context);
        setOrientation(VERTICAL);
        setBackgroundResource(R.color.white);
    }

    public ShareComponent init(BaseQuickAdapter.OnItemClickListener listener) {
        Context context = getContext();
        int p = getResources().getDimensionPixelOffset(R.dimen.dp_8);
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        view.setBackgroundResource(R.color.line);
        view.setPadding(0, p, 0, p);
        addView(view);


        TextView textView = new TextView(context);
        textView.setTextSize(16);
        textView.setTextColor(getResources().getColor(R.color.textcolor_Body2));
        textView.setText("分享到");
        textView.setPadding(p, p, p, 0);
        addView(textView);

        RecyclerView recyclerView = new RecyclerView(context);
        List<UmengAction> shareList = UmengData.getShareList();
        recyclerView.setLayoutManager(new GridLayoutManager(context, Math.min(5, shareList.size())));
        recyclerView.setPadding(p, 0, p, p);
        Adapter adapter = new Adapter();
        adapter.setNewData(shareList);
        adapter.setOnItemClickListener(listener);
        recyclerView.setAdapter(adapter);
        addView(recyclerView);

        return this;
    }

    final static class Adapter extends BaseAdapter<UmengAction> {

        @Override
        protected ItemConverter<UmengAction> createItemConverter(int viewType) {
            return new ItemConverter<UmengAction>() {
                @Override
                public int layoutId() {
                    return R.layout.item_share;
                }

                @Override
                public void convert(BaseViewHolder holder, int position, UmengAction action) {
                    ((ImageView)holder.getView(R.id.action_image)).setImageResource(action.getRes());
                    holder.setText(R.id.action_title, action.getName());
                }
            };
        }
    }

}

package com.dingtai.android.library.video.ui.live.tab.programme;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/9/4
 */
public class LiveWeekAdapter extends BaseAdapter<String> {

    private int currentSelectedIndex;

    public void selecte(int index) {
        if(index < 0 || index > getItemCount() - 1) {
            return;
        }

        currentSelectedIndex = index;
        notifyDataSetChanged();
    }

    public int getSelectedIndex() {
        return currentSelectedIndex;
    }

    public String getSelectedItem() {
        if(currentSelectedIndex < 0 || currentSelectedIndex >= getItemCount()) {
            return null;
        }
        return getItem(currentSelectedIndex);
    }

    @Override
    protected ItemConverter<String> createItemConverter(int viewType) {
        return new ItemConverter<String>() {

            @Override
            public int layoutId() {
                return R.layout.item_live_week;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, String s) {
                TextView textView = holder.getView(R.id.item_title);
                textView.setText(s);
                textView.setSelected(currentSelectedIndex == position);
                holder.getView(R.id.item_arrow).setVisibility(currentSelectedIndex == position ? View.VISIBLE : View.INVISIBLE);

                holder.itemView.setSelected(currentSelectedIndex == position);
            }
        };
    }
}

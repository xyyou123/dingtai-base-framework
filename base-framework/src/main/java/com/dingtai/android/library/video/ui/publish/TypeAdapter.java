package com.dingtai.android.library.video.ui.publish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dingtai.android.library.video.model.PublishLiveTypeModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.view.adapterview.BaseAdapterViewAdapter;

/**
 * author:lnr
 * date:2018/11/9
 */
public class TypeAdapter extends BaseAdapterViewAdapter<PublishLiveTypeModel> {

    @Override
    protected View createView(ViewGroup viewGroup, Context context, int i) {
        return LayoutInflater.from(context).inflate(R.layout.item_publish_type, viewGroup, false);
    }

    @Override
    protected void convert(ViewHolder viewHolder, int i, PublishLiveTypeModel publishLiveTypeModel) {
        viewHolder.setText(R.id.item_title, publishLiveTypeModel.getEventName());
    }
}

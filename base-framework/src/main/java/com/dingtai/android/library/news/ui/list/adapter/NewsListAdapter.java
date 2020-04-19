package com.dingtai.android.library.news.ui.list.adapter;

import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.list.adapter.convertor.NewsItemConvertor;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

public class NewsListAdapter extends BaseAdapter<NewsListModel> {

    @Override
    protected int getDefItemViewType(int position) {
        NewsListModel item = getItem(position);
        if(item == null) return super.getDefItemViewType(position);

        return NewsItemConvertor.converterType(item);
    }

    @Override
    protected ItemConverter<NewsListModel> createItemConverter(int viewType) {
        return NewsItemConvertor.converterItem(viewType);
    }
}

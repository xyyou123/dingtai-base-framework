package com.dingtai.android.library.news.ui.subject.neo;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.list.adapter.convertor.NewsItemConvertor;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseSectionAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/9/17
 */
public class SubjectNeoAdapter extends BaseSectionAdapter<SubjectNeoSectionEntity> {

    @Override
    protected ItemConverter<SubjectNeoSectionEntity> createHeadConverter() {
        return new ItemConverter<SubjectNeoSectionEntity>() {
            @Override
            public int layoutId() {
                return R.layout.item_subject_neo_header;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, SubjectNeoSectionEntity entity) {
                holder.setText(R.id.item_title, entity.header);

                holder.addOnClickListener(R.id.item_more);
            }
        };
    }

    @Override
    protected ItemConverter<SubjectNeoSectionEntity> createItemConverter(int viewType) {
        return new SubjectNeoItemConverter(viewType);
    }


    @Override
    public int getViewType(int position) {
        SubjectNeoSectionEntity item = getItem(position);
        if(item == null || item.isHeader) {
            return super.getItemViewType(position);
        }
        return NewsItemConvertor.converterType(item.t);
    }

    protected static final class SubjectNeoItemConverter implements ItemConverter<SubjectNeoSectionEntity> {

        protected ItemConverter<NewsListModel> itemConverter;

        public SubjectNeoItemConverter(int viewType) {
            itemConverter = NewsItemConvertor.converterItem(viewType);
        }

        @Override
        public int layoutId() {
            return itemConverter.layoutId();
        }

        @Override
        public void convert(BaseViewHolder holder, int position, SubjectNeoSectionEntity entity) {
            itemConverter.convert(holder, position, entity.t);
        }
    }
}

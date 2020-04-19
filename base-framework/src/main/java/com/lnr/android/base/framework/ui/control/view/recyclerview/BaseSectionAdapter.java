package com.lnr.android.base.framework.ui.control.view.recyclerview;

import android.util.SparseArray;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * author :  lnr
 * date : 2018/8/20.
 */
public abstract class BaseSectionAdapter<T extends SectionEntity> extends BaseSectionQuickAdapter<T, BaseViewHolder> {

    private ItemConverter<T> headConverter;

    private SparseArray<ItemConverter<T>> itemConverterSparseArray;

    public BaseSectionAdapter() {
        super(0, 0, null);
        itemConverterSparseArray = new SparseArray<>();
    }

    @Override
    protected final int getDefItemViewType(int position) {
        return mData.get(position).isHeader ? SECTION_HEADER_VIEW : getViewType(position);
    }

    @Override
    public final int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    protected int getViewType(int position) {
        return 0;
    }

    protected abstract ItemConverter<T> createHeadConverter();

    protected abstract ItemConverter<T> createItemConverter(int viewType);

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SECTION_HEADER_VIEW) {
            if(headConverter == null) {
                headConverter = createHeadConverter();
            }
            return createBaseViewHolder(getItemView(headConverter.layoutId(), parent));
        }

        ItemConverter<T> converter = itemConverterSparseArray.get(viewType);
        if(converter == null) {
            converter = createItemConverter(viewType);
            itemConverterSparseArray.put(viewType, converter);
        }

        return createBaseViewHolder(getItemView(converter.layoutId(), parent));
    }

    @Override
    protected void convertHead(BaseViewHolder helper, T item) {
        headConverter.convert(helper, helper.getAdapterPosition() - getHeaderLayoutCount(), item);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        itemConverterSparseArray.get(helper.getItemViewType()).convert(helper, helper.getAdapterPosition() - getHeaderLayoutCount(), item);
    }
}

package com.lnr.android.base.framework.ui.control.view.recyclerview;

import android.util.SparseArray;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * author:lnr
 * date:2018/9/27
 */
public abstract class BaseDraggableAdapter<T> extends BaseItemDraggableAdapter<T, BaseViewHolder> {

    private SparseArray<ItemConverter<T>> itemConverterSparseArray;

    public BaseDraggableAdapter() {
        super(null);
        itemConverterSparseArray = new SparseArray<>();
    }

    @Override
    public final int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected int getDefItemViewType(int position) {
        return 0;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        ItemConverter<T> itemConverter = itemConverterSparseArray.get(viewType);
        if(itemConverter == null) {
            itemConverter = createItemConverter(viewType);
            itemConverterSparseArray.put(viewType, itemConverter);
        }
        return createBaseViewHolder(getItemView(itemConverter.layoutId(), parent));
    }

    protected abstract ItemConverter<T> createItemConverter(int viewType);

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        itemConverterSparseArray.get(helper.getItemViewType()).convert(helper, helper.getAdapterPosition() - getHeaderLayoutCount(), item);
    }
}

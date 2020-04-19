package com.lnr.android.base.framework.ui.control.view.recyclerview;

import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/17
 */
public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private SparseArray<ItemConverter<T>> itemConverterSparseArray;

    public BaseAdapter() {
        super(null);
        itemConverterSparseArray = new SparseArray<>();
    }

    @Override
    public int getItemViewType(int position) {
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

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
        if(payloads.isEmpty()) {
            onBindViewHolder(holder, position);
            return;
        }

        int viewType = holder.getItemViewType();

        switch (viewType) {
            case LOADING_VIEW:
            case HEADER_VIEW:
            case EMPTY_VIEW:
            case FOOTER_VIEW:
                onBindViewHolder(holder, position);
                break;
            default:
                convert(holder, getItem(position - getHeaderLayoutCount()), payloads.get(0));
                break;
        }

        super.onBindViewHolder(holder, position, payloads);
    }

    protected abstract ItemConverter<T> createItemConverter(int viewType);

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        itemConverterSparseArray.get(helper.getItemViewType()).convert(helper, helper.getAdapterPosition() - getHeaderLayoutCount(), item);
    }

    protected void convert(BaseViewHolder helper, T item, Object payload) {
        ItemConverter<T> itemConverter = itemConverterSparseArray.get(helper.getItemViewType());
        if(itemConverter instanceof IDiffUpdate) {
            ((IDiffUpdate) itemConverter).update(helper, helper.getAdapterPosition() - getHeaderLayoutCount(), item, payload);
        }else {
            itemConverter.convert(helper, helper.getAdapterPosition() - getHeaderLayoutCount(), item);
        }
    }

    @Override
    public int expand(int position) {
        return super.expand(position);
    }
}

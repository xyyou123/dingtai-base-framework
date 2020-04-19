package com.lnr.android.base.framework.common.umeng;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.dialog.AbstractBottomDialog;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/25
 */
public class ShareMenu extends AbstractBottomDialog {

    private BaseQuickAdapter.OnItemClickListener onItemClickListener;

    private Activity activity;

    private UMWeb umWeb;

    private OnShareListener mOnShareListener;

    private List<SHARE_MEDIA> mShareMedias;

    public ShareMenu(@NonNull Activity context, BaseQuickAdapter.OnItemClickListener onItemClickListener) {
        super(context);
        this.onItemClickListener = onItemClickListener;
        this.activity = context;
    }

    public ShareMenu(@NonNull Activity context, UMWeb umWeb) {
        super(context);
        this.umWeb = umWeb;
        this.activity = context;
    }

    public ShareMenu(@NonNull Activity context, UMWeb umWeb, List<SHARE_MEDIA> list) {
        super(context);
        this.umWeb = umWeb;
        this.activity = context;
        this.mShareMedias = list;
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_share_menu;
    }

//    @Override
//    protected int[] getDialogSize(DisplayMetrics displayMetrics) {
//        int margin = getContext().getResources().getDimensionPixelOffset(R.dimen.dp_8);
//        displayMetrics.widthPixels -= margin * 2;
//        return super.getDialogSize(displayMetrics);
//    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);

        final UmengActionAdapter actionAdapter = new UmengActionAdapter();
        actionAdapter.setNewData(UmengData.getShareList(mShareMedias));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), Math.min(actionAdapter.getItemCount(), 3)));
        actionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dismiss();
                UmengAction item = actionAdapter.getItem(position);
                if(item == null) return;
                if(umWeb != null) {
                    UMengShare.shareWeb(activity, item.getType(), umWeb);
                }else {
                    onItemClickListener.onItemClick(adapter, view, position);
                }

                if(mOnShareListener != null) {
                    mOnShareListener.onShare(item.getType());
                }
            }
        });
        recyclerView.setAdapter(actionAdapter);

        ViewListen.setClick(findViewById(R.id.btn_cancel), new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                dismiss();
            }
        });
    }

    public void show(OnShareListener listener) {
        this.mOnShareListener = listener;
        show();
    }

    public interface OnShareListener {
        void onShare(SHARE_MEDIA type);
    }
}

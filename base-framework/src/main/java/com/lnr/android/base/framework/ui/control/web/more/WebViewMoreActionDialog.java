package com.lnr.android.base.framework.ui.control.web.more;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.dialog.AbstractBottomDialog;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.text.MessageFormat;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class WebViewMoreActionDialog extends AbstractBottomDialog {

    /**
     * 分享
     */
    private MoreActionAdapter shareAdapter;
    /**
     * 操作
     */
    private MoreActionAdapter operationAdapter;

    private OnActionClickListener onActionClickListener;

    private String url;
    private TextView tvUrl;

    public WebViewMoreActionDialog(Context context, @NonNull OnActionClickListener onActionClickListener) {
        super(context);
        this.onActionClickListener = onActionClickListener;
        init(context);
    }

    public interface OnActionClickListener {
        void onActionClick(MoreAction action);
    }

    private void init(Context context) {
        shareAdapter = new MoreActionAdapter();
        shareAdapter.addData(new MoreAction(SHARE_MEDIA.WEIXIN, "分享到微信", R.drawable.icon_share_weixin));
        shareAdapter.addData(new MoreAction(SHARE_MEDIA.WEIXIN_CIRCLE, "分享到朋友圈", R.drawable.icon_share_pengyouquan));
        shareAdapter.addData(new MoreAction(SHARE_MEDIA.QQ, "分享到QQ", R.drawable.icon_share_qq));
        shareAdapter.addData(new MoreAction(SHARE_MEDIA.QZONE, "分享到QQ空间", R.drawable.icon_share_qqzone));
        shareAdapter.addData(new MoreAction(SHARE_MEDIA.SINA, "分享到微博", R.drawable.icon_share_weibo));

        operationAdapter = new MoreActionAdapter();
        //operationAdapter.add(new MoreAction(MoreAction.ACTION_OPEN_IN_SYSTEM, "在浏览器中打开", R.drawable.icon_browser));
        //operationAdapter.add(new MoreAction(MoreAction.ACTION_COPY_LINK, "复制链接", R.drawable.icon_copy_link));
        operationAdapter.addData(new MoreAction(MoreAction.ACTION_REFRESH, "刷新", R.drawable.icon_refresh));
        operationAdapter.addData(new MoreAction(MoreAction.ACTION_CHANGE_TEXT_SIZE, "调整字体", R.drawable.icon_change_text_size));
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_web_more_action;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.more_action_share);
        recyclerView.setLayoutManager(new LinearLayoutManagerWrapper(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(shareAdapter);
        shareAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.more_action_image) {
                    dismiss();
                    onActionClickListener.onActionClick(shareAdapter.getItem(position));
                }
            }
        });

        recyclerView = view.findViewById(R.id.more_action_operation);
        recyclerView.setLayoutManager(new LinearLayoutManagerWrapper(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(operationAdapter);
        operationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.more_action_image) {
                    dismiss();
                    onActionClickListener.onActionClick(shareAdapter.getItem(position));
                }
            }
        });

        ViewListen.setClick(view.findViewById(R.id.more_action_cancel), new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                dismiss();
            }
        });

        tvUrl = view.findViewById(R.id.more_action_title);
        tvUrl.setText(url);
        if(url == null) {
            tvUrl.setVisibility(View.GONE);
        }else {
            tvUrl.setVisibility(View.VISIBLE);
        }
    }


    public void show(String content) {
        if(content == null) {
            url = null;
            if(tvUrl != null) {
                tvUrl.setVisibility(View.INVISIBLE);
            }
        }else {
            int start = Math.max(0, content.indexOf("://")) + 3;
            int end = Math.max(0, content.indexOf("/", start));
            if(start <= 0 || end <= start) {
                return;
            }
            this.url = MessageFormat.format("此网页由{0}提供", content.substring(start, end));
            if(tvUrl != null) {
                tvUrl.setText(url);
                tvUrl.setVisibility(View.VISIBLE);
            }
        }
        show();
    }
}

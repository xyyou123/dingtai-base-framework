package com.dingtai.android.library.video.ui.live.tab.chat;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.Collections;
import java.util.List;

/**
 * author:lnr
 * date:2018/9/3
 * 直播聊天页面
 */
@Route(path = "/video/live/chat/desc")
public class LiveChatDescFragment extends LiveChatFragment {

    protected Handler mHandler = new Handler();

    protected long mAutoLoadDelayed = 20000L;

    @Override
    protected void refresh(int i) {
        //下拉改为加载更多
        mLiveChatPresenter.GetNewsComment(this.type, this.liveId, String.valueOf(i), "" + mAdapter.getItemCount(), this.tabCode);
    }


    @Override
    protected void loadMore(int i, int i1) {
        //上拉改为刷新
        mLiveChatPresenter.GetNewsComment(this.type, this.liveId, String.valueOf(i), "0", this.tabCode);
    }

    @Override
    public void refresh(boolean result, String message, List list) {
        mSmartRefreshLayout.finishLoadMore();
        if (result) {
            if (list != null && !list.isEmpty()) {
                list.removeAll(mAdapter.getData());
                Collections.reverse(list);
                mStatusLayoutManager.showContent();
                //追加到末尾
                mAdapter.addData(list);
                mSmartRefreshLayout.setEnableLoadMore(true);
                scrollToBottom();
            } else {
                mSmartRefreshLayout.setEnableLoadMore(false);
                mStatusLayoutManager.showEmpty();
            }
        } else if (mAdapter.getItemCount() == 0) {
            mStatusLayoutManager.showEmpty();
        }

        if(mAdapter.getItemCount() > 0) {
            mHandler.removeCallbacks(mAutoLoadRunnable);
            mHandler.postDelayed(mAutoLoadRunnable, mAutoLoadDelayed);
        }
    }

    protected Runnable mAutoLoadRunnable = new Runnable() {
        @Override
        public void run() {
            if(mSmartRefreshLayout != null) {
                mSmartRefreshLayout.autoLoadMore();
            }
        }
    };

    @Override
    public void load(boolean result, String message, List list) {
        mSmartRefreshLayout.finishRefresh();

        if (result) {
            if (list != null && !list.isEmpty()) {
                list.removeAll(mAdapter.getData());
                Collections.reverse(list);
                mAdapter.addData(0, list);
            }
        }
    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        mSmartRefreshLayout.setEnableAutoLoadMore(false);
        refresh(page());
    }

    protected void scrollToBottom() {
        mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    public void onStop() {
        if(mHandler != null) {
            mHandler.removeCallbacks(mAutoLoadRunnable);
            mHandler.removeCallbacksAndMessages(null);
        }
        super.onStop();
    }
}

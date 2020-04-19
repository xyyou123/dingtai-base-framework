package com.dingtai.android.library.video.ui.live.tab.programme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.VideoComponentConstant;
import com.dingtai.android.library.video.event.Event;
import com.dingtai.android.library.video.model.LiveProgramModel;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.ui.base.fragment.AbstractRecyclerViewFragment;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.lnr.android.base.framework.uitl.date.Time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/3
 * 直播节目单页面
 */
@Contract(name = "LiveProgramme")
@Route(path = "/video/live/programme")
public class LiveProgrammeFragment extends AbstractRecyclerViewFragment implements LiveProgrammeContract.View {

    /**
     * 直播类型
     * @see VideoComponentConstant.LiveType
     */
    @Autowired
    protected int type = 1;

    /**
     * 直播id
     */
    @Autowired
    protected String liveId;

    @Autowired
    protected String tabCode;

    @Inject
    LiveProgrammePresenter mLiveProgrammePresenter;

    protected List<String> weekday;

    protected RecyclerView mWeekRecyclerView;

    protected LiveWeekAdapter mWeekAdapter;

    protected List<String> mWeeks;

    protected HashMap<String, LiveProgrammeAdapter> mProgramAdapterMap;

    protected View mCurrentLiveLayout;
    protected View mCurrentLiveLine;

    @Override
    protected int rootLayoutResId() {
        return R.layout.fragment_live_programme;
    }

    @Override
    protected void retry() {
        mLiveProgrammePresenter.GetProgramList(liveId, mWeeks.get(mWeekAdapter.getSelectedIndex()), tabCode);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mLiveProgrammePresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);

        mCurrentLiveLayout = findViewById(R.id.live_programme_current);
        TextView textView = findViewById(R.id.item_title);
        textView.setTextColor(getResources().getColor(R.color.theme));
        textView.setText("正在直播");
        textView = findViewById(R.id.item_replay);
        textView.setText("回到直播");


        mCurrentLiveLine = findViewById(R.id.live_programme_current_line);

        ViewListen.setClick(mCurrentLiveLayout, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                mCurrentLiveLayout.setVisibility(View.GONE);
                mCurrentLiveLine.setVisibility(View.GONE);
                RxBus.getDefault().post(new Event.ChangeCurrentLiveProgramme(null));
            }
        });

        mWeekRecyclerView = findViewById(R.id.week_RecyclerView);
        mWeekRecyclerView.setLayoutManager(new LinearLayoutManagerWrapper(getContext()));

        weekday = new ArrayList<>();
        mWeeks = new ArrayList<>();
        mProgramAdapterMap = new HashMap<>();

        long today = Time.today();
        String week;
        long time;
        for (int i = 0; i < 7; i++) {
            time = today - Time.Day * i;
            if(i == 0) {
                week = "今天";
            }else if(i == 1) {
                week = "昨天";
            }else {
                week = Time.weekday(time);
            }
            weekday.add(week);
            mWeeks.add(String.valueOf(Time.dayOfWeek(time) - 1));
        }

        LiveProgrammeAdapter adapter = new LiveProgrammeAdapter(true);
        mProgramAdapterMap.put(mWeeks.get(0), adapter);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        adapter.setOnItemClickListener(onItemClickListener);

        mWeekAdapter = new LiveWeekAdapter();
        mWeekAdapter.setNewData(weekday);
        mWeekRecyclerView.setAdapter(mWeekAdapter);

        mWeekAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mWeekAdapter.selecte(position);
                String week = mWeeks.get(position);
                LiveProgrammeAdapter liveProgrammeAdapter = mProgramAdapterMap.get(week);
                if(liveProgrammeAdapter == null) {
                    liveProgrammeAdapter = new LiveProgrammeAdapter();
                    liveProgrammeAdapter.setOnItemClickListener(onItemClickListener);
                    mProgramAdapterMap.put(week, liveProgrammeAdapter);
                    mStatusLayoutManager.showLoading();
                    retry();
                }else if(liveProgrammeAdapter.getItemCount() == 0){
                    mStatusLayoutManager.showEmpty();
                }else {
                    mStatusLayoutManager.showContent();
                }
                mRecyclerView.setAdapter(liveProgrammeAdapter);
            }
        });
    }


    private BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            LiveProgrammeAdapter adapt = (LiveProgrammeAdapter) adapter;
            if(!adapt.isCanPlaying(position)) {
                return;
            }

            if(mCurrentLiveLayout.getVisibility() != View.VISIBLE) {
                mCurrentLiveLayout.setVisibility(View.VISIBLE);
                mCurrentLiveLine.setVisibility(View.VISIBLE);
            }
            RxBus.getDefault().post(new Event.ChangeCurrentLiveProgramme(adapt.getItem(position)));

            String current = mWeeks.get(mWeekAdapter.getSelectedIndex());

            for (Map.Entry<String, LiveProgrammeAdapter> entry : mProgramAdapterMap.entrySet()) {
                if(entry.getKey().equals(current)) {
                    entry.getValue().updatePlayingStatus(position);
                }else {
                    entry.getValue().updatePlayingStatus(-1);
                }
            }
        }
    };

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        mSmartRefreshLayout.setEnableRefresh(false);
        mSmartRefreshLayout.setEnableLoadMore(false);

        retry();
    }


    @Override
    public void GetProgramList(boolean result, String message, String week, List<LiveProgramModel> list) {
        LiveProgrammeAdapter liveProgrammeAdapter = mProgramAdapterMap.get(week);
        if(liveProgrammeAdapter != null) {
            handlerRefreshResult(result, liveProgrammeAdapter, list, 1000);
        }

        mSmartRefreshLayout.setEnableLoadMore(false);
    }
}

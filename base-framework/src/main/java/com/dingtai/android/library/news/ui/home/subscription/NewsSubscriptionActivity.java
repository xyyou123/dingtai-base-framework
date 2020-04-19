package com.dingtai.android.library.news.ui.home.subscription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.dingtai.android.library.database.DB;

import com.dingtai.android.library.news.db.ChannelModelDao;
import com.dingtai.android.library.news.event.SubscriptionUpdateEvent;
import com.dingtai.android.library.news.model.ChannelModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.ui.base.common.SubscriptionActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseDraggableAdapter;
import com.lnr.android.base.framework.uitl.SP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author:lnr
 * date:2018/9/27
 */
@Route(path = "/news/home/subscription")
public class NewsSubscriptionActivity extends SubscriptionActivity<ChannelModel> {

    @Autowired
    protected ArrayList<ChannelModel> list;

    @Autowired
    protected String parentID;

    @Override
    protected BaseDraggableAdapter<ChannelModel> fixedAdapter() {
        return new NewsSubscriptionFixedAdapter();
    }

    @Override
    protected BaseAdapter<ChannelModel> moreAdapter() {
        return new NewsSubscriptionMoreAdapter();
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected ItemDragAndSwipeCallback dragAndSwipeCallback(BaseItemDraggableAdapter adapter) {
        return new ItemDragAndSwipeCallback(adapter) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
                return target.getItemViewType() == 0;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if(viewHolder.getItemViewType() == 0) return super.getMovementFlags(recyclerView, viewHolder);
                return makeMovementFlags(0, 0);
            }
        };
    }

    @Override
    protected int toggleViewId() {
        return R.id.item;
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        toolbar().setRightText("完成");
        toolbar().setRightListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                if(list == null) return;
                String sort = "";
                for (ChannelModel model : list) {
                    sort += model.getID() + "@";
                }
                if(sort.endsWith("@")) {
                    sort = sort.substring(0, sort.length() - 1);
                }

                SP.getDefault().edit().putString("NewsSubscription_sort_" + parentID, sort).apply();
                RxBus.getDefault().post(new SubscriptionUpdateEvent(list));
                finish();
            }
        });

        ArrayList<ChannelModel> temp = list;
        if(temp == null || temp.isEmpty()) {
            ChannelModelDao dao = (ChannelModelDao) DB.getInstance().getConmon().getDao(ChannelModelDao.class);
            temp = (ArrayList<ChannelModel>) dao.loadAll();
        }

        String sort = SP.getDefault().getString("NewsSubscription_sort_" + parentID, null);
        if(sort == null) {
            list = temp;
        }else {
            ChannelModel m = new ChannelModel();
            list = new ArrayList<>();
            String[] strings = sort.split("@");
            for (String id : strings) {
                m.setID(id);
                int i = temp.indexOf(m);
                if(i >= 0) {
                    list.add(temp.get(i));
                }
            }
        }

        if(list != null) {
            Iterator<ChannelModel> iterator = list.iterator();
            while (iterator.hasNext()) {
                ChannelModel next = iterator.next();
                String id = next.getID();
                if(TextUtils.isEmpty(id) || "0".equals(id)) {
                    iterator.remove();
                }
            }
        }

        fixedAdapter.setNewData(list);
    }
}

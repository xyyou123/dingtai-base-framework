package com.dingtai.android.library.video.ui.video.upload.my;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.model.VideoModel;
import com.dingtai.android.library.video.ui.VideoNavigation;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.common.DefaultToolbarRecyclerviewActivity;
import com.lnr.android.base.framework.ui.control.dialog.MessageDialogHelper;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/12/4
 * 我上传的视频
 */
@Contract(name = "MyUploadVideoList")
@Route(path = "/video/video/upload/my")
public class MyUploadVideoListActivity extends DefaultToolbarRecyclerviewActivity implements MyUploadVideoListContract.View {

    @Inject
    protected MyUploadVideoListPresenter mMyUploadVideoListPresenter;

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mMyUploadVideoListPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected int page() {
        return 10000;
    }

    @Override
    protected void refresh(int page) {
        mMyUploadVideoListPresenter.getMyUploadVideoList();
    }

    @Override
    protected void loadMore(int page, int current) {

    }

    @Override
    protected void initView() {
        super.initView();
        toolbar().setTitle("我的视频");

        toolbar().setRightText("上传");
        toolbar().setRightListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                VideoNavigation.videoUploadPublish();
            }
        });
    }

    @Override
    protected BaseAdapter adapter() {
        return new MyUploadVideoAdapter();
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return new DividerItemDecoration(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemChildClick(adapter, view, position);

        final VideoModel item = (VideoModel) adapter.getItem(position);
        if(item == null) return;

        if(view.getId() == R.id.item_layout_content) {
            VideoNavigation.videoListDetails(item);
        }else if(view.getId() == R.id.item_delete) {
            MessageDialogHelper.showHasCancel(this, "确定要删除该视频吗？", "删除", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMyUploadVideoListPresenter.delete(item);
                }
            });
        }
    }

    @Override
    public void delete(boolean result, VideoModel model) {
        if(result) {
            ToastHelper.toastSucceed("已删除");
            remove(model);
        }else {
            ToastHelper.toastError("删除失败");
        }
    }
}

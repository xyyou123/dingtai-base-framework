package com.lnr.android.base.framework.common.map;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.fragment.EmptyStatusFragment;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/9/18
 * di
 */
public abstract class MapFragment extends EmptyStatusFragment {

    protected MapView mapView;

    protected BaiduMap baiduMap;

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected int contentLayoutResId() {
        return R.layout.layout_frame;
    }

    @Override
    protected void retry() {
        initMap();
    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        initMap();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mapView != null) mapView.onResume();
    }

    @Override
    public void onPause() {
        if(mapView != null) mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if(mapView != null) mapView.onDestroy();
        super.onDestroy();
    }

    protected void initMap() {
        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .request(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(aBoolean) {
                            mStatusLayoutManager.showContent();

                            FrameLayout frameLayout = findViewById(R.id.frame);
                            if (frameLayout.getChildCount() <= 0) {
                                mapView = new MapView(getContext());
                                mapView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                                baiduMap = mapView.getMap();
                                frameLayout.addView(mapView);
                                afterInitMap();
                            }
                        }else {
                            mStatusLayoutManager.showEmpty("没有定位权限，无法使用地图功能。");
                        }
                    }
                });
    }

    protected abstract void afterInitMap();
}

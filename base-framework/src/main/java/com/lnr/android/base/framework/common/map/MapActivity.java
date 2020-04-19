package com.lnr.android.base.framework.common.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarActivity;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/18
 * di
 */
public abstract class MapActivity extends ToolbarActivity {

    protected MapView mapView;

    protected BaiduMap baiduMap;

    @Override
    protected View contentView() {
        mapView = new MapView(this);
        mapView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return mapView;
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void initView() {
        baiduMap = mapView.getMap();
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mapView != null) mapView.onResume();
    }

    @Override
    protected void onPause() {
        if(mapView != null) mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(mapView != null) mapView.onDestroy();
        super.onDestroy();
    }
}

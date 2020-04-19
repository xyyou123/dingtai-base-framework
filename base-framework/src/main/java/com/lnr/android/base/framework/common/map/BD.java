package com.lnr.android.base.framework.common.map;

import android.content.Context;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/9/18
 */
public class BD {

    public static BDLocation current;

    private LocationClient locationClient;

    private static List<BDAbstractLocationListener> listenerList = new ArrayList<>();

    private static boolean isLocating;

    private PoiSearch poiSearch;

    private BDAbstractLocationListener bdAbstractLocationListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            current = bdLocation;
            for (BDAbstractLocationListener listener : listenerList) {
                listener.onReceiveLocation(bdLocation);
            }
            listenerList.clear();
            isLocating = false;
        }
    };

    public void release() {
        if(locationClient != null && bdAbstractLocationListener != null) {
            locationClient.unRegisterLocationListener(bdAbstractLocationListener);
            stopLocate();
        }
        locationClient = null;
        if(poiSearch != null) {
            poiSearch.destroy();
        }
        poiSearch = null;
    }

    public void locate(Context context, BDAbstractLocationListener locationListener) {
        if(current != null && locationListener != null) {
            locationListener.onReceiveLocation(current);
            return;
        }
        listenerList.add(locationListener);

        if(isLocating) {
            return;
        }
        if(locationClient == null) locationClient = new LocationClient(context);
        LocationClientOption option = new LocationClientOption();
        if (!option.isOpenGps()) {
            option.setOpenGps(true);//开启gps
        }
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setIsNeedAddress(true);
        option.setScanSpan(1000);
        locationClient.setLocOption(option);
        locationClient.registerLocationListener(bdAbstractLocationListener);
        locationClient.start();
    }

    public void stopLocate() {
        if(locationClient != null) {
            locationClient.stop();
        }
    }

    public void searchNearby(final PoiNearbySearchOption option, final OnGetPoiSearchResultListener listener) {
        if(poiSearch == null) poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(listener);
        poiSearch.searchNearby(option);
    }

    public static class SimpleOnGetPoiSearchResultListener implements OnGetPoiSearchResultListener {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {

        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    }
}

package com.lnr.android.base.framework.uitl.net;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * author:lnr
 * date:2018/10/8
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {

    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
        NetworkUtil.updateState();
    }

    @Override
    public void onLosing(Network network, int maxMsToLive) {
        super.onLosing(network, maxMsToLive);
        NetworkUtil.updateState();
    }

    @Override
    public void onLost(Network network) {
        super.onLost(network);
        NetworkUtil.updateState();
    }

    @Override
    public void onUnavailable() {
        super.onUnavailable();
        NetworkUtil.updateState();
    }

    @Override
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        NetworkUtil.updateState();
    }

    @Override
    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        super.onLinkPropertiesChanged(network, linkProperties);
        NetworkUtil.updateState();
    }
}

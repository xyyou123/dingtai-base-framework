package com.lnr.android.base.framework.uitl;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.model.models.AccountModel;
import com.lnr.android.base.framework.Framework;
import com.shuwen.analytics.SHWAnalytics;
import com.shuwen.analytics.SHWAnalyticsConfig;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by Administrator on 2019/2/25 0025.
 */

public class MaiDianUtils {
   //机构ID 区分Manifest 中的设置ID
    public static String MECHANISM_ID;

    public static String obtainNetIP() {
        try {
            for (Enumeration<NetworkInterface> enNetI = NetworkInterface
                    .getNetworkInterfaces(); enNetI.hasMoreElements(); ) {
                NetworkInterface netI = enNetI.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = netI
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String getMetaDataFromAppication(String key) {
        try {
            ApplicationInfo appInfo = Framework.getInstance().getApplication().getPackageManager().getApplicationInfo(Framework.getInstance().getApplication().getPackageName(),
                    PackageManager.GET_META_DATA);
            return appInfo.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  进入新闻详情
     * @param newsID
     */
    public static void comeIn( String newsID) {
        ArrayMap<String, String> properties = obtainDefaultProperties( newsID);
// 其它未标识必需的字段，在可以获取的情况下尽量获取，如果确实不能获取，可以传递为空

// 立即上报事件，如果要检验上报效果建议使用这种方式
        SHWAnalytics.recordEvent("comeIn", properties, true);

// 延迟上报事件，需要过一段时间才会进行集中上报，流量使用较少
// SHWAnalytics.recordEvent("comeIn", properties);

    }

    /**
     *  点赞
     * @param newsID
     */
    public static void praise(String newsID) {
        ArrayMap<String, String> properties = obtainDefaultProperties(newsID);
// 其它未标识必需的字段，在可以获取的情况下尽量获取，如果确实不能获取，可以传递为空

// 立即上报事件，如果要检验上报效果建议使用这种方式
        SHWAnalytics.recordEvent("praise", properties, true);

// 延迟上报事件，需要过一段时间才会进行集中上报，流量使用较少
// SHWAnalytics.recordEvent("praise", properties);

    }

    /**
     *  转发
     * @param newsID
     */
    public static void forward( String newsID) {
        ArrayMap<String, String> properties = obtainDefaultProperties( newsID);
// 其它未标识必需的字段，在可以获取的情况下尽量获取，如果确实不能获取，可以传递为空

// 立即上报事件，如果要检验上报效果建议使用这种方式
        SHWAnalytics.recordEvent("forward", properties, true);

// 延迟上报事件，需要过一段时间才会进行集中上报，流量使用较少
// SHWAnalytics.recordEvent("forward", properties);

    }

    /**
     *  离开新闻详情页面
     * @param newsID
     */
    public static void leave( String newsID) {
        ArrayMap<String, String> properties = obtainDefaultProperties( newsID);
// 其它未标识必需的字段，在可以获取的情况下尽量获取，如果确实不能获取，可以传递为空

// 立即上报事件，如果要检验上报效果建议使用这种方式
        SHWAnalytics.recordEvent("leave", properties, true);

// 延迟上报事件，需要过一段时间才会进行集中上报，流量使用较少
// SHWAnalytics.recordEvent("leave", properties);

    }

    /**
     * 评论
     *
     * @param newsID
     * @param comment
     */
    public static void comment(String newsID, String comment) {
        ArrayMap<String, String> properties = obtainDefaultProperties( newsID);
// 其它未标识必需的字段，在可以获取的情况下尽量获取，如果确实不能获取，可以传递为空
        properties.put("targetURL", ""); // 如果评论内容有单独的url，则填写该url，否则使用稿件url
        properties.put("comment", comment);  // 该字段必需
// 其它未标识必需的字段，在可以获取的情况下尽量获取，如果确实不能获取，可以传递为空

// 立即上报事件，如果要检验上报效果建议使用这种方式
        SHWAnalytics.recordEvent("comment", properties, true);

// 延迟上报事件，需要过一段时间才会进行集中上报，流量使用较少
// SHWAnalytics.recordEvent("comment", properties);

    }

    /**
     *  播放视频
     * @param newsID
     * @param videoUrl
     */
    public static void playVideo(String newsID, String videoUrl) {
        ArrayMap<String, String> properties = obtainDefaultProperties(newsID);
        properties.put("videoUrl", videoUrl); // 该字段必需
// 其它未标识必需的字段，在可以获取的情况下尽量获取，如果确实不能获取，可以传递为空

// 立即上报事件，如果要检验上报效果建议使用这种方式
        SHWAnalytics.recordEvent("playVideo", properties, true);

// 延迟上报事件，需要过一段时间才会进行集中上报，流量使用较少
// SHWAnalytics.recordEvent("playVideo", properties);

    }

    /**
     *  基本参数
     * @param newsID
     * @return
     */
    @NonNull
    private static ArrayMap<String, String> obtainDefaultProperties(String newsID) {
        ArrayMap<String, String> properties = new ArrayMap<>();
        AccountModel user = AccountHelper.getInstance().getUser();
        properties.put("userid", user == null ? "" : user.getUserGUID());  // 如用户已登录，该字段为必需
        properties.put("sex", user == null ? "" : wrapUserSex(user.getUserSex()));
        properties.put("profession", "");
        properties.put("age", "");
        properties.put("ip", obtainNetIP());  // 该字段必需
        properties.put("targetID", newsID);  // 该字段必需
        properties.put("url", "");
        properties.put("organization", MECHANISM_ID); // 该字段必需
        properties.put("applicationID", TextUtils.isEmpty(getMetaDataFromAppication("SHWANALYTICS_APPKEY")) ? "" : getMetaDataFromAppication("SHWANALYTICS_APPKEY")); // 该字段必需
// 其它未标识必需的字段，在可以获取的情况下尽量获取，如果确实不能获取，可以传递为空
        return properties;
    }

    /**
     *  用户性别转换
     * @param userSex
     * @return
     */
    private static String wrapUserSex(String userSex) {
        if ("0".equals(userSex)) {
            return "女";
        }
        if ("1".equals(userSex)) {
            return "男";
        }
        return "";
    }

    public static void init(Application application, Boolean printLog) {
        SHWAnalyticsConfig config = new SHWAnalyticsConfig.Builder()
                .logServer("dot.wts.xinwen.cn")
                .printLog(true)
                // 其它配置项可以使用默认值，也可以使用自己配置
                .build();
        SHWAnalytics.init(application, config);
    }
}

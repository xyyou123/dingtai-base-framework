package com.lnr.android.base.framework.common.umeng;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.dingtai.android.library.resource.GlobalConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * author:lnr
 * date:2018/9/25
 */
public class UMengConfig {

    public static void init(Context context) {

        ApplicationInfo appInfo =
                null;
        String msg = "dingtai";
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            msg=appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("UMengConfig", "UMENG_CHANNEL--->" + msg);
        MobclickAgent.setCatchUncaughtExceptions(true);
        MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);
        UMConfigure.init(context, GlobalConfig.UMENG_KEY,msg,UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setWeixin(GlobalConfig.WENXIN_ID, GlobalConfig.WENXIN_SECRET);
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo(GlobalConfig.WEIBO_KEY, GlobalConfig.WEIBO_SECRET,GlobalConfig.WEIBO_CALLBACKURI);
        PlatformConfig.setQQZone(GlobalConfig.QQ_ID, GlobalConfig.QQ_KEY);
    }
}

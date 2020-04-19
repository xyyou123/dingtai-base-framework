package androidlibrary;

import android.util.Log;


import com.dingtai.android.library.database.DBVersion;

import com.dingtai.android.library.news.NewsComponent;
import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Resource;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.FrameworkApplication;
import com.lnr.android.base.framework.GlobalPropertiesConfig;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.data.asyn.http.retrofit.RetrofitDefaultFactory;
import com.lnr.android.base.framework.data.asyn.http.retrofit.RetrofitProvider;
import com.lnr.android.base.framework.uitl.Base64Util;
import com.lnr.android.base.framework.uitl.FontsUtils;
import com.lnr.android.base.framework.uitl.logger.Logger;

import library.dingtai.com.androidlibrary.R;

public class App extends FrameworkApplication {

    @Override
    protected void initFramework() {
        DBVersion.initVersion(15);
        Logger.openDebug();

        RetrofitDefaultFactory.openDebug = true;
        Framework.getInstance().initModule(this, new NewsComponent());
        Resource.API.URL = "http://slb.gd.cz.hn.d5mt.com.cn/";
        RetrofitProvider.getInstance().registe(Resource.API.BASE,
                RetrofitDefaultFactory.defaultRetrofit(Resource.API.URL));
//        RetrofitProvider.getInstance().registe(Resource.API.BASE,
//                RetrofitDefaultFactory.defaultRetrofit("http://118.31.41.112:8081/"));
        GlobalConfig.SHARE_URL = "http://main.t.hn0746.com/";

        String success = Base64Util.decodeBase64ToUTF8("Success");

        Log.e("abc", success);

//        BaoliaoDetailsActivity.showZan = true;
//        BaoliaoDetailsActivity.showReport = true;
//        BaoliaoComponentConstant.LIST_ADAPTER_TYPE = 2;
        FontsUtils.setDefaultFont(this, "SERIF", "fonts/FZLTZHK.ttf");

//        AccountComponentConstant.USER_STID = "65";
        Resource.API.STID = "0";

        GlideHelper.error = R.drawable.icon_error;


        BlockCanary.install(this, new BlockCanaryContext()).start();

        //Routes.Account.FORGETPASSWORD = "/account/updatepwd2";

        boolean aBoolean = GlobalPropertiesConfig.getBoolean(GlobalPropertiesConfig.BAOLIAO_DETAILS_SHARE, false);
        Logger.log("config", "jieguo = " + aBoolean);


//        GlobalConfig.UMENG_KEY = BuildConfig.UMENG_KEY;
//        GlobalConfig.QQ_ID = BuildConfig.QQ_ID;
//        GlobalConfig.QQ_KEY = BuildConfig.QQ_KEY;
//        GlobalConfig.WENXIN_ID = BuildConfig.WENXIN_ID;
//        GlobalConfig.WENXIN_SECRET = BuildConfig.WENXIN_SECRET;
//        GlobalConfig.WEIBO_KEY = BuildConfig.WEIBO_KEY;
//        GlobalConfig.WEIBO_SECRET = BuildConfig.WEIBO_SECRET;
//        GlobalConfig.WEIBO_CALLBACKURI = BuildConfig.WEIBO_CALLBACKURI;
    }
}

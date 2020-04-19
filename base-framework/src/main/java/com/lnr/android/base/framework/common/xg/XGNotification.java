package com.lnr.android.base.framework.common.xg;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.XGNotificationModelDao;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.model.models.XGNotificationModel;
import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapterProvider;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapterType;
import com.lnr.android.base.framework.data.asyn.db.greendao.GreendaoCallAdapter;
import com.lnr.android.base.framework.uitl.JsonUtil;
import com.lnr.android.base.framework.uitl.SP;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author:lnr
 * date:2018/10/18
 */
public class XGNotification {

    public static String HINT_MESSAGE_STATUS = "message_status";

    public static void changeMessageStatus(boolean b) {


        SP.getDefault().edit().putBoolean(HINT_MESSAGE_STATUS, b).apply();
    }

    public static boolean getMessageStatus() {
        return SP.getDefault().getBoolean(HINT_MESSAGE_STATUS, false);
    }

    public static void save(XGNotificationModel model) {
        Framework.getInstance().registe(Observable.just(model)
                .subscribeOn(Schedulers.io())
                .map(new Function<XGNotificationModel, Object>() {
                    @Override
                    public Object apply(XGNotificationModel model) throws Exception {
                        return AsynCallAdapterProvider.getInstance().get(AsynCallAdapterType.DATABASE)
                                .call(XGNotificationModelDao.class)
                                .insertOrReplace(model);
                    }
                }).subscribe());
    }

    public static List<XGNotificationModel> loadAll() {
        return AsynCallAdapterProvider.getInstance().get(AsynCallAdapterType.DATABASE)
                .call(XGNotificationModelDao.class)
                .queryBuilder().orderDesc(XGNotificationModelDao.Properties.Time)
                .list();
    }

    public static void delete(XGNotificationModel model) {
        if (model == null) return;
        if (model.get_id() != null && model.get_id() != 0) {
            AsynCallAdapterProvider.getInstance().get(AsynCallAdapterType.DATABASE)
                    .call(XGNotificationModelDao.class).delete(model);
        }
    }

    public static void clear() {
        AsynCallAdapterProvider.getInstance().get(AsynCallAdapterType.DATABASE)
                .call(XGNotificationModelDao.class).deleteAll();
    }
}

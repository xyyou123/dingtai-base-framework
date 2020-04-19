package com.lnr.android.base.framework.common.xg;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.models.XGNotificationModel;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.uitl.JsonUtil;
import com.lnr.android.base.framework.uitl.SP;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import java.util.UUID;

/**
 * author:lnr
 * date:2018/10/18
 */
public class XGMessageReceiver extends XGPushBaseReceiver {

    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult result) {


    }

    @Override
    public void onUnregisterResult(Context context, int i) {

    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage result) {
        if (context == null || result == null) {
            return;
        }

        if (XGInitializer.sCallback != null) {
            XGNotificationModel model = new XGNotificationModel();
            model.setTitle(result.getTitle());
            model.setContent(result.getContent());
            model.setCustomContent(result.getCustomContent());
            model.setTime(System.currentTimeMillis());
            show(context, model, Math.abs(Long.valueOf(model.getTime()).hashCode()));
        }
    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {

    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult result) {
        if (XGInitializer.sCallback != null) {
            XGNotificationModel model = new XGNotificationModel();
            model.setTitle(result.getTitle());
            model.setContent(result.getContent());
            model.setCustomContent(result.getCustomContent());
            model.setTime(System.currentTimeMillis());

            XGNotification.save(model);
            XGNotification.changeMessageStatus(true);
            show(context, model, result.getNotifactionId());
        }
    }

    private void show(Context context, XGNotificationModel model, int notifactionId) {
        try {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager == null) return;

            //关闭推送后不显示推送消息
            if (!SP.getDefault().getBoolean(Resource.KEY.SETTING_PUSH, true)) {
                manager.cancel(notifactionId);
                return;
            }

            Intent intent = XGInitializer.sCallback.getIntent(context, model);
            if (intent == null) return;

            PendingIntent contentIntent = PendingIntent.getActivity(context, 0x11, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification.Builder builder = new Notification.Builder(context)
                    // API 11添加的方法
                    .setContentIntent(contentIntent).setSmallIcon(Resource.Drawable.APP_ICON)
                    .setWhen(System.currentTimeMillis()).setTicker(model.getTitle())
                    .setAutoCancel(true)
                    .setContentTitle(model.getTitle())
                    .setContentText(model.getContent())
                    .setDefaults(Notification.DEFAULT_ALL);
            Notification notification = builder.build();// API 16添加创建notification的方法
            manager.notify(notifactionId, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RxBus.getDefault().post(model);
    }
}

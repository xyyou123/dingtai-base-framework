package com.lnr.android.base.framework.ui.control.permission;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.view.View;


import com.lnr.android.base.framework.ui.control.dialog.MessageDialogHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/6/12
 */

public class PermissionRequest {

    private Activity mContext;
    private RxPermissions mRxPermissions;

    private Consumer<Boolean> mRequestPermissionCallback;

    private String[] mPermissions;


    private List<Disposable> mDisposableList;

    private PermissionRequest() {
        mDisposableList = new ArrayList<>();
    }

    public void recycle() {
        for (Disposable disposable : mDisposableList) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        }
        mDisposableList.clear();
        mContext = null;
    }

    public static PermissionRequest with(FragmentActivity context) {
        PermissionRequest request = new PermissionRequest();
        request.mContext = context;
        request.mRxPermissions = new RxPermissions(context);
        return request;
    }

    public PermissionRequest setMessage(String onRefuse, String onNotAsk) {
        return this;
    }

    public PermissionRequest permissions(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    private static final class RequestCallback implements Consumer<Permission> {

        private List<String> nomal = new ArrayList<>();
        private List<Permission> error = new LinkedList<>();
        private Consumer<List<Permission>> consumer;

        public RequestCallback(String[] permission, Consumer<List<Permission>> consumer) {
            nomal.addAll(Arrays.asList(permission));
            this.consumer = consumer;
        }

        @Override
        public void accept(Permission permission) throws Exception {
            nomal.remove(0);
            if(!permission.granted) {
                error.add(permission);
            }

            if(!nomal.isEmpty()) {
                return;
            }else {
                consumer.accept(error);
            }
        }
    }

    public void request(Consumer<Boolean> callback) {
        this.mRequestPermissionCallback = callback;
        mDisposableList.add(mRxPermissions.requestEach(mPermissions)
                .subscribe(new RequestCallback(mPermissions, new Consumer<List<Permission>>() {
                    @Override
                    public void accept(List<Permission> permissions) throws Exception {
                        if(permissions.isEmpty()) {
                            result(true);
                            return;
                        }

                        List<Permission> retry = new ArrayList<>();

                        for (Permission p : permissions) {
                            if (p.shouldShowRequestPermissionRationale) {
                                retry.add(p);
                            }
                        }

                        String message = "APP没有";
                        int count = permissions.size();
                        for (int i = 0; i < count; i++) {
                            message += PermissionContants.MESSAGE.get(permissions.get(i).name);
                            if(i < count - 1) {
                                message += "、";
                            }
                        }


                        if(retry.isEmpty()) {
                            //没有可以重试的,全部被拒绝且开启了不再提示
                            message += "权限，请前往权限管理开启";

                            MessageDialogHelper.builder(mContext)
                                    .setMsg(message)
                                    .setCancelable(false)
                                    .setPositiveButton("权限设置", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            jump2SystemPermissionSetting();
                                            result(false);
                                        }
                                    }).setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    result(false);
                                }
                            }).show();
                        }else {
                            message += "权限，是否重新请求权限？";
                            MessageDialogHelper.builder(mContext)
                                    .setMsg(message)
                                    .setCancelable(false)
                                    .setPositiveButton("重新申请", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            request(mRequestPermissionCallback);
                                        }
                                    }).setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    result(false);
                                }
                            }).show();
                        }
                    }
                })));
    }

    private void jump2SystemPermissionSetting() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
        mContext.startActivity(localIntent);
    }

    private void result(boolean result) {
        if (mRequestPermissionCallback != null) {
            try {
                mRequestPermissionCallback.accept(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

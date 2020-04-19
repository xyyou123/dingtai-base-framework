package com.lnr.android.base.framework.mvp.call.impl;

import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.common.image.compress.Luban;
import com.lnr.android.base.framework.common.image.compress.OnRenameListener;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.FileUtil;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * author:lnr
 * date:2018/11/9
 */
public class CompressSimgleImageAsynCall extends AbstractAsynCall<String> {

    @Override
    public Observable<String> call(AsynParams params) {
        final String type = params.get("type");
        return Observable.just((String) params.get("path"))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return Luban.with(Framework.getInstance().getApplication())
                                .load(s)
                                .setRenameListener(new OnRenameListener() {
                                    @Override
                                    public String rename(String filePath) {
                                        File file = new File(filePath);
                                        return type == null ? file.getName() : type + new File(filePath).getName();
                                    }
                                })
                                .get().get(0).getAbsolutePath();
                    }
                });
    }
}

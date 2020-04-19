package com.lnr.android.base.framework.uitl.voice2word;

import android.content.Context;
import android.content.SharedPreferences;

import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

/**
 * author:lnr
 * date:2018/9/7
 */
public class Voice2WordHelper {

    private Context context;
    private SharedPreferences sp;

    // 语音听写对象
    private RecognizerDialog recognizerDialog;

    public Voice2WordHelper(Context context, String appId) {
        this.context = context;
        this.sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public void listen(RecognizerDialogListener listener) {
        if(sp.getBoolean("iat_show", true)) {
            show(listener);
        }
    }

    private void show(RecognizerDialogListener listener) {
        if(recognizerDialog == null) {
            recognizerDialog = new RecognizerDialog(context, null);
        }
        // 显示听写对话框
        recognizerDialog.setListener(listener);
        recognizerDialog.show();
    }


    public void release() {
        context = null;
    }
}

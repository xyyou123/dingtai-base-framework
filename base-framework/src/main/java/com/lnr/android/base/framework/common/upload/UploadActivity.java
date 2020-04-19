package com.lnr.android.base.framework.common.upload;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.lnr.android.base.framework.common.image.selecte.MdeiaHelper;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarActivity;
import com.lnr.android.base.framework.uitl.voice2word.Voice2WordHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/10/11
 */
public abstract class UploadActivity extends ToolbarActivity {

    private static final String TAG = "UploadActivity";
    private File mTakeCameraTempFile;

    private Voice2WordHelper mVoice2WordHelper;

    @Override
    protected Dialog createLoadingDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
        dialog.setCancelable(false);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setTitle("正在发布");
        dialog.setMax(100);
        return dialog;
    }

    /**
     * 拍照
     */
    public void takeImage() {
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .request(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        int count = getSelecteCount(true);
                        if (aBoolean && count > 0) {
                            mTakeCameraTempFile = MdeiaHelper.takeCamera(mActivity);
                        }
                    }
                });
    }

    /**
     * 录制
     */
    public void takeVideo() {
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .request(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        int count = getSelecteCount(false);
                        if (aBoolean && count > 0) {
                            MdeiaHelper.recordViero(mActivity);
                        }
                    }
                });
    }

    /**
     * 选择照片
     */
    public void selecteImage() {
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        int count = getSelecteCount(true);
                        if (aBoolean && count > 0) {
                            MdeiaHelper.selecteImage(mActivity, count);
                        }
                    }
                });
    }

    /**
     * 选择视频
     */
    public void selecteVedio() {
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        int count = getSelecteCount(false);
                        if (aBoolean && count > 0) {
                            MdeiaHelper.selecteVideo(mActivity, count);
                        }
                    }
                });
    }

    public void voice2word() {
        requestPermission(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            if (mVoice2WordHelper == null)
                                mVoice2WordHelper = new Voice2WordHelper(mActivity, "5ba46870");
                            mVoice2WordHelper.listen(new RecognizerDialogListener() {
                                @Override
                                public void onResult(RecognizerResult recognizerResult, boolean b) {
                                    Log.e(TAG, b + "   " + recognizerResult.getResultString());

                                        try {
                                            JSONArray array = JSON.parseObject(recognizerResult.getResultString()).getJSONArray("ws");
                                            for (int i = 0; i < array.size(); i++) {
                                                onVoice2WordResult(array.getJSONObject(i).getJSONArray("cw").getJSONObject(0).getString("w"));
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                }

                                @Override
                                public void onError(SpeechError speechError) {
                                    Log.e(TAG, speechError.getMessage());
                                }
                            });
                        }
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        List<String> paths;
        switch (requestCode) {
            case MdeiaHelper.REQUEST_CODE_IMAGE:
                paths = MdeiaHelper.result(data);
                onActivityResult(paths, false);
                break;
            case MdeiaHelper.REQUEST_CODE_VIDEO:
                paths = MdeiaHelper.result(data);
                onActivityResult(paths, true);
                break;
            case MdeiaHelper.REQUEST_CODE_CAMERA:
                paths = new ArrayList<>();
                paths.add(mTakeCameraTempFile.getAbsolutePath());
                onActivityResult(paths, false);
                break;
            case MdeiaHelper.REQUEST_CODE_RECORD:
                paths = new ArrayList<>();
                paths.add(data.getStringExtra("RESULT_REOCRD_PATH"));
                onActivityResult(paths, true);
                break;

        }
    }

    protected abstract int getSelecteCount(boolean image);

    protected abstract void onActivityResult(List<String> paths, boolean video);

    protected abstract void onVoice2WordResult(String content);

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

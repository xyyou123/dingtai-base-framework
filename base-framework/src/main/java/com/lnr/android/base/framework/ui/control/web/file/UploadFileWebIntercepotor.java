package com.lnr.android.base.framework.ui.control.web.file;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import com.lnr.android.base.framework.common.image.selecte.MdeiaHelper;
import com.lnr.android.base.framework.common.upload.Uploader;
import com.lnr.android.base.framework.data.asyn.core.AsynCallExecutor;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.call.impl.CompressSimgleImageAsynCall;
import com.lnr.android.base.framework.mvp.call.impl.UploadFileAsynCall;
import com.lnr.android.base.framework.ui.control.dialog.BottomMenu;
import com.lnr.android.base.framework.ui.control.dialog.BottomMenuHelper;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.ui.control.web.AbstractWebViewInterceptor;
import com.lnr.android.base.framework.uitl.date.DateUtil;
import com.tencent.smtt.sdk.WebView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/12/13
 * 上传文件
 */
public class UploadFileWebIntercepotor extends AbstractWebViewInterceptor {

    private Callback mCallback;

    private File mTakeCameraTempFile;

    private Uploader mUploader;
    private AsynCallExecutor mAsynCallExecutor;
    private ProgressDialog mUploadDialog;
    private UploadFileAsynCall mUploadFileAsynCall;
    private CompressSimgleImageAsynCall mCompressSimgleImageAsynCall;

    public interface Callback {

        void requestPermission(Consumer<Boolean> consumer, String... permissions);

        LifecycleTransformer bindLife();
    }

    public UploadFileWebIntercepotor(Activity activity, Callback callback) {
        super(activity);
        this.mCallback = callback;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.contains("dingtai$")) {
            String[] actions = url.substring(url.indexOf("dingtai$") + "dingtai$".length()).split("@");
            if(actions.length < 2)  return false;
            return shouldRequest(actions);
        }
        return false;
    }

    @Override
    public void onPageFinished(WebView webView, String s) {

    }

    private boolean shouldRequest(final String[] action) {
        if(!"file".equals(action[0])) {
            return false;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (action[1]) {
                    case "Img":
                        showImageMenu();
                        break;
                    case "Media":
                        showVideoMenu();
                        break;
                }
            }
        });

        return true;
    }

    private void showImageMenu() {
        BottomMenuHelper.newNoTitle(activity)
                .addMenuItem("选择照片", BottomMenu.MenuColor.Black, new BottomMenu.OnMenuClickListener() {
                    @Override
                    public void onClick() {
                        selecteImage();
                    }
                })
                .addMenuItem("拍摄照片", BottomMenu.MenuColor.Black, new BottomMenu.OnMenuClickListener() {
                    @Override
                    public void onClick() {
                        takeImage();
                    }
                })
                .show();
    }

    private void showVideoMenu() {
        BottomMenuHelper.newNoTitle(activity)
                .addMenuItem("选择视频", BottomMenu.MenuColor.Black, new BottomMenu.OnMenuClickListener() {
                    @Override
                    public void onClick() {
                        selecteVedio();
                    }
                })
                .addMenuItem("拍摄视频", BottomMenu.MenuColor.Black, new BottomMenu.OnMenuClickListener() {
                    @Override
                    public void onClick() {
                        takeVideo();
                    }
                })
                .show();
    }

    /**
     * 拍照
     */
    private void takeImage() {
        mCallback.requestPermission(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    MdeiaHelper.recordViero(activity);
                }
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }

    /**
     * 录制
     */
    private void takeVideo() {
        mCallback.requestPermission(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    mTakeCameraTempFile = MdeiaHelper.takeCamera(activity);
                }
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }

    /**
     * 选择照片
     */
    private void selecteImage() {
        mCallback.requestPermission(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    MdeiaHelper.selecteImage(activity, 1);
                }
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 选择视频
     */
    private void selecteVedio() {
        mCallback.requestPermission(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    MdeiaHelper.selecteVideo(activity, 1);
                }
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            return;
        }

        String path = null;
        boolean image = false;
        switch (requestCode) {
            case MdeiaHelper.REQUEST_CODE_IMAGE:
                List<String> images = MdeiaHelper.result(data);
                if(images != null && images.size() > 0) {
                    path = images.get(0);
                    image = true;
                }
                break;
            case MdeiaHelper.REQUEST_CODE_VIDEO:
                List<String> videos = MdeiaHelper.result(data);
                if(videos != null && videos.size() > 0) {
                    path = videos.get(0);
                    image = false;
                }
                break;
            case MdeiaHelper.REQUEST_CODE_CAMERA:
                path = mTakeCameraTempFile.getAbsolutePath();
                image = true;
                break;
            case MdeiaHelper.REQUEST_CODE_RECORD:
                path = data.getStringExtra("RESULT_REOCRD_PATH");
                image = false;
                break;
        }

        if(path == null) return;
        uploadFile(path, image);
    }


    protected void uploadFile(final String path, final boolean image) {
        if(mUploadDialog == null) {
            mUploadDialog = new ProgressDialog(activity);
            mUploadDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
            mUploadDialog.setCancelable(false);// 设置是否可以通过点击Back键取消
            mUploadDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
            mUploadDialog.setTitle("正在上传");
            mUploadDialog.setMax(100);

            mAsynCallExecutor = new AsynCallExecutor(mCallback.bindLife(), null);
            mUploadFileAsynCall = new UploadFileAsynCall();
            mCompressSimgleImageAsynCall = new CompressSimgleImageAsynCall();
            mUploader = new Uploader();
        }

        if(image) {
            mUploadDialog.show();
            mAsynCallExecutor.create(mCompressSimgleImageAsynCall.call(AsynParams.create("path", path)
                    .put("type", "active")))
                    .excuteWithLoading(new AsynCallback<String>() {
                        @Override
                        public void onCallResponse(String r) {
                            upload(r, true);
                        }

                        @Override
                        public void onCallError(Throwable ex) {
                            mUploadDialog.dismiss();
                            ToastHelper.toastError("文件上传失败");
                        }
                    });
        }else {
            upload(path, false);
        }
    }

    private void upload(final String path, final boolean image) {
        List<String> file = new ArrayList<>();
        file.add(path);

        mUploader.upload(file, new Uploader.OnUploadCallback() {
            @Override
            public void onBegin() {
            }

            @Override
            public void onUploading(int index, int progress) {
                mUploadDialog.setProgress(progress);
            }

            @Override
            public void onSucceed() {
                mUploadDialog.dismiss();

                String uploadPath = "";
                String pattern = "yyyyMMdd";

                if(image) {
                    uploadPath += "/" + DateUtil.format(System.currentTimeMillis(), pattern) + "/" + new File(path).getName();
                }else {
                    uploadPath = new File(path).getName();
                }

                AsynParams params = image ? AsynParams.create("PicUrl", uploadPath) : AsynParams.create("VideoUrl", uploadPath);
                mAsynCallExecutor.create(mUploadFileAsynCall.call(params))
                        .excuteWithLoading(new AsynCallback<String>() {
                            @Override
                            public void onCallResponse(String r) {
                                if(r != null) {
                                    wrapper.quickCallJs("GetAlert", r);
                                }else {
                                    ToastHelper.toastError("文件上传失败");
                                }
                            }

                            @Override
                            public void onCallError(Throwable ex) {
                                ToastHelper.toastError("文件上传失败");
                            }
                        });
            }

            @Override
            public void onFailed(int code, String message) {
                mUploadDialog.dismiss();
                ToastHelper.toastError("文件上传失败");
            }
        });
    }

    @Override
    public void onDestroy() {
        mAsynCallExecutor = null;
        if(mUploader != null) mUploader.release();
        mUploader = null;
        super.onDestroy();
    }
}

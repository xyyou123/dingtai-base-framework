package com.lnr.android.base.framework.common.image.selecte;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtai.android.library.resource.Routes;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.uitl.DeviceUtils;
import com.lnr.android.base.framework.uitl.FileUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.SelectionCreator;

import java.io.File;
import java.util.List;

/**
 * author:lnr
 * date:2018/9/7
 * 系统媒体选择工具
 */
public class MdeiaHelper {

    public static final int REQUEST_CODE_IMAGE = 0x112;
    public static final int REQUEST_CODE_VIDEO = 0x113;
    public static final int REQUEST_CODE_CAMERA = 0x114;
    public static final int REQUEST_CODE_RECORD = 0x115;

    private static SelectionCreator apply(SelectionCreator creator) {
        return creator.countable(true)
                .theme(com.zhihu.matisse.R.style.Matisse_Dracula)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.5f) // 缩略图的比例
                .showSingleMediaType(true)
                .imageEngine(new LoaderEngine()); // 使用的图片加载引擎
    }

    public static void selecteImage(Activity activity) {
        selecteImage(activity, 6);
    }

    public static void selecteImage(Activity activity, int max) {
        apply(Matisse.from(activity).choose(MimeType.ofImage(), false))
                .maxSelectable(max)
                .forResult(REQUEST_CODE_IMAGE);
    }

    public static void selecteImage(Fragment fragment) {
        selecteImage(fragment, 6);
    }

    public static void selecteImage(Fragment fragment, int max) {
        apply(Matisse.from(fragment).choose(MimeType.ofImage(), false))
                .maxSelectable(max)
                .forResult(REQUEST_CODE_IMAGE);
    }

    public static void selecteVideo(Activity activity) {
        selecteVideo(activity, 3);
    }

    public static void selecteVideo(Activity activity, int max) {
        apply(Matisse.from(activity).choose(MimeType.ofVideo(), false))
                .maxSelectable(max)
                .forResult(REQUEST_CODE_VIDEO);
    }

    public static void selecteVideo(Fragment fragment) {
        selecteVideo(fragment, 3);
    }

    public static void selecteVideo(Fragment fragment, int max) {
        apply(Matisse.from(fragment).choose(MimeType.ofVideo(), false))
                .maxSelectable(max)
                .forResult(REQUEST_CODE_VIDEO);
    }

    public static List<String> result(Intent intent) {
        return Matisse.obtainPathResult(intent);
    }


    public static File takeCamera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = FileUtil.newFile(FileUtil.IMAGE, System.currentTimeMillis() + ".jpg");
        Uri imageUri;
        //下面这句指定调用相机拍照后的照片存储的路径
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            imageUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileprovider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            imageUri = Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, REQUEST_CODE_CAMERA);
        return file;
    }

    public static File takeCamera(Fragment fragment) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = FileUtil.newFile(FileUtil.IMAGE, System.currentTimeMillis() + ".jpg");
        Uri imageUri;
        //下面这句指定调用相机拍照后的照片存储的路径
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            imageUri = FileProvider.getUriForFile(fragment.getContext(), fragment.getContext().getPackageName() + ".fileprovider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            imageUri = Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        fragment.startActivityForResult(intent, REQUEST_CODE_CAMERA);
        return file;
    }

    public static void recordViero(Activity activity) {
        ARouter.getInstance().build(Routes.Recorder.REOCRD).navigation(activity, REQUEST_CODE_RECORD);
    }

    public static void pushLive(String url) {
        ARouter.getInstance().build(Routes.Recorder.PUSH)
                .withString("url", url).navigation();
    }
}

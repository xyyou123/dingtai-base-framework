package com.lnr.android.base.framework.common.upload;

import com.lnr.android.base.framework.common.image.compress.Luban;
import com.lnr.android.base.framework.ui.control.dialog.BottomMenu;
import com.lnr.android.base.framework.ui.control.dialog.BottomMenuHelper;
import com.lnr.android.base.framework.ui.control.dialog.MessageDialogHelper;
import com.lnr.android.base.framework.uitl.date.DateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author:lnr
 * date:2018/10/11
 */
public abstract class SimpleUploadActivity extends UploadActivity {

    protected MediaAdapter mediaAdapter;

    protected int MAX_IMAGE = 6;
    protected int MAX_VIDEO = 3;

    protected MediaAdapter getMediaAdapter() {
        return mediaAdapter;
    }

    @Override
    protected void initView() {
        initMediaAdapter();
    }

    protected void initMediaAdapter() {
        mediaAdapter = new MediaAdapter();
        mediaAdapter.setShowDelete(true);
    }

    @Override
    protected void onActivityResult(List<String> paths, boolean video) {
        if (paths == null || paths.isEmpty()) return;

        if (video) {
            for (String path : paths) {
                mediaAdapter.add(new MediaAdapter.MediaItem(path, true));
            }
            mediaAdapter.notifyDataSetChanged();
        } else {
            registe(Observable.just(paths)
                    .subscribeOn(Schedulers.io())
                    .map(new Function<List<String>, List<File>>() {
                        @Override
                        public List<File> apply(List<String> strings) throws Exception {
                            return Luban.with(mActivity).load(strings).get();
                        }
                    }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<File>>() {
                        @Override
                        public void accept(List<File> files) throws Exception {
                            for (File path : files) {
                                mediaAdapter.add(new MediaAdapter.MediaItem(path.getAbsolutePath
                                        (), false));
                            }
                            mediaAdapter.notifyDataSetChanged();
                        }
                    }));
        }
    }

    @Override
    protected int getSelecteCount(boolean image) {
        return image ? MAX_IMAGE - mediaAdapter.getImageCount() :
                MAX_VIDEO - mediaAdapter.getVideoCount();
    }

    protected void clickImageMenu() {
        if (mediaAdapter.getImageCount() >= MAX_IMAGE) {
            MessageDialogHelper.show(mActivity, "最多只能上传" + MAX_IMAGE + "张图片");
            return;
        }
        BottomMenuHelper.newNoTitle(mActivity)
                .addMenuItem("选择照片", BottomMenu.MenuColor.Black, new BottomMenu
                        .OnMenuClickListener() {
                    @Override
                    public void onClick() {
                        selecteImage();
                    }
                })
                .addMenuItem("拍摄照片", BottomMenu.MenuColor.Black, new BottomMenu
                        .OnMenuClickListener() {
                    @Override
                    public void onClick() {
                        takeImage();
                    }
                })
                .show();
    }

    protected void clickVideoMenu() {
        if (mediaAdapter.getVideoCount() >= MAX_VIDEO) {
            MessageDialogHelper.show(mActivity, "最多只能上传" + MAX_VIDEO + "个视频");
            return;
        }
        BottomMenuHelper.newNoTitle(mActivity)
                .addMenuItem("选择视频", BottomMenu.MenuColor.Black, new BottomMenu
                        .OnMenuClickListener() {
                    @Override
                    public void onClick() {
                        selecteVedio();
                    }
                })
                .addMenuItem("拍摄视频", BottomMenu.MenuColor.Black, new BottomMenu
                        .OnMenuClickListener() {
                    @Override
                    public void onClick() {
                        takeVideo();
                    }
                })
                .show();
    }

    /**
     * 获取带上上传的文件地址
     * image : 图片
     * video ：视频
     * all   ：全部的
     */
    protected HashMap<String, Object> getUploadPaths() {

        HashMap<String, Object> map = new HashMap<>();

        List<String> paths = new ArrayList<>();
        String imagePaths = "", videoPaths = "";
        String pattern = "yyyyMMdd";

        for (MediaAdapter.MediaItem item : getMediaAdapter().getDatas()) {
            // TODO: 2020/4/2  去掉替换中文命名
            item.imageUrl= setRename(item.imageUrl);
            if (item.video) {
                paths.add(item.imageUrl);
                videoPaths += new File(item.imageUrl).getName() + ",";
            } else {
                paths.add(item.imageUrl);
                imagePaths += "/" + DateUtil.format(System.currentTimeMillis(), pattern) + "/" +
                        new File(item.imageUrl).getName() + ",";
            }
        }

        if (videoPaths.endsWith(",")) {
            videoPaths = videoPaths.substring(0, videoPaths.length() - 1);
        }

        if (imagePaths.endsWith(",")) {
            imagePaths = imagePaths.substring(0, imagePaths.length() - 1);
        }

        map.put("image", imagePaths);
        map.put("video", videoPaths);
        map.put("all", paths);

        return map;
    }

    private String setRename(String path) {
        String filetype = path.substring(path.lastIndexOf("."), path.length());
        File oldfile = new File(path);
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(oldfile.getName());
        if (m.find()) {
            //包含中文 重命名
            File newfile = new File(oldfile.getParent() + File.separator + System
                    .currentTimeMillis()+filetype);
            if (oldfile.renameTo(newfile)) {
                return newfile.getPath();
            } else {
                return path;
            }
        } else {
            return path;
        }

    }
}

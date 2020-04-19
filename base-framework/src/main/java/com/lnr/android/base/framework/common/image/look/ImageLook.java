package com.lnr.android.base.framework.common.image.look;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class ImageLook {

    public static void look(String openPath, List<String> paths, List<String> contents) {
       look(paths.indexOf(openPath), paths, contents);
    }

    public static void look(int position, List<String> paths, List<String> contents) {
        ARouter.getInstance().build("/framework/image/look")
                .withInt("index", position)
                .withStringArrayList("images", new ArrayList<>(paths))
                .withStringArrayList("contents", new ArrayList<>(contents))
                .navigation();
    }

    /**
     * @see LookImageActivity
     * @param paths 地址
     * @param content 描述
     */
    public static void look(List<String> paths, String content) {
        ARouter.getInstance().build("/framework/image/look")
                .withInt("index", 0)
                .withStringArrayList("images", new ArrayList<>(paths))
                .withString("content", content)
                .navigation();
    }

    /**
     * @see LookImageActivity
     * @param paths 地址
     * @param content 描述
     */
    public static void look(int position, List<String> paths, String content) {
        ARouter.getInstance().build("/framework/image/look")
                .withInt("index", position)
                .withStringArrayList("images", new ArrayList<>(paths))
                .withString("content", content)
                .navigation();
    }

    /**
     * @see LookImageActivity
     * @param paths 地址
     * @param content 描述
     */
    public static void look(String path, List<String> paths, String content) {
        ARouter.getInstance().build("/framework/image/look")
                .withInt("index", paths.indexOf(path))
                .withStringArrayList("images", new ArrayList<>(paths))
                .withString("content", content)
                .navigation();
    }
}

package com.lnr.android.base.framework.uitl.cache;

import android.os.Environment;

import com.lnr.android.base.framework.uitl.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * author:lnr
 * date:2018/5/25
 */

public class FileCache {

    private static final String CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dingtai";

    private static final String DIR_LOG = "log";
    private static final String IMAGE_LOG = "image";
    private static final String AUDIO_LOG = "audio";

    private static File mkdir(File file) {
        if(!file.exists() && file.mkdirs()) {
            return file;
        }
        return file;
    }

    private static File createNewFile(File dir, String name) {
        try {
            mkdir(dir);
            File file = new File(dir, name);
            boolean newFile = file.createNewFile();
            if(newFile) {
                return file;
            }else {
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File getLogDir() {
        return mkdir(new File(FileUtil.LOG));
    }

    public static File getImageDir() {
        return mkdir(new File(CACHE_DIR, IMAGE_LOG));
    }

    public static File getAudioDir() {
        return mkdir(new File(CACHE_DIR, AUDIO_LOG));
    }

    public static File getInnerAudioDir() {
        return mkdir(new File(getAudioDir(), ""));
    }

    public static File newImageFile() {
        return new File(getImageDir(), System.currentTimeMillis() + ".jpg");
    }

    public static File newAudioFile(String name) {
        return createNewFile(getInnerAudioDir(), name);
    }
}

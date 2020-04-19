package com.lnr.android.base.framework.uitl;

import android.os.Environment;

import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.R;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class FileUtil {

    private static final String ROOT = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dingtai";

    private static final String APP;
    public static final String IMAGE;
    public static final String FILE;
    public static final String LOG;

    public static final String CACHE_FILE;

    static {
        String app = Framework.getInstance().getApplication().getResources().getString(R.string.app_name);
        APP = ROOT + File.separator + app;
        IMAGE = APP + File.separator + "image";
        FILE = APP + File.separator + "file";
        LOG = APP + File.separator + "log";

        CACHE_FILE = Framework.getInstance().getApplication().getFilesDir().getAbsolutePath();
    }

    public static File app() {
        return new File(APP);
    }

    public static String contentPath(String path) {
        if(path.startsWith("file://")) {
            return path;
        }

        return "file://" + path;
    }

    public static boolean isFilePath(String path) {
        return path.startsWith("file://");
    }

    public static BufferedReader inputStream2BufferedReader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }


    public static String file2String(InputStream inputStream) {
        if(inputStream == null) return null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = inputStream2BufferedReader(inputStream);
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static File newFile(String path) {
        File file = new File(path);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File newFile(File dir, String name) {
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return newFile(dir.getAbsolutePath() + File.separator + name);
    }

    public static File newFile(String dir, String name) {
        return newFile(new File(dir), name);
    }

    public static void delete(String path) {
        try {
            if(path == null) return;
            File file = new File(path);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存文件到本地
     */
    public static File saveFile(String filePath, InputStream inputStream) {
        byte[] buf = new byte[4096];
        int len;
        FileOutputStream fos = null;
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if(parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }
            if(!file.exists()) {
                file.createNewFile();
            }
            file.setReadable(true);
            file.setWritable(true);
            fos = new FileOutputStream(file);
            while ((len = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            return file;
        } catch (IOException e) {
            return null;
        } finally {
            close(fos, inputStream);
        }
    }

    public static void read2OutputStream(File file, OutputStream out, boolean close) {
        byte[] data = new byte[300];
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            int len;
            while ((len = is.read(data)) > 0) {
                out.write(data, 0, len);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(close) {
                FileUtil.close(out);
            }
            if(is != null) {
                FileUtil.close(is);
            }
        }
    }

    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            }catch (Exception e){}
        }
    }
}

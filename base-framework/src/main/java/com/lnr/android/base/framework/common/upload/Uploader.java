package com.lnr.android.base.framework.common.upload;

import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.app.AppHandler;
import com.lnr.android.base.framework.app.HandlerRunnable;
import com.lnr.android.base.framework.uitl.NumberUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author:lnr
 * date:2018/9/7
 */
public class Uploader {

    public static final int ERROR_CONNECT_FAILED = 0x601;
    public static final int ERROR_UPLOAD_FAILED = 0x602;

    private Socket socket;
    private InputStream is;
    private OutputStream os;

    private List<String> paths;
    private List<String> names;
    private List<String> succeedPath;

    /**
     * 线程池
     */
    private ExecutorService pool;

    private boolean excuteing;

    /**
     * 当前上传的文件下标
     */
    private int currentFileIndex;

    /**
     * 上次上传位置
     */
    private volatile long lastPosition;

    /**
     * 当前失败次数
     */
    private volatile int retryCount;
    /**
     * 失败重试总次数
     */
    private final static int retryMaxCount = 2;

    /**
     * 已经上传的大小
     */
    private long uploadedSize;
    /**
     * 已经上传的大小(截止上个文件，当前文件已上传的大小不包含，上传出错时回滚使用)
     */
    private long lastUploadSize;
    /**
     * 带上传的文件总大小
     */
    private long allSize;

    private Object mTag;

    private OnUploadCallback onUploadCallback;

    public Uploader() {
        this.succeedPath = new ArrayList<>();
    }

    public void upload(List<String> files, OnUploadCallback onUploadCallback) {
        this.onUploadCallback = onUploadCallback;
        this.paths = files;
        execute();
    }

    public void upload(List<String> files, List<String> fileNames, OnUploadCallback onUploadCallback) {
        this.onUploadCallback = onUploadCallback;
        this.paths = files;
        this.names = fileNames;
        execute();
    }

    public void setTag(Object tag) {
        this.mTag = tag;
    }

    public Object getTag() {
        return mTag;
    }

    public void cancel() {
        excuteing = false;
    }

    public void release() {
        cancel();
        if (pool != null) pool.shutdownNow();

        onUploadCallback = null;
        pool = null;
    }

    private void execute() {
        if (excuteing) {
            return;
        }

        if (pool == null || pool.isShutdown()) {
            this.pool = Executors.newFixedThreadPool(2);
        }
        currentFileIndex = 0;
        uploadedSize = 0;
        allSize = 0;
        retryCount = 0;
        lastPosition = 0;
        excuteing = true;
        if (onUploadCallback != null) {
            onUploadCallback.onBegin();
        }
        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (String path : paths) {
                    allSize += new File(path).length();
                }
                connect();
            }
        });
    }

    private void connect() {
        try {
            socket = new Socket(Resource.API.FIEL_IP, Resource.API.FIEL_PORT);
            if (socket.isConnected()) {
                is = socket.getInputStream();
                os = socket.getOutputStream();
                read();
                write();
            } else {
                error(ERROR_CONNECT_FAILED, "连接服务器失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            close();
            error(ERROR_CONNECT_FAILED, "连接服务器失败！");
        }
    }

    private void close() {
        try {
            if (socket != null) socket.close();
            if (is != null) is.close();
            if (os != null) os.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void read() {
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (excuteing) {
                        int start = is.read();
                        if (start == -1) {
                            continue;
                        }
                        byte[] result = new byte[1 + is.available()];
                        result[0] = (byte) start;
                        is.read(result, 1, result.length - 1);

                        Command response = Command.response(result);
                        HashMap<String, String> params = response.getParams();

                        switch (response.getCmd()) {
                            case "Login":
                                sendUploadRequest();
                                break;
                            case "Upload":
                                lastPosition = NumberUtil.parseLong(params.get("FileSize"));
                                lastUploadSize = uploadedSize;
                                upload();
                                break;
                            case "Eof":
                                if ("0".equals(params.get("Code"))) {
                                    retryCount = 0;
                                    succeedPath.add(paths.get(currentFileIndex));

                                    currentFileIndex++;
                                    while (currentFileIndex < paths.size()) {
                                        if (!succeedPath.contains(paths.get(currentFileIndex))) {
                                            break;
                                        }
                                        //已经成功上传的，此处无需再次上传
                                        currentFileIndex++;
                                    }

                                    if (currentFileIndex < paths.size()) {
                                        // upload 上传请求
                                        sendUploadRequest();
                                    } else {
                                        try {
                                            is.close();
                                            os.close();
                                            socket.close();
                                        } finally {
                                            succeed();
                                        }
                                    }
                                } else {
                                    uploadedSize = lastUploadSize;
                                    upload();
                                }
                                break;
                        }
                    }
                } catch (IOException e) {
                    close();
                    if (excuteing)
                        error(ERROR_UPLOAD_FAILED, "上传失败！");
                }
            }
        });
    }

    private void write() throws IOException {
        byte[] b1 = new byte[1];
        b1[0] = (byte) 2;
        os.write(b1);

        Command command = Command.request("Login")
//                .addParams("UserName", "admin")
//                .addParams("Password", "21232f297a57a5a743894a0e4a801fc3")
                .addParams("UserName", Resource.API.FIEL_UPLOAD_USERNAME)
                .addParams("Password", Resource.API.FIEL_UPLOAD_PASSWORD);
        os.write(command.toPacket(null));
    }

    private void sendUploadRequest() throws IOException {
        Command upload = Command.request("Upload")
                .addParams("DirName", "")
                .addParams("FileName", names == null ? new File(paths.get(currentFileIndex)).getName() : names.get(currentFileIndex));
        os.write(upload.toPacket(null));
    }


    private void upload() throws IOException {
        if (retryCount >= retryMaxCount) {
            error(ERROR_UPLOAD_FAILED, "上传失败！");
        } else {
            ++retryCount;
            long position = lastPosition;
            RandomAccessFile fileOutStream = new RandomAccessFile(new File(paths.get(currentFileIndex)), "r");
            fileOutStream.seek(position);
            Command command = Command.request("Data");
            int len;
            byte[] btf = new byte[1024 * 300];
            while (excuteing && (len = fileOutStream.read(btf)) != -1) {
                uploadedSize += len;
                if (len != 1024 * 300) {
                    byte[] arr = new byte[len];
                    System.arraycopy(btf, 0, arr, 0, len);
                    btf = arr;
                }
                os.write(command.toPacket(btf));
                onUploading((int) (uploadedSize * 100L / allSize));
            }

            Command upload = Command.request("Eof")
                    .addParams("FileSize", String.valueOf(fileOutStream.length()));
            os.write(upload.toPacket(null));
        }
    }

    private void error(final int code, final String message) {
        excuteing = false;
        AppHandler.getInstance().post(new HandlerRunnable() {
            @Override
            protected void safeRun() {
                if (onUploadCallback != null) {
                    onUploadCallback.onFailed(code, message);
                }
            }
        });
    }

    private void onUploading(final int progress) {
        AppHandler.getInstance().post(new HandlerRunnable() {
            @Override
            protected void safeRun() {
                if (onUploadCallback != null) {
                    onUploadCallback.onUploading(currentFileIndex, progress);
                }
            }
        });
    }

    private void succeed() {
        excuteing = false;
        AppHandler.getInstance().post(new HandlerRunnable() {
            @Override
            protected void safeRun() {
                if (onUploadCallback != null) {
                    onUploadCallback.onSucceed();
                }
                release();
            }
        });
    }


    public interface OnUploadCallback {

        void onBegin();

        void onUploading(int index, int progress);

        void onSucceed();

        void onFailed(int code, String message);
    }

}

package com.dingtai.android.library.video.ui.video.upload;

import com.dingtai.android.library.video.api.impl.PublishVideoAsynCall;
import com.dingtai.android.library.video.model.VideoModel;
import com.lnr.android.base.framework.common.upload.Uploader;
import com.lnr.android.base.framework.data.asyn.core.AsynCallExecutor;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.NumberUtil;
import com.lnr.android.base.framework.uitl.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * author:lnr
 * date:2018/11/28
 * 视频上传管理
 */
public class UploadVideoManager {

    private Uploader mUploader1;
    private Uploader mUploader2;

    private LinkedList<VideoModel> mWatingList;
    private LinkedList<VideoModel> mUploadingList;
    private LinkedList<VideoModel> mCompleteList;
    private LinkedList<VideoModel> mErrorList;

    private static final Object LOCK = new Object();

    private OnUploadStateUpdateListener mOnUploadProgressChangeListener;

    private AsynCallExecutor mExecutor;
    private PublishVideoAsynCall mPublishVideoAsynCall;

    private static final class SingleHolder {
        private static final UploadVideoManager INSTANCE = new UploadVideoManager();
    }

    private UploadVideoManager() {
        mWatingList = new LinkedList<>();
        mUploadingList = new LinkedList<>();
        mCompleteList = new LinkedList<>();
        mErrorList = new LinkedList<>();

        mExecutor = new AsynCallExecutor(null, null);
        mPublishVideoAsynCall = new PublishVideoAsynCall();
    }

    public static UploadVideoManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    public void add(VideoModel model) {
        synchronized (LOCK) {
            mWatingList.add(model);
        }
        upload();
    }

    private void upload() {
        synchronized (LOCK) {

            Logger.log("upload", "准备上传");

            if(mWatingList.isEmpty()) {
                Logger.log("upload", "没有文件需要上传");
                return;
            }
            if(mUploadingList.size() >= 2) {
                if(mOnUploadProgressChangeListener != null) {
                    UploadState state = new UploadState();
                    state.model = mWatingList.getLast();
                    state.state = UploadState.STATE_WAIT;
                    mOnUploadProgressChangeListener.onUpdate(state);
                }
                return;
            }

            VideoModel model = mWatingList.removeLast();
            mUploadingList.add(model);

            if(mUploader1 == null) {
                Logger.log("upload", "Uploader1空闲，使用Uploader1上传");

                mUploader1 = new Uploader();

                UploadState state = new UploadState();
                state.model = model;
                state.state = UploadState.STATE_WAIT;
                mUploader1.setTag(state);

                mUploader1.upload(Collections.singletonList(model.getMediaUrl()),
                        Collections.singletonList(model.getUploadName()),
                        new OnUploadCallbackImpl(1));
                return;
            }

            if(mUploader2 == null) {
                Logger.log("upload", "Uploader2空闲，使用Uploader2上传");
                mUploader2 = new Uploader();
                UploadState state = new UploadState();
                state.model = model;
                state.state = UploadState.STATE_WAIT;
                mUploader2.setTag(state);
                mUploader2.upload(Collections.singletonList(model.getMediaUrl()), new OnUploadCallbackImpl(2));
                return;
            }

            Logger.log("upload", "当前上传队列已满，等待中...");

            if(mOnUploadProgressChangeListener != null) {
                UploadState state = new UploadState();
                state.model = mWatingList.getLast();
                state.state = UploadState.STATE_WAIT;
                mOnUploadProgressChangeListener.onUpdate(state);
            }
        }
    }

    private void release(int index, boolean success) {
        synchronized (LOCK) {
            Uploader uploader = index == 1 ? mUploader1 : mUploader2;
            UploadState state = (UploadState)uploader.getTag();
            mUploadingList.remove(state.model);
            if(success) {
                mCompleteList.add(state.model);
                publish(state.model);
            }else {
                mErrorList.add(state.model);
            }
            uploader.release();

            if(index == 1) {
                mUploader1 = null;
            }else {
                mUploader2 = null;
            }
        }

        upload();
    }

    private void publish(final VideoModel model) {
        AsynParams params = AsynParams.create("ChannelID", model.getID2())
                .put("Name", model.getName())
                .put("Detail", model.getDetail())
                .put("ImageUrl", model.getDetail())
                .put("FileName", model.getUploadName())
                .put("FileExt", model.getMediaUrl().substring(model.getMediaUrl().lastIndexOf(".")))
                .put("UploadType", "2");
        mExecutor.create(mPublishVideoAsynCall, params).excuteNoLoading(new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean r) {
                if(mOnUploadProgressChangeListener != null) {
                    UploadState state = new UploadState();
                    state.model = model;
                    if(r) {
                        state.state = UploadState.STATE_POST;
                    }else {
                        state.isError = true;
                    }
                    mOnUploadProgressChangeListener.onUpdate(state);
                }
            }

            @Override
            public void onCallError(Throwable ex) {
                if(mOnUploadProgressChangeListener != null) {
                    UploadState state = new UploadState();
                    state.model = model;
                    state.isError = true;
                    mOnUploadProgressChangeListener.onUpdate(state);
                }
            }
        });
    }

    private class OnUploadCallbackImpl implements Uploader.OnUploadCallback {
        private final int mIndex;
        private OnUploadCallbackImpl(int index) {
            this.mIndex = index;
        }

        @Override
        public void onBegin() {
            UploadState state = (UploadState) (mIndex == 1 ? mUploader1.getTag() : mUploader2.getTag());
            if(mOnUploadProgressChangeListener != null) {
                mOnUploadProgressChangeListener.onUpdate(state);
            }

            Logger.log("upload", (mIndex == 1 ? "Uploader1" : "Uploader2") + "开始上传");
        }

        @Override
        public void onUploading(int index, int progress) {
            UploadState state = (UploadState) (mIndex == 1 ? mUploader1.getTag() : mUploader2.getTag());
            state.state = UploadState.STATE_PROGRESS;
            state.progress = progress;
            if(mOnUploadProgressChangeListener != null) {
                mOnUploadProgressChangeListener.onUpdate(state);
            }

            Logger.log("upload", (mIndex == 1 ? "Uploader1" : "Uploader2") + "上传中：progress = " + progress);
        }

        @Override
        public void onSucceed() {
            UploadState state = (UploadState) (mIndex == 1 ? mUploader1.getTag() : mUploader2.getTag());
            state.state = UploadState.STATE_COMPLETE;

            release(mIndex, true);

            if(mOnUploadProgressChangeListener != null) {
                mOnUploadProgressChangeListener.onUpdate(state);
            }

            Logger.log("upload", (mIndex == 1 ? "Uploader1" : "Uploader2") + "上传成功");


        }

        @Override
        public void onFailed(int code, String message) {
            UploadState state = (UploadState) (mIndex == 1 ? mUploader1.getTag() : mUploader2.getTag());
            state.isError = true;

            release(mIndex, false);

            if(mOnUploadProgressChangeListener != null) {
                mOnUploadProgressChangeListener.onUpdate(state);
            }
            Logger.log("upload", (mIndex == 1 ? "Uploader1" : "Uploader2") + "上传失败 : " + message);

        }
    }

    public void registeUploadListener(OnUploadStateUpdateListener listener) {
        this.mOnUploadProgressChangeListener = listener;
    }

    /**
     * activity 退出时必须调用，否则可能造成内存泄漏
     */
    public void unRegisteUploadListener() {
        mOnUploadProgressChangeListener = null;
    }

    public void retry(UploadState state) {
        synchronized (LOCK) {
            if(state.isError) {
                mErrorList.remove(state.model);
                mWatingList.add(state.model);
                state.isError = false;
            }else {
                return;
            }
        }

        if(state.state < UploadState.STATE_COMPLETE) {
            upload();
        }else {
            publish(state.model);
        }
    }

    public void delete(UploadState state) {
        synchronized (LOCK) {
            mWatingList.remove(state.model);
            mCompleteList.remove(state.model);
            mErrorList.remove(state.model);

            int i = mUploadingList.indexOf(state.model);
            if(i == 0) {
                mUploader1.cancel();
                mUploader1.release();
                mUploader1 = null;
            }else if(i == 1) {
                mUploader2.cancel();
                mUploader2.release();
                mUploader2 = null;
            }
        }

        upload();
    }

    public List<UploadState> getUploadList() {
        synchronized (LOCK) {
            List<UploadState> list = new ArrayList<>();
            for (VideoModel model : mWatingList) {
                UploadState state = new UploadState();
                state.model = model;
                list.add(state);
            }

            for (VideoModel model : mCompleteList) {
                UploadState state = new UploadState();
                state.model = model;
                state.state = UploadState.STATE_COMPLETE;
                list.add(state);
            }

            for (VideoModel model : mErrorList) {
                UploadState state = new UploadState();
                state.model = model;
                state.isError = true;
                list.add(state);
            }

            if(mUploader1 != null) {
                list.add((UploadState) mUploader1.getTag());
            }
            if(mUploader2 != null) {
                list.add((UploadState) mUploader2.getTag());
            }

            Collections.sort(list, new Comparator<UploadState>() {
                @Override
                public int compare(UploadState o1, UploadState o2) {
                    return (int) (NumberUtil.parseLong(o1.model.getUploadDate()) - NumberUtil.parseLong(o2.model.getUploadDate()));
                }
            });
            return list;
        }
    }

    public interface OnUploadStateUpdateListener {

        void onUpdate(UploadState model);
    }


    public static final class UploadState {
        public static final int STATE_WAIT = 0;//队列等待
        public static final int STATE_BEGIN = 1;//开始上传
        public static final int STATE_PROGRESS = 2;//上传中
        public static final int STATE_COMPLETE = 3;//文件上传完成
        public static final int STATE_POST = 4;//视频信息提交完成

        private VideoModel model;

        private int state;

        private int progress;

        private boolean isError;

        public VideoModel getModel() {
            return model;
        }

        public void setModel(VideoModel model) {
            this.model = model;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public boolean isError() {
            return isError;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof UploadState && ((UploadState) obj).model.equals(model);
        }
    }
}

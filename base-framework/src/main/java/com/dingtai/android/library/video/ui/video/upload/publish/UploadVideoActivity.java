package com.dingtai.android.library.video.ui.video.upload.publish;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.event.UploadVideoEvent;
import com.dingtai.android.library.video.model.VideoChannelModel;
import com.dingtai.android.library.video.model.VideoModel;
import com.dingtai.android.library.video.ui.VideoNavigation;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.common.image.selecte.MdeiaHelper;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.ui.FrameworkNavigation;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarActivity;
import com.lnr.android.base.framework.ui.control.dialog.BottomMenu;
import com.lnr.android.base.framework.ui.control.dialog.BottomMenuHelper;
import com.lnr.android.base.framework.ui.control.dialog.MessageDialogHelper;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.OnTextChangeWatcher;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.FixImageView;
import com.lnr.android.base.framework.ui.control.view.adapterview.BaseAdapterViewAdapter;
import com.lnr.android.base.framework.uitl.FileUtil;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.lnr.android.base.framework.uitl.date.DateUtil;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/12/4
 * 上传视频
 */
@Contract(name = "UploadVideo")
@Route(path = "/video/video/upload/publish")
public class UploadVideoActivity extends ToolbarActivity implements UploadVideoContract.View {

    protected EditText editTitle, editContent;

    protected Button btnPublish;

    protected FixImageView imageView;

    protected CheckBox checkBox;

    protected AppCompatSpinner spinner;

    protected File mVideoFile;

    protected List<VideoChannelModel> models;

    @Inject
    protected UploadVideoPresenter mUploadVideoPresenter;

    protected BaseAdapterViewAdapter<VideoChannelModel> mBaseAdapterViewAdapter;

    protected VideoChannelModel mCurrentModel;

    @Override
    protected View contentView() {
        return View.inflate(this, R.layout.activity_upload_video, null);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mUploadVideoPresenter);
    }

    @Override
    protected void initView() {
        toolbar().setTitle("视频上传");
        editTitle = findViewById(R.id.text_title);
        editContent = findViewById(R.id.text_content);
        imageView = findViewById(R.id.FixImageView);
        checkBox = findViewById(R.id.checkbox);
        btnPublish = findViewById(R.id.btn_upload);

        spinner = findViewById(R.id.AppCompatSpinner);

        ViewListen.addTextChangeWatcher(new OnTextChangeWatcher.OnTextChangeListener() {
            @Override
            public void onChange(boolean isHasEmpty) {
                btnPublish.setEnabled(!isHasEmpty && mVideoFile != null && checkBox.isChecked());
            }
        }, editTitle, editContent);

        ViewListen.setClick(findViewById(R.id.btn_protocol), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                FrameworkNavigation.web(Resource.API.URL_PROTOCOL, "用户服务协议");
            }
        });

        ViewListen.setClick(imageView, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                showMenu();
            }
        });

        ViewListen.setClick(btnPublish, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                VideoModel model = new VideoModel();
                model.setID(UUID.randomUUID().toString());
                model.setName(editTitle.getText().toString());
                model.setDetail(editContent.getText().toString());
                model.setMediaUrl(mVideoFile.getAbsolutePath());
                model.setID2(mCurrentModel.getID());
                model.setUploadDate(System.currentTimeMillis() + "");

                String fileName = mVideoFile.getName();
                String uploadName = DateUtil.format(System.currentTimeMillis(), "yyyyMMddHHmmssSSS")
                        + "000000"
                        + fileName.substring(fileName.lastIndexOf("."));
                model.setUploadName(uploadName);
                RxBus.getDefault().post(new UploadVideoEvent(model));

                VideoNavigation.videoUploading(mActivity);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateBtn();
            }
        });

        mBaseAdapterViewAdapter = new BaseAdapterViewAdapter<VideoChannelModel>() {
            @Override
            protected View createView(ViewGroup parent, Context context, int type) {
                return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            @Override
            protected void convert(ViewHolder holder, int position, VideoChannelModel videoChannelModel) {
                TextView textView = holder.getView(android.R.id.text1);
                textView.setGravity(Gravity.CENTER);
                textView.setText(videoChannelModel.getName());
            }
        };

        spinner.setAdapter(mBaseAdapterViewAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCurrentModel = models.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected void updateBtn() {
        btnPublish.setEnabled(editTitle.getText().length() > 0
                && editContent.getText().length() > 0
                && mVideoFile != null && checkBox.isChecked());
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        mUploadVideoPresenter.getMediaChannelsList(null);
    }

    protected void showMenu() {
        BottomMenu menu = BottomMenuHelper.newNoTitle(this);
        if (mVideoFile != null) {
            menu.addMenuItem("播放视频", BottomMenu.MenuColor.Black, new BottomMenu.OnMenuClickListener() {
                @Override
                public void onClick() {
                    VideoNavigation.simplePlayer(PlayerModel.Builder.newBuilder(PlayerModel.TYPE_VOD)
                            .addUrls(FileUtil.contentPath(mVideoFile.getAbsolutePath()))
                            .setSize(PlayerModel.SIZE_FULL)
                            .build());
                }
            });
        }

        menu.addMenuItem("录制视频", BottomMenu.MenuColor.Black, new BottomMenu.OnMenuClickListener() {
            @Override
            public void onClick() {
                MdeiaHelper.recordViero(mActivity);
            }
        }).addMenuItem("选择视频", BottomMenu.MenuColor.Black, new BottomMenu.OnMenuClickListener() {
            @Override
            public void onClick() {
                requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .request(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if(aBoolean) {
                                    MdeiaHelper.selecteVideo(mActivity, 1);
                                }
                            }
                        });
            }
        });
        menu.show();
    }

    @Override
    public void getMediaChannelsList(List<VideoChannelModel> list) {
        if(list == null || list.isEmpty()) {
            MessageDialogHelper.show(mActivity, "类型错误!", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishActivity();
                }
            });
        }else {
            models = list;
            mCurrentModel = models.get(0);
            mBaseAdapterViewAdapter.clear();
            mBaseAdapterViewAdapter.addAll(models);
            mBaseAdapterViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case MdeiaHelper.REQUEST_CODE_RECORD:
                    String path = data.getStringExtra("RESULT_REOCRD_PATH");
                    if (TextUtils.isEmpty(path)) return;
                    mVideoFile = new File(path);
                    GlideHelper.load(imageView, mVideoFile);
                    updateBtn();
                    break;
                case MdeiaHelper.REQUEST_CODE_VIDEO:
                    List<String> result = MdeiaHelper.result(data);
                    if (result == null || result.isEmpty()) return;
                    mVideoFile = new File(result.get(0));
                    GlideHelper.load(imageView, mVideoFile);
                    updateBtn();
                    break;
            }
        }
    }
}

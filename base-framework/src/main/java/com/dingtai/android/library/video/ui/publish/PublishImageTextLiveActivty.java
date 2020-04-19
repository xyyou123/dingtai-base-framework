package com.dingtai.android.library.video.ui.publish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.model.PublishLiveTypeModel;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.upload.SimpleUploadActivity;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.control.dialog.MessageDialogHelper;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.OnTextChangeWatcher;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.ui.control.view.adapterview.FixGridView;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/11/9
 * 发布图文直播
 */
@Contract(name = "PublishImageTextLive")
@Route(path = "/video/publish/imagetext")
public class PublishImageTextLiveActivty extends SimpleUploadActivity implements PublishImageTextLiveContract.View, AdapterView.OnItemSelectedListener {

    protected AppCompatSpinner mTitleSpinner;
    protected TypeAdapter mTitleSpinnerAdapter;
    protected List<PublishLiveTypeModel> revelationClassModels;

    protected PublishLiveTypeModel mCurrentClass;

    @Inject
    protected PublishImageTextLivePresenter mPublishImageTextLivePresenter;

    protected EditText editContent;
    protected EditText editPhone;

    protected FixGridView mImageGridView;

    @Override
    protected View contentView() {
        return View.inflate(this, R.layout.activity_publish_image_text, null);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mPublishImageTextLivePresenter);
    }

    @Override
    protected void initView() {
        super.initView();

        FrameLayout frameLayout = new FrameLayout(this);

        ViewGroup titleLayout = toolbar().getTitleLayout();
        ViewGroup toolbar = (ViewGroup) titleLayout.getParent();

        int index = toolbar.indexOfChild(titleLayout);
        frameLayout.setLayoutParams(titleLayout.getLayoutParams());
        toolbar.removeView(titleLayout);
        toolbar.addView(frameLayout, index);

        mTitleSpinner = new AppCompatSpinner(this);
        mTitleSpinner.setGravity(Gravity.CENTER_HORIZONTAL);

        frameLayout.addView(mTitleSpinner, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));

        toolbar().setRightText("发布");
        toolbar().getRightLayout().setEnabled(false);
        toolbar().getRightText().setTextColor(getResources().getColor(R.color.theme_shallow));
        toolbar().setRightListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                publish();
            }
        });

        editContent = findViewById(R.id.text_content);
        editPhone = findViewById(R.id.text_phone);
        editPhone.setText(AccountHelper.getInstance().getUser().getUserPhone());

        ViewListen.setClick(findViewById(R.id.btn_pic), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                clickImageMenu();
            }
        });

        ViewListen.setClick(findViewById(R.id.btn_audio), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                voice2word();
            }
        });

        ViewListen.setClick(findViewById(R.id.btn_video), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                clickVideoMenu();
            }
        });

        ViewListen.addTextChangeWatcher(new OnTextChangeWatcher.OnTextChangeListener() {
            @Override
            public void onChange(boolean isHasEmpty) {
                toolbar().getRightLayout().setEnabled(!isHasEmpty);
                toolbar().getRightText().setTextColor(getResources().getColor(isHasEmpty ? R.color.theme_shallow : R.color.theme));
            }
        }, editContent, editPhone);


        mImageGridView = findViewById(R.id.FixGridView);
        mImageGridView.setAdapter(getMediaAdapter());
        mImageGridView.setOnItemClickListener(getMediaAdapter().getSimpleItemClickListener());
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        mTitleSpinnerAdapter = new TypeAdapter();
        mPublishImageTextLivePresenter.getPublishImageTextLiveType();
    }

    @Override
    public void getPublishImageTextLiveType(List<PublishLiveTypeModel> list) {
        if (list != null && !list.isEmpty()) {
            revelationClassModels = list;
            mTitleSpinnerAdapter.addAll(revelationClassModels);
            mTitleSpinner.setAdapter(mTitleSpinnerAdapter);
            mTitleSpinner.setDropDownWidth(mTitleSpinner.getWidth());
            mTitleSpinner.setOnItemSelectedListener(this);
            onItemSelected(mTitleSpinner, null, 0, 0);

            mTitleSpinner.performClick();
        } else {
            MessageDialogHelper.show(this, "未获取到任何信息，请稍后再试!", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishActivity();
                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        PublishLiveTypeModel model = revelationClassModels.get(position);
        if (model == null) return;
        mCurrentClass = model;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onVoice2WordResult(String content) {
        editContent.append(content);
    }

    /**
     * 发布
     */
    protected void publish() {
        getLoadingDialog().setTitle("正在上传文件...");

        String content = editContent.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();

        if (content.isEmpty()) {
            MessageDialogHelper.show(mActivity, "内容不可为空！");
            return;
        }

        if (content.length() < 5) {
            MessageDialogHelper.show(mActivity, "内容长度不可小于5个字符！");
            return;
        }

//        if (phone.isEmpty()) {
//            MessageDialogHelper.show(mActivity, "手机号不可为空！");
//            return;
//        }
//
//        if (phone.length() < 11) {
//            MessageDialogHelper.show(mActivity, "手机号格式有误！");
//            return;
//        }

        HashMap<String, Object> uploadPaths = getUploadPaths();
        String imagePaths = (String) uploadPaths.get("image");
        String videoPaths = (String) uploadPaths.get("video");

        List<String> paths = (List<String>) uploadPaths.get("all");

        String RevelationType;
        if (!TextUtils.isEmpty(imagePaths) && !TextUtils.isEmpty(videoPaths)) {
            //有图片 并且有视频或者语音
            RevelationType = "4";
        } else if (!TextUtils.isEmpty(imagePaths)) {
            //只有图片
            RevelationType = "2";
        } else if (!TextUtils.isEmpty(videoPaths)) {
            //有视频
            RevelationType = "3";
        } else {
            RevelationType = "1";
        }

        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        String a = String.valueOf(y);
        int m = cal.get(Calendar.MONTH);
        String b;
        if (m < 10) {
            b = "0" + String.valueOf(m);
        } else {
            b = String.valueOf(m);
        }

        int d = cal.get(Calendar.DAY_OF_MONTH);
        String c = String.valueOf(d);
        String f = a + b + c;
        mPublishImageTextLivePresenter.publish(mCurrentClass.getID(), RevelationType, null, content, imagePaths, videoPaths,
                f, paths);
    }

    @Override
    public void publish(boolean result, String message) {
        hideLoading();
        if (result) {
            MessageDialogHelper.show(mActivity, "发布成功，请等待管理员审核。", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setResult(RESULT_OK);
                    finishActivity();
                }
            });
        } else {
            ToastHelper.toastError("发布失败");
        }
    }

    @Override
    public void uploadFileSucceed() {
        getLoadingDialog().setTitle("文件上传成功，正在发布...");
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
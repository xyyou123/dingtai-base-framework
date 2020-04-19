package com.lnr.android.base.framework.ui.control.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.uitl.ContextUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * author:lnr
 * date:2018/6/5
 */

public class Toolbar extends LinearLayout {

    private ViewGroup mLeftLayout;
    private ImageView mLeftImage;
    private ImageView mLeftImageSingle;
    private TextView mLeftText;

    private ViewGroup mTitleLayout;
    private TextView mTitle;
    private TextView mSubTitle;

    private ViewGroup mRightLayout;
    private ImageView mRightImage;
    private TextView mRightText;

    private List<View> mRightList;

    public Toolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        inflate(context, R.layout.layout_toolbar, this);
        setId(R.id.toolbar);
        setBackgroundResource(R.color.toolbar);

        mRightList = new ArrayList<>();

        mLeftLayout = findViewById(R.id.toolbar_left_layout);
        mLeftImage = findViewById(R.id.toolbar_left_img);
        mLeftImage.setImageResource(Resource.Drawable.TOOLBAR_BACK);
        mLeftImageSingle = findViewById(R.id.toolbar_left_img_single);
        mLeftImageSingle.setImageResource(Resource.Drawable.TOOLBAR_BACK);
        mLeftText = findViewById(R.id.toolbar_left_text);

        mTitleLayout = findViewById(R.id.toolbar_title_layout);
        mTitle = findViewById(R.id.toolbar_title);
        mSubTitle = findViewById(R.id.toolbar_subtitle);

        mRightLayout = findViewById(R.id.toolbar_right_layout);
        mRightImage = findViewById(R.id.toolbar_right_img);
        mRightText = findViewById(R.id.toolbar_right_text);

        setLeftVisibility(false, false, false);
        setRightVisibility(true, true, false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(getContext().getResources().getDimensionPixelOffset(R.dimen.dp_44), MeasureSpec.EXACTLY));
    }

    public void setLeftListener(com.lnr.android.base.framework.ui.control.listener.OnClickListener listener) {
        mLeftImage.setVisibility(VISIBLE);
        mLeftImageSingle.setVisibility(GONE);

        mLeftText.setOnClickListener(null);
        mLeftImageSingle.setOnClickListener(null);
        ViewListen.setClick(mLeftLayout, listener);
    }

    public void setLeftListener(com.lnr.android.base.framework.ui.control.listener.OnClickListener icon,
                                com.lnr.android.base.framework.ui.control.listener.OnClickListener text) {
        mLeftImage.setVisibility(GONE);
        mLeftImageSingle.setVisibility(VISIBLE);

        mLeftLayout.setOnClickListener(null);
        mLeftLayout.setClickable(false);
        ViewListen.setClick(mLeftImageSingle, icon);
        ViewListen.setClick(mLeftText, text);
    }

    public void setRightListener(com.lnr.android.base.framework.ui.control.listener.OnClickListener listener) {
        ViewListen.setClick(mRightLayout, listener);
    }

    public void setLeftVisibility(boolean layout, boolean img, boolean text) {
        mLeftLayout.setVisibility(layout ? VISIBLE : INVISIBLE);
        mLeftImage.setVisibility(img ? VISIBLE : GONE);
        mLeftText.setVisibility(text ? VISIBLE : GONE);
    }

    public void setRightVisibility(boolean layout, boolean img, boolean text) {
        mRightLayout.setVisibility(layout ? VISIBLE : INVISIBLE);
        mRightImage.setVisibility(img ? VISIBLE : GONE);
        mRightText.setVisibility(text ? VISIBLE : GONE);
    }

    public void setLeftImage(int res) {
        mLeftImage.setImageResource(res);
        mLeftImageSingle.setImageResource(res);
        setLeftVisibility(true, true, true);
    }

    public void setRightImage(int res) {
        setRightVisibility(true, true, false);
        mRightImage.setImageResource(res);
    }

    public void addRightImage(int res, com.lnr.android.base.framework.ui.control.listener.OnClickListener listener) {
        mRightLayout.removeView(mRightText);

        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new LayoutParams(ContextUtil.getDimen(R.dimen.dp_32), ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(res);
        imageView.setPadding(ContextUtil.getDimen(R.dimen.dp_8), 0, 0, 0);
        ViewListen.setClick(imageView, listener);

        mRightLayout.addView(imageView, 0);
    }

    public void setLeftText(String text) {
        mLeftText.setText(text);
    }



    public void setRightText(String text) {
        setRightVisibility(true, false, true);
        mRightText.setText(text);
    }

    public void setTitle(String text) {
        mTitle.setText(text);
    }

    public void setSubTitle(String text) {
        mSubTitle.setVisibility(VISIBLE);
        mSubTitle.setText(text);
    }

    public void setLeftEnable(boolean enable) {
        mLeftLayout.setEnabled(enable);
        mLeftImage.setEnabled(enable);
        mLeftText.setEnabled(enable);
    }

    public void setRightEnable(boolean enable) {
        mRightLayout.setEnabled(enable);
        mRightImage.setEnabled(enable);
        mRightText.setEnabled(enable);
    }

    public ViewGroup getLeftLayout() {
        return mLeftLayout;
    }

    public ImageView getLeftImage() {
        return mLeftImage;
    }

    public TextView getLeftText() {
        return mLeftText;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public ViewGroup getTitleLayout() {
        return mTitleLayout;
    }

    public TextView getSubTitle() {
        return mSubTitle;
    }

    public ViewGroup getRightLayout() {
        return mRightLayout;
    }

    public ImageView getRightImage() {
        return mRightImage;
    }

    public TextView getRightText() {
        return mRightText;
    }


    public void setBuleColor(TextView tv) {
        tv.setTextColor(R.drawable.textcolor_button);
    }

    public int getToolbarHeight() {
        return getPaddingTop() + getPaddingBottom() + getResources().getDimensionPixelOffset(R.dimen.dp_44);
    }
}

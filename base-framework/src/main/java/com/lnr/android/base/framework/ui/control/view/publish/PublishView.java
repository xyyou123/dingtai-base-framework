package com.lnr.android.base.framework.ui.control.view.publish;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dingtai.android.library.model.models.ADModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;

import java.util.List;

/**
 * author:lnr
 * date:2018/12/19
 */
public class PublishView extends android.support.v7.widget.AppCompatImageView {

    private PublishDialog mDialog;
    private List<ADModel> adModels;

    public PublishView(Context context) {
        super(context);
    }

    public PublishView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PublishView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PublishView);
        mDialog = new PublishDialog(getContext());
        mDialog.setIcons(a.getResourceId(R.styleable.PublishView_icon_left, 0),
                a.getResourceId(R.styleable.PublishView_icon_center, 0),
                a.getResourceId(R.styleable.PublishView_icon_right, 0));
        mDialog.setTexts(a.getString(R.styleable.PublishView_text_left),
                a.getString(R.styleable.PublishView_text_center),
                a.getString(R.styleable.PublishView_text_right));

        mDialog.setCloseIcon(a.getResourceId(R.styleable.PublishView_icon_close, 0));

        a.recycle();

        mDialog.setMenu(true, true, true);

        ViewListen.setClick(this, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                mDialog.show(adModels);
            }
        });
    }

    public void setMenu(boolean left, boolean center, boolean right) {
        mDialog.setMenu(left, center, right);
    }

    public void setOnClickMenuListener(PublishDialog.OnClickMenuListener onClickMenuListener) {
        mDialog.setOnClickMenuListener(onClickMenuListener);
    }

    public void updateAdList(List<ADModel> adModels) {
        this.adModels = adModels;
    }
}

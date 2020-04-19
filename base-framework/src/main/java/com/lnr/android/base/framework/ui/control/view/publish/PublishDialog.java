package com.lnr.android.base.framework.ui.control.view.publish;

import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dingtai.android.library.model.models.ADModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.dialog.AbstractBottomDialog;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.lnr.android.base.framework.uitl.DimenUtil;

import java.util.List;

/**
 * author:lnr
 * date:2018/11/9
 */
public class PublishDialog extends AbstractBottomDialog implements DialogInterface.OnShowListener {

    private ImageView closeView;

    private TextView textLeft, textMid, textRight;
    private ImageView imageLeft, imageMid, imageRight;

    private View layoutLeft, layoutMid, layoutRight;

    private int mIconLeft, mIconCenter, mIconRight;
    private String mTextLeft, mTextCenter, mTextRight;

    private int mCloseIcon;

    private DialogAnim mCloseViewShowAnim;
    private DialogAnim mCloseViewDismissAnim;

    private OvershootInterpolator mAnimInterpolator;
    private DecelerateInterpolator mCloseAnimAnimInterpolator;

    private boolean[] mShowMenus;
    private int tranX, tranY, textHeight;

    private OnClickMenuListener mOnClickMenuListener;

    private AdAdapter mAdAdapter;

    public PublishDialog(@NonNull Context context) {
        super(context);
        mShowMenus = new boolean[3];
        setOnShowListener(this);
    }

    public void setOnClickMenuListener(OnClickMenuListener onClickMenuListener) {
        this.mOnClickMenuListener = onClickMenuListener;
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_publish_dialog;
    }

    @Override
    protected void initView(View view) {
        closeView = view.findViewById(R.id.btn_close);

        textLeft = view.findViewById(R.id.text_left);
        textMid = view.findViewById(R.id.text_mid);
        textRight = view.findViewById(R.id.text_right);

        layoutLeft = view.findViewById(R.id.layout_left);
        layoutMid = view.findViewById(R.id.layout_mid);
        layoutRight = view.findViewById(R.id.layout_right);

        imageLeft = view.findViewById(R.id.image_left);
        imageMid = view.findViewById(R.id.image_mid);
        imageRight = view.findViewById(R.id.image_right);

        closeView.setImageResource(mCloseIcon);

        ViewListen.setClick(closeView, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View view) {
                dismiss();
            }
        });

        ViewListen.setClick(layoutLeft, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View view) {
                dismiss();
                if(mOnClickMenuListener != null) {
                    mOnClickMenuListener.onClickLeftMenu();
                }
            }
        });

        ViewListen.setClick(layoutMid, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View view) {
                dismiss();
                if(mOnClickMenuListener != null) {
                    mOnClickMenuListener.onClickMidMenu();
                }
            }
        });

        ViewListen.setClick(layoutRight, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View view) {
                dismiss();
                if(mOnClickMenuListener != null) {
                    mOnClickMenuListener.onClickRightMenu();
                }
            }
        });

        closeView.measure(0, 0);
        layoutLeft.measure(0, 0);
        textLeft.measure(0, 0);

        textHeight = textLeft.getMeasuredHeight() + DimenUtil.dp2px(getContext(), 4);

        int[] screenSize = DimenUtil.getScreenSize(getContext());
        tranX = (int) (screenSize[0] * 0.27f);
        tranY = (int) (tranX * 0.8f);

        View layout = findViewById(R.id.layout_btns);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (tranY * 1.2 + layoutLeft.getMeasuredHeight() + closeView.getMeasuredHeight() + layout.getPaddingBottom())
        ));

        mCloseViewShowAnim = new DialogAnim() {
            @Override
            public void onAnimationEnd(Animator animation) {
                textLeft.setVisibility(View.VISIBLE);
                textMid.setVisibility(View.VISIBLE);
                textRight.setVisibility(View.VISIBLE);
            }
        };

        mCloseViewDismissAnim = new DialogAnim() {
            @Override
            public void onAnimationEnd(Animator animation) {
                dismiss(false);
            }
        };

        mAnimInterpolator = new OvershootInterpolator();
        mCloseAnimAnimInterpolator = new DecelerateInterpolator();

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManagerWrapper(getContext()));
        recyclerView.setAdapter(mAdAdapter);
    }

    public void setIcons(int left, int center, int right) {
        mIconLeft = left;
        mIconCenter = center;
        mIconRight = right;
    }

    public void setTexts(String left, String center, String right) {
        mTextLeft = left;
        mTextCenter = center;
        mTextRight = right;
    }

    public void setCloseIcon(int icon) {
        mCloseIcon = icon;
    }

    public void setMenu(boolean left, boolean center, boolean right) {
        mShowMenus[0] = left;
        mShowMenus[1] = center;
        mShowMenus[2] = right;
    }

    @Override
    protected int[] getDialogSize(DisplayMetrics displayMetrics) {
        return new int[]{displayMetrics.widthPixels, ViewGroup.LayoutParams.MATCH_PARENT};
    }

    @Override
    protected int getWindowAnimations() {
        return 0;
    }

    public void show(List<ADModel> adModels) {
        if(mAdAdapter == null) {
            mAdAdapter = new AdAdapter();
        }
        mAdAdapter.setNewData(adModels);
        show();
    }

    @Override
    public void dismiss() {
        dismiss(true);
    }

    private void dismiss(boolean anim) {
        if(anim && isCreated()) {
            dismissAnim();
        }else {
            super.dismiss();
        }
    }

    private void dismissAnim() {
        closeView.animate().rotation(0).setDuration(500).setListener(mCloseViewDismissAnim);

        if(mShowMenus[0] && mShowMenus[1] && mShowMenus[2]) {
            textLeft.setVisibility(View.INVISIBLE);
            textMid.setVisibility(View.INVISIBLE);
            textRight.setVisibility(View.INVISIBLE);

            dismissAnim(layoutLeft.animate());
            dismissAnim(layoutMid.animate());
            dismissAnim(layoutRight.animate());
        }else if(mShowMenus[0] && mShowMenus[1]) {
            textLeft.setVisibility(View.INVISIBLE);
            textMid.setVisibility(View.INVISIBLE);
            textRight.setVisibility(View.INVISIBLE);

            dismissAnim(layoutLeft.animate());
            dismissAnim(layoutMid.animate());
        }else if(mShowMenus[0] && mShowMenus[2]) {
            textLeft.setVisibility(View.INVISIBLE);
            textMid.setVisibility(View.INVISIBLE);
            textRight.setVisibility(View.INVISIBLE);

            dismissAnim(layoutLeft.animate());
            dismissAnim(layoutRight.animate());
        }else if(mShowMenus[1] && mShowMenus[2]) {
            textLeft.setVisibility(View.INVISIBLE);
            textMid.setVisibility(View.INVISIBLE);
            textRight.setVisibility(View.INVISIBLE);

            dismissAnim(layoutMid.animate());
            dismissAnim(layoutRight.animate());
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if(!isCreated()) return;

        textLeft.setText(mTextLeft);
        textMid.setText(mTextCenter);
        textRight.setText(mTextRight);

        imageLeft.setImageResource(mIconLeft);
        imageMid.setImageResource(mIconCenter);
        imageRight.setImageResource(mIconRight);

        int tx = tranX;
        closeView.animate().rotation(-45).setDuration(500).setListener(mCloseViewShowAnim);

        if(mShowMenus[0] && mShowMenus[1] && mShowMenus[2]) {
            textLeft.setVisibility(View.INVISIBLE);
            textMid.setVisibility(View.INVISIBLE);
            textRight.setVisibility(View.INVISIBLE);

            layoutLeft.setVisibility(View.VISIBLE);
            layoutMid.setVisibility(View.VISIBLE);
            layoutRight.setVisibility(View.VISIBLE);

            showAnim(layoutLeft.animate(), tx);
            showAnim(layoutMid.animate(), 0);
            showAnim(layoutRight.animate(), -tx);
        }else if(mShowMenus[0] && mShowMenus[1]) {
            textLeft.setVisibility(View.INVISIBLE);
            textMid.setVisibility(View.INVISIBLE);
            textRight.setVisibility(View.INVISIBLE);

            layoutLeft.setVisibility(View.VISIBLE);
            layoutMid.setVisibility(View.VISIBLE);
            layoutRight.setVisibility(View.INVISIBLE);

            tx = (int) (tx / 2f);
            showAnim(layoutLeft.animate(), -tx);
            showAnim(layoutMid.animate(), tx);
        }else if(mShowMenus[0] && mShowMenus[2]) {
            textLeft.setVisibility(View.INVISIBLE);
            textMid.setVisibility(View.INVISIBLE);
            textRight.setVisibility(View.INVISIBLE);

            layoutLeft.setVisibility(View.VISIBLE);
            layoutMid.setVisibility(View.GONE);
            layoutRight.setVisibility(View.VISIBLE);

            tx = (int) (tx / 2f);
            showAnim(layoutLeft.animate(), -tx);
            showAnim(layoutRight.animate(), tx);
        }else if(mShowMenus[1] && mShowMenus[2]) {
            textLeft.setVisibility(View.INVISIBLE);
            textMid.setVisibility(View.INVISIBLE);
            textRight.setVisibility(View.INVISIBLE);

            layoutLeft.setVisibility(View.GONE);
            layoutMid.setVisibility(View.VISIBLE);
            layoutRight.setVisibility(View.VISIBLE);

            tx = (int) (tx / 2f);
            showAnim(layoutMid.animate(), -tx);
            showAnim(layoutRight.animate(), tx);
        }
    }

    private void showAnim(ViewPropertyAnimator animator, int tx) {
        if(tx != 0) {
            animator = animator.translationX(tx);
        }
        animator.translationY(-tranY)
                .translationX(tx)
                .scaleX(1.2f).scaleY(1.2f).setDuration(500).setInterpolator(mAnimInterpolator).start();
    }

    private void dismissAnim(ViewPropertyAnimator animator) {
        animator.scaleX(1f).scaleY(1f).translationX(0).translationY(textHeight).setInterpolator(mCloseAnimAnimInterpolator).setDuration(500);
    }

    private abstract static class DialogAnim implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

    public interface OnClickMenuListener {
        void onClickLeftMenu();
        void onClickMidMenu();
        void onClickRightMenu();
    }

    private static class AdAdapter extends BaseAdapter<ADModel> {
        @Override
        protected ItemConverter<ADModel> createItemConverter(int i) {
            return new ItemConverter<ADModel>() {
                @Override
                public int layoutId() {
                    return R.layout.item_publish_dialog;
                }

                @Override
                public void convert(BaseViewHolder baseViewHolder, int i, ADModel adModel) {
                    GlideHelper.load(baseViewHolder.getView(R.id.item_icon), adModel.getImgUrl());
                }
            };
        }
    }
}

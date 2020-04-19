package com.lnr.android.base.framework.common.comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.dialog.AbstractBottomDialog;
import com.lnr.android.base.framework.ui.control.dialog.MessageDialog;
import com.lnr.android.base.framework.ui.control.dialog.MessageDialogHelper;
import com.lnr.android.base.framework.ui.control.listener.OnTextChangeWatcher;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018/10/11
 */
public class CommentBottomDialog extends AbstractBottomDialog {

    public static boolean ENABLE_JUDGE_TEXT_LENGTH = false;
    public static int MAX_TEXT_LENGTH = 500;
    public static String JUDGE_TEXT_HINT = "评论超过字数限制";

    private TextView mEditText;
    private TextView mSubmit;
    private OnSubmitListener submitListener;
    private String hint;

    private HashMap<String, String> mCommentContentCacheMap;
    private String mCurrentCommentToId;

    public CommentBottomDialog(Context context, @NonNull OnSubmitListener submitListener) {
        super(context, R.style.CommentDialog);
        this.submitListener = submitListener;
        mCommentContentCacheMap = new HashMap<>();
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_comment_edit;
    }

    @Override
    protected void initView(View view) {
        mEditText = view.findViewById(R.id.action_bar_edittext);
        mEditText.setHint(hint);
        mEditText.setText(mCommentContentCacheMap.get(mCurrentCommentToId));
        mSubmit = view.findViewById(R.id.action_bar_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditText.getText().toString();
                if (ENABLE_JUDGE_TEXT_LENGTH) {

                    if (!TextUtils.isEmpty(content)&& content.length()>MAX_TEXT_LENGTH) {
                        ToastHelper.toastDefault(JUDGE_TEXT_HINT);
                        return;
                    }

                }

                dismiss();



                if(submitListener.onSubnit(content) && mEditText != null) {
                    mEditText.setText(null);
                }
            }
        });

        ViewListen.addTextChangeWatcher(new OnTextChangeWatcher.OnTextChangeListener() {
            @Override
            public void onChange(boolean isHasEmpty) {
                mSubmit.setEnabled(!isHasEmpty);
            }
        }, mEditText);
    }

    public interface OnSubmitListener {
        boolean onSubnit(String content);
    }

    public String getText() {
        return mEditText != null ? mEditText.getText().toString() : null;
    }

    public void show(String id, String hint) {
        if(mCurrentCommentToId != null) {
            mCommentContentCacheMap.put(mCurrentCommentToId, mEditText.getText().toString());
        }

        mCurrentCommentToId = id;
        this.hint = hint;
        if(mEditText != null) {
            mEditText.setText(mCommentContentCacheMap.get(mCurrentCommentToId));
            mEditText.setHint(hint);
        }
        show();
    }

    @Override
    public void dismiss() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        }
        super.dismiss();
    }

    @Override
    protected int getWindowAnimations() {
        return 0;
    }

    public String getToId() {
        return mCurrentCommentToId;
    }
}
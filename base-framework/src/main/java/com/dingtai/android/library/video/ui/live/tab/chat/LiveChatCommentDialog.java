package com.dingtai.android.library.video.ui.live.tab.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.dialog.AbstractBottomDialog;
import com.lnr.android.base.framework.ui.control.listener.OnTextChangeWatcher;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.uitl.ContextUtil;

public class LiveChatCommentDialog extends AbstractBottomDialog {

    private TextView mEditText;
    private TextView mSubmit;
    private OnSubmitListener submitListener;

    public LiveChatCommentDialog(Context context, @NonNull OnSubmitListener submitListener) {
        super(context);
        this.submitListener = submitListener;
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_live_chat_comment;
    }

    @Override
    protected void initView(View view) {
        mEditText = view.findViewById(R.id.action_bar_edittext);

        mSubmit = view.findViewById(R.id.action_bar_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                submitListener.onSubnit(mEditText.getText().toString());
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

    @Override
    public void show() {
        super.show();

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(mEditText, InputMethodManager.SHOW_FORCED);
        }
    }

    @Override
    public void dismiss() {
        ContextUtil.hideSoftInput(mEditText);
        super.dismiss();
    }
}

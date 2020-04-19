package com.dingtai.android.library.news.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.news.DaggerNewsDagger;

import com.dingtai.android.library.news.ui.NewsNavigation;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.OnTextChangeWatcher;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.FlowLayout;
import com.lnr.android.base.framework.uitl.ContextUtil;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.lnr.android.base.framework.uitl.SP;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/27
 */
@Route(path = "/news/search")
@Contract(name = "NewsSearch")
public class NewsSearchActivity extends ToolbarActivity implements NewsSearchContract.View {

    protected static final String SEPARATOR = "@";

    @Inject
    protected NewsSearchPresenter mNewsSearchPresenter;

    protected FlowLayout flowLayout;

    protected EditText editSearch;

    protected ImageView imageDelete;

    protected View emptyView;

    protected Button btnSearch;

    @Override
    protected View contentView() {
        return View.inflate(this, R.layout.activity_news_search, null);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mNewsSearchPresenter);
    }

    @Override
    protected void initView() {
        toolbar().setTitle("搜索");

        flowLayout = findViewById(R.id.FlowLayout);
        flowLayout.setAutoSetOnclickListener(false);
        editSearch = findViewById(R.id.edit_search);
        imageDelete = findViewById(R.id.image_delete);
        emptyView = findViewById(R.id.text_history_empty);
        btnSearch = findViewById(R.id.btn_search);

        ViewListen.setClick(imageDelete, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                SP.getDefault().edit().remove("search_history").apply();
                flowLayout.setVisibility(View.GONE);
                flowLayout.removeAllViews();
                emptyView.setVisibility(View.VISIBLE);
            }
        });

        ViewListen.addTextChangeWatcher(new OnTextChangeWatcher.OnTextChangeListener() {
            @Override
            public void onChange(boolean b) {
                btnSearch.setEnabled(!b);
            }
        }, editSearch);

        ViewListen.setClick(btnSearch, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                search();
            }
        });

        editSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getAction() == KeyEvent.KEYCODE_ENTER)) {
                    search();
                    return true;
                }
                return false;
            }
        });
    }

    protected void search() {
        String data = editSearch.getText().toString();
        if(data.length() == 0) return;
        String history = SP.getDefault().getString("search_history", null);
        if(history == null) {
            SP.getDefault().edit().putString("search_history", data).apply();
        }else if(!history.contains(data)){
            SP.getDefault().edit().putString("search_history", history + SEPARATOR + data).apply();
            flowLayout.addView(createFlowItem(data));
        }

        if(flowLayout.getVisibility() != View.VISIBLE) {
            flowLayout.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        NewsNavigation.searchResult(data);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        mNewsSearchPresenter.getNewsKeyWord();

        String history = SP.getDefault().getString("search_history", null);
        if(history == null) {
            emptyView.setVisibility(View.VISIBLE);
        }else {
            String[] strings = history.split(SEPARATOR);
            for (String key : strings) {
                flowLayout.addView(createFlowItem(key));
            }

            flowLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getNewsKeyWord(String key) {
        if(key != null && key.length() > 0) {
            editSearch.setHint("大家都在搜：" + key);
        }
    }

    protected View createFlowItem(final String key) {
        TextView textView = (TextView) View.inflate(this, R.layout.view_search_flow_item, null);
        textView.setText(key);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = ContextUtil.getDimen(R.dimen.dp_4);
        params.setMargins(margin, margin, margin, margin);
        textView.setLayoutParams(params);
        ViewListen.setClick(textView, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                editSearch.setText(key);
                editSearch.setSelection(key.length());
                InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(editSearch, InputMethodManager.SHOW_FORCED);
                }
            }
        });
        return textView;
    }
}

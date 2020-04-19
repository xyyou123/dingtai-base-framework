package com.lnr.android.base.framework.ui.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;
import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.FrameworkApplication;
import com.lnr.android.base.framework.app.ActivityStack;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IDigest;
import com.lnr.android.base.framework.mvp.view.IView;
import com.lnr.android.base.framework.rx.RxDisposable;
import com.lnr.android.base.framework.ui.FrameworkNavigation;
import com.lnr.android.base.framework.ui.control.dialog.LoadingDialog;
import com.lnr.android.base.framework.ui.control.permission.PermissionRequest;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.uitl.ImmersionBarUtil;
import com.lnr.android.base.framework.uitl.logger.Logger;
import com.lnr.android.base.framework.uitl.net.NetworkUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
/**
 * author:lnr
 * date:2018/5/10
 * activity基类
 */

public abstract class BaseActivity extends RxAppCompatActivity implements IDigest, IView {

    public static String NETWORK_ERROR_HINT = "";
    /**
     * 加载框
     */
    Dialog mLoadingDialog;

    /**
     * disposable管理者
     */
    protected RxDisposable mRxDisposable;

    /**
     * 权限申请器
     */
    private PermissionRequest mPermissionRequest;

    /**
     * 类本身
     */
    protected BaseActivity mActivity;
    /**
     * 沉浸式处理
     */
    protected ImmersionBar mImmersionBar;

    protected ArrayList<Fragment> mFragmentList;
    protected Fragment mCurrentFragment;
    protected int mFragmentContainer;

    @Autowired
    protected String toolbarTitile;

    /**
     * 创建加载框
     * @return 加载框
     */
    protected Dialog createLoadingDialog() {
        return new LoadingDialog(this);
    }

    /**
     * 显示加载框
     */
    public void showLoading(){
        if(mLoadingDialog != null && !mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    /**
     * 隐藏加载框
     */
    public void hideLoading() {
        if(mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    public void updateProgress(int progress) {
        if(mLoadingDialog instanceof ProgressDialog) {
            ((ProgressDialog) mLoadingDialog).setProgress(progress);
        }
    }

    /**
     * Presenter
     * @return Presenter
     */
    protected abstract List<IPresenter> getIPresenters();

    /**
     * 绑定mvp
     * @param view v
     */
    private void bindPresenter(IView view) {
        List<IPresenter> presenters = getIPresenters();

        if(presenters == null || presenters.isEmpty()) return;
        for (IPresenter presenter : presenters) {
            if(presenter == null) {
                Logger.log("bindPresenter", getClass().getSimpleName() + " bindPresenter presenter == null");
                continue;
            }
            presenter.bindView(view);
        }
    }

    /**
     * 解绑mvp
     */
    protected final void unBindPresenter() {
        List<IPresenter> presenters = getIPresenters();

        if(presenters == null || presenters.isEmpty()) return;
        for (IPresenter presenter : presenters) {
            if(presenter == null) {
                Logger.log("unBindPresenter", getClass().getSimpleName() + " unBindPresenter presenter == null");
                continue;
            }
            presenter.unBindView();
        }
    }

    /*  -----------------   生命周期  start  -----------------   */

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        beforeSetContentView(savedInstanceState);
        mRxDisposable = new RxDisposable();
        //阿里注入初始化
        ARouter.getInstance().inject(this);
        inject(Framework.getInstance().getApplicationComponent());
        //创建权限申请器
        mPermissionRequest = PermissionRequest.with(this);
        setContentView();
        mLoadingDialog = createLoadingDialog();
        //初始化沉浸式工具
        initImmersionBar(isImmersion());
        initView();
        bindPresenter(this);
        afterInitView(savedInstanceState);
        initOtherInfo();
    }

    protected abstract void setContentView();

    protected void beforeSetContentView(@Nullable Bundle savedInstanceState) {
    }

    protected void initOtherInfo() {

    }

    /**
     * 是否沉浸式
     * @return 是否启动沉浸式
     */
    protected boolean isImmersion() {
        return false;
    }

    /**
     * 初始化沉浸式工具 此处可自定义实现
     * @param immersion 是否沉浸式
     */
    protected void initImmersionBar(boolean immersion) {
        if(immersion) {
            mImmersionBar = ImmersionBarUtil.buildImmersion(this);
        }else {
            mImmersionBar = ImmersionBarUtil.buildNotImmersion(this);
        }
        mImmersionBar.init();
    }

    /**
     * 摘要
     * @return 页面摘要
     */
    @Override
    public String digest() {
        return null;
    }

    protected Dialog getLoadingDialog() {
        return mLoadingDialog;
    }

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 添加dagger注入
     * @param component 全局ApplicationComponent
     */
    protected abstract void inject(ApplicationComponent component);

    /**
     * view初始化完成之后调用
     * @param savedInstanceState 页面恢复数据
     */
    protected abstract void afterInitView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化fragments
     * @param layout 布局id
     * @param fragments fragment
     */
    protected void initFragments(int layout, Fragment... fragments) {
        if(mFragmentList == null) {
            mFragmentList = new ArrayList<>();
        }
        mFragmentContainer = layout;
        mFragmentList.addAll(Arrays.asList(fragments));
    }

    /**
     * 显示fragment
     * @param position 下标
     */
    protected void showFragment(int position) {
        if(mFragmentList == null) {
            return;
        }

        Fragment fragment = mFragmentList.get(position);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mCurrentFragment != null) {
            ft.hide(mCurrentFragment);
        }
        if(fragment.isAdded()) {
            ft.show(fragment);
        }else {
            ft.add(mFragmentContainer, fragment);
            ft.show(fragment);
        }
        ft.commit();
        mCurrentFragment = fragment;
    }

    protected void replaceFragment(int layoutId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(layoutId, fragment).commit();
    }



    @Override
    protected void onDestroy() {
        unBindPresenter();
        if(mRxDisposable != null) mRxDisposable.clear();
        if(mPermissionRequest != null) mPermissionRequest.recycle();
        if(mImmersionBar != null) mImmersionBar.destroy();
        mPermissionRequest = null;
        UMShareAPI.get(this).release();
        super.onDestroy();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /*  -----------------   生命周期   end   -----------------   */

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale > FrameworkApplication.MAX_FONT)
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale > FrameworkApplication.MAX_FONT) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            newConfig.fontScale = FrameworkApplication.MAX_FONT;
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    protected <T> RxDisposable registe(Class<T> tClass, Consumer<T> action) {
        return mRxDisposable.registe(tClass, action);
    }

    protected RxDisposable registe(Disposable disposable) {
        return mRxDisposable.registe(disposable);
    }

    /**
     * 请求权限
     * @param permissions 权限
     * @return 权限申请器
     */
    protected PermissionRequest requestPermission(String... permissions) {
        return mPermissionRequest.permissions(permissions).setMessage("应用没有权限使用该功能，是否重新请求权限？", "应用没有权限使用该功能，请前往权限管理开启后才能继续使用");
    }

    /**
     * 自动刷新
     */
    public void autoRefresh() {

    }

    /**
     * 包装路由器
     * @param postcard 路由
     * @return 路由器
     */
    protected Postcard navigation(Postcard postcard) {
        return postcard.withString("digest", digest());
    }

    /**
     * 创建路由器
     * @param path 路由地址
     * @return 路由器
     */
    protected Postcard navigation(String path) {
        return ARouter.getInstance().build(path).withString("digest", digest());
    }

    @Override
    public void finish() {
        exitActivity(false);
    }

    public void exitActivity(boolean onBackPressed) {
        if(!ActivityStack.getInstance().hasMain() && ActivityStack.getInstance().count() <= 1) {
            FrameworkNavigation.home(mActivity);
        }else {
            super.finish();
        }
    }

    public void finishActivity() {
        super.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(NETWORK_ERROR_HINT)) {
            if (NetworkUtil.getNetworkType() == NetworkUtil.NetworkType.NETWORK_NO) {
                ToastHelper.toastDefault(NETWORK_ERROR_HINT);
            }
        }

    }
}

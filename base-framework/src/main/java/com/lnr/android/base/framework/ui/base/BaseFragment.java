package com.lnr.android.base.framework.ui.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;
import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IDigest;
import com.lnr.android.base.framework.mvp.view.IView;
import com.lnr.android.base.framework.rx.RxDisposable;
import com.lnr.android.base.framework.ui.control.permission.PermissionRequest;
import com.lnr.android.base.framework.uitl.ImmersionBarUtil;
import com.lnr.android.base.framework.uitl.logger.Logger;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.umeng.socialize.UMShareAPI;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/5/24
 * 基础fragment
 */

public abstract class BaseFragment extends RxFragment implements IDigest, IView {

    public final long ID = System.nanoTime()/1000;

    /**
     * 布局缓存引用
     */
    private WeakReference<View> mRootView;

    /**
     * 沉浸式组件
     */
    protected ImmersionBar mImmersionBar;

    /**
     * 观察者组件
     */
    RxDisposable mRxDisposable;

    private boolean isFragmentVisible;
    private boolean isReuseView;
    private boolean isFirstVisible;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    protected Dialog createLoadingDialog(Activity activity) {
        return null;
    }

    public void showLoading(){
        FragmentActivity activity = getActivity();
        if(activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).showLoading();
        }
    }

    public void hideLoading() {
        FragmentActivity activity = getActivity();
        if(activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).hideLoading();
        }
    }

    @Override
    public void updateProgress(int progress) {
        FragmentActivity activity = getActivity();
        if(activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).updateProgress(progress);
        }
    }

    @Override
    public String digest() {
        return null;
    }


    protected abstract List<IPresenter> getIPresenters();

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

    //setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
    //如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
    //如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
    //总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
    //如果我们需要在 Fragment 可见与不可见时干点事，用这个的话就会有多余的回调了，那么就需要重新封装一个
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //setUserVisibleHint()有可能在fragment的生命周期外被调用
        if (mRootView == null || mRootView.get() == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser) {
            isFragmentVisible = true;

            if(!isFirstVisible && mImmersionBar != null) {
                try {
                    mImmersionBar.init();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (mRootView == null) {
            return;
        }
        if (isFirstVisible && !hidden) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }

        if (!hidden) {
            isFragmentVisible = true;

            if(mImmersionBar != null) {
                try {
                    mImmersionBar.init();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return;
        }

        if(isFragmentVisible) {
            isFragmentVisible = false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    private void onFragmentFirstVisible() {
        mRxDisposable = new RxDisposable();
        if(getParentFragment() == null) {
            try {
                initImmersionBar(isImmersion());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ARouter.getInstance().inject(this);
        inject(Framework.getInstance().getApplicationComponent());
        initView(getRootView(), null);
        bindPresenter(this);
        afterInitView(getRootView(), null);
    }

    /**
     * 自动刷新
     */
    public void autoRefresh() {

    }

    public boolean isShowing() {
        return !isFirstVisible && isFragmentVisible;
    }

    @Nullable
    @Override
    public final View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout;
        if(mRootView != null && (layout = mRootView.get()) != null) {
            onCacheViewInit(layout, savedInstanceState);
        }else {
            layout = contentLayout(inflater, container, savedInstanceState);
        }
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        if (mRootView == null || mRootView.get() == null) {
            mRootView = new WeakReference<>(view);
            if (getUserVisibleHint() && !isHidden()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView ? mRootView.get() : view, savedInstanceState);
    }



    protected  <V extends View> V findViewById(int id) {
        View view = getRootView();
        if(view == null) return null;
        return view.findViewById(id);
    }

    protected View getRootView() {
        if(mRootView == null) return null;
        return mRootView.get();
    }

    private void initVariable() {
        isFirstVisible = true;
        isFragmentVisible = false;
        mRootView = null;
        isReuseView = true;
    }


    protected abstract void inject(ApplicationComponent component);

    protected boolean isImmersion() {
        return false;
    }

    /**
     * 初始化沉浸式
     * @param immersion 是否开启沉浸式
     */
    protected void initImmersionBar(boolean immersion) {
        if(immersion) {
            mImmersionBar = ImmersionBarUtil.buildImmersion(this);
        }else {
            mImmersionBar = ImmersionBarUtil.buildNotImmersion(this);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroy() {
        if(mRxDisposable != null) mRxDisposable.clear();
        if(mImmersionBar != null) {
            mImmersionBar.destroy();
            mImmersionBar = null;
        }
        UMShareAPI.get(getContext()).release();
        initVariable();
        unBindPresenter();
        super.onDestroy();
    }

    protected void finish() {
        FragmentActivity activity = getActivity();
        if(activity != null) {
            activity.finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }

    protected <T> RxDisposable registe(Class<T> tClass, Consumer<T> action) {
        return mRxDisposable.registe(tClass, action);
    }

    protected abstract View contentLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    protected abstract void initView(View view, @Nullable Bundle savedInstanceState);

    protected abstract void afterInitView(View view, @Nullable Bundle savedInstanceState);

    /**
     * 从缓存中读取view,如需处理自定义操作,请自行实现
     * @param view view
     * @param savedInstanceState 恢复的数据
     */
    protected void onCacheViewInit(View view, @Nullable Bundle savedInstanceState) {
    }

    /**
     * 请求权限
     * @param permissions 权限列表
     * @return 权限请求Request
     */
    protected PermissionRequest requestPermission(String... permissions) {
        BaseActivity activity = (BaseActivity) getActivity();
        if(activity == null) return null;
        return activity.requestPermission(permissions).setMessage("应用没有权限使用该功能，是否重新请求权限？", "应用没有权限使用该功能，请前往权限管理开启后才能继续使用");
    }

    /**
     * 路由调转
     * @param path 地址
     * @return 路由器
     */
    protected Postcard navigation(String path) {
        return ARouter.getInstance().build(path).withString("digest", digest());
    }
}

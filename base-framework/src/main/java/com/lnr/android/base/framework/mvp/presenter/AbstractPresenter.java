package com.lnr.android.base.framework.mvp.presenter;

import com.lnr.android.base.framework.data.asyn.AsynCode;
import com.lnr.android.base.framework.data.asyn.AsynException;
import com.lnr.android.base.framework.data.asyn.core.AsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynCallExecutor;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.view.IView;

import javax.inject.Inject;


/**
 * author:lnr
 * date:2018/5/10
 */

public class AbstractPresenter<V extends IView> implements IPresenter<V> {

    private V view;

    @Inject
    protected AsynCallExecutor executor;

    @Override
    public void bindView(V view) {
        this.view = view;
    }

    protected V view() {
        return view;
    }

    @Override
    public void unBindView() {
        view = null;
    }

    private <R> boolean checkIsNull(AsynCallback<R> callback) {
        if(checkViewIsNull() && callback != null) {
            callback.onCallError(new AsynException(AsynCode.Error.VIEW_RECYCLED));
        }
        return view == null;
    }

    private boolean checkViewIsNull() {
        return view == null;
    }

    protected final <T> void excuteWithLoading(AsynCall<T> call, AsynParams params, final AsynCallback<T> callback) {
        executor.create(call, params).excuteWithLoading(new AsynCallback<T>() {
            @Override
            public void onCallResponse(T r) {
                if(callback == null) return;
                if(checkIsNull(this)) return;
                callback.onCallResponse(r);
            }

            @Override
            public void onCallError(Throwable ex) {
                if(callback == null) return;
                if(checkViewIsNull()) return;
                callback.onCallError(ex);
            }
        });
    }

    protected final <T> void excuteNoLoading(AsynCall<T> call, AsynParams params, final AsynCallback<T> callback) {
        executor.create(call, params).excuteNoLoading(new AsynCallback<T>() {
            @Override
            public void onCallResponse(T r) {
                if(callback == null) return;
                if(checkIsNull(this)) return;
                callback.onCallResponse(r);
            }

            @Override
            public void onCallError(Throwable ex) {
                if(callback == null) return;
                if(checkViewIsNull()) return;
                callback.onCallError(ex);
            }
        });
    }
}

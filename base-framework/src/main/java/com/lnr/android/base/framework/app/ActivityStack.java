package com.lnr.android.base.framework.app;

import android.app.Activity;

import com.lnr.android.base.framework.ui.base.BaseActivity;

import java.util.Stack;

/**
 * author:lnr
 * date:2018/5/10
 * activity 栈管理
 */

public final class ActivityStack {

    private final Stack<Activity> activityStack;
    private Activity mainActivity;

    private static final class SingleHolder {
        private static final ActivityStack INSTANCE = new ActivityStack();
    }

    private ActivityStack() {
        activityStack = new Stack<>();
    }

    /**
     * 单一实例
     */
    public static ActivityStack getInstance() {
        return SingleHolder.INSTANCE;
    }

    public int count() {
        return activityStack.size();
    }

    public boolean contains(Class c) {
        if(count() == 0 || c == null) return false;
        String name = c.getName();
        for(Activity activity : activityStack) {
            if(activity != null && activity.getClass().getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public void setMainActivity(Activity activity) {
        this.mainActivity = activity;
    }

    public boolean hasMain() {
        return mainActivity != null;
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (count() > 0) {
            return activityStack.lastElement();
        }
        return null;
    }

    /**
     * 获取第一个activity
     *
     * @return
     */
    public Activity firstActivity() {
        if (count() > 0) {
            return activityStack.firstElement();
        }
        return null;
    }

    public Activity lastActivity() {
        if (count() > 0) {
            return activityStack.lastElement();
        }
        return null;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public Stack<Activity> getStack() {
        return activityStack;
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if(activity instanceof BaseActivity) {
                ((BaseActivity) activity).finishActivity();
            }else {
                activity.finish();
            }
        }
    }

    /**
     * 清除某个activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }

    /**
     * 返回定类名的Activity
     */
    public <T extends Activity> T getActivity(Class<T> cls) {
        if (activityStack.isEmpty()) {
            return null;
        }
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return (T) activity;
            }
        }
        return null;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }


    public void exit() {
        finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public String toString() {
        return activityStack.toString();
    }
}

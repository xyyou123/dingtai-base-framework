package com.lnr.android.base.framework.ui.control.view;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.view.View;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class ToolbarBehavior extends Behavior<Toolbar> {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }
}

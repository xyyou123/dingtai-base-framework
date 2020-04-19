package com.lnr.android.base.framework.ui.control.view;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * author:lnr
 * date:2018/9/20
 */
public class TabEntity implements CustomTabEntity {

    private String title;
    private int selectedIcon;
    private int unSelectedIcon;

    public TabEntity(String title, int unSelectedIcon, int selectedIcon) {
        this.title = title;
        this.unSelectedIcon = unSelectedIcon;
        this.selectedIcon = selectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}

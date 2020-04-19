package com.lnr.android.base.framework;

import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = AsynCallModule.class)
public interface FrameworkDagger {
}

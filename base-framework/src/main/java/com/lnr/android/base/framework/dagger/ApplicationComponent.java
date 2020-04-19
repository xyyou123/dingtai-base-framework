package com.lnr.android.base.framework.dagger;

import com.lnr.android.base.framework.Framework;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Framework framework);
}

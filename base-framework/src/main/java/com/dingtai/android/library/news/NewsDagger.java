package com.dingtai.android.library.news;

import com.dingtai.android.library.news.ui.details.base.BaseNewsActivity;
import com.dingtai.android.library.news.ui.details.comment.NewsCommentActivity;
import com.dingtai.android.library.news.ui.details.web.NewsDetailsActivity;
import com.dingtai.android.library.news.ui.home.NewsHomeFragment;
import com.dingtai.android.library.news.ui.home.more.NewsFirstMoreActivity;
import com.dingtai.android.library.news.ui.image.NewsImageActivity;
import com.dingtai.android.library.news.ui.launch.LaunchAdActivity;
import com.dingtai.android.library.news.ui.launch.LaunchAdListActivity;
import com.dingtai.android.library.news.ui.list.NewsListFragment;
import com.dingtai.android.library.news.ui.list.NewsListHasAdFragment;
import com.dingtai.android.library.news.ui.list.more.NewsListMoreActivity;
import com.dingtai.android.library.news.ui.relevant.NewsRelevantFragment;
import com.dingtai.android.library.news.ui.search.NewsSearchActivity;
import com.dingtai.android.library.news.ui.search.result.NewsSearchResultActivity;
import com.dingtai.android.library.news.ui.subject.neo.SubjectNeoListActivity;
import com.dingtai.android.library.news.ui.subject.old.SubjectOldListActivity;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = AsynCallModule.class)
public interface NewsDagger {

    void inject(NewsListHasAdFragment fragment);
    void inject(NewsHomeFragment fragment);
    void inject(NewsListFragment fragment);
    void inject(NewsRelevantFragment fragment);

    void inject(BaseNewsActivity activity);
    void inject(NewsDetailsActivity activity);
    void inject(SubjectOldListActivity activity);
    void inject(SubjectNeoListActivity activity);
    void inject(NewsListMoreActivity activity);
    void inject(NewsCommentActivity activity);
    void inject(LaunchAdActivity activity);
    void inject(NewsSearchActivity activity);
    void inject(NewsSearchResultActivity activity);
    void inject(NewsImageActivity activity);
    void inject(NewsFirstMoreActivity activity);

    void inject(LaunchAdListActivity activity);
}

package androidlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import com.dingtai.android.library.resource.Resource;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lnr.android.base.framework.app.ActivityStack;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.FrameworkNavigation;
import com.lnr.android.base.framework.ui.base.BaseActivity;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.ui.control.view.TabEntity;
import com.lnr.android.base.framework.ui.control.view.publish.PublishDialog;
import com.lnr.android.base.framework.ui.control.view.publish.PublishView;

import java.util.ArrayList;
import java.util.List;

import library.dingtai.com.androidlibrary.R;

import static com.dingtai.android.library.resource.Routes.News.HOME_TAB;

/**
 * author:lnr
 * date:2018/11/5
 */
@Route(path = "/app/tab")
public class HomeActivity extends BaseActivity  {

    protected CommonTabLayout mTabLayout;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void initView() {
        mTabLayout = findViewById(R.id.TabLayout);
    }

    @Override
    protected void inject(ApplicationComponent applicationComponent) {

    }

    @Override
    protected void afterInitView(@Nullable Bundle bundle) {

        initTab();
    }


    private void initTab() {

        ArrayList<CustomTabEntity> entities = new ArrayList<>();
        entities.add(new TabEntity("首页", R.drawable.icon_home_tab_0_0, R.drawable.icon_home_tab_0_1));
        entities.add(new TabEntity("活动", R.drawable.icon_home_tab_1_0, R.drawable.icon_home_tab_1_1));
//        entities.add(new TabEntity("株洲圈", R.drawable.icon_home_tab_2_0, R.drawable.icon_home_tab_2_0));
//        entities.add(new TabEntity("服务", R.drawable.icon_home_tab_3_0, R.drawable.icon_home_tab_3_1));
//        entities.add(new TabEntity("我的", R.drawable.icon_home_tab_4_0, R.drawable.icon_home_tab_4_1));

        if(mFragmentList != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            for (Fragment fragment : mFragmentList) {
                fragmentTransaction.remove(fragment);
            }
            fragmentTransaction.commit();
            mFragmentList.clear();
        }
//        Resource.API.URL = "http://47.99.113.47:7080/";
        Resource.API.URL = "http://47.99.113.47:8090/";
        initFragments(R.id.fragment_container,
                (Fragment) ARouter.getInstance().build(HOME_TAB).navigation(),
//                (Fragment) ARouter.getInstance().build("/video/video/list").navigation(),
//                (Fragment) ARouter.getInstance().build("/video/video/list").navigation(),
//                (Fragment) ARouter.getInstance().build("/video/video/list").navigation(),
                (Fragment) ARouter.getInstance().build("/video/video/list").navigation()
        );

        mTabLayout.setTabData(entities,this,mFragmentContainer, mFragmentList);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if(position == 0 ) {
                    mTabLayout.getIconView(0).setImageResource(R.drawable.icon_home_tab_0_2);
                    mTabLayout.getTitleView(0).setText("回到顶部");
                }else if(position == 0) {
                    mTabLayout.getIconView(0).setImageResource(R.drawable.icon_home_tab_0_1);
                    mTabLayout.getTitleView(0).setText("首页");
                }else {
                    mTabLayout.getIconView(0).setImageResource(R.drawable.icon_home_tab_0_0);
                    mTabLayout.getTitleView(0).setText("首页");
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        PublishView publishView = findViewById(R.id.PublishView);
        publishView.setMenu(true, true, false);
        publishView.setOnClickMenuListener(new PublishDialog.OnClickMenuListener() {
            @Override
            public void onClickLeftMenu() {

            }

            @Override
            public void onClickMidMenu() {

            }

            @Override
            public void onClickRightMenu() {

            }
        });
    }

    private long lastBackTime;

    @Override
    public void onBackPressed() {
        long current = System.currentTimeMillis();
        if(current - lastBackTime < 1000) {
            ActivityStack.getInstance().exit();
        }else {
            lastBackTime = current;
            ToastHelper.toastDefault("再按一次退出程序");
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


}

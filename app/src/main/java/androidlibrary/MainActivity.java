package androidlibrary;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.resource.Routes;
import com.lnr.android.base.framework.app.ActivityStack;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

import java.util.List;
import java.util.Random;

import library.dingtai.com.androidlibrary.R;

@Route(path = "/app/home")
public class MainActivity extends ToolbarActivity {

    private RecyclerView mRecyclerView;
    private Fragment fragment;

    @Override
    public String digest() {
        return null;
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected View contentView() {
        return View.inflate(this, R.layout.activity_main, null);
    }

    @Override
    protected void initView() {
//        AppUpdate.getInstance().updateApp();
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        ActivityStack.getInstance().setMainActivity(this);
        toolbar().setLeftVisibility(false, false, false);

        toolbar().setTitle("开发使用");

        toolbar().setRightText("退出登录");
        toolbar().setRightListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                AccountHelper.getInstance().logout();
                ToastHelper.toastSucceed("已退出");
            }
        });

        mRecyclerView = findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        Adapter adapter = new Adapter();
        mRecyclerView.setAdapter(adapter);

        adapter.addData("设置");
        adapter.addData("收藏");
        adapter.addData("爆料");
        adapter.addData("新闻");
        adapter.addData("scrollweb");
        adapter.addData("tab");
        adapter.addData("上传头像");
        adapter.addData("政务");
        adapter.addData("直播tab");
        adapter.addData("点播tab");
        adapter.addData("新闻搜索");
        adapter.addData("直播");
        adapter.addData("登录");

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String item = (String) adapter.getItem(position);
                if(item == null) return;
                switch (item) {
                    case "设置":
//                        SettingNavigation.settingCenter();
                        break;
                    case "收藏":
//                        AccountNavigation.accountCollect();
                        break;
                    case "爆料":
                        navigation("/baoliao/main").navigation();
                        break;
                    case "新闻搜索":
//                        NewsNavigation.search();
                        break;
                    case "新闻":
//                        NewsNavigation.tabActivity(null, null, null);
                        break;
                    case "scrollweb":
                        navigation("/app/scrollweb").navigation();
                        break;
                    case "上传头像":
//                        AccountNavigation.accountUploadHeader();
                        break;
                    case "政务":
//                        ModulesNavigation.wenzhengTab();
                        break;
                    case "直播tab":
//                        VideoNavigation.liveChannelTab();
                        break;
                    case "点播tab":
//                        VideoNavigation.vodChannels();
                        break;
                    case "tab":
                        navigation("/app/tab").navigation();
                        break;

                    case "登录":
                        navigation(Routes.Account.LOGIN).navigation();
                        break;


                    case "直播":
                        //navigation("/app/tab").navigation();
                        break;
                }
            }
        });

//        AppUpdate.getInstance().updateApp();
    }

    @Override
    public void onBackPressed() {
        if(fragment != null) {
            mRecyclerView.setVisibility(View.VISIBLE);
            fragment = null;
            return;
        }

        super.onBackPressed();
    }

    private static class Adapter extends BaseAdapter<String> {

        private Random random = new Random();

        @Override
        protected ItemConverter<String> createItemConverter(int viewType) {
            return new ItemConverter<String>() {
                @Override
                public int layoutId() {
                    return android.R.layout.simple_list_item_1;
                }

                @Override
                public void convert(BaseViewHolder holder, int position, String item) {
                    holder.setText(android.R.id.text1, item);

                    holder.itemView.setBackgroundColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

                    holder.setTextColor(android.R.id.text1, Color.WHITE);
                }
            };
        }
    }

}

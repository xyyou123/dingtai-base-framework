//package androidlibrary;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//
//import com.alibaba.android.arouter.facade.annotation.Route;
//import com.dingtai.android.library.news.model.NewsListModel;
//import com.dingtai.android.library.news.ui.details.base.BaseNewsActivity;
//import com.lnr.android.base.framework.common.umeng.ShareMenu;
//import com.lnr.android.base.framework.dagger.ApplicationComponent;
//
///**
// * author:lnr
// * date:2018/11/1
// */
//@Route(path = "/lib/news")
//public class NewsActivity extends BaseNewsActivity {
//
//    @Override
//    protected int layoutId() {
//        return 0;
//    }
//
//    @Override
//    protected boolean isWhiteTheme() {
//        return false;
//    }
//
//    @Override
//    protected ShareMenu createShareMenu() {
//        return null;
//    }
//
//    @Override
//    protected int rootLayoutResId() {
//        return R.layout.root_layout;
//    }
//
//    @Override
//    protected void retry() {
//
//    }
//
//    @Override
//    protected void inject(ApplicationComponent component) {
//
//    }
//
//    @Override
//    protected void initView() {
//        model = new NewsListModel();
//        model.setResourceGUID("11111");
//        super.initView();
//    }
//
//    @Override
//    protected void afterInitView(@Nullable Bundle savedInstanceState) {
//        mStatusLayoutManager.showContent();
//    }
//}

//package androidlibrary;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.widget.NestedScrollView;
//import android.view.View;
//
//import com.alibaba.android.arouter.facade.annotation.Route;
//import com.dingtai.android.library.news.ui.home.NewsFirstFragment;
//import com.lnr.android.base.framework.dagger.ApplicationComponent;
//import com.lnr.android.base.framework.mvp.presenter.IPresenter;
//
//import java.util.List;
//
///**
// * author:lnr
// * date:2018/12/17
// */
//@Route(path = "/homenews/first")
//public class ScrollTopFragment extends NewsFirstFragment {
//
//    @Override
//    protected List<IPresenter> getIPresenters() {
//        return null;
//    }
//
//    @Override
//    protected void inject(ApplicationComponent component) {
//
//    }
//
//    @Override
//    protected int contentLayoutResId() {
//        return R.layout.fragment_scrolltop;
//    }
//
//    @Override
//    protected void retry() {
//
//    }
//
//    @Override
//    protected void initView(View view, @Nullable Bundle savedInstanceState) {
//        registerScrollTop((NestedScrollView) findViewById(R.id.NestedScrollView));
//    }
//
//    @Override
//    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
//
//    }
//}

//package androidlibrary;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//
//import com.dingtai.android.library.model.models.PlayerModel;
//
//import com.lnr.android.base.framework.dagger.ApplicationComponent;
//import com.lnr.android.base.framework.mvp.presenter.IPresenter;
//import com.lnr.android.base.framework.ui.base.fragment.BaseRecyclerViewFragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * author:lnr
// * date:2018/10/25
// */
//public class VideoListFragment extends BaseRecyclerViewFragment {
//
////    private VideoAdapter mVideoAdapter;
//    private PIPManager mPIPManager;
//
//
//    private IjkVideoView mIjkVideoView;
//
//    @Override
//    protected void retry() {
//
//    }
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
//    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
//
//        mStatusLayoutManager.showContent();
//
//        List<PlayerModel> models = new ArrayList<>();
////        PlayerModel model = new PlayerModel();
////        model.title = "幸福是什么";
////        model.thumb = "https://cms-bucket.nosdn.127.net/171956fc3b0f493482424654b6fb14a520180418140011.jpeg";
////        model.urls = new ArrayList<>();
////        model.urls.add("http://mov.bn.netease.com/open-movie/nos/flv/2015/12/31/SBB7M663L_hd.flv");
////        models.add(model);
////
////
////        model = new PlayerModel();
////        model.title = "被拒100天";
////        model.thumb = "https://cms-bucket.nosdn.127.net/eb411c2810f04ffa8aaafc42052b233820180418095416.jpeg";
////        model.urls = new ArrayList<>();
////        model.urls.add("http://mov.bn.netease.com/open-movie/nos/flv/2017/01/16/SC9VQFO3E_hd.flv");
////        models.add(model);
////
////        model = new PlayerModel();
////        model.title = "群体性孤独";
////        model.thumb = "https://cms-bucket.nosdn.127.net/cb37178af1584c1588f4a01e5ecf323120180418133127.jpeg";
////        model.urls = new ArrayList<>();
////        model.urls.add("http://mov.bn.netease.com/open-movie/nos/flv/2015/11/26/SB8EEJKNH_hd.flv");
////        models.add(model);
////
////        model = new PlayerModel();
////        model.title = "什么让我们更热爱自己的工作?";
////        model.thumb = "https://cms-bucket.nosdn.127.net/e2af1d563faa46d0aa19da87f83159fd20180418131040.jpeg";
////        model.urls = new ArrayList<>();
////        model.urls.add("http://mov.bn.netease.com/open-movie/nos/flv/2014/01/03/S9GO68OJG_sd.flv");
////        models.add(model);
////
////        model = new PlayerModel();
////        model.title = "为什么健康的生活方式几乎把我害死?";
////        model.thumb = "https://cms-bucket.nosdn.127.net/f90b03a4bac34419b4bc0b22f4d989b420180411100506.jpeg";
////        model.urls = new ArrayList<>();
////        model.urls.add("http://mov.bn.netease.com/open-movie/nos/flv/2013/08/05/S94IVFMKI_hd.flv");
////        models.add(model);
////
////        model = new PlayerModel();
////        model.title = "如何掌控你的自由时间?";
////        model.thumb = "https://cms-bucket.nosdn.127.net/aae5c06c35d94f45ae3c3108dcb493e520180408212733.jpeg";
////        model.urls = new ArrayList<>();
////        model.urls.add("http://mov.bn.netease.com/open-movie/nos/flv/2017/01/03/SC8U8K7BC_hd.flv");
////        models.add(model);
////
////        model = new PlayerModel();
////        model.title = "韩雪：积极的悲观主义者?";
////        model.thumb = "https://cms-bucket.nosdn.127.net/b963028024f847fe903b1638b05516a120180408212720.jpeg";
////        model.urls = new ArrayList<>();
////        model.urls.add("http://mov.bn.netease.com/open-movie/nos/flv/2017/07/24/SCP786QON_hd.flv");
////        models.add(model);
////
////        mPIPManager = PIPManager.getInstance();
////        mIjkVideoView = mPIPManager.getIjkVideoView();
////        mPIPManager.setActClass(getActivity().getClass());
////
////        mVideoAdapter = new VideoAdapter(mPIPManager);
////        mVideoAdapter.setNewData(models);
////        mRecyclerView.setAdapter(mVideoAdapter);
//
//        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
//            @Override
//            public void onChildViewAttachedToWindow(View view) {
//
//            }
//
//            @Override
//            public void onChildViewDetachedFromWindow(View view) {
//
//            }
//        });
//
//    }
//
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mPIPManager.resume();
//    }
//
//    @Override
//    public void onPause() {
//        mPIPManager.pause();
//        super.onPause();
//    }
//
//    @Override
//    public boolean onBackPressed() {
//        return mPIPManager.onBackPress();
//    }
//
//    @Override
//    public void onDestroy() {
//        mPIPManager.reset();
//        super.onDestroy();
//    }
//}

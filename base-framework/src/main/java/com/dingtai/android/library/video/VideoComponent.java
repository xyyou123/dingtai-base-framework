package com.dingtai.android.library.video;

import com.dingtai.android.library.database.DBDaoSessionGenerateManager;
import com.dingtai.android.library.database.DBTableMasterManager;
import com.dingtai.android.library.video.api.impl.AddLiveReadNumAsynCall;
import com.dingtai.android.library.video.api.impl.AddReadNumAsynCall;
import com.dingtai.android.library.video.db.impl.ModelDBDaoSessionGenerateImpl;
import com.dingtai.android.library.video.db.impl.ModelDBTableImpl;
import com.dingtai.android.library.video.event.AddLiveReadNumEvent;
import com.dingtai.android.library.video.event.AddVodReadNumEvent;
import com.dingtai.android.library.video.event.UploadVideoEvent;
import com.dingtai.android.library.video.ui.video.upload.UploadVideoManager;
import com.dueeeke.videoplayer.util.L;
import com.lnr.android.base.framework.AbstractComponent;
import com.lnr.android.base.framework.BuildConfig;
import com.lnr.android.base.framework.data.asyn.core.AsynCallExecutor;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import io.reactivex.functions.Consumer;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class VideoComponent extends AbstractComponent {

    private AsynCallExecutor executor;
    private AddLiveReadNumAsynCall mAddLiveReadNumAsynCall;
    private AddReadNumAsynCall mAddReadNumAsynCall;

    @Override
    protected void initDatabase() {
        DBTableMasterManager.getInstance().add(new ModelDBTableImpl());
        DBDaoSessionGenerateManager.getInstance().add(new ModelDBDaoSessionGenerateImpl());

    }

    @Override
    protected void initInfo() {
        L.setDebug(BuildConfig.DEBUG);

        executor = new AsynCallExecutor(null, null);
        mAddLiveReadNumAsynCall = new AddLiveReadNumAsynCall();
        mAddReadNumAsynCall = new AddReadNumAsynCall();
        registe(AddLiveReadNumEvent.class, new Consumer<AddLiveReadNumEvent>() {
            @Override
            public void accept(AddLiveReadNumEvent event) throws Exception {
                AsynParams params = AsynParams.create("ID", event.getLiveId());
                executor.create(mAddLiveReadNumAsynCall, params).excuteNoLoading(null);
            }
        });

        registe(AddVodReadNumEvent.class, new Consumer<AddVodReadNumEvent>() {
            @Override
            public void accept(AddVodReadNumEvent event) throws Exception {
                AsynParams params = AsynParams.create("ID", event.getVodId());
                executor.create(mAddReadNumAsynCall, params).excuteNoLoading(null);
            }
        });

        registe(UploadVideoEvent.class, new Consumer<UploadVideoEvent>() {
            @Override
            public void accept(UploadVideoEvent uploadVideoEvent) throws Exception {
                UploadVideoManager.getInstance().add(uploadVideoEvent.getModel());
            }
        });
    }
}

package com.dingtai.android.library.video.event;

import com.dingtai.android.library.video.model.LiveProgramModel;

/**
 * author:lnr
 * date:2018/9/5
 */
public interface Event {

    class ChangeCurrentLiveProgramme {
        public LiveProgramModel model;

        public ChangeCurrentLiveProgramme(LiveProgramModel model) {
            this.model = model;
        }
    }
}

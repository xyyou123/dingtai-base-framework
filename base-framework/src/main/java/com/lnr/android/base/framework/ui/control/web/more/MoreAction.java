package com.lnr.android.base.framework.ui.control.web.more;

import com.lnr.android.base.framework.data.models.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class MoreAction extends ShareAction {

    public static final int ACTION_OPEN_IN_SYSTEM = 20;
    public static final int ACTION_COPY_LINK = 21;
    public static final int ACTION_REFRESH = 22;
    public static final int ACTION_CHANGE_TEXT_SIZE = 23;

    private int action;

    public MoreAction(SHARE_MEDIA type, String title, int res) {
        super(type, title, res);
    }

    public MoreAction(int action, String title, int res) {
        super(null, title, res);
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}

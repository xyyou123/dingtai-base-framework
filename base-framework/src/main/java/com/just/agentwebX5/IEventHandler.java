package com.just.agentwebX5;

import android.view.KeyEvent;

/**
 * <b>@项目名：</b> com.just.agentwebX5<br>
 * <b>@包名：</b>com.just.library<br>
 * <b>@创建者：</b> cxz --  just<br>
 * <b>@创建时间：</b> &{DATE}<br>
 * <b>@公司：</b> 宝诺科技<br>
 * <b>@邮箱：</b> cenxiaozhong.qqcom@qq.com<br>
 * <b>@描述</b><br>
 *
 *     source CODE  https://github.com/Justson/AgentWebX5
 */

public interface IEventHandler {

    boolean onKeyDown(int keyCode, KeyEvent event);


    boolean back();
}
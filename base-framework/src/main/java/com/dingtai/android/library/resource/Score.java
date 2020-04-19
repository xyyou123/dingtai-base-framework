package com.dingtai.android.library.resource;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018/9/26
 */
public class Score {

    /**
     * 积分相关
     */
    public static final String SCORE_TYPE_ADD = "1";
    public static final String SCORE_EXCHANGE_ID = "1";
    public static final String SCORE_TASKCONTENT_ID = "";

    public static final String SCORE_REGIST = "100101";//新注册用户
    public static final String SCORE_LOGIN = "100102";//登录
    public static final String SCORE_SHAR = "100103";//分享一次
    public static final String SCORE_RECOMMEND = "100104";//将app推荐给好友
    public static final String SCORE_STARTAPP = "100105";//启动一次app

    public static final String SCORE_CLICK_STARTAD = "100201";//点击开机画面广告
    public static final String SCORE_CLICK_OTHERAD = "100202";//点击其他广告

    public static final String SCORE_MUTUAL = "100110"; 	//互助
    public static final String SCORE_GOODS_BAOLIAO = "100111";		//商家快讯


    public static final String SCORE_REPLY_NEWS = "100301";//回复一篇新闻、论坛帖子
    public static final String SCORE_DIG_PAI = "100302";//点赞回复内容
    public static final String SCORE_SPEAK_BBS = "100303";//论坛发表一篇帖子
    public static final String SCORE_UPLOAD_VIDEO = "100304";//发布一个视频
    public static final String SCORE_UPLOAD_PICTURE = "100305";//发布一个图片
    public static final String SCORE_SPEAK_BAOLIAO = "100306";//发布一个快讯
    public static final String SCORE_LOOK_NEWS = "100307";//查看一篇新闻
    public static final String SCORE_LOOK_PICTURE = "100308";//查看一篇图片
    public static final String SCORE_LOOK_VIDEO = "100309";//查看一篇视频

    public static final String SCORE_LOOK_NEARBY = "100401";//查看一次附近内容

    //public static final String SCORE_SUBMIT_ORDER = "100501";//提交一个订单
    public static final String SCORE_COMMENT_SCORE = "100501";//打分
    public static final String SCORE_PAY_SUCCESS = "100502";//支付成功
    //public static final String SCORE_COMMENT_GOODS = "100504";//评论电商
    public static final String SCORE_COMPLETE_ADDRESS = "100503";//完善收货地址

    public static final String SCORE_JOIN_ACTIVITY = "100601";//参与一次活动

    public static final class ScoreModel {
        public String name;
        public String code;
        public int count;
        public int score;

        public ScoreModel() {
        }

        public ScoreModel(String name, int count, int score, String code) {
            this.name = name;
            this.count = count;
            this.score = score;
            this.code = code;
        }
    }

    public static final HashMap<String, ScoreModel> MAP = new HashMap<>();
    static {
        MAP.put(SCORE_REGIST, new ScoreModel("新注册用户", 1, 10, SCORE_REGIST));
        MAP.put(SCORE_LOGIN, new ScoreModel("登录", 3, 1, SCORE_LOGIN));
        MAP.put(SCORE_SHAR, new ScoreModel("分享一次", 10, 5, SCORE_SHAR));
        MAP.put(SCORE_RECOMMEND, new ScoreModel("将app推荐给好友", 0, 20, SCORE_RECOMMEND));
        MAP.put(SCORE_STARTAPP, new ScoreModel("启动一次app", 1, 2, "100105"));
        MAP.put("100201", new ScoreModel("点击开机画面广告", 10, 3, "100201"));
        MAP.put("100202", new ScoreModel("点击其他广告", 8, 3, "100202"));
        MAP.put("100301", new ScoreModel("回复一篇新闻、论坛帖子", 5, 1, "100301"));
        MAP.put("100302", new ScoreModel("点赞回复内容", 5, 1, "100302"));
        MAP.put("100303", new ScoreModel("论坛发表一篇帖子", 0, 5, "100303"));
        MAP.put("100304", new ScoreModel("发布一个视频", 0, 5, "100304"));
        MAP.put("100305", new ScoreModel("发布一个图片", 0, 5, "100305"));
        MAP.put("100306", new ScoreModel("发布一个快讯", 0, 5, "100306"));
        MAP.put("100307", new ScoreModel("查看一篇新闻", 3, 1, "100307"));
        MAP.put("100308", new ScoreModel("查看一篇图片", 1, 1, "100308"));
        MAP.put("100309", new ScoreModel("查看一篇视频", 1, 1, "100309"));
        MAP.put("100401", new ScoreModel("查看一次附近内容", 0, 3, "100401"));
        MAP.put("100501", new ScoreModel("打分", 0, 3, "100501"));
        MAP.put("100502", new ScoreModel("支付成功", 0, 1000, "100502"));
        MAP.put("100503", new ScoreModel("完善收货地址", 0, 1, "100503"));
        MAP.put("100601", new ScoreModel("参与一次活动", 0, 3, "100601"));
        MAP.put("100110", new ScoreModel("发布一个互助", 0, 5, "100110"));
        MAP.put("100111", new ScoreModel("发布一个商家快讯", 0, 5, "100111"));
    }
}

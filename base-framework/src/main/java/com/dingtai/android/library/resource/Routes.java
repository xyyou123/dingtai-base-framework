package com.dingtai.android.library.resource;

/**
 * author:lnr
 * date:2018/9/12
 * 路由管理
 */
public final class Routes {

    public static class Framework {

        public static  String HOME = "/app/home";

        /**
         * 基础浏览器组件
         */
        public static  String WEB_BASE = "/framework/web/base";
        public static  String WEB_BASE_FRAGMENT = "/framework/web/base/fragment";
    }

    public static  class Account {

        /**
         * 登录
         */
        public static  String LOGIN = "/account/login";


        /**
         * 登录 并绑定手机号
         */
        public static  String LOGIN_BIND_PHONE= "/account/login/bindphone";
        
        /**
         * 注册
         */
         public static  String REGISTER = "/account/register";
        /**
         * 忘记密码
         */
         public static  String FORGETPASSWORD = "/account/forgetpwd";
        /**
         * 修改密码
         */
         public static  String UPDATEPASSWORD = "/account/updatepwd";
         public static  String UPDATEPASSWORD2 = "/account/updatepwd2";
        /**
         * 上传头像
         */
         public static  String UPLOAD_HEADER = "/account/uploadheader";
        /**
         * 更新资料
         */
         public static  String UPDATEINFO = "/account/updateinfo";
        /**
         * 用户中心
         */
         public static  String CENTER = "/account/center";

        /**
         * 历史
         */
         public static  String HISTORY = "/account/history";
        /**
         * 历史 列表
         */
         public static  String HISTORY_LIST = "/account/history/list";
        /**
         * 收藏
         */
         public static  String COLLECT = "/account/collect";

        /**
         * 积分商城
         */
         public static  String SCORE_STORE = "/account/score/store";
        /**
         * fragment
         */
         public static  String SCORE_STORE_FRAGMENT = "/account/score/store/fragment";

        /**
         * 商品详情
         */
         public static  String SCORE_STORE_COMMODITY_DETAILS = "/account/score/store/commodity/details";
        /**
         * 确认兑换商品
         */
         public static  String SCORE_STORE_EXCHANGE_CONFIRM = "/account/score/store/exchange/confirm";
        /**
         * 兑换记录
         */
         public static  String SCORE_STORE_EXCHANGE_RECORD = "/account/score/store/exchange/record";
        /**
         * 兑换详情
         */
         public static  String SCORE_STORE_EXCHANGE_DETAILS = "/account/score/store/exchange/details";

        /**
         * 积分任务
         */
         public static  String SCORE_TASK = "/account/score/task";
        /**
         * 邀请码
         */
         public static  String INVITE_CODE = "/account/invite/code";
        /**
         * 我邀请的人
         */
         public static  String INVITE_LIST = "/account/invite/list";

        /**
         * 摇摇乐
         */
         public static  String YOYO = "/account/yoyo";

        /**
         * 摇摇乐
         */
         public static  String YOYO_SHAKE = "/account/yoyo/shake";

         public static  String XG_HISTORY = "/account/xghistory";
    }

    public static  class News {
        /**
         * webview
         */
         public static  String WEB = "/news/web";
        /**
         * 图集
         */
         public static  String IMAGE = "/news/image";

        /**
         * 新闻详情
         */
         public static  String DETAILS = "/news/details";
        /**
         * 新闻详情 评论
         */
         public static  String DETAILS_COMMENT = "/news/details/comment";

        /**
         * 新闻列表 主页tab
         */
         public static  String HOME_TAB = "/news/home";
         public static  String HOME_TAB_ACTIVITY = "/news/home/activity";

         /**
         * 新闻列表第一页 推荐页
         */
         public static  String HOME_FIRST = "/homenews/first";
         /**
         * 新闻列表第一页 推荐页 更多新闻
         */
         public static  String HOME_FIRST_NEWS_MORE = "/news/first/newsmore";

        /**
         * 栏目订阅
         */
         public static  String HOME_SUBSCRIPTION = "/news/home/subscription";
        /**
         * 新闻列表 activity
         */
         public static  String NEWS_LIST_ACTIVITY = "/news/list/activity";

        /**
         * 新闻列表 无ad
         */
         public static  String NEWS_LIST_NOAD = "/news/list/noad";

        /**
         * 新闻列表
         */
         public static  String NEWS_LIST_HASAD = "/news/list/hasad";

        /**
         * 旧专题列表
         */
         public static  String SUBJECT_OLD_LIST = "/news/subject/old/list";
        /**
         * 新专题列表
         */
         public static  String SUBJECT_NEO_LIST = "/news/subject/neo/list";

        /**
         *
         * 新闻更多列表
         */
         public static  String LIST_MORE = "/news/list/more";

        /**
         * 搜索页
         */
         public static  String SEARCH = "/news/search";
        /**
         * 搜索结果页
         */
         public static  String SEARCH_RESULT = "/news/search/result";
        /**
         * 相关新闻
         */
         public static  String RELEVANT = "/news/relevant";
    }

    public static class Baoliao {
        /**
         * 主页
         */
         public static  String MAIN = "/baoliao/main";
        /**
         * 我的爆料
         */
         public static  String MY_PUBLISH = "/baoliao/my";
        /**
         * 发布
         */
         public static  String PUBLISH = "/baoliao/publish";
        /**
         * 列表页
         */
         public static  String LIST_ALL = "/baoliao/list/all";
        /**
         * 全部列表页
         */
         public static  String LIST = "/baoliao/list";
        /**
         * 主页 tab页
         */
         public static  String TAB = "/baoliao/tab";

         public static  String DETAILS = "/baoliao/details";
    }

    public static  class Setting {
        /**
         * 设置主页
         */
         public static  String CENTER = "/setting/center";

         public static  String ABOUT = "/setting/about";
        /**
         * 意见反馈
         */
         public static  String FEEDBACK = "/setting/feedback";
        /**
         * 隐私政策
         */
         public static  String PRIVACY = "/setting/privacy";

        /**
         * 引导页
         */
         public static  String GUIDE = "/setting/guide";
    }

    public static  class Video {
        /**
         * 直播间主页
         */
         public static  String LIVE_MAIN = "/video/live/main";
        /**
         * 直播主页  图文直播间
         */
         public static  String LIVE_MAIN_IMAGETEXT = "/video/live/main/imagetext";
        /**
         * 聊天
         */
         public static  String LIVE_CHAT = "/video/live/chat";

        /**
         * 聊天
         */
        public static  String LIVE_CHAT_DESC = "/video/live/chat/desc";

        /**
         * 聊天
         */
        public static  String LIVE_CHAT_REDPACKET = "/video/live/chat/redpacket";
        /**
         * 节目单
         */
         public static  String LIVE_PROGRAMME = "/video/live/programme";
        /**
         * 互动游戏
         */
         public static  String LIVE_GAME = "/video/live/game";
        /**
         * 图文内容
         */
         public static  String LIVE_IMAGE_TEXT = "/video/live/imagetext";

        /**
         * 直播列表
         */
         public static  String LIVE_LIST = "/video/live/channels";
        /**
         * 活动直播
         */
         public static  String LIVE_ACTIVITIES = "/video/live/activities";

        /**
         * 直播列表
         */
         public static  String LIVE_LIST_CHANNEL = "/video/live/list/channel";

        /**
         * 点播列表
         */
         public static  String VOD_LIST = "/video/vod/list";

         public static  String VOD_CHANNELS = "/video/vod/channels";

        /**
         * 点播子列表
         */
         public static  String VOD_LIST_CHILD = "/video/vod/list/child";
        /**
         * 点播详情
         */
         public static  String VOD_DETAILS = "/video/vod/details";

        /**
         * 点播评论
         */
        public static  String VOD_DETAILS_COMMENT = "/video/vod/details/comment";

        /**
         * 简单的播放页面
         * */
         public static  String PLAYER_SIMPLE = "/video/player/simple";

        /**
         * 播放器
         */
         public static  String PLAYER = "/video/player/fragment";

        /**
         * 发布图文直播
         */
        public static  String PUBLISH_IMAGE_TEXT = "/video/publish/imagetext";


        /**
         * 视频列表 tab页
         */
        public static  String VIDEO_TAB = "/video/video/tab";
        /**
         * 视频列表
         */
        public static  String VIDEO_LIST = "/video/video/list";
        /**
         * 视频列表
         */
        public static  String VIDEO_SHORT_LIST = "/video/shortvideo/list";
        /**
         * 视频列表
         */
        public static  String VIDEO_SHORT_DETIAL = "/video/shortvideo/detial";

        /**
         * 视频列表
         */
        public static  String VIDEO_PERSION_VIDEO_LIST = "/video/pvlist/list";

        /**
         * 视频列表 详情
         */
        public static  String VIDEO_LIST_DETAILS = "/video/video/list/details";

        /**
         * 视频 我的上传
         */
        public static  String VIDEO_UPLOAD_MY = "/video/video/upload/my";
        /**
         * 视频 发布
         */
        public static  String VIDEO_UPLOAD_PUBLISH = "/video/video/upload/publish";

        /**
         * 视频 正在上传
         */
        public static  String VIDEO_UPLOAD_UPLOADING = "/video/video/upload/uploading";
        
        
        
        
    }

    public static  class Modules {
        /**
         * 主页
         */
         public static  String MAIN = "/modules/main";


         public static  String WEB = "/modules/web";

        /**
         * 公交主页
         */
         public static  String BUS = "/modules/bus/main";
        /**
         * 公交-附近
         */
         public static  String BUS_NEARBY = "/modules/bus/nearby";
        /**
         * 公交-到附近的公家站 步行导航
         */
         public static  String BUS_NEARBY_MAP = "/modules/bus/nearby/map";

        /**
         * 公交-线路查询
         */
         public static  String BUS_WAY = "/modules/bus/way";
        /**
         * 公交-线路详情
         */
         public static  String BUS_WAY_DETAILS = "/modules/bus/way/details";

        /**
         * 公交-公交线路地图
         */
         public static  String BUS_WAY_MAP = "/modules/bus/way/map";

        /**
         * 公交-站点查询
         */
         public static  String BUS_STATION = "/modules/bus/station";

        /**
         * 公交-站点列表
         */
         public static  String BUS_STATION_LIST = "/modules/bus/station/list";

        /**
         * 公交-站点详情
         */
         public static  String BUS_STATION_DETAILS = "/modules/bus/station/details";

        /**
         * 公交-换乘
         */
         public static  String BUS_TRANSFER = "/modules/bus/transfer";
        /**
         * 公交-换乘详情
         */
         public static  String BUS_TRANSFER_DETAILS = "/modules/bus/transfer/details";

        /**
         * 公交-收藏
         */
         public static  String BUS_COLLECT = "/modules/bus/collect";

        /**
         * 公交-收藏 线路
         */
         public static  String BUS_COLLECT_WAY = "/modules/bus/collect/way";

        /**
         * 公交-收藏  站点
         */
         public static  String BUS_COLLECT_STATION = "/modules/bus/collect/station";

        /**
         * 公交-收藏-换乘
         */
         public static  String BUS_COLLECT_TRANSFER = "/modules/bus/collect/transfer";


        /**
         * 互助
         */
         public static  String HELP_MAIN_FRAGMENT = "/modules/help/main/fragment";
         public static  String HELP_MAIN_ACTIVITY = "/modules/help/main/activity";
        /**
         * 互助-全部页
         */
         public static  String HELP_ALL = "/modules/help/tab/all";
        /**
         * 互助-热点
         */
         public static  String HELP_HOT = "/modules/help/tab/hot";

        /**
         * 互助-专家
         */
         public static  String HELP_EXPERT = "/modules/help/tab/expert";
        /**
         * 互助-专家 详情
         */
         public static  String HELP_EXPERT_DETAILS = "/modules/help/tab/expert/details";
        /**
         * 互助-专家 回答列表
         */
         public static  String HELP_EXPERT_ANSWER = "/modules/help/tab/expert/answer";


        /**
         * 互助-详情页
         */
         public static  String HELP_DETAILS = "/modules/help/details";

        /**
         * 互助-我的
         */
         public static  String HELP_MY = "/modules/help/tab/my";

         public static  String HELP_MY_ANSWER = "/modules/help/tab/my/answer";
         public static  String HELP_MY_ATTENTION = "/modules/help/tab/my/attention";
         public static  String HELP_MY_QUESTION = "/modules/help/tab/my/question";

        /**
         * 互助 求助
         */
         public static  String HELP_ASK = "/modules/help/ask";

        /**
         * 活动列表
         */
         public static  String ACTIVITIES_LIST = "/modules/activities/list";
         public static  String ACTIVITIES_LIST_FRAGMENT = "/modules/activities/list/fragment";

        /**
         * 掌上政务列表
         */
         public static  String AFFAIRS_PBA_LIST = "/modules/affairs/pba/list";

        /**
         * 掌上政务详情
         */
         public static  String AFFAIRS_PBA_DETAILS = "/modules/affairs/pba/details";
        /**
         * 掌上政务详情 - 信息
         */
         public static  String AFFAIRS_PBA_DETAILS_INFO = "/modules/affairs/pba/details/info";


        /**
         * 政务 - 主页
         */
         public static  String WENGZHENG_TAB = "/modules/wenzheng/tab";


        /**
         * 政务 - 大厅
         */
         public static  String WENGZHENG_HALL = "/modules/wenzheng/tab/hall";
        /**
         * 最新提问
         */
         public static  String WENGZHENG_HALL_LATESTQUESTION = "/modules/wenzheng/tab/hall/LatestQuestion";
        /**
         * 最新回复
         */
         public static  String WENGZHENG_HALL_LATESTREPLY = "/modules/wenzheng/tab/hall/LatestReply";




        /**
         * 政务 - 部门
         */
         public static  String WENGZHENG_DEPT = "/modules/wenzheng/tab/dept";
        /**
         *  政务 - 部门 详情
         */
         public static  String WENGZHENG_DEPT_DETAILS = "/modules/wenzheng/tab/dept/details";

        /**
         * 政务 - 领导
         */
         public static  String WENGZHENG_LEADER = "/modules/wenzheng/tab/leader";

         public static  String WENGZHENG_LEADER_DETAILS = "/modules/wenzheng/tab/leader/details";

        /**
         * 政务 - 详情
         */
         public static  String WENGZHENG_DETAILS = "/modules/wenzheng/details";

        /**
         * 政务 - 进度查询
         */
         public static  String WENGZHENG_PROGRESS = "/modules/wenzheng/progress";
         public static  String WENGZHENG_PROGRESS_FRAGMENT = "/modules/wenzheng/progress/fragment";

        /**
         * 问政 提问
         */
         public static  String WENGZHENG_PUBLISH = "/modules/wenzheng/publish";
        /**
         * 选择部门
         */
         public static  String WENGZHENG_PUBLISH_PICK_DEPT = "/modules/wenzheng/publish/pick/dept";
         public static  String WENGZHENG_PUBLISH_PICK_AREA = "/modules/wenzheng/publish/pick/area";








        /**
         * 预约挂号
         */
         public static  String HOSPITAL_TAB = "/modules/hospital/tab";
        /**
         * 医院部门
         */
         public static  String HOSPITAL_DEPT = "/modules/hospital/dept";
        /**
         * 医生列表
         */
         public static  String HOSPITAL_DOCTOR = "/modules/hospital/doctor";
        /**
         * 医生时段信息
         */
         public static  String HOSPITAL_DOCTOR_TIME = "/modules/hospital/doctor/time";
        /**
         * 提交挂号信息
         */
         public static  String HOSPITAL_SUBMIT = "/modules/hospital/submit";

        /**
         * 我的预约 - 搜索
         */
         public static  String HOSPITAL_MY_SEARCH = "/modules/hospital/my/search";
        /**
         * 我的预约 - 预约列表
         */
         public static  String HOSPITAL_MY_LIST = "/modules/hospital/my/list";


        /**
         * 路况
         */
         public static  String ROAD = "/modules/roadcondition";
        /**
         * 地图
         */
         public static  String ROAD_MAP = "/modules/roadcondition/map";
        /**
         * 列表
         */
         public static  String ROAD_LIST = "/modules/roadcondition/list";
        /**
         * 详情
         */
         public static  String ROAD_DETAILS = "/modules/roadcondition/details";
    }

    public static  class Weather {
        /**
         * 天气主页
         */
         public static  String MAIN = "/weather/main";
        /**
         * 详情页
         */
         public static  String DETAILS = "/weather/details";
        /**
         * 指数
         */
         public static  String ZHISHU = "/weather/zhishu";

         public static  String SELECTE_CITY = "/weather/selectecity";
    }

    public static  class Recorder {

         public static  String REOCRD = "/recorder/record";
         public static  String PUSH = "/recorder/push";
         public static  String PUSH_SETTING = "/recorder/push/setting";
    }

    public static class Scan {
        public static  String SCAN = "/scan/scan";
        public static  String RESULT = "/scan/result";
    }

    public static class Pay {

        /**
         * 缴费 广电郴州 主页
         */
        public static String JIAOFEI_GDCZ_MAIN = "/pay/jiaofei/gdcz/main";

        public static String JIAOFEI_GDCZ_SEARCH_CARD = "/pay/jiaofei/gdcz/search/card";
        public static String JIAOFEI_GDCZ_PAY = "/pay/jiaofei/gdcz/pay";
        public static String JIAOFEI_GDCZ_PAY_WEB = "/pay/jiaofei/gdcz/pay/web";

        public static String JIAOFEI_GDCZ_SEARCH_INFO = "/pay/jiaofei/gdcz/search/info";
        public static String JIAOFEI_GDCZ_SEARCH_INFO_DETAILS = "/pay/jiaofei/gdcz/search/details";

    }

    public static class Store {
        /**
         * 主页
         */
        public static String HOME = "/store/home";
        /**
         * 筛选
         */
        public static String FILTER = "/store/filter";
        /**
         * 商品详情
         */
        public static String COMMODITY_DETAILS = "/store/details/commodity";
        /**
         * 实物订单
         */
        public static String ORDER_REAL = "/store/order/submit/real";
        /**
         * 虚拟物品订单
         */
        public static String ORDER_FICTITIOUS = "/store/order/submit/fictitious";
        /**
         * 订单支付
         */
        public static String ORDER_PAY = "/store/order/pay";
        public static String ORDER_PAY_ZHIFUBAO_WEB = "/store/order/pay/alipyweb";

        /**
         * 我的订单
         */
        public static String ORDER_MY = "/store/order/my";
        /**
         * 我的订单字列表
         */
        public static String ORDER_MY_LIST = "/store/order/my/list";

        /**
         * 订单详情
         */
        public static String ORDER_DETAILS = "/store/order/details";

        /**
         * 搜索
         */
        public static String SEARCH = "/store/search";

        /**
         * 收货地址
         */
        public static String ADDRESS = "/store/address";
        /**
         * 编辑/新增 收货地址
         */
        public static String ADDRESS_EDIT = "/store/address/edit";
    }
}

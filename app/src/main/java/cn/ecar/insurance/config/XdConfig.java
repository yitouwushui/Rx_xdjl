package cn.ecar.insurance.config;


import android.support.v4.util.ArrayMap;

import java.util.Map;

import cn.ecar.insurance.net.NetWorkApi;

/**
 * Created by yx on 2016/8/11.
 * 常量类接口
 */
public interface XdConfig {


    String VIEW_TITLE = "view.title";
    String BLANK = "";
    String OPTION = "option";
    String TEXT = "text";

    String NO_NETWORK = "网络未连接,请检查网络.";

    String Token = "token";
    String SP_CURRENT = "sp_current";//当前登陆账号

    String SESSION_ID = "SESSION_ID";
    long SESSION_TIME = 3600000L;
    String SIGN_IN = "SIGN_IN"; //签到

    String BALANCE = "BALANCE";


    String PARAM_NEXT_TIME = "PARAM_NEXT_TIME";
    String LOCATION_RESULT = "Location.RESULT";
    /**
     * app SECRET
     */
    String APP_SECRET = "ECAR8888SECRET";
    String APP_ID = "ecar";
    String VERSION = "1.0";

    /**
     * 支付成功回调
     */
    String REDIRECT_URL = NetWorkApi.BASE_URL + "ecar-front/rechargeSmartPay/smartPaySuccess/smartPaySuccess";

    // 请求码
    int LOCATION_MUTISELECT_REQUEST = 102;

    // 省份请求码
    int SELECT_PROVINCE_REQUEST = 103;
    // 城市
    int SELECT_CITY_REQUEST = 104;
    // 银行请求码
    int SELECT_BANK_REQUEST = 105;
    // 银行分行请求码
    int SELECT_BRANCH_BANK_REQUEST = 106;
    // 银行分行请求码
    int MODIFY_THE_BANK_CARD_REQUEST = 107;
    // address
    int ADDRESS_IS_ADD_REQUEST = 108;
    int ADDRESS_IS_UPDATE_REQUEST = 109;


    // 保险
    int SELECT_ADDRESS_REQUEST = 110;
    // photo
    int PHOTO_SHEN_FEN_ZHENG1 = 111;
    int PHOTO_SHEN_FEN_ZHENG2 = 112;
    int PHOTO_JIA_SHI_ZHENG = 113;
    int PHOTO_YING_YE_ZHI_ZHAO = 114;
    int PHOTO_KAI_PIAO_ZI_LIAO = 115;

//    Map<Integer, String> PHOTO_MAP = new ArrayMap<Integer, String>(5) {
//        {
//            PHOTO_MAP.put(PHOTO_SHEN_FEN_ZHENG1, "certPath");
//            PHOTO_MAP.put(PHOTO_SHEN_FEN_ZHENG2, "certOtherPath");
//            PHOTO_MAP.put(PHOTO_JIA_SHI_ZHENG, "drivingPath");
//            PHOTO_MAP.put(PHOTO_YING_YE_ZHI_ZHAO, "certPath");
//            PHOTO_MAP.put(PHOTO_KAI_PIAO_ZI_LIAO, "certPath");
//        }
//    };
    /**请求成功front*/
    String RESPONSE_T = "EC0000";
    /**请求成功upload*/
    String RESPONSE_T_UPLOAD = "EU0000";
    /** 账户失效*/
    String RESPONSE_ACCOUNT_FAILURE = "EC0001";

    String RESPONSE_MSG = "请求成功";

    String RESPONSE_F = "EC0000F";
    String RESPONSE_MSG_F = "链接服务器失败，请检查网络";

    String CS = "调试";

    // EXTRA

    String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    String EXTRA_VALUE = "EXTRA_VALUE";
    String EXTRA_ARRAY_VALUE = "EXTRA_ARRAY_VALUE";
    String EXTRA_STRING_VALUE = "EXTRA_STRING_VALUE";
    String EXTRA_STRING_VALUE_2 = "EXTRA_STRING_VALUE_2";
    String EXTRA_INT_VALUE = "EXTRA_INT_VALUE";
    String EXTRA_REQUEST_VALUE = "EXTRA_REQUEST_VALUE";
    String EXTRA_CLASS_VALUE = "EXTRA_CLASS_VALUE";


    //保单状态
    /**
     * 订单状态--询价已发送
     */
    public static final String STATUS_SEND = "1";
    /**
     * 订单状态--询价返回成功
     */
    public static final String STATUS_BACK_SUCESS = "2";
    /**
     * 订单状态--询价失败
     */
    public static final String STATUS_BACK_FAIL = "3";
    /**
     * 订单状态--保险订单确认
     */
    public static final String STATUS_CONFIRM = "4";
    /**
     * 订单状态--保险支付成功
     */
    public static final String STATUS_PAY_SUCESS = "5";
    /**
     * 订单状态--保险支付失败
     */
    public static final String STATUS_PAY_FAIL = "6";
    /**
     * 订单状态--订单完成
     */
    public static final String STATUS_FINISH = "7";

    String SHARE_IMAGE_PATH = "SHARE_IMAGE_PATH";
}

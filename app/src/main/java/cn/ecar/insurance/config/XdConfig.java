package cn.ecar.insurance.config;

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


    String PARAM_NEXT_TIME = "PARAM_NEXT_TIME";
    String LOCATION_RESULT = "Location.RESULT";
    /**
     * app SECRET
     */
    String APP_SECRET = "ECAR8888SECRET";
    String APP_ID = "ecar";
    String VERSION = "1.0";

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

    String RESPONSE_T = "EC0000";
    String RESPONSE_MSG = "请求成功";

    String RESPONSE_F = "EC0000F";
    String RESPONSE_MSG_F = "请求失败";

    String CS = "调试";

    // EXTRA

    String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    String EXTRA_VALUE = "EXTRA_VALUE";
    String EXTRA_ARRAY_VALUE = "EXTRA_ARRAY_VALUE";
    String EXTRA_STRING_VALUE = "EXTRA_STRING_VALUE";
    String EXTRA_INT_VALUE = "EXTRA_INT_VALUE";
    String EXTRA_CLASS_VALUE = "EXTRA_CLASS_VALUE";
}

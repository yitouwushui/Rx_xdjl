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
    // 请求码
    int SELECT_REQUEST = 101;

    String RESPONSE_T = "EC0000";

    String RESPONSE_MSG = "请求成功";

    String CS = "调试";

    // EXTRA

    String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    String EXTRA_ARRAY_VALUE = "EXTRA_ARRAY_VALUE";
    String EXTRA_STRING_VALUE = "EXTRA_STRING_VALUE";
    String EXTRA_CLASS_VALUE = "EXTRA_CLASS_VALUE";
}

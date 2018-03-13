package cn.ecar.insurance.rxevent;

/**
 * Created by yx on 2017/5/31.
 * rxbus消息常量
 */

public interface RxCodeConstants {
    int JUMP_TYPE = 0;//code多类型接收
//    int JUMP_TYPE_TO_ONE = 1;//code单类型接收

    int TYPE_USER_LOGIN = 1;//登录
    int TYPE_USER_LOGOUT = 2;//登出
    int TYPE_USERINFO_CHANGED = 3;//用户信息改变
    int TYPE_PHOTO_POSITION = 4;//职业认证
    int TYPE_PHOTO_ID_CARD = 5;//身份认证
    int TYPE_GESTURE_FINISH = 6;//设置手势退出
    int TYPE_MONEY_REFRESH = 7; // 跟新余额
    int TYPE_GETUI_HAVE_NOTIFICATION = 22;//个推接收到通知
    int TYPE_GETUI_CLICK_NOTIFICATION = 23;//用户点击通知
    int TYPE_NETWORK_CHANGED_CONNECTED = 29;//网络状态改变并且有网络时

    int TYPE_MY_INSURANCE_LOCATION = 29;//网络状态改变并且有网络时


}

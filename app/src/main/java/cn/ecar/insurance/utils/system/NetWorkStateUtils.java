package cn.ecar.insurance.utils.system;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdAppContext;

/**
 * Created by yx on 2016/7/15.
 * 判断网络状态工具类
 */
public class NetWorkStateUtils {

    /**
     * 判断是否有网络连接
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        Context context = XdAppContext.app().getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @return
     */
    public static boolean isWifiConnected() {
        Context context = XdAppContext.app().getContext();
        ConnectivityManager mconnectivityManagerManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wiFiNetworkInfo = mconnectivityManagerManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wiFiNetworkInfo != null && wiFiNetworkInfo.isAvailable();
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @return
     */
    public static boolean isMobileConnected() {
        Context context = XdAppContext.app().getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetworkInfo != null && mobileNetworkInfo.isAvailable();
    }

    /**
     * 获取当前网络连接的类型信息
     *
     * @return
     */
    public static int getConnectedType() {
        Context context = XdAppContext.app().getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            return networkInfo.getType();
        }
        return -1;
    }

    /***
     * 判断Network具体类型（联通移动wap，电信wap，其他net）
     *
     * */
    public static int checkNetworkType() {
        try {
            Context context = XdAppContext.app().getContext();
            final ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo mobNetInfoActivity = connectivityManager
                    .getActiveNetworkInfo();
            if (mobNetInfoActivity == null || !mobNetInfoActivity.isAvailable()) {
                // 注意一：
                // NetworkInfo 为空或者不可以用的时候正常情况应该是当前没有可用网络，
                // 但是有些电信机器，仍可以正常联网，
                // 所以当成net网络处理依然尝试连接网络。
                // （然后在socket中捕捉异常，进行二次判断与用户提示）。
                return TYPE_NET_WORK_DISABLED;
            } else {
                // NetworkInfo不为null开始判断是网络类型
                int netType = mobNetInfoActivity.getType();
                if (netType == ConnectivityManager.TYPE_WIFI) {
                    // wifi net处理
                    return TYPE_WIFI;
                } else if (netType == ConnectivityManager.TYPE_MOBILE) {
                    // 注意二：
                    // 判断是否电信wap:
                    // 不要通过getExtraInfo获取接入点名称来判断类型，
                    // 因为通过目前电信多种机型测试发现接入点名称大都为#777或者null，
                    // 电信机器wap接入点中要比移动联通wap接入点多设置一个用户名和密码,
                    // 所以可以通过这个进行判断！

                    boolean is3G = isFastMobileNetwork(context);

                    final Cursor c = context.getContentResolver().query(
                            PREFERRED_APN_URI, null, null, null, null);
                    if (c != null) {
                        c.moveToFirst();
                        final String user = c.getString(c
                                .getColumnIndex("user"));
                        if (!TextUtils.isEmpty(user)) {
                            if (user.startsWith(CTWAP)) {
                                return is3G ? TYPE_CT_WAP : TYPE_CT_WAP_2G;
                            } else if (user.startsWith(CTNET)) {
                                return is3G ? TYPE_CT_NET : TYPE_CT_NET_2G;
                            }
                        }
                    }
                    c.close();

                    // 注意三：
                    // 判断是移动联通wap:
                    // 其实还有一种方法通过getString(c.getColumnIndex("proxy")获取代理ip
                    // 来判断接入点，10.0.0.172就是移动联通wap，10.0.0.200就是电信wap，但在
                    // 实际开发中并不是所有机器都能获取到接入点代理信息，例如魅族M9 （2.2）等...
                    // 所以采用getExtraInfo获取接入点名字进行判断

                    String netMode = mobNetInfoActivity.getExtraInfo();
                    Logger.w("==================netmode:" + netMode);
                    if (netMode != null) {
                        // 通过apn名称判断是否是联通和移动wap
                        netMode = netMode.toLowerCase();

                        if (netMode.equals(CMWAP)) {
                            return is3G ? TYPE_CM_WAP : TYPE_CM_WAP_2G;
                        } else if (netMode.equals(CMNET)) {
                            return is3G ? TYPE_CM_NET : TYPE_CM_NET_2G;
                        } else if (netMode.equals(NET_3G)
                                || netMode.equals(UNINET)) {
                            return is3G ? TYPE_CU_NET : TYPE_CU_NET_2G;
                        } else if (netMode.equals(WAP_3G)
                                || netMode.equals(UNIWAP)) {
                            return is3G ? TYPE_CU_WAP : TYPE_CU_WAP_2G;
                        }
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return TYPE_OTHER;
        }

        return TYPE_OTHER;

    }

    public static final String CTWAP = "ctwap";
    public static final String CTNET = "ctnet";
    public static final String CMWAP = "cmwap";
    public static final String CMNET = "cmnet";
    public static final String NET_3G = "3gnet";
    public static final String WAP_3G = "3gwap";
    public static final String UNIWAP = "uniwap";
    public static final String UNINET = "uninet";

    public static final int TYPE_CT_WAP = 5;
    public static final int TYPE_CT_NET = 6;
    public static final int TYPE_CT_WAP_2G = 7;
    public static final int TYPE_CT_NET_2G = 8;

    public static final int TYPE_CM_WAP = 9;
    public static final int TYPE_CM_NET = 10;
    public static final int TYPE_CM_WAP_2G = 11;
    public static final int TYPE_CM_NET_2G = 12;

    public static final int TYPE_CU_WAP = 13;
    public static final int TYPE_CU_NET = 14;
    public static final int TYPE_CU_WAP_2G = 15;
    public static final int TYPE_CU_NET_2G = 16;

    public static final int TYPE_OTHER = 17;

    public static Uri PREFERRED_APN_URI = Uri
            .parse("content://telephony/carriers/preferapn");

    /**
     * 没有网络
     */
    public static final int TYPE_NET_WORK_DISABLED = 0;

    /**
     * wifi网络
     */
    public static final int TYPE_WIFI = 4;

    private static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;

        }
    }

    /**
     * 获取网络连接状态
     *
     * @return
     */
    public static String getNetWorkState() {
        int checkNetworkType = checkNetworkType();
        String state = null;
        switch (checkNetworkType) {
            case TYPE_WIFI:
                state = "wifi";
                break;
            case TYPE_NET_WORK_DISABLED:
                state = "no network";
                break;
            case TYPE_CT_WAP:
                state = "ctwap";
                break;
            case TYPE_CT_WAP_2G:
                state = "ctwap_2g";
                break;
            case TYPE_CT_NET:
                state = "ctnet";
                break;
            case TYPE_CT_NET_2G:
                state = "ctnet_2g";
                break;
            case TYPE_CM_WAP:
                state = "cmwap";
                break;
            case TYPE_CM_WAP_2G:
                state = "cmwap_2g";
                break;
            case TYPE_CM_NET:
                state = "cmnet";
                break;
            case TYPE_CM_NET_2G:
                state = "cmnet_2g";
                break;
            case TYPE_CU_NET:
                state = "cunet";
                break;
            case TYPE_CU_NET_2G:
                state = "cunet_2g";
                break;
            case TYPE_CU_WAP:
                state = "cuwap";
                break;
            case TYPE_CU_WAP_2G:
                state = "cuwap_2g";
                break;
            case TYPE_OTHER:
                state = "other";
                break;
            default:
                break;
        }


        return state;
    }

    /**
     * 获取手机设备名,app版本号,网络连接情况和app名字
     *
     * @return
     */

    public static String getAppState() {
        Context context = XdAppContext.app().getContext();
        String netWorkState = getNetWorkState();
        //获取设备名,app版本号,app名字
        String model = Build.MODEL;
        String version = VersionCompareUtils.getVersion();
        String appname = context.getResources().getString(R.string.app_name);

        return "手机型号:" + model + ";版本号:" + version + ";网络情况:" + netWorkState + ";app名字:" + appname;

    }


}

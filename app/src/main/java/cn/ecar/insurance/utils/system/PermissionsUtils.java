package cn.ecar.insurance.utils.system;

import android.app.Activity;

import com.tbruyelle.rxpermissions.RxPermissions;

/**
 * Created by ding on 2017/11/29.
 */

public class PermissionsUtils {

    public static RxPermissions mRxPermissions;

    /**
     * 构造方法
     *
     * @param activity
     */
    private PermissionsUtils(Activity activity) {
        if (mRxPermissions == null) {
            mRxPermissions = new RxPermissions(activity);
        }
    }

    /**
     * @param activity
     * @return
     */
    public static RxPermissions initClient(Activity activity) {
        if (mRxPermissions == null) {
            synchronized (PermissionsUtils.class) {
                if (mRxPermissions == null) {
                    mRxPermissions = new RxPermissions(activity);
                }
            }
        }
        return mRxPermissions;
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static RxPermissions getInstance() {
        return initClient(null);
    }

    public static void cleal(){
        mRxPermissions = null;
    }

}

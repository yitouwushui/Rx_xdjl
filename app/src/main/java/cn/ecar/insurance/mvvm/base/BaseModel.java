package cn.ecar.insurance.mvvm.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.MainThread;

import com.orhanobut.logger.Logger;

import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.rxevent.RxBus;
import cn.ecar.insurance.rxevent.RxCodeConstants;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.widget.dialog.LoadingDialog;

/**
 * Created by yx on 2017/8/20.
 * 基类model
 */

public class BaseModel {

    public BaseModel() {
    }

    //获取app上下文
    protected Context getAppContext() {
        return XdAppContext.app().getContext();
    }

    //获取当前activity
    protected Activity getCurrentActivity() {
        return XdAppContext.app().getStartActivity();
    }

    //获取资源
    protected Resources getResources() {
        return XdAppContext.app().getmResources();
    }

    //获取等待加载框
    private LoadingDialog waitDialog;

    @MainThread
    protected LoadingDialog showWaitDialog() {
        if (getCurrentActivity() != null && !getCurrentActivity().isFinishing()) {
            waitDialog = new LoadingDialog(getCurrentActivity());
            waitDialog.setCancelable(false);
            if (waitDialog != null) {
                waitDialog.show();
            }
            return waitDialog;
        }
        return null;
    }

    @MainThread
    protected void hideWaitDialog() {
        if (waitDialog != null) {
            try {
                waitDialog.dismiss();
                waitDialog = null;
            } catch (Exception ex) {
                Logger.w("极其个别的机型,会报错-_-");
            }
        }
    }

    protected boolean loginOut(String responseCode) {
        if (responseCode == null) {
            return false;
        }
        if (XdConfig.RESPONSE_ACCOUNT_FAILURE.equals(responseCode)) {
            RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_USER_LOGOUT);
            ToastUtils.showToast("身份过期，请重新登录");
            return true;
        }
        return false;
    }

    /**
     * 服务器返回的错误信息 抛出异常
     *
     * @param result
     * @param msg
     */
    protected void handingResult(String result, String msg) {
        if (getCurrentActivity() != null && !getCurrentActivity().isFinishing()) {
            if (result.equals("authentication")) {
                //未认证
            } else if (result.equals("token")) {
                //token失效
            } else if (result.equals("fail")) {
                //服务器返回错误
            } else {
                ToastUtils.showToast(msg);
            }
        }
    }

}

package cn.ecar.insurance.xdjl.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import cn.ecar.insurance.xdjl.config.XdConfig;
import cn.ecar.insurance.xdjl.rxevent.RxBus;
import cn.ecar.insurance.xdjl.rxevent.RxCodeConstants;
import cn.ecar.insurance.xdjl.utils.system.NetWorkStateUtils;
import cn.ecar.insurance.xdjl.utils.ui.ToastUtils;

/**
 * Created by yx on 2017/8/10.
 * 网络状态改变监听广播
 */

public class NetStateReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (NetWorkStateUtils.isNetworkConnected()) {
            RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE,RxCodeConstants.TYPE_NETWORK_CHANGED_CONNECTED);

        } else {
            Logger.w("NetStateReceiver NO_NETWORK");
            ToastUtils.showToast(XdConfig.NO_NETWORK);
        }

    }
}

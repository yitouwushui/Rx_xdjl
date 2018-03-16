package cn.ecar.insurance.mvvm.view.act.main;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

import java.lang.ref.WeakReference;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.base.Token;
import cn.ecar.insurance.databinding.ActivityLoadingBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.login.LoginActivity;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;

/**
 * 选择银行，会员单位等
 *
 * @author ding
 */
public class LoadingActivity extends BaseBindingActivity<ActivityLoadingBinding> {

    private long mExitTime = 0L;
    private MyHandler handler;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void setStatusBar() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_loading;
    }

    @Override
    protected void initView() {
        handler = new MyHandler(this);
        try {
            Token token = SpUtils.getData(XdConfig.SESSION_ID, Token.class);
            long current = System.currentTimeMillis();
            if (token != null && token.getSessionId() != null
                    && !"".equals(token.getSessionId()) && token.getExpirationTime() > current) {
                RetrofitUtils.setSessionId(token.getSessionId());
                token.setExpirationTime(current + XdConfig.SESSION_TIME);
                SpUtils.putData(XdConfig.SESSION_ID, token);
                handler.sendEmptyMessageDelayed(1, 2000);
                return;
            }
        } catch (Exception e) {

        }
        handler.sendEmptyMessageDelayed(0, 2000);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnable, 800);

        }
    };

    static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;

        MyHandler(Activity activity) {
            mActivityReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final Activity activity = mActivityReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                        new IntentUtils.Builder(activity)
                                .setTargetActivity(MainActivity.class)
                                .build()
                                .startActivityWithFinishUi(true);
                        break;
                    case 0:
                        new IntentUtils.Builder(activity)
                                .setTargetActivity(LoginActivity.class)
                                .build()
                                .startActivityWithFinishUi(true);
                        break;
                    default:
                }
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000L) {
                ToastUtils.showToast("再按一次退出" + getResources().getString(R.string.app_name));
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

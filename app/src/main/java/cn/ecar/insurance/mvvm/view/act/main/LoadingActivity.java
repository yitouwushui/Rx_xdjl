package cn.ecar.insurance.mvvm.view.act.main;

import android.os.Bundle;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.base.Token;
import cn.ecar.insurance.databinding.ActivityLoadingBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.login.LoginActivity;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;

/**
 * 选择银行，会员单位等
 *
 * @author ding
 */
public class LoadingActivity extends BaseBindingActivity<ActivityLoadingBinding> {

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_loading;
    }

    @Override
    protected void initView() {
        try {

            Token token = SpUtils.getData(XdConfig.SESSION_ID, Token.class);
            long current = System.currentTimeMillis();
            if (token != null && token.getSessionId() != null
                    && !"".equals(token.getSessionId()) && token.getExpirationTime() > current) {
                RetrofitUtils.setSessionId(token.getSessionId());
                token.setExpirationTime(current + XdConfig.SESSION_TIME);
                SpUtils.putData(XdConfig.SESSION_ID, token);
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(MainActivity.class)
                        .build()
                        .startActivityWithFinishUi(true);
                return;
            }
        } catch (Exception e) {

        }
        new IntentUtils.Builder(mContext)
                .setTargetActivity(LoginActivity.class)
                .build()
                .startActivityWithFinishUi(true);
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

}

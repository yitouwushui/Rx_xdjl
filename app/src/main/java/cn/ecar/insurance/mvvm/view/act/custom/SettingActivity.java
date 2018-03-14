package cn.ecar.insurance.mvvm.view.act.custom;

import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.base.Token;
import cn.ecar.insurance.databinding.ActivitySettingBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.login.LoginActivity;
import cn.ecar.insurance.rxevent.RxBus;
import cn.ecar.insurance.rxevent.RxCodeConstants;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * 选择银行，会员单位等
 *
 * @author ding
 */
public class SettingActivity extends BaseBindingActivity<ActivitySettingBinding> implements OnViewClick {

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btLogOut, 1, this);
        RxViewUtils.onViewClick(mVB.tvAboutUs, 1, this);
        RxViewUtils.onViewClick(mVB.tvFeedback, 1, this);
        RxViewUtils.onViewClick(mVB.tvServiceTelephone, 1, this);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_log_out:
                SpUtils.putData(XdConfig.SESSION_ID, new Token("", 0));
                RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE,RxCodeConstants.TYPE_USER_LOGOUT);
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(LoginActivity.class)
                        .build()
                        .startActivityWithFinishUi(true);
                break;
            default:
        }
    }
}

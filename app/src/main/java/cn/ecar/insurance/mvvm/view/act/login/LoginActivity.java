package cn.ecar.insurance.mvvm.view.act.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.ActivityLoginBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.LoginViewModel;
import cn.ecar.insurance.mvvm.viewmodel.main.HomeViewModel;
import cn.ecar.insurance.net.NetServer;
import cn.ecar.insurance.net.NetWorkApi;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class LoginActivity extends BaseBindingActivity<ActivityLoginBinding> implements OnViewClick {

    LoginViewModel mLoginViewModel;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        NetServer netServer = NetWorkApi.getInstance().gradleRetrofit(null).create(NetServer.class);

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btLogin, this);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                String psw = mVB.etHidePsw.getText().toString();
                String account = mVB.etClearAccount.getText().toString();
                mLoginViewModel.login(account, psw);
                break;
        }
    }

}

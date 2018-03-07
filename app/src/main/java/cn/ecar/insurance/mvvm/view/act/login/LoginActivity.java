package cn.ecar.insurance.mvvm.view.act.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.orhanobut.logger.Logger;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActivityLoginBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.main.MainActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.LoginViewModel;
import cn.ecar.insurance.net.NetServer;
import cn.ecar.insurance.net.NetWorkApi;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class LoginActivity extends BaseBindingActivity<ActivityLoginBinding> implements OnViewClick {

    LoginViewModel mLoginViewModel;

    @Override
    public void getBundleExtras(Bundle extras) {
        RetrofitUtils.setSessionId(SpUtils.getString(XdConfig.SESSION_ID));
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        mVB.includeToolbar.toolbar.setVisibility(View.GONE);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        String str = SpUtils.getString(XdConfig.SP_CURRENT);
        mVB.etAccount.setText("".equals(str) ? "13818175906" : str);
        mVB.etPsw.setText("asdf1234");

//        mVB.etAccount.setText(SpUtils.getString(XdConfig.SP_CURRENT));
    }

    @Override
    protected void initData() {
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btLogin, 2, this);
        RxViewUtils.onViewClick(mVB.tvRegister, 2, this);

        mVB.etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (str.contains(" ")) {
                    str = str.replaceAll(" ", "");
                    mVB.etAccount.setText(str);
                }
                if (str.length() > 11) {
                    ToastUtils.showToast("手机号格式错误");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                login();
                break;
            case R.id.tv_register:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(RegisterActivity.class)
                        .build()
                        .startActivity(true);
                break;
            default:
        }
    }

    /**
     * 登录
     */
    private void login() {
        String psw = mVB.etPsw.getText().toString();
        String account = mVB.etAccount.getText().toString();
        SpUtils.putString(XdConfig.SP_CURRENT, account);
        mLoginViewModel.login(account, psw).observe(this, customerGson -> {
            SpUtils.putString(XdConfig.SESSION_ID, customerGson.getSessionId());
            SpUtils.putData(customerGson.getCustomer());
            RetrofitUtils.setSessionId(customerGson.getSessionId());
            loginSuccess();
        });
    }

    private void loginSuccess() {
        new IntentUtils.Builder(mContext)
                .setTargetActivity(MainActivity.class)
                .build()
                .startActivity(true);
    }

}

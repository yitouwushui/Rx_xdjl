package cn.ecar.insurance.mvvm.view.act.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.imnjh.imagepicker.ImageLoader;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.HashMap;

import javax.annotation.Nonnull;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.gson.BaseGson;
import cn.ecar.insurance.databinding.ActivityRegisterBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.LoginViewModel;
import cn.ecar.insurance.net.NetServer;
import cn.ecar.insurance.net.NetWorkApi;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class RegisterActivity extends BaseBindingActivity<ActivityRegisterBinding> implements OnViewClick {

    private LoginViewModel mLoginViewModel;
    private RequestOptions options;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        options = new RequestOptions()
                .placeholder(R.drawable.verify_before)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true);
        loadVerify();
    }

    /**
     * 加载图片验证码
     */
    private void loadVerify() {
        options.signature(new ObjectKey(String.valueOf(System.currentTimeMillis())));
        Glide.with(mContext)
                .load(NetWorkApi.BASE_URL + NetServer.KAPTCHA)
                .apply(options)
                .into(mVB.imgVerify);
    }

    @Override
    protected void initData() {
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btVerify, this);
        RxViewUtils.onViewClick(mVB.btRegister, this);
        RxViewUtils.onViewClick(mVB.imgVerify, this);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_verify:
                loadVerify();
                break;
            case R.id.bt_verify:
                mVB.btVerify.setEnabled(false);
                String phoneNo = mVB.etAccount.getText().toString();
                String captcha = mVB.etVerifyImage.getText().toString();

                if ("".equals(captcha) || "".equals(phoneNo)) {
                    ToastUtils.showToast("请先输入图片验证码和手机号");
                }
                mLoginViewModel.getVerifyCode(phoneNo, captcha).observe(this, new Observer<BaseGson>() {
                    @Override
                    public void onChanged(@Nullable BaseGson baseGson) {
                        if (XdConfig.RESPONSE_T.equals(baseGson.getResponseCode())) {
//                            mVB.btVerify.setTimeCountStart();
                            mVB.btVerify.timeStart();
                        } else {
                            mVB.btVerify.setEnabled(true);
                            ToastUtils.showToast("获取验证码失败");
                        }
                    }
                });
                break;
            case R.id.bt_register:
                String phone = mVB.etAccount.getText().toString();
                String pwd1 = mVB.etPsw1.getText().toString();
                String pwd2 = mVB.etPsw2.getText().toString();
                String codeVerify = mVB.etVerifyCode.getText().toString();
                String invitationPhoneNo = mVB.etInvitationPhone.getText().toString();
                if ("".equals(phone) || "".equals(pwd1)
                        || "".equals(pwd2) || "".equals(codeVerify)
                        || "".equals(codeVerify)) {
                    ToastUtils.showToast("请完整填写信息");
                }
                if (!pwd1.equals(pwd2)) {
                    ToastUtils.showToast("密码不一致");
                }
                HashMap<String, String> hashMap = new HashMap<>(5);
                hashMap.put("phoneNo", phone);
                hashMap.put("password", pwd1);
                hashMap.put("repPassword", pwd2);
                hashMap.put("verifyCode", codeVerify);
                hashMap.put("invitationPhoneNo", invitationPhoneNo);
                mLoginViewModel.register(hashMap);
                break;
            default:
        }
    }
}

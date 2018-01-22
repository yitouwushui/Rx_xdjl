package cn.ecar.insurance.mvvm.view.act.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.text.TextUtils;


import cn.ecar.insurance.R;
import cn.ecar.insurance.dao.base.BaseEntity;
import cn.ecar.insurance.databinding.ActivityForgetPasswordBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.user.UserInfoViewModel;
import cn.ecar.insurance.utils.TextTimerUtils;
import cn.ecar.insurance.utils.ui.CustomUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;
import rx.Subscription;

/**
 * Created by lq on 2017/11/28.
 */

public class ForgetPasswordActivity extends BaseBindingActivity<ActivityForgetPasswordBinding> {

    private Subscription mSubscribeDuanxin;
    private UserInfoViewModel mUserInfoViewModel;
    private boolean getCode = true;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView() {
        mUserInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        mVB.forgetTitle.textTitle.setText("验证身份");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.buttonGetIdentifyingCode, () -> {
            getYanzhengCode("ForgetPasswordOneSMS");
        });

        RxViewUtils.onViewClick(mVB.forgetTitle.linearBack, () -> {
            finishActivity();
        });

        RxViewUtils.onViewClick(mVB.buttonSubmit, () -> {
            if (verify()) {
                if (getCode) {
//                    mUserInfoViewModel.doYanzhengCodeVerification("ForgetPasswordOne", CustomUtils.getString(mVB.editPhoneNum), "code", CustomUtils.getString(mVB.editIdentifyingCode)).observe(this, baseEntity -> {
//                        assert baseEntity != null;
//                        mVB.linearCode.setVisibility(View.GONE);
//                        mVB.linearPwd.setVisibility(View.VISIBLE);
//                        mVB.editPhoneNum.setFocusable(false);
//                        getCode = false;
//                    });
                } else {
//                    mUserInfoViewModel.doYanzhengCodeVerification("ForgetPasswordTwo", CustomUtils.getString(mVB.editPhoneNum), "pwd", CustomUtils.getString(mVB.editPwd)).observe(this, baseEntity -> {
//                        assert baseEntity != null;
//                        RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, CustomUtils.getString(mVB.editPhoneNum));
//                        finishActivity();
//                    });
                }
            }
        });
    }

    @Override
    protected void destroyView() {
        if (mSubscribeDuanxin != null) {
            mSubscribeDuanxin.unsubscribe();
        }
    }

    /**
     * 获取验证码
     */
    private void getYanzhengCode(String type) {
        String phoneNumber = CustomUtils.getString(mVB.editPhoneNum);
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtils.showToast("手机号码不能为空");
        } else if (phoneNumber.length() < 11) {
            ToastUtils.showToast("请输入正确的手机号码");
        } else if (CustomUtils.isChinaPhoneLegal(phoneNumber)) {
//            mUserInfoViewModel.doYanzhengCodeRequest(type, phoneNumber).subscribe(new Subscriber<BaseEntity>() {
//
//                @Override
//                public void onStart() {
//                    super.onStart();
//                    showWaitDialog();
//                }
//
//                @Override
//                public void onCompleted() {
//                    hideWaitDialog();
//                    startRxCountDouwn(type, phoneNumber);
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    hideWaitDialog();
//                    ToastUtils.showToast(e.getMessage());
//                    TextTimerUtils.unsubscribe(mSubscribeDuanxin, mVB.buttonGetIdentifyingCode);
//                }
//
//                @Override
//                public void onNext(BaseEntity baseEntity) {
//                    hideWaitDialog();
//                    if (baseEntity.getResult().equals("suc")) {
//
//                    } else {
//                        handingResult(baseEntity.getResult(), baseEntity.getMsg());
//                        TextTimerUtils.unsubscribe(mSubscribeDuanxin, mVB.buttonGetIdentifyingCode);
//                    }
//                }
//            });
        } else {
            ToastUtils.showToast("手机号码格式不正确");
        }
    }


    /**
     * 开启倒计时
     *
     * @param type
     * @param phoneNumber
     */
    private void startRxCountDouwn(String type, String phoneNumber) {
        //因为会出现用户同时点击两个验证码的情况,所以要多家个判断值
        mSubscribeDuanxin = TextTimerUtils.getSubsrription(mVB.buttonGetIdentifyingCode);
    }


    private boolean verify() {
        if (getCode) {
            if (CustomUtils.getString(mVB.editPhoneNum).length() < 11 || CustomUtils.getString(mVB.editPhoneNum).length() > 11) {
                ToastUtils.showToast("请输入正确的手机号码");
                return false;
            } else if (!CustomUtils.isChinaPhoneLegal(CustomUtils.getString(mVB.editPhoneNum))) {
                ToastUtils.showToast("请输入正确的手机号码");
                return false;
            }
        } else {
            if (!CustomUtils.getString(mVB.editPwd).equals(CustomUtils.getString(mVB.editVerifyPwd))) {
                ToastUtils.showToast("两次密码输入不一致");
                return false;
            } else if (CustomUtils.getString(mVB.editVerifyPwd).length() < 6) {
                ToastUtils.showToast("密码不能小于6位");
                return false;
            }
        }
        return true;
    }


}

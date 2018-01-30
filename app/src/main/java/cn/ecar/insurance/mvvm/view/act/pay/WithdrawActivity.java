package cn.ecar.insurance.mvvm.view.act.pay;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.gson.BankBindGson;
import cn.ecar.insurance.databinding.ActivityWithdrawBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.mvvm.viewmodel.custom.PayViewModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class WithdrawActivity extends BaseBindingActivity<ActivityWithdrawBinding> implements OnViewClick {

    private PayViewModel mPayViewModel;
    private CustomViewModel mCustomViewModel;
    private double balance = -1.0;
    private String fee = "2";

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView() {
        mVB.viewTitle.setTitle("提现");
    }

    @Override
    protected void initData() {
        mPayViewModel = ViewModelProviders.of(this).get(PayViewModel.class);
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btOutMoney, this);
        goToWithdrawals();
    }

    private void goToWithdrawals() {
        showWaitDialog();
        mCustomViewModel.goToWithdrawals().observe(this, balanceGson -> {
            if (XdConfig.RESPONSE_T.equals(balanceGson.getResponseCode())) {
                balance = balanceGson.getBalance();
                mVB.tvWithdraw.setText(String.valueOf(balanceGson.getBalance()));
            } else {
                ToastUtils.showToast("查询可提现金额失败，请点击重新查询");
            }
            hideWaitDialog();
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_out_money:
                submitWithdrawals();
                break;
            case R.id.tv_withdraw:
                goToWithdrawals();
                break;
            default:
        }
    }

    private void submitWithdrawals() {
        if (balance == -1.0) {
            goToWithdrawals();
            return;
        }
        String cash = mVB.etWithdraw.getText().toString();
        if ("".equals(cash)) {
            ToastUtils.showToast("金额格式不正确");
            return;
        }
        BigDecimal b2 = new BigDecimal(balance).subtract(new BigDecimal(fee));
        if ((new BigDecimal(cash).compareTo(b2) == 1)) {
            ToastUtils.showToast("可提现金额不足");
            return;
        }
        Map<String, String> map = RetrofitUtils.getInstance().getParamsMap(
                "cash", cash,
                "formSubmitTime", String.valueOf(System.currentTimeMillis()),
                "fee", fee);
        showWaitDialog();
        mPayViewModel.submitWithdrawals(map).observe(this, bankBindGson -> {
            if (XdConfig.RESPONSE_T.equals(bankBindGson.getResponseCode())) {
                ToastUtils.showToast("提现成功：" + bankBindGson.getCash() + "元");
            } else {
                ToastUtils.showToast(bankBindGson.getResponseMsg());
            }
            hideWaitDialog();
        });
    }

    @Override
    protected void destroyView() {

    }
}

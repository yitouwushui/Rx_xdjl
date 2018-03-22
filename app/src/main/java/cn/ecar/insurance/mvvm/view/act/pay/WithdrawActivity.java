package cn.ecar.insurance.mvvm.view.act.pay;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import java.math.BigDecimal;
import java.util.Map;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.BankBind;
import cn.ecar.insurance.databinding.ActivityWithdrawBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.mvvm.viewmodel.custom.PayViewModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.rxevent.RxBus;
import cn.ecar.insurance.rxevent.RxCodeConstants;
import cn.ecar.insurance.utils.ui.CommonUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
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
    private String binkId;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("提现");

    }

    @Override
    protected void initData() {
        mPayViewModel = ViewModelProviders.of(this).get(PayViewModel.class);
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btOutMoney, this);
        RxViewUtils.onViewClick(mVB.tvWithdraw, this);
        RxViewUtils.onViewClick(mVB.btCard, this);
        goToWithdrawals();
        getBankInfo();
    }

    /**
     * 查询已绑定的银行卡信息
     */
    private void getBankInfo() {
        showWaitDialog();
        mPayViewModel.getBankInfoByWithdrawals().observe(this, bankBindGson -> {
            if (XdConfig.RESPONSE_T.equals(bankBindGson.getResponseCode())) {
                BankBind bankBind = bankBindGson.getBankBindDto();
                if (bankBind == null) {
                    return;
                }
                binkId = String.valueOf(bankBind.getBankId());
                mVB.tvBankCard.setText(bankBind.getBankCardNo());
            } else {
                ToastUtils.showToast("没有查询到已绑定的卡信息");
            }
            hideWaitDialog();
        });
    }

    /**
     * 查询余额
     */
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
            case R.id.bt_card:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(InformationActivity.class)
                        .build()
                        .startActivityForResult(XdConfig.MODIFY_THE_BANK_CARD_REQUEST);
                break;
            default:
        }
    }

    private void submitWithdrawals() {
        if (balance == -1.0) {
            goToWithdrawals();
            return;
        }
        if (binkId == null || "".equals(binkId)) {
            getBankInfo();
        }
        String cash = mVB.etWithdraw.getText().toString();
        if ("".equals(cash) || !CommonUtils.isAmount(cash)) {
            ToastUtils.showToast("金额格式不正确");
            return;
        }
        BigDecimal b2 = new BigDecimal(balance).subtract(new BigDecimal(fee));
        mVB.tvIsTrue.setText("可提:" + b2.toString() + "元");
        if ((new BigDecimal(cash).compareTo(b2) == 1)) {
            ToastUtils.showToast("可提现金额不足");
            return;
        }

        Map<String, String> map = RetrofitUtils.getInstance().getParamsMap(
                "cash", cash,
                "formSubmitTime", String.valueOf(System.currentTimeMillis()),
                "fee", fee,
                "bindId", binkId);
        showWaitDialog();
        mPayViewModel.submitWithdrawals(map).observe(this, bankBindGson -> {
            if (XdConfig.RESPONSE_T.equals(bankBindGson.getResponseCode())) {
                ToastUtils.showToast("提现成功：" + bankBindGson.getCash() + "元");
                RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_PAY_SUCCESS);
                finishActivity();
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

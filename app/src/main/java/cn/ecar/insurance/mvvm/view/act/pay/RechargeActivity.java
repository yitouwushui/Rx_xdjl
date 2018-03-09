package cn.ecar.insurance.mvvm.view.act.pay;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActivityRechargeBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.PayViewModel;
import cn.ecar.insurance.utils.ui.CommonUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class RechargeActivity extends BaseBindingActivity<ActivityRechargeBinding> implements OnViewClick {

    private String org = "1";
    private PayViewModel mPayViewModel;

    @Override
    public void getBundleExtras(Bundle extras) {
        String amount = extras.getString(XdConfig.EXTRA_STRING_VALUE);
        if (amount != null && !"".equals(amount)) {
            mVB.etRechargeMoney.setText(amount);
            mVB.etRechargeMoney.setEnabled(false);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("充值");

    }

    @Override
    protected void initData() {
        mPayViewModel = ViewModelProviders.of(this).get(PayViewModel.class);

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btSubmit,1, this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                String amount = mVB.etRechargeMoney.getText().toString();
                HashMap<String, String> hm = new HashMap<>(2);
                hm.put("org", org);
                hm.put("amount", amount);
                mPayViewModel.submitPay(hm).observe(this, payGson -> {
                    if (payGson != null && XdConfig.RESPONSE_T.equals(payGson.getResponseCode())) {
                        payGson.setParam(CommonUtils.mapToString(payGson.getData()));
                        new IntentUtils.Builder(mContext)
                                .setTargetActivity(PaymentActivity.class)
                                .setParcelableExtra(XdConfig.EXTRA_VALUE, payGson)
                                .build().startActivity(true);
                    } else {
                        ToastUtils.showToast(payGson.getResponseMsg());
                    }
                });
//                } else {
//                    ToastUtils.showToast("金额格式错误");
//                }
                break;
            default:
        }
    }

    @Override
    protected void destroyView() {

    }
}

package cn.ecar.insurance.mvvm.view.act.pay;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.View;


import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActivityPayBinding;
import cn.ecar.insurance.databinding.ActivityRechargeBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.PayViewModel;
import cn.ecar.insurance.utils.encrypt.MD5Helper;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.CommonUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class PayActivity extends BaseBindingActivity<ActivityPayBinding> implements OnViewClick {

    private String org = "1";
    private PayViewModel mPayViewModel;
    private String orderNo = "";
    private String amount = "";


    @Override
    public void getBundleExtras(Bundle extras) {
        amount = extras.getString("payAmount");
        orderNo = extras.getString("orderNo");
        if (amount != null && !"".equals(amount)) {
            mVB.tvRechargeMoney.setText(amount);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("收银台");

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
                ArrayMap<String, String> map = new ArrayMap<>(7);
                map.put("org", org);
                map.put("orderNo", orderNo);
                map.put("amount", amount);
                map.put("version", OtherUtil.getVersionName(mContext));
                map.put("timestamp", String.valueOf(System.currentTimeMillis()));
                map.put("appId", XdConfig.APP_ID);
                String sign = null;
                try {
                    sign = MD5Helper.getSign(map, XdConfig.APP_SECRET, "UTF-8");
                    map.put("sign", sign);
                    mPayViewModel.commitInsuranceOrder(map).observe(this, payGson -> {
                        if (payGson != null) {
                            payGson.setParam(CommonUtils.mapToString(payGson.getData()));
                            new IntentUtils.Builder(mContext)
                                    .setTargetActivity(PaymentActivity.class)
                                    .setParcelableExtra(XdConfig.EXTRA_VALUE, payGson)
                                    .build().startActivity(true);
                        }
                    });
                } catch (Exception e) {
                    ToastUtils.showToast("出错了");
                }
                break;
            default:
        }
    }

    @Override
    protected void destroyView() {

    }
}

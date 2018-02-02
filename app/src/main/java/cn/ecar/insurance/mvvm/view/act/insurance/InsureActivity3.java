package cn.ecar.insurance.mvvm.view.act.insurance;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActivityInsure3Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.InsuranceViewModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class InsureActivity3 extends BaseBindingActivity<ActivityInsure3Binding> implements OnViewClick {

    private String bsinessexpiredate = "";
    private InsuranceViewModel mInsuranceViewModel;

    @Override
    public void getBundleExtras(Bundle extras) {
        bsinessexpiredate = extras.getString(XdConfig.EXTRA_STRING_VALUE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure3;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("车险");
    }

    @Override
    protected void initData() {
        mInsuranceViewModel = ViewModelProviders.of(this).get(InsuranceViewModel.class);
        mInsuranceViewModel.getInsuranceOfferList(RetrofitUtils.getInstance().getParamsMap("businessExpireDate", bsinessexpiredate))
                .observe(this, cateMapGson -> {
                    if (cateMapGson != null && XdConfig.RESPONSE_T.equals(cateMapGson.getResponseCode())) {
                        String businessStartDate = cateMapGson.getBusinessStartDate();
                        String forceStartDate = cateMapGson.getForceStartDate();
                    } else {
                        ToastUtils.showToast("数据请求失败");
                    }
                });
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btNext, this);

    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(InsureActivity4.class)
                        .build().startActivity(true);
                break;
            default:
        }
    }
}

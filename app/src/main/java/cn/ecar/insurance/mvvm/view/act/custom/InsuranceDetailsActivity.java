package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.InsuranceDetails;
import cn.ecar.insurance.databinding.ActivityInsuranceDetailBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class InsuranceDetailsActivity extends BaseBindingActivity<ActivityInsuranceDetailBinding> implements OnViewClick {

    CustomViewModel mCustomViewModel;
    InsuranceDetails mInsuranceDetails;

    @Override
    public void getBundleExtras(Bundle extras) {
        mInsuranceDetails = extras.getParcelable(XdConfig.EXTRA_VALUE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insurance_detail;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("保单详情");
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);

    }

    @Override
    protected void initEvent() {
//        RxViewUtils.onViewClick(mVB.btNext, this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void destroyView() {

    }
}

package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Team;
import cn.ecar.insurance.databinding.ActivityInsuranceDetailBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class TeamDetailsActivity extends BaseBindingActivity<ActivityInsuranceDetailBinding> implements OnViewClick {

    CustomViewModel mCustomViewModel;
    Team team;

    @Override
    public void getBundleExtras(Bundle extras) {
        team = extras.getParcelable(XdConfig.EXTRA_VALUE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insurance_detail;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("我的团队");
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

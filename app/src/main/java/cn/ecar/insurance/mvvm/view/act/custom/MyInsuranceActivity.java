package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.MyInsuranceAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Insurance;
import cn.ecar.insurance.databinding.ActivityMyInsuranceBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class MyInsuranceActivity extends BaseBindingActivity<ActivityMyInsuranceBinding> implements OnViewClick {

    CustomViewModel mCustomViewModel;


    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_insurance;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("我的保单");
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        mCustomViewModel.getMyInsuranceList().observe(this, insuranceGson -> {
            if (insuranceGson != null && XdConfig.RESPONSE_T.equals(insuranceGson.getResponseCode())) {
                mVB.listViewMyInsurance.setAdapter(
                        new MyInsuranceAdapter(mContext, R.layout.item_list_my_insurance, insuranceGson.getData())
                );
                mVB.listViewMyInsurance.setOnItemClickListener((parent, view, position, id) -> {
                    //
                    Insurance insurance = (Insurance) mVB.listViewMyInsurance.getAdapter().getItem(position);
                    new IntentUtils.Builder(mContext)
                            .setParcelableExtra(XdConfig.EXTRA_VALUE, insurance)
                            .setTargetActivity(InsuranceDetailsActivity.class)
                            .build()
                            .startActivity(true);
                });
            } else {
                ToastUtils.showToast("获取保单失败");
            }
        });
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

package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.MyInsuranceAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.OrderBean;
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
    int indexPage = 1;

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
        mCustomViewModel.getInsuranceOrderByPage(String.valueOf(indexPage)).observe(this, insuranceGson -> {
            if (insuranceGson != null && XdConfig.RESPONSE_T.equals(insuranceGson.getResponseCode())) {
                mVB.listViewMyInsurance.setAdapter(
                        new MyInsuranceAdapter(mContext, R.layout.item_list_my_insurance, insuranceGson.getList())
                );
                mVB.listViewMyInsurance.setOnItemClickListener((parent, view, position, id) -> {
                    //
                    OrderBean insuranceDetails = (OrderBean) mVB.listViewMyInsurance.getAdapter().getItem(position);
                    new IntentUtils.Builder(mContext)
                            .setStringExtra(XdConfig.EXTRA_STRING_VALUE, insuranceDetails.getOrderNo())
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

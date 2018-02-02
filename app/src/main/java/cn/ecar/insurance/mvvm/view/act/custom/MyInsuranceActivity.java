package cn.ecar.insurance.mvvm.view.act.custom;

import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.ActivityMyInsuranceBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class MyInsuranceActivity extends BaseBindingActivity<ActivityMyInsuranceBinding> implements OnViewClick {


    @Override
    public void onClick(View view) {

    }

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_insurance;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }
}

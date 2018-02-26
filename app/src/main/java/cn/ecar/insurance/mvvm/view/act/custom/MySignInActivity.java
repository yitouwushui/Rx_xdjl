package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.MySignInAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActivitySignInBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class MySignInActivity extends BaseBindingActivity<ActivitySignInBinding> implements OnViewClick {

    CustomViewModel mCustomViewModel;


    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("我的签到");
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        mCustomViewModel.getMySignInList().observe(this, signInGson -> {
            if (signInGson != null && XdConfig.RESPONSE_T.equals(signInGson.getResponseCode())) {
                mVB.listViewMySignIn.setAdapter(
                        new MySignInAdapter(mContext, R.layout.item_list_my_sign_in, signInGson.getData())
                );
            } else {
                ToastUtils.showToast("获取签到信息失败");
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

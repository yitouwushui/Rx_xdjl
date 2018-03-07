package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.MySignInAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActicityPersonalBinding;
import cn.ecar.insurance.databinding.ActivitySignInBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 * @date 2018/2/2
 */

public class PersonalActivity extends BaseBindingActivity<ActicityPersonalBinding> implements OnViewClick {

    CustomViewModel mCustomViewModel;


    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.acticity_personal;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("个人资料");
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
//        mCustomViewModel.getMySignInList().observe(this, signInGson -> {
//            if (signInGson != null && XdConfig.RESPONSE_T.equals(signInGson.getResponseCode())) {
//
//            } else {
//                ToastUtils.showToast("获取签到信息失败");
//            }
//        });
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btAddress, 1, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_address:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(AddressActivity.class)
                        .build()
                        .startActivity(true);
                break;
            default:
        }
    }

    @Override
    protected void destroyView() {

    }
}

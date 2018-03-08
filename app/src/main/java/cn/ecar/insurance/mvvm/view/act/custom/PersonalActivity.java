package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.MySignInAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.databinding.ActicityPersonalBinding;
import cn.ecar.insurance.databinding.ActivitySignInBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.encrypt.MD5Helper;
import cn.ecar.insurance.utils.system.OtherUtil;
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
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btAddress, 1, this);
        RxViewUtils.onViewClick(mVB.btComparison, 1, this);
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
            case R.id.bt_comparison:
                try {
                    Map<String, String> map = RetrofitUtils.getInstance().getParamsMap(
                    );

                    map.put("version", OtherUtil.getVersionName(mContext));
                    map.put("timestamp", String.valueOf(System.currentTimeMillis()));
                    map.put("appId", XdConfig.APP_ID);
                    String sign = null;

                    sign = MD5Helper.getSign(map, XdConfig.APP_SECRET, "UTF-8");

                    map.put("sign", sign);
                    mCustomViewModel.joinShow(map).observe(this, baseGson -> {
                        if (XdConfig.RESPONSE_T.equals(baseGson.getResponseCode())) {
                            ToastUtils.showToast("加入评比成功");
                        } else {
                            ToastUtils.showToast(baseGson.getResponseMsg());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }

    @Override
    protected void destroyView() {

    }
}

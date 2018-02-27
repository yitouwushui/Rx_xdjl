package cn.ecar.insurance.mvvm.view.act.insurance;

import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.SaveQuote;
import cn.ecar.insurance.dao.bean.UserInfo;
import cn.ecar.insurance.databinding.ActivityInsure2Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.TimeUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class InsureActivity2 extends BaseBindingActivity<ActivityInsure2Binding> implements OnViewClick {

    private SaveQuote saveQuote;
    private UserInfo userInfo;

    @Override
    public void getBundleExtras(Bundle extras) {
        userInfo = extras.getParcelable("UserInfo");
        saveQuote = extras.getParcelable("SaveQuote");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure2;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("车险");
        mVB.tvLicenseNo.setText(userInfo.getLicenseno());
        mVB.tvModleName.setText(userInfo.getModlename());
        mVB.tvCarVin.setText(userInfo.getCarvin());
        mVB.tvEngineNo.setText(userInfo.getEngineno());
        mVB.tvLicenseOwner.setText(userInfo.getLicenseowner());
        mVB.tvRegisterDate.setText(TimeUtils.getStringByTime(userInfo.getRegisterdate()));
        mVB.tvForceExpireDate.setText("");
        mVB.tvBusinessExpireDate.setText(TimeUtils.getStringByDate(userInfo.getBusinessexpiredate()));
        mVB.tvQuoteGroup.setText(saveQuote.getSavequoteId() + "");
        mVB.tvBuJiMianCheSun.setText(saveQuote.getBujimianchesun() + "元");
        mVB.tvBuJiMianSanZhe.setText(saveQuote.getBujimiansanzhe() + "元");
        mVB.tvBuJiMianDaoQiang.setText(saveQuote.getBujimiandaoqiang() + "元");
    }

    @Override
    protected void initData() {

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
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, TimeUtils.getStringByDate(userInfo.getBusinessexpiredate()))
                        .setTargetActivity(InsureActivity3.class)
                        .build().startActivity(true);
                break;
            default:
        }
    }
}

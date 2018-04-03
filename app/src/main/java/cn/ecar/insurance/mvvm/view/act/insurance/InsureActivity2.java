package cn.ecar.insurance.mvvm.view.act.insurance;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.SaveQuote;
import cn.ecar.insurance.dao.bean.SubmitInsurance;
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
        userInfo = UserInfo.getInstance();
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
        if (userInfo.getBusinessexpiredate() < System.currentTimeMillis()) {
            mVB.tvExpireDate.setText("0");
        }
        mVB.tvSourceName.setText(saveQuote.getSourceName());

        mVB.tvBuJiMianCheSun.setText(((int) saveQuote.getBujimianchesun()) == 1 ? "车辆损失险(不计免赔)" : "车辆损失险");

        mVB.tvBuJiMianSanZhe.setText(((int) saveQuote.getBujimiansanzhe()) == 1 ? "第三者责任险(不计免赔)" : "第三者责任险");

        mVB.tvBuJiMianDaoQiang.setText(((int) saveQuote.getBujimiandaoqiang()) == 1 ? "全车盗抢险(不计免赔)" : "全车盗抢险");

        mVB.tvCheSun.setText(saveQuote.getChesun() + "元");
        mVB.tvSanZhe.setText(saveQuote.getSanzhe() + "元");
        mVB.tvDaoQiang.setText(saveQuote.getDaoqiang() + "元");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btNext, 2, this);

    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(InsureActivity3.class)
                        .build().startActivity(true);
                break;
            default:
        }
    }
}

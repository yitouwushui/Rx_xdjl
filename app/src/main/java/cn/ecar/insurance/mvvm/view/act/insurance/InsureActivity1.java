package cn.ecar.insurance.mvvm.view.act.insurance;

import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActivityInsure1Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.main.MutiSelectActivity;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class InsureActivity1 extends BaseBindingActivity<ActivityInsure1Binding> implements OnViewClick {


    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure1;
    }

    @Override
    protected void initView() {
        mVB.viewTitle.setTitle("车险1");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.lBtPlate, this);
        RxViewUtils.onViewClick(mVB.lBtRegion, this);
        RxViewUtils.onViewClick(mVB.btNext, this);

    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.l_bt_plate:
                break;
            case R.id.l_bt_region:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(MutiSelectActivity.class)
                        .build().startActivityForResult(XdConfig.LOCATION_MUTISELECT_REQUEST);
                break;
            case R.id.bt_next:
                break;
            default:
        }
    }
}

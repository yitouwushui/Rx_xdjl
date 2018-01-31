package cn.ecar.insurance.mvvm.view.act.insurance;

import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActivityInsure1Binding;
import cn.ecar.insurance.databinding.ActivityInsure2Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.main.MutiSelectActivity;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class InsureActivity2 extends BaseBindingActivity<ActivityInsure2Binding> implements OnViewClick {


    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure2;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("车险");
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
                        .setTargetActivity(InsureActivity3.class)
                        .build().startActivity(true);
                break;
            default:
        }
    }
}

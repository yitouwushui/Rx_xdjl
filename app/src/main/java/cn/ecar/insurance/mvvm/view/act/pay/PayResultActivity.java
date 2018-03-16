package cn.ecar.insurance.mvvm.view.act.pay;

import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.ActivityPaySuccessBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.main.MainActivity;
import cn.ecar.insurance.rxevent.RxBus;
import cn.ecar.insurance.rxevent.RxCodeConstants;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class PayResultActivity extends BaseBindingActivity<ActivityPaySuccessBinding> implements OnViewClick {


    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pay_success;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("支付结果");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btReturnHome, 1, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_return_home:
                RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_PAY_SUCCESS);
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(MainActivity.class)
                        .build()
                        .startActivityWithFinishUi(true);
                break;
            default:
        }
    }

    @Override
    protected void destroyView() {

    }

}

package cn.ecar.insurance.mvvm.view.act.insurance;

import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.ActivityInsure6Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class InsureActivity6 extends BaseBindingActivity<ActivityInsure6Binding> implements OnViewClick {


    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure6;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("车险--确认订单");
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btNext, this);
        RxViewUtils.onViewClick(mVB.lBtShenfengzhen1, this);
        RxViewUtils.onViewClick(mVB.lBtShenfengzhen2, this);
        RxViewUtils.onViewClick(mVB.lBtJiashizheng, this);
        RxViewUtils.onViewClick(mVB.lBtYingyezhizhao, this);
        RxViewUtils.onViewClick(mVB.lBtKaipiaozhiliao, this);

    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                break;
            case R.id.l_bt_shenfengzhen1:
                break;
            case R.id.l_bt_shenfengzhen2:
                break;
            case R.id.l_bt_jiashizheng:
                break;
            case R.id.l_bt_yingyezhizhao:
                break;
            case R.id.l_bt_kaipiaozhiliao:
                break;
            default:
        }
    }
}

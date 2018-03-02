package cn.ecar.insurance.mvvm.view.act.insurance;

import android.os.Bundle;
import android.view.View;

import java.math.BigDecimal;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.databinding.ActivityInsure5Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class InsureActivity5 extends BaseBindingActivity<ActivityInsure5Binding> implements OnViewClick {

    private OrderBean orderBean;

    @Override
    public void getBundleExtras(Bundle extras) {
        orderBean = extras.getParcelable(XdConfig.EXTRA_VALUE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure5;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("方案详情");
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
                        .setTargetActivity(InsureActivity6.class)
                        .build().startActivity(true);
                break;
            case R.id.bt_calculate:
                BigDecimal percent = BigDecimal.valueOf(100);
                BigDecimal force = new BigDecimal(mVB.tvForce.getText().toString()).divide(percent);
                BigDecimal business = new BigDecimal(mVB.tvBusiness.getText().toString()).divide(percent);
//                BigDecimal
                break;
            default:
        }
    }
}

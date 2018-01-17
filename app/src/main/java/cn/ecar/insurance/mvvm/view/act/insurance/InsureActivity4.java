package cn.ecar.insurance.mvvm.view.act.insurance;

import android.os.Bundle;
import android.view.View;

import java.util.TreeMap;
import java.util.TreeSet;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.ActivityInsure4Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class InsureActivity4 extends BaseBindingActivity<ActivityInsure4Binding> implements OnViewClick {


    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure4;
    }

    @Override
    protected void initView() {
        mVB.viewTitle.setTitle("车险4");
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
                break;
            default:
        }
    }
}

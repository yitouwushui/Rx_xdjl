package cn.ecar.insurance.xdjl.mvvm.view.act.main;

import android.os.Bundle;
import android.view.View;


import cn.ecar.insurance.xdjl.R;
import cn.ecar.insurance.xdjl.databinding.LayoutMainBinding;
import cn.ecar.insurance.xdjl.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.xdjl.mvvm.view.frag.HomeFragment;
import cn.ecar.insurance.xdjl.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.xdjl.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 * @date 2017/12/18
 */

public class MainActivity extends BaseBindingActivity<LayoutMainBinding> implements OnViewClick {

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_main;
    }

    @Override
    protected void initView() {
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_main, homeFragment).commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btHome, this);
        RxViewUtils.onViewClick(mVB.btList, this);
        RxViewUtils.onViewClick(mVB.btShare, this);
        RxViewUtils.onViewClick(mVB.btMember, this);
        RxViewUtils.onViewClick(mVB.btMe, this);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_home:
                break;
            case R.id.bt_list:
                break;
            case R.id.bt_list:
                break;
                default:
        }
    }
}

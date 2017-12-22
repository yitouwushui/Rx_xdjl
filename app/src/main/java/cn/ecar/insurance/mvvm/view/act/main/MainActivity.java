package cn.ecar.insurance.mvvm.view.act.main;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;


import com.tbruyelle.rxpermissions.RxPermissions;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.LayoutMainBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.frag.HomeFragment;
import cn.ecar.insurance.mvvm.view.frag.ListFragment;
import cn.ecar.insurance.mvvm.view.frag.MeFragment;
import cn.ecar.insurance.mvvm.view.frag.MemberFragment;
import cn.ecar.insurance.mvvm.view.frag.ShareFragment;
import cn.ecar.insurance.utils.system.PermissionsUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;
import rx.functions.Action1;

/**
 * @author ding
 * @date 2017/12/18
 */

public class MainActivity extends BaseBindingActivity<LayoutMainBinding> implements OnViewClick {

    private HomeFragment homeFragment;
    private ListFragment listFragment;
    private ShareFragment shareFragment;
    private MemberFragment memberFragment;
    private MeFragment meFragment;
    private FragmentManager fm;
    private Fragment currentFragment;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_main;
    }

    @Override
    protected void initView() {
        homeFragment = new HomeFragment();
        fm = getSupportFragmentManager();
        addAndShowFragment(homeFragment);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_WIFI_STATE).subscribe(permission -> {
                    if (permission.granted) {
//                        ToastUtils.showToast("授权成功");
                    } else if (permission.shouldShowRequestPermissionRationale){

                    } else {
                        ToastUtils.showToast("您没有授权该权限，请在设置中打开授权");
                    }
                }
        );
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
        Fragment show = null;
        switch (view.getId()) {
            case R.id.bt_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    addAndShowFragment(homeFragment);
                } else {
                    show = homeFragment;
                }
                break;
            case R.id.bt_list:
                if (listFragment == null) {
                    listFragment = new ListFragment();
                    addAndShowFragment(listFragment);
                } else {
                    show = listFragment;
                }
                break;
            case R.id.bt_share:
                if (shareFragment == null) {
                    shareFragment = new ShareFragment();
                    addAndShowFragment(shareFragment);
                } else {
                    show = shareFragment;
                }
                break;
            case R.id.bt_member:
                if (memberFragment == null) {
                    memberFragment = new MemberFragment();
                    addAndShowFragment(memberFragment);
                } else {

                    show = memberFragment;
                }
                break;
            case R.id.bt_me:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    addAndShowFragment(meFragment);
                } else {
                    show = meFragment;
                }
                break;
            default:
        }
        showFragment(show);
    }

    /**
     * 添加fragment
     *
     * @param fragment
     */
    private void addAndShowFragment(Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }
        ft.add(R.id.frame_main, fragment);
        ft.show(fragment).commit();
        currentFragment = fragment;

    }

    /**
     * 显示相应的fragment
     *
     * @param showFragment
     */
    private void showFragment(Fragment showFragment) {
        if (showFragment == null || showFragment == currentFragment) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }
        currentFragment = showFragment;
        ft.show(showFragment).commit();
    }
}
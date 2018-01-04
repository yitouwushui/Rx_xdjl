package cn.ecar.insurance.mvvm.view.act.main;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.tbruyelle.rxpermissions.RxPermissions;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.LayoutMainBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.frag.HomeFragment;
import cn.ecar.insurance.mvvm.view.frag.ListFragment;
import cn.ecar.insurance.mvvm.view.frag.MeFragment;
import cn.ecar.insurance.mvvm.view.frag.MemberFragment;
import cn.ecar.insurance.mvvm.viewmodel.main.ShareViewModel;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;
import cn.ecar.insurance.widget.dialog.AlertDialog;

/**
 * @author ding
 * @date 2017/12/18
 */

public class MainActivity extends BaseBindingActivity<LayoutMainBinding> implements OnViewClick {

    private HomeFragment homeFragment;
    private ListFragment listFragment;
//    private ShareFragment shareFragment;
    private MemberFragment memberFragment;
    private MeFragment meFragment;
    private FragmentManager fm;
    private Fragment currentFragment;
    private PopupWindow mShareWindow;
    private ShareViewModel mShareViewModel;
    private PopupHolder mPopupHolder;


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
        // 检测权限
        final RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
        boolean granted = rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) && rxPermissions.isGranted(Manifest.permission.READ_EXTERNAL_STORAGE);
        //如果没有被授权
        if (!granted) {
            showPermissionsDialog(rxPermissions);
        }
    }


    @Override
    protected void initData() {
        mShareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);
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
//                if (shareFragment == null) {
//                    shareFragment = new ShareFragment();
//                    addAndShowFragment(shareFragment);
//                } else {
//                    show = shareFragment;
//                }
//                break;
                if (mShareWindow == null) {
                    initPopupWindow();
                    mShareViewModel.getShareQRCode("这是一份测试数据").observe(
                            this,
                            bitmap -> {
                                //显示二维码
                                mPopupHolder.imgCode.setImageBitmap(bitmap);
                            });
                }
                mShareWindow.showAtLocation(mVB.viewMain, Gravity.CENTER, 0, 0);
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
     * 初始化
     */
    private void initPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.fragment_share, null);
        mShareWindow = new PopupWindow(
                contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                true);

        mShareWindow.setAnimationStyle(R.style.shareWindowAnim);
        mShareWindow.setOutsideTouchable(true);
        mShareWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupHolder = new PopupHolder(contentView);
    }

    public class PopupHolder {
        ImageView imgCode;

        public PopupHolder(View view) {
            imgCode = view.findViewById(R.id.img_code);
        }
    }

    /**
     * 获取授权
     *
     * @param rxPermissions
     */
    private void showPermissionsDialog(RxPermissions rxPermissions) {
        AlertDialog permissionsDialog = new AlertDialog(
                this,
                "应用需要授权文件读取权限，用于证件，二维码存储分享。我们承诺不会涉及您的隐私，请授权");
        permissionsDialog.setCancelable(false);
        permissionsDialog.setCanceledOnTouchOutside(false);
        permissionsDialog.setCancelClickListener(dialog -> MainActivity.this.onBackPressed());
        permissionsDialog.setConfirmClickListener(
                dialog -> {
                    final int[] count = {0};
                    rxPermissions.requestEach(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.ACCESS_WIFI_STATE).subscribe(
                            permission -> {
                                if (permission.granted) {
                                    // 某条权限授权成功
                                    count[0]++;
                                    if (count[0] == 4) {
                                        ToastUtils.showToast("授权成功");
                                        dialog.cancel();
                                    }
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    ToastUtils.showToast("部分未授权，请重新获取");
                                } else {
                                    ToastUtils.showToast("您没有授权该权限，请在设置-应用权限管理中打开授权");
                                }
                            });
                }
        );
        permissionsDialog.show();
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

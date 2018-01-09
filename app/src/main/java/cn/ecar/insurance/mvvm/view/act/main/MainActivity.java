package cn.ecar.insurance.mvvm.view.act.main;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.LayoutMainBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.frag.HomeFragment;
import cn.ecar.insurance.mvvm.view.frag.ListFragment;
import cn.ecar.insurance.mvvm.view.frag.MeFragment;
import cn.ecar.insurance.mvvm.view.frag.MemberFragment;
import cn.ecar.insurance.mvvm.viewmodel.main.ShareViewModel;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;
import cn.ecar.insurance.widget.dialog.AlertDialog;

/**
 * @author ding
 * @date 2017/12/18
 */

public class MainActivity extends BaseBindingActivity<LayoutMainBinding> implements OnViewClick {

    private static final int MAIN_BAR_LENGTH = 4;
    private HomeFragment homeFragment;
    private ListFragment listFragment;
    private MemberFragment memberFragment;
    private MeFragment meFragment;
    private FragmentManager fm;
    private PopupWindow mShareWindow;
    private ShareViewModel mShareViewModel;
    private PopupHolder mPopupHolder;

    //导航栏控件
    private TextView[] mBarTextArray = new TextView[MAIN_BAR_LENGTH];
    private ImageView[] mBarImgArray = new ImageView[MAIN_BAR_LENGTH];
    private Drawable[] mDrawableGray = new Drawable[MAIN_BAR_LENGTH];
    private Drawable[] mDrawableBlue = new Drawable[MAIN_BAR_LENGTH];
    private Fragment[] mFragmentArray = new Fragment[MAIN_BAR_LENGTH];
    // 导航栏颜色
    int colorBule, colorGray;
    int currentPosition;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_main;
    }

    @Override
    protected void setStatusBar() {
//        if (OtherUtil.getSDKInt() >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
//        }
    }

    @Override
    protected void initView() {
//        if (OtherUtil.getSDKInt() >= Build.VERSION_CODES.LOLLIPOP) {

//        }
//        if (OtherUtil.getSDKInt() >= Build.VERSION_CODES.LOLLIPOP) {
//            // 5.0以上的系统，全屏式状态栏
//            int barHeight = OtherUtil.getStatusBarHeight(mContext);
////            OtherUtil.setBarColorAll(this, true);
////            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mVB.statusBarMain.getLayoutParams();
////            layoutParams.height = barHeight;
////            mVB.statusBarMain.setLayoutParams(layoutParams);
////            mVB.statusBarMain.setVisibility(View.VISIBLE);
//            View view = new ImageView(mContext);
//            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,barHeight);
//            view.setLayoutParams(layoutParams);
//            view.setBackground(mResources.getDrawable(R.drawable.me_top_bg_1));
//        }
        mBarTextArray[0] = mVB.tvHome;
        mBarTextArray[1] = mVB.tvList;
        mBarTextArray[2] = mVB.tvMember;
        mBarTextArray[3] = mVB.tvMe;
        mBarImgArray[0] = mVB.imgHome;
        mBarImgArray[1] = mVB.imgList;
        mBarImgArray[2] = mVB.imgMember;
        mBarImgArray[3] = mVB.imgMe;
        Resources resources = getResources();
        mDrawableGray[0] = resources.getDrawable(R.mipmap.main_bar_home_g);
        mDrawableGray[1] = resources.getDrawable(R.mipmap.main_bar_list_g);
        mDrawableGray[2] = resources.getDrawable(R.mipmap.main_bar_member_g);
        mDrawableGray[3] = resources.getDrawable(R.mipmap.main_bar_me_g);
        mDrawableBlue[0] = resources.getDrawable(R.mipmap.main_bar_home_b);
        mDrawableBlue[1] = resources.getDrawable(R.mipmap.main_bar_list_b);
        mDrawableBlue[2] = resources.getDrawable(R.mipmap.main_bar_member_b);
        mDrawableBlue[3] = resources.getDrawable(R.mipmap.main_bar_me_b);
        colorBule = resources.getColor(R.color.main_blue);
        colorGray = resources.getColor(R.color.main_gray);

        homeFragment = new HomeFragment();
        fm = getSupportFragmentManager();
        currentPosition = 0;
        addAndShowFragment(homeFragment, currentPosition);


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
        Integer nextPosition = null;
        int currentPosition = this.currentPosition;
        switch (view.getId()) {
            case R.id.bt_home:
                nextPosition = 0;
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    addAndShowFragment(homeFragment, nextPosition);
                } else {
                    showFragment(mFragmentArray[nextPosition], mFragmentArray[currentPosition]);
                }
                break;
            case R.id.bt_list:
                nextPosition = 1;
                if (listFragment == null) {
                    listFragment = new ListFragment();
                    addAndShowFragment(listFragment, nextPosition);
                } else {
                    showFragment(mFragmentArray[nextPosition], mFragmentArray[currentPosition]);
                }
                break;
            case R.id.bt_share:
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
                nextPosition = 2;
                if (memberFragment == null) {
                    memberFragment = new MemberFragment();
                    addAndShowFragment(memberFragment, nextPosition);
                } else {
                    showFragment(mFragmentArray[nextPosition], mFragmentArray[currentPosition]);
                }
                break;
            case R.id.bt_me:
                nextPosition = 3;
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    addAndShowFragment(meFragment, nextPosition);
                } else {
                    showFragment(mFragmentArray[nextPosition], mFragmentArray[currentPosition]);
                }
                break;
            default:
        }
        replaceBar(nextPosition);
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
     * 首次初始化添加fragment
     *
     * @param showFragment
     */
    private void addAndShowFragment(Fragment showFragment, int position) {
        if (showFragment == null) {
            return;
        }
        Fragment currentFragment = mFragmentArray[currentPosition];
        FragmentTransaction ft = fm.beginTransaction();
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }
        mFragmentArray[position] = showFragment;
        ft.add(R.id.frame_main, showFragment);
        ft.show(showFragment).commit();
    }

    /**
     * 显示相应的fragment
     *
     * @param showFragment
     */
    private void showFragment(Fragment showFragment, Fragment currentFragment) {
        if (showFragment == null) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }
        ft.show(showFragment).commit();
    }

    private void replaceBar(Integer nextPosition) {
        if (nextPosition == null || nextPosition == currentPosition) {
            return;
        }
        int current = currentPosition;
        mBarTextArray[current].setTextColor(colorGray);
        mBarImgArray[current].setImageDrawable(mDrawableGray[current]);

        mBarTextArray[nextPosition].setTextColor(colorBule);
        mBarImgArray[nextPosition].setImageDrawable(mDrawableBlue[nextPosition]);

        currentPosition = nextPosition;
    }

}

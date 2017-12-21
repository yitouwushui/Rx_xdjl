package cn.ecar.insurance.mvvm.view.frag;


import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import com.tbruyelle.rxpermissions.RxPermissions;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.FragmentShareBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.mvvm.viewmodel.main.ShareViewModel;
import cn.ecar.insurance.utils.ui.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author ding
 */
public class ShareFragment extends BaseBindingFragment<FragmentShareBinding> {

    private ShareViewModel shareViewModel;

    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_share;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        shareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);

        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.requestEach(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_WIFI_STATE).subscribe(permission -> {
                    if (permission.granted) {
                        shareViewModel.getShareQRCode("这是一份测试数据").observe(
                                this,
                                bitmap -> {
                                    //显示二维码
                                    mVB.imgCode.setImageBitmap(bitmap);
                                });
                    } else if (permission.shouldShowRequestPermissionRationale){
                        ToastUtils.showToast("您没有授权该权限，请在设置中打开授权");
                    } else {
                        ToastUtils.showToast("您没有授权该权限，请在设置中打开授权");
                    }
                }
        );


    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }

}

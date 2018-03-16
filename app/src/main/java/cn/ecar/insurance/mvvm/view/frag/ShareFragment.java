package cn.ecar.insurance.mvvm.view.frag;


import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tbruyelle.rxpermissions.RxPermissions;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.databinding.FragmentShareBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.mvvm.viewmodel.main.ShareViewModel;
import cn.ecar.insurance.net.NetWorkApi;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author ding
 */
public class ShareFragment extends BaseBindingFragment<FragmentShareBinding> {

    //    private ShareViewModel shareViewModel;
    private RequestOptions options;

    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_share;
    }

    @Override
    protected void initView() {
//        String path = SpUtils.getString(XdConfig.SHARE_IMAGE_PATH);
//        if ("".equals(path)) {
//            ToastUtils.showToast("分享信息为空，请重新登录");
//        }
//        options = new RequestOptions()
//                .placeholder(R.drawable.verify_before)
//                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//                .skipMemoryCache(false);
////        options.signature(new ObjectKey(String.valueOf(System.currentTimeMillis())));
////                ByteArrayOutputStream stream = new ByteArrayOutputStream();
////                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
////                byte[] bitmapData = stream.toByteArray();
//
//        Glide.with(mContext)
//                .load(path)
//                .apply(options)
//                .into(mVB.imgCode);
    }

    @Override
    protected void initData() {
//        shareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);
//
//        RxPermissions rxPermissions = new RxPermissions(getActivity());
//        rxPermissions.requestEach(
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.ACCESS_WIFI_STATE).subscribe(permission -> {
//                    if (permission.granted) {
//                        shareViewModel.getShareQRCode("这是一份测试数据").observe(
//                                this,
//                                bitmap -> {
//                                    //显示二维码
//                                    mVB.imgCode.setImageBitmap(bitmap);
//                                });
//                    } else if (permission.shouldShowRequestPermissionRationale){
//                        ToastUtils.showToast("您没有授权该权限，请在设置中打开授权");
//                    } else {
//                        ToastUtils.showToast("您没有授权该权限，请在设置中打开授权");
//                    }
//                }
//        );


    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }

}

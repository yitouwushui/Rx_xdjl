package cn.ecar.insurance.mvvm.view.act.custom;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.databinding.ActicityPersonalBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.mvvm.viewmodel.data.PhotoViewModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.camera.CameraAlbumUtils;
import cn.ecar.insurance.utils.camera.ImagePickSelectUtils;
import cn.ecar.insurance.utils.camera.ImageUtil;
import cn.ecar.insurance.utils.encrypt.MD5Helper;
import cn.ecar.insurance.utils.file.FileUtils;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.TimeUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 * @date 2018/2/2
 */

public class PersonalActivity extends BaseBindingActivity<ActicityPersonalBinding> implements OnViewClick {

    private CustomViewModel mCustomViewModel;
    private PhotoViewModel mPhotoViewModel;
    private ImagePickSelectUtils mSelectDialog;
    private String typeName = "pingbi";
    private String picPath = "";
    private int customerId = 0;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.acticity_personal;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("个人资料");
    }

    @Override
    protected void initData() {
        Customer customer = SpUtils.getData(Customer.class);
        if (customer == null) {
            ToastUtils.showToast("未获取到客户信息请，重新登录");
            return;
        }
        customerId = customer.getCustomerId();
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        mPhotoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btComparison, 1, this);

        RxViewUtils.onViewClick(mVB.imageTakePhoto, () -> {
            new RxPermissions(this)
                    .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(success -> {
                        if (!success) {
                            ToastUtils.showToast("权限被拒绝了,可能无法启动相机或相册!");
                            return;
                        }
                        if (mSelectDialog == null) {
                            mSelectDialog = new ImagePickSelectUtils(PersonalActivity.this, typeName + ".jpeg");
                            mSelectDialog.setCrop(true);
                        }
                        mSelectDialog.showMdDialog(FileUtils.DEFAULT_SAVE_IMAGE_PATH);
                    });
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSelectDialog == null || resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CameraAlbumUtils.CAMERA_REQUEST_CODE://拍照
            case CameraAlbumUtils.ALBUM_REQUEST_CODE://打开相册
                mSelectDialog.setCrop(true);
                mSelectDialog.onActivityResult(requestCode, resultCode, data);
                break;
            case CameraAlbumUtils.CROP_REQUEST_CODE://裁剪完成
                Bitmap bitmap = mSelectDialog.onActivityResult(requestCode, resultCode, data);
                if (bitmap == null) {
                    ToastUtils.showToast("获取图片失败");
                    return;
                }
                mVB.imageTakePhoto.setImageBitmap(bitmap);
                try {
                    String picturePath = ImageUtil.saveFile(bitmap, typeName + ".jpeg");
                    if (bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                    uploadAndSaveAvatar(0, picturePath, customerId);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showToast("存储相片失败，请换种方式");
                }
                break;
        }
    }

    /**
     * 上传并本地保存图片
     */
    private void uploadAndSaveAvatar(int type, String filePath, int customerId) {
        mPhotoViewModel.uploadPhoto(type, filePath, customerId).observe(this, uploadImageGson -> {
//            mVB.photoStatus.setText("我的图片(待审核)");
//            RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_PHOTO_ID_CARD);
            if (uploadImageGson != null) {
                ToastUtils.showToast("图片上传成功");
                picPath = uploadImageGson.getFilePath();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_comparison:
                try {
                    String name = mVB.etName.getText().toString();
                    if ("".equals(name)) {
                        ToastUtils.showToast("请先添加评比昵称");
                        break;
                    }
                    if ("".equals(picPath)) {
                        ToastUtils.showToast("请先上传图片");
                    }
                    Map<String, String> map = RetrofitUtils.getInstance().getParamsMap(
                            "customerId", String.valueOf(customerId),
                            "name", name,
                            "picPath", picPath
                    );

                    map.put("version", OtherUtil.getVersionName(mContext));
                    map.put("timestamp", String.valueOf(System.currentTimeMillis()));
                    map.put("appId", XdConfig.APP_ID);
                    String sign = null;

                    sign = MD5Helper.getSign(map, XdConfig.APP_SECRET, "UTF-8");

                    map.put("sign", sign);
                    mCustomViewModel.joinShow(map).observe(this, baseGson -> {
                        if (XdConfig.RESPONSE_T.equals(baseGson.getResponseCode())) {
                            ToastUtils.showToast(baseGson.getResponseMsg());
                        } else {
                            ToastUtils.showToast(baseGson.getResponseMsg());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }

    @Override
    protected void destroyView() {

    }
}

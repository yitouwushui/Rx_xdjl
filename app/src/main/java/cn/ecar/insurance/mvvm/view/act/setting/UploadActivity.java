package cn.ecar.insurance.mvvm.view.act.setting;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.IOException;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActicityRealNameAuthBinding;
import cn.ecar.insurance.databinding.ActicityUploadBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.data.PhotoViewModel;
import cn.ecar.insurance.rxevent.RxBus;
import cn.ecar.insurance.rxevent.RxCodeConstants;
import cn.ecar.insurance.utils.camera.ImagePickSelectUtils;
import cn.ecar.insurance.utils.camera.ImageUtil;
import cn.ecar.insurance.utils.file.FileUtils;
import cn.ecar.insurance.utils.ui.CustomUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author dzx
 * @date 2017/12/4
 * 实名认证
 */

public class UploadActivity extends BaseBindingActivity<ActicityUploadBinding> {

    private PhotoViewModel mPhotoViewModel;
    private ImagePickSelectUtils mSelectDialog;
    private String picturePath;

    /**
     * 照片类型
     */
    private int type = 0;
    /**
     * PHOTO_SHEN_FEN_ZHENG1, "certPath"
     * PHOTO_SHEN_FEN_ZHENG2, "certOtherPath"
     * PHOTO_JIA_SHI_ZHENG, "drivingPath"
     * PHOTO_YING_YE_ZHI_ZHAO, "businessLicensePath"
     * PHOTO_KAI_PIAO_ZI_LIAO, "invoicePath"
     */
    private String typeStr = "ecar";

    @Override
    public void getBundleExtras(Bundle extras) {
        type = extras.getInt(XdConfig.EXTRA_REQUEST_VALUE);
        typeStr = extras.getString(XdConfig.EXTRA_STRING_VALUE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.acticity_upload;
    }

    @Override
    protected void initView() {
        mPhotoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
        mVB.includeToolbar.textTitle.setText("图片上传");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.imageTakePhoto, () -> {
            new RxPermissions(UploadActivity.this)
                    .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(success -> {
                        if (success) {
                            if (mSelectDialog == null) {
                                mSelectDialog = new ImagePickSelectUtils(UploadActivity.this, typeStr + ".jpeg");
                                mSelectDialog.setCrop(false);
                            }
                            mSelectDialog.showMdDialog(FileUtils.DEFAULT_SAVE_IMAGE_PATH);
                        } else {
                            ToastUtils.showToast("权限被拒绝了,可能无法启动相机或相册!");
                        }
                    });
        });

        RxViewUtils.onViewClick(mVB.btUpload, () -> {
            uploadAndSaveAvatar(type, picturePath);
        });

        RxViewUtils.onViewClickNeedPermission(this, mVB.includeToolbar.textRightTitle, permission -> {
            if (permission) {
                if (mSelectDialog == null) {
                    mSelectDialog = new ImagePickSelectUtils(UploadActivity.this, typeStr + ".jpeg");
                    mSelectDialog.setCrop(false);
                }
                mSelectDialog.showMdDialog(FileUtils.DEFAULT_SAVE_IMAGE_PATH);
            } else {
                ToastUtils.showToast("权限被拒绝了,可能无法启动相机或相册!");
            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        RxViewUtils.onViewClick(mVB.includeToolbar.linearBack, () -> finishActivity());
    }

    @Override
    protected void destroyView() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSelectDialog != null) {
            Bitmap bitmap = mSelectDialog.onActivityResult(requestCode, resultCode, data);
            if (bitmap != null) {
                mVB.imageTakePhoto.setImageBitmap(bitmap);
                try {
                    picturePath = ImageUtil.saveFile(bitmap, typeStr + ".jpeg");
                    if (bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    ToastUtils.showToast("存储相片失败，请换种方式");
                }
            } else {
                ToastUtils.showToast("获取图片失败");
            }
        }
    }

    /**
     * 上传并本地保存图片
     */
    private void uploadAndSaveAvatar(int type, String filePath) {
        mPhotoViewModel.uploadPhoto(type, filePath).observe(this, uploadImageGson -> {
//            mVB.photoStatus.setText("我的图片(待审核)");
//            RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_PHOTO_ID_CARD);
            if (uploadImageGson != null) {
                ToastUtils.showToast("图片上传成功");
                new IntentUtils.Builder(mContext)
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, uploadImageGson.getFilePath())
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE_2, typeStr)
                        .build()
                        .setResultOkWithFinishUi();
            }
        });
    }
}

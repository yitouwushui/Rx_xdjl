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

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.ActicityRealNameAuthBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.data.PhotoViewModel;
import cn.ecar.insurance.rxevent.RxBus;
import cn.ecar.insurance.rxevent.RxCodeConstants;
import cn.ecar.insurance.utils.camera.ImagePickSelectUtils;
import cn.ecar.insurance.utils.file.FileUtils;
import cn.ecar.insurance.utils.ui.CustomUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 *
 * @author lq
 * @date 2017/12/4
 * 实名认证
 */

public class RealNameAuthActivity extends BaseBindingActivity<ActicityRealNameAuthBinding> {

    private String trueName = "";
    private PhotoViewModel mPhotoViewModel;
    private ImagePickSelectUtils mSelectDialog;

    @Override
    public void getBundleExtras(Bundle extras) {
        trueName = extras.getString("status");
        com.orhanobut.logger.Logger.w("trueName = " + trueName);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.acticity_real_name_auth;
    }

    @Override
    protected void initView() {
        mPhotoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
        mVB.includeToolbar.textTitle.setText("实名认证");
        if (!trueName.equals("")) {
            mVB.photoStatus.setVisibility(View.VISIBLE);
            mVB.photoStatus.setText("我的图片(" + CustomUtils.getDictValue(mContext, trueName, "renzhengInfo") + ")");
        }
        if (!mPhotoViewModel.getSfzPhoto().equals("")) {
            Glide.with(this).load(mPhotoViewModel.getSfzPhoto()).into(mVB.imageTakePhoto);
            if (trueName.equals("2")) {
                //2表示认证成功
                mVB.photoStatus.setTextColor(Color.parseColor("#7194ff"));
                mVB.includeToolbar.textRightTitle.setVisibility(View.GONE);
                mVB.linearCkPhoto.setVisibility(View.GONE);
            } else {
                mVB.includeToolbar.textRightTitle.setVisibility(View.VISIBLE);
                mVB.includeToolbar.textRightTitle.setText("重新认证");
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.imageTakePhoto, () -> {
            if (mPhotoViewModel.getSfzPhoto().equals("")) {
                new RxPermissions(RealNameAuthActivity.this)
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(success -> {
                            if (success) {
                                if (mSelectDialog == null) {
                                    mSelectDialog = new ImagePickSelectUtils(RealNameAuthActivity.this, "sfz.jpeg");
                                    mSelectDialog.setCrop(false);
                                }
                                mSelectDialog.showMdDialog(FileUtils.DEFAULT_SAVE_IMAGE_PATH);
                            } else {
                                ToastUtils.showToast("权限被拒绝了,可能无法启动相机或相册!");
                            }
                        });
            } else {
                //看大图片;
                ToastUtils.showToast("看大图");
            }
        });

        RxViewUtils.onViewClickNeedPermission(this,mVB.includeToolbar.textRightTitle,permission ->{
            if (permission) {
                if (mSelectDialog == null) {
                    mSelectDialog = new ImagePickSelectUtils(RealNameAuthActivity.this, "sfz.jpeg");
                    mSelectDialog.setCrop(false);
                }
                mSelectDialog.showMdDialog(FileUtils.DEFAULT_SAVE_IMAGE_PATH);
            } else {
                ToastUtils.showToast("权限被拒绝了,可能无法启动相机或相册!");
            }
        },Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        RxViewUtils.onViewClick(mVB.includeToolbar.linearBack,() -> finishActivity());
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
                uploadAndSaveAvatar(bitmap);
            }
        }
    }

    /**
     * 上传并本地保存图片
     *
     * @param bitmap
     */
    private void uploadAndSaveAvatar(Bitmap bitmap) {
        mPhotoViewModel.uploadSfzPhoto("sfz.jpeg", "SfzPhoto", bitmap).observe(this, photoBean -> {
            mVB.imageTakePhoto.setImageBitmap(bitmap);//设置头像
            mVB.includeToolbar.textRightTitle.setVisibility(View.GONE);
            mVB.photoStatus.setText("我的图片(待审核)");
            ToastUtils.showToast("图片上传成功");
            RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_PHOTO_ID_CARD);
        });
    }
}

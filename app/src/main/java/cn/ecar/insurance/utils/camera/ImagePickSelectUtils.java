package cn.ecar.insurance.utils.camera;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.utils.file.FileUtils;
import cn.ecar.insurance.utils.ui.MdDialogUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import me.drakeet.materialdialog.MaterialDialog;

import static android.app.Activity.RESULT_OK;
import static cn.ecar.insurance.utils.camera.CameraAlbumUtils.ALBUM_REQUEST_CODE;
import static cn.ecar.insurance.utils.camera.CameraAlbumUtils.CAMERA_REQUEST_CODE;
import static cn.ecar.insurance.utils.camera.CameraAlbumUtils.CROP_REQUEST_CODE;

/**
 * @author yx
 * @date 2017/5/16
 * 可裁剪相机 相册Dailog类
 */
public class ImagePickSelectUtils implements OnClickListener {

    private Dialog mWayDialog;
    private Context mContext;
    private TextView mTvCamera, mTvAlbum, mTvCancel;//相机  相册 取消
    private CameraAlbumUtils mCameraAlbumUtils;
    private WeakReference<Activity> mActivity;
    private WeakReference<Fragment> mFragment;
    private boolean mIsCrop = true;
    private MaterialDialog mMaterialDialog;
    private Uri mOutputUri;

    /**
     * 从Fragment启动
     *
     * @param fragment
     */
    public ImagePickSelectUtils(Fragment fragment, String imageName) {
        if (mFragment == null) {
            mFragment = new WeakReference<>(fragment);
            if (!imageName.endsWith(".jpeg")) {
                throw new IllegalStateException("imageName must endWith .jpeg");
            }
            mCameraAlbumUtils = new CameraAlbumUtils(fragment, imageName);
        }
        mContext = fragment.getActivity();
    }

    /**
     * 从Activity启动
     *
     * @param activity
     */
    public ImagePickSelectUtils(Activity activity, String imageName) {
        if (mActivity == null) {
            mActivity = new WeakReference<>(activity);
            if (!imageName.endsWith(".jpeg")) {
                throw new IllegalStateException("imageName must endWith .jpeg");
            }
            mCameraAlbumUtils = new CameraAlbumUtils(activity, imageName);
        }
        mContext = activity;
    }

//    /**
//     * 弹出仿QQ dialog 选择拍照 相册
//     *
//     */
//    public void show() {
//        if (mWayDialog == null) {
//            mWayDialog = new Dialog(mContext, R.style.dialog);
////            View view = View.inflate(mContext, R.layout.dialog_photo_choice, null);
//            mWayDialog.setCancelable(true);
//            mWayDialog.setCanceledOnTouchOutside(true);
//            mWayDialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT));
//            Window window = mWayDialog.getWindow();
//            //设置动画
//            window.setWindowAnimations(R.style.dialogWindowAnim);
//            WindowManager.LayoutParams wl = window.getAttributes();
//            wl.x = 0;
//            wl.y = mActivity.get().getWindowManager().getDefaultDisplay().getHeight();
//            wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//            mWayDialog.onWindowAttributesChanged(wl);
////            mTvCamera = (TextView) mWayDialog.findViewById(R.id.tv_paishe);
////            mTvAlbum = (TextView) mWayDialog.findViewById(R.id.tv_xiangce);
////            mTvCancel = (TextView) mWayDialog.findViewById(R.id.tv_cancle);
//            mTvCamera.setOnClickListener(this);
//            mTvAlbum.setOnClickListener(this);
//            mTvCancel.setOnClickListener(this);
//        }
//        mWayDialog.show();
//    }


    /**
     * 弹出MD风格dialog 选择拍照 相册
     *
     * @param path
     */
    public void showMdDialog(String path) {
        if (mMaterialDialog == null) {
            mMaterialDialog = new MaterialDialog(mContext);
            ListView listView = new ListView(mContext);
            listView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            listView.setDividerHeight(0);
            listView.setAdapter(new CommonAdapter<String>(mContext, R.layout.item_shaixuan,
                    MdDialogUtils.createList("拍摄照片", "从相册选择")) {
                @Override
                protected void convert(ViewHolder viewHolder, String str, int position) {
                    viewHolder.setText(R.id.tv_riqi, str);
                }
            });
            listView.setOnItemClickListener((parent, view1, position, id) -> {
                mMaterialDialog.dismiss();
                switch (position) {
                    case 0:
                        //相机
                        mCameraAlbumUtils.openCamera(path);
                        break;
                    case 1:
                        //相册
                        mCameraAlbumUtils.openAlbum();
                        break;
                }

            });
            mMaterialDialog.setContentView(listView).setCanceledOnTouchOutside(true);
            mMaterialDialog.show();

        } else {
            mMaterialDialog.show();
        }
    }

    public void dismiss() {
        mWayDialog.cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_paishe:
//                mCameraAlbumUtils.openCamera();
//                break;
//            case R.id.tv_xiangce:
//                mCameraAlbumUtils.openAlbum();
//                break;
//            case R.id.tv_cancle:
//                break;
        }
        dismiss();
    }

    /**
     * 获取图片回调 在activity或fragment 的 onActivityResult 中调用
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public Bitmap onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return null;
        }
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (mIsCrop) {
                    // 拍照完后裁剪
                    startPhotoZoom(mCameraAlbumUtils.getFileUri());
                    return null;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    return getBitmapFromUriMoreThanSeven(mCameraAlbumUtils.getFileUri());
                } else {
                    return getBitmapFromUri(mCameraAlbumUtils.getFileUri());
                }
            case ALBUM_REQUEST_CODE:
                if (data == null) {
                    return null;
                }
                if (mIsCrop) {
                    startPhotoZoom(data.getData());
                    return null;
                }
                return getDataBitmap(data);
            case CROP_REQUEST_CODE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    return getBitmapFromUriMoreThanSeven(mOutputUri);
                } else {
                    return getBitmapFromUri(mOutputUri);
                }
            default:
        }
        return null;
    }

    /**
     * 获取返回data中解析的bitmap
     *
     * @param data
     * @return
     */
    private Bitmap getDataBitmap(Intent data) {
        Uri uri = data.getData();
        Bitmap bitmap = null;
        if (uri != null) {
            bitmap = getBitmapFromUri(uri); //拿到图片
        }
        if (bitmap == null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                bitmap = (Bitmap) bundle.get("data");
                if (bitmap == null) {
                    bitmap = data.getParcelableExtra("data");
                    if (bitmap == null) {
                        bitmap = data.getExtras().getParcelable("data");
                    }
                }
            } else {
                ToastUtils.showToast("找不到图片");
                return null;
            }
        }
        return bitmap;
    }

    /**
     * 裁剪图片
     */
    private void startPhotoZoom(Uri uri) {
        // 创建File对象，用于存储裁剪后的图片，避免更改原图
        File file = new File(FileUtils.DEFAULT_SAVE_IMAGE_PATH, "crop_image.jpg");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mOutputUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        //裁剪图片的宽高比例
        intent.putExtra("aspectX", 16);
        intent.putExtra("aspectY", 9);
        intent.putExtra("crop", "true");//可裁剪
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);//支持缩放
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片格式
        intent.putExtra("noFaceDetection", true);//取消人脸识别
        String startFrom = mCameraAlbumUtils.getStartFrom();
        if (startFrom.equals(CameraAlbumUtils.START_FROM_ACTIVITY)) {
            mActivity.get().startActivityForResult(intent, CROP_REQUEST_CODE);
        } else if (startFrom.equals(CameraAlbumUtils.START_FROM_FRAGMENT)) {
            mFragment.get().startActivityForResult(intent, CROP_REQUEST_CODE);
        }
    }

    /**
     * 将返回的URL解析为图片
     *
     * @param uri
     * @return
     */
    private Bitmap getBitmapFromUriMoreThanSeven(Uri uri) {
        try {
            // 读取uri所在的图片
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext
//                    .getContentResolver(), uri);
            return ImageUtil.getThumbnail(mContext, uri, 1080);
        } catch (Exception e) {
            Logger.e("[Android]", e.getMessage());
            Logger.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将返回的URL解析为图片
     *
     * @param uri
     * @return
     */
    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            // 读取uri所在的图片
            return ImageUtil.getImageFromUri(uri, mContext);
        } catch (Exception e) {
            Logger.e("[Android]", e.getMessage());
            Logger.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    public void setCrop(Boolean crop) {
        mIsCrop = crop;
    }
}

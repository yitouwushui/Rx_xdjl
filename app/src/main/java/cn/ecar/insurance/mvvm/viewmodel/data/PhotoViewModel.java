package cn.ecar.insurance.mvvm.viewmodel.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;

import cn.ecar.insurance.base.PhotoBean;
import cn.ecar.insurance.base.PhotoEntity;
import cn.ecar.insurance.mvvm.model.data.PhotoModel;


/**
 * Created by lq on 2017/12/4.
 */

public class PhotoViewModel extends ViewModel {

    private PhotoModel mPhotoModel;

    public PhotoViewModel() {
        mPhotoModel = PhotoModel.getInstance();
    }

    /**
     * 获取认证图片状态
     *
     * @return
     */
    public LiveData<PhotoEntity> getPhotoStatus() {
        return mPhotoModel.getPhotoStatus();
    }

    public String getBigJobPhoto() {
        return mPhotoModel.getBigJobPhoto();
    }

    public String getJobPhoto() {
        return mPhotoModel.getJobPhoto();
    }

    public String getBigSfzPhoto() {
        return mPhotoModel.getBigSfzPhoto();
    }

    public String getSfzPhoto() {
        return mPhotoModel.getSfzPhoto();
    }

    /**
     * 上传认证图片
     *
     * @param name
     * @return
     */
    public LiveData<PhotoBean> uploadSfzPhoto(String name, String type, Bitmap bitmap) {
        return mPhotoModel.uploadSfzPhoto(name, type, bitmap);
    }
}

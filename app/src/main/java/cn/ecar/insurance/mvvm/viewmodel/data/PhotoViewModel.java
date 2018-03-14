package cn.ecar.insurance.mvvm.viewmodel.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import cn.ecar.insurance.dao.gson.UploadImageGson;
import cn.ecar.insurance.mvvm.model.data.PhotoModel;


/**
 * Created by dzx on 2017/12/4.
 */

public class PhotoViewModel extends ViewModel {

    private PhotoModel mPhotoModel;

    public PhotoViewModel() {
        mPhotoModel = PhotoModel.getInstance();
    }

    /**
     * 上传认证图片
     *
     * @return
     */
    public LiveData<UploadImageGson> uploadPhoto(int type,String filePath) {
        return mPhotoModel.uploadPhoto(type,filePath);
    }
}

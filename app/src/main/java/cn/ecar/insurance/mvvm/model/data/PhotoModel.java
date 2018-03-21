package cn.ecar.insurance.mvvm.model.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.gson.UploadImageGson;
import cn.ecar.insurance.mvvm.base.BaseModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dzx on 2017/12/4.
 * 图片的操作
 */

public class PhotoModel extends BaseModel {

    private static volatile PhotoModel instance;

    public static PhotoModel getInstance() {
        if (instance == null) {
            synchronized (PhotoModel.class) {
                if (instance == null) {
                    instance = new PhotoModel();
                }
            }
        }
        return instance;
    }


    /**
     * 压缩存储并上传认证图片
     *
     * @return
     */
    public LiveData<UploadImageGson> uploadPhoto(int type, String filePath, int customerId) {
        MutableLiveData<UploadImageGson> data = new MutableLiveData<>();
        File file;
        file = new File(filePath);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Uri url = FileProvider.getUriForFile(
//                    getCurrentActivity(),
//                    getCurrentActivity().getPackageName() + ".fileProvider",
//                    new File(filePath));
//            file = new File(url.getPath());
//        } else {
//            file = new File(filePath);
//        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("File1", file.getName(), requestBody);
        showWaitDialog();
        RetrofitUtils.getInstance().getUploadData(photo, customerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UploadImageGson>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        hideWaitDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(UploadImageGson uploadImageGson) {
                        if (XdConfig.RESPONSE_T_UPLOAD.equals(uploadImageGson.getResponseCode())) {
                            uploadImageGson.setType(type);
                            data.postValue(uploadImageGson);
                        } else {
                            ToastUtils.showToast(uploadImageGson.getResponseMsg());
                        }
                        hideWaitDialog();
                    }
                });
        return data;
    }

}

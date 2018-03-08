package cn.ecar.insurance.mvvm.model.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.File;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.base.PhotoBean;
import cn.ecar.insurance.dao.base.PhotoEntity;
import cn.ecar.insurance.dao.gson.UploadImageGson;
import cn.ecar.insurance.mvvm.base.BaseModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.file.FileUtils;
import cn.ecar.insurance.utils.file.ImageFactory;
import cn.ecar.insurance.utils.trilateral.GsonUtil;
import cn.ecar.insurance.utils.ui.ToastUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
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

    private String sfzPhoto = "";
    private String jobPhoto = "";
    private String bigSfzPhoto = "";
    private String bigJobPhoto = "";

    /**
     * 获取认证图片状态
     *
     * @return
     */
    public LiveData<PhotoEntity> getPhotoStatus() {
        MutableLiveData<PhotoEntity> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getEncryptedData(RetrofitUtils.getInstance().getParamsMap(
                "d", RetrofitUtils.getInstance().toAesJsonString("type", "renzhengInfo", "token", "17C2ED5106C4F27E4AD6EDF6C13235D7A8345E069262117E982C244534BE23EC96CE88E710BEB4BE0EF12BF8651B627B0CA05CC36AAD2AC8CE0C8A7F47231059AB667B19F718E5888C97E1F64C433B967955BD6951CC8866B7E3AB764E9D1264F63DBF5C448E3CEC17F14EB3B168D7864F4DC495A7D7C1B1")))
                .map(aesEntity -> {
                    Logger.wtf("aesEntity = " + RetrofitUtils.getInstance().decpytJsonString(aesEntity.getD()));
                    return GsonUtil.fromJson(RetrofitUtils.getInstance().decpytJsonString(aesEntity.getD()), new TypeToken<PhotoEntity>() {
                    });
                })
                .subscribe(new Subscriber<PhotoEntity>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        showWaitDialog();
                    }

                    @Override
                    public void onCompleted() {
                        hideWaitDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitDialog();
                        ToastUtils.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(PhotoEntity photoEntity) {
                        hideWaitDialog();
                        if (photoEntity.getResult().equals("suc")) {
                            jobPhoto = photoEntity.getPositionPhoto();
                            sfzPhoto = photoEntity.getTrueNamePhoto();
                            data.setValue(photoEntity);
                        } else {
                            handingResult(photoEntity.getResult(), photoEntity.getMsg());
                        }
                    }
                });
        return data;
    }

    /**
     * 压缩存储并上传认证图片
     *
     * @param name 图片名字
     * @return
     */
    public LiveData<PhotoBean> uploadSfzPhoto(String name, String type, Bitmap bitmap) {
        MutableLiveData<PhotoBean> data = new MutableLiveData<>();
//        String clientId =  PushManager.getInstance().getClientid(getAppContext());
        String clientId = "8bb8a4be899abc38554564a31f7b3f5f";
        String token = "17C2ED5106C4F27E4AD6EDF6C13235D7A8345E069262117E982C244534BE23EC96CE88E710BEB4BE0EF12BF8651B627B0CA05CC36AAD2AC8CE0C8A7F47231059AB667B19F718E5888C97E1F64C433B967955BD6951CC8866B7E3AB764E9D1264F63DBF5C448E3CEC17F14EB3B168D7864F4DC495A7D7C1B1";
        Observable.fromCallable(() -> {
            Bitmap ratio = ImageFactory.ratio(bitmap, 500f, 600f);
            bitmap.recycle();
            FileUtils.saveImage(ratio, "sfz.jpeg");
            ratio.recycle();
            return Observable.just("UploadImg", type, token, clientId);
        })
                .flatMap(stringObservable -> {
                    return stringObservable.buffer(4);
                })
                .flatMap(listObservable -> {
                    String encryptString = RetrofitUtils.getInstance().toAesJsonString(
                            "type", listObservable.get(0), "renzhengType", listObservable.get(1),
                            "token", listObservable.get(2), "DataType", listObservable.get(3));
                    File file = new File(FileUtils.getImagePath() + name);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
                    MultipartBody.Part photo = MultipartBody.Part.createFormData("File1", file.getName(), requestBody);
                    return RetrofitUtils.getInstance().getUploadAesData(encryptString, photo);
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(aesEntity -> {
                    ToastUtils.showToast(aesEntity.getResponseCode());
                    return GsonUtil.fromJson(RetrofitUtils.getInstance().decpytJsonString(aesEntity.getD()), new TypeToken<PhotoBean>() {
                    });
                })
                .subscribe(new Subscriber<PhotoBean>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        showWaitDialog();
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
                    public void onNext(PhotoBean bean) {
                        hideWaitDialog();
                        if (bean.getResult().equals("suc")) {
                            if (type.equals("SfzPhoto")) {
                                bigSfzPhoto = bean.getBigPhoto();
                                sfzPhoto = bean.getPhoto();
                            } else {
                                bigJobPhoto = bean.getBigPhoto();
                                jobPhoto = bean.getPhoto();
                            }
                            data.setValue(bean);
                        } else {
                            handingResult(bean.getResult(), bean.getMsg());
                        }
                    }
                });
        return data;
    }

    /**
     * 压缩存储并上传认证图片
     *
     * @return
     */
    public LiveData<UploadImageGson> uploadPhoto(int type, String filePath) {
        showWaitDialog();
        MutableLiveData<UploadImageGson> data = new MutableLiveData<>();
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("File1", file.getName(), requestBody);
        showWaitDialog();
        RetrofitUtils.getInstance().getUploadAesData(photo)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
                        if (XdConfig.RESPONSE_T.equals(uploadImageGson.getResponseCode())) {
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

    public String getBigJobPhoto() {
        return bigJobPhoto;
    }

    public String getJobPhoto() {
        return jobPhoto;
    }

    public String getBigSfzPhoto() {
        return bigSfzPhoto;
    }

    public String getSfzPhoto() {
        return sfzPhoto;
    }
}

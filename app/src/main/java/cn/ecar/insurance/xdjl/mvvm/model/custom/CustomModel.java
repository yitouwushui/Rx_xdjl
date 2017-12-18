package cn.ecar.insurance.xdjl.mvvm.model.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import cn.ecar.insurance.xdjl.entity.base.BaseEntity;
import cn.ecar.insurance.xdjl.mvvm.base.BaseModel;
import cn.ecar.insurance.xdjl.net.OkHttpUtilListener;
import cn.ecar.insurance.xdjl.net.RetrofitUtils;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by yx on 2017/8/11.
 * custom model
 */

public class CustomModel extends BaseModel {

    private static volatile CustomModel instance;

    public static CustomModel getInstance() {
        if (instance == null) {
            synchronized (CustomModel.class) {
                if (instance == null) {
                    instance = new CustomModel();
                }
            }
        }
        return instance;
    }

    private CustomModel() {
        super();
    }

    public BaseEntity getBase() {
        return new BaseEntity();
    }

    public LiveData<String> getBaiDu() {
        MutableLiveData<String> data =new MutableLiveData<>();
        RetrofitUtils.getInstance().getOkhttpUtilsRequest("www.baidu.com",
                "param", new OkHttpUtilListener() {
            @Override
            public void onRequestStart(Request request, int id) {

            }

            @Override
            public void onRequestComplete(int id) {

            }

            @Override
            public void onRequestError(Call call, Exception e, int id) {

            }

            @Override
            public void onRequestSuccess(String response, int id) {
                data.postValue(response);
            }
        });
        return data;
    }


}

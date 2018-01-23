package cn.ecar.insurance.mvvm.viewmodel.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;

import java.util.HashMap;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.gson.CustomerGson;
import cn.ecar.insurance.mvvm.model.custom.LoginModel;

/**
 * @author yx
 * @date 2017/8/11
 * custom viewmodel
 * 包含三方库调用api(百度地图,友盟,环信等),app启动时调用api,产品统计等相关api
 */
public class LoginViewModel extends ViewModel {

    private LoginModel mLoginModel;

    public LoginViewModel() {
        mLoginModel = LoginModel.getInstance();
    }


    public LiveData<CustomerGson> login(String account, String pwd) {
        return mLoginModel.login(account, pwd);
    }

    public LiveData<BaseGson> getVerifyCode(String phoneNo, String imageVerifyCode) {
        return mLoginModel.getVerifyCode(phoneNo, imageVerifyCode);
    }
    public LiveData<Bitmap> getVerifyImage() {
        return mLoginModel.getVerifyImage();
    }

    public LiveData<BaseGson> register(HashMap<String, String> map) {
        return mLoginModel.register(map);
    }

}

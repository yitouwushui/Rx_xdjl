package cn.ecar.insurance.mvvm.model.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;

import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.base.Token;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.dao.gson.CustomerGson;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.mvvm.base.BaseModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.encrypt.MD5Helper;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.ToastUtils;
import rx.Observer;

/**
 * @author ding
 * @date 2018/1/12
 */

public class LoginModel extends BaseModel {

    private static volatile LoginModel instance;

    public static LoginModel getInstance() {
        if (instance == null) {
            synchronized (LoginModel.class) {
                if (instance == null) {
                    instance = new LoginModel();
                }
            }
        }
        return instance;
    }


    public LiveData<CustomerGson> login(String account, String pwd) {
        MutableLiveData<CustomerGson> data = new MutableLiveData<>();
        HashMap<String, String> hm = new HashMap<>(6);
//        hm.put("customerName", account);
        hm.put("customerIdentification", account);
        hm.put("password", pwd);
        hm.put("version", OtherUtil.getVersionName(getAppContext()));
        hm.put("timestamp", String.valueOf(System.currentTimeMillis()));
        hm.put("appId", XdConfig.APP_ID);
        String sign = null;
        try {
            sign = MD5Helper.getSign(hm, XdConfig.APP_SECRET, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        hm.put("sign", sign);
        showWaitDialog();
        RetrofitUtils.getInstance().login(hm).subscribe(new Observer<CustomerGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showToast(e.toString());
                hideWaitDialog();
            }

            @Override
            public void onNext(CustomerGson customerGson) {
                if (customerGson.getResponseCode().equals(XdConfig.RESPONSE_T)) {
                    //存储session
                    SpUtils.putData(XdConfig.SESSION_ID,
                            new Token(customerGson.getSessionId(),System.currentTimeMillis()+XdConfig.SESSION_TIME));
                    Customer customer = customerGson.getCustomer();
                    if (customer != null){
                        SpUtils.putData(customer);
                        SpUtils.putString(XdConfig.SHARE_IMAGE_PATH,customer.getShareImagePath());
                    }
                    RetrofitUtils.setSessionId(customerGson.getSessionId());
                    data.postValue(customerGson);
                } else {
                    ToastUtils.showToast(customerGson.getResponseMsg());
                }
                hideWaitDialog();
            }
        });
        return data;
    }

    public LiveData<BaseGson> getVerifyCode(String phoneNo, String imageVerifyCode) {
        MutableLiveData<BaseGson> data = new MutableLiveData<>();
        HashMap<String, String> hm = new HashMap<>(2);
        hm.put("phoneNo", phoneNo);
        hm.put("captcha", imageVerifyCode);
        RetrofitUtils.getInstance().getVerifyCode(hm).subscribe(new Observer<BaseGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showToast(e.toString());
                BaseGson baseGson = new BaseGson();
                baseGson.setResponseCode("ERROR");
                data.postValue(baseGson);
            }

            @Override
            public void onNext(BaseGson baseGson) {
                data.postValue(baseGson);
            }
        });
        return data;
    }

    public LiveData<Bitmap> getVerifyImage() {
        MutableLiveData<Bitmap> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getVerifyImage().subscribe(new Observer<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
            }

            @Override
            public void onNext(Bitmap bitmap) {
                data.postValue(bitmap);
            }
        });
        return data;
    }

    public LiveData<BaseGson> register(HashMap<String, String> map) {
        MutableLiveData<BaseGson> data = new MutableLiveData<>();
        showWaitDialog();
        RetrofitUtils.getInstance().register(map).subscribe(new Observer<BaseGson>() {
            @Override
            public void onCompleted() {
                hideWaitDialog();
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
            }

            @Override
            public void onNext(BaseGson baseGson) {
                Logger.i(baseGson.toString());
                if (baseGson.getResponseCode().equals(XdConfig.RESPONSE_T)) {
                    data.postValue(baseGson);
                    ToastUtils.showToast(baseGson.getResponseMsg());
                } else {
                    ToastUtils.showToast(baseGson.getResponseMsg());
                }
            }
        });
        return data;
    }
}

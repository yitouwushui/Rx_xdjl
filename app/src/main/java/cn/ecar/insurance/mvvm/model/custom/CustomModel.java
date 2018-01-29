package cn.ecar.insurance.mvvm.model.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.base.BaseEntity;
import cn.ecar.insurance.dao.gson.BankGson;
import cn.ecar.insurance.dao.gson.CustomerGson;
import cn.ecar.insurance.mvvm.base.BaseModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import rx.Observer;

/**
 * @author yx
 * @date 2017/8/11
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


    public LiveData<BankGson> getBankInfo() {
        LiveData<BankGson> bankInfo = new MutableLiveData<>();

        return bankInfo;
    }

    public LiveData<CustomerGson> getCustomerAllInfo() {
        MutableLiveData<CustomerGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getCustomerAllInfo(RetrofitUtils.getSessionId()).subscribe(new Observer<CustomerGson>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showToast(e.toString());
            }

            @Override
            public void onNext(CustomerGson customerGson) {
                if (customerGson.getResponseCode().equals(XdConfig.RESPONSE_T)) {
                    data.postValue(customerGson);
                } else {
                    ToastUtils.showToast(customerGson.getResponseMsg());
                }
            }
        });
        return data;
    }
}

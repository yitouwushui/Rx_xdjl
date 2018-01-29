package cn.ecar.insurance.mvvm.model.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.orhanobut.logger.Logger;

import java.util.HashMap;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Province;
import cn.ecar.insurance.dao.gson.BankGson;
import cn.ecar.insurance.dao.gson.CityGson;
import cn.ecar.insurance.dao.gson.PayGson;
import cn.ecar.insurance.dao.gson.ProvinceGson;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import rx.Observer;

/**
 * @author ding
 * @date 2017/12/21
 */

public class PayModel {

    private static volatile PayModel instance;

    public static PayModel getInstance() {
        if (instance == null) {
            synchronized (PayModel.class) {
                if (instance == null) {
                    instance = new PayModel();
                }
            }
        }
        return instance;
    }

    public LiveData<ProvinceGson> getProvinceList() {
        MutableLiveData<ProvinceGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getProvinceList().subscribe(new Observer<ProvinceGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                ProvinceGson error = new ProvinceGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(ProvinceGson baseGson) {
                data.postValue(baseGson);
            }
        });
        return data;
    }


    public LiveData<CityGson> getCityListProvinceCode(String provinceCode) {
        MutableLiveData<CityGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getCityListProvinceCode(provinceCode).subscribe(new Observer<CityGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                CityGson error = new CityGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(CityGson baseGson) {
                data.postValue(baseGson);
            }
        });
        return data;
    }


    public LiveData<BankGson> getBranchBankList(String bankCode, String cityCode){
        MutableLiveData<BankGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getBranchBankList(bankCode, cityCode).subscribe(new Observer<BankGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                BankGson error = new BankGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(BankGson bankGson) {
                data.postValue(bankGson);
            }
        });
        return data;
    }

    public LiveData<BankGson> getBankList() {
        MutableLiveData<BankGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getBankList().subscribe(new Observer<BankGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                BankGson error = new BankGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(BankGson bankGson) {
                data.postValue(bankGson);
            }
        });
        return data;
    }

    public LiveData<BaseGson> bindBank(HashMap<String, String> map) {
        MutableLiveData<BaseGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().bindBank(map).subscribe(new Observer<BaseGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                BaseGson error = new BaseGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(BaseGson bankGson) {
                data.postValue(bankGson);
            }
        });
        return data;
    }

    public LiveData<PayGson> submitPay(HashMap<String, String> map) {
        MutableLiveData<PayGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().submitPay(map).subscribe(new Observer<PayGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                PayGson error = new PayGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(PayGson bankGson) {
                data.postValue(bankGson);
            }
        });
        return data;
    }

}

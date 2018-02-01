package cn.ecar.insurance.mvvm.model.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.Map;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.gson.CateMapGson;
import cn.ecar.insurance.dao.gson.CityGson;
import cn.ecar.insurance.dao.gson.InsuranceInfoGson;
import cn.ecar.insurance.mvvm.base.BaseModel;
import cn.ecar.insurance.net.RetrofitUtils;
import rx.Observer;

/**
 * Created by ding on 2018/1/12.
 */

public class InsureModel extends BaseModel {

    private static volatile InsureModel instance;

    public static InsureModel getInstance() {
        if (instance == null) {
            synchronized (InsureModel.class) {
                if (instance == null) {
                    instance = new InsureModel();
                }
            }
        }
        return instance;
    }


    /**
     * 获取保险城市
     *
     * @return
     */
    public LiveData<CityGson> getInsuranceCityCode() {
        MutableLiveData<CityGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getInsuranceCityCode().subscribe(new Observer<CityGson>() {
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

    /**
     * 获取保单信息
     *
     * @return
     */
    public LiveData<InsuranceInfoGson> getInsuranceInfo(Map<String, String> map) {
        MutableLiveData<InsuranceInfoGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getInsuranceInfo(map).subscribe(new Observer<InsuranceInfoGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                InsuranceInfoGson error = new InsuranceInfoGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(InsuranceInfoGson baseGson) {
                data.postValue(baseGson);
            }
        });
        return data;
    }

    /**
     * 获取保单信息2
     *
     * @return
     */
    public LiveData<CateMapGson> getInsuranceOfferList(Map<String, String> map) {
        MutableLiveData<CateMapGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getInsuranceOfferList(map).subscribe(new Observer<CateMapGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                CateMapGson error = new CateMapGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(CateMapGson baseGson) {
                data.postValue(baseGson);
            }
        });
        return data;
    }

}

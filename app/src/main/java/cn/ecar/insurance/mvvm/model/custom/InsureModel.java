package cn.ecar.insurance.mvvm.model.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.base.BaseEntity;
import cn.ecar.insurance.dao.gson.CityGson;
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


    public BaseEntity getInsuranceList() {

        return null;
    }

    /**
     * 获取保险城市
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

}

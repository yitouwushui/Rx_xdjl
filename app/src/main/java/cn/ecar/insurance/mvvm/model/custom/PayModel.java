package cn.ecar.insurance.mvvm.model.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.orhanobut.logger.Logger;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Province;
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
                Logger.e(e.toString());
            }

            @Override
            public void onNext(ProvinceGson baseGson) {
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

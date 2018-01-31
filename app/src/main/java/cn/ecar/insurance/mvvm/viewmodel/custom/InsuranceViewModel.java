package cn.ecar.insurance.mvvm.viewmodel.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import cn.ecar.insurance.dao.base.BaseEntity;
import cn.ecar.insurance.dao.gson.CityGson;
import cn.ecar.insurance.mvvm.model.custom.InsureModel;

/**
 * @author yx
 * @date 2017/8/11
 * custom viewmodel
 * 包含三方库调用api(百度地图,友盟,环信等),app启动时调用api,产品统计等相关api
 */
public class InsuranceViewModel extends ViewModel {

    private InsureModel mInsureModel;

    public InsuranceViewModel() {
        mInsureModel = InsureModel.getInstance();
    }

    public LiveData<CityGson> getInsuranceCityCode() {
        return mInsureModel.getInsuranceCityCode();
    }
}

package cn.ecar.insurance.mvvm.viewmodel.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Map;

import cn.ecar.insurance.dao.gson.CateMapGson;
import cn.ecar.insurance.dao.gson.CityGson;
import cn.ecar.insurance.dao.gson.InsuranceInfoGson;
import cn.ecar.insurance.dao.gson.OrderListGson;
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

    public LiveData<InsuranceInfoGson> getInsuranceInfo(Map<String,String> map) {
        return mInsureModel.getInsuranceInfo(map);
    }

    public LiveData<CateMapGson> getInsuranceOfferList(Map<String,String> map) {
        return mInsureModel.getInsuranceOfferList(map);
    }
    public LiveData<OrderListGson> submitCase(Map<String,String> map) {
        return mInsureModel.submitCase(map);
    }
    public LiveData<OrderListGson> getInsuranceOrderPrice(Map<String,String> map) {
        return mInsureModel.getInsuranceOrderPrice(map);
    }
    public LiveData<OrderListGson> getInsurancePriceByOrderNo(Map<String,String> map) {
        return mInsureModel.getInsurancePriceByOrderNo(map);
    }
}

package cn.ecar.insurance.mvvm.viewmodel.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.HashMap;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.gson.BankGson;
import cn.ecar.insurance.dao.gson.CityGson;
import cn.ecar.insurance.dao.gson.PayGson;
import cn.ecar.insurance.dao.gson.ProvinceGson;
import cn.ecar.insurance.mvvm.model.custom.PayModel;

/**
 * @author ding
 * @date 2017/12/21
 */

public class PayViewModel extends ViewModel {


    private PayModel mPayModel;

    public PayViewModel() {
        mPayModel = PayModel.getInstance();
    }

    /**
     * 获得省份列表
     *
     * @return
     */
    public LiveData<ProvinceGson> getProvinceList() {
        return mPayModel.getProvinceList();
    }

    /**
     * 获得市列表
     *
     * @return
     */
    public LiveData<CityGson> getCityListProvinceCode(String provinceCode) {
        return mPayModel.getCityListProvinceCode(provinceCode);
    }

    /**
     * 获得银行分行
     *
     * @return
     */
    public LiveData<BankGson> getBranchBankList(String bankCode, String cityCode) {
        return mPayModel.getBranchBankList(bankCode, cityCode);
    }

    /**
     * 获得银行
     *
     * @return
     */
    public LiveData<BankGson> getBankList() {
        return mPayModel.getBankList();
    }

    /**
     * 获得银行
     *
     * @param map
     * @return
     */
    public LiveData<BaseGson> bindBank(HashMap<String, String> map) {
        return mPayModel.bindBank(map);
    }

    /**
     * 获得银行
     *
     * @param map
     * @return
     */
    public LiveData<PayGson> submitPay(HashMap<String, String> map) {
        return mPayModel.submitPay(map);
    }

}

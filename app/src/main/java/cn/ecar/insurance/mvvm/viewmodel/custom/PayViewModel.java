package cn.ecar.insurance.mvvm.viewmodel.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Map;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.gson.BankBindGson;
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
     * @param
     * @return
     */
    public LiveData<ProvinceGson> getProvinceList() {
        return mPayModel.getProvinceList();
    }

    /**
     * 获得市列表
     *
     * @param provinceCode
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
     * @return
     */
    public LiveData<BankBindGson> getBankInfo() {
        return mPayModel.getBankInfo();
    }

    /**
     * 获得银行
     *
     * @param map
     * @return
     */
    public LiveData<BaseGson> bindBank(Map<String, String> map) {
        return mPayModel.bindBank(map);
    }

    /**
     * 提交支付请求
     *
     * @param map
     * @return
     */
    public LiveData<PayGson> submitPay(Map<String, String> map) {
        return mPayModel.submitPay(map);
    }

    /**
     * 支付保险订单
     *
     * @param map
     * @return
     */
    public LiveData<PayGson> commitInsuranceOrder(Map<String, String> map) {
        return mPayModel.commitInsuranceOrder(map);
    }

    /**
     * 查询绑定信息
     *
     * @return
     */
    public LiveData<BankBindGson> getBankInfoByWithdrawals() {
        return mPayModel.getBankInfoByWithdrawals();
    }

    /**
     * 提现
     *
     * @param map
     * @return
     */
    public LiveData<BankBindGson> submitWithdrawals(Map<String, String> map) {
        return mPayModel.submitWithdrawals(map);
    }


}

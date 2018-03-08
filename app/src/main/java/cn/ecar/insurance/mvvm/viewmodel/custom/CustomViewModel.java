package cn.ecar.insurance.mvvm.viewmodel.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Map;

import cn.ecar.insurance.dao.base.BaseEntity;
import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.gson.AddressGson;
import cn.ecar.insurance.dao.gson.BalanceGson;
import cn.ecar.insurance.dao.gson.BankGson;
import cn.ecar.insurance.dao.gson.CustomerGson;
import cn.ecar.insurance.dao.gson.InsuranceGson;
import cn.ecar.insurance.dao.gson.SignInGson;
import cn.ecar.insurance.dao.gson.TeamGson;
import cn.ecar.insurance.mvvm.model.custom.CustomModel;

/**
 * @author ding
 * @date 2017/
 * custom viewmodel
 */
public class CustomViewModel extends ViewModel {

    private CustomModel mCustomModel;

    public CustomViewModel() {
        mCustomModel = CustomModel.getInstance();
    }

    public BaseEntity getBase() {
        return mCustomModel.getBase();
    }

    public LiveData<BankGson> getBankInfo() {
        return mCustomModel.getBankInfo();
    }

    public LiveData<CustomerGson> getCustomerAllInfo() {
        return mCustomModel.getCustomerAllInfo();
    }

    /**
     * 查询余额
     *
     * @return
     */
    public LiveData<BalanceGson> goToWithdrawals() {
        return mCustomModel.goToWithdrawals();
    }

    /**
     * 获取保单信息
     *
     * @return
     */
    public LiveData<InsuranceGson> getMyInsuranceList() {
        return mCustomModel.getMyInsuranceList();
    }

    /**
     * 获取团队信息
     *
     * @return
     */
    public LiveData<TeamGson> getMyTeamList() {
        return mCustomModel.getMyTeamList();
    }

    /**
     * 获取签到信息
     *
     * @return
     */
    public LiveData<SignInGson> getMySignInList() {
        return mCustomModel.getMySignInList();
    }

    /**
     * 获取地址列表
     *
     * @return
     */
    public LiveData<AddressGson> getCustomerAddressList() {
        return mCustomModel.getCustomerAddressList();
    }

    /**
     * 添加地址
     *
     * @return
     */
    public LiveData<AddressGson> saveAddress(Map<String, String> params) {
        return mCustomModel.saveAddress(params);
    }

    /**
     * 修改地址
     *
     * @return
     */
    public LiveData<AddressGson> updateAddress(Map<String, String> params) {
        return mCustomModel.updateAddress(params);
    }

    /**
     * 参加明星会员
     *
     * @return
     */
    public LiveData<BaseGson> joinShow(Map<String, String> params) {
        return mCustomModel.joinShow(params);
    }
}

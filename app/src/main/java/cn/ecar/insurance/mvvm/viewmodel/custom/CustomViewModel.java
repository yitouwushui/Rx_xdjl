package cn.ecar.insurance.mvvm.viewmodel.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Map;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.gson.AddressGson;
import cn.ecar.insurance.dao.gson.BalanceGson;
import cn.ecar.insurance.dao.gson.BankGson;
import cn.ecar.insurance.dao.gson.CustomerGson;
import cn.ecar.insurance.dao.gson.FrozenCashGson;
import cn.ecar.insurance.dao.gson.FundsFlowGson;
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
    public LiveData<InsuranceGson> getInsuranceOrderByPage(String pageNum) {
        return getInsuranceOrderByPage(pageNum, 20);
    }

    /**
     * 获取保单信息
     *
     * @return
     */
    public LiveData<InsuranceGson> getInsuranceOrderByPage(String pageNum, int pageSize) {
        return mCustomModel.getInsuranceOrderByPage(pageNum, pageSize);
    }

    /**
     * 获取团队信息
     *
     * @return
     */
    public LiveData<TeamGson> getMyTeamList(String pageNum, int pageSize) {
        return mCustomModel.getMyTeamList(pageNum, pageSize);
    }

    /**
     * 获取团队信息二级
     *
     * @return
     */
    public LiveData<TeamGson> getTeamInfoByLevel(String pageNum, int level, int pageSize) {
        return mCustomModel.getTeamInfoByLevel(pageNum, level, pageSize);
    }

    /**
     * 获取冻结资金
     *
     * @return
     */
    public LiveData<FrozenCashGson> getFrozenCapitalList(String pageNum, int pageSize) {
        return mCustomModel.getFrozenCapitalList(pageNum, pageSize);
    }

    /**
     * 获取资金明细
     *
     * @return
     */
    public LiveData<FundsFlowGson> getFundsList(int pageNum, int pageSize) {
        return mCustomModel.getFundsList(pageNum, pageSize);
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

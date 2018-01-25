package cn.ecar.insurance.mvvm.viewmodel.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import cn.ecar.insurance.dao.base.BaseEntity;
import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.gson.BankGson;
import cn.ecar.insurance.dao.gson.CustomerGson;
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

}

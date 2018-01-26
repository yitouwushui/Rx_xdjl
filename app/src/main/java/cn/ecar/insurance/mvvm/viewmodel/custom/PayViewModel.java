package cn.ecar.insurance.mvvm.viewmodel.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;

import cn.ecar.insurance.dao.gson.ProvinceGson;
import cn.ecar.insurance.mvvm.model.custom.PayModel;
import cn.ecar.insurance.mvvm.model.main.ShareModel;

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
     * 获的二维码
     *
     * @return
     */
    public LiveData<ProvinceGson> getProvinceList() {
        return mPayModel.getProvinceList();
    }

}

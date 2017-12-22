package cn.ecar.insurance.mvvm.viewmodel.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import cn.ecar.insurance.base.BaseEntity;
import cn.ecar.insurance.mvvm.model.custom.CustomModel;

/**
 *
 * @author yx
 * @date 2017/8/11
 * custom viewmodel
 * 包含三方库调用api(百度地图,友盟,环信等),app启动时调用api,产品统计等相关api
 */
public class CustomViewModel extends ViewModel{

    private CustomModel mCustomModel;

    public CustomViewModel() {
        mCustomModel = CustomModel.getInstance();
    }

    public BaseEntity getBase() {
        return  mCustomModel.getBase();
    }


    public LiveData<String> getBaiDu() {
        return mCustomModel.getBaiDu();
    }
}
package cn.ecar.insurance.mvvm.viewmodel.home;

import android.arch.lifecycle.ViewModel;

import cn.ecar.insurance.mvvm.model.home.AdvertsModel;

/**
 * @author yx
 * @date 2017/8/11
 * custom viewmodel
 * 包含三方库调用api(百度地图,友盟,环信等),app启动时调用api,产品统计等相关api
 */
public class AdvertsViewModel extends ViewModel {

    private AdvertsModel mAdvertsModel;


    public AdvertsViewModel() {
        mAdvertsModel = AdvertsModel.getInstance();
    }


}

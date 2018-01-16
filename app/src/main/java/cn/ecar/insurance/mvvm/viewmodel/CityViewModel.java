package cn.ecar.insurance.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import cn.ecar.insurance.mvvm.model.CityModel;


/**
 * Created by yx on 2017/8/13.
 * city viewmodel
 */

public class CityViewModel extends ViewModel {

    private CityModel mCityModel;

    public CityViewModel() {
        mCityModel = new CityModel();
    }

    //城市集合
    public LiveData<List<CityModel>> getCityList() {
        return mCityModel.getCiytList();
    }

    //热门城市集合
    public LiveData<List<String>> getHotCityList() {
        return mCityModel.getHotCityList();
    }

    //获取单个城市
    public LiveData<String> getSingleCity(String city) {
        return mCityModel.getSingleCity(city);
    }


}

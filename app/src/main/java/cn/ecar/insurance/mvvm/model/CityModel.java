package cn.ecar.insurance.mvvm.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.mvvm.base.BaseModel;
import cn.ecar.insurance.utils.city.PinYinUtils;

/**
 * Created by yx on 2016/7/22.
 * 城市model
 */
public class CityModel extends BaseModel implements Comparable<CityModel> {

    private String mName;
    private String mPinyin;

    public CityModel(String city) {
        String pinyin = PinYinUtils.getPinyin(city);
        if (pinyin.contains("ZHANGSHA")) {
            pinyin = "CHANGSHA";
        }
        if (pinyin.contains("ZHANGCHUN")) {
            pinyin = "CHANGCHUN";
        }
        if (pinyin.contains("ZHANGZHI")) {
            pinyin = "CHANGZHI";
        }
        if (pinyin.contains("ZHONGQING")) {
            pinyin = "CHONGQING";
        }
        if (pinyin.contains("SHAMEN")) {
            pinyin = "XIAMEN";
        }
        this.mPinyin = pinyin;
        this.mName = city;
    }

    public CityModel() {

    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPinyin() {
        return mPinyin;
    }

    public void setPinyin(String pinyin) {
        this.mPinyin = pinyin;
    }

    @Override
    public int compareTo(CityModel another) {
        return this.mPinyin.compareTo(another.getPinyin());
    }

    /**
     * 城市列表集合
     * @return
     */
    public LiveData<List<CityModel>> getCiytList() {
        MutableLiveData<List<CityModel>> data = new MutableLiveData<>();
        List<CityModel> cityList = XdAppContext.app().getCityList();
        data.setValue(cityList);
        return data;
    }

    /**
     * 热门城市列表
     * @return
     */
    public LiveData<List<String>> getHotCityList() {
        MutableLiveData<List<String>> data = new MutableLiveData<>();
        List<String> hotCityList = XdAppContext.app().getHotCityList();
        data.setValue(hotCityList);
        return data;
    }

    //获取单个城市
    public LiveData<String> getSingleCity(String city) {
        MutableLiveData<String> data = new MutableLiveData<>();
        String[] split = city.split(" ");
        if (split.length == 3) {
            city = split[1];
        }
        data.setValue(city);
        return data;
    }


}

package cn.ecar.insurance.utils.city;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.ecar.insurance.config.Cheese;
import cn.ecar.insurance.mvvm.model.CityModel;


/**
 * Created by yx on 2016/12/16.
 * 城市选择初始化
 */

public class CityChoiceUtils {

    private static volatile CityChoiceUtils instance;
    private List<CityModel> cityList = new ArrayList<>();
    private List<String> hotCityList = new ArrayList<>();

    public static CityChoiceUtils getInstance() {
        if (instance == null) {
            synchronized (CityChoiceUtils.class) {
                if (instance == null) {
                    instance = new CityChoiceUtils();
                }
            }
        }
        return instance;
    }

    public List<CityModel> initCity() {
        for (String city : Cheese.getCityNames()) {
            cityList.add(new CityModel(city));
        }
        Collections.sort(cityList);
        return cityList;
    }

    public List<String> initHotCity() {
        Collections.addAll(hotCityList, Cheese.getHotCity());
        return hotCityList;
    }


}

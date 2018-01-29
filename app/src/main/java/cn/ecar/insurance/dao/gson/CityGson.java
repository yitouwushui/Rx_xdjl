package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.City;
import cn.ecar.insurance.dao.bean.Province;

/**
 * @author ding
 * @date 2018/1/26
 */
public class CityGson extends BaseGson {


    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}

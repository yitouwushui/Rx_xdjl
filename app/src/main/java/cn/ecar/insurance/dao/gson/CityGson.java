package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.City;

/**
 * @author ding
 * @date 2018/1/26
 */
public class CityGson extends BaseGson {

    /**
     * 银行卡相关城市
     */
    private List<City> cityList;
    /**
     * 保险相关城市
     */
    private List<City> insuranceCitycodeDtoList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<City> getInsuranceCitycodeDtoList() {
        return insuranceCitycodeDtoList;
    }

    public void setInsuranceCitycodeDtoList(List<City> insuranceCitycodeDtoList) {
        this.insuranceCitycodeDtoList = insuranceCitycodeDtoList;
    }
}

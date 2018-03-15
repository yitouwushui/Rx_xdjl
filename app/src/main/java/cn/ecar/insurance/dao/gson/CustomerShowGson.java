package cn.ecar.insurance.dao.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.CustomerHeroBean;

/**
 * 明星会员展示gson
 *
 * @author ding
 * @date 2018/1/23
 */
public class CustomerShowGson extends BaseGson {


    private List<CustomerHeroBean> customerHeroInfoList;

    public List<CustomerHeroBean> getCustomerHeroInfoList() {
        return customerHeroInfoList;
    }
    public void setCustomerHeroInfoList(List<CustomerHeroBean> customerHeroInfoList) {
        this.customerHeroInfoList = customerHeroInfoList;
    }

}


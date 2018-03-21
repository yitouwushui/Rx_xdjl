package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.CustomerHeroBean;
import cn.ecar.insurance.dao.bean.CustomerMember;

/**
 * 明星会员展示gson
 *
 * @author ding
 * @date 2018/1/23
 */
public class CustomerShowGson extends BaseGson {


    /**
     * 首页会员展示
     */
    private List<CustomerMember> customerShowDto;
    /**
     * list页英雄榜
     */
    private List<CustomerHeroBean> customerHeroInfoList;

    public List<CustomerMember> getCustomerShowDto() {
        return customerShowDto;
    }

    public void setCustomerShowDto(List<CustomerMember> customerShowDto) {
        this.customerShowDto = customerShowDto;
    }

    public List<CustomerHeroBean> getCustomerHeroInfoList() {
        return customerHeroInfoList;
    }

    public void setCustomerHeroInfoList(List<CustomerHeroBean> customerHeroInfoList) {
        this.customerHeroInfoList = customerHeroInfoList;
    }

}


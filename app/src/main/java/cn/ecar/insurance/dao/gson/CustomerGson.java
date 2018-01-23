package cn.ecar.insurance.dao.gson;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Customer;

/**
 *
 * @author ding
 * @date 2018/1/22
 */

public class CustomerGson extends BaseGson {

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}

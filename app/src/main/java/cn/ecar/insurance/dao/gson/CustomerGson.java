package cn.ecar.insurance.dao.gson;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Customer;

/**
 * 客户gson
 *
 * @author ding
 * @date 2018/1/22
 */

public class CustomerGson extends BaseGson {

    /**
     * login返回customer
     */
    private Customer customer;

    /**
     * getCustomerInfo返回customer
     */
    private Customer customerInfo;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CustomerGson{" +
                "customer=" + customer +
                '}';
    }
}

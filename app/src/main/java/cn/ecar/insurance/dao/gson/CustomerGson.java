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

    private String sessionId;

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

    public Customer getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(Customer customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "CustomerGson{" +
                "customer=" + customer +
                '}';
    }
}

package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Customer;

/**
 * 明星会员展示gson
 *
 * @author ding
 * @date 2018/1/23
 */
public class CustomerShowGson extends BaseGson {

    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "CustomerShowGson{" +
                "customers=" + customers +
                '}';
    }
}


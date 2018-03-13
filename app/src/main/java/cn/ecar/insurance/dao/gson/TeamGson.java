package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Customer;

/**
 * @author ding
 * @date 2018/2/7
 */
public class TeamGson extends BaseGson {

    /**
     * total : 1
     * pageSize : 6
     * list : [{"customerCode":"C18030500028","customerId":29,"firstAgentId":26,"firstQuantity":0,"identity":"1","phoneNo":"13818178888","registTime":1520228543000,"secondQuantity":0,"status":"1","type":"1"}]
     */
    private int total;
    private String pageSize;
    private List<Customer> list;
    /**
     * agentInfo : {"customerCode":"C18030500027","customerId":26,"identity":"1","phoneNo":"13818175906","registTime":1520220574000,"status":"1","type":"1"}
     */
    private Customer agentInfo;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        this.list = list;
    }

    public Customer getAgentInfo() {
        return agentInfo;
    }

    public void setAgentInfo(Customer agentInfo) {
        this.agentInfo = agentInfo;
    }
}

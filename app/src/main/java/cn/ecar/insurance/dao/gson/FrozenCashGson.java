package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.FrozenCash;

/**
 *
 * @author ding
 * @date 2018/3/14
 */

public class FrozenCashGson extends BaseGson {


    /**
     * total : 1
     * pageSize : 6
     * list : [{"amount":200,"createTime":1520228559000,"cusCustomerId":29,"customerId":26,"frozenId":4,"frozenTime":1520228551000,"phoneNo":"13818178888","status":"1","type":"2"}]
     */

    private int total;
    private String pageSize;
    private List<FrozenCash> list;

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

    public List<FrozenCash> getList() {
        return list;
    }

    public void setList(List<FrozenCash> list) {
        this.list = list;
    }

}

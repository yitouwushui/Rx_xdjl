package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.FundsFlow;

/**
 * @author ding
 * @date 2018/3/14
 */
public class FundsFlowGson extends BaseGson {


    /**
     * total : 64
     * pageSize : 6
     * list : [{"accountId":30,"accountStatus":"0","amount":2300,"checkStatus":"0","fundioOrderId":117,"fundioOrderSn":"2018031300000121","ioflag":"2","orderTime":1520870400000,"paymentOrg":"1","paymentRequestTime":1520870400000,"paymentStatus":"1","status":"1"},{"accountId":26,"accountStatus":"0","amount":1700,"checkStatus":"0","fundioOrderId":118,"fundioOrderSn":"2018031300000122","ioflag":"2","orderTime":1520870400000,"paymentOrg":"1","paymentStatus":"0","status":"0"},{"accountId":26,"accountStatus":"0","amount":1700,"checkStatus":"0","fundioOrderId":119,"fundioOrderSn":"2018031300000123","ioflag":"2","orderTime":1520870400000,"paymentOrg":"1","paymentStatus":"0","status":"0"},{"accountId":26,"accountStatus":"0","amount":1700,"checkStatus":"0","fundioOrderId":120,"fundioOrderSn":"2018031300000124","ioflag":"2","orderTime":1520870400000,"paymentOrg":"1","paymentStatus":"0","status":"0"},{"accountId":26,"accountStatus":"0","amount":1700,"checkStatus":"0","fundioOrderId":121,"fundioOrderSn":"2018031300000125","ioflag":"2","orderTime":1520870400000,"paymentOrg":"1","paymentStatus":"0","status":"0"},{"accountId":26,"accountStatus":"0","amount":599,"checkStatus":"0","fundioOrderId":122,"fundioOrderSn":"2018031300000126","ioflag":"2","orderTime":1520870400000,"paymentOrg":"1","paymentRequestTime":1520870400000,"paymentStatus":"1","status":"1"}]
     */

    private int total;
    private String pageSize;
    private List<FundsFlow> list;

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

    public List<FundsFlow> getList() {
        return list;
    }

    public void setList(List<FundsFlow> list) {
        this.list = list;
    }

}

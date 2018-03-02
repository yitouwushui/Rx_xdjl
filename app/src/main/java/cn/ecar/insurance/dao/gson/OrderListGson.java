package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.OrderBean;

/**
 * Created by ding on 2018/2/28.
 */

public class OrderListGson extends BaseGson {


    /**
     * responseCode : EC0000
     * status : 0
     * responseMsg : 获取信息成功!
     * orderList : [{"areaCode":"1","brandModel":"名爵CSA7153ACS轿车","carNumber":"苏AS37S1","certCode":"320114198411191812","companyId":1,"createTime":1519811786740,"customerId":5,"engineNumber":"B2GD3040576","insuranceName":"太平洋车险","name":"徐文杰","orderNo":"IO00000037","phone":"13888881818","registerDate":1367078400000,"status":"1","vehicleCode":"LSJZ14E65DS034002"},{"areaCode":"1","brandModel":"名爵CSA7153ACS轿车","carNumber":"苏AS37S1","certCode":"320114198411191812","companyId":3,"createTime":1519811786781,"customerId":5,"engineNumber":"B2GD3040576","insuranceName":"人保车险","name":"徐文杰","orderNo":"IO00000038","phone":"13888881818","registerDate":1367078400000,"status":"1","vehicleCode":"LSJZ14E65DS034002"}]
     */
    private List<OrderBean> orderList;

    /**
     * 单个价格
     */
    private OrderBean insuranceOrderDto;

    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderBean> orderList) {
        this.orderList = orderList;
    }

    public OrderBean getInsuranceOrderDto() {
        return insuranceOrderDto;
    }

    public void setInsuranceOrderDto(OrderBean insuranceOrderDto) {
        this.insuranceOrderDto = insuranceOrderDto;
    }
}

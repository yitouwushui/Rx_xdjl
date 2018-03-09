package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.dao.bean.InsuranceOrderType;

/**
 * @author ding
 * @date 2018/2/28
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

    /**
     * 单个价格
     */
    private List<InsuranceOrderType> insuranceOrderDetailDtoList;
    /**
     * payAmount : 3920.0
     * insuranceOrderDto : {"addressId":1,"areaCode":"8","backAmount":1680,"brandModel":"名爵CSA7153ACS轿车","carNumber":"苏AS37S1","certCode":"320114198411191812","companyId":3,"createTime":1519903956000,"customerId":4,"engineNumber":"B2GD3040576","forceInsurance":950,"insuranceName":"人保车险","name":"徐文杰","orderId":69,"orderNo":"IO00000069","phone":"","registerDate":1367078400000,"status":"3","totalAmount":5600,"totalBusiness":4500,"totalForcetax":1100,"vehicleCode":"LSJZ14E65DS034002","vehicleTax":150}
     * backPrice : 1680.0
     * memberPrice : 3920.0
     * totalPrice : 5600.0
     */

    private double payAmount;
    private double backPrice;
    private double memberPrice;
    private double totalPrice;

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

    public List<InsuranceOrderType> getInsuranceOrderDetailDtoList() {
        return insuranceOrderDetailDtoList;
    }

    public void setInsuranceOrderDetailDtoList(List<InsuranceOrderType> insuranceOrderDetailDtoList) {
        this.insuranceOrderDetailDtoList = insuranceOrderDetailDtoList;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public double getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(double backPrice) {
        this.backPrice = backPrice;
    }

    public double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

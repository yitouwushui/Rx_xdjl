package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.OrderBean;

/**
 * @author ding
 * @date 2018/2/7
 */
public class InsuranceGson extends BaseGson {

    /**
     * total : 104
     * pageSize : 6
     * list : [{"areaCode":"1","brandModel":"名爵CSA7153ACS轿车","carNumber":"苏AS37S1","certCode":"320114198411191812","companyId":1,"createTime":1519981915000,"customerId":5,"engineNumber":"B2GD3040576","insuranceName":"太平洋车险","name":"徐文杰","orderId":106,"orderNo":"IO00000106","phone":"","registerDate":1367078400000,"status":"3","vehicleCode":"LSJZ14E65DS034002"},{"areaCode":"1","brandModel":"名爵CSA7153ACS轿车","carNumber":"苏AS37S1","certCode":"320114198411191812","companyId":3,"createTime":1519981915000,"customerId":5,"engineNumber":"B2GD3040576","insuranceName":"人保车险","name":"徐文杰","orderId":107,"orderNo":"IO00000107","phone":"","registerDate":1367078400000,"status":"3","vehicleCode":"LSJZ14E65DS034002"},{"areaCode":"1","brandModel":"名爵CSA7153ACS轿车","carNumber":"苏AS37S1","certCode":"320114198411191812","companyId":1,"createTime":1519981860000,"customerId":5,"engineNumber":"B2GD3040576","insuranceName":"太平洋车险","name":"徐文杰","orderId":104,"orderNo":"IO00000104","phone":"","registerDate":1367078400000,"status":"3","vehicleCode":"LSJZ14E65DS034002"},{"areaCode":"1","brandModel":"名爵CSA7153ACS轿车","carNumber":"苏AS37S1","certCode":"320114198411191812","companyId":3,"createTime":1519981860000,"customerId":5,"engineNumber":"B2GD3040576","insuranceName":"人保车险","name":"徐文杰","orderId":105,"orderNo":"IO00000105","phone":"","registerDate":1367078400000,"status":"3","vehicleCode":"LSJZ14E65DS034002"},{"areaCode":"1","brandModel":"名爵CSA7153ACS轿车","carNumber":"苏AS37S1","certCode":"320114198411191812","companyId":3,"createTime":1519979682000,"customerId":5,"engineNumber":"B2GD3040576","insuranceName":"人保车险","name":"徐文杰","orderId":103,"orderNo":"IO00000103","phone":"","registerDate":1367078400000,"status":"3","vehicleCode":"LSJZ14E65DS034002"},{"areaCode":"1","brandModel":"名爵CSA7153ACS轿车","carNumber":"苏AS37S1","certCode":"320114198411191812","companyId":1,"createTime":1519979682000,"customerId":5,"engineNumber":"B2GD3040576","insuranceName":"太平洋车险","name":"徐文杰","orderId":102,"orderNo":"IO00000102","phone":"","registerDate":1367078400000,"status":"3","vehicleCode":"LSJZ14E65DS034002"}]
     */
    private int total;
    private String pageSize;
    private List<OrderBean> list;

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

    public List<OrderBean> getList() {
        return list;
    }

    public void setList(List<OrderBean> list) {
        this.list = list;
    }

}

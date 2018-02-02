package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.CategoryBean;

/**
 * @author ding
 * @date 2018/2/1
 */

public class CateMapGson extends BaseGson {


    /**
     * forceStartDate : 2018-02-01
     * cateAndValuesDtoList : [{"cateId":1,"categoriesValuesDtoLit":[{"cateId":1,"cateValue":"0","name":"单商业","valueId":1},{"cateId":1,"cateValue":"1","name":"商业+交强车船","valueId":2},{"cateId":1,"cateValue":"2","name":"单交强+车船","valueId":3}],"name":"交强险","shortName":"ForceTax","sort":1},{"cateId":2,"categoriesValuesDtoLit":[{"cateId":2,"cateValue":"0","name":"不投保","valueId":4},{"cateId":2,"cateValue":"1","name":"国产","valueId":5},{"cateId":2,"cateValue":"2","name":"进口","valueId":6}],"name":"玻璃单独破碎险","shortName":"BoLi","sort":2},{"cateId":3,"categoriesValuesDtoLit":[{"cateId":3,"cateValue":"0","name":"不投保","valueId":7},{"cateId":3,"cateValue":"1","name":"投保","valueId":8}],"name":"涉水行驶损失险","shortName":"SheShui","sort":3},{"cateId":4,"categoriesValuesDtoLit":[{"cateId":4,"cateValue":"0","name":"不投保","valueId":9},{"cateId":4,"cateValue":"2000","name":"2000元","valueId":10},{"cateId":4,"cateValue":"5000","name":"5000元","valueId":11},{"cateId":4,"cateValue":"10000","name":"1万元","valueId":12},{"cateId":4,"cateValue":"20000","name":"2万元","valueId":13}],"name":"车身划痕损失险","shortName":"HuaHen","sort":4},{"cateId":5,"categoriesValuesDtoLit":[{"cateId":5,"cateValue":"0","name":"不投保","valueId":14},{"cateId":5,"cateValue":"500000","name":"50万元","valueId":22},{"cateId":5,"cateValue":"200000","name":"20万元","valueId":21},{"cateId":5,"cateValue":"100000","name":"10万元","valueId":20},{"cateId":5,"cateValue":"50000","name":"5万元","valueId":19},{"cateId":5,"cateValue":"40000","name":"4万元","valueId":18},{"cateId":5,"cateValue":"30000","name":"3万元","valueId":17},{"cateId":5,"cateValue":"20000","name":"2万元","valueId":16},{"cateId":5,"cateValue":"10000","name":"1万元","valueId":15},{"cateId":5,"cateValue":"1000000","name":"100万元","valueId":23}],"name":"车上司机责任险","shortName":"SiJi","sort":5},{"cateId":6,"categoriesValuesDtoLit":[{"cateId":6,"cateValue":"0","name":"不投保","valueId":24},{"cateId":6,"cateValue":"500000","name":"50万元","valueId":32},{"cateId":6,"cateValue":"200000","name":"20万元","valueId":31},{"cateId":6,"cateValue":"100000","name":"10万元","valueId":30},{"cateId":6,"cateValue":"50000","name":"5万元","valueId":29},{"cateId":6,"cateValue":"40000","name":"4万元","valueId":28},{"cateId":6,"cateValue":"30000","name":"3万元","valueId":27},{"cateId":6,"cateValue":"20000","name":"2万元","valueId":26},{"cateId":6,"cateValue":"10000","name":"1万元","valueId":25},{"cateId":6,"cateValue":"1000000","name":"100万元","valueId":33}],"name":"车上乘客责任险","shortName":"ChengKe","sort":6},{"cateId":7,"categoriesValuesDtoLit":[{"cateId":7,"cateValue":"0","name":"不投保","valueId":34},{"cateId":7,"cateValue":"1","name":"投保","valueId":35}],"name":"机动车损失保险","shortName":"CheSun","sort":7},{"cateId":8,"categoriesValuesDtoLit":[{"cateId":8,"cateValue":"0","name":"不投保","valueId":36},{"cateId":8,"cateValue":"1","name":"投保","valueId":37}],"name":"全车盗抢保险","shortName":"DaoQiang","sort":8},{"cateId":9,"categoriesValuesDtoLit":[{"cateId":9,"cateValue":"0","name":"不投保","valueId":38},{"cateId":9,"cateValue":"3000000","name":"300万元","valueId":49},{"cateId":9,"cateValue":"2500000","name":"250万元","valueId":48},{"cateId":9,"cateValue":"2000000","name":"200万元","valueId":47},{"cateId":9,"cateValue":"1500000","name":"150万元","valueId":46},{"cateId":9,"cateValue":"1000000","name":"100万元","valueId":45},{"cateId":9,"cateValue":"500000","name":"50万元","valueId":44},{"cateId":9,"cateValue":"300000","name":"30万元","valueId":43},{"cateId":9,"cateValue":"200000","name":"20万元","valueId":42},{"cateId":9,"cateValue":"150000","name":"15万元","valueId":41},{"cateId":9,"cateValue":"100000","name":"10万元","valueId":40},{"cateId":9,"cateValue":"50000","name":"5万元","valueId":39},{"cateId":9,"cateValue":"5000000","name":"500万元","valueId":50}],"name":"第三者责任保险","shortName":"SanZhe","sort":9},{"cateId":10,"categoriesValuesDtoLit":[{"cateId":10,"cateValue":"0","name":"不投保","valueId":51},{"cateId":10,"cateValue":"1","name":"投保","valueId":52}],"name":"自燃损失险","shortName":"ZiRan","sort":10},{"cateId":11,"categoriesValuesDtoLit":[{"cateId":11,"cateValue":"0","name":"不投保","valueId":53},{"cateId":11,"cateValue":"1","name":"投保","valueId":54}],"name":"指定修理厂险","shortName":"HcXiuLiChang","sort":11}]
     * businessStartDate : 2018-02-01
     */
    private String forceStartDate;
    private String businessStartDate;
    private List<CategoryBean> cateAndValuesDtoList;

    public String getForceStartDate() {
        return forceStartDate;
    }

    public void setForceStartDate(String forceStartDate) {
        this.forceStartDate = forceStartDate;
    }

    public String getBusinessStartDate() {
        return businessStartDate;
    }

    public void setBusinessStartDate(String businessStartDate) {
        this.businessStartDate = businessStartDate;
    }

    public List<CategoryBean> getCateAndValuesDtoList() {
        return cateAndValuesDtoList;
    }

    public void setCateAndValuesDtoList(List<CategoryBean> cateAndValuesDtoList) {
        this.cateAndValuesDtoList = cateAndValuesDtoList;
    }
}

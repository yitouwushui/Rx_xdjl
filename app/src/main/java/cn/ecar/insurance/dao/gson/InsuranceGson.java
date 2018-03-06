package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.InsuranceDetails;

/**
 *
 * @author ding
 * @date 2018/2/7
 */
public class InsuranceGson extends BaseGson {

    private List<InsuranceDetails> data;

    public List<InsuranceDetails> getData() {
        return data;
    }

    public void setData(List<InsuranceDetails> data) {
        this.data = data;
    }
}

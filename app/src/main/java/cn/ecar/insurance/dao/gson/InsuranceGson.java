package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Insurance;

/**
 *
 * @author ding
 * @date 2018/2/7
 */
public class InsuranceGson extends BaseGson {

    private List<Insurance> data;

    public List<Insurance> getData() {
        return data;
    }

    public void setData(List<Insurance> data) {
        this.data = data;
    }
}

package cn.ecar.insurance.mvvm.model.insurance;

import cn.ecar.insurance.dao.base.BaseEntity;
import cn.ecar.insurance.mvvm.base.BaseModel;

/**
 * Created by ding on 2018/1/12.
 */

public class InsureModel extends BaseModel {

    private static volatile InsureModel instance;

    public static InsureModel getInstance() {
        if (instance == null) {
            synchronized (InsureModel.class) {
                if (instance == null) {
                    instance = new InsureModel();
                }
            }
        }
        return instance;
    }


    public BaseEntity getInsuranceList() {

        return null;
    }
}

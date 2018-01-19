package cn.ecar.insurance.mvvm.model.insurance;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import cn.ecar.insurance.base.BaseEntity;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.mvvm.base.BaseModel;
import cn.ecar.insurance.utils.encrypt.MD5Helper;
import cn.ecar.insurance.utils.system.OtherUtil;

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

package cn.ecar.insurance.mvvm.model.custom;

import cn.ecar.insurance.base.BaseEntity;
import cn.ecar.insurance.mvvm.base.BaseModel;

/**
 * Created by ding on 2018/1/12.
 */

public class LoginModel extends BaseModel {

    private static volatile LoginModel instance;

    public static LoginModel getInstance() {
        if (instance == null) {
            synchronized (LoginModel.class) {
                if (instance == null) {
                    instance = new LoginModel();
                }
            }
        }
        return instance;
    }


    public BaseEntity login() {

        return null;
    }
}

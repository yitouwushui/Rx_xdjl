package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.SignIn;

/**
 * @author ding
 * @date 2018/2/7
 */
public class SignInGson extends BaseGson {

    private List<SignIn> data;

    public List<SignIn> getData() {
        return data;
    }

    public void setData(List<SignIn> data) {
        this.data = data;
    }
}

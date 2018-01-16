package cn.ecar.insurance.mvvm.model.custom;

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


    public BaseEntity login(String account, String pwd) {
        HashMap<String, String> hm = new HashMap<>(5);
        hm.put("customerName", account);
//        hm.put("")
        try {
            String sign = MD5Helper.getSign(hm, XdConfig.APP_SECRET, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        hm.put("version", OtherUtil.getVersionName(getAppContext()));
        hm.put("timestamp", String.valueOf(System.currentTimeMillis()));
        hm.put("appId", XdConfig.APP_ID);
        return null;
    }
}

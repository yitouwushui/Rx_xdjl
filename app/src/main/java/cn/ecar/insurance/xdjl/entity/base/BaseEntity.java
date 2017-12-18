package cn.ecar.insurance.xdjl.entity.base;

import java.util.List;

/**
 * Created by yx on 2016/11/11.
 *
 */

public class BaseEntity<T> {


    public String result;
    public String msg;
//    private String token;
//
//    public String getToken() {
//        return token;
//    }

    private List<T> Rows;

    public List<T> getRows() {
        return Rows;
    }

    public String getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

}

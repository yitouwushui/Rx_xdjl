package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.SignIn;

/**
 * @author ding
 * @date 2018/2/7
 */
public class SignInGson extends BaseGson {


    /**
     * total : 1
     * pageSize : 6
     * list : [{"customerId":26,"luckdrawTimes":0,"signDate":1520956800000}]
     */
    private int total;
    private String pageSize;
    private List<SignIn> list;
    /**
     * isSign=0 已签到  isSign=-1 未签到
     */
    private int isSign;
    /**
     * signStatus : 0
     */
    private String signStatus;


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

    public List<SignIn> getList() {
        return list;
    }

    public void setList(List<SignIn> list) {
        this.list = list;
    }

    public int getIsSign() {
        return isSign;
    }

    public void setIsSign(int isSign) {
        this.isSign = isSign;
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }
}

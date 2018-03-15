package cn.ecar.insurance.dao.bean;

import cn.ecar.insurance.dao.base.BaseBean;

/**
 * @author ding
 * @date 2018/2/26
 */

public class SignIn extends BaseBean {


    /**
     * customerId : 26
     * luckdrawTimes : 0
     * signDate : 1520956800000
     */

    private int customerId;
    private int luckdrawTimes;
    private long signDate;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getLuckdrawTimes() {
        return luckdrawTimes;
    }

    public void setLuckdrawTimes(int luckdrawTimes) {
        this.luckdrawTimes = luckdrawTimes;
    }

    public long getSignDate() {
        return signDate;
    }

    public void setSignDate(long signDate) {
        this.signDate = signDate;
    }
}

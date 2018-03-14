package cn.ecar.insurance.dao.bean;

import cn.ecar.insurance.dao.base.BaseBean;

/**
 * Created by ding on 2018/3/14.
 */

public class FundsFlow extends BaseBean {

    /**
     * accountId : 30
     * accountStatus : 0
     * amount : 2300.0
     * checkStatus : 0
     * fundioOrderId : 117
     * fundioOrderSn : 2018031300000121
     * ioflag : 2
     * orderTime : 1520870400000
     * paymentOrg : 1
     * paymentRequestTime : 1520870400000
     * paymentStatus : 1
     * status : 1
     */

    private int accountId;
    private String accountStatus;
    private double amount;
    private String checkStatus;
    private int fundioOrderId;
    private String fundioOrderSn;
    private String ioflag;
    private long orderTime;
    private String paymentOrg;
    private long paymentRequestTime;
    private String paymentStatus;
    private String status;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getFundioOrderId() {
        return fundioOrderId;
    }

    public void setFundioOrderId(int fundioOrderId) {
        this.fundioOrderId = fundioOrderId;
    }

    public String getFundioOrderSn() {
        return fundioOrderSn;
    }

    public void setFundioOrderSn(String fundioOrderSn) {
        this.fundioOrderSn = fundioOrderSn;
    }

    public String getIoflag() {
        return ioflag;
    }

    public void setIoflag(String ioflag) {
        this.ioflag = ioflag;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public String getPaymentOrg() {
        return paymentOrg;
    }

    public void setPaymentOrg(String paymentOrg) {
        this.paymentOrg = paymentOrg;
    }

    public long getPaymentRequestTime() {
        return paymentRequestTime;
    }

    public void setPaymentRequestTime(long paymentRequestTime) {
        this.paymentRequestTime = paymentRequestTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String statusX) {
        this.status = statusX;
    }
}

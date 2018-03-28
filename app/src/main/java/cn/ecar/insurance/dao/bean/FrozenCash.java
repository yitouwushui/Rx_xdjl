package cn.ecar.insurance.dao.bean;

import cn.ecar.insurance.dao.base.BaseBean;

/**
 *
 * @author ding
 * @date 2018/3/14
 */

public class FrozenCash extends BaseBean {
    /**
     * amount : 200.0
     * createTime : 1520228559000
     * cusCustomerId : 29
     * customerId : 26
     * frozenId : 4
     * frozenTime : 1520228551000
     * phoneNo : 13818178888
     * status : 1
     * type : 2
     */

    private double amount;
    private long createTime;
    private int cusCustomerId;
    private int customerId;
    private int frozenId;
    private long frozenTime;
    private String phoneNo;
    private String status;
    private String type;
    private String customerCode;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCusCustomerId() {
        return cusCustomerId;
    }

    public void setCusCustomerId(int cusCustomerId) {
        this.cusCustomerId = cusCustomerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFrozenId() {
        return frozenId;
    }

    public void setFrozenId(int frozenId) {
        this.frozenId = frozenId;
    }

    public long getFrozenTime() {
        return frozenTime;
    }

    public void setFrozenTime(long frozenTime) {
        this.frozenTime = frozenTime;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

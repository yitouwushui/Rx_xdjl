package cn.ecar.insurance.dao.bean;

import java.io.Serializable;

/**
 * Created by ding on 2018/1/22.
 */

public class Customer implements Serializable {

    /**
     * customerCode : C18010800016
     * customerId : 2
     * firstAgentId : -1
     * identity : 1
     * phoneNo : 13818178888
     * pw :
     * registTime : 1515380194000
     * salt :
     * secondAgentId : -1
     * status : 1
     * thirdAgentId : -1
     * type : 1
     */

    private String customerCode;
    private int customerId;
    private int firstAgentId;
    private String identity;
    private String phoneNo;
    private String pw;
    private long registTime;
    private String salt;
    private int secondAgentId;
    private String status;
    private int thirdAgentId;
    private String type;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFirstAgentId() {
        return firstAgentId;
    }

    public void setFirstAgentId(int firstAgentId) {
        this.firstAgentId = firstAgentId;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public long getRegistTime() {
        return registTime;
    }

    public void setRegistTime(long registTime) {
        this.registTime = registTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getSecondAgentId() {
        return secondAgentId;
    }

    public void setSecondAgentId(int secondAgentId) {
        this.secondAgentId = secondAgentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getThirdAgentId() {
        return thirdAgentId;
    }

    public void setThirdAgentId(int thirdAgentId) {
        this.thirdAgentId = thirdAgentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package cn.ecar.insurance.dao.bean;

import cn.ecar.insurance.dao.base.BaseBean;

/**
 * @author yitouwushui
 * @date 2018/1/25
 */
public class CashAccount extends BaseBean {

    private String accountType;
    private long activateTime;
    private double balance;
    private int cashAccountId;
    private int customerId;
    private double ecarBalance;
    private double ecarFrozenBalance;
    private double frozenBalance;
    private String mobile;
    private long opendate;
    private String pw;
    private String salt;
    private String status;
    private double totalBalance;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public long getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(long activateTime) {
        this.activateTime = activateTime;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCashAccountId() {
        return cashAccountId;
    }

    public void setCashAccountId(int cashAccountId) {
        this.cashAccountId = cashAccountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getEcarBalance() {
        return ecarBalance;
    }

    public void setEcarBalance(double ecarBalance) {
        this.ecarBalance = ecarBalance;
    }

    public double getEcarFrozenBalance() {
        return ecarFrozenBalance;
    }

    public void setEcarFrozenBalance(double ecarFrozenBalance) {
        this.ecarFrozenBalance = ecarFrozenBalance;
    }

    public double getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(double frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getOpendate() {
        return opendate;
    }

    public void setOpendate(long opendate) {
        this.opendate = opendate;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }
}

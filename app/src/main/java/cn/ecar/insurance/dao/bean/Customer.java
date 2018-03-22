package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import cn.ecar.insurance.dao.base.BaseBean;

/**
 * @author ding
 * @date 2018/1/22
 */

public class Customer extends BaseBean implements Serializable, Parcelable {

    /**
     * activationDate : 1515403234000
     * cashAccountDto : {"accountType":"2","activateTime":1515400526000,"balance":-1200,"cashAccountId":3,"customerId":4,"ecarBalance":0,"ecarFrozenBalance":0,"frozenBalance":0,"mobile":"13818178881","opendate":1515400526000,"pw":"aea15b2d0d22b31ffb95e35c89744379","salt":"78b6612e-032d-49ab-9917-1ed0d566931a","status":"1","totalBalance":0}
     * customerCode : C18010800017
     * customerId : 4
     * endDate : 1546939234000
     * firstAgentId : 2
     * identity : 1
     * phoneNo : 13818178881
     * pw : e88dba5e278e3dffedd726e9443de08d
     * registTime : 1515400526000
     * salt : 84657455-a07d-4da7-9831-7e245f778f25
     * secondAgentId : -1
     * status : 1
     * thirdAgentId : -1
     * type : 2
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
    /**
     * 客户类型：type 1：普通会员   2：E会员  3：合伙人
     */
    private String type;
    /**
     * 会员结束时间：endDate
     */
    private long endDate;
    private long activationDate;
    private String shareImagePath;
    private CashAccount cashAccountDto;
    /**
     * firstQuantity : 0
     * secondQuantity : 0
     */
    private int firstQuantity;
    private int secondQuantity;


    public Customer() {
    }


    protected Customer(Parcel in) {
        customerCode = in.readString();
        customerId = in.readInt();
        firstAgentId = in.readInt();
        identity = in.readString();
        phoneNo = in.readString();
        pw = in.readString();
        registTime = in.readLong();
        salt = in.readString();
        secondAgentId = in.readInt();
        status = in.readString();
        thirdAgentId = in.readInt();
        type = in.readString();
        endDate = in.readLong();
        activationDate = in.readLong();
        shareImagePath = in.readString();
        firstQuantity = in.readInt();
        secondQuantity = in.readInt();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public CashAccount getCashAccountDto() {
        return cashAccountDto;
    }


    public void setCashAccountDto(CashAccount cashAccountDto) {
        this.cashAccountDto = cashAccountDto;
    }

    public String getShareImagePath() {
        return shareImagePath;
    }

    public void setShareImagePath(String shareImagePath) {
        this.shareImagePath = shareImagePath;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public long getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(long activationDate) {
        this.activationDate = activationDate;
    }

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

    public int getFirstQuantity() {
        return firstQuantity;
    }

    public void setFirstQuantity(int firstQuantity) {
        this.firstQuantity = firstQuantity;
    }

    public int getSecondQuantity() {
        return secondQuantity;
    }

    public void setSecondQuantity(int secondQuantity) {
        this.secondQuantity = secondQuantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerCode);
        dest.writeInt(customerId);
        dest.writeInt(firstAgentId);
        dest.writeString(identity);
        dest.writeString(phoneNo);
        dest.writeString(pw);
        dest.writeLong(registTime);
        dest.writeString(salt);
        dest.writeInt(secondAgentId);
        dest.writeString(status);
        dest.writeInt(thirdAgentId);
        dest.writeString(type);
        dest.writeLong(endDate);
        dest.writeLong(activationDate);
        dest.writeString(shareImagePath);
        dest.writeInt(firstQuantity);
        dest.writeInt(secondQuantity);
    }
}

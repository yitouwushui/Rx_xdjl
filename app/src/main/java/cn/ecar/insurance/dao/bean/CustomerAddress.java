package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ding
 * @date 2018/3/7
 */

public class CustomerAddress extends BaseBean implements Parcelable {

    /**
     * address : 浦东南路1111号
     * addressId : 1
     * customerId : 4
     * isDefault : 0
     * phoneNo : 13818188888
     * receiver : 张三
     * region : 上海市浦东新区
     * status : 1
     */
    private String address;
    private int addressId;
    private int customerId;
    private String isDefault;
    private String phoneNo;
    private String receiver;
    private String region;
    private String status;

    public CustomerAddress() {
    }

    protected CustomerAddress(Parcel in) {
        address = in.readString();
        addressId = in.readInt();
        customerId = in.readInt();
        isDefault = in.readString();
        phoneNo = in.readString();
        receiver = in.readString();
        region = in.readString();
        status = in.readString();
    }

    public static final Creator<CustomerAddress> CREATOR = new Creator<CustomerAddress>() {
        @Override
        public CustomerAddress createFromParcel(Parcel in) {
            return new CustomerAddress(in);
        }

        @Override
        public CustomerAddress[] newArray(int size) {
            return new CustomerAddress[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeInt(addressId);
        dest.writeInt(customerId);
        dest.writeString(isDefault);
        dest.writeString(phoneNo);
        dest.writeString(receiver);
        dest.writeString(region);
        dest.writeString(status);
    }
}

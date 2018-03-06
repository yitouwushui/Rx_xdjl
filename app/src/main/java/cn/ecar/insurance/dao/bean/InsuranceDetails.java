package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ding
 * @date 2018/2/7
 */

public class InsuranceDetails extends BaseBean implements Parcelable {

    /**
     * name : 机动车损失保险
     * orderNo : IO00000069
     * status : 1
     */
    private String name;
    private String orderNo;
    private String status;

    public InsuranceDetails() {
    }

    protected InsuranceDetails(Parcel in) {
        name = in.readString();
        orderNo = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(orderNo);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InsuranceDetails> CREATOR = new Creator<InsuranceDetails>() {
        @Override
        public InsuranceDetails createFromParcel(Parcel in) {
            return new InsuranceDetails(in);
        }

        @Override
        public InsuranceDetails[] newArray(int size) {
            return new InsuranceDetails[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Creator<InsuranceDetails> getCREATOR() {
        return CREATOR;
    }
}

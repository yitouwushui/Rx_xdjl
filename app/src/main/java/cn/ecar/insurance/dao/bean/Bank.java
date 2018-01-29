package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import cn.ecar.insurance.mvvm.view.act.main.ChoiceInterFace;

/**
 *
 * @author ding
 * @date 2018/1/29
 */

public class Bank extends BaseBean implements Parcelable,Serializable,ChoiceInterFace{

    /**
     * bankId : 1
     * bankName : 中国工商银行
     * bankNo : 102
     * sort : 10
     */
    private int bankId;
    private String bankName;
    private String bankNo;
    private int sort;

    public Bank() {
    }

    protected Bank(Parcel in) {
        bankId = in.readInt();
        bankName = in.readString();
        bankNo = in.readString();
        sort = in.readInt();
    }

    public static final Creator<Bank> CREATOR = new Creator<Bank>() {
        @Override
        public Bank createFromParcel(Parcel in) {
            return new Bank(in);
        }

        @Override
        public Bank[] newArray(int size) {
            return new Bank[size];
        }
    };

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bankId);
        dest.writeString(bankName);
        dest.writeString(bankNo);
        dest.writeInt(sort);
    }

    @Override
    public String getSelectString() {
        return getBankNo();
    }

    @Override
    public Integer getSelectInteger() {
        return getBankId();
    }

    @Override
    public String getSelectContent() {
        return getBankName();
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", sort=" + sort +
                '}';
    }
}

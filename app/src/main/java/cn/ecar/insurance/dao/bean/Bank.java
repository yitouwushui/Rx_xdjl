package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import cn.ecar.insurance.dao.ChoiceInterFace;

/**
 * @author ding
 * @date 2016/10/11
 */
public class Bank implements Serializable, ChoiceInterFace, Parcelable {
    /**
     * 银行编号
     */
    String bankCode;
    /**
     * 银行名字
     */
    String bankName;
    /**
     * 银行编号(自用)
     */
    String ord;

    public Bank() {
    }

    protected Bank(Parcel in) {
        bankCode = in.readString();
        bankName = in.readString();
        ord = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bankCode);
        dest.writeString(bankName);
        dest.writeString(ord);
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    @Override
    public String getSelectCode() {
        return getBankCode();
    }

    @Override
    public String getSelectContent() {
        return getBankName();
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankCode='" + bankCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", ord='" + ord + '\'' +
                '}';
    }
}
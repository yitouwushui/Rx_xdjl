package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import cn.ecar.insurance.mvvm.view.act.main.ChoiceInterFace;

/**
 * @author ding
 * @date 2016/10/11
 */
public class Bank implements Serializable, ChoiceInterFace, Parcelable {
    /**
     * bankId : 1
     * bankNo : 102
     * binId : 237
     * branchName : 中国工商银行股份有限公司天津北城支行
     * branchNo : 102110001639
     */
    private int bankId;
    private String bankNo;
    private int binId;
    private String branchName;
    private String branchNo;
    public Bank() {
    }

    protected Bank(Parcel in) {
        bankId = in.readInt();
        bankNo = in.readString();
        binId = in.readInt();
        branchName = in.readString();
        branchNo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bankId);
        dest.writeString(bankNo);
        dest.writeInt(binId);
        dest.writeString(branchName);
        dest.writeString(branchNo);
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

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public int getBinId() {
        return binId;
    }

    public void setBinId(int binId) {
        this.binId = binId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    @Override
    public String getSelectString() {
        return getBranchNo();
    }

    @Override
    public Integer getSelectInteger() {
        return null;
    }

    @Override
    public String getSelectContent() {
        return getBranchName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
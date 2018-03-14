package cn.ecar.insurance.dao.bean;

import cn.ecar.insurance.dao.base.BaseBean;

/**
 * @author ding
 * @date 2018/1/25
 */

public class BankBind extends BaseBean {

    /**
     * bankCardNo : 6226021001010501
     * bankId : 3
     * bindId : 1
     * branchName : 外滩支行
     * branchNo : 20008812
     * certificateCode : 413026198709094848
     * customerId : 4
     * name : 曹强
     * payType : 1
     * status : 1
     * type : 1
     */

    private String bankCardNo;
    private int bankId;
    private int bindId;
    private String branchName;
    private String branchNo;
    private String certificateCode;
    private int customerId;
    private String name;
    private String payType;
    private String status;
    private String type;

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getBindId() {
        return bindId;
    }

    public void setBindId(int bindId) {
        this.bindId = bindId;
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

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ding
 * @date 2018/2/28
 */

public class OrderBean extends BaseBean implements Parcelable {

    /**
     * areaCode : 1
     * brandModel : 名爵CSA7153ACS轿车
     * carNumber : 苏AS37S1
     * certCode : 320114198411191812
     * companyId : 1
     * createTime : 1519811786740
     * customerId : 5
     * engineNumber : B2GD3040576
     * insuranceName : 太平洋车险
     * name : 徐文杰
     * orderNo : IO00000037
     * phone : 13888881818
     * registerDate : 1367078400000
     * status : 1
     * vehicleCode : LSJZ14E65DS034002
     */
    private String areaCode;
    private String brandModel;
    private String carNumber;
    private String certCode;
    private int companyId;
    private long createTime;
    private int customerId;
    private String engineNumber;
    private String insuranceName;
    private String name;
    private String orderNo;
    private String phone;
    private long registerDate;
    private String status;
    private String vehicleCode;
    /**
     * backAmount : 1680.0
     * forceInsurance : 950.0
     * orderId : 69
     * totalAmount : 5600.0
     * totalBusiness : 4500.0
     * totalForcetax : 1100.0
     * vehicleTax : 150.0
     */
    private double backAmount;
    private double forceInsurance;
    private int orderId;
    private double totalAmount;
    private double totalBusiness;
    private double totalForcetax;
    private double vehicleTax;
    /**
     * 是否正在请求价格。次字段仅用于getInsurancePriceByOrderNo
     */
    private boolean isAmountRequest;

    public OrderBean() {
    }

    protected OrderBean(Parcel in) {
        areaCode = in.readString();
        brandModel = in.readString();
        carNumber = in.readString();
        certCode = in.readString();
        companyId = in.readInt();
        createTime = in.readLong();
        customerId = in.readInt();
        engineNumber = in.readString();
        insuranceName = in.readString();
        name = in.readString();
        orderNo = in.readString();
        phone = in.readString();
        registerDate = in.readLong();
        status = in.readString();
        vehicleCode = in.readString();
        backAmount = in.readDouble();
        forceInsurance = in.readDouble();
        orderId = in.readInt();
        totalAmount = in.readDouble();
        totalBusiness = in.readDouble();
        totalForcetax = in.readDouble();
        vehicleTax = in.readDouble();
        isAmountRequest = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(areaCode);
        dest.writeString(brandModel);
        dest.writeString(carNumber);
        dest.writeString(certCode);
        dest.writeInt(companyId);
        dest.writeLong(createTime);
        dest.writeInt(customerId);
        dest.writeString(engineNumber);
        dest.writeString(insuranceName);
        dest.writeString(name);
        dest.writeString(orderNo);
        dest.writeString(phone);
        dest.writeLong(registerDate);
        dest.writeString(status);
        dest.writeString(vehicleCode);
        dest.writeDouble(backAmount);
        dest.writeDouble(forceInsurance);
        dest.writeInt(orderId);
        dest.writeDouble(totalAmount);
        dest.writeDouble(totalBusiness);
        dest.writeDouble(totalForcetax);
        dest.writeDouble(vehicleTax);
        dest.writeByte((byte) (isAmountRequest ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
        @Override
        public OrderBean createFromParcel(Parcel in) {
            return new OrderBean(in);
        }

        @Override
        public OrderBean[] newArray(int size) {
            return new OrderBean[size];
        }
    };

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(long registerDate) {
        this.registerDate = registerDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public double getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(double backAmount) {
        this.backAmount = backAmount;
    }

    public double getForceInsurance() {
        return forceInsurance;
    }

    public void setForceInsurance(double forceInsurance) {
        this.forceInsurance = forceInsurance;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(double totalBusiness) {
        this.totalBusiness = totalBusiness;
    }

    public double getTotalForcetax() {
        return totalForcetax;
    }

    public void setTotalForcetax(double totalForcetax) {
        this.totalForcetax = totalForcetax;
    }

    public double getVehicleTax() {
        return vehicleTax;
    }

    public void setVehicleTax(double vehicleTax) {
        this.vehicleTax = vehicleTax;
    }

    public boolean isAmountRequest() {
        return isAmountRequest;
    }

    public void setAmountRequest(boolean amountRequest) {
        isAmountRequest = amountRequest;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof OrderBean) {
            OrderBean o = (OrderBean) obj;
            return this.getOrderNo().equals(o.getOrderNo());
        }
        return false;
    }
}

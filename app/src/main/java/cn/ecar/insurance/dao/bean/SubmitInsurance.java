package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ding
 * @date 2018/2/28
 */

public class SubmitInsurance extends BaseBean implements Parcelable {

    /**
     Name=%E5%BE%90%E6%96%87%E6%9D%B0
     &HolderIdCard=320114198411191812
     &Phone=13888881818
     &RegisterDate=2013-04-28
     &CityCode=1
     &LicenseNo=%E8%8B%8FAS37S1
     &EngineNo=B2GD3040576
     &CarVin=LSJZ14E65DS034002
     &MoldName=%E5%90%8D%E7%88%B5CSA7153ACS%E8%BD%BF%E8%BD%A6
     &ForceTax=1
     &BoLi=0
     &SheShui=0
     &HuaHen=0
     &SiJi=0
     &ChengKe=0
     &CheSun=1
     &DaoQiang=1
     &SanZhe=300000
     &ZiRan=0
     &timestamp=1519713403343
     &version=1.0
     &appId=ecar
     &sign=B7643201A1F6179555E258B66E61D386
     */


    /**
     * 名字
     */
    private String Name;
    private String HolderIdCard;
    private String Phone;
    private String RegisterDate;
    private String CityCode;
    private String LicenseNo;
    private String EngineNo;
    private String CarVin;
    private String MoldName;
    private String ForceTax;
    private String BoLi;
    private String SheShui;
    private String HuaHen;
    private String SiJi;
    private String ChengKe;
    private String CheSun;
    private String DaoQiang;
    private String SanZhe;
    private String ZiRan;
    private String timestamp;
    private String version;
    private String appId;

    public SubmitInsurance() {
    }

    protected SubmitInsurance(Parcel in) {
        Name = in.readString();
        HolderIdCard = in.readString();
        Phone = in.readString();
        RegisterDate = in.readString();
        CityCode = in.readString();
        LicenseNo = in.readString();
        EngineNo = in.readString();
        CarVin = in.readString();
        MoldName = in.readString();
        ForceTax = in.readString();
        BoLi = in.readString();
        SheShui = in.readString();
        HuaHen = in.readString();
        SiJi = in.readString();
        ChengKe = in.readString();
        CheSun = in.readString();
        DaoQiang = in.readString();
        SanZhe = in.readString();
        ZiRan = in.readString();
        timestamp = in.readString();
        version = in.readString();
        appId = in.readString();
    }

    public static final Creator<SubmitInsurance> CREATOR = new Creator<SubmitInsurance>() {
        @Override
        public SubmitInsurance createFromParcel(Parcel in) {
            return new SubmitInsurance(in);
        }

        @Override
        public SubmitInsurance[] newArray(int size) {
            return new SubmitInsurance[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHolderIdCard() {
        return HolderIdCard;
    }

    public void setHolderIdCard(String holderIdCard) {
        HolderIdCard = holderIdCard;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getLicenseNo() {
        return LicenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        LicenseNo = licenseNo;
    }

    public String getEngineNo() {
        return EngineNo;
    }

    public void setEngineNo(String engineNo) {
        EngineNo = engineNo;
    }

    public String getCarVin() {
        return CarVin;
    }

    public void setCarVin(String carVin) {
        CarVin = carVin;
    }

    public String getMoldName() {
        return MoldName;
    }

    public void setMoldName(String moldName) {
        MoldName = moldName;
    }

    public String getForceTax() {
        return ForceTax;
    }

    public void setForceTax(String forceTax) {
        ForceTax = forceTax;
    }

    public String getBoLi() {
        return BoLi;
    }

    public void setBoLi(String boLi) {
        BoLi = boLi;
    }

    public String getSheShui() {
        return SheShui;
    }

    public void setSheShui(String sheShui) {
        SheShui = sheShui;
    }

    public String getHuaHen() {
        return HuaHen;
    }

    public void setHuaHen(String huaHen) {
        HuaHen = huaHen;
    }

    public String getSiJi() {
        return SiJi;
    }

    public void setSiJi(String siJi) {
        SiJi = siJi;
    }

    public String getChengKe() {
        return ChengKe;
    }

    public void setChengKe(String chengKe) {
        ChengKe = chengKe;
    }

    public String getCheSun() {
        return CheSun;
    }

    public void setCheSun(String cheSun) {
        CheSun = cheSun;
    }

    public String getDaoQiang() {
        return DaoQiang;
    }

    public void setDaoQiang(String daoQiang) {
        DaoQiang = daoQiang;
    }

    public String getSanZhe() {
        return SanZhe;
    }

    public void setSanZhe(String sanZhe) {
        SanZhe = sanZhe;
    }

    public String getZiRan() {
        return ZiRan;
    }

    public void setZiRan(String ziRan) {
        ZiRan = ziRan;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(HolderIdCard);
        dest.writeString(Phone);
        dest.writeString(RegisterDate);
        dest.writeString(CityCode);
        dest.writeString(LicenseNo);
        dest.writeString(EngineNo);
        dest.writeString(CarVin);
        dest.writeString(MoldName);
        dest.writeString(ForceTax);
        dest.writeString(BoLi);
        dest.writeString(SheShui);
        dest.writeString(HuaHen);
        dest.writeString(SiJi);
        dest.writeString(ChengKe);
        dest.writeString(CheSun);
        dest.writeString(DaoQiang);
        dest.writeString(SanZhe);
        dest.writeString(ZiRan);
        dest.writeString(timestamp);
        dest.writeString(version);
        dest.writeString(appId);
    }
}

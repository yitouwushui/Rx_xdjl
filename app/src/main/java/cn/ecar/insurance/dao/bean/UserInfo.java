package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.ecar.insurance.dao.base.BaseBean;

/**
 * @author ding
 * @date 2018/2/1
 */

public class UserInfo extends BaseBean implements Parcelable {

    private volatile static UserInfo instance;

    private UserInfo() {
    }

    public static UserInfo getInstance() {
        if (instance == null) {
            synchronized (UserInfo.class) {
                if (instance == null) {
                    instance = new UserInfo();
                }
            }
        }
        return instance;
    }

    public static void setInstance(UserInfo instance) {
        UserInfo.instance = instance;
    }

    /**
     * businessexpiredate : 1504972800000
     * carusedtype : 1
     * carvin : LSJZ14E65DS034002
     * citycode : 1
     * clausetype : 2
     * credentislasnum : 320114198411191812
     * engineno : B2GD3040576
     * fueltype : 1
     * holderidcard : 320114198411191812
     * holderidtype : 1
     * holdermobile :
     * idtype : 1
     * insuredidcard : 320114198411191812
     * insuredidtype : 1
     * insuredmobile :
     * insuredname : 徐文杰
     * ispublic : 2
     * licensecolor : 1
     * licenseno : 苏AS37S1
     * licenseowner : 徐文杰
     * modlename : 名爵CSA7153ACS轿车
     * nextbusinessstartdate : 1516809600000
     * postedname : 徐文杰
     * prooftype : 1
     * purchaseprice : 79700.0
     * queryDate : 1517328000000
     * ratefactor1 : 1.0
     * ratefactor2 : 1.0
     * ratefactor3 : 1.0
     * ratefactor4 : 1.0
     * registerdate : 1367078400000
     * runregion : 1
     * seatcount : 5
     * userinfoId : 12
     */
    private long businessexpiredate;
    private String carusedtype;
    private String carvin;
    private String citycode;
    private String cityName;
    private String clausetype;
    private String credentislasnum;
    private String engineno;
    private String fueltype;
    private String holderidcard;
    private String holderidtype;
    private String holdermobile;
    private String idtype;
    private String insuredidcard;
    private String insuredidtype;
    private String insuredmobile;
    private String insuredname;
    private String ispublic;
    private String licensecolor;
    private String licenseno;
    private String licenseowner;
    private String modlename;
    private long nextbusinessstartdate;
    private String postedname;
    private String prooftype;
    private double purchaseprice;
    private long queryDate;
    private double ratefactor1;
    private double ratefactor2;
    private double ratefactor3;
    private double ratefactor4;
    private long registerdate;
    private String runregion;
    private int seatcount;
    private int userinfoId;

    protected UserInfo(Parcel in) {
        businessexpiredate = in.readLong();
        carusedtype = in.readString();
        carvin = in.readString();
        citycode = in.readString();
        cityName = in.readString();
        clausetype = in.readString();
        credentislasnum = in.readString();
        engineno = in.readString();
        fueltype = in.readString();
        holderidcard = in.readString();
        holderidtype = in.readString();
        holdermobile = in.readString();
        idtype = in.readString();
        insuredidcard = in.readString();
        insuredidtype = in.readString();
        insuredmobile = in.readString();
        insuredname = in.readString();
        ispublic = in.readString();
        licensecolor = in.readString();
        licenseno = in.readString();
        licenseowner = in.readString();
        modlename = in.readString();
        nextbusinessstartdate = in.readLong();
        postedname = in.readString();
        prooftype = in.readString();
        purchaseprice = in.readDouble();
        queryDate = in.readLong();
        ratefactor1 = in.readDouble();
        ratefactor2 = in.readDouble();
        ratefactor3 = in.readDouble();
        ratefactor4 = in.readDouble();
        registerdate = in.readLong();
        runregion = in.readString();
        seatcount = in.readInt();
        userinfoId = in.readInt();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public long getBusinessexpiredate() {
        return businessexpiredate;
    }

    public void setBusinessexpiredate(long businessexpiredate) {
        this.businessexpiredate = businessexpiredate;
    }

    public String getCarusedtype() {
        return carusedtype;
    }

    public void setCarusedtype(String carusedtype) {
        this.carusedtype = carusedtype;
    }

    public String getCarvin() {
        return carvin;
    }

    public void setCarvin(String carvin) {
        this.carvin = carvin;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getClausetype() {
        return clausetype;
    }

    public void setClausetype(String clausetype) {
        this.clausetype = clausetype;
    }

    public String getCredentislasnum() {
        return credentislasnum;
    }

    public void setCredentislasnum(String credentislasnum) {
        this.credentislasnum = credentislasnum;
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getHolderidcard() {
        return holderidcard;
    }

    public void setHolderidcard(String holderidcard) {
        this.holderidcard = holderidcard;
    }

    public String getHolderidtype() {
        return holderidtype;
    }

    public void setHolderidtype(String holderidtype) {
        this.holderidtype = holderidtype;
    }

    public String getHoldermobile() {
        return holdermobile;
    }

    public void setHoldermobile(String holdermobile) {
        this.holdermobile = holdermobile;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getInsuredidcard() {
        return insuredidcard;
    }

    public void setInsuredidcard(String insuredidcard) {
        this.insuredidcard = insuredidcard;
    }

    public String getInsuredidtype() {
        return insuredidtype;
    }

    public void setInsuredidtype(String insuredidtype) {
        this.insuredidtype = insuredidtype;
    }

    public String getInsuredmobile() {
        return insuredmobile;
    }

    public void setInsuredmobile(String insuredmobile) {
        this.insuredmobile = insuredmobile;
    }

    public String getInsuredname() {
        return insuredname;
    }

    public void setInsuredname(String insuredname) {
        this.insuredname = insuredname;
    }

    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public String getLicensecolor() {
        return licensecolor;
    }

    public void setLicensecolor(String licensecolor) {
        this.licensecolor = licensecolor;
    }

    public String getLicenseno() {
        return licenseno;
    }

    public void setLicenseno(String licenseno) {
        this.licenseno = licenseno;
    }

    public String getLicenseowner() {
        return licenseowner;
    }

    public void setLicenseowner(String licenseowner) {
        this.licenseowner = licenseowner;
    }

    public String getModlename() {
        return modlename;
    }

    public void setModlename(String modlename) {
        this.modlename = modlename;
    }

    public long getNextbusinessstartdate() {
        return nextbusinessstartdate;
    }

    public void setNextbusinessstartdate(long nextbusinessstartdate) {
        this.nextbusinessstartdate = nextbusinessstartdate;
    }

    public String getPostedname() {
        return postedname;
    }

    public void setPostedname(String postedname) {
        this.postedname = postedname;
    }

    public String getProoftype() {
        return prooftype;
    }

    public void setProoftype(String prooftype) {
        this.prooftype = prooftype;
    }

    public double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public long getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(long queryDate) {
        this.queryDate = queryDate;
    }

    public double getRatefactor1() {
        return ratefactor1;
    }

    public void setRatefactor1(double ratefactor1) {
        this.ratefactor1 = ratefactor1;
    }

    public double getRatefactor2() {
        return ratefactor2;
    }

    public void setRatefactor2(double ratefactor2) {
        this.ratefactor2 = ratefactor2;
    }

    public double getRatefactor3() {
        return ratefactor3;
    }

    public void setRatefactor3(double ratefactor3) {
        this.ratefactor3 = ratefactor3;
    }

    public double getRatefactor4() {
        return ratefactor4;
    }

    public void setRatefactor4(double ratefactor4) {
        this.ratefactor4 = ratefactor4;
    }

    public long getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(long registerdate) {
        this.registerdate = registerdate;
    }

    public String getRunregion() {
        return runregion;
    }

    public void setRunregion(String runregion) {
        this.runregion = runregion;
    }

    public int getSeatcount() {
        return seatcount;
    }

    public void setSeatcount(int seatcount) {
        this.seatcount = seatcount;
    }

    public int getUserinfoId() {
        return userinfoId;
    }

    public void setUserinfoId(int userinfoId) {
        this.userinfoId = userinfoId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(businessexpiredate);
        dest.writeString(carusedtype);
        dest.writeString(carvin);
        dest.writeString(citycode);
        dest.writeString(cityName);
        dest.writeString(clausetype);
        dest.writeString(credentislasnum);
        dest.writeString(engineno);
        dest.writeString(fueltype);
        dest.writeString(holderidcard);
        dest.writeString(holderidtype);
        dest.writeString(holdermobile);
        dest.writeString(idtype);
        dest.writeString(insuredidcard);
        dest.writeString(insuredidtype);
        dest.writeString(insuredmobile);
        dest.writeString(insuredname);
        dest.writeString(ispublic);
        dest.writeString(licensecolor);
        dest.writeString(licenseno);
        dest.writeString(licenseowner);
        dest.writeString(modlename);
        dest.writeLong(nextbusinessstartdate);
        dest.writeString(postedname);
        dest.writeString(prooftype);
        dest.writeDouble(purchaseprice);
        dest.writeLong(queryDate);
        dest.writeDouble(ratefactor1);
        dest.writeDouble(ratefactor2);
        dest.writeDouble(ratefactor3);
        dest.writeDouble(ratefactor4);
        dest.writeLong(registerdate);
        dest.writeString(runregion);
        dest.writeInt(seatcount);
        dest.writeInt(userinfoId);
    }
}

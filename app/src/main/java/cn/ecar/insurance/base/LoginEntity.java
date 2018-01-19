package cn.ecar.insurance.base;

/**
 * Created by lq on 2017/11/27.
 */

public class LoginEntity extends BaseEntity {
    private String token;
    private String photo;
    private String name;
    private String renzheng;
    private String company;
    private String pingjia;
    private String kefuName;
    private String kefuQQ;
    private String kefuMobile;
    private String city;
    private String businessCity;//覆盖城市
    private String CPS;
    private String userName;

    @Override
    public String toString() {
        return "LoginEntity{" +
                "token='" + token + '\'' +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", renzheng='" + renzheng + '\'' +
                ", company='" + company + '\'' +
                ", pingjia='" + pingjia + '\'' +
                ", kefuName='" + kefuName + '\'' +
                ", kefuQQ='" + kefuQQ + '\'' +
                ", kefuMobile='" + kefuMobile + '\'' +
                ", city='" + city + '\'' +
                ", businessCity='" + businessCity + '\'' +
                ", CPS='" + CPS + '\'' +
                ", userName='" + userName + '\'' +
                ", result='" + getResult() + '\'' +
                ", msg='" + getMsg() + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRenzheng() {
        return renzheng;
    }

    public void setRenzheng(String renzheng) {
        this.renzheng = renzheng;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPingjia() {
        return pingjia;
    }

    public void setPingjia(String pingjia) {
        this.pingjia = pingjia;
    }

    public String getKefuName() {
        return kefuName;
    }

    public void setKefuName(String kefuName) {
        this.kefuName = kefuName;
    }

    public String getKefuQQ() {
        return kefuQQ;
    }

    public void setKefuQQ(String kefuQQ) {
        this.kefuQQ = kefuQQ;
    }

    public String getKefuMobile() {
        return kefuMobile;
    }

    public void setKefuMobile(String kefuMobile) {
        this.kefuMobile = kefuMobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getCPS() {
        return CPS;
    }

    public void setCPS(String CPS) {
        this.CPS = CPS;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

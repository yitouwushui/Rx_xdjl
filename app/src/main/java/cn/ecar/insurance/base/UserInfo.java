package cn.ecar.insurance.base;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by yx on 2016/8/1.
 * 用户信息
 */

public class UserInfo extends DataSupport {

    @Column(defaultValue = "unknown")
    private String photo;
    @Column(unique = true)
    private String name;
    @Column(defaultValue = "unknown")
    private String renzheng;
    @Column(defaultValue = "unknown")
    private String company;
    @Column(defaultValue = "unknown")
    private String pingjia;
    @Column(defaultValue = "unknown")
    private String kefuName;
    @Column(defaultValue = "unknown")
    private String kefuQQ;
    @Column(defaultValue = "unknown")
    private String kefuMobile;
    @Column(defaultValue = "unknown")
    private String city;
    @Column(defaultValue = "unknown")
    private String businessCity;
    @Column(defaultValue = "unknown")//覆盖城市
    private String CPS;
    @Column(defaultValue = "unknown")
    private String userName;

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



package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.ecar.insurance.dao.base.BaseBean;

/**
 *
 * @author ding
 * @date 2018/2/1
 */
public class SaveQuote extends BaseBean implements Parcelable {
    /**
     * Shebeis : []
     * boli : 0
     * bujimianchengke : 0.0
     * bujimianchesun : 1.0
     * bujimiandaoqiang : 1.0
     * bujimianhuahen : 0.0
     * bujimianjingshensunshi : 0.0
     * bujimiansanzhe : 1.0
     * bujimiansheshui : 0.0
     * bujimiansiji : 0.0
     * bujimianziran : 0.0
     * chengke : 0.0
     * chesun : 60572.0
     * daoqiang : 60572.0
     * hcjingshensunshi : 0.0
     * hcsanfangteyue : 0.0
     * huahen : 0.0
     * licenseno : 苏AS37S1
     * sanzhe : 300000.0
     * savequoteId : 10
     * shebeis : []
     * sheshui : 0.0
     * siji : 0.0
     * source : 4.0
     * ziran : 0.0
     */
    private String boli;
    private double bujimianchengke;
    private double bujimianchesun;
    private double bujimiandaoqiang;
    private double bujimianhuahen;
    private double bujimianjingshensunshi;
    private double bujimiansanzhe;
    private double bujimiansheshui;
    private double bujimiansiji;
    private double bujimianziran;
    private double chengke;
    private double chesun;
    private double daoqiang;
    private double hcjingshensunshi;
    private double hcsanfangteyue;
    private double huahen;
    private String licenseno;
    private double sanzhe;
    private int savequoteId;
    private double sheshui;
    private double siji;
    private double source;
    private double ziran;


    protected SaveQuote(Parcel in) {
        boli = in.readString();
        bujimianchengke = in.readDouble();
        bujimianchesun = in.readDouble();
        bujimiandaoqiang = in.readDouble();
        bujimianhuahen = in.readDouble();
        bujimianjingshensunshi = in.readDouble();
        bujimiansanzhe = in.readDouble();
        bujimiansheshui = in.readDouble();
        bujimiansiji = in.readDouble();
        bujimianziran = in.readDouble();
        chengke = in.readDouble();
        chesun = in.readDouble();
        daoqiang = in.readDouble();
        hcjingshensunshi = in.readDouble();
        hcsanfangteyue = in.readDouble();
        huahen = in.readDouble();
        licenseno = in.readString();
        sanzhe = in.readDouble();
        savequoteId = in.readInt();
        sheshui = in.readDouble();
        siji = in.readDouble();
        source = in.readDouble();
        ziran = in.readDouble();
    }

    public static final Creator<SaveQuote> CREATOR = new Creator<SaveQuote>() {
        @Override
        public SaveQuote createFromParcel(Parcel in) {
            return new SaveQuote(in);
        }

        @Override
        public SaveQuote[] newArray(int size) {
            return new SaveQuote[size];
        }
    };

    public String getBoli() {
        return boli;
    }

    public void setBoli(String boli) {
        this.boli = boli;
    }

    public double getBujimianchengke() {
        return bujimianchengke;
    }

    public void setBujimianchengke(double bujimianchengke) {
        this.bujimianchengke = bujimianchengke;
    }

    public double getBujimianchesun() {
        return bujimianchesun;
    }

    public void setBujimianchesun(double bujimianchesun) {
        this.bujimianchesun = bujimianchesun;
    }

    public double getBujimiandaoqiang() {
        return bujimiandaoqiang;
    }

    public void setBujimiandaoqiang(double bujimiandaoqiang) {
        this.bujimiandaoqiang = bujimiandaoqiang;
    }

    public double getBujimianhuahen() {
        return bujimianhuahen;
    }

    public void setBujimianhuahen(double bujimianhuahen) {
        this.bujimianhuahen = bujimianhuahen;
    }

    public double getBujimianjingshensunshi() {
        return bujimianjingshensunshi;
    }

    public void setBujimianjingshensunshi(double bujimianjingshensunshi) {
        this.bujimianjingshensunshi = bujimianjingshensunshi;
    }

    public double getBujimiansanzhe() {
        return bujimiansanzhe;
    }

    public void setBujimiansanzhe(double bujimiansanzhe) {
        this.bujimiansanzhe = bujimiansanzhe;
    }

    public double getBujimiansheshui() {
        return bujimiansheshui;
    }

    public void setBujimiansheshui(double bujimiansheshui) {
        this.bujimiansheshui = bujimiansheshui;
    }

    public double getBujimiansiji() {
        return bujimiansiji;
    }

    public void setBujimiansiji(double bujimiansiji) {
        this.bujimiansiji = bujimiansiji;
    }

    public double getBujimianziran() {
        return bujimianziran;
    }

    public void setBujimianziran(double bujimianziran) {
        this.bujimianziran = bujimianziran;
    }

    public double getChengke() {
        return chengke;
    }

    public void setChengke(double chengke) {
        this.chengke = chengke;
    }

    public double getChesun() {
        return chesun;
    }

    public void setChesun(double chesun) {
        this.chesun = chesun;
    }

    public double getDaoqiang() {
        return daoqiang;
    }

    public void setDaoqiang(double daoqiang) {
        this.daoqiang = daoqiang;
    }

    public double getHcjingshensunshi() {
        return hcjingshensunshi;
    }

    public void setHcjingshensunshi(double hcjingshensunshi) {
        this.hcjingshensunshi = hcjingshensunshi;
    }

    public double getHcsanfangteyue() {
        return hcsanfangteyue;
    }

    public void setHcsanfangteyue(double hcsanfangteyue) {
        this.hcsanfangteyue = hcsanfangteyue;
    }

    public double getHuahen() {
        return huahen;
    }

    public void setHuahen(double huahen) {
        this.huahen = huahen;
    }

    public String getLicenseno() {
        return licenseno;
    }

    public void setLicenseno(String licenseno) {
        this.licenseno = licenseno;
    }

    public double getSanzhe() {
        return sanzhe;
    }

    public void setSanzhe(double sanzhe) {
        this.sanzhe = sanzhe;
    }

    public int getSavequoteId() {
        return savequoteId;
    }

    public void setSavequoteId(int savequoteId) {
        this.savequoteId = savequoteId;
    }

    public double getSheshui() {
        return sheshui;
    }

    public void setSheshui(double sheshui) {
        this.sheshui = sheshui;
    }

    public double getSiji() {
        return siji;
    }

    public void setSiji(double siji) {
        this.siji = siji;
    }

    public double getSource() {
        return source;
    }

    public void setSource(double source) {
        this.source = source;
    }

    public double getZiran() {
        return ziran;
    }

    public void setZiran(double ziran) {
        this.ziran = ziran;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(boli);
        dest.writeDouble(bujimianchengke);
        dest.writeDouble(bujimianchesun);
        dest.writeDouble(bujimiandaoqiang);
        dest.writeDouble(bujimianhuahen);
        dest.writeDouble(bujimianjingshensunshi);
        dest.writeDouble(bujimiansanzhe);
        dest.writeDouble(bujimiansheshui);
        dest.writeDouble(bujimiansiji);
        dest.writeDouble(bujimianziran);
        dest.writeDouble(chengke);
        dest.writeDouble(chesun);
        dest.writeDouble(daoqiang);
        dest.writeDouble(hcjingshensunshi);
        dest.writeDouble(hcsanfangteyue);
        dest.writeDouble(huahen);
        dest.writeString(licenseno);
        dest.writeDouble(sanzhe);
        dest.writeInt(savequoteId);
        dest.writeDouble(sheshui);
        dest.writeDouble(siji);
        dest.writeDouble(source);
        dest.writeDouble(ziran);
    }
}

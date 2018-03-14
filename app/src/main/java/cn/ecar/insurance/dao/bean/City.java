package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import cn.ecar.insurance.dao.base.BaseBean;
import cn.ecar.insurance.mvvm.view.act.main.ChoiceInterFace;

/**
 * @author ding
 * @date 2018/1/29
 */

public class City extends BaseBean implements Serializable, Parcelable, ChoiceInterFace {

    /**
     * code : 10001000
     * id : 2150
     * name : 北京市
     * icodeId : 2
     * sort : 1
     */

    private String code;
    private int id;
    private String name;
    private int icodeId;
    private int sort;

    public City() {
    }

    protected City(Parcel in) {
        code = in.readString();
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeInt(id);
        dest.writeString(name);
    }

    @Override
    public String getSelectString() {
        return getCode();
    }

    @Override
    public Integer getSelectInteger() {
        return getId();
    }

    @Override
    public String getSelectContent() {
        return getName();
    }

    @Override
    public String toString() {
        return "City{" +
                "code='" + code + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getIcodeId() {
        return icodeId;
    }

    public void setIcodeId(int icodeId) {
        this.icodeId = icodeId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}

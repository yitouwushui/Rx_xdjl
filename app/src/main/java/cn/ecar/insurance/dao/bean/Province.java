package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import cn.ecar.insurance.mvvm.view.act.main.ChoiceInterFace;

/**
 *
 * @author ding
 * @date 2018/1/26
 */

public class Province implements Serializable, ChoiceInterFace, Parcelable {

    /**
     * code : 1000
     * id : 31
     * name : 北京市
     */
    private String code;
    private int id;
    private String name;

    public Province() {
    }

    protected Province(Parcel in) {
        code = in.readString();
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<Province> CREATOR = new Creator<Province>() {
        @Override
        public Province createFromParcel(Parcel in) {
            return new Province(in);
        }

        @Override
        public Province[] newArray(int size) {
            return new Province[size];
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
}

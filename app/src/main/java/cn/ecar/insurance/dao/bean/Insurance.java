package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ding
 * @date 2018/2/7
 */

public class Insurance extends BaseBean implements Parcelable {

    private int id;

    public Insurance() {
    }

    protected Insurance(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<Insurance> CREATOR = new Creator<Insurance>() {
        @Override
        public Insurance createFromParcel(Parcel in) {
            return new Insurance(in);
        }

        @Override
        public Insurance[] newArray(int size) {
            return new Insurance[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }
}

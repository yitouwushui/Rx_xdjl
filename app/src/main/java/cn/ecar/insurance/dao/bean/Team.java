package cn.ecar.insurance.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ding
 * @date 2018/2/7
 */

public class Team extends BaseBean implements Parcelable {

    private int id;

    public Team() {
    }

    protected Team(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
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

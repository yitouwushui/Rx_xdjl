package cn.ecar.insurance.dao.base;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 *
 * @author ding
 * @date 2018/3/14
 */

public class Token implements Serializable,Parcelable {

    private String sessionId;
    private long expirationTime;

    public Token() {
    }

    public Token(String sessionId, long expirationTime) {
        this.sessionId = sessionId;
        this.expirationTime = expirationTime;
    }

    protected Token(Parcel in) {
        sessionId = in.readString();
        expirationTime = in.readLong();
    }

    public static final Creator<Token> CREATOR = new Creator<Token>() {
        @Override
        public Token createFromParcel(Parcel in) {
            return new Token(in);
        }

        @Override
        public Token[] newArray(int size) {
            return new Token[size];
        }
    };

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sessionId);
        dest.writeLong(expirationTime);
    }
}

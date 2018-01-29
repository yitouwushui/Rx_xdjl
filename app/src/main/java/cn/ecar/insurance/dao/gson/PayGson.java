package cn.ecar.insurance.dao.gson;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;

import cn.ecar.insurance.dao.base.BaseGson;

/**
 * @author ding
 * @date 2018/1/29
 */
public class PayGson extends BaseGson implements Parcelable,Serializable {

    /**
     * bankUrl : http://opsweb.koolyun.cn/mobilepay/index.do
     * data : {"inputCharset":"1","pickupUrl":"http","receiveUrl":"http","version":"v1.0","language":"1","signType":"1","merchantId":"100020091219001","payerName":"C18011800018","orderNo":"2018012900000027","orderAmount":"59900","orderCurrency":"156","orderDatetime":"20180129150658","productName":"ecar车险","payType":"0","issuerId":"","signMsg":"66700F15CEB8284C7E4F19DB8BA21F6D"}
     */
    private String bankUrl;
    private HashMap<String, String> data;

    public PayGson() {
    }

    protected PayGson(Parcel in) {
        bankUrl = in.readString();
    }

    public static final Creator<PayGson> CREATOR = new Creator<PayGson>() {
        @Override
        public PayGson createFromParcel(Parcel in) {
            return new PayGson(in);
        }

        @Override
        public PayGson[] newArray(int size) {
            return new PayGson[size];
        }
    };

    public String getBankUrl() {
        return bankUrl;
    }

    public void setBankUrl(String bankUrl) {
        this.bankUrl = bankUrl;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bankUrl);
    }
}

package cn.ecar.insurance.net;


import android.support.v4.util.ArrayMap;

import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;


import cn.ecar.insurance.base.AesEntity;
import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.utils.encrypt.AESOperator;
import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.Request;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * @author yx
 * @date 2016/10/17
 * retrofit加解密与网络请求类
 */

public class RetrofitUtils {

    private static volatile RetrofitUtils instance;

    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    private NetServer getNetServer() {
        return XdAppContext.app().getNetServer();
    }


    /**
     * obj转json字符串
     * @param obj
     * @return
     */
    public String paramsToJsonString(Object...obj) {
        if (obj.length % 2 != 0) {
            throw new IllegalStateException("参数个数必须是2的倍数!!!");
        }

        StringBuffer sb = new StringBuffer();
        int i = 0;
        sb.append("{");
        for (Object o : obj) {
            i++;
            if (o instanceof Boolean) {
                sb.append(o);
            } else {
                sb.append("\"");
                sb.append(o);
                sb.append("\"");
            }
            if (i % 2 == 1) {
                sb.append(":");
            } else {
                if (i != obj.length) {
                    sb.append(",");
                }
            }
        }
        sb.append("}");
        return sb.toString();

    }

    /**
     * aes加密
     *
     * @param str
     * @return
     */
    public String toAesJsonString(String... str) {
        if (str.length % 2 != 0) {
            throw new IllegalStateException("加密参数个数必须是2的倍数!!!");
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        sb.append("{");
        for (String s : str) {
            i++;
            sb.append("\"");
            sb.append(s);
            sb.append("\"");
            if (i % 2 == 1) {
                sb.append(":");
            } else {
                if (i != str.length) {
                    sb.append(",");
                }
            }
        }
        sb.append("}");
        String jsonString = sb.toString();
        Logger.w("jsonString = " + jsonString);
        String encryptValue = null;
        try {
            String encryptString = AESOperator.getInstance().encrypt(jsonString);

            if (encryptString.contains("sql")) {
                encryptValue = encryptString.replace("sql", "******");
            } else {
                encryptValue = encryptString;
                Logger.wtf("encryptValue = " + encryptString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptValue;
    }

    /**
     * aes解密
     *
     * @param encpytString
     * @return
     */
    public String decpytJsonString(String encpytString) {
        String decryptString = null;
        try {
            decryptString = AESOperator.getInstance().decrypt(encpytString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptString;
    }

    /**
     * 获取参数map集合
     *
     * @param str
     * @return
     */
    public Map<String, String> getParamsMap(String... str) {
        if (str.length < 2 && str.length % 2 != 0) {
            throw new IllegalStateException("参数个数必须是2的倍数!!!");
        }
        ArrayMap<String, String> map = new ArrayMap<>();
        for (int i = 0; i < str.length; i++) {
            if (i % 2 == 1) {
                map.put(str[i - 1], str[i]);
            }
        }
        return map;
    }


    /**
     * 直接输入其他url的网络请求
     *
     * @param baseUrl
     * @param str
     * @param lisenter
     */
    public void getOkHttpUtilsRequest(String baseUrl, String str, OkHttpUtilListener lisenter) {
        OkHttpUtils.get().url(baseUrl + str).build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        lisenter.onRequestStart(request, id);
                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        lisenter.onRequestComplete(id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        lisenter.onRequestError(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        lisenter.onRequestSuccess(response, id);
                    }
                });
    }

    public Observable<AesEntity> getNoTokenData(Map params) {
        return getNetServer().getNoTokendata(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AesEntity> getEncryptedData(Map params) {
        return getNetServer().getEncryptedData(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AesEntity> getUploadAesData(String d, MultipartBody.Part part) {
        return getNetServer().getUploadAesData(d,part);
    }
}

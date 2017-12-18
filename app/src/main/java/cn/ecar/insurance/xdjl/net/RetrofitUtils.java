package cn.ecar.insurance.xdjl.net;


import android.support.v4.util.ArrayMap;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;


import cn.ecar.insurance.xdjl.config.XdAppContext;
import okhttp3.Call;
import okhttp3.Request;

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
    public void getOkhttpUtilsRequest(String baseUrl, String str, OkHttpUtilListener lisenter) {
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


}

package cn.ecar.insurance.net;

import android.annotation.SuppressLint;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author yx
 * @date 2016/7/15
 * 网络请求构建
 */
public class NetWorkApi {

    public static final String BASE_URL = "http://192.168.1.196:8282/";//测试地址2
//    public static final String BASE_URL = "http://116.228.18.10:88/";//正式地址

    public static final String updateUrl = "http://norepeatapp.dai88.cn/apk/managerupdate.html";//更新地址

    private static volatile NetWorkApi instance;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;

    public static NetWorkApi getInstance() {
        if (instance == null) {
            synchronized (NetWorkApi.class) {
                if (instance == null) {
                    instance = new NetWorkApi();
                }
            }
        }
        return instance;
    }

    /**
     * 构建okhttp
     */
    private OkHttpClient gradleOkHttp(Context context) {
        if (mOkHttpClient == null) {
            CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(context));
            OkHttpClient.Builder okHttpBuider = new OkHttpClient.Builder()
                    .addInterceptor(new NetLoggerInterceptor(false))
                    .connectTimeout(240, TimeUnit.SECONDS)
                    .readTimeout(240, TimeUnit.SECONDS)
                    .cookieJar(cookieJar);//持久化session
            okHttpBuider.sslSocketFactory(getAllTrustSSl());
            okHttpBuider.hostnameVerifier((hostname, session) -> true);
            mOkHttpClient = okHttpBuider.build();
            OkHttpUtils.initClient(mOkHttpClient);
        }
        return mOkHttpClient;
    }


    //okhttp信任所有证书
    private SSLSocketFactory getAllTrustSSl() {
        SSLSocketFactory sslSocketFactory;
        try {
            // OKHttp信任所有证书
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sslSocketFactory;
    }

    public OkHttpClient getOkHttpClient() {
        return gradleOkHttp(null);
    }

    /**
     * 构建Retrofit
     *
     * @return
     */
    public Retrofit gradleRetrofit(Context context) {
        if (mRetrofit == null) {
            OkHttpClient okHttpClient = gradleOkHttp(context);
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return mRetrofit;
    }


}


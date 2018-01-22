package cn.ecar.insurance.net;

import java.util.Map;

import cn.ecar.insurance.dao.gson.CustomerGson;
import cn.ecar.insurance.dao.base.AesEntity;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @author yx
 * @date 2017/11/9
 * app网络请求接口
 */

public interface NetServer {

    String KAPTCHA = "kaptcha";

    /**
     * 登录
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("login?")
    rx.Observable<CustomerGson> login(@FieldMap Map<String, String> options);

    /**
     * 获取验证码
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("captcha?")
    rx.Observable<Object> getVerifyCode(@FieldMap Map<String, String> options);

    /**
     * 获取验证码
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("register?")
    rx.Observable<Object> register(@FieldMap Map<String, String> options);


    String NO_TOKEN = "noToken.aspx";
    String ENCRYPTED = "encrypted.aspx";

    @FormUrlEncoded
    @POST(NO_TOKEN)
    rx.Observable<AesEntity> getNoTokendata(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(ENCRYPTED)
    rx.Observable<AesEntity> getEncryptedData(@FieldMap Map<String, String> params);

    /**
     * 上传图片
     */
    @Multipart
    @POST(ENCRYPTED)
    rx.Observable<AesEntity> getUploadAesData(@Query("d") String d,
                                              @Part MultipartBody.Part part);


}

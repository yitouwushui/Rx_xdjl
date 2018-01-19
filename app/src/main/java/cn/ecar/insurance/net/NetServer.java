package cn.ecar.insurance.net;

import java.util.Map;

import cn.ecar.insurance.base.AesEntity;
import cn.ecar.insurance.base.BaseEntity;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author yx
 * @date 2017/11/9
 * app网络请求接口
 */

public interface NetServer {

    @FormUrlEncoded
    @POST("ecar-front/checkLogin?")
    Call<BaseEntity> login(@QueryMap Map<String, String> options);

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

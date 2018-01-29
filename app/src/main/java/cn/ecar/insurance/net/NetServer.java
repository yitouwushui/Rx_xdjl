package cn.ecar.insurance.net;

import java.util.Map;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.gson.BankGson;
import cn.ecar.insurance.dao.gson.CityGson;
import cn.ecar.insurance.dao.gson.CustomerGson;
import cn.ecar.insurance.dao.base.AesEntity;
import cn.ecar.insurance.dao.gson.CustomerShowGson;
import cn.ecar.insurance.dao.gson.InformationListGson;
import cn.ecar.insurance.dao.gson.MessageListGson;
import cn.ecar.insurance.dao.gson.PayGson;
import cn.ecar.insurance.dao.gson.ProvinceGson;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

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
     * 登录
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("checkLogin?")
    rx.Observable<CustomerGson> checkLogin(@FieldMap Map<String, String> options);

    /**
     * 获取图片验证码
     *
     * @return
     */
    @GET("kaptcha?")
    @Streaming
    rx.Observable<ResponseBody> getVerifyImage();

    /**
     * 获取验证码
     *
     * @param options
     * @return
     */
    @GET("sendVerifyCodeV2?")
    rx.Observable<BaseGson> getVerifyCode(@QueryMap Map<String, String> options);

    /**
     * 注册
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("register?")
    rx.Observable<BaseGson> register(@FieldMap Map<String, String> options);

    /**
     * 明星会员列表
     *
     * @return
     */
    @GET("customerShowList.do?")
    rx.Observable<CustomerShowGson> getCustomerShowList();

    /**
     * 通知信息列表
     *
     * @return
     */
    @GET("messageList.do?")
    rx.Observable<MessageListGson> getMessageList();

    /**
     * 资讯列表信息
     *
     * @return
     */
    @GET("infoList.do?")
    rx.Observable<InformationListGson> getInformationList();

    /**
     * 个人信息
     *
     * @return
     */
    @GET("getCustomerAllInfo.do?")
    rx.Observable<CustomerGson> getCustomerAllInfo();

    /**
     * 资讯省份
     *
     * @return
     */
    @GET("getProvinceList?")
    rx.Observable<ProvinceGson> getProvinceList();

    /**
     * 获取城市
     *
     * @param provinceCode
     * @return
     */
    @GET("getCityListProvinceCode?")
    rx.Observable<CityGson> getCityListProvinceCode(@Query("provinceCode") String provinceCode);

    /**
     * 获取银行
     *
     * @return
     */
    @GET("getBankList?")
    rx.Observable<BankGson> getBankList();

    /**
     * 获取银行支行
     *
     * @param bankCode
     * @param cityCode
     * @return
     */
    @GET("getBranchBankList.do?")
    rx.Observable<BankGson> getBranchBankList(@Query("bankCode") String bankCode, @Query("cityCode") String cityCode);

    /**
     * 获取银行卡绑定信息
     *
     * @return
     */
    @GET("getBankInfo?")
    rx.Observable<BankGson> getBankInfo();

    /**
     * 获取支付通道
     *
     * @param params
     * @return
     */
//    @FormUrlEncoded
    @GET("submitPay.do?")
    rx.Observable<PayGson> submitPay(@QueryMap Map<String, String> params);

    /**
     * 绑定银行卡
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("bindBank.do?")
    rx.Observable<BaseGson> bindBank(@FieldMap Map<String, String> params);

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

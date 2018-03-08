package cn.ecar.insurance.net;

import java.util.Map;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.base.AesEntity;
import cn.ecar.insurance.dao.gson.*;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    String front = "ecar-front/";
    String upload = "ecar-upload/";

    /**
     * 登录
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST(front + "login?")
    rx.Observable<CustomerGson> login(@FieldMap Map<String, String> options);

    /**
     * 登录
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST(front + "checkLogin?")
    rx.Observable<CustomerGson> checkLogin(@FieldMap Map<String, String> options);

    /**
     * 获取图片验证码
     *
     * @return
     */
    @GET(front + "kaptcha?")
    @Streaming
    rx.Observable<ResponseBody> getVerifyImage();

    /**
     * 获取验证码
     *
     * @param options
     * @return
     */
    @GET(front + "sendVerifyCodeV2?")
    rx.Observable<BaseGson> getVerifyCode(@QueryMap Map<String, String> options);

    /**
     * 注册
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST(front + "register?")
    rx.Observable<BaseGson> register(@FieldMap Map<String, String> options);

    /**
     * 明星会员列表
     *
     * @return
     */
    @GET(front + "customerShowList.do?")
    rx.Observable<CustomerShowGson> getCustomerShowList();

    /**
     * 通知信息列表
     *
     * @return
     */
    @GET(front + "messageList.do?")
    rx.Observable<MessageListGson> getMessageList();

    /**
     * 资讯列表信息
     *
     * @return
     */
    @GET(front + "infoList.do?")
    rx.Observable<InformationListGson> getInformationList();

    /**
     * 个人信息
     *
     * @return
     */
    @GET(front + "getCustomerAllInfo.do?")
    rx.Observable<CustomerGson> getCustomerAllInfo();

    /**
     * 资讯省份
     *
     * @return
     */
    @GET(front + "getProvinceList?")
    rx.Observable<ProvinceGson> getProvinceList();

    /**
     * 获取城市
     *
     * @param provinceCode
     * @return
     */
    @GET(front + "getCityListProvinceCode?")
    rx.Observable<CityGson> getCityListProvinceCode(@Query("provinceCode") String provinceCode);

    /**
     * 获取银行
     *
     * @return
     */
    @GET(front + "getBankList?")
    rx.Observable<BankGson> getBankList();

    /**
     * 获取银行支行
     *
     * @param bankCode
     * @param cityCode
     * @return
     */
    @GET(front + "getBranchBankList.do?")
    rx.Observable<BankGson> getBranchBankList(@Query("bankCode") String bankCode, @Query("cityCode") String cityCode);

    /**
     * 获取银行卡绑定信息
     *
     * @return
     */
    @GET(front + "getBankInfo?")
    rx.Observable<BankBindGson> getBankInfo();

    /**
     * 获取支付通道
     *
     * @param params
     * @return
     */
//    @FormUrlEncoded
    @GET(front + "submitPay.do?")
    rx.Observable<PayGson> submitPay(@QueryMap Map<String, String> params);

    /**
     * 绑定银行卡
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(front + "bindBank.do?")
    rx.Observable<BaseGson> bindBank(@FieldMap Map<String, String> params);

    /**
     * 获取提款银行卡信息
     *
     * @return
     */
//    @FormUrlEncoded
    @GET(front + "getBankInfoByWithdrawals?")
    rx.Observable<BankBindGson> getBankInfoByWithdrawals();

    /**
     * 提现
     *
     * @param params cash=200&formSubmitTime=20180130122001
     * @return
     */
//    @FormUrlEncoded
    @GET(front + "submitWithdrawals?")
    rx.Observable<BankBindGson> submitWithdrawals(@QueryMap Map<String, String> params);

    /**
     * 查询余额
     *
     * @return
     */
//    @FormUrlEncoded
    @GET(front + "goToWithdrawals?")
    rx.Observable<BalanceGson> goToWithdrawals();

    /**
     * 查询我的保单列表
     *
     * @return
     */
//    @FormUrlEncoded
    @GET(front)
    rx.Observable<InsuranceGson> getMyInsuranceList();
    //-------------------------------下面为个人资料---------------------------------------

    /**
     * 查询收货地址
     *
     * @return
     */
    @GET(front + "getCustomerAddressList.do?")
    rx.Observable<AddressGson> getCustomerAddressList();

    /**
     * 添加收货地址
     *
     * @return
     */
    @GET(front + "saveAddress.do?")
    rx.Observable<AddressGson> saveAddress(@QueryMap Map<String, String> params);

    /**
     * 添加收货地址
     *
     * @return
     */
    @GET(front + "updateAddress.do?")
    rx.Observable<AddressGson> updateAddress(@QueryMap Map<String, String> params);

    /**
     * 添加收货地址
     *
     * @return
     */
    @GET(front + "joinShow.do?")
    rx.Observable<BaseGson> joinShow(@QueryMap Map<String, String> params);


    //-------------------------------下面为车险接口---------------------------------------

    /**
     * 获得投保城市code
     *
     * @return
     */
    @GET(front + "getInsuranceCityCode.do?")
    rx.Observable<CityGson> getInsuranceCityCode();

    /**
     * 获得车险信息
     *
     * @param map
     * @return
     */
    @GET(front + "getInsuranceInfo.do?")
    rx.Observable<InsuranceInfoGson> getInsuranceInfo(@QueryMap Map<String, String> map);

    /**
     * 获得车险方案信息
     *
     * @param map
     * @return
     */
    @GET(front + "getInsuranceOfferList.do?")
    rx.Observable<CateMapGson> getInsuranceOfferList(@QueryMap Map<String, String> map);

    /**
     * 提交车险方案
     *
     * @param map
     * @return
     */
    @GET(front + "submitCase.do?")
    rx.Observable<OrderListGson> submitCase(@QueryMap Map<String, String> map);


    /**
     * 查询订单list
     * getInsuranceOrderPrice.do?
     * LicenseNo=%E8%8B%8FAS37S1&orderNoes=IO00000068,IO00000069&timestamp=1519713403343&version=1.0&appId=ecar&sign=14B154EDC21077D36C398696144A300C
     *
     * @param map
     * @return
     */
    @GET(front + "getInsuranceOrderPrice.do?")
    rx.Observable<OrderListGson> getInsuranceOrderPrice(@QueryMap Map<String, String> map);

    /**
     * 根据订单orderNo
     * getInsuranceOrderPrice.do?
     * LicenseNo=%E8%8B%8FAS37S1&orderNoes=IO00000068,IO00000069&timestamp=1519713403343&version=1.0&appId=ecar&sign=14B154EDC21077D36C398696144A300C
     *
     * @param map
     * @return
     */
    @GET(front + "getInsurancePriceByOrderNo.do?")
    rx.Observable<OrderListGson> getInsurancePriceByOrderNo(@QueryMap Map<String, String> map);

    /**
     * 根据订单orderNo获取详情
     * getInsuranceOrderDeatil.do?
     * LicenseNo=%E8%8B%8FAS37S1&orderNoes=IO00000068,IO00000069&timestamp=1519713403343&version=1.0&appId=ecar&sign=14B154EDC21077D36C398696144A300C
     *
     * @param map
     * @return
     */
    @GET(front + "getInsuranceOrderDeatil.do?")
    rx.Observable<OrderListGson> getInsuranceOrderDeatil(@QueryMap Map<String, String> map);

//    String businessExpireDate

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
    @POST(upload + "uploadImage?")
    rx.Observable<AesEntity> getUploadAesData(@Query("d") String d, @Part MultipartBody.Part part);

    /**
     * 上传图片
     */
    @Multipart
    @POST(upload + "uploadImage?")
    rx.Observable<UploadImageGson> getUploadAesData(@Part MultipartBody.Part part);


}

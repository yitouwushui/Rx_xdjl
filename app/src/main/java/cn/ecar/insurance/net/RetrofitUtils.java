package cn.ecar.insurance.net;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.ArrayMap;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Field;
import java.util.Map;

import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.gson.AddressGson;
import cn.ecar.insurance.dao.gson.BalanceGson;
import cn.ecar.insurance.dao.gson.BankBindGson;
import cn.ecar.insurance.dao.gson.BankGson;
import cn.ecar.insurance.dao.gson.CateMapGson;
import cn.ecar.insurance.dao.gson.CityGson;
import cn.ecar.insurance.dao.gson.CustomerGson;
import cn.ecar.insurance.dao.gson.CustomerShowGson;
import cn.ecar.insurance.dao.gson.FrozenCashGson;
import cn.ecar.insurance.dao.gson.FundsFlowGson;
import cn.ecar.insurance.dao.gson.InformationListGson;
import cn.ecar.insurance.dao.gson.InsuranceGson;
import cn.ecar.insurance.dao.gson.InsuranceInfoGson;
import cn.ecar.insurance.dao.gson.MessageListGson;
import cn.ecar.insurance.dao.gson.OrderListGson;
import cn.ecar.insurance.dao.gson.PayGson;
import cn.ecar.insurance.dao.gson.ProvinceGson;
import cn.ecar.insurance.dao.gson.SignInGson;
import cn.ecar.insurance.dao.gson.TeamGson;
import cn.ecar.insurance.dao.gson.UploadImageGson;
import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.Request;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author yx
 * @date 2016/10/17
 * retrofit加解密与网络请求类
 */

public class RetrofitUtils {

    private static volatile RetrofitUtils instance;

    private static String sessionId;

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        RetrofitUtils.sessionId = sessionId;
//        NetWorkApi.getInstance().getOkHttpClient().interceptors()
//                .add(chain -> {
//                    Request request = chain.request();
//                    MediaType mediaType = request.body().contentType();
//                    try {
//                        Field field = mediaType.getClass().getDeclaredField("mediaType");
//                        field.setAccessible(true);
//                        field.set(mediaType, "application/json");
//                        field.set("JSESSIONID", sessionId);
//                    } catch (NoSuchFieldException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                    return chain.proceed(request);
//                });
    }

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
     *
     * @param obj
     * @return
     */
    public String paramsToJsonString(Object... obj) {
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
     * @param o
     * @return
     * @throws Exception
     */
    public static Map<String, String> objectToArrayMap(Object o) throws Exception {
        Class<? extends Object> c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        Map<String, String> map = new ArrayMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(o);
            if (value != null) {
                map.put(name, value.toString());
            }
        }
        return map;
//        Set<Map.Entry<String, Object>> set = map.entrySet();
//        Iterator<Map.Entry<String, Object>> it = set.iterator();
//        StringBuffer sb = new StringBuffer();
//        while (it.hasNext()) {
//            Map.Entry<String, Object> e = it.next();
//            sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
//        }
//        return sb.deleteCharAt(sb.length()-1).toString();
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

    //客户相关
    public Observable<CustomerGson> login(Map params) {
        return getNetServer().login(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseGson> getVerifyCode(Map params) {
        return getNetServer().getVerifyCode(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Bitmap> getVerifyImage() {
        return getNetServer().getVerifyImage().subscribeOn(Schedulers.io())
                .map(responseBody -> BitmapFactory.decodeStream(responseBody.byteStream()))
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseGson> register(Map params) {
        return getNetServer().register(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CustomerShowGson> getCustomerShowList() {
        return getNetServer().getCustomerShowList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<InformationListGson> getInformationList() {
        return getNetServer().getInformationList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MessageListGson> getMessageList() {
        return getNetServer().getMessageList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SignInGson> customerSignToday() {
        return getNetServer().customerSignToday().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SignInGson> judgeCustomerIsSignToday() {
        return getNetServer().judgeCustomerIsSignToday().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CustomerGson> getCustomerAllInfo() {
        return getNetServer().getCustomerAllInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    // 支付相关
    public Observable<ProvinceGson> getProvinceList() {
        return getNetServer().getProvinceList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BankGson> getBranchBankList(String bankCode, String cityCode) {
        return getNetServer().getBranchBankList(bankCode, cityCode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CityGson> getCityListProvinceCode(String provinceCode) {
        return getNetServer().getCityListProvinceCode(provinceCode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BankGson> getBankList() {
        return getNetServer().getBankList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BankBindGson> getBankInfo() {
        return getNetServer().getBankInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<PayGson> submitPay(Map params) {
        return getNetServer().submitPay(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<PayGson> commitInsuranceOrder(Map<String, String> map) {
        return getNetServer().commitInsuranceOrder(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BankGson> bindBank(Map params) {
        return getNetServer().bindBank(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BankBindGson> getBankInfoByWithdrawals() {
        return getNetServer().getBankInfoByWithdrawals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BankBindGson> submitWithdrawals(Map params) {
        return getNetServer().submitWithdrawals(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    // 个人模块

    public Observable<BalanceGson> goToWithdrawals() {
        return getNetServer().goToWithdrawals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SignInGson> getCustomerSignByPage(int pageNum, int pageSize) {
        return getNetServer().getCustomerSignByPage(pageNum,pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<InsuranceGson> getInsuranceOrderByPage(int pageNum, int pageSize) {
        return getNetServer().getInsuranceOrderByPage(pageNum, pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TeamGson> getFirstTeamByPage(int pageNum, int pageSize) {
        return getNetServer().getFirstTeamByPage(pageNum, pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TeamGson> getTeamInfoByLevel(int pageNum, int level, int pageSize) {
        return getNetServer().getTeamInfoByLevel(pageNum, level, pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<FrozenCashGson> getFrozenCapitalList(int pageNum, int pageSize) {
        return getNetServer().getFrozenCapitalList(pageNum, pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<FundsFlowGson> getFundsList(int pageNum, int pageSize) {
        return getNetServer().getFundsList(pageNum, pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AddressGson> getCustomerAddressList() {
        return getNetServer().getCustomerAddressList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AddressGson> saveAddress(Map<String, String> params) {
        return getNetServer().saveAddress(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AddressGson> updateAddress(Map<String, String> params) {
        return getNetServer().updateAddress(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseGson> joinShow(Map<String, String> params) {
        return getNetServer().joinShow(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    // 车险接口

    public Observable<CityGson> getInsuranceCityCode() {
        return getNetServer().getInsuranceCityCode().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<InsuranceInfoGson> getInsuranceInfo(Map<String, String> map) {
        return getNetServer().getInsuranceInfo(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CateMapGson> getInsuranceOfferList(Map<String, String> map) {
        return getNetServer().getInsuranceOfferList(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OrderListGson> submitCase(Map<String, String> map) {
        return getNetServer().submitCase(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OrderListGson> getInsuranceOrderPrice(Map<String, String> map) {
        return getNetServer().getInsuranceOrderPrice(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OrderListGson> getInsurancePriceByOrderNo(Map<String, String> map) {
        return getNetServer().getInsurancePriceByOrderNo(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OrderListGson> getInsuranceOrderDeatil(Map<String, String> map) {
        return getNetServer().getInsuranceOrderDeatil(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OrderListGson> saveInsuranceData(Map<String, String> map) {
        return getNetServer().saveInsuranceData(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<UploadImageGson> getUploadData(MultipartBody.Part part) {
        return getNetServer().getUploadData(part);
    }

}

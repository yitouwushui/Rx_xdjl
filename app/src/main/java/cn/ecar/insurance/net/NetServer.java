package cn.ecar.insurance.net;

import java.util.Map;

import cn.ecar.insurance.base.BaseEntity;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

}

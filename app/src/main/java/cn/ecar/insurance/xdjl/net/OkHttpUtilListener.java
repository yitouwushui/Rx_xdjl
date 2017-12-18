package cn.ecar.insurance.xdjl.net;

import okhttp3.Call;
import okhttp3.Request;

/**
 * @author xy
 * @date 2017/6/8
 * okhttputils回调接口
 */

public interface OkHttpUtilListener {

    /**
     * 网络请求开始
     *
     * @param request
     * @param id
     */
    void onRequestStart(Request request, int id);

    /**
     * 请求完成
     *
     * @param id
     */
    void onRequestComplete(int id);

    /**
     * 请求出错
     *
     * @param call
     * @param e
     * @param id
     */
    void onRequestError(Call call, Exception e, int id);

    /**
     * 请求成功
     *
     * @param response
     * @param id
     */
    void onRequestSuccess(String response, int id);

}

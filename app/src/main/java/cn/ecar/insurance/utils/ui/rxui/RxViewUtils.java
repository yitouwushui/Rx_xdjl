package cn.ecar.insurance.utils.ui.rxui;

import android.app.Activity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxAdapterView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.concurrent.TimeUnit;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.utils.system.NetWorkStateUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yx on 2017/7/25.
 * rxbinding工具类
 */

public class RxViewUtils {

    private RxViewUtils() {
    }

    /**
     * rxbind控件点击方法
     *
     * @param view          点击的控件
     * @param second        设置抖动秒数
     * @param clicklistener 点击回调
     */
    public static void onViewClick(View view, int second, ViewClickListener clicklistener) {
        RxView.clicks(view).throttleFirst(second, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.immediate()).observeOn(AndroidSchedulers.mainThread())
                .filter(aVoid -> {
                    boolean hasNetWork = NetWorkStateUtils.isNetworkConnected();
                    if (!hasNetWork) {
                        ToastUtils.showToast(XdConfig.NO_NETWORK);
                    }
                    return hasNetWork;
                })
                .subscribe(aVoid -> clicklistener.onViewClick());
    }

    /**
     * rxbind控件点击方法
     *
     * @param view          点击的控件
     * @param clicklistener 点击回调
     */
    public static void onViewClick(View view, int second, OnViewClick clicklistener) {
        RxView.clicks(view).throttleFirst(second, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.immediate()).observeOn(AndroidSchedulers.mainThread())
                .filter(aVoid -> {
                    boolean hasNetWork = NetWorkStateUtils.isNetworkConnected();
                    if (!hasNetWork) {
                        ToastUtils.showToast(XdConfig.NO_NETWORK);
                    }
                    return hasNetWork;
                })
                .subscribe(aVoid -> clicklistener.onClick(view));
    }

    /**
     * rxbind控件点击方法
     *
     * @param view          点击的控件
     * @param clicklistener 点击回调
     */
    public static void onViewClick(View view, OnViewClick clicklistener) {
        RxView.clicks(view)
                .subscribeOn(Schedulers.immediate()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> clicklistener.onClick(view));
    }


    /**
     * 默认间隔500毫秒点击
     *
     * @param view
     * @param clickListener
     */
    public static void onViewClick(View view, ViewClickListener clickListener) {
        RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.immediate()).observeOn(AndroidSchedulers.mainThread())
                .filter(aVoid -> {
                    boolean hasNetWork = NetWorkStateUtils.isNetworkConnected();
                    if (!hasNetWork) {
                        ToastUtils.showToast(XdConfig.NO_NETWORK);
                    }
                    return hasNetWork;
                })
                .subscribe(aVoid -> clickListener.onViewClick());
    }

    public static void onViewClickNeedPermission(Activity activity, View view, OnViewClickWithPermission clickListener,
                                                 String... permissions) {
        RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.immediate()).observeOn(AndroidSchedulers.mainThread())
                .filter(aVoid -> {
                    boolean hasNetWork = NetWorkStateUtils.isNetworkConnected();
                    if (!hasNetWork) {
                        ToastUtils.showToast(XdConfig.NO_NETWORK);
                    }
                    return hasNetWork;
                })
                .compose(new RxPermissions(activity).ensure(permissions))
                .subscribe(permission -> {
                    clickListener.onViewClick(permission);
                });
    }

    /**
     * 设置textview text
     *
     * @param textView
     * @param text
     */
    public static void setTvText(TextView textView, CharSequence text) {
        RxTextView.text(textView).call(text);
    }

    /**
     * 设置textview hint
     *
     * @param textView
     * @param text
     */
    public static void setTvHint(TextView textView, CharSequence text) {
        RxTextView.hint(textView).call(text);
    }

    /**
     * 设置textview color
     *
     * @param textView
     * @param color
     */
    public static void setTvColor(TextView textView, int color) {
        RxTextView.color(textView).call(color);
    }

    /**
     * 设置控件是否可用
     *
     * @param view
     * @param enable
     */
    public static void setViewEnable(View view, boolean enable) {
        RxView.enabled(view).call(enable);
    }

    /**
     * textview text状态改变观察者
     *
     * @param textView
     * @param second
     */
//    public static void onTvTextChanges(TextView textView, int second, TextChangeListener changeListener) {
//        RxTextView.textChanges(textView).delay(second, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.immediate()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(charSequence ->
//                        changeListener.onTextChange(textView,charSequence.toString()));
//    }

    /**
     * EditText text状态改变观察者
     *
     * @param editView
     * @param second
     */
    public static void onTvTextChanges(EditText editView, int second, TextChangeListener changeListener) {
        RxTextView.textChanges(editView).delay(second, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.immediate()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence ->
                        changeListener.onTextChange(editView, charSequence.toString()));
    }


    /**
     * 适配器条目点击
     *
     * @param view
     */
    public static void onAdapterItemClick(AdapterView<? extends Adapter> view, int second,
                                          OnAdapterItemClickListener itemClickListener) {
        RxAdapterView.itemClicks(view).throttleFirst(second, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.immediate()).observeOn(AndroidSchedulers.mainThread())
                .filter(aVoid -> {
                    boolean hasNetWork = NetWorkStateUtils.isNetworkConnected();
                    if (!hasNetWork) {
                        ToastUtils.showToast(XdConfig.NO_NETWORK);
                    }
                    return hasNetWork;
                })
                .subscribe(itemClickListener::onItemClick);
    }

    /**
     * 判断网络状态和抖动的控件点击
     *
     * @param view
     * @param second
     * @return
     */
    public static Observable<Void> onViewClickOnNet(View view, int second) {
        return RxView.clicks(view).throttleFirst(second, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.immediate()).observeOn(AndroidSchedulers.mainThread())
                .filter(aVoid -> {
                    boolean hasNetWork = NetWorkStateUtils.isNetworkConnected();
                    if (!hasNetWork) {
                        ToastUtils.showToast(XdConfig.NO_NETWORK);
                    }
                    return hasNetWork;
                });
    }

    /**
     * 判断抖动的控件点击
     *
     * @param view
     * @param second
     * @return
     */
    public static Observable<Void> onViewClick(View view, int second) {
        return RxView.clicks(view).throttleFirst(second, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.immediate()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 控件焦点改变的时候
     */
    public static Observable<Boolean> onViewFocusChange(View view) {
        return RxView.focusChanges(view);
    }

}

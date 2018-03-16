package cn.ecar.insurance.mvvm.view.act.pay;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.litepal.util.LogUtil;

import java.lang.ref.WeakReference;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.gson.PayGson;
import cn.ecar.insurance.databinding.ActivityPaymentBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.main.MainActivity;
import cn.ecar.insurance.rxevent.RxBus;
import cn.ecar.insurance.rxevent.RxCodeConstants;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 */
public class PaymentActivity extends BaseBindingActivity<ActivityPaymentBinding> implements OnViewClick {

    private PayGson payGson;
    private long mExitTime;
    private static final String TAG = "PaymentActivity";


    @Override
    public void getBundleExtras(Bundle extras) {
        payGson = (PayGson) extras.get(XdConfig.EXTRA_VALUE);
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initView() {
        mVB.webView.addJavascriptInterface(new JavaScriptInterface(mContext), "handler");
        WebSettings settings = mVB.webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持javaScript
        settings.setDefaultTextEncodingName("utf-8");//设置网页默认编码
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //post请求(使用键值对形式，格式与get请求一样，key=value,多个用&连接)
        String url = payGson.getBankUrl().trim() + payGson.getParam().trim();
        mVB.webView.loadUrl(url);
//        mVB.webView.postUrl(payGson.getBankUrl().trim(), payGson.getParam().trim().getBytes());
        mVB.webView.setWebViewClient(new MyClient());
    }

    @Override
    protected void initData() {

    }


    public class JavaScriptInterface {
        private WeakReference<Context> mContext;

        /**
         * Instantiate the interface and set the context
         */
        public JavaScriptInterface(Context c) {
            mContext = new WeakReference<>(c);
        }

        @JavascriptInterface
        public void showContent(String toast) {
            Log.d(TAG, toast);
            Toast.makeText(mContext.get(), toast, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void initEvent() {
//        RxViewUtils.onViewClick(mVB.btSubmit, this);

    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.bt_submit:
//                break;
//            default:
//        }
    }

    @Override
    protected void destroyView() {
        mVB.webView.removeAllViews();
        mVB.webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mVB.webView.canGoBack()) {
                mVB.webView.goBack();
            }
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showToast("2秒内连续按两次退出");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    final class MyClient extends WebViewClient {
        /**
         * 开始加载页面
         *
         * @param view    WebView
         * @param url     加载页面的URL
         * @param favicon 位图(网址的图标)
         */
        @Override
        public void onPageStarted(WebView view, final String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            // 请求
            if (url.startsWith(XdConfig.REDIRECT_URL)) {
                Log.d(TAG, "充值成功");
                view.loadUrl("javascript:window.handler.showContent(document.body.innerHTML);");
                //将此activity添加到List
                RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_PAY_SUCCESS);
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(MainActivity.class)
                        .build()
                        .setResultOkWithFinishUi();
            }
        }


        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest
                request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            Log.e(TAG, errorResponse.toString());
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }
    }


//
//    /**
//     * 浏览器可弹窗
//     *
//     * @author Administrator
//     */
//    final class MyWebChromeClient extends WebChromeClient {
//
//
//        @Override
//        public boolean onJsConfirm(WebView view, String url, String message,
//                                   final JsResult result) {
//            Log.i("测试url:",url);
//            new AlertDialog.Builder(mContext)
//                    .setTitle("App Titler")
//                    .setMessage(message)
//                    .setPositiveButton(android.R.string.ok,
//                            (dialog, which) -> {
//                                //
//                                result.confirm();
//                            })
//                    .setNegativeButton(android.R.string.cancel,
//                            (dialog, which) -> {
//                                //
//                                result.cancel();
//                            }).create().show();
//
//            return true;
//        }
//    }
}

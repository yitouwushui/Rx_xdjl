package cn.ecar.insurance.mvvm.view.act.pay;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.gson.PayGson;
import cn.ecar.insurance.databinding.ActivityPaymentBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.ui.CommonUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 */
public class PaymentActivity extends BaseBindingActivity<ActivityPaymentBinding> implements OnViewClick {

    private PayGson payGson;
    private long mExitTime;


    @Override
    public void getBundleExtras(Bundle extras) {
        payGson = (PayGson) extras.get(XdConfig.EXTRA_VALUE);
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        mVB.includeToolbar.toolbar.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initView() {
        WebSettings settings = mVB.webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持javaScript
        settings.setDefaultTextEncodingName("utf-8");//设置网页默认编码
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //post请求(使用键值对形式，格式与get请求一样，key=value,多个用&连接)
        String url = payGson.getBankUrl().trim() + payGson.getParam().trim();
        mVB.webView.loadUrl(url);
//        mVB.webView.postUrl(payGson.getBankUrl().trim(), payGson.getParam().trim().getBytes());
        mVB.webView.setWebChromeClient(new MyWebChromeClient());// 设置浏览器可弹窗
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mVB.webView.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void initData() {

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

    /**
     * 浏览器可弹窗
     *
     * @author Administrator
     */
    final class MyWebChromeClient extends WebChromeClient {

        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                                   final JsResult result) {
            new AlertDialog.Builder(mContext)
                    .setTitle("App Titler")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    result.confirm();
                                }
                            })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    result.cancel();
                                }
                            }).create().show();

            return true;
        }
    }
}

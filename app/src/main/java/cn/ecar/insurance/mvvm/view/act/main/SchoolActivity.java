package cn.ecar.insurance.mvvm.view.act.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.ActivitySchoolBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.utils.ui.ToastUtils;

/**
 * 选择银行，会员单位等
 *
 * @author ding
 */
public class SchoolActivity extends BaseBindingActivity<ActivitySchoolBinding> {

    private long mExitTime = 0L;
    private static final String YOUKU_URL = "http://m.youku.com/video/id_XMjk4MjU5NzgwNA==.html?spm=a2h0k.8191407.0.0&from=s1.8-1-1.2&source=";
    private WebChromeClient.CustomViewCallback mCallBack;
    private CustomWebViewChromeClient mCustomWebViewChromeClient;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_school;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("让每一次的分享都有意义");
        mCustomWebViewChromeClient = new CustomWebViewChromeClient();
        mVB.webView.setWebChromeClient(mCustomWebViewChromeClient);
//        mVB.webView.setWebViewClient(new WebViewClient());
        mVB.progressLoad.setMax(100);
        WebSettings webSettings = mVB.webView.getSettings();
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setUseWideViewPort(true); // 关键点
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);//优酷视频特性
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mVB.webView.setWebViewClient(new CustomWebClient());

        mVB.webView.addJavascriptInterface(new JsObject(), "onClick");
        mVB.webView.loadUrl(YOUKU_URL);
    }

    private class CustomWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //这个方法必须重写。否则会出现优酷视频周末无法播放。周一-周五可以播放的问题
            if (url.startsWith("intent") || url.startsWith("youku")) {
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            String js = getJs(url);
            view.loadUrl(js);
        }
    }

    private String getJs(String url) {
        return "javascript:document.getElementsByClassName('"
                + getTagByUrl(url)
                + "')[0].addEventListener('click',function(){onClick.fullscreen();return false;});";
    }


    private class JsObject {
        @JavascriptInterface
        public void fullscreen() {
            //监听到用户点击全屏按钮
            fullScreen();
        }
    }


    private final class CustomWebViewChromeClient extends WebChromeClient {

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            fullScreen();
            mVB.includeToolbar.includeToolbarLineLay.setVisibility(View.GONE);
            mVB.webView.setVisibility(View.GONE);
            mVB.videoContainer.setVisibility(View.VISIBLE);
            mVB.videoContainer.addView(view);
            mCallBack = callback;
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            fullScreen();
            if (mCallBack != null) {
                mCallBack.onCustomViewHidden();
            }
            mVB.includeToolbar.includeToolbarLineLay.setVisibility(View.VISIBLE);
            mVB.webView.setVisibility(View.VISIBLE);
            mVB.videoContainer.removeAllViews();
            mVB.videoContainer.setVisibility(View.GONE);
            super.onHideCustomView();
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mVB.progressLoad.setProgress(newProgress);
            if (newProgress == 100) {
                mVB.progressLoad.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    private void fullScreen() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public static String getTagByUrl(String url) {
        if (url.contains("qq")) {
            return "tvp_fullscreen_button"; // http://m.v.qq.com
        } else if (url.contains("youku")) {
            return "x-zoomin";              // http://www.youku.com
        } else if (url.contains("bilibili")) {
            return "icon-widescreen";       // http://www.bilibili.com/mobile/index.html
        } else if (url.contains("acfun")) {
            return "controller-btn-fullscreen"; //http://m.acfun.tv   无效
        } else if (url.contains("le")) {
            return "hv_ico_screen";         // http://m.le.com  无效
        }
        return "";
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {
        mVB.webView.removeAllViews();
        mVB.webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            mCustomWebViewChromeClient.onHideCustomView();
            if (mVB.webView.canGoBack()) {
                mVB.webView.goBack();
            }
            if ((System.currentTimeMillis() - mExitTime) > 1500) {
                ToastUtils.showToast("2秒内连续按两次退出");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

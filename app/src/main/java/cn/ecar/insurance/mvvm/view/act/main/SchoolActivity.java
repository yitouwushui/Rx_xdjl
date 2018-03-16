package cn.ecar.insurance.mvvm.view.act.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
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


    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_school;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("让每一次的分享都有意义");
        mVB.progressLoad.setMax(100);

        mVB.webView.getSettings().setJavaScriptEnabled(true);
        mVB.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mVB.webView.getSettings().setSupportMultipleWindows(true);
        mVB.webView.setWebViewClient(new WebViewClient());
        mVB.webView.setWebChromeClient(new MyWebChromeClient());
        mVB.webView.loadUrl(YOUKU_URL);
    }

    public final class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mVB.progressLoad.setProgress(newProgress);
            if (newProgress == 100) {
                mVB.progressLoad.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }

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
            if ((System.currentTimeMillis() - mExitTime) > 2000L) {
                ToastUtils.showToast("再按一次退出");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

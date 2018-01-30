package cn.ecar.insurance.mvvm.base;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.LayoutRes;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;
import com.zhy.autolayout.AutoLayoutActivity;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.widget.dialog.AlertDialog;
import cn.ecar.insurance.widget.dialog.LoadingDialog;
import rx.Observable;
import rx.Subscription;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * @author yx
 * @date 2017/4/19
 * 基类 databinding activity
 */

public abstract class BaseBindingActivity<VB extends ViewDataBinding> extends AutoLayoutActivity implements LifecycleProvider<ActivityEvent> {

    protected VB mVB;
    protected Resources mResources;
    private LoadingDialog waitDialog;
    private AlertDialog mErrorDialog;
    private CompositeSubscription mCompositeSubscription;
    protected Context mContext;


    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVB = DataBindingUtil.setContentView(this, getLayoutRes());
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        mResources = XdAppContext.app().getmResources();
        mContext = this;
        Bundle extras = getIntent().getExtras();
        setStatusBar();
        if (null != extras) {
            getBundleExtras(extras);
        }
        initView();
        initTitleBar();
        initData();
        initEvent();
    }

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }


    @UiThread
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, mResources.getColor(R.color.color_hk_sqjd_nor_0063b0), 0);
    }

    public abstract void getBundleExtras(Bundle extras);

    //初始化titlebar
    @UiThread
    protected void initTitleBar() {
//        ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
//        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
////        if (ivBack != null) {
////            RxViewUtils.onViewClick(ivBack, view -> finishActivity());
////        }
//        if (tvTitle != null) {
//            String title = getIntent().getStringExtra(WcConfig.VIEW_TITLE);
//            if (title != null) {
//                RxViewUtils.setTvText(tvTitle, title);
//            }
//        }

    }

    @MainThread
    public void addSubscription(@NonNull Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycleSubject.onNext(ActivityEvent.STOP);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_enter2, R.anim.anim_exit2);
    }

    @UiThread
    public Dialog showWaitDialog() {
        if (!this.isFinishing()) {
            waitDialog = new LoadingDialog(this);
            waitDialog.setCancelable(false);
            if (waitDialog != null) {
                waitDialog.show();
            }
            return waitDialog;
        }

        return null;
    }

    @UiThread
    public void hideWaitDialog() {
        if (waitDialog != null) {
            try {
                waitDialog.dismiss();
                waitDialog = null;
            } catch (Exception ex) {
                Logger.w("极其个别的机型,会报错-_-");
            }
        }
    }

    /**
     * 服务器返回的非suc结果
     *
     * @param result
     * @param msg
     */
    protected void handingResult(String result, String msg) {
        if (result.equals("authentication")) {
            //未认证

        } else if (result.equals("token")) {
            //token失效

        } else if (result.equals("fail")) {
            //服务器返回错误

        } else {
            ToastUtils.showToast(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        mVB.unbind();
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
        destroyView();
    }

    @UiThread
    public void finishActivity() {
        finish();
        overridePendingTransition(R.anim.anim_enter2, R.anim.anim_exit2);
    }

    @UiThread
    protected void initLRecyclerView(@NonNull LRecyclerView recyclerView) {
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setArrowImageView(R.drawable.ic_refresh_arrow);
        recyclerView.setFooterViewHint(getString(R.string.footer_loading), getString(R.string.footer_nomore), getString(R.string.footer_network_error));
        recyclerView.setHeaderViewColor(R.color.colorAccent, R.color.colorAccent, android.R.color.transparent);
    }


    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    protected abstract void destroyView();
}
package cn.ecar.insurance.mvvm.base;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle.components.support.RxFragment;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.widget.dialog.AlertDialog;
import cn.ecar.insurance.widget.dialog.LoadingDialog;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yx on 2017/8/18.
 * 基类 databinding fragment
 */

public abstract class BaseBindingFragment<VB extends ViewDataBinding> extends RxFragment {

    public VB mVB;
    protected Context mContext;
    protected Resources mResources;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mResources = XdAppContext.app().getResources();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mVB = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mVB.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initEvent();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @MainThread
    public void addSubscription(@NonNull Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }


    //获取等待加载框
    private LoadingDialog waitDialog;

    private AlertDialog mErrorDialog;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyView();
        mVB.unbind();
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    @UiThread
    protected LoadingDialog showWaitDialog() {
        if (!getActivity().isFinishing()) {
            waitDialog = new LoadingDialog(getActivity());
            waitDialog.setCancelable(false);
            if (waitDialog != null) {
                waitDialog.show();
            }
            return waitDialog;
        }
        return null;
    }

    @UiThread
    protected void hideWaitDialog() {
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

    @UiThread
    protected void initLrecyclerView(@NonNull LRecyclerView recyclerView) {
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setArrowImageView(R.drawable.ic_refresh_arrow);
        recyclerView.setFooterViewHint(getString(R.string.footer_loading), getString(R.string.footer_no_pro), getString(R.string.footer_network_error));
        recyclerView.setHeaderViewColor(R.color.colorAccent, R.color.colorAccent, android.R.color.transparent);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    protected abstract void destroyView();
}

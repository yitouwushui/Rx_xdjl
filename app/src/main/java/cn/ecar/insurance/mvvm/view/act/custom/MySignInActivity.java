package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.MySignInAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.SignIn;
import cn.ecar.insurance.databinding.ActivitySignInBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.mvvm.viewmodel.main.HomeViewModel;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.ui.TimeUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class MySignInActivity extends BaseBindingActivity<ActivitySignInBinding> implements OnViewClick {

    private CustomViewModel mCustomViewModel;
    private int indexPage = 1;
    private MySignInAdapter myTeamAdapter;
    private HomeViewModel mHomeViewModel;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("我的签到");
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        loadListData(indexPage);
        loadTitle();
        mVB.refresh.setOnRefreshListener(refreshlayout -> {
            indexPage = 1;
            loadListData(indexPage);
        });
        mVB.refresh.setOnLoadmoreListener(refreshlayout -> {
            indexPage++;
            loadListData(indexPage);
        });
    }

    private void loadTitle() {
        String signData = SpUtils.getString(XdConfig.SIGN_IN);
        mVB.tvToday.setText("日期:"+TimeUtils.getStringByDate(System.currentTimeMillis()));
        if (!TimeUtils.getStringByDate(System.currentTimeMillis()).equals(signData)) {
            mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
            mHomeViewModel.judgeCustomerIsSignToday().observe(this, signInGson -> {
                if (signInGson != null && signInGson.getIsSign() == 0) {
                    mVB.tvSignStatus.setText("已签到");
                }
            });
        } else {
            mVB.tvSignStatus.setText("已签到");
        }
    }

    /**
     * 加载数据
     *
     * @param indexPage
     */
    private void loadListData(int indexPage) {
        mCustomViewModel.getMySignInList(indexPage, 20).observe(this, signInGson -> {
            mVB.refresh.finishRefresh();
            mVB.refresh.finishLoadmore();
            if (signInGson == null) {
                ToastUtils.showToast("获取签到信息失败");
                return;
            }
            if (!XdConfig.RESPONSE_T.equals(signInGson.getResponseCode())) {
                ToastUtils.showToast(signInGson.getResponseMsg());
                return;
            }
            List<SignIn> signInList = signInGson.getList();
            if (signInList == null || signInList.isEmpty()) {
                ToastUtils.showToast("没有更多的数据了");
                return;
            }
            showData(signInList);

        });
    }

    /**
     * 展示数据
     *
     * @param datas
     */
    private void showData(List<SignIn> datas) {
        if (myTeamAdapter == null) {
            myTeamAdapter = new MySignInAdapter(mContext, R.layout.item_list_my_sign_in, datas);
            mVB.listViewMySignIn.setAdapter(myTeamAdapter);
        } else {
            if (indexPage != 1) {
                myTeamAdapter.addmDatas(datas);
            } else {
                myTeamAdapter.setmDatas(datas);
            }
            myTeamAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initEvent() {
//        RxViewUtils.onViewClick(mVB.btNext, this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void destroyView() {

    }
}

package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.FundsAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.FrozenCash;
import cn.ecar.insurance.dao.bean.FundsFlow;
import cn.ecar.insurance.dao.gson.FundsFlowGson;
import cn.ecar.insurance.databinding.ActivityFundsBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class FundsActivity extends BaseBindingActivity<ActivityFundsBinding> implements OnViewClick {

    private CustomViewModel mCustomViewModel;
    private int indexPage = 1;
    private FundsAdapter mFundsAdapter;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_funds;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("资金明细");
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        loadData(1);
        mVB.refresh.setOnRefreshListener(refreshlayout -> {
            indexPage = 1;
            loadData(indexPage);
        });
        mVB.refresh.setOnLoadmoreListener(refreshlayout -> {
            indexPage++;
            loadData(indexPage);
        });
    }

    private void loadData(int index) {
        mCustomViewModel.getFundsList(index, 20).observe(this, frozenCashGson -> {
            mVB.refresh.finishRefresh();
            mVB.refresh.finishLoadmore();
            if (frozenCashGson == null) {
                ToastUtils.showToast("获取数据失败");
                return;
            }
            if (!XdConfig.RESPONSE_T.equals(frozenCashGson.getResponseCode())) {
                ToastUtils.showToast(frozenCashGson.getResponseMsg());
                return;
            }
            List<FundsFlow> list = frozenCashGson.getList();
            if (list == null || list.isEmpty()) {
                ToastUtils.showToast("没有更多数据了");
                return;
            }
            showData(list);
        });
    }

    /**
     * @param datas
     */
    private void showData(List<FundsFlow> datas) {
        if (mFundsAdapter == null) {
            mFundsAdapter = new FundsAdapter(mContext, R.layout.me_item_list_funds, datas);
            mVB.listViewFunds.setAdapter(mFundsAdapter);
        } else {
            if (indexPage != 1) {
                mFundsAdapter.addmDatas(datas);
            } else {
                mFundsAdapter.setmDatas(datas);
            }
            mFundsAdapter.notifyDataSetChanged();
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

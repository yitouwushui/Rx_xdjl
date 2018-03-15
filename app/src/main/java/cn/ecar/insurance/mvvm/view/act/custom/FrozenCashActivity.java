package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.FrozenCashAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.FrozenCash;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.databinding.ActivityFrozenCashBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class FrozenCashActivity extends BaseBindingActivity<ActivityFrozenCashBinding> implements OnViewClick {

    private CustomViewModel mCustomViewModel;
    private int indexPage = 1;
    private FrozenCashAdapter mFrozenCashAdapter;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_frozen_cash;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("冻结资金");
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
        mCustomViewModel.getFrozenCapitalList(index,20).observe(this, frozenCashGson -> {
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
            List<FrozenCash> list = frozenCashGson.getList();
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
    private void showData(List<FrozenCash> datas) {
        if (mFrozenCashAdapter == null) {
            mFrozenCashAdapter = new FrozenCashAdapter(mContext, R.layout.me_item_list_frozen_cash, datas);
            mVB.listViewFrozenCash.setAdapter(mFrozenCashAdapter);
        } else {
            if (indexPage != 1) {
                mFrozenCashAdapter.addmDatas(datas);
            } else {
                mFrozenCashAdapter.setmDatas(datas);
            }
            mFrozenCashAdapter.notifyDataSetChanged();
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

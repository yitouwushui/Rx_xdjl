package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.MyInsuranceAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.dao.gson.InsuranceGson;
import cn.ecar.insurance.databinding.ActivityMyInsuranceBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.rxevent.RxBus;
import cn.ecar.insurance.rxevent.RxCodeConstants;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class MyInsuranceActivity extends BaseBindingActivity<ActivityMyInsuranceBinding> implements OnViewClick {

    private CustomViewModel mCustomViewModel;
    private int indexPage = 1;
    private MyInsuranceAdapter mMyInsuranceAdapter;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_insurance;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("我的保单");
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
        mCustomViewModel.getInsuranceOrderByPage(String.valueOf(index)).observe(this, insuranceGson -> {
            mVB.refresh.finishRefresh();
            mVB.refresh.finishLoadmore();
            if(insuranceGson == null){
                ToastUtils.showToast("获取保单失败");
                return;
            }
            if (XdConfig.RESPONSE_T.equals(insuranceGson.getResponseCode())) {
                List<OrderBean> list = insuranceGson.getList();
                if (list == null || list.isEmpty()) {
                    ToastUtils.showToast("没有更多数据了");
                } else {
                    showData(insuranceGson.getList());
                }
            } else {
                ToastUtils.showToast(insuranceGson.getResponseMsg());
            }
        });
    }

    /**
     * @param datas
     */
    private void showData(List<OrderBean> datas) {
        if (mMyInsuranceAdapter == null) {
            mMyInsuranceAdapter = new MyInsuranceAdapter(mContext, R.layout.item_list_my_insurance, datas);
            mVB.listViewMyInsurance.setAdapter(mMyInsuranceAdapter);
            mVB.listViewMyInsurance.setOnItemClickListener((parent, view, position, id) -> {
                OrderBean insuranceDetails = mMyInsuranceAdapter.getItem(position);
                new IntentUtils.Builder(mContext)
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, insuranceDetails.getOrderNo())
                        .setTargetActivity(InsuranceDetailsActivity.class)
                        .build()
                        .startActivity(true);
            });
        } else {
            if (indexPage != 1) {
                mMyInsuranceAdapter.addmDatas(datas);
            } else {
                mMyInsuranceAdapter.setmDatas(datas);
            }
            mMyInsuranceAdapter.notifyDataSetChanged();
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

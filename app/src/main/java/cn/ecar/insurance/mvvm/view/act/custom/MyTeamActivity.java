package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.MyTeamAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.databinding.ActivityMyTeamBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class MyTeamActivity extends BaseBindingActivity<ActivityMyTeamBinding> implements OnViewClick {

    private CustomViewModel mCustomViewModel;
    private int indexPage = 1;
    private MyTeamAdapter myTeamAdapter;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_team;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("我的团队");
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        loadData(indexPage);
        mVB.refresh.setOnRefreshListener(refreshlayout -> {
            indexPage = 1;
            loadData(indexPage);
        });
        mVB.refresh.setOnLoadmoreListener(refreshlayout -> {
            indexPage++;
            loadData(indexPage);
        });
    }

    private void loadData(int indexPage) {
        mCustomViewModel.getMyTeamList(indexPage, 20).observe(this, teamGson -> {
            mVB.refresh.finishRefresh();
            mVB.refresh.finishLoadmore();
            if (teamGson == null) {
                ToastUtils.showToast("获取保单失败");
                return;
            }
            if (!XdConfig.RESPONSE_T.equals(teamGson.getResponseCode())) {
                ToastUtils.showToast(teamGson.getResponseMsg());
                return;
            }
            List<Customer> customerList = teamGson.getList();
            if (customerList == null || customerList.isEmpty()) {
                ToastUtils.showToast("没有更多的数据了");
                return;
            }
            showData(customerList);
        });
    }

    /**
     * 刷新数据
     *
     * @param datas
     */
    private void showData(List<Customer> datas) {
        if (myTeamAdapter == null) {
            myTeamAdapter = new MyTeamAdapter(mContext, R.layout.item_list_my_team, datas);
            mVB.listViewMyTeam.setAdapter(myTeamAdapter);
            mVB.listViewMyTeam.setOnItemClickListener((parent, view, position, id) -> {
                Customer customer = myTeamAdapter.getItem(position);
                new IntentUtils.Builder(mContext)
                        .setParcelableExtra(XdConfig.EXTRA_VALUE, customer)
                        .setTargetActivity(TeamDetailsActivity.class)
                        .build()
                        .startActivity(true);
            });
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

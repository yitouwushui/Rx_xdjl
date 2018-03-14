package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.MyTeamDetailsAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.databinding.ActivityTeamDetailBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.ui.TimeUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 * @date 2018/2/2
 */

public class TeamDetailsActivity extends BaseBindingActivity<ActivityTeamDetailBinding> implements OnViewClick {

    private CustomViewModel mCustomViewModel;
    private Customer customer;
    private int indexPage = 1;
    private MyTeamDetailsAdapter myTeamDetailsAdapter;

    @Override
    public void getBundleExtras(Bundle extras) {
        customer = extras.getParcelable(XdConfig.EXTRA_VALUE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_team_detail;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("我的团队");
        showAgentInfo(customer);
    }

    private void showAgentInfo(Customer customer) {
        if (customer == null) {
            return;
        }
        mVB.tvName.setText("姓名:" + customer.getCustomerId());
        mVB.tvPhoneNo.setText("手机号:" + customer.getPhoneNo());
        mVB.tvRegistTime.setText("注册日期:" + TimeUtils.getStringByDate(customer.getRegistTime()));
        mVB.tvIdentity.setText("身份证:" + customer.getIdentity());
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
        mCustomViewModel.getTeamInfoByLevel(String.valueOf(indexPage), 1, 10)
                .observe(this, teamGson -> {
                    mVB.refresh.finishRefresh();
                    mVB.refresh.finishLoadmore();
                    if (teamGson == null) {
                        ToastUtils.showToast("查询失败,拉下刷新");
                        return;
                    }
                    if (!teamGson.getResponseCode().equals(XdConfig.RESPONSE_T)) {
                        ToastUtils.showToast(teamGson.getResponseMsg());
                        return;
                    }
                    List<Customer> list = teamGson.getList();
                    if (list == null || list.isEmpty()) {
                        ToastUtils.showToast("没有更多的数据了");
                        return;
                    }
                    showAgentInfo(teamGson.getAgentInfo());
                    showData(list);
                });
    }

    private void showData(List<Customer> datas) {
        if (myTeamDetailsAdapter == null) {
            myTeamDetailsAdapter = new MyTeamDetailsAdapter(mContext, R.layout.me_item_list_team_detail, datas);
            mVB.listViewMyTeamDetails.setAdapter(myTeamDetailsAdapter);
        } else {
            if (indexPage != 1) {
                myTeamDetailsAdapter.addmDatas(datas);
            } else {
                myTeamDetailsAdapter.setmDatas(datas);
            }
            myTeamDetailsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void destroyView() {

    }
}

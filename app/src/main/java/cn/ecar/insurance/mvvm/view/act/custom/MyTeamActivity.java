package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.MyInsuranceAdapter;
import cn.ecar.insurance.adapter.list.MyTeamAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Insurance;
import cn.ecar.insurance.dao.bean.Team;
import cn.ecar.insurance.databinding.ActivityMyInsuranceBinding;
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

    CustomViewModel mCustomViewModel;


    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_team;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("我的保单");
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        mCustomViewModel.getMyTeamList().observe(this, teamGson -> {
            if (teamGson != null && XdConfig.RESPONSE_T.equals(teamGson.getResponseCode())) {
                mVB.listViewMyTeam.setAdapter(
                        new MyTeamAdapter(mContext, R.layout.item_list_my_team, teamGson.getData())
                );
                mVB.listViewMyTeam.setOnItemClickListener((parent, view, position, id) -> {
                    //
                    Team team = (Team) mVB.listViewMyTeam.getAdapter().getItem(position);
                    new IntentUtils.Builder(mContext)
                            .setParcelableExtra(XdConfig.EXTRA_VALUE, team)
                            .setTargetActivity(InsuranceDetailsActivity.class)
                            .build()
                            .startActivity(true);
                });
            } else {
                ToastUtils.showToast("获取保单失败");
            }
        });
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

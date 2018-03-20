package cn.ecar.insurance.mvvm.view.frag;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.recycler.RewardAdapter;
import cn.ecar.insurance.dao.bean.CustomerHeroBean;
import cn.ecar.insurance.databinding.FragmentListBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.mvvm.viewmodel.main.HomeViewModel;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author yitouwushui
 */
public class ListFragment extends BaseBindingFragment<FragmentListBinding> {

    private HomeViewModel mHomeViewModel;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        mVB.rcyViewReward.setLayoutManager(new LinearLayoutManager(
                mContext, LinearLayoutManager.VERTICAL, false
        ));
    }

    @Override
    protected void initData() {
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        mHomeViewModel.getCustomerShowList().observe(this, customerHeroBeans -> {
            showData(customerHeroBeans);
        });
    }

    private void showData(List<CustomerHeroBean> dataList) {
        CustomerHeroBean customerHeroBean1 = null, customerHeroBean2 = null, customerHeroBean3 = null;
        int length = dataList.size();
        if (length >= 3) {
            customerHeroBean1 = dataList.get(0);
            customerHeroBean2 = dataList.get(1);
            customerHeroBean3 = dataList.get(2);
            mVB.heroLItem1.setVisibility(View.VISIBLE);
            mVB.heroLItem2.setVisibility(View.VISIBLE);
            mVB.heroLItem3.setVisibility(View.VISIBLE);
        } else if (length >= 2) {
            customerHeroBean1 = dataList.get(0);
            customerHeroBean2 = dataList.get(1);
            mVB.heroLItem1.setVisibility(View.VISIBLE);
            mVB.heroLItem2.setVisibility(View.VISIBLE);
        } else if (length == 1) {
            customerHeroBean1 = dataList.get(0);
            mVB.heroLItem1.setVisibility(View.VISIBLE);
        }

        if (customerHeroBean1 != null) {
            mVB.heroTvName1.setText(customerHeroBean1.getCustomerCode());
            mVB.heroTvShareNumber1.setText("分享人数" + customerHeroBean1.getShareTimes() + "位");
            CustomerHeroBean.CustomerPrizeRuleBean prizeRule = customerHeroBean1.getCustomerPrizeRule();
            if (prizeRule != null) {
                mVB.heroTvReward1.setText(prizeRule.getPrizeName());
            }
        }

        if (customerHeroBean2 != null) {
            mVB.heroTvName2.setText(customerHeroBean2.getCustomerCode());
            mVB.heroTvShareNumber2.setText("分享人数" + customerHeroBean2.getShareTimes() + "位");
            CustomerHeroBean.CustomerPrizeRuleBean prizeRule = customerHeroBean2.getCustomerPrizeRule();
            if (prizeRule != null) {
                mVB.heroTvReward2.setText(prizeRule.getPrizeName());
            }
        }

        if (customerHeroBean3 != null) {
            mVB.heroTvName3.setText(customerHeroBean3.getCustomerCode());
            mVB.heroTvShareNumber3.setText("分享人数" + customerHeroBean3.getShareTimes() + "位");
            CustomerHeroBean.CustomerPrizeRuleBean prizeRule = customerHeroBean3.getCustomerPrizeRule();
            if (prizeRule != null) {
                mVB.heroTvReward3.setText(prizeRule.getPrizeName());
            }
        }
        if (length < 4) {
            return;
        }
        ArrayList<CustomerHeroBean> newList = new ArrayList<>(length - 3);
        for (int i = 3; i < length; i++) {
            newList.add(dataList.get(i));
        }
        mVB.rcyViewReward.setAdapter(new RewardAdapter(mContext, R.layout.item_list_reward, newList));
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }
}

package cn.ecar.insurance.mvvm.view.frag;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.recycler.RewardAdapter;
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

        mHomeViewModel.getCustomerShowList().observe(this, customers -> {
            mVB.rcyViewReward
                    .setAdapter(
                            new RewardAdapter(mContext, R.layout.item_list_reward, customers)
                    );
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }
}

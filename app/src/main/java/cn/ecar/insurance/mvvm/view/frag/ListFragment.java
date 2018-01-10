package cn.ecar.insurance.mvvm.view.frag;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.RewardAdapter;
import cn.ecar.insurance.databinding.FragmentListBinding;
import cn.ecar.insurance.entity.Member;
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

        mHomeViewModel.getNewsString().observe(this, members -> {
            mVB.rcyViewReward
                    .setAdapter(
                            new RewardAdapter(mContext, R.layout.item_list_reward, members)
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

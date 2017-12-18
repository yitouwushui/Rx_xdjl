package cn.ecar.insurance.xdjl.mvvm.view.frag;


import android.arch.lifecycle.ViewModelProviders;

import cn.ecar.insurance.xdjl.R;
import cn.ecar.insurance.xdjl.databinding.FragmentHomeBinding;
import cn.ecar.insurance.xdjl.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.xdjl.mvvm.viewmodel.custom.CustomViewModel;


/**
 * @author yitouwushui
 */
public class HomeFragment extends BaseBindingFragment<FragmentHomeBinding> {


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        CustomViewModel customViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        customViewModel.getBase();
        customViewModel.getBaiDu().observe(this, str -> {
            assert str != null;

        });

        mVB.btSign.setText("签到2");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }

}

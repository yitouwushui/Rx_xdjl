package cn.ecar.insurance.mvvm.view.frag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.FragmentMeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseBindingFragment<FragmentMeBinding> {


    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }

}

package cn.ecar.insurance.xdjl.mvvm.view.frag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ecar.insurance.xdjl.R;
import cn.ecar.insurance.xdjl.databinding.FragmentListBinding;
import cn.ecar.insurance.xdjl.mvvm.base.BaseBindingFragment;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author yitouwushui
 */
public class ListFragment extends BaseBindingFragment<FragmentListBinding> {


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
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

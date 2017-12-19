package cn.ecar.insurance.mvvm.view.frag;


import android.support.v4.app.Fragment;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.FragmentListBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;


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

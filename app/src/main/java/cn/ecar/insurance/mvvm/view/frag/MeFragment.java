package cn.ecar.insurance.mvvm.view.frag;


import android.support.v4.app.Fragment;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.databinding.FragmentMeBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.mvvm.view.act.login.LoginActivity;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseBindingFragment<FragmentMeBinding> implements OnViewClick {


    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        Customer customer = SpUtils.getData(Customer.class);
        if (customer != null){
            mVB.tvAccount.setText(customer.getPhoneNo());
            mVB.tvInviteCode.setText(customer.getCustomerCode());
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btOutMoney, this);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_out_money:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(LoginActivity.class)
                        .build()
                        .startActivity(true);
                break;
            default:
        }
    }
}

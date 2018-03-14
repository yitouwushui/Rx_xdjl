package cn.ecar.insurance.mvvm.view.frag;


import android.support.v4.app.Fragment;
import android.view.View;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.FragmentMemberBinding;
import cn.ecar.insurance.mvvm.view.act.pay.RechargeActivity;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author ding
 */
public class MemberFragment extends BaseBindingFragment<FragmentMemberBinding> implements OnViewClick {

    private Customer customer;
    public MemberFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_member;
    }

    @Override
    protected void initView() {
        customer = SpUtils.getData(Customer.class);
        if (customer != null) {
            mVB.tvAccount.setText("账号  "+customer.getPhoneNo());
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btBuyMember, this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_buy_member:
                new IntentUtils.Builder(mContext)
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, "599")
                        .setTargetActivity(RechargeActivity.class)
                        .build()
                        .startActivity(true);
                break;
            default:
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){

        }
    }

    @Override
    protected void destroyView() {

    }

}

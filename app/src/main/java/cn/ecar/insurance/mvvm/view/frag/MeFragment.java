package cn.ecar.insurance.mvvm.view.frag;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.view.View;

import com.orhanobut.logger.Logger;

import cn.ecar.insurance.R;
import cn.ecar.insurance.dao.bean.CashAccount;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.databinding.FragmentMeBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.mvvm.view.act.custom.AddressActivity;
import cn.ecar.insurance.mvvm.view.act.custom.FrozenCashActivity;
import cn.ecar.insurance.mvvm.view.act.custom.FundsActivity;
import cn.ecar.insurance.mvvm.view.act.custom.MyInsuranceActivity;
import cn.ecar.insurance.mvvm.view.act.custom.MySignInActivity;
import cn.ecar.insurance.mvvm.view.act.custom.MyTeamActivity;
import cn.ecar.insurance.mvvm.view.act.custom.PersonalActivity;
import cn.ecar.insurance.mvvm.view.act.custom.SettingActivity;
import cn.ecar.insurance.mvvm.view.act.pay.InformationActivity;
import cn.ecar.insurance.mvvm.view.act.pay.WithdrawActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author yitouwushui
 */
public class MeFragment extends BaseBindingFragment<FragmentMeBinding> implements OnViewClick {

    private CustomViewModel mCustomViewModel;

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
        if (customer != null) {
            mVB.tvAccount.setText(customer.getPhoneNo());
            mVB.tvInviteCode.setText(customer.getCustomerCode());
        }
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        mCustomViewModel.getCustomerAllInfo().observe(this, customerGson -> {
            Customer customer = customerGson.getCustomerInfo();
            CashAccount cashAccount = customer.getCashAccountDto();
            mVB.tvAccountMoney.setText(String.valueOf(cashAccount.getBalance()));
            mVB.tvFrozenFund.setText(String.valueOf(cashAccount.getFrozenBalance()));
            mVB.tvAccount.setText(customer.getPhoneNo());
            mVB.tvInviteCode.setText(customer.getCustomerCode());
            SpUtils.putData(customer);
            SpUtils.putData(cashAccount);
        });

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btAddress, 1, this);
        RxViewUtils.onViewClick(mVB.btOutMoney, 1, this);
        RxViewUtils.onViewClick(mVB.lBtBindCard, 1, this);
        RxViewUtils.onViewClick(mVB.lBtInsurance,1,  this);
        RxViewUtils.onViewClick(mVB.lBtSign,1,  this);
        RxViewUtils.onViewClick(mVB.lBtTeam, 1, this);
        RxViewUtils.onViewClick(mVB.lBtInfo, 1, this);
        RxViewUtils.onViewClick(mVB.lBtFrozenFunds, 1, this);
        RxViewUtils.onViewClick(mVB.lBtFunds, 1, this);
        RxViewUtils.onViewClick(mVB.lBtSetting, 1, this);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_out_money:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(WithdrawActivity.class)
                        .build()
                        .startActivity(true);
                break;
            case R.id.l_bt_bind_card:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(InformationActivity.class)
                        .build()
                        .startActivity(true);
                break;
            case R.id.l_bt_insurance:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(MyInsuranceActivity.class)
                        .build()
                        .startActivity(true);
                break;
            case R.id.l_bt_sign:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(MySignInActivity.class)
                        .build()
                        .startActivity(true);
                break;
            case R.id.l_bt_team:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(MyTeamActivity.class)
                        .build()
                        .startActivity(true);
                break;
            case R.id.l_bt_frozen_funds:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(FrozenCashActivity.class)
                        .build()
                        .startActivity(true);
                break;
            case R.id.l_bt_funds:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(FundsActivity.class)
                        .build()
                        .startActivity(true);
                break;
            case R.id.l_bt_setting:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(SettingActivity.class)
                        .build()
                        .startActivity(true);
                break;
            case R.id.l_bt_info:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(PersonalActivity.class)
                        .build()
                        .startActivity(true);
                break;
            case R.id.bt_address:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(AddressActivity.class)
                        .build()
                        .startActivity(true);
                break;
            default:
        }
    }
}

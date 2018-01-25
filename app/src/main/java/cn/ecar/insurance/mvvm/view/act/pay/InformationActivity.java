package cn.ecar.insurance.mvvm.view.act.pay;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import cn.ecar.insurance.R;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Bank;
import cn.ecar.insurance.databinding.ActivityInformationBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.main.ChoiceActivity;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * 2018/1/25
 *
 * @author ding
 */
public class InformationActivity extends BaseBindingActivity<ActivityInformationBinding> implements OnViewClick {

    private String bankCode = "";
    ArrayList<Bank> bankList = new ArrayList<>();

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_information;
    }

    @Override
    protected void initView() {
        Bank bank1 = new Bank();
        bank1.setBankCode("1");
        bank1.setBankName("2");
        Bank bank2 = new Bank();
        bank2.setBankCode("1");
        bank2.setBankName("2");
        bankList.add(bank1);
        bankList.add(bank2);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btSubmit, this);
        RxViewUtils.onViewClick(mVB.lBtBank, this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                String code = bankCode;
                String bankCard = mVB.etBankCard.getText().toString();
                String customerName = mVB.tvCustomerName.getText().toString();
                String identification = mVB.etIdentification.getText().toString();
                if ("".equals(code)) {
                    ToastUtils.showToast("请选择银行");
                } else if ("".equals(bankCard)) {
                    ToastUtils.showToast("银行卡不能为空");
                } else if ("".equals(customerName)) {
                    ToastUtils.showToast("姓名不能为空");
                } else if ("".equals(identification)) {
                    ToastUtils.showToast("身份证不能为空");
                } else {

                }
                break;
            case R.id.l_bt_bank:
                Bundle bundle = new Bundle();
                bundle.putSerializable(XdConfig.EXTRA_CLASS_VALUE, Bank.class);
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(ChoiceActivity.class)
                        .setParcelableArrayLsitExtra(XdConfig.EXTRA_ARRAY_VALUE, bankList)
                        .setBundleExtra(XdConfig.EXTRA_BUNDLE, bundle)
                        .build().startActivityForResult(XdConfig.SELECT_REQUEST);
                break;
            default:
        }
    }

    @Override
    protected void destroyView() {

    }

}

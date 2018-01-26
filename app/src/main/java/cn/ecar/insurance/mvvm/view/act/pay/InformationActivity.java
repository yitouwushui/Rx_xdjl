package cn.ecar.insurance.mvvm.view.act.pay;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Bank;
import cn.ecar.insurance.dao.bean.Province;
import cn.ecar.insurance.databinding.ActivityInformationBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.main.ChoiceActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.PayViewModel;
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

    //    private String bankCode = "";
    ArrayList<Bank> bankList = new ArrayList<>();
    ArrayList<Province> provinces = new ArrayList<>();
    private PayViewModel mPayViewModel;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_information;
    }

    @Override
    protected void initView() {
        mVB.lBtProvince.setEnabled(false);

    }

    @Override
    protected void initData() {
        mPayViewModel = ViewModelProviders.of(this).get(PayViewModel.class);
        getProvinceList();
    }

    /**
     * 获取省份
     */
    private void getProvinceList() {
        showWaitDialog();
        mPayViewModel.getProvinceList().observe(this, provinceGson -> {
            if (provinceGson != null && XdConfig.RESPONSE_T.equals(provinceGson.getResponseCode())) {
                mVB.lBtProvince.setEnabled(true);
                provinces.addAll(provinceGson.getProvinceList());
            } else {
                ToastUtils.showToast("查询省份失败,请点击选择重新请求");
            }
            hideWaitDialog();
        });
    }


    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btSubmit, this);
        RxViewUtils.onViewClick(mVB.lBtBank, this);
        RxViewUtils.onViewClick(mVB.lBtProvince, this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                String code = mVB.tvBankCode.getText().toString();
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
                        .build().startActivityForResult(XdConfig.SELECT_BANK_REQUEST);
                break;
            case R.id.l_bt_province:
                if (provinces.isEmpty()) {
                    getProvinceList();
                } else {
                    Bundle province = new Bundle();
                    province.putSerializable(XdConfig.EXTRA_CLASS_VALUE, Province.class);
                    new IntentUtils.Builder(mContext)
                            .setTargetActivity(ChoiceActivity.class)
                            .setParcelableArrayLsitExtra(XdConfig.EXTRA_ARRAY_VALUE, provinces)
                            .setBundleExtra(XdConfig.EXTRA_BUNDLE, province)
                            .build().startActivityForResult(XdConfig.SELECT_PROVINCE_REQUEST);
                }
                break;
            default:
        }
    }

    @Override
    protected void destroyView() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case XdConfig.SELECT_PROVINCE_REQUEST:
                if (resultCode == IntentUtils.RESULT_CODE_S) {
                    Province province = data.getParcelableExtra(XdConfig.EXTRA_VALUE);
                    if (province == null) {
                        ToastUtils.showToast("选择失败");
                        break;
                    }
                    mVB.tvProvinceCode.setText(province.getCode());
                    mVB.tvProvinceName.setText(province.getName());
//                    int position = data.getIntExtra(XdConfig.EXTRA_INT_VALUE, -1);
//                    if (position == -1) {
//                        ToastUtils.showToast("选择失败");
//                        break;
//                    }
//                    if (provinces.size() > position) {
//                        province = provinces.get(position);
//                        mVB.tvProvinceCode.setText(province.getCode());
//                        mVB.tvProvinceName.setText(province.getName());
//                    }
                }
                break;
            default:
        }
    }
}

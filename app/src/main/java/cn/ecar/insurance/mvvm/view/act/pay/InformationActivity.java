package cn.ecar.insurance.mvvm.view.act.pay;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Bank;
import cn.ecar.insurance.dao.bean.BranchBank;
import cn.ecar.insurance.dao.bean.City;
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

    /**
     * 分行列表
     */
    ArrayList<BranchBank> branchBankList = new ArrayList<>();
    /**
     * 银行列表
     */
    ArrayList<Bank> bankList = new ArrayList<>();
    /**
     * 省份列表
     */
    ArrayList<Province> provinces = new ArrayList<>();
    /**
     * 城市列表
     */
    ArrayList<City> citys = new ArrayList<>();
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
        mVB.viewTitle.setTitle("绑定银行卡");
        mVB.lBtProvince.setEnabled(false);
        mVB.lBtCity.setEnabled(false);
        mVB.lBtBank.setEnabled(false);
//        mVB.lBtBranch.setEnabled(false);

    }

    @Override
    protected void initData() {
        mPayViewModel = ViewModelProviders.of(this).get(PayViewModel.class);
        getProvinceList();
        getBankList();
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


    /**
     * 获取城市
     * getCityListProvinceCode
     */
    private void getCityListProvinceCode(String provinceCode) {
        showWaitDialog();
        mVB.lBtCity.setEnabled(false);
        mPayViewModel.getCityListProvinceCode(provinceCode).observe(this, cityGson -> {
            if (cityGson != null && XdConfig.RESPONSE_T.equals(cityGson.getResponseCode())) {
                mVB.lBtCity.setEnabled(true);
                citys.clear();
                citys.addAll(cityGson.getCityList());
            } else {
                ToastUtils.showToast("查询城市失败,请点击选择重新请求");
            }
            mVB.lBtCity.setEnabled(true);
            hideWaitDialog();
        });
    }

    /**
     * 获取银行
     * getCityListProvinceCode
     */
    private void getBankList() {
        mVB.lBtBank.setEnabled(false);
        mPayViewModel.getBankList().observe(this, bankGson -> {
            if (bankGson != null && XdConfig.RESPONSE_T.equals(bankGson.getResponseCode())) {
                mVB.lBtCity.setEnabled(true);
                bankList.addAll(bankGson.getBankcodeSmartpayDtoList());
            } else {
                ToastUtils.showToast("查询银行信息失败,请点击选择重新请求");
            }
            mVB.lBtBank.setEnabled(true);
        });
    }

    /**
     * 获取分行列表
     * getCityListProvinceCode
     */
    private void getBranchBankList(String bankCode, String cityCode) {
        showWaitDialog();
        mVB.lBtBranch.setEnabled(false);
        mPayViewModel.getBranchBankList(bankCode, cityCode).observe(this, bankGson -> {
            if (bankGson != null && XdConfig.RESPONSE_T.equals(bankGson.getResponseCode())) {
                mVB.lBtCity.setEnabled(true);
                branchBankList.clear();
                branchBankList.addAll(bankGson.getBankNumberDtoList());
                startBranchSelect();
            } else {
                ToastUtils.showToast("查询银行分行信息失败,请点击选择重新请求");
            }
            mVB.lBtBranch.setEnabled(true);
            hideWaitDialog();
        });
    }

    /**
     * 获取分行列表
     * getCityListProvinceCode
     */
    private void submitBindBank() {
        String bankId = mVB.tvBranchBankId.getText().toString();
        String branchName = mVB.tvBranchName.getText().toString();
        String branchNo = mVB.tvBranchCode.getText().toString();
        String bankCardNo = mVB.etBankCard.getText().toString();
        String name = mVB.tvCustomerName.getText().toString();
        String certificateCode = mVB.etIdentification.getText().toString();
        if ("".equals(bankId) || "".equals(branchNo) || branchName.equals("")) {
            ToastUtils.showToast("请选择银行分行");
        } else if ("".equals(bankCardNo)) {
            ToastUtils.showToast("银行卡不能为空");
        } else if ("".equals(name)) {
            ToastUtils.showToast("姓名不能为空");
        } else if ("".equals(certificateCode)) {
            ToastUtils.showToast("身份证不能为空");
        } else {
            HashMap<String, String> hashMap = new HashMap<>(6);
            hashMap.put("bankId", bankId);
            hashMap.put("branchName", branchName);
            hashMap.put("branchNo", branchNo);
            hashMap.put("bankCardNo", bankCardNo);
            hashMap.put("name", name);
            hashMap.put("certificateCode", certificateCode);
            showWaitDialog();
            mVB.btSubmit.setEnabled(false);
            mPayViewModel.bindBank(hashMap).observe(this, baseGson -> {
                mVB.btSubmit.setEnabled(true);
                hideWaitDialog();
                if (baseGson != null && XdConfig.RESPONSE_T.equals(baseGson.getResponseCode())) {
                    ToastUtils.showToast(baseGson.getResponseMsg());
                    finishActivity();
                } else {
                    ToastUtils.showToast(baseGson.getResponseMsg());
                }
            });
        }
    }


    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btSubmit, this);
        RxViewUtils.onViewClick(mVB.lBtBank, this);
        RxViewUtils.onViewClick(mVB.lBtBranch, this);
        RxViewUtils.onViewClick(mVB.lBtProvince, this);
        RxViewUtils.onViewClick(mVB.lBtCity, this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                submitBindBank();
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
            case R.id.l_bt_city:
                String codeProvince = mVB.tvProvinceCode.getText().toString();
                if ("".equals(codeProvince)) {
                    ToastUtils.showToast("请先选择省份");
                    break;
                }
                if (citys.isEmpty()) {
                    getCityListProvinceCode(codeProvince);
                    break;
                }
                Bundle city = new Bundle();
                city.putSerializable(XdConfig.EXTRA_CLASS_VALUE, City.class);
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(ChoiceActivity.class)
                        .setParcelableArrayLsitExtra(XdConfig.EXTRA_ARRAY_VALUE, citys)
                        .setBundleExtra(XdConfig.EXTRA_BUNDLE, city)
                        .build().startActivityForResult(XdConfig.SELECT_CITY_REQUEST);
                break;
            case R.id.l_bt_bank:
                if (bankList.isEmpty()) {
                    getBankList();
                    break;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable(XdConfig.EXTRA_CLASS_VALUE, Bank.class);
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(ChoiceActivity.class)
                        .setParcelableArrayLsitExtra(XdConfig.EXTRA_ARRAY_VALUE, bankList)
                        .setBundleExtra(XdConfig.EXTRA_BUNDLE, bundle)
                        .build().startActivityForResult(XdConfig.SELECT_BANK_REQUEST);
                break;
            case R.id.l_bt_branch:
                String cityCode = mVB.tvCityCode.getText().toString();
                String bankCode = mVB.tvBankCode.getText().toString();
                if ("".equals(bankCode)) {
                    ToastUtils.showToast("请先选择省份");
                    break;
                }
                if ("".equals(cityCode)) {
                    ToastUtils.showToast("请先选择城市");
                    break;
                }
                if (branchBankList.isEmpty()) {
                    getBranchBankList(bankCode, cityCode);
                    break;
                }
                startBranchSelect();
                break;
            default:
        }
    }

    /**
     * 启动支行选择
     */
    private void startBranchSelect() {
        Bundle branchBank = new Bundle();
        branchBank.putSerializable(XdConfig.EXTRA_CLASS_VALUE, BranchBank.class);
        new IntentUtils.Builder(mContext)
                .setTargetActivity(ChoiceActivity.class)
                .setParcelableArrayLsitExtra(XdConfig.EXTRA_ARRAY_VALUE, branchBankList)
                .setBundleExtra(XdConfig.EXTRA_BUNDLE, branchBank)
                .build().startActivityForResult(XdConfig.SELECT_BRANCH_BANK_REQUEST);
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
                        ToastUtils.showToast("选择省份失败");
                        break;
                    }
                    mVB.tvProvinceCode.setText(province.getCode());
                    mVB.tvProvinceName.setText(province.getName());
                    getCityListProvinceCode(province.getCode());
                }
                break;
            case XdConfig.SELECT_CITY_REQUEST:
                if (resultCode == IntentUtils.RESULT_CODE_S) {
                    City city = data.getParcelableExtra(XdConfig.EXTRA_VALUE);
                    if (city == null) {
                        ToastUtils.showToast("选择城市失败");
                        break;
                    }
                    mVB.tvCityCode.setText(city.getCode());
                    mVB.tvCityName.setText(city.getName());
                    branchBankList.clear();
                }
                break;
            case XdConfig.SELECT_BANK_REQUEST:
                if (resultCode == IntentUtils.RESULT_CODE_S) {
                    Bank bank = data.getParcelableExtra(XdConfig.EXTRA_VALUE);
                    if (bank == null) {
                        ToastUtils.showToast("选择银行失败");
                        break;
                    }
                    mVB.tvBankCode.setText(bank.getBankNo());
                    mVB.tvBankName.setText(bank.getBankName());
                    branchBankList.clear();
                }
                break;
            case XdConfig.SELECT_BRANCH_BANK_REQUEST:
                if (resultCode == IntentUtils.RESULT_CODE_S) {
                    BranchBank branchBank = data.getParcelableExtra(XdConfig.EXTRA_VALUE);
                    if (branchBank == null) {
                        ToastUtils.showToast("选择分行失败");
                        break;
                    }
                    mVB.tvBranchCode.setText(branchBank.getBranchNo());
                    mVB.tvBranchName.setText(branchBank.getBranchName());
                    mVB.tvBranchBankId.setText(String.valueOf(branchBank.getBankId()));
                }
                break;
            default:
        }
    }
}

package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import java.util.Map;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.CustomerAddress;
import cn.ecar.insurance.dao.gson.AddressGson;
import cn.ecar.insurance.databinding.ActivityModifyAddressBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.encrypt.MD5Helper;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 * @date 2018/2/2
 */

public class ModifyAddressActivity extends BaseBindingActivity<ActivityModifyAddressBinding> implements OnViewClick {

    private CustomViewModel mCustomViewModel;
    private CustomerAddress customerAddress;
    private boolean isAdd;
    private int position;

    @Override
    public void getBundleExtras(Bundle extras) {
        customerAddress = extras.getParcelable(XdConfig.EXTRA_VALUE);
        int requestCode = extras.getInt(XdConfig.EXTRA_REQUEST_VALUE);
        if (requestCode == XdConfig.ADDRESS_IS_ADD_REQUEST) {
            isAdd = true;
        } else {
            position = extras.getInt(XdConfig.EXTRA_INT_VALUE);
            isAdd = false;
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_modify_address;
    }

    @Override
    protected void initView() {
        if (isAdd) {
            mVB.includeToolbar.textTitle.setText("新增地址");
        } else {
            mVB.includeToolbar.textTitle.setText("编辑地址");
        }
        if (customerAddress != null) {
            mVB.etName.setText(customerAddress.getReceiver());
            mVB.etPhone.setText(customerAddress.getPhoneNo());
            mVB.etRegion.setText(customerAddress.getRegion());
            mVB.etAddress.setText(customerAddress.getAddress());
            mVB.ckIsDefault.setChecked("1".equals(customerAddress.getIsDefault()));
        }
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btSubmit, 1, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                try {
                    String receiver = mVB.etName.getText().toString();
                    String phoneNo = mVB.etPhone.getText().toString();
                    String region = mVB.etRegion.getText().toString();
                    String address = mVB.etAddress.getText().toString();
                    String isDefault = mVB.ckIsDefault.isChecked() ? "1" : "0";
                    Map<String, String> map = RetrofitUtils.getInstance().getParamsMap(
                            "receiver", receiver,
                            "phoneNo", phoneNo,
                            "region", region,
                            "address", address,
                            "isDefault", isDefault
                    );
                    if (customerAddress != null) {
                        // 如果是修改的话，还需要传地址id
                        map.put("addressId", String.valueOf(customerAddress.getAddressId()));
                        map.put("customerId", String.valueOf(customerAddress.getCustomerId()));
                    }
                    map.put("version", OtherUtil.getVersionName(mContext));
                    map.put("timestamp", String.valueOf(System.currentTimeMillis()));
                    map.put("appId", XdConfig.APP_ID);
                    String sign = MD5Helper.getSign(map, XdConfig.APP_SECRET, "UTF-8");
                    map.put("sign", sign);
                    showWaitDialog();
                    if (isAdd) {
                        mCustomViewModel.saveAddress(map).observe(this, addressGson -> {
                            getCustomerAddress(addressGson);
                            hideWaitDialog();
                        });
                    } else {
                        mCustomViewModel.updateAddress(map).observe(this, addressGson -> {
                            getCustomerAddress(addressGson);
                            hideWaitDialog();
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }

    /**
     * 处理返回的数据
     *
     * @param addressGson
     */
    private void getCustomerAddress(AddressGson addressGson) {
        if (addressGson == null) {
            ToastUtils.showToast("请求失败,请检查网络");
            return;
        }
        if (XdConfig.RESPONSE_T.equals(addressGson.getResponseCode())) {
            CustomerAddress customerAddress = addressGson.getCustomerAddressDto();
            if (customerAddress == null) {
                ToastUtils.showToast("返回地址为null");
                return;
            }
            new IntentUtils.Builder(mContext)
                    .setParcelableExtra(XdConfig.EXTRA_VALUE, customerAddress)
                    .setIntExtra(XdConfig.EXTRA_INT_VALUE, position)
                    .build()
                    .setResultOkWithFinishUi();
        } else {
            ToastUtils.showToast(addressGson.getResponseMsg());
        }
    }

    @Override
    protected void destroyView() {

    }
}

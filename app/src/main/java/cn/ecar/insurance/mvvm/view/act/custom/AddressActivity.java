package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.CustomerAddressAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.CustomerAddress;
import cn.ecar.insurance.dao.gson.AddressGson;
import cn.ecar.insurance.databinding.ActicityAddressBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 * @date 2018/2/2
 */

public class AddressActivity extends BaseBindingActivity<ActicityAddressBinding> implements OnViewClick {

    private CustomViewModel mCustomViewModel;
    private CustomerAddressAdapter customerAddressAdapter;
    private int requestCode = 0;

    @Override
    public void getBundleExtras(Bundle extras) {
        requestCode = extras.getInt(XdConfig.EXTRA_INT_VALUE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.acticity_address;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("地址列表");
        mVB.includeToolbar.textRightTitle.setText("添加地址");
        mVB.includeToolbar.textRightTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        customerAddressAdapter = new CustomerAddressAdapter(mContext, R.layout.me_item_address, new ArrayList<>());
        mVB.listViewAddress.setAdapter(customerAddressAdapter);
        if (requestCode == XdConfig.SELECT_ADDRESS_REQUEST) {
            mVB.listViewAddress.setOnItemClickListener((parent, view, position, id) -> {
                CustomerAddress customerAddress = (CustomerAddress) parent.getAdapter().getItem(position);
                new IntentUtils.Builder(mContext)
                        .setParcelableExtra(XdConfig.EXTRA_VALUE, customerAddress)
                        .build()
                        .setResultOkWithFinishUi();
            });
        }
        mCustomViewModel.getCustomerAddressList().observe(this, addressGson -> {
            try {
                if (XdConfig.RESPONSE_T.equals(addressGson.getResponseCode())) {
                    showData(addressGson.getCustomerAddressDtoList());
                } else {
                    ToastUtils.showToast(addressGson.getResponseMsg());
                }
            } catch (Exception e) {
                ToastUtils.showToast("获取地址列表失败");
            }
        });
    }

    /**
     * 展示数据
     *
     * @param addressList
     */
    private void showData(List<CustomerAddress> addressList) {
        customerAddressAdapter.setmDatas(addressList);
        customerAddressAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.includeToolbar.textRightTitle, 1, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_right_title:
                new IntentUtils.Builder(mContext)
                        .setIntExtra(XdConfig.EXTRA_REQUEST_VALUE, XdConfig.ADDRESS_IS_ADD_REQUEST)
                        .setTargetActivity(ModifyAddressActivity.class)
                        .build()
                        .startActivityForResult(XdConfig.ADDRESS_IS_ADD_REQUEST);
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
            case XdConfig.ADDRESS_IS_ADD_REQUEST:
                if (resultCode == IntentUtils.RESULT_CODE_S) {
                    CustomerAddress customerAddress = data.getParcelableExtra(XdConfig.EXTRA_VALUE);
                    customerAddressAdapter.addmDatas(customerAddress);
                    customerAddressAdapter.notifyDataSetChanged();
                }
                break;
            //
            case XdConfig.ADDRESS_IS_UPDATE_REQUEST:
                if (resultCode == IntentUtils.RESULT_CODE_S) {
                    CustomerAddress customerAddress = data.getParcelableExtra(XdConfig.EXTRA_VALUE);
                    int position = data.getIntExtra(XdConfig.EXTRA_INT_VALUE, -1);
                    if (position != -1) {
                        customerAddressAdapter.setSingleDate(customerAddress, position);
                        customerAddressAdapter.notifyDataSetChanged();
                    }
                }
                break;
            default:

        }
    }

}

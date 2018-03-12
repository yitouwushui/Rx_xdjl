package cn.ecar.insurance.mvvm.view.act.custom;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.InsuranceOrderListAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.InsuranceOrderType;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.databinding.ActivityInsuranceDetailBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.insurance.InsureActivity6;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.mvvm.viewmodel.custom.InsuranceViewModel;
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

public class InsuranceDetailsActivity extends BaseBindingActivity<ActivityInsuranceDetailBinding> implements OnViewClick {

    /**
     * 保单
     */
    private String orderNo = "";
    private InsuranceViewModel mInsuranceViewModel;
    private OrderBean orderBean;

    @Override
    public void getBundleExtras(Bundle extras) {
        orderNo = extras.getString(XdConfig.EXTRA_STRING_VALUE);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insurance_detail;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("保单详情");
    }

    @Override
    protected void initData() {
        mInsuranceViewModel = ViewModelProviders.of(this).get(InsuranceViewModel.class);
        getOrderDeatil(orderNo);
    }

    @Override
    protected void initEvent() {

    }


    /**
     * 获取保单价格
     *
     * @param orderNo
     */
    public void getOrderDeatil(String orderNo) {
        Map<String, String> map = RetrofitUtils.getInstance().getParamsMap(
                "version", OtherUtil.getVersionName(getApplicationContext()),
                "appId", XdConfig.APP_ID,
                "orderNo", orderNo,
                "timestamp", String.valueOf(System.currentTimeMillis()));
        String sign = null;
        try {
            sign = MD5Helper.getSign(map, XdConfig.APP_SECRET, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("sign", sign);
        mInsuranceViewModel.getInsuranceOrderDeatil(map).observe(this, orderListGson -> {
            if (orderListGson != null && XdConfig.RESPONSE_T.equals(orderListGson.getResponseCode())) {
                orderBean = orderListGson.getInsuranceOrderDto();
                List<InsuranceOrderType> orderTypeArrayList = orderListGson.getInsuranceOrderDetailDtoList();
                showData(orderBean, orderTypeArrayList);
            } else {
                ToastUtils.showToast("获取保单失败，请检查网络");
            }

        });
    }

    /**
     * 展示数据
     *
     * @param orderBean
     * @param orderTypeArrayList
     */
    private void showData(OrderBean orderBean, List<InsuranceOrderType> orderTypeArrayList) {
        mVB.listViewBusiness.setAdapter(new InsuranceOrderListAdapter(mContext, R.layout.insure_item_list_business, orderTypeArrayList));
        mVB.listViewBusiness.setOnTouchListener((v, event) -> true);
        mVB.listViewBusiness.setDividerHeight(0);
        mVB.tvCarNumber.setText(orderBean.getCarNumber());
        mVB.tvTotalForcetax.setText("¥" + String.valueOf(orderBean.getTotalForcetax()));
        mVB.tvForceInsurance.setText("¥" + String.valueOf(orderBean.getForceInsurance()));
        mVB.tvVehicleTax.setText("¥" + String.valueOf(orderBean.getVehicleTax()));
        mVB.tvTotalBusiness.setText("¥" + orderBean.getTotalBusiness());
        mVB.tvTotalAmount.setText("总额:¥" + orderBean.getTotalAmount());

    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
    }
}

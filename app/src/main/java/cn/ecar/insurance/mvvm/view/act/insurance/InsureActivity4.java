package cn.ecar.insurance.mvvm.view.act.insurance;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.BuyInsuranceListAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.databinding.ActivityInsure4Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.InsuranceViewModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.encrypt.MD5Helper;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;

/**
 * @author ding
 */
public class InsureActivity4 extends BaseBindingActivity<ActivityInsure4Binding> implements OnViewClick {

    private ArrayList<OrderBean> orderBeans;
    private String licenseNo = "";
    private String orderNoes = "";
    private InsuranceViewModel mInsuranceViewModel;
    private boolean[] getPrice;
    private Map<String, Integer> orderIndexOfListMap = new ArrayMap<>();
    private BuyInsuranceListAdapter buyInsuranceListAdapter;
    private int count = 0;
    private TimeHandle mTimeHandle;

    @Override
    public void getBundleExtras(Bundle extras) {
        orderBeans = extras.getParcelableArrayList(XdConfig.EXTRA_ARRAY_VALUE);
        licenseNo = extras.getString("LicenseNo");
        getPrice = new boolean[orderBeans.size()];
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure4;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("车险方案");
        mTimeHandle = new TimeHandle(this);
    }

    @Override
    protected void initData() {
        mInsuranceViewModel = ViewModelProviders.of(this).get(InsuranceViewModel.class);
        buyInsuranceListAdapter = new BuyInsuranceListAdapter(mContext, R.layout.item_list_price, orderBeans);
        mVB.listPrice.setAdapter(buyInsuranceListAdapter);
        StringBuilder sb = new StringBuilder();
        for (int i = 0, len = orderBeans.size(); i < len; i++) {
            String orderNo = orderBeans.get(i).getOrderNo();

            // 记录订单在list中的位置
            orderIndexOfListMap.put(orderNo, i);

            // 拼接
            sb.append(orderNo);
            if (i != len - 1) {
                sb.append(",");
            }
        }
        orderNoes = sb.toString();

        submitOrderArray();
    }

    /**
     * 提交需要查询的订单
     */
    private void submitOrderArray() {
        Map<String, String> map = RetrofitUtils.getInstance().getParamsMap(
                "LicenseNo", licenseNo,
                "orderNoes", orderNoes,
                "version", OtherUtil.getVersionName(getApplicationContext()),
                "timestamp", String.valueOf(System.currentTimeMillis()),
                "appId", XdConfig.APP_ID);
        String sign = null;
        try {
            sign = MD5Helper.getSign(map, XdConfig.APP_SECRET, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("sign", sign);
        mInsuranceViewModel.getInsuranceOrderPrice(map).observe(this, orderListGson -> {

            if (orderListGson != null && XdConfig.RESPONSE_T.equals(orderListGson.getResponseCode())) {
                mTimeHandle.postDelayed(() -> {
                    // 请求所有报价
                    getAllOrderPrice();
                }, 1000);
            } else {
                if (count <= 3) {
                    ToastUtils.showToast("查询价格失败,请检查网络");
                    submitOrderArray();
                    count++;
                } else {
                    ToastUtils.showToast("查询价格失败,请返回重新请求");
                }
            }
        });
    }

    /**
     * 获取所有保单报价
     */
    private void getAllOrderPrice() {

        for (int i = 0, len = orderBeans.size(); i < len; i++) {
            OrderBean orderBean = orderBeans.get(i);
            String orderNo = orderBean.getOrderNo();
            final int index = orderIndexOfListMap.get(orderNo);

            // 获取单个订单的价格
            getOrderPrice(orderNo, index);
        }
    }

    public void getOrderPrice(String orderNo, int index) {
        // 更新显示
        buyInsuranceListAdapter.getSingleDate(index).setAmountRequest(true);
        buyInsuranceListAdapter.notifyDataSetChanged();
        // 网络请求
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
        mInsuranceViewModel.getInsurancePriceByOrderNo(map).observe(this, orderListGson -> {
            buyInsuranceListAdapter.getSingleDate(index).setAmountRequest(false);
            if (orderListGson != null && XdConfig.RESPONSE_T.equals(orderListGson.getResponseCode())) {
                OrderBean orderBean = orderListGson.getInsuranceOrderDto();
                buyInsuranceListAdapter.setSingleDate(orderBean, index);
            } else {
                ToastUtils.showToast("单个保险价格刷新失败，请点击重新加载");
            }
            buyInsuranceListAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void initEvent() {


    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.bt_next:
//                break;
//            default:
//        }
    }

    public static class TimeHandle extends Handler {
        private WeakReference<Activity> mActivity;

        public TimeHandle(Activity activity) {
            mActivity = new WeakReference<>(activity);
        }
    }
}

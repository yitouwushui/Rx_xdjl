package cn.ecar.insurance.mvvm.view.act.insurance;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.View;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.dao.bean.CustomerAddress;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.dao.bean.UserInfo;
import cn.ecar.insurance.databinding.ActivityInsure6Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.custom.AddressActivity;
import cn.ecar.insurance.mvvm.view.act.login.LoginActivity;
import cn.ecar.insurance.mvvm.view.act.pay.PayActivity;
import cn.ecar.insurance.mvvm.view.act.setting.UploadActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.InsuranceViewModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.encrypt.MD5Helper;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.TimeUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class InsureActivity6 extends BaseBindingActivity<ActivityInsure6Binding> implements OnViewClick {

    private UserInfo userInfo;
    private OrderBean orderBean;
    private ArrayMap<String, String> map;
    private InsuranceViewModel mInsuranceViewModel;

    @Override
    public void getBundleExtras(Bundle extras) {
        orderBean = extras.getParcelable(XdConfig.EXTRA_VALUE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure6;
    }

    @Override
    protected void initView() {


        mVB.includeToolbar.textTitle.setText("车险--确认订单");
        mInsuranceViewModel = ViewModelProviders.of(this).get(InsuranceViewModel.class);
    }

    @Override
    protected void initData() {

        map = new ArrayMap<>();
//        testDate();

        try {
            map.put("orderNo", orderBean.getOrderNo());
            userInfo = UserInfo.getInstance();
            mVB.tvLicenseNo.setText(userInfo.getLicenseno());
            mVB.tvModleName.setText(userInfo.getModlename());
            mVB.tvCarVin.setText(userInfo.getCarvin());
            mVB.tvEngineNo.setText(userInfo.getEngineno());
            mVB.tvLicenseOwner.setText(userInfo.getLicenseowner());
            mVB.tvRegisterDate.setText(TimeUtils.getStringByTime(userInfo.getRegisterdate()));
            mVB.tvBusinessExpireDate.setText(TimeUtils.getStringByTime(userInfo.getNextbusinessstartdate()));
            mVB.tvCityName.setText(userInfo.getCityName());
            mVB.tvTotalForcetax.setText("¥" + String.valueOf(orderBean.getTotalForcetax()));
            mVB.tvTotalBusiness.setText("¥" + orderBean.getTotalBusiness());
            mVB.tvTotalAmount.setText("总额:¥" + orderBean.getTotalAmount());
            mVB.tvInsuredmobile.setText(userInfo.getInsuredmobile());
            mVB.tvHolderidcard.setText(userInfo.getHolderidcard());
        } catch (Exception e) {
            ToastUtils.showToast("数据加载错误");
        }
    }

//    private void testDate() {
//        // 测试数据
//        if (RetrofitUtils.getSessionId() == null) {
//            RetrofitUtils.setSessionId(SpUtils.getString(XdConfig.SESSION_ID));
//        }
//        map.put("orderNo", "IO00000165");
//        map.put("addressId", "4");
//        map.put("certPath", "/mnt/ecar-upload/4/JMI7T7G0Q7XTZ6TGFDT4DZU65NK1A09P.jpg");
//        map.put("drivingPath", "/mnt/ecar-upload/4/EILRYIXX10JF2XXO9H50OZ8AXIH58VG0.jpg");
//        map.put("certOtherPath", "/mnt/ecar-upload/4/NSJRT8DA4OG0AP03GBVX3L5IYWVERVLF.jpg");
//    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btNext, this);
        RxViewUtils.onViewClick(mVB.lBtShenfenzhen1, this);
        RxViewUtils.onViewClick(mVB.lBtShenfenzhen2, this);
        RxViewUtils.onViewClick(mVB.lBtJiashizheng, this);
        RxViewUtils.onViewClick(mVB.lBtYingyezhizhao, this);
        RxViewUtils.onViewClick(mVB.lBtKaipiaozhiliao, this);
        RxViewUtils.onViewClick(mVB.lBtSelectAddress, this);

    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.l_bt_select_address:
                new IntentUtils.Builder(mContext)
                        .setIntExtra(XdConfig.EXTRA_REQUEST_VALUE, XdConfig.SELECT_ADDRESS_REQUEST)
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, "certPath")
                        .setTargetActivity(AddressActivity.class)
                        .build().startActivityForResult(XdConfig.SELECT_ADDRESS_REQUEST);
                break;
            case R.id.bt_next:
                if (map.get("orderNo") == null || "".equals(map.get("orderNo"))) {
                    ToastUtils.showToast("获取订单id失败，请返回重试");
                    break;
                }
                if (map.get("addressId") == null || "".equals(map.get("addressId"))) {
                    ToastUtils.showToast("请先选择地址");
                    break;
                }
                if (map.get("certPath") == null || "".equals(map.get("certPath"))) {
                    ToastUtils.showToast("请上传身份证正面");
                    break;
                }
                if (map.get("certOtherPath") == null || "".equals(map.get("certOtherPath"))) {
                    ToastUtils.showToast("请上传身份证反面");
                    break;
                }
                if (map.get("drivingPath") == null || "".equals(map.get("drivingPath"))) {
                    ToastUtils.showToast("请上传驾驶证");
                    break;
                }
                map.put("version", OtherUtil.getVersionName(mContext));
                map.put("timestamp", String.valueOf(System.currentTimeMillis()));
                map.put("appId", XdConfig.APP_ID);
                String sign = null;
                try {
                    if (map.get("sign") != null) {
                        map.remove("sign");
                    }
                    sign = MD5Helper.getSign(map, XdConfig.APP_SECRET, "UTF-8");
                    map.put("sign", sign);
                    mInsuranceViewModel.saveInsuranceData(map).observe(this, orderListGson -> {
                        if (orderListGson != null) {
                            new IntentUtils.Builder(mContext)
                                    .setStringExtra("payAmount", String.valueOf(orderListGson.getPayAmount()))
                                    .setStringExtra("totalPrice", String.valueOf(orderListGson.getTotalPrice()))
                                    .setStringExtra("orderNo", map.get("orderNo"))
                                    .setTargetActivity(PayActivity.class)
                                    .build()
                                    .startActivity(true);
                        } else {
                            ToastUtils.showToast("数据错误");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
            case R.id.l_bt_shenfenzhen1:
                new IntentUtils.Builder(mContext)
                        .setIntExtra(XdConfig.EXTRA_REQUEST_VALUE, XdConfig.PHOTO_SHEN_FEN_ZHENG1)
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, "certOtherPath")
                        .setTargetActivity(UploadActivity.class)
                        .build().startActivityForResult(XdConfig.PHOTO_SHEN_FEN_ZHENG1);
                break;
            case R.id.l_bt_shenfenzhen2:
                new IntentUtils.Builder(mContext)
                        .setIntExtra(XdConfig.EXTRA_REQUEST_VALUE, XdConfig.PHOTO_SHEN_FEN_ZHENG2)
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, "drivingPath")
                        .setTargetActivity(UploadActivity.class)
                        .build().startActivityForResult(XdConfig.PHOTO_SHEN_FEN_ZHENG2);
                break;
            case R.id.l_bt_jiashizheng:
                new IntentUtils.Builder(mContext)
                        .setIntExtra(XdConfig.EXTRA_REQUEST_VALUE, XdConfig.PHOTO_JIA_SHI_ZHENG)
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, "certPath")
                        .setTargetActivity(UploadActivity.class)
                        .build().startActivityForResult(XdConfig.PHOTO_JIA_SHI_ZHENG);
                break;
            case R.id.l_bt_yingyezhizhao:
                new IntentUtils.Builder(mContext)
                        .setIntExtra(XdConfig.EXTRA_REQUEST_VALUE, XdConfig.PHOTO_YING_YE_ZHI_ZHAO)
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, "businessLicensePath")
                        .setTargetActivity(UploadActivity.class)
                        .build().startActivityForResult(XdConfig.PHOTO_YING_YE_ZHI_ZHAO);
                break;
            case R.id.l_bt_kaipiaozhiliao:
                new IntentUtils.Builder(mContext)
                        .setIntExtra(XdConfig.EXTRA_REQUEST_VALUE, XdConfig.PHOTO_KAI_PIAO_ZI_LIAO)
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, "invoicePath")
                        .setTargetActivity(UploadActivity.class)
                        .build().startActivityForResult(XdConfig.PHOTO_KAI_PIAO_ZI_LIAO);
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case XdConfig.SELECT_ADDRESS_REQUEST:
                if (resultCode == IntentUtils.RESULT_CODE_S) {
                    CustomerAddress customerAddress = data.getParcelableExtra(XdConfig.EXTRA_VALUE);
                    map.put("addressId", String.valueOf(customerAddress.getAddressId()));
                    changeCustomerAddress(customerAddress);
                }
                break;
            case XdConfig.PHOTO_SHEN_FEN_ZHENG1:
            case XdConfig.PHOTO_SHEN_FEN_ZHENG2:
            case XdConfig.PHOTO_JIA_SHI_ZHENG:
            case XdConfig.PHOTO_KAI_PIAO_ZI_LIAO:
            case XdConfig.PHOTO_YING_YE_ZHI_ZHAO:
                if (resultCode == IntentUtils.RESULT_CODE_S) {
                    String path = data.getStringExtra(XdConfig.EXTRA_STRING_VALUE);
                    String key = data.getStringExtra(XdConfig.EXTRA_STRING_VALUE_2);
                    map.put(key, path);
                }
                break;
            default:

        }
    }

    /**
     * 修改配送地址
     *
     * @param customerAddress
     */
    private void changeCustomerAddress(CustomerAddress customerAddress) {
        mVB.rlAddress.setVisibility(View.VISIBLE);
        mVB.tvName.setText(customerAddress.getReceiver());
        mVB.tvPhone.setText(customerAddress.getPhoneNo());
        mVB.tvRegion.setText(customerAddress.getRegion() + customerAddress.getAddress());
    }
}

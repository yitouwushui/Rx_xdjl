package cn.ecar.insurance.mvvm.view.act.insurance;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.Cheese;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.City;
import cn.ecar.insurance.dao.bean.SaveQuote;
import cn.ecar.insurance.dao.bean.UserInfo;
import cn.ecar.insurance.dao.gson.InsuranceInfoGson;
import cn.ecar.insurance.databinding.ActivityInsure1Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.InsuranceViewModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

/**
 * @author ding
 */
public class InsureActivity1 extends BaseBindingActivity<ActivityInsure1Binding> implements OnViewClick {

    private PopupWindow mPlatePopup;
    private PopupWindow mCityPopup;
    private int[] windowLocation = new int[2];
    private InsuranceViewModel mInsuranceViewModel;
    private ArrayList<City> insuranceCityList = new ArrayList<>();
    private String cityCode;
    private String cityName;
    private static final int PLATE_LENGTH = 6;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure1;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("车险");
        mVB.etNumber.setText("AS37S1");
        mVB.tvPlate.setText("苏");

    }

    @Override
    protected void initData() {
        mInsuranceViewModel = ViewModelProviders.of(this).get(InsuranceViewModel.class);
        windowLocation[0] = (int) OtherUtil.getScreenWidth(mContext);
        windowLocation[1] = (int) OtherUtil.getScreenHeight(mContext);
        getPlate();
        getInsuranceCityCode();
    }

    private void getInsuranceCityCode() {
        showWaitDialog();
        mInsuranceViewModel.getInsuranceCityCode().observe(this, cityGson -> {
            if (cityGson != null && XdConfig.RESPONSE_T.equals(cityGson.getResponseCode())) {
                insuranceCityList.addAll(cityGson.getInsuranceCitycodeDtoList());
                ArrayList<String> cityString = new ArrayList<>(insuranceCityList.size());
                for (City city : insuranceCityList) {
                    cityString.add(city.getName());
                }
                mCityPopup = initPopupWindow(mContext, cityString, (parent, view, position, id) -> {
                    City city = insuranceCityList.get(position);
                    mVB.tvRegion.setText(city.getName());
                    cityCode = city.getCode();
                    cityName = city.getName();
                    mCityPopup.dismiss();
                });
            } else {
                ToastUtils.showToast("查询城市信息失败,请重试");
            }
            hideWaitDialog();
        });
    }

    /**
     * 获得牌头
     */
    private void getPlate() {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(Cheese.PLATE_HEADER));
        mPlatePopup = initPopupWindow(
                mContext,
                arrayList,
                (parent, view, position, id) -> {
                    mVB.tvPlate.setText(arrayList.get(position));
                    mPlatePopup.dismiss();
                });
    }


    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.lBtPlate, this);
        RxViewUtils.onViewClick(mVB.lBtRegion, this);
        RxViewUtils.onViewClick(mVB.btNext, this);


    }

    /**
     * 初始化
     */
    private PopupWindow initPopupWindow(Context context, ArrayList<String> data,
                                        AdapterView.OnItemClickListener onItemClickListener) {
        View mView = LayoutInflater.from(context).inflate(R.layout.select_popwindow_layout, null);
        PopupWindow popupWindow = new PopupWindow(
                mView,
                (int) OtherUtil.getScreenWidth(context) / 3 * 2,
                (int) OtherUtil.getScreenHeight(context) / 3 * 2,
                true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOnDismissListener(() -> {
            // 设置背景灰度
            backgroundAlpha(1f, (Activity) context);
        });
        ListView listView = mView.findViewById(R.id.select_list);
        listView.setAdapter(new ArrayAdapter<>(context, R.layout.item_select_listview_tv, R.id.tv_content, data));
        listView.setOnItemClickListener(onItemClickListener);
        return popupWindow;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        //0.0-1.0
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.l_bt_plate:
                showPopupWindow(mPlatePopup, mVB.lBtPlate);
                break;
            case R.id.l_bt_region:
                if (insuranceCityList.isEmpty()) {
                    getInsuranceCityCode();
                    break;
                }
                if (mCityPopup != null) {
                    showPopupWindow(mCityPopup, mVB.lBtRegion);
                }
//                new IntentUtils.Builder(mContext)
//                        .setTargetActivity(MutiSelectActivity.class)
//                        .build().startActivityForResult(XdConfig.LOCATION_MUTISELECT_REQUEST);
                break;
            case R.id.bt_next:
                String plate = mVB.tvPlate.getText().toString().trim();
                if ("".equals(plate)) {
                    ToastUtils.showToast("请选择车牌牌头");
                    break;
                }
                String number = mVB.etNumber.getText().toString().trim();
                if ("".equals(number) || number.length() != PLATE_LENGTH) {
                    ToastUtils.showToast("请填写车牌号码,长度为6位");
                }
                if (cityCode == null || cityCode.equals("")) {
                    ToastUtils.showToast("请选择投保地区");
                    break;
                }
                Map<String, String> map = RetrofitUtils.getInstance().getParamsMap(
                        "licenseNo", plate + number
                        , "cityCode", cityCode);
                showWaitDialog();
                mInsuranceViewModel.getInsuranceInfo(map).observe(this, insuranceInfo -> {
                    if (null != insuranceInfo && XdConfig.RESPONSE_T.equals(insuranceInfo.getResponseCode())) {
                        InsuranceInfoGson.ReInfoDtoBean reInfoDtoBean = insuranceInfo.getReInfoDto();
                        if (reInfoDtoBean == null) {
                            ToastUtils.showToast("请求数据失败");
                        } else {
                            SaveQuote saveQuote = reInfoDtoBean.getSaveQuote();
                            UserInfo userInfo = reInfoDtoBean.getUserInfo();
                            userInfo.setCityName(cityName);
                            UserInfo.setInstance(userInfo);
                            new IntentUtils.Builder(mContext)
                                    .setParcelableExtra("SaveQuote", saveQuote)
                                    .setTargetActivity(InsureActivity2.class)
                                    .build().startActivity(true);
                        }
                    } else {
                        ToastUtils.showToast("请求数据失败");
                    }
                    hideWaitDialog();
                });
                break;
            default:
        }
    }

    /**
     * 显示
     *
     * @param popupWindow
     * @param parentView
     */
    private void showPopupWindow(PopupWindow popupWindow, View parentView) {
        if (popupWindow != null) {
//            parentView.getLocationOnScreen(windowLocation);
            popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
//            popupWindow.showAtLocation(
//                    parentView,
//                    Gravity.NO_GRAVITY,
//                    windowLocation[0],
//                    windowLocation[1] + mVB.lBtPlate.getHeight());
            backgroundAlpha(0.5f, this);
        }
    }
}

package cn.ecar.insurance.mvvm.view.act.insurance;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.list.SelectCateAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.CategoryBean;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.dao.bean.SubmitInsurance;
import cn.ecar.insurance.dao.bean.UserInfo;
import cn.ecar.insurance.dao.gson.CateMapGson;
import cn.ecar.insurance.databinding.ActivityInsure3Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.viewmodel.custom.InsuranceViewModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.encrypt.MD5Helper;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.TimeUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;
import cn.ecar.insurance.widget.dialog.SelectDialog;

/**
 * @author ding
 */
public class InsureActivity3 extends BaseBindingActivity<ActivityInsure3Binding> implements OnViewClick, CompoundButton.OnCheckedChangeListener {

    private String forceStartDate = "";
    private String businessStartDate = "";
    private InsuranceViewModel mInsuranceViewModel;
    private SelectDialog<CategoryBean.CategorySecond> mSelectDialog;
    private SelectCateAdapter mSelectCateAdapter;
    private HashMap<Integer, List<CategoryBean.CategorySecond>> categoryBeanMap;
    private SubmitInsurance submitInsurance;
    private UserInfo userInfo;
    /**
     * 当前对话框险种类型
     * 1:交强险
     * 2:玻璃单独破碎险
     * 3:涉水行驶损失险
     * 4:车身划痕损失险
     * 5:车上司机责任险
     * 6:车上乘客责任险
     * 7:机动车损失保险
     * 8:全车盗抢保险
     * 9:第三者责任保险
     * 10:自燃损失险
     */
    private int currentType = 0;

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure3;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("方案选择");
        userInfo = UserInfo.getInstance();
        businessStartDate = TimeUtils.getStringByDate(userInfo.getNextbusinessstartdate());
        mVB.tvNextbusinessstartdate.setText(businessStartDate);
        mVB.tvForceStartDate.setText(forceStartDate);
    }

    @Override
    protected void initData() {
        submitInsurance = new SubmitInsurance();
        submitInsurance.setName(userInfo.getLicenseowner());
        submitInsurance.setHolderIdCard(userInfo.getHolderidcard());
        submitInsurance.setPhone(userInfo.getInsuredmobile());
        submitInsurance.setRegisterDate(TimeUtils.getStringByDate(userInfo.getRegisterdate()));
        submitInsurance.setCityCode(userInfo.getCitycode());
        submitInsurance.setLicenseNo(userInfo.getLicenseno());
        submitInsurance.setEngineNo(userInfo.getEngineno());
        submitInsurance.setCarVin(userInfo.getCarvin());
        submitInsurance.setMoldName(userInfo.getModlename());

        String cateValue = "0";
        submitInsurance.setCheSun(cateValue);
        submitInsurance.setDaoQiang(cateValue);
        submitInsurance.setZiRan(cateValue);
        submitInsurance.setSheShui(cateValue);
        submitInsurance.setForceTax(cateValue);
        submitInsurance.setBoLi(cateValue);
        submitInsurance.setHuaHen(cateValue);
        submitInsurance.setSiJi(cateValue);
        submitInsurance.setChengKe(cateValue);
        submitInsurance.setSanZhe(cateValue);

        mInsuranceViewModel = ViewModelProviders.of(this).get(InsuranceViewModel.class);
        mInsuranceViewModel.getInsuranceOfferList(RetrofitUtils.getInstance().getParamsMap("businessExpireDate", businessStartDate))
                .observe(this, cateMapGson -> {
                    if (cateMapGson != null && XdConfig.RESPONSE_T.equals(cateMapGson.getResponseCode())) {
                        loadData(cateMapGson);
                    } else {
                        ToastUtils.showToast("数据请求失败");
                    }
                });
    }

    /**
     * 处理相关方案数据
     *
     * @param cateMapGson
     */
    private void loadData(CateMapGson cateMapGson) {
        businessStartDate = cateMapGson.getBusinessStartDate();
        forceStartDate = cateMapGson.getForceStartDate();
        mVB.tvNextbusinessstartdate.setText(businessStartDate);
        mVB.tvForceStartDate.setText(forceStartDate);
        ArrayList<CategoryBean> categoryBeanArrayList = (ArrayList<CategoryBean>) cateMapGson.getCateAndValuesDtoList();
        categoryBeanMap = new HashMap<>(categoryBeanArrayList.size());
        for (CategoryBean categoryBean : categoryBeanArrayList) {
            categoryBeanMap.put(categoryBean.getCateId(), categoryBean.getCategoriesValuesDtoLit());
        }
        mSelectCateAdapter = new SelectCateAdapter(this, new ArrayList<>());
        mSelectDialog = new SelectDialog<>(
                this,
                mSelectCateAdapter,
                (parent, view, position, id) -> {
                    CategoryBean.CategorySecond categorySecond = (CategoryBean.CategorySecond) parent.getAdapter().getItem(position);
//                    ToastUtils.showToast(categorySecond.getCateValue());
                    saveSelectCategory(categorySecond);
                    mSelectDialog.cancel();
                });
    }

    /**
     * 保存相应数据
     *
     * @param categorySecond
     */
    private void saveSelectCategory(CategoryBean.CategorySecond categorySecond) {
        switch (categorySecond.getCateId()) {
            case 1:
                mVB.tvJiaoqinagxian.setText(categorySecond.getName());
                submitInsurance.setForceTax(categorySecond.getCateValue());
                break;
            case 2:
                mVB.tvBoli.setText(categorySecond.getName());
                submitInsurance.setBoLi(categorySecond.getCateValue());
                break;
            case 4:
                mVB.tvHuaheng.setText(categorySecond.getName());
                submitInsurance.setHuaHen(categorySecond.getCateValue());
                break;
            case 5:
                mVB.tvSiji.setText(categorySecond.getName());
                submitInsurance.setSiJi(categorySecond.getCateValue());
                break;
            case 6:
                mVB.tvChengke.setText(categorySecond.getName());
                submitInsurance.setChengKe(categorySecond.getCateValue());
                break;
            case 9:
                mVB.tvSanzhe.setText(categorySecond.getName());
                submitInsurance.setSanZhe(categorySecond.getCateValue());
                break;
            default:
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String cateValue = isChecked ? "1" : "0";
        switch (buttonView.getId()) {
            case R.id.cb_cheliangsunshi:
                submitInsurance.setCheSun(cateValue);
                break;
            case R.id.cb_quancheqiangdao:
                submitInsurance.setDaoQiang(cateValue);
                break;
            case R.id.cb_ziransunshi:
                submitInsurance.setZiRan(cateValue);
                break;
            case R.id.cb_sheshui:
                submitInsurance.setSheShui(cateValue);
                break;
            default:
        }
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btNext, this);
        RxViewUtils.onViewClick(mVB.lBtBoli, this);
        RxViewUtils.onViewClick(mVB.lBtChengke, this);
        RxViewUtils.onViewClick(mVB.lBtHuaheng, this);
        RxViewUtils.onViewClick(mVB.lBtSanzhe, this);
        RxViewUtils.onViewClick(mVB.lBtSiji, this);
        RxViewUtils.onViewClick(mVB.lBtJiaoqiangxiang, this);
//        RxViewUtils.onViewClick(mVB.lBtZhidingxiulichang, this);

    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        int type = -1;
        switch (view.getId()) {
            case R.id.bt_next:
                submit();
                break;
            case R.id.l_bt_jiaoqiangxiang:
                type = 1;
                break;
            case R.id.l_bt_boli:
                type = 2;
                break;
            case R.id.l_bt_huaheng:
                type = 4;
                break;
            case R.id.l_bt_siji:
                type = 5;
                break;
            case R.id.l_bt_chengke:
                type = 6;
                break;
            case R.id.l_bt_sanzhe:
                type = 9;
                break;
            default:
        }
        if (type != -1 && mSelectDialog != null) {
            mSelectCateAdapter.setmDatas(categoryBeanMap.get(type));
            mSelectCateAdapter.notifyDataSetChanged();
            currentType = type;
            mSelectDialog.show();
        }
    }

    private void submit() {
        try {
            showWaitDialog();
            submitInsurance.setTimestamp(String.valueOf(System.currentTimeMillis()));
            submitInsurance.setVersion(OtherUtil.getVersionName(mContext));
            submitInsurance.setAppId(XdConfig.APP_ID);
            Map<String, String> map = RetrofitUtils.objectToArrayMap(submitInsurance);
            String sign = MD5Helper.getSign(map, XdConfig.APP_SECRET, "UTF-8");
            map.put("sign", sign);
            mInsuranceViewModel.submitCase(map).observe(this, orderListGson -> {
                if (XdConfig.RESPONSE_T.equals(orderListGson.getResponseCode())) {
                    ArrayList<OrderBean> orderBeans = (ArrayList<OrderBean>) orderListGson.getOrderList();
                    hideWaitDialog();
                    if (orderBeans == null || orderBeans.isEmpty()) {
                        ToastUtils.showToast("没有获取到相关保险方案");
                    } else {
                        new IntentUtils.Builder(mContext)
                                .setStringExtra("LicenseNo", submitInsurance.getLicenseNo())
                                .setParcelableArrayLsitExtra(XdConfig.EXTRA_ARRAY_VALUE, orderBeans)
                                .setTargetActivity(InsureActivity4.class)
                                .build().startActivity(true);
                    }
                } else {
                    hideWaitDialog();
                    ToastUtils.showToast(orderListGson.getResponseMsg());
                }
            });
        } catch (Exception e) {
            ToastUtils.showToast("程序出错啦");
        }

    }
}

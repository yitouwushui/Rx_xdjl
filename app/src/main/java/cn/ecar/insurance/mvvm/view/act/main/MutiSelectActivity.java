package cn.ecar.insurance.mvvm.view.act.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.city.CityAdapter;
import cn.ecar.insurance.adapter.city.HotCityAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActivityMutiSelectBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.model.CityModel;
import cn.ecar.insurance.mvvm.viewmodel.CityViewModel;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;

import static cn.ecar.insurance.config.Cheese.LETTERS;

/**
 * @author lq
 * @date 2017/11/29
 */
public class MutiSelectActivity extends BaseBindingActivity<ActivityMutiSelectBinding> {

    private String type = "";
    private String title = "";
    private List<CityModel> mCityList = new ArrayList<>();
    private ArrayList<String> selectList = new ArrayList<>();
    private HotCityAdapter selectAdapter;
    private GridView mGvHotCity;

    @Override
    public void getBundleExtras(Bundle extras) {
        type = extras.getString("type");
        title = extras.getString("title");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_muti_select;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("城市列表");
        View headView = View.inflate(this, R.layout.item_citylist_headview, null);
        mGvHotCity = headView.findViewById(R.id.gv_hotcity);
        mVB.lvLocation.addHeaderView(headView);
        mVB.buttonConfrim.setVisibility(View.VISIBLE);
        String[] oldCity = title.split(",");
        for (int i = 0; i < oldCity.length; i++) {
            if (!oldCity[i].equals("")) {
                selectList.add(oldCity[i].trim());
            }
        }
        mVB.sidrbar.setLetters(LETTERS);
    }

    @Override
    protected void initData() {
        CityViewModel cityViewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        cityViewModel.getCityList().observe(this, list -> {
            mCityList = list;
            CityAdapter cityAdapter = new CityAdapter(mContext, R.layout.item_citylist, list);
            mVB.lvLocation.setAdapter(cityAdapter);
        });
        cityViewModel.getHotCityList().observe(this, list -> {
            HotCityAdapter hotCityAdapter = new HotCityAdapter(mContext, R.layout.item_hotcity, list);
            mGvHotCity.setAdapter(hotCityAdapter);
            hotCityAdapter.setItemClick((item, position) -> setDataAndFinishAct(item));
        });

        selectAdapter = new HotCityAdapter(mContext, R.layout.item_hotcity, selectList);
        mVB.gvHotcity.setAdapter(selectAdapter);
        mVB.gvHotcity.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            selectList.remove(position);
            selectAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void initEvent() {
        //侧边字母
        mVB.sidrbar.setOnLetterUpdateListener(letter -> {
            int size = mCityList.size();
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    mVB.lvLocation.setSelection(0);
                }
                String first = mCityList.get(i).getPinyin().charAt(0) + "";
                if (TextUtils.equals(first, letter)) {
                    mVB.lvLocation.setSelection(i + 1);
                    break;
                }
                showIndex(letter);
            }
        });

        RxViewUtils.onAdapterItemClick(mVB.lvLocation, 1, i -> {
            CityModel city = mCityList.get(i - 1);
            String cityName = city.getName();
            setDataAndFinishAct(cityName);
        });

        RxViewUtils.onViewClick(mVB.buttonConfrim, () -> {
            saveOrUpdateCity();
        });

        RxViewUtils.onViewClick(mVB.includeToolbar.linearBack, () -> {
            finishActivity();
        });
    }

    private void saveOrUpdateCity() {
        String temp = "";
        for (int i = 0; i < selectList.size(); i++) {
            temp = temp + selectList.get(i).trim() + ",";
        }

        if (type != null && !type.equals("")) {

        } else {
            new IntentUtils.Builder(mContext)
                    .setStringArrayListExtra(XdConfig.LOCATION_RESULT, selectList)
                    .build().setResultWithFinishUi(RESULT_OK);
        }
    }

    private void showIndex(String letter) {
        mVB.tvCurrentIndeax.setVisibility(View.VISIBLE);
        mVB.tvCurrentIndeax.setText(letter);
        mVB.tvCurrentIndeax.postDelayed(() -> mVB.tvCurrentIndeax.setVisibility(View.GONE), 250);
    }

    /**
     * 回传数据
     *
     * @param cityName
     */
    private void setDataAndFinishAct(final String cityName) {
//        if (CustomUtils.contains(selectList, cityName)) {
//            for (int i = 0; i < selectList.size(); i++) {
//                if (selectList.get(i).contains(cityName) || cityName.contains(selectList.get(i))) {
//                    selectList.remove(selectList.get(i));
//                }
//            }
//            selectAdapter.notifyDataSetChanged();
//        } else if (selectList.size() > 2) {
//            ToastUtils.showToast("覆盖城市做多只能选择三个");
//        } else {
//            selectList.add(cityName);
//            selectAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    protected void destroyView() {

    }
}

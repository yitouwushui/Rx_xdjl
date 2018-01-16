package cn.ecar.insurance.adapter.city;

import android.content.Context;
import android.text.TextUtils;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.mvvm.model.CityModel;


/**
 * Created by yx on 2017/8/13.
 * 城市适配器
 */

public class CityAdapter extends CommonAdapter<CityModel> {

    private List<CityModel> mCityList;

    public CityAdapter(Context context, int layoutId, List<CityModel> datas) {
        super(context, layoutId, datas);
        mCityList = datas;
    }

    @Override
    protected void convert(ViewHolder viewHolder, CityModel city, int i) {
        String currentLetter = city.getPinyin().charAt(0) + "";
        String letter = null;
        if (i == 0) {
            //首位
            letter = currentLetter;
        } else {
            // 当前首字母和上一个首字母不同
            String preLetter = mCityList.get(i - 1).getPinyin().charAt(0) + "";
            if (!TextUtils.equals(currentLetter, preLetter)) {
                letter = currentLetter;
            }
        }
        viewHolder.setVisible(R.id.tv_index, letter != null);
        viewHolder.setText(R.id.tv_index, letter);
        viewHolder.setText(R.id.tv_cityName, city.getName());
    }
}

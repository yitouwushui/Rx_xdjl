package cn.ecar.insurance.adapter.city;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;


/**
 * Created by yx on 2017/8/13.
 * 热门城市适配器
 */

public class HotCityAdapter extends CommonAdapter<String> {

    private OnAdaperItemClick mItemClick;

    public void setItemClick(OnAdaperItemClick itemClick) {
        mItemClick = itemClick;
    }

    public HotCityAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String city, int position) {
        viewHolder.setText(R.id.btn_hotcity, city);
        viewHolder.setOnClickListener(R.id.btn_hotcity, view ->
                mItemClick.onItemClick(city, position));
    }
}

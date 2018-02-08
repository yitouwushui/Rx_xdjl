package cn.ecar.insurance.adapter.list;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.content.res.AppCompatResources;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.dao.bean.Insurance;

/**
 * @author ding
 * @date 2017/12/20
 */

public class MyInsuranceAdapter extends CommonAdapter<Insurance> {

    private int colorBlue, colorOrange;

    public MyInsuranceAdapter(Context context, int layoutId, List<Insurance> datas) {
        super(context, layoutId, datas);
        colorBlue = ContextCompat.getColor(mContext, R.color.main_blue);
        colorOrange = ContextCompat.getColor(mContext, R.color.color_orange);
    }

    @Override
    protected void convert(ViewHolder holder, Insurance insurance, int position) {
        if (position % 2 == 0) {
            holder.setTextColor(R.id.tv_state, colorBlue);
        } else {
            holder.setTextColor(R.id.tv_state, colorOrange);
        }
//        holder.setImageDrawable(R.id.img_icon,member.getIcon());
//        holder.setText(R.id.tv_name,member.getName());
//        holder.setText(R.id.tv_content,member.getSelectContent());
    }

}


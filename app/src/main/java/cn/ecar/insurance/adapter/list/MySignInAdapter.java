package cn.ecar.insurance.adapter.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.dao.bean.Insurance;
import cn.ecar.insurance.dao.bean.SignIn;

/**
 * @author ding
 * @date 2017/12/20
 */

public class MySignInAdapter extends CommonAdapter<SignIn> {

    private int colorBlue, colorOrange;

    public MySignInAdapter(Context context, int layoutId, List<SignIn> datas) {
        super(context, layoutId, datas);
        colorBlue = ContextCompat.getColor(mContext, R.color.main_blue);
        colorOrange = ContextCompat.getColor(mContext, R.color.color_orange);
    }

    @Override
    protected void convert(ViewHolder holder, SignIn insurance, int position) {
        if (position % 2 == 0) {
            holder.setTextColor(R.id.tv_is_sign, colorBlue);
        } else {
            holder.setTextColor(R.id.tv_is_sign, colorOrange);
        }
//        holder.setImageDrawable(R.id.img_icon,member.getIcon());
//        holder.setText(R.id.tv_name,member.getName());
//        holder.setText(R.id.tv_content,member.getSelectContent());
    }

}


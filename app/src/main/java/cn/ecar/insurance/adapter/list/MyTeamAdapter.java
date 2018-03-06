package cn.ecar.insurance.adapter.list;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.dao.bean.Team;

/**
 * @author ding
 * @date 2017/12/20
 */

public class MyTeamAdapter extends CommonAdapter<Team> {

//    private int colorBlue, colorOrange;

    public MyTeamAdapter(Context context, int layoutId, List<Team> datas) {
        super(context, layoutId, datas);
//        colorBlue = ContextCompat.getColor(mContext, R.color.main_blue);
//        colorOrange = ContextCompat.getColor(mContext, R.color.color_orange);
    }

    @Override
    protected void convert(ViewHolder holder, Team insurance, int position) {
//        if (position % 2 == 0) {
//            holder.setTextColor(R.id.tv_state, colorBlue);
//        } else {
//            holder.setTextColor(R.id.tv_state, colorOrange);
//        }
//        holder.setImageDrawable(R.id.img_icon,member.getIcon());
//        holder.setText(R.id.tv_name,member.getName());
//        holder.setText(R.id.tv_content,member.getSelectContent());
    }

}


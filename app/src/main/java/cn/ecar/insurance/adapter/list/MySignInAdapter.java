package cn.ecar.insurance.adapter.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.dao.bean.SignIn;
import cn.ecar.insurance.utils.ui.TimeUtils;

/**
 * @author ding
 * @date 2017/12/20
 */

public class MySignInAdapter extends CommonAdapter<SignIn> {

    private int colorBlue;
//    private int colorOrange;

    public MySignInAdapter(Context context, int layoutId, List<SignIn> datas) {
        super(context, layoutId, datas);
        colorBlue = ContextCompat.getColor(mContext, R.color.main_blue);
//        colorOrange = ContextCompat.getColor(mContext, R.color.color_orange);
    }

    @Override
    protected void convert(ViewHolder holder, SignIn signIn, int position) {

        holder.setTextColor(R.id.tv_is_sign, colorBlue);
        holder.setText(R.id.tv_date, TimeUtils.getStringByDate(signIn.getSignDate()));
//        holder.setText(R.id.tv_award, String.valueOf(signIn.getLuckdrawTimes()));

    }

}


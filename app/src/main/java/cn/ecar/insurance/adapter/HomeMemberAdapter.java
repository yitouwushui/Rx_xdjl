package cn.ecar.insurance.adapter;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.absrecyclerview.CommonAdapter;
import cn.ecar.insurance.adapter.absrecyclerview.base.ViewHolder;
import cn.ecar.insurance.dao.bean.Customer;

/**
 *
 * @author ding
 * @date 2017/12/20
 */

public class HomeMemberAdapter extends CommonAdapter<Customer> {


    public HomeMemberAdapter(Context context, int layoutId, List<Customer> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Customer customer, int position) {
//        holder.setImageDrawable(R.id.img_icon,member.getIcon());
//        holder.setText(R.id.tv_name,member.getName());
//        holder.setText(R.id.tv_content,member.getSelectContent());
    }

}


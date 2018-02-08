package cn.ecar.insurance.adapter.recycler;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.absrecyclerview.CommonAdapter;
import cn.ecar.insurance.adapter.absrecyclerview.base.ViewHolder;
import cn.ecar.insurance.dao.bean.Customer;

/**
 *
 * @author ding
 * @date 2018/1/10
 */

public class RewardAdapter extends CommonAdapter<Customer> {

    public RewardAdapter(Context context, int layoutId, List<Customer> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Customer customer, int position) {
            holder.setText(R.id.tv_no, (position + 1) + ".");
//            holder.setImageDrawable(R.id.img_icon, member.getIcon());
//            holder.setText(R.id.tv_name, member.getName());
//            holder.setText(R.id.tv_content, member.getSelectContent());
    }

}

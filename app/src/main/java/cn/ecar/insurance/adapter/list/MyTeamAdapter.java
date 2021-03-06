package cn.ecar.insurance.adapter.list;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.dao.bean.Customer;

/**
 * @author ding
 * @date 2017/12/20
 */

public class MyTeamAdapter extends CommonAdapter<Customer> {


    public MyTeamAdapter(Context context, int layoutId, List<Customer> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Customer customer, int position) {
        holder.setText(R.id.tv_name, String.valueOf(customer.getCustomerId()));
        holder.setText(R.id.tv_level1, customer.getFirstQuantity() + "人");
        holder.setText(R.id.tv_level2, customer.getSecondQuantity() + "人");
        holder.setText(R.id.tv_phone, customer.getPhoneNo());
    }

}


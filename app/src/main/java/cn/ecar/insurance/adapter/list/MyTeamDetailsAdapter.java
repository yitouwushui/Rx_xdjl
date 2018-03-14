package cn.ecar.insurance.adapter.list;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.utils.ui.TimeUtils;

/**
 * @author ding
 * @date 2017/12/20
 */

public class MyTeamDetailsAdapter extends CommonAdapter<Customer> {


    public MyTeamDetailsAdapter(Context context, int layoutId, List<Customer> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Customer customer, int position) {
        holder.setText(R.id.tv_name, String.valueOf(customer.getCustomerId()));
        holder.setText(R.id.tv_status, customer.getStatus());
        holder.setText(R.id.tv_registTime, TimeUtils.getStringByDate(customer.getRegistTime()));
        holder.setText(R.id.tv_region, "null");
    }

}


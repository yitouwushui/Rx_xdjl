package cn.ecar.insurance.adapter.recycler;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.absrecyclerview.CommonAdapter;
import cn.ecar.insurance.adapter.absrecyclerview.base.ViewHolder;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.dao.bean.CustomerHeroBean;
import cn.ecar.insurance.dao.gson.CustomerShowGson;

/**
 * @author ding
 * @date 2018/1/10
 */

public class HomeMemberAdapter extends CommonAdapter<CustomerHeroBean> {

    public HomeMemberAdapter(Context context, int layoutId, List<CustomerHeroBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, CustomerHeroBean customer, int position) {
        holder.setText(R.id.tv_name, (position + 1) + "." + customer.getCustomerCode());
        holder.setText(R.id.tv_content, "分享人数" + customer.getShareTimes() + "位");
    }

}
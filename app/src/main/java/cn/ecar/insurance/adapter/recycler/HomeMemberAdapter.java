package cn.ecar.insurance.adapter.recycler;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.absrecyclerview.CommonAdapter;
import cn.ecar.insurance.adapter.absrecyclerview.base.ViewHolder;
import cn.ecar.insurance.dao.bean.CustomerMember;

/**
 * @author ding
 * @date 2018/1/10
 */

public class HomeMemberAdapter extends CommonAdapter<CustomerMember> {
    private RequestOptions options;

    public HomeMemberAdapter(Context context, int layoutId, List<CustomerMember> datas) {
        super(context, layoutId, datas);
        options = new RequestOptions()
                .placeholder(R.drawable.verify_loading)
                .error(R.drawable.verify_loading);
    }

    @Override
    protected void convert(ViewHolder holder, CustomerMember customer, int position) {
        holder.setText(R.id.tv_name, (position + 1) + "." + customer.getName());
        holder.setText(R.id.tv_content, "分享人数" + customer.getShowTimes() + "位");
        Glide.with(mContext).load(customer.getPicPath()).apply(options).into((ImageView) holder.getView(R.id.img_icon));
    }

}
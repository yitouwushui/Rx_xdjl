package cn.ecar.insurance.adapter.list;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.CustomerAddress;
import cn.ecar.insurance.mvvm.view.act.custom.ModifyAddressActivity;
import cn.ecar.insurance.utils.ui.IntentUtils;

/**
 * @author ding
 * @date 2017/12/20
 */

public class CustomerAddressAdapter extends CommonAdapter<CustomerAddress> {

//    private int colorBlue, colorOrange;

    public CustomerAddressAdapter(Context context, int layoutId, List<CustomerAddress> datas) {
        super(context, layoutId, datas);
//        colorBlue = ContextCompat.getColor(mContext, R.color.main_blue);
//        colorOrange = ContextCompat.getColor(mContext, R.color.color_orange);
    }

    @Override
    protected void convert(ViewHolder holder, CustomerAddress customerAddress, int position) {
        holder.setText(R.id.tv_is_default, ("1".equals(customerAddress.getIsDefault()) ? "默认" : ""));
        holder.setText(R.id.tv_name, customerAddress.getReceiver());
        holder.setText(R.id.tv_phone, customerAddress.getPhoneNo());
        holder.setText(R.id.tv_region, customerAddress.getRegion() + customerAddress.getAddress());
        holder.setOnClickListener(R.id.bt_modify, v -> {
            new IntentUtils.Builder(mContext)
                    .setParcelableExtra(XdConfig.EXTRA_VALUE, customerAddress)
                    .setIntExtra(XdConfig.EXTRA_INT_VALUE, position)
                    .setIntExtra(XdConfig.EXTRA_REQUEST_VALUE, XdConfig.ADDRESS_IS_UPDATE_REQUEST)
                    .setTargetActivity(ModifyAddressActivity.class)
                    .build()
                    .startActivityForResult(XdConfig.ADDRESS_IS_UPDATE_REQUEST);
        });
    }

}


package cn.ecar.insurance.adapter.list;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.mvvm.view.act.insurance.InsureActivity4;
import cn.ecar.insurance.mvvm.view.act.insurance.InsureActivity5;
import cn.ecar.insurance.utils.ui.IntentUtils;

/**
 * @author ding
 * @date 2017/12/20
 */

public class BuyInsuranceListAdapter extends CommonAdapter<OrderBean> {

//    private int colorBlue, colorOrange;

    public BuyInsuranceListAdapter(Context context, int layoutId, List<OrderBean> datas) {
        super(context, layoutId, datas);
//        colorBlue = ContextCompat.getColor(mContext, R.color.main_blue);
//        colorOrange = ContextCompat.getColor(mContext, R.color.color_orange);
    }

    @Override
    protected void convert(ViewHolder holder, OrderBean insurance, int position) {
        holder.setText(R.id.tv_name, insurance.getInsuranceName());
        double totalAmount = insurance.getTotalAmount();

        if (insurance.isAmountRequest()) {
            holder.setText(R.id.tv_money, "价格正在加载");
            holder.setEnabled(R.id.tv_money, false);
        } else {
            holder.setEnabled(R.id.tv_money, true);
            if (totalAmount == 0.0f) {
                holder.setText(R.id.tv_money, "点击请求价格");
            } else {
                holder.setText(R.id.tv_money, "¥" + insurance.getTotalAmount());
            }
        }
        holder.setOnClickListener(R.id.bt_details, v -> {
            new IntentUtils.Builder(mContext)
                    .setParcelableExtra(XdConfig.EXTRA_VALUE, insurance)
                    .setTargetActivity(InsureActivity5.class)
                    .build().startActivity(true);
        });

        holder.setOnClickListener(R.id.tv_money, v -> {
            //网络请求
            ((InsureActivity4) mContext).getOrderPrice(insurance.getOrderNo(), position);
        });
    }

}


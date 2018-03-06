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
import cn.ecar.insurance.utils.ui.ToastUtils;

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
            if (insurance.isAmountRequest()) {
                ToastUtils.showToast("正在生成保单，请稍后再试");
            } else {
                new IntentUtils.Builder(mContext)
                        .setStringExtra(XdConfig.EXTRA_STRING_VALUE, insurance.getOrderNo())
                        .setTargetActivity(InsureActivity5.class)
                        .build().startActivity(true);
            }
        });

        holder.setOnClickListener(R.id.tv_money, v -> {
            if (insurance.isAmountRequest()) {
                ToastUtils.showToast("正在查询保单，请稍后再试");
            } else {
                //网络请求
                ((InsureActivity4) mContext).getOrderPrice(insurance.getOrderNo(), position);
            }
        });
    }

}


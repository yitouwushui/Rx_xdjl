package cn.ecar.insurance.adapter.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.utils.ui.TimeUtils;

/**
 * @author ding
 * @date 2017/12/20
 */
public class MyInsuranceAdapter extends CommonAdapter<OrderBean> {

    private int colorBlue, colorOrange;

    public MyInsuranceAdapter(Context context, int layoutId, List<OrderBean> datas) {
        super(context, layoutId, datas);
        colorBlue = ContextCompat.getColor(mContext, R.color.main_blue);
        colorOrange = ContextCompat.getColor(mContext, R.color.color_orange);
    }

    @Override
    protected void convert(ViewHolder holder, OrderBean order, int position) {
        holder.setText(R.id.tv_licenseNo, order.getCarNumber());
        holder.setText(R.id.tv_licenseOwner, order.getName());
        String status = order.getStatus();
        switch (status) {
            case XdConfig.STATUS_SEND:
                status = "询价已发送";
                holder.setTextColor(R.id.tv_state, colorOrange);
                break;
            case XdConfig.STATUS_BACK_SUCESS:
                status = "询价返回成功";
                holder.setTextColor(R.id.tv_state, colorOrange);
                break;
            case XdConfig.STATUS_BACK_FAIL:
                status = "询价失败";
                holder.setTextColor(R.id.tv_state, colorOrange);
                break;
            case XdConfig.STATUS_CONFIRM:
                status = "保险订单确认";
                holder.setTextColor(R.id.tv_state, colorOrange);
                break;
            case XdConfig.STATUS_PAY_SUCESS:
                status = "保险支付成功";
                holder.setTextColor(R.id.tv_state, colorOrange);
                break;
            case XdConfig.STATUS_PAY_FAIL:
                status = "保险支付失败";
                holder.setTextColor(R.id.tv_state, colorOrange);
                break;
            case XdConfig.STATUS_FINISH:
                status = "订单完成";
                holder.setTextColor(R.id.tv_state, colorBlue);
                break;
            default:
        }
        holder.setText(R.id.tv_state, status);
        holder.setText(R.id.tv_amount, String.valueOf(order.getTotalAmount()));
        holder.setText(R.id.tv_date, TimeUtils.getStringByTime(order.getCreateTime()));
    }

}


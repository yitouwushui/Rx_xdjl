package cn.ecar.insurance.adapter.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.dao.bean.FrozenCash;
import cn.ecar.insurance.dao.bean.FundsFlow;
import cn.ecar.insurance.dao.gson.FundsFlowGson;
import cn.ecar.insurance.utils.ui.TimeUtils;

/**
 * @author ding
 * @date 2017/12/20
 */
public class FundsAdapter extends CommonAdapter<FundsFlow> {


    public FundsAdapter(Context context, int layoutId, List<FundsFlow> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, FundsFlow fundsFlow, int position) {
        holder.setText(R.id.tv_order_no, String.valueOf(fundsFlow.getFundioOrderSn()));
        holder.setText(R.id.tv_name, String.valueOf(fundsFlow.getAccountId()));
        holder.setText(R.id.tv_yongjin, String.valueOf(fundsFlow.getFundioOrderId()));
        holder.setText(R.id.tv_amount, String.valueOf(fundsFlow.getAmount()));
        holder.setText(R.id.tv_date, TimeUtils.getStringByDate(fundsFlow.getOrderTime()));
    }

}

